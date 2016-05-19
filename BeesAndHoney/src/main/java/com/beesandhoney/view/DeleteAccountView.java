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
import javafx.scene.control.Label;

public class DeleteAccountView implements IObservableView {
    
    private BankingBookModel itemToDelete;

    private final DeleteAccountController controller;
    private final ArrayList<ObserverInterface> observers;
    
    private final String deleteMessagePattern;
    
    @FXML
    private Label deleteMessageLabel;
    
    public DeleteAccountView() {
        this.observers = new ArrayList<>();
        this.controller = new DeleteAccountController(this);
        this.itemToDelete = null;
        this.deleteMessagePattern = "Do you want to remove %s account?";
    }
    
    public void setItemToDelete(BankingBookModel itemToDelete) {
        this.itemToDelete = itemToDelete;
        
        String deleteMessage = String.format(
                deleteMessagePattern, this.itemToDelete.getAlias());
        this.deleteMessageLabel.setText(deleteMessage);
    }
    
    @Override
    public void cleanUp() {
        this.deleteMessageLabel.setText("");
    }

    @Override
    public IController getController() {
        return this.controller;
    }
    
    @Override
    public void setController(IController controller) {
        throw new UnsupportedOperationException("Not supported.");
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
