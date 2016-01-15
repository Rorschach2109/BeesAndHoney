/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.controller;

import com.beesandhoney.statemachine.AddAccountStageState;
import com.beesandhoney.statemachine.DecisionStageState;
import com.beesandhoney.statemachine.DetailsStageState;
import com.beesandhoney.statemachine.SecondStageStateInterface;
import com.beesandhoney.utils.ObservableInterface;
import com.beesandhoney.utils.ObserverInterface;
import com.beesandhoney.view.BeesAndHoney;
import com.beesandhoney.view.BeesAndHoneyMainView;
import com.beesandhoney.view.IView;
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
    private ObservableInterface observable;
    
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
    
    public void handleShowBankingBookItemDetails(int selectedItemIndex) {
        this.currentState = new DetailsStageState();
        showSecondStage(DETAILS_VIEW_RESOURCE_PATH);
    }
    
    public void handleRefreshBankingBookTable() {
        
    }
    
    public void handleAddBankingBookItem() {
        this.currentState = new AddAccountStageState();
        showSecondStage(ADD_ACCOUNT_VIEW_RESOURCE_PATH);
    }
    
    public void handleDeleteBankingBookItem(int selectedItemIndex) {
        this.currentState = new DecisionStageState(this, selectedItemIndex);
        showSecondStage(DECISION_VIEW_RESOURCE_PATH);
    }
    
    public IView getCurrentSecondStageView() {
        return (IView) this.observable;
    }
    
    public void deleteBankingBookItem(int selectedItemIndex) {
        System.out.println("com.beesandhoney.controller.BeesAndHoneyMainController.deleteBankingBookItem(): " + selectedItemIndex);
    }
    
    @Override
    public void update() {
        this.currentState.handleBeforeExit();
        this.secondStage.close();

        this.secondStage = null;
        this.observable = null;
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
            
            this.observable = fxmlLoader.getController();
            this.observable.registerObserver(this);
            
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
