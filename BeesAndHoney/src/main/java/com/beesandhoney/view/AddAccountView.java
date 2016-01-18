/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.view;

import com.beesandhoney.controller.IController;
import com.beesandhoney.utils.ObserverInterface;
import com.beesandhoney.utils.constants.BankConstants;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class AddAccountView implements IObservableView {
    
    boolean decisionResult;
    ArrayList<ObserverInterface> observers;
    
    @FXML
    TextField accountLoginAliasField;
    @FXML
    ChoiceBox<String> bankNameChoiceBox;
    @FXML
    TextField clientIdField;
    @FXML
    PasswordField passwordField;
    
    public AddAccountView() {
        this.decisionResult = false;
        this.observers = new ArrayList<>();
    }
    
    public String getAccountAlias() {
        return accountLoginAliasField.getText();
    }
    
    public String getBankName() {
        return bankNameChoiceBox.getValue();
    }
    
    public String getClientId() {
        return clientIdField.getText();
    }
    
    public String getPassword() {
        return passwordField.getText();
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
    private void handleCancelButtonClicked() {
        setDecisionResult(false);
    }
    
    @FXML
    private void handleSaveButtonReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            setDecisionResult(true);
        }
    }
    
    @FXML
    private void handleSaveButtonClicked() {
        setDecisionResult(true);
    }
    
    @FXML
    private void initialize() {
        this.bankNameChoiceBox.setItems(BankConstants.bankNameList);
    }
}
