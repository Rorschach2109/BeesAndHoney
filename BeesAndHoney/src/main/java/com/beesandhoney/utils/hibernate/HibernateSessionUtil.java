/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.utils.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;


public class HibernateSessionUtil {
    
    private static Transaction transaction = null;
    private static Session currentSession = null;
    
    public static void openSession() {
        if (null == currentSession) {
            currentSession = HibernateUtil.getSessionFactory().openSession();
        }
    }
    
    public static void closeSession() {
        if (null != currentSession) {
            currentSession.close();
        }
    }
    
    public static void openSessionWithTransaction() {
        if (null == currentSession) {
            currentSession = HibernateUtil.getSessionFactory().openSession();
            transaction = currentSession.beginTransaction();
        }
    }
    
    public static void closeSessionWithTransaction() {
        if (null != currentSession) {
            transaction.commit();
            currentSession.close();
            currentSession = null;
        }
    }
    
    public static Session getSession() {
        return currentSession;
    }
}
