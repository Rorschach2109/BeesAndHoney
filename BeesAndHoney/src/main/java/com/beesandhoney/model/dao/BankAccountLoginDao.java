/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.model.dao;

import com.beesandhoney.model.BankAccountLogin;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class BankAccountLoginDao extends GenericDaoHibernateImpl<BankAccountLogin, Integer> {
    
    private static BankAccountLoginDao instance;
    
    static
    {
        instance = null;
    }
    
    private BankAccountLoginDao() {
        super(BankAccountLogin.class);
    }
    
    public static BankAccountLoginDao getInstance() {
        if (null == instance) {
            instance = new BankAccountLoginDao();
        }
        
        return instance;
    }
    
    public List<BankAccountLogin> findByBankName(String bankName, Session session) {
        Criteria criteria = session.createCriteria(type)
                .createCriteria("bank", "bank")
                    .add(Restrictions.eq("bank.bankName", bankName));
        return (List<BankAccountLogin>) criteria.list();
    }
    
    public BankAccountLogin findByClientIdAndAlias(String clientId, 
            String bankAccountLoginAlias, Session session) {
        Criteria criteria = session.createCriteria(type)
                .add(Restrictions.eq("clientId", clientId))
                .add(Restrictions.eq("bankAccountLoginAlias", bankAccountLoginAlias));
        return (BankAccountLogin) criteria.uniqueResult();
    }
}
