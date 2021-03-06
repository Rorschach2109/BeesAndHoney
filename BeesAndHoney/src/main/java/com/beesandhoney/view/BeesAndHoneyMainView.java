/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.view;

import com.beesandhoney.controller.BeesAndHoneyMainController;
import com.beesandhoney.controller.IController;
import com.beesandhoney.model.AccountModel;
import com.beesandhoney.model.BankAccount;
import com.beesandhoney.model.BankAccountLogin;
import com.beesandhoney.model.BankingBookModel;
import com.beesandhoney.model.BeesAndHoneyUser;
import com.beesandhoney.model.CreditCardModel;
import com.beesandhoney.model.ModelFactory;
import com.beesandhoney.model.dao.BankAccountDao;
import com.beesandhoney.model.dao.BeesAndHoneyUserDao;
import com.beesandhoney.model.dao.DaoModelFactory;
import com.beesandhoney.utils.ObserverInterface;
import com.beesandhoney.utils.constants.BankAccountConstants.BankAccountType;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import org.hibernate.Session;

public class BeesAndHoneyMainView implements IView, ObserverInterface {
    
    @FXML
    private BorderPane mainView;
    
    @FXML
    private TabPane mainViewTabPane;
        
    @FXML
    private Label currentUserLabel;
    
    @FXML
    private Label lastAccessDateLabel;
    
    @FXML
    private TableView<BankingBookModel> bankingBookTable;
    @FXML
    private TableView<AccountModel> accountsTable;
    @FXML
    private TableView<CreditCardModel> creditCardsTable;
    
    private final BeesAndHoneyMainController mainController;
    
    public BeesAndHoneyMainView() {
        this.mainController = new BeesAndHoneyMainController(this);
    }
    
    public void setDisableFlag(boolean disableFlag) {
        this.mainView.setDisable(disableFlag);
    }
    
    public void setCurrentUser(String currentUserLogin) {
        this.currentUserLabel.setText(currentUserLogin);
    }
    
    public void setLastAccessDate(String lastAccessDate) {
        this.lastAccessDateLabel.setText(lastAccessDate);
    }
    
    public ObservableList<BankingBookModel> getBankingBookTableItems() {
        return this.bankingBookTable.getItems();
    }
    
    public ObservableList<AccountModel> getAccountTableItems() {
        return this.accountsTable.getItems();
    }
    
    public ObservableList<CreditCardModel> getCreditCardTableItems() {
        return this.creditCardsTable.getItems();
    }
    
    public void updateTables() {
        updateBankingBookTable();
        updateAccountsTable();
        updateCreditCardsTable();
    }
    
    @Override
    public void cleanUp() {
        
    }
    
    @Override
    public IController getController() {
        return this.mainController;
    }

    @Override
    public void setController(IController controller) {
        throw new UnsupportedOperationException("Not supported.");
    }
    
    @Override
    public void update() {
        updateTables();
    }
    
    private BankingBookModel getSelectedBankingBookModel() {
        return this.bankingBookTable.getSelectionModel().getSelectedItem();
    }
    
    private AccountModel getSelectedAccountModel() {
        return this.accountsTable.getSelectionModel().getSelectedItem();
    }
    
    private CreditCardModel getSelectedCreditCardModel() {
        return this.creditCardsTable.getSelectionModel().getSelectedItem();
    }
    
    private void updateAccountsTable() {
        BankAccountDao dao = DaoModelFactory.getBankAccountDaoInstance();
        Session session = dao.openSession();
        
        ObservableList<AccountModel> accountModelTableContent = 
                this.accountsTable.getItems();
        
        accountModelTableContent.clear();
        
        for (BankingBookModel bookModel : getBankingBookTableItems()) {
            for (BankAccount bankAccount : dao.findByClientIdAndAccountType(
                    bookModel.getClientId(), BankAccountType.BANK_ACCOUNT, session)) {
                accountModelTableContent.add(ModelFactory.createAccountModel(
                        bookModel.getAlias(), 
                        bankAccount.getAccountName(), 
                        bankAccount.getAvailableSources(),
                        bankAccount.getAccountNumber(),
                        bankAccount.getCurrency()));
            }
        }
        
        dao.closeSession(session);
    }
    
    private void updateCreditCardsTable() {
        BankAccountDao dao = DaoModelFactory.getBankAccountDaoInstance();
        Session session = dao.openSession();
        
        ObservableList<CreditCardModel> creditCardModelTableContent = 
                this.creditCardsTable.getItems();
        
        creditCardModelTableContent.clear();
        
        for (BankingBookModel bookModel : getBankingBookTableItems()) {
            for (BankAccount bankAccount : dao.findByClientIdAndAccountType(
                    bookModel.getClientId(), BankAccountType.CREDIT_CARD, session)) {
                creditCardModelTableContent.add(ModelFactory.createCreditCardModel(
                        bookModel.getAlias(), 
                        bankAccount.getAccountName(), 
                        bankAccount.getAccountLimit(),
                        bankAccount.getAvailableSources(),
                        bankAccount.getAccountNumber(),
                        bankAccount.getCurrency()));
            }
        }
        
        dao.closeSession(session);
    }
    
    private void updateBankingBookTable() {
        BeesAndHoneyUserDao dao = DaoModelFactory.getBeesAndHoneyUserDaoInstance();
        Session session = dao.openSession();
        
        BeesAndHoneyUser currentUser = dao.findByUserName(
                this.mainController.getCurrentUserLogin(), session
        );
        
        ObservableList<BankingBookModel> bankingBookTableContent = 
                this.bankingBookTable.getItems();
        
        bankingBookTableContent.clear();
        
        for (BankAccountLogin log : currentUser.getBankAccountLogins()) {
            bankingBookTableContent.add(ModelFactory.createBankingBookModel(
                    log.getBankAccountLoginAlias(),
                    log.getBank().getBankName(),
                    log.getClientId())
            );
        }
        
        dao.closeSession(session);
    }

    private void initListeners() {
        this.accountsTable.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldSelection, newSelection) -> {
                    if (null != newSelection) {
                        this.creditCardsTable.getSelectionModel().clearSelection();
                    }
                });
        
        this.creditCardsTable.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldSelection, newSelection) -> {
                    if (null != newSelection) {
                        this.accountsTable.getSelectionModel().clearSelection();
                    }
                });
    }
    
    @FXML
    private void handleEditButtonClicked() {
        BankingBookModel selectedBankingBookModel = getSelectedBankingBookModel();
        
        if (null != selectedBankingBookModel) {
            this.mainController.handleEditBankingBookItem(
                    selectedBankingBookModel
            );
        }
    }
    
    @FXML
    private void handleAddButtonClicked() {
        this.mainController.handleAddBankingBookItem();
    }
    
    @FXML
    private void handleDeleteButtonClicked() {
        BankingBookModel selectedBankingBookModel = getSelectedBankingBookModel();
        
        if (null != selectedBankingBookModel) {
            this.mainController.handleDeleteBankingBookItem(
                    selectedBankingBookModel
            );
        }
    }
    
    @FXML
    private void handleDetailsButtonClicked() {
        AccountModel selectedAccountModel = getSelectedAccountModel();
        CreditCardModel selectedCreditCardModel = getSelectedCreditCardModel();
        
        if (null != selectedAccountModel) {
            this.mainController.handleShowAccountDetails(
                    selectedAccountModel
            );
        } else if (null != selectedCreditCardModel) {
            this.mainController.handleShowCreditCardDetails(
                    selectedCreditCardModel
            );
        }
    }
    
    @FXML
    private void handleSummaryButtonClicked() {
        
    }
    
    @FXML
    private void handleRefreshButtonClicked() {
        this.mainController.handleRefreshBankingBookTable();
    }
    
    @FXML
    private void initialize() {
        DaoModelFactory.getBeesAndHoneyUserDaoInstance().registerObserver(this);
        DaoModelFactory.getBankAccountLoginDaoInstance().registerObserver(this);
        DaoModelFactory.getBankAccountOwnerDaoInstance().registerObserver(this);
        initListeners();
    }
}
