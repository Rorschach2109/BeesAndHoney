/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.controller;

import com.beesandhoney.model.Bank;
import com.beesandhoney.model.BankAccountLogin;
import com.beesandhoney.model.BeesAndHoneyUser;
import com.beesandhoney.model.ModelFactory;
import com.beesandhoney.model.dao.BankDao;
import com.beesandhoney.model.dao.BeesAndHoneyUserDao;
import com.beesandhoney.model.dao.DaoModelFactory;
import com.beesandhoney.view.BeesAndHoney;
import org.hibernate.Session;

public class AddAccountController extends AbstractAddEditController {

    public AddAccountController(BeesAndHoney application) {
        super(application);
    }
    
    @Override
    protected boolean isDuplicated() {
        return (null != findDuplicate());
    }
    
    @Override
    protected void handleSaveAccountLogin() {
        insertAccount();
    }
    
    private void insertAccount() {
        BankAccountLogin bankAccountLogin = createBankAccountLoginFromAddAccountView();

        BeesAndHoneyUserDao dao = DaoModelFactory.getBeesAndHoneyUserDao();
        Session session = dao.openSessionWithTransaction();

        BeesAndHoneyUser currentUser = dao.findByUserName(
                getCurrentUserLogin(), session);        
        currentUser.getBankAccountLogins().add(bankAccountLogin);
        bankAccountLogin.setBeesAndHoneyUser(currentUser);
        
        dao.update(currentUser, session);
        dao.closeSessionWithTransaction(session);
    }
    
    private BankAccountLogin createBankAccountLoginFromAddAccountView() {
        
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
}
