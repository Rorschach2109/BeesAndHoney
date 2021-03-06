/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.model.dao;

import com.beesandhoney.model.BankAccount;
import com.beesandhoney.utils.constants.BankAccountConstants.BankAccountType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public final class BankAccountDao extends GenericDaoHibernateImpl<BankAccount, String> {
    
    private static BankAccountDao instance;
    
    static
    {
        instance = null;
    }
    
    private BankAccountDao() {
        super(BankAccount.class);
    }
    
    public static BankAccountDao getInstance() {
        if (null == instance) {
            instance = new BankAccountDao();
        }
        
        return instance;
    }
    
    public List<BankAccount> findByOwnerKey(String ownerName, 
            String ownerSurname, Session session) {
        Criteria criteria = session.createCriteria(type)
                .createCriteria("bankAccountOwner", "owner")
                    .add(Restrictions.eq("owner.bankAccountOwnerKey.ownerName", ownerName))
                    .add(Restrictions.eq("owner.bankAccountOwnerKey.ownerSurname", ownerSurname));
        
        return (List<BankAccount>) criteria.list();
    }
    
    public List<BankAccount> findByBankName(String bankName, Session session) {
        Criteria criteria = session.createCriteria(type)
                .createCriteria("bankAccountLogin", "accountLogin")
                .createCriteria("bank", "bank")
                .add(Restrictions.eq("bank.bankName", bankName));
        
        return (List<BankAccount>) criteria.list();
    }
    
    public List<BankAccount> findByClientId(String clientId, Session session) {
        Criteria criteria = session.createCriteria(type)
                .createCriteria("bankAccountLogin", "bankAccountLogin")
                    .add(Restrictions.eq("bankAccountLogin.clientId", clientId))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<BankAccount>) criteria.list();
    }
    
    public List<BankAccount> findByClientIdAndAccountType(
            String clientId, BankAccountType accountType, Session session) {
        Criteria criteria = session.createCriteria(type)
                .add(Restrictions.eq("bankAccountType", accountType))
                .createCriteria("bankAccountLogin", "bankAccountLogin")
                    .add(Restrictions.eq("bankAccountLogin.clientId", clientId))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<BankAccount>) criteria.list();
    }
}
