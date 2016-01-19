/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.controller;

import com.beesandhoney.model.Bank;
import com.beesandhoney.model.BankAccountLogin;
import com.beesandhoney.model.ModelFactory;
import com.beesandhoney.model.dao.BankAccountLoginDao;
import com.beesandhoney.model.dao.BankDao;
import com.beesandhoney.statemachine.AddAccountStageState;
import com.beesandhoney.statemachine.DecisionStageState;
import com.beesandhoney.statemachine.DetailsStageState;
import com.beesandhoney.statemachine.SecondStageStateInterface;
import com.beesandhoney.utils.ObserverInterface;
import com.beesandhoney.utils.hibernate.HibernateSessionUtil;
import com.beesandhoney.view.AddAccountView;
import com.beesandhoney.view.BeesAndHoney;
import com.beesandhoney.view.BeesAndHoneyMainView;
import com.beesandhoney.view.IObservableView;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BeesAndHoneyMainController implements IController, ObserverInterface {
    
    private BeesAndHoneyMainView mainView;
    private BeesAndHoney application;
    
    private Stage secondStage;
    private IObservableView observableView;
    
    private SecondStageStateInterface currentState;
    
    private static final String ADD_ACCOUNT_VIEW_RESOURCE_PATH;
    private static final String DECISION_VIEW_RESOURCE_PATH;
    private static final String DETAILS_VIEW_RESOURCE_PATH;
    
    static
    {
        ADD_ACCOUNT_VIEW_RESOURCE_PATH = "views/AddAccountView.fxml";
        DECISION_VIEW_RESOURCE_PATH = "views/DecisionView.fxml";
        DETAILS_VIEW_RESOURCE_PATH = "views/DetailsView.fxml";
    }
    
    public BeesAndHoneyMainController(BeesAndHoneyMainView mainView) {
        this.mainView = mainView;
    }
    
    public void initialize() {
        this.mainView.setCurrentUser(this.application.getUserLogin());
    }
    
    public void handleEditBankingBookItem(int selectedItemIndex) {
//        this.currentState = new DetailsStageState();
//        showSecondStage(DETAILS_VIEW_RESOURCE_PATH);
    }
    
    public void handleRefreshBankingBookTable() {
        
    }
    
    public void handleAddBankingBookItem() {
        this.currentState = new AddAccountStageState(this);
        showSecondStage(ADD_ACCOUNT_VIEW_RESOURCE_PATH);
    }
    
    public void handleDeleteBankingBookItem(int selectedItemIndex) {
        this.currentState = new DecisionStageState(this, selectedItemIndex);
        showSecondStage(DECISION_VIEW_RESOURCE_PATH);
    }
    
    public IObservableView getCurrentSecondStageView() {
        return this.observableView;
    }
    
    public void insertAccount() {
        HibernateSessionUtil.openSessionWithTransaction();
        
        AddAccountView addAccountView = (AddAccountView) this.observableView;
        BankAccountLogin bankAccountLogin = getBankAccountLogin(addAccountView);
        
        BankAccountLoginDao dao = new BankAccountLoginDao();
        System.out.println(dao.create(bankAccountLogin));
        
        HibernateSessionUtil.closeSessionWithTransaction();
    }
    
    public void deleteBankingBookItem(int selectedItemIndex) {
        System.out.println("com.beesandhoney.controller.BeesAndHoneyMainController.deleteBankingBookItem(): " + selectedItemIndex);
    }
    
    @Override
    public void update() {
        this.currentState.handleBeforeExit();
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
            this.secondStage.setScene(scene);
            this.secondStage.show();
            
        } catch (IOException e) {
            
        }
    }
    
    private BankAccountLogin getBankAccountLogin(AddAccountView addAccountView) {
        
        BankAccountLogin bankAccountLogin = ModelFactory.createBankAccountLogin(
                addAccountView.getClientId(),
                addAccountView.getPassword(),
                addAccountView.getAccountAlias()
        );
        
        BankDao bankDao = new BankDao();
        Bank bank = bankDao.findByName(addAccountView.getBankName());
        
        bankAccountLogin.setBank(bank);
        
        return bankAccountLogin;
    }
}
