/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.controller;

import com.beesandhoney.utils.validator.AbstractTextValidator;
import com.beesandhoney.utils.validator.LoginValidator;
import com.beesandhoney.utils.validator.PasswordValidator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public final class LoginController {
    
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button enterButton;
    
    private final int PASSWORD_MIN_LENGTH;
    
    public LoginController() {
        this.PASSWORD_MIN_LENGTH = 8;
    }
    
    @FXML
    private void handlePasswordKeyReleased() {
        System.out.println("com.beesandhoney.controller.LoginController.handlePasswordKeyReleased()");;
    }
    
    @FXML
    private void handleEnterButtonReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            logIn();
        }
    }
    
    @FXML void handleEnterButtonClicked() {
        logIn();
    }
    
    private void logIn() {
        System.out.println("com.beesandhoney.controller.LoginController.logIn()");
        
        if (false == validateInputText(new LoginValidator(), loginField.getText())) {
            System.out.println("Incorret login");
            return;
        }
        
        if (false == validateInputText(new PasswordValidator(), passwordField.getText())) {
            System.out.println("Incorret password");
            return;
        }
        
        System.out.println("Everything OK");
    }
    
    private boolean validateInputText(AbstractTextValidator inputTextValidator, 
            String inputText) {
        return inputTextValidator.validateText(inputText);
    }
    
    @FXML
    private void initialize() {
        System.out.println("[AK] initialize");
    }
}
