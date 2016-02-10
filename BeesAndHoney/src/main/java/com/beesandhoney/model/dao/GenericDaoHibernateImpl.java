/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.model.dao;

import com.beesandhoney.utils.ObservableInterface;
import com.beesandhoney.utils.ObserverInterface;
import com.beesandhoney.utils.hibernate.HibernateSessionUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

public class GenericDaoHibernateImpl <T, PK extends java.io.Serializable> 
        implements GenericDao<T, PK, Session>, ObservableInterface {
    
    protected final Class<T> type;
    
    private final ArrayList<ObserverInterface> observers;
    
    protected GenericDaoHibernateImpl(Class<T> type) {
        this.type = type;
        this.observers = new ArrayList<>();
    }

    @Override
    public PK create(T newInstance, Session session) {
        return (PK) session.save(newInstance);
    }
    
    @Override
    public T read(PK primaryKey, Session session) {
        return (T) session.get(type, primaryKey);
    }
    
    @Override
    public void update(T transientObject, Session session) {
        session.update(transientObject);
    }
    
    @Override
    public void delete(T persistentObject, Session session) {
        session.delete(persistentObject);
    }
    
    @Override
    public void createOrUpdate(T instance, Session session) {
        session.saveOrUpdate(instance);
    }
    
    @Override
    public List<T> readAll(Session session) {
        return (List<T>) session.createCriteria(type).list();
    }

    @Override
    public void registerObserver(ObserverInterface observer) {
        this.observers.add(observer);
    }

    @Override
    public void deleteObserver(ObserverInterface observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (ObserverInterface observer : this.observers) {
            observer.update();
        }
    }
    
    public Session openSession() {
        return HibernateSessionUtil.openSession();
    }
    
    public void closeSession(Session session) {
        HibernateSessionUtil.closeSession(session);
    }
    
    public Session openSessionWithTransaction() {
        return HibernateSessionUtil.openSessionWithTransaction();
    }
    
    public void closeSessionWithTransaction(Session session) {
        HibernateSessionUtil.closeSessionWithTransaction(session);
        notifyObservers();
    }
}
