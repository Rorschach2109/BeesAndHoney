/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.view;

import com.beesandhoney.controller.AbstractBankAccountLoginController;
import com.beesandhoney.controller.IController;
import com.beesandhoney.model.BankingBookModel;
import com.beesandhoney.utils.ObserverInterface;
import com.beesandhoney.utils.constants.BankConstants;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class AddAccountView implements IObservableView {
    
    private AbstractBankAccountLoginController controller;
    
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
    @FXML
    private Label accountAlreadyExistsLabel;

    private final ArrayList<ObserverInterface> observers;
    
    public AddAccountView() {
        this.controller = null;
        this.observers = new ArrayList<>();
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
        
        this.controller.setOriginalBankingBookModel(bankingBookModel);
    }
    
    public void setErrorLabelVisibility(boolean visible) {
        this.errorLabel.setVisible(visible);
    }
    
    public void setAccountAlreadyExistsVisibility(boolean visible) {
        this.accountAlreadyExistsLabel.setVisible(visible);
    }
    
    public void cleanErrorLabels() {
        this.errorLabel.setVisible(false);
        this.accountAlreadyExistsLabel.setVisible(false);
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
        this.controller = (AbstractBankAccountLoginController) controller;
        this.controller.setView(this);
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
        if (true == this.controller.saveAccountLogin()) {
            notifyObservers();
        }
    }
    
    private void addListeners() {
        this.passwordField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, 
                    Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    passwordField.clear();
                }
            }
        });
    }
    
    @FXML
    private void handleCancelButtonReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            notifyObservers();
        }
    }
    
    @FXML
    private void handleCancelButtonClicked() {
        notifyObservers();
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
        addListeners();
    }
}
