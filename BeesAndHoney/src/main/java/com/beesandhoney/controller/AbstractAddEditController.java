/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.controller;

import com.beesandhoney.utils.validator.AbstractTextValidator;
import com.beesandhoney.utils.validator.InputTextLengthValidator;
import com.beesandhoney.view.AddAccountView;
import com.beesandhoney.view.BeesAndHoney;

public abstract class AbstractAddEditController implements IController {
    protected AddAccountView addAccountView;
    private AbstractTextValidator textValidator;
    
    private BeesAndHoney application;
    
    public AbstractAddEditController() {
        this.textValidator = new InputTextLengthValidator();
        this.addAccountView = null;
        this.application = null;
    }
    
    protected abstract boolean isDuplicated();
    protected abstract void handleSaveAccountLogin();
    
    public void setView(AddAccountView addAccountView) {
        this.addAccountView = addAccountView;
    }
    
    public boolean saveAccountLogin() {
        this.addAccountView.cleanErrorLabels();
        
        if (false == validateInput()) {
            this.addAccountView.setErrorLabelVisibility(true);
            return false;
        }
        
        if (true == isDuplicated()) {
            this.addAccountView.setAccountAlreadyExistsVisibility(true);
            return false;
        }
        
        handleSaveAccountLogin();
        
        return true;
    }
    
    @Override
    public void setApplication(BeesAndHoney application) {
        this.application = application;
    }
    
    protected String getCurrentUserLogin() {
        return this.application.getUserLogin();
    }
    
    private boolean validateInput() {
        return validateAccountAlias() &&
                validateBankName() &&
                validateClientId() &&
                validatePassword();
    }
    
    private boolean validateAccountAlias() {
        String accountAlias = addAccountView.getAccountAlias();
        
        if (null != accountAlias) {
            return textValidator.validateText(accountAlias);
        }
        
        return false;
    }
    
    private boolean validateBankName() {
        String bankName = addAccountView.getBankName();
        
        if (null != bankName) {
            return textValidator.validateText(bankName);
        }
        
        return false;
    }
    
    private boolean validateClientId() {
        String clientId = addAccountView.getClientId();
        
        if (null != clientId) {
            return textValidator.validateText(clientId);
        }
        
        return false;
    }
    
    private boolean validatePassword() {
        String password = addAccountView.getPassword();
        
        if (null != password) {
            return textValidator.validateText(password);
        }
        
        return false;
    }
}
