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

public class AddAccountController implements IController {

    private AddAccountView addAccountView;
    private AbstractTextValidator textValidator;
    
    public AddAccountController(AddAccountView addAccountView) {
        this.addAccountView = addAccountView;
        this.textValidator = new InputTextLengthValidator();
    }
    
    public boolean validateInput() {
        return validateAccountAlias() &&
                validateBankName() &&
                validateClientId() &&
                validatePassword();
    }
    
    @Override
    public void setApplication(BeesAndHoney application) {
        throw new UnsupportedOperationException("Not supported yet.");
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
        System.out.println("Validator: " + bankName);
        
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
