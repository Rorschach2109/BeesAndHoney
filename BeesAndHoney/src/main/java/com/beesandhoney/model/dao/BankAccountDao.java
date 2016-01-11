/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.model.dao;

import com.beesandhoney.model.BankAccount;
import com.beesandhoney.utils.hibernate.HibernateSessionUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class BankAccountDao extends GenericDaoHibernateImpl<BankAccount, Integer> {
    
    public BankAccountDao() {
        super(BankAccount.class);
    }
    
    public List<BankAccount> findByOwnerKey(String ownerName, String ownerSurname) {
        Session currentSession = HibernateSessionUtil.getSession();
        
        Criteria criteria = currentSession.createCriteria(type)
                .createCriteria("bankAccountOwner", "owner")
                    .add(Restrictions.eq("owner.bankAccountOwnerKey.ownerName", ownerName))
                    .add(Restrictions.eq("owner.bankAccountOwnerKey.ownerSurname", ownerSurname));
        
        return (List<BankAccount>) criteria.list();
    }
    
    public List<BankAccount> findByBankName(String bankName) {
        Session currentSession = HibernateSessionUtil.getSession();
        
        Criteria criteria = currentSession.createCriteria(type)
                .createCriteria("bankAccountLogin", "accountLogin")
                .createCriteria("bank", "bank")
                .add(Restrictions.eq("bank.bankName", bankName));
        
        return criteria.list();
    }
}
