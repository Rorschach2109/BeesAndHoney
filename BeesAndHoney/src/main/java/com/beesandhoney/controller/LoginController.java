/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.controller;

import com.beesandhoney.utils.validator.AbstractTextValidator;
import com.beesandhoney.utils.validator.LoginValidator;
import com.beesandhoney.utils.validator.PasswordValidator;
import com.beesandhoney.view.BeesAndHoney;
import com.beesandhoney.view.LoginView;

public final class LoginController implements IController {
    
    private LoginView loginView;
    private BeesAndHoney application;
        
    public LoginController(LoginView loginView) {
        this.loginView = loginView;
    }
    
    public void logIn(String login, String password, String securityPassword) {
        this.loginView.cleanErrorLabels();

//        AbstractTextValidator textValidator = new LoginValidator();
//        if (false == validateInputText(textValidator, login)) {
//            this.loginView.handleIncorrectLogin();
//            return;
//        }
//        
//        textValidator = new PasswordValidator();
//        if (false == validateInputText(textValidator, password)) {
//            this.loginView.handleIncorrectPassword();
//            return;
//        }
//
//        if (false == validateInputText(textValidator, password)) {
//            this.loginView.handleIncorrectSecurityPassword();
//            return;
//        }
//
//        if (0 == password.compareTo(securityPassword)) {
//            this.loginView.handleSamePasswords();
//            return;
//        }
        
        this.application.handleSuccessfulLogin(login);
    }
    
    private boolean validateInputText(AbstractTextValidator inputTextValidator, 
            String inputText) {
        return inputTextValidator.validateText(inputText);
    }
    
    @Override
    public void setApplication(BeesAndHoney application) {
        this.application = application;
    }
}
