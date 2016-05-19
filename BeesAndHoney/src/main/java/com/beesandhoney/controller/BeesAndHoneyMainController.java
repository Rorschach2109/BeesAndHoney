/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.controller;

import com.beesandhoney.browser.IWebBrowser;
import com.beesandhoney.browser.WebBrowser;
import com.beesandhoney.model.AccountModel;
import com.beesandhoney.model.BankAccount;
import com.beesandhoney.model.BankAccountLogin;
import com.beesandhoney.model.BankAccountOwner;
import com.beesandhoney.model.BankingBookModel;
import com.beesandhoney.model.CreditCardModel;
import com.beesandhoney.model.dao.BankAccountLoginDao;
import com.beesandhoney.model.dao.BankAccountOwnerDao;
import com.beesandhoney.model.dao.DaoModelFactory;
import com.beesandhoney.statemachine.AddAccountStageState;
import com.beesandhoney.statemachine.DeleteStageState;
import com.beesandhoney.statemachine.EditAccountStageState;
import com.beesandhoney.statemachine.SecondStageStateInterface;
import com.beesandhoney.utils.ObserverInterface;
import com.beesandhoney.view.AddAccountView;
import com.beesandhoney.view.BeesAndHoney;
import com.beesandhoney.view.BeesAndHoneyMainView;
import com.beesandhoney.view.DeleteAccountView;
import com.beesandhoney.view.AccountDetailsView;
import com.beesandhoney.view.CreditCardDetailsView;
import com.beesandhoney.view.IObservableView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.hibernate.Session;

public class BeesAndHoneyMainController implements IController, ObserverInterface {
    
    private BeesAndHoney application;

    private Stage secondStage;
    private Stage browserStage;
    private SecondStageStateInterface currentState;

    private IObservableView observableView;
    
    private int currentBankAccountLoginIndex;

    private Set<BankAccount> stashedBankAccounts;
    private ArrayList<BankAccountLogin> bankAccountLogins;

    private IWebBrowser webBrowser;
            
    private final BeesAndHoneyMainView mainView;
    
    private static final String ADD_ACCOUNT_VIEW_RESOURCE_PATH;
    private static final String DELETE_ACCOUNT_VIEW_RESOURCE_PATH;
    private static final String ACCOUNT_DETAILS_VIEW_RESOURCE_PATH;
    private static final String CREDIT_CARD_DETAILS_VIEW_RESOURCE_PATH;
    
    static
    {
        ADD_ACCOUNT_VIEW_RESOURCE_PATH = "views/AddAccountView.fxml";
        DELETE_ACCOUNT_VIEW_RESOURCE_PATH = "views/DeleteAccountView.fxml";
        ACCOUNT_DETAILS_VIEW_RESOURCE_PATH = "views/AccountDetailsView.fxml";
        CREDIT_CARD_DETAILS_VIEW_RESOURCE_PATH = "views/CreditCardDetailsView.fxml";
    }
    
    public BeesAndHoneyMainController(BeesAndHoneyMainView mainView) {
        this.mainView = mainView;
        
        this.currentBankAccountLoginIndex = 0;
        this.stashedBankAccounts = new HashSet<>();
        this.bankAccountLogins = new ArrayList<>();
    }
    
    public void initialize() {
        this.mainView.setCurrentUser(this.application.getUserLogin());
        this.mainView.setLastAccessDate(this.application.getLastAccessDate());
        this.mainView.updateTables();
        
        initializeWebBrowser();
    }
    
    public String getCurrentUserLogin() {
        return this.application.getUserLogin();
    }
    
    public IObservableView getCurrentSecondStageView() {
        return this.observableView;
    }
    
    public void handleEditBankingBookItem(BankingBookModel selectedItem) {
        this.currentState = new EditAccountStageState(this);
        showSecondStage(ADD_ACCOUNT_VIEW_RESOURCE_PATH);
        
        AddAccountView addAccountView = (AddAccountView) this.observableView;
        AbstractBankAccountLoginController controller = new EditAccountController(this.application);
        addAccountView.setController(controller);
        
        addAccountView.fillBankingBookModel(selectedItem);
    }
    
    public void handleAddBankingBookItem() {
        this.currentState = new AddAccountStageState(this);
        showSecondStage(ADD_ACCOUNT_VIEW_RESOURCE_PATH);
        
        AbstractBankAccountLoginController controller = new AddAccountController(this.application);
        this.observableView.setController(controller);
    }
    
    public void handleDeleteBankingBookItem(BankingBookModel selectedItem) {
        this.currentState = new DeleteStageState(this);
        showSecondStage(DELETE_ACCOUNT_VIEW_RESOURCE_PATH);
        
        DeleteAccountView deleteAccountView = (DeleteAccountView) this.observableView;
        deleteAccountView.setItemToDelete(selectedItem);
        deleteAccountView.getController().setApplication(this.application);
    }
    
    public void handleShowAccountDetails(AccountModel selectedItem) {
        showSecondStage(ACCOUNT_DETAILS_VIEW_RESOURCE_PATH);
        
        AccountDetailsView detailsView = (AccountDetailsView) this.observableView;
        detailsView.fillAccountDetails(selectedItem);
    }
    
    public void handleShowCreditCardDetails(CreditCardModel selectedItem) {
        showSecondStage(CREDIT_CARD_DETAILS_VIEW_RESOURCE_PATH);
        
        CreditCardDetailsView detailsView = (CreditCardDetailsView) this.observableView;
        detailsView.fillAccountDetails(selectedItem);
    }
    
    public void handleShowSummaryDetails() {
        
    }
    
    public void handleRefreshBankingBookTable() {
        getAccountInformation();
        openWebBrowser();
        nextAccountInformation();
    }
    
    public void nextAccountInformation() {
        if (this.currentBankAccountLoginIndex < this.bankAccountLogins.size()) {
            BankAccountLogin nextBankAccountLogin = this.bankAccountLogins.get(this.currentBankAccountLoginIndex);
            this.webBrowser.getAccountInformation(nextBankAccountLogin);
            ++this.currentBankAccountLoginIndex;
        } else {
            destroyWebBrowser();
            insertBankAccounts();
        }
    }

    public void stashAccountInformation(BankAccount bankAccount) {
        this.stashedBankAccounts.add(bankAccount);
    }
    
    @Override
    public void update() {
        if (null != this.currentState) {
            this.currentState.handleBeforeExit();
        }
        this.secondStage.close();

        this.secondStage = null;
        this.observableView = null;
        this.currentState = null;
    }
    
    @Override
    public void setApplication(BeesAndHoney application) {
        this.application = application;
        initialize();
    }
    
    private void showSecondStage(String viewResourcePath) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getClassLoader()
                .getResource(viewResourcePath));

            Pane pane = fxmlLoader.load();
            
            this.observableView = fxmlLoader.getController();
            this.observableView.registerObserver(this);
            
            Scene scene = new Scene(pane);
            
            this.secondStage = new Stage();
            this.secondStage.initModality(Modality.WINDOW_MODAL);
            this.secondStage.initOwner(this.application.getCurrentScene().getWindow());
            this.secondStage.setTitle(this.application.getClass().getSimpleName());
            this.secondStage.setResizable(false);
            this.secondStage.setScene(scene);
            this.secondStage.show();
            
        } catch (IOException e) {
            
        }
    }
    
    private void openWebBrowser() {
        initializeWebBrowser();
        Scene browserScene = new Scene((Region) this.webBrowser);
        
        this.browserStage = new Stage();
        
        this.browserStage.setScene(browserScene);
        this.browserStage.show();
        
        //this.browserStage.hide();        
    }
    
    private void getAccountInformation() {
        BankAccountLoginDao dao = DaoModelFactory.getBankAccountLoginDaoInstance();
        
        Session session = dao.openSession();
        for (BankingBookModel bankingBookModel : this.mainView.getBankingBookTableItems()) {

            BankAccountLogin accountLogin = dao.findByClientId_Alias_UserLogin(
                    bankingBookModel.getClientId(), 
                    bankingBookModel.getAlias(),
                    this.application.getUserLogin(), 
                    session);

            this.bankAccountLogins.add(accountLogin);
            
        }
        
        dao.closeSession(session);
    }
    
    private void insertBankAccounts() {
        BankAccountOwnerDao dao = DaoModelFactory.getBankAccountOwnerDaoInstance();
        Session session = dao.openSessionWithTransaction();
        
        for (BankAccount bankAccount : this.stashedBankAccounts) {
            BankAccountOwner existingOwner = dao.read(bankAccount.getBankAccountOwner().getOwnerNameSurname(), session);
            
            if (null != existingOwner) {
                insertBankAccountToExistingOwner(existingOwner, bankAccount, dao, session);
            } else {
                insertNewBankAccountOwner(bankAccount, dao, session);
            }
        }
        
        dao.closeSessionWithTransaction(session);
        this.stashedBankAccounts.clear();
    }
    
    private void initializeWebBrowser() {
        this.webBrowser = new WebBrowser();
        this.webBrowser.initialize(this);
    }
    
    private void destroyWebBrowser() {
        this.browserStage.close();
        this.browserStage = null;
        this.webBrowser = null;
    }
    
    private void insertNewBankAccountOwner(BankAccount bankAccount, BankAccountOwnerDao dao, Session session) {
        BankAccountOwner owner = bankAccount.getBankAccountOwner();
        owner.getBankAccounts().add(bankAccount);
        dao.create(bankAccount.getBankAccountOwner(), session);
    }
    
    private void insertBankAccountToExistingOwner(BankAccountOwner existingOwner, 
            BankAccount bankAccount, BankAccountOwnerDao dao, Session session) {
        Set<BankAccount> bankAccounts = existingOwner.getBankAccounts();
        
        if (bankAccounts.contains(bankAccount)) {
            editBankAccount(bankAccount, bankAccounts);
        } else {
            bankAccounts.add(bankAccount);
        }

        existingOwner.setOwnerAddress(bankAccount.getBankAccountOwner().getOwnerAddress());
        existingOwner.setBankAccounts(bankAccounts);
        
        dao.update(existingOwner, session);
    }
    
    private void editBankAccount(BankAccount bankAccount, Set<BankAccount> bankAccounts) {
        for (Iterator<BankAccount> it = bankAccounts.iterator(); it.hasNext(); ) {
            BankAccount existingBankAccount = it.next();
            if (existingBankAccount.equals(bankAccount)) {
                existingBankAccount.setAccountName(bankAccount.getAccountName());
                existingBankAccount.setAccountBalance(bankAccount.getAccountBalance());
                existingBankAccount.setAvailableSources(bankAccount.getAvailableSources());
                existingBankAccount.setAccountLimit(bankAccount.getAccountLimit());
            }
        }
    }
}
