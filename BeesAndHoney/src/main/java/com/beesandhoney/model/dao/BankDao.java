/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.model.dao;

import com.beesandhoney.model.Bank;
import com.beesandhoney.utils.hibernate.HibernateSessionUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class BankDao extends GenericDaoHibernateImpl<Bank, Integer> {
    
    public BankDao() {
        super(Bank.class);
    }
    
    public Bank findByName(String bankName) {
        Session currentSession = HibernateSessionUtil.getSession();
        
        Criteria criteria = currentSession.createCriteria(type);
        criteria.add(Restrictions.eq("bankName", bankName));
        
        return (Bank) criteria.uniqueResult();
    }
}
