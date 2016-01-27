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
import com.beesandhoney.view.BeesAndHoney;
import com.beesandhoney.view.DeleteAccountView;
import org.hibernate.Session;

public class DeleteAccountController implements IController {

    private DeleteAccountView deleteAccountView;
    private BeesAndHoney application;
    
    public DeleteAccountController(DeleteAccountView deleteAccountView) {
        this.deleteAccountView = deleteAccountView;
        this.application = null;
    }
    
    public void deleteBankingBookItem(BankingBookModel bankingBookModel) {
        Session session;

        BankAccountLoginDao bankAccountDao = DaoModelFactory.getBankAccountLoginDaoInstance();
        session = bankAccountDao.openSessionWithTransaction();

        BankAccountLogin oldBankAccountLogin = bankAccountDao
                .findByClientId_Alias_UserLogin(
                        bankingBookModel.getClientId(),
                        bankingBookModel.getAlias(), 
                        application.getUserLogin(),
                        session);
        
        bankAccountDao.delete(oldBankAccountLogin, session);
        bankAccountDao.closeSessionWithTransaction(session);
    }
    
    @Override
    public void setApplication(BeesAndHoney application) {
        this.application = application;
    }
    
}
