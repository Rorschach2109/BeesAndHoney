/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.view;

import com.beesandhoney.controller.DeleteAccountController;
import com.beesandhoney.controller.IController;
import com.beesandhoney.model.BankingBookModel;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import com.beesandhoney.utils.ObserverInterface;
import java.util.ArrayList;

public class DeleteAccountView implements IObservableView {
    
    private ArrayList<ObserverInterface> observers;
    private boolean decisionResult;
    private DeleteAccountController controller;
    private BankingBookModel itemToDelete;
    
    public DeleteAccountView() {
        this.observers = new ArrayList<>();
        this.decisionResult = false;
        this.controller = new DeleteAccountController(this);
        this.itemToDelete = null;
    }
    
    public boolean getDecisionResult() {
        return this.decisionResult;
    }
    
    public void setItemToDelete(BankingBookModel itemToDelete) {
        this.itemToDelete = itemToDelete;
    }
    
    @Override
    public void cleanUp() {
    }

    @Override
    public IController getController() {
        return this.controller;
    }
    
    @Override
    public void setController(IController controller) {
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
        if (true == decisionResult) {
            this.controller.deleteBankingBookItem(this.itemToDelete);
        }
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
