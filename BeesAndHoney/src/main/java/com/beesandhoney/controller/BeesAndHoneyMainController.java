/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.controller;

import com.beesandhoney.utils.ObservableInterface;
import com.beesandhoney.utils.ObserverInterface;
import com.beesandhoney.view.BeesAndHoney;
import com.beesandhoney.view.BeesAndHoneyMainView;
import com.beesandhoney.view.DecisionView;
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
    
    public BeesAndHoneyMainController(BeesAndHoneyMainView mainView) {
        this.mainView = mainView;
    }
    
    public void initialize() {
        this.mainView.setCurrentUser(this.application.getUserLogin());
    }
    
    public void handleShowBankingBookItemDetails(int selectedItemIndex) {
        
    }
    
    public void handleRefreshBankingBookTable() {
        
    }
    
    public void handleAddBankingBookItem() {
        
    }
    
    public void handleDeleteBankingBookItem(int selectedItemIndex) {
        showSecondStage("views/DecisionView.fxml");
    }
    
    @Override
    public void update() {
        DecisionView dec = (DecisionView) this.observable;
        System.out.println(dec.getDecisionResult());
        this.secondStage.close();
        this.secondStage = null;
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
