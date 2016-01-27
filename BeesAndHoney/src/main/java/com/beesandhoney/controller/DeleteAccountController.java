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
    
    public DeleteAccountController(DeleteAccountView deleteAccountView) {
        this.deleteAccountView = deleteAccountView;
    }
    
    public void deleteBankingBookItem(BankingBookModel bankingBookModel) {
        Session session;

        BankAccountLoginDao bankAccountDao = DaoModelFactory.getBankAccountLoginDaoInstance();
        session = bankAccountDao.openSessionWithTransaction();

        BankAccountLogin oldBankAccountLogin = bankAccountDao
                .findByClientIdAndAlias(
                        bankingBookModel.getClientId(),
                        bankingBookModel.getAlias(), 
                        session);
        
        bankAccountDao.delete(oldBankAccountLogin, session);
        bankAccountDao.closeSessionWithTransaction(session);
    }
    
    @Override
    public void setApplication(BeesAndHoney application) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
