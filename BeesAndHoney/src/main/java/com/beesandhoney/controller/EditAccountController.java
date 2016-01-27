/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.controller;

import com.beesandhoney.model.Bank;
import com.beesandhoney.model.BankAccountLogin;
import com.beesandhoney.model.ModelFactory;
import com.beesandhoney.model.dao.BankAccountLoginDao;
import com.beesandhoney.model.dao.BankDao;
import com.beesandhoney.model.dao.DaoModelFactory;
import com.beesandhoney.view.BeesAndHoney;
import org.hibernate.Session;

public class EditAccountController extends AbstractAddEditController {
    
    public EditAccountController(BeesAndHoney application) {
        super(application);
    }
    
    @Override
    protected boolean isDuplicated() {
        if (null == findDuplicate()) {
            return false;
        }
        
        return isChanged();
    }
    
    @Override
    protected void handleSaveAccountLogin() {
        editAccount();
    }
    
    public void editAccount() {
        BankAccountLogin editedBankAccountLogin = createBankAccountLogin();
        
        BankAccountLoginDao dao = DaoModelFactory.getBankAccountLoginDaoInstance();
        Session session = dao.openSessionWithTransaction();
        
        BankAccountLogin oldBankAccountLogin = getBankAccountLogin(dao, session);
        
        editBankAccountLogin(oldBankAccountLogin, editedBankAccountLogin);
        
        dao.update(oldBankAccountLogin, session);
        
        dao.closeSessionWithTransaction(session);
    }
    
    private BankAccountLogin createBankAccountLogin() {
        
        BankAccountLogin bankAccountLogin = ModelFactory.createBankAccountLogin(
                this.addAccountView.getClientId(),
                this.addAccountView.getPassword(),
                this.addAccountView.getAccountAlias()
        );
        
        BankDao bankDao = DaoModelFactory.getBankDaoInstance();
        
        Session session = bankDao.openSession();
        Bank bank = bankDao.findByName(this.addAccountView.getBankName(), session);
        bankDao.closeSession(session);
        
        bankAccountLogin.setBank(bank);
        
        return bankAccountLogin;
    }
    
    private BankAccountLogin getBankAccountLogin(BankAccountLoginDao dao,
            Session session) {
        return dao.findByClientIdAndAlias(
                this.originalBankingBookModel.getClientId(),
                this.originalBankingBookModel.getAlias(), 
                session);
    }
    
    private boolean isChanged() {
        if (this.originalBankingBookModel.getAlias().equals(this.addAccountView.getAccountAlias()) &&
                this.originalBankingBookModel.getClientId().equals(this.addAccountView.getClientId()) &&
                this.originalBankingBookModel.getBankName().equals(this.addAccountView.getBankName()) ) {
            return false;
        }
        
        return true;
    }
    
    private void editBankAccountLogin(BankAccountLogin oldBankAccountLogin, 
            BankAccountLogin editedBankAccountLogin) {
        oldBankAccountLogin.setBankAccountLoginAlias(
                editedBankAccountLogin.getBankAccountLoginAlias());
        oldBankAccountLogin.setBank(editedBankAccountLogin.getBank());
        oldBankAccountLogin.setClientId(editedBankAccountLogin.getClientId());
        oldBankAccountLogin.setLoginPassword(
                editedBankAccountLogin.getLoginPassword());
    }
}
