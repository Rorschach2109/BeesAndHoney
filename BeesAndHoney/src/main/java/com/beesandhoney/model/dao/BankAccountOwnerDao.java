/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.model.dao;

import com.beesandhoney.model.BankAccountOwner;

public class BankAccountOwnerDao extends GenericDaoHibernateImpl<BankAccountOwner, String> {
    private static BankAccountOwnerDao instance;
    
    static
    {
        instance = null;
    }
    
    private BankAccountOwnerDao() {
        super(BankAccountOwner.class);
    }
    
    public static BankAccountOwnerDao getInstance() {
        if (null == instance) {
            instance = new BankAccountOwnerDao();
        }
        
        return instance;
    }
}
