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
        implements GenericDao<T, PK>, ObservableInterface {
    
    protected final Class<T> type;
    private ArrayList<ObserverInterface> observers;
    
    protected GenericDaoHibernateImpl(Class<T> type) {
        this.type = type;
    }

    @Override
    public PK create(T newInstance) {
        return (PK) HibernateSessionUtil.getSession().save(newInstance);
    }
    
    @Override
    public T read(PK primaryKey) {
        return (T) HibernateSessionUtil.getSession().get(type, primaryKey);
    }
    
    @Override
    public void update(T transientObject) {
        HibernateSessionUtil.getSession().update(transientObject);
    }
    
    @Override
    public void delete(T persistentObject) {
        HibernateSessionUtil.getSession().delete(persistentObject);
    }
    
    @Override
    public void createOrUpdate(T instance) {
        HibernateSessionUtil.getSession().saveOrUpdate(instance);
    }
    
    @Override
    public List<T> readAll() {
        Session currentSession = HibernateSessionUtil.getSession();
        return (List<T>) currentSession.createCriteria(type).list();
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
}
