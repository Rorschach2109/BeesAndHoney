/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.controller;

import com.beesandhoney.model.BankingBookModel;
import com.beesandhoney.statemachine.AddAccountStageState;
import com.beesandhoney.statemachine.DeleteStageState;
import com.beesandhoney.statemachine.EditAccountStageState;
import com.beesandhoney.statemachine.SecondStageStateInterface;
import com.beesandhoney.utils.ObserverInterface;
import com.beesandhoney.view.AddAccountView;
import com.beesandhoney.view.BeesAndHoney;
import com.beesandhoney.view.BeesAndHoneyMainView;
import com.beesandhoney.view.DeleteAccountView;
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
    private static final String DELETE_ACCOUNT_VIEW_RESOURCE_PATH;
    
    static
    {
        ADD_ACCOUNT_VIEW_RESOURCE_PATH = "views/AddAccountView.fxml";
        DELETE_ACCOUNT_VIEW_RESOURCE_PATH = "views/DeleteAccountView.fxml";
    }
    
    public BeesAndHoneyMainController(BeesAndHoneyMainView mainView) {
        this.mainView = mainView;
    }
    
    public void initialize() {
        this.mainView.setCurrentUser(this.application.getUserLogin());
        this.mainView.setLastAccessDate(this.application.getLastAccessDate());
        this.mainView.update();
    }
    
    public String getCurrentUserLogin() {
        return this.application.getUserLogin();
    }
    
    public void handleEditBankingBookItem(BankingBookModel selectedItem) {
        this.currentState = new EditAccountStageState(this);
        showSecondStage(ADD_ACCOUNT_VIEW_RESOURCE_PATH);
        
        AddAccountView addAccountView = (AddAccountView) this.observableView;
        AbstractBankAccountLoginController controller = new EditAccountController(this.application);
        addAccountView.setController(controller);
        
        addAccountView.fillBankingBookModel(selectedItem);
    }
    
    public void handleRefreshBankingBookTable() {
        
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
    
    public IObservableView getCurrentSecondStageView() {
        return this.observableView;
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
}
