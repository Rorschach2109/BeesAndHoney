/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.controller;

import com.beesandhoney.model.BankAccountLogin;
import com.beesandhoney.model.BankingBookModel;
import com.beesandhoney.model.dao.BankAccountLoginDao;
import com.beesandhoney.model.dao.DaoModelFactory;
import com.beesandhoney.utils.validator.AbstractTextValidator;
import com.beesandhoney.utils.validator.InputTextLengthValidator;
import com.beesandhoney.view.AddAccountView;
import com.beesandhoney.view.BeesAndHoney;
import org.hibernate.Session;

public abstract class AbstractBankAccountLoginController implements IController {

    protected AddAccountView addAccountView;
    protected BankingBookModel originalBankingBookModel;
   
    protected BeesAndHoney application;

    private final AbstractTextValidator textValidator;
    
    public AbstractBankAccountLoginController(BeesAndHoney application) {
        this.textValidator = new InputTextLengthValidator();
        this.addAccountView = null;
        this.application = application;
        this.originalBankingBookModel = null;
    }
    
    protected abstract boolean isDuplicated();
    protected abstract void handleSaveAccountLogin();
    
    public void setView(AddAccountView addAccountView) {
        this.addAccountView = addAccountView;
    }
    
    public void setOriginalBankingBookModel(BankingBookModel originalBankingBookModel) {
        this.originalBankingBookModel = originalBankingBookModel;
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
    
    protected boolean findDuplicate() {
        boolean isDuplicate = false;
        
        BankAccountLoginDao dao = DaoModelFactory.getBankAccountLoginDaoInstance();
        Session session = dao.openSession();
        
        BankAccountLogin duplicate = dao.findByClientId_Alias_UserLogin(
                this.addAccountView.getClientId(), 
                this.addAccountView.getAccountAlias(), 
                this.application.getUserLogin(),
                session);
        
        if (null != duplicate) {
            isDuplicate = (duplicate.getBeesAndHoneyUser().getUserName().equals(
                    this.application.getUserLogin()));
        }
        
        dao.closeSession(session);
        
        return isDuplicate;
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
