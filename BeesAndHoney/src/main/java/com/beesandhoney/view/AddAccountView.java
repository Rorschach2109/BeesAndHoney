/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.view;

import com.beesandhoney.controller.AddAccountController;
import com.beesandhoney.controller.IController;
import com.beesandhoney.model.BankingBookModel;
import com.beesandhoney.utils.ObserverInterface;
import com.beesandhoney.utils.constants.BankConstants;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class AddAccountView implements IObservableView {
    
    private AddAccountController controller;
    
    private boolean decisionResult;
    private ArrayList<ObserverInterface> observers;
    
    @FXML
    private TextField accountLoginAliasField;
    @FXML
    private ChoiceBox<String> bankNameChoiceBox;
    @FXML
    private TextField clientIdField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;
    
    
    public AddAccountView() {
        this.controller = new AddAccountController(this);
        this.decisionResult = false;
        this.observers = new ArrayList<>();
    }
    
    public boolean getDecisionResult() {
        return this.decisionResult;
    }
    
    public String getAccountAlias() {
        return this.accountLoginAliasField.getText();
    }
    
    public void setAccountAlias(String accountAlias) {
        this.accountLoginAliasField.setText(accountAlias);
    }
    
    public String getBankName() {
        return this.bankNameChoiceBox.getValue();
    }
    
    public void setBankName(String bankName) {
        this.bankNameChoiceBox.setValue(bankName);
    }
    
    public String getClientId() {
        return this.clientIdField.getText();
    }
    
    public void setClientId(String clientId) {
        this.clientIdField.setText(clientId);
    }
    
    public String getPassword() {
        return this.passwordField.getText();
    }
    
    public void fillBankingBookModel(BankingBookModel bankingBookModel) {
        setAccountAlias(bankingBookModel.getAlias());
        setBankName(bankingBookModel.getBankName());
        setClientId(bankingBookModel.getClientId());
    }
    
    @Override
    public void cleanUp() {
    }

    @Override
    public IController getController() {
        return this.controller;
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
    
    private void saveAccountLogin() {
        if (true == this.controller.validateInput()) {
            setDecisionResult(true);
        } else {
            setErrorLabelVisibility(true);
        }
    }
    
    private void setDecisionResult(boolean decisionResult) {
        setErrorLabelVisibility(false);
        
        this.decisionResult = decisionResult;
        notifyObservers();
    }
    
    private void setErrorLabelVisibility(boolean visible) {
        this.errorLabel.setVisible(visible);
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
            saveAccountLogin();
        }
    }
    
    @FXML
    private void handleSaveButtonClicked() {
        saveAccountLogin();
    }
    
    @FXML
    private void initialize() {
        this.bankNameChoiceBox.setItems(BankConstants.bankNameList);
    }
}
