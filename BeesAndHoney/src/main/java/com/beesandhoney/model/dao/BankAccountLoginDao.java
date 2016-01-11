/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.model.dao;

import com.beesandhoney.model.BankAccountLogin;
import com.beesandhoney.utils.hibernate.HibernateSessionUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author azazello
 */
public class BankAccountLoginDao extends GenericDaoHibernateImpl<BankAccountLogin, Integer> {
    
    public BankAccountLoginDao() {
        super(BankAccountLogin.class);
    }
    
    public List<BankAccountLogin> findByBankName(String bankName) {
        Session currentSession = HibernateSessionUtil.getSession();
        Criteria criteria = currentSession.createCriteria(type)
                .createCriteria("bank", "bank")
                    .add(Restrictions.eq("bank.bankName", bankName));
        return (List<BankAccountLogin>) criteria.list();
    }
}
