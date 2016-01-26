/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.model.dao;

import com.beesandhoney.model.Bank;
import com.beesandhoney.model.BeesAndHoneyUser;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class BeesAndHoneyUserDao extends GenericDaoHibernateImpl<BeesAndHoneyUser, Integer> {
    
    private static BeesAndHoneyUserDao instance;
    
    static
    {
        instance = null;
    }
    
    private BeesAndHoneyUserDao() {
        super(BeesAndHoneyUser.class);
    }
    
    public static BeesAndHoneyUserDao getInstance() {
        if (null == instance) {
            instance = new BeesAndHoneyUserDao();
        }
        
        return instance;
    }
    
    public BeesAndHoneyUser findByUserName(String userName, Session session) {
        Criteria criteria = session.createCriteria(type);
        criteria.add(Restrictions.eq("userName", userName));
        
        return (BeesAndHoneyUser) criteria.uniqueResult();
    }
}
