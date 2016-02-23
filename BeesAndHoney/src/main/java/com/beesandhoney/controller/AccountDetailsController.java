/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.controller;

import com.beesandhoney.model.AccountModel;
import com.beesandhoney.model.BankAccount;
import com.beesandhoney.model.dao.BankAccountDao;
import com.beesandhoney.model.dao.DaoModelFactory;
import com.beesandhoney.view.BeesAndHoney;
import org.hibernate.Session;

public class AccountDetailsController implements IController {

    public BankAccount getAccountInformation(AccountModel accountModel) {
        BankAccountDao dao = DaoModelFactory.getBankAccountDaoInstance();
        Session session = dao.openSession();
        
        BankAccount bankAccount = dao.read(accountModel.getAccountNumber(), session);
        
        dao.closeSession(session);
        
        return bankAccount;
    }
    
    @Override
    public void setApplication(BeesAndHoney application) {
        throw new UnsupportedOperationException("Not supported");
    }
    
}
