/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.utils.hibernate;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
    
    static {
        open();
    }
    
    public static void open() {
        if (null == sessionFactory || 
                (null != sessionFactory && sessionFactory.isClosed())) {
            try {
                Configuration configuration = new Configuration();
                configuration.configure();
                ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
                configuration.getProperties()). buildServiceRegistry();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Throwable ex) {
                throw new ExceptionInInitializerError(ex);
            }
        }
    }
    
    public static void close() {
        if (null != sessionFactory && false == sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }
    
    public static void restart() {
        close();
        open();
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
