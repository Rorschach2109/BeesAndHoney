/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.view;

import com.beesandhoney.controller.IController;
import com.beesandhoney.controller.LoginController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;    
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public final class LoginView implements IView {
    
    @FXML
    private Label incorrectPasswordLabel;
    @FXML
    private Label incorrectLoginLabel;
    @FXML
    private Label invalidPasswordLabel;
    @FXML
    private Label incorrectSecurityPasswordLabel;
    @FXML
    private Label samePasswordsLabel;
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField securityPasswordField;

    private final LoginController loginController;
    
    public LoginView() {
        this.loginController = new LoginController(this);
    }
    
    public void handleIncorrectLogin() {
        this.incorrectLoginLabel.setVisible(true);
    }
    
    public void handleIncorrectPassword() {
        this.incorrectPasswordLabel.setVisible(true);
    }
    
    public void handleInvalidPassword() {
        this.invalidPasswordLabel.setVisible(true);
    }
    
    public void handleIncorrectSecurityPassword() {
        this.incorrectSecurityPasswordLabel.setVisible(true);
    }
    
    public void handleSamePasswords() {
        this.samePasswordsLabel.setVisible(true);
    }

    @Override
    public IController getController() {
        return this.loginController;
    }
    
    @Override
    public void cleanUp() {
        cleanErrorLabels();
        cleanTextFields();
    }

    public void cleanErrorLabels() {
        this.incorrectLoginLabel.setVisible(false);
        this.incorrectPasswordLabel.setVisible(false);
        this.invalidPasswordLabel.setVisible(false);
        this.incorrectSecurityPasswordLabel.setVisible(false);
        this.samePasswordsLabel.setVisible(false);
    }
    
    private void cleanTextFields() {
        this.loginField.clear();
        this.passwordField.clear();
        this.securityPasswordField.clear();
    }
        
    private void addListeners() {
        passwordField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, 
                    Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    passwordField.clear();
                    incorrectPasswordLabel.setVisible(false);
                    invalidPasswordLabel.setVisible(false);
                }
            }
        });
        
        securityPasswordField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0,
                    Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    securityPasswordField.clear();
                    incorrectSecurityPasswordLabel.setVisible(false);
                    samePasswordsLabel.setVisible(false);
                }
            }
        });
    }
    
    private void logIn() {
        this.loginController.logIn(loginField.getText(), 
                passwordField.getText(), securityPasswordField.getText());
    }
    
    @FXML
    private void handleEnterButtonReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            logIn();
        }
    }
    
    @FXML 
    private void handleEnterButtonClicked() {
        logIn();
    }
    
    @FXML
    private void initialize() {
        addListeners();
    }
}