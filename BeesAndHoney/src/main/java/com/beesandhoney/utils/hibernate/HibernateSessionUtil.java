/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.utils.hibernate;

import org.hibernate.Session;


public class HibernateSessionUtil {
    
    public static Session openSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }
    
    public static void closeSession(Session session) {
        if (null != session) {
            session.close();
        }
    }
    
    public static Session openSessionWithTransaction() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        return session;
    }
    
    public static void closeSessionWithTransaction(Session session) {
        if (null != session) {
            session.getTransaction().commit();
            session.close();
        }
    }
}
