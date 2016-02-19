/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.model.dao;

public final class DaoModelFactory {
    
    public static BankAccountDao getBankAccountDaoInstance() {
        return BankAccountDao.getInstance();
    }
    
    public static BankAccountLoginDao getBankAccountLoginDaoInstance() {
        return BankAccountLoginDao.getInstance();
    }
    
    public static BankDao getBankDaoInstance() {
        return BankDao.getInstance();
    }
    
    public static BeesAndHoneyUserDao getBeesAndHoneyUserDaoInstance() {
        return BeesAndHoneyUserDao.getInstance();
    }
    
    public static BankAccountOwnerDao getBankAccountOwnerDaoInstance() {
        return BankAccountOwnerDao.getInstance();
    }
}
