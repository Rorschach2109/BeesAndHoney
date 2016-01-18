/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.view;

import com.beesandhoney.controller.IController;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import com.beesandhoney.utils.ObserverInterface;
import java.util.ArrayList;

public class DecisionView implements IObservableView {
    
    boolean decisionResult;
    ArrayList<ObserverInterface> observers;
    
    public DecisionView() {
        this.observers = new ArrayList<>();
        this.decisionResult = false;
    }
    
    public boolean getDecisionResult() {
        return this.decisionResult;
    }
    
    @Override
    public void cleanUp() {
    }

    @Override
    public IController getController() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void registerObserver(ObserverInterface observer) {
        this.observers.add(observer);
    }
    
    @Override
    public void deleteObserver(ObserverInterface observer) {
        this.observers.remove(observer);
    }
    
    @Override
    public void notifyObservers() {
        for (ObserverInterface observer : this.observers) {
            observer.update();
        }
    }    
    
    private void setDecisionResult(boolean decisionResult) {
        this.decisionResult = decisionResult;
        notifyObservers();
    }
    
    @FXML
    private void handleCancelButtonReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            setDecisionResult(false);
        }
    }
    
    @FXML
    private void handleCancelButtonPressed() {
        setDecisionResult(false);
    }
    
    @FXML
    private void handleAgreeButtonReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            setDecisionResult(true);
        }
    }
    
    @FXML
    private void handleAgreeButtonClicked() {
        setDecisionResult(true);
    }
}
