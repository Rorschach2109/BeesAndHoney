/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.model.dao;

import java.util.List;

public interface GenericDao <T, PK extends java.io.Serializable, S> {
    public PK create(T newInstance, S session);
    public T read(PK primaryKey, S session);
    public void update(T transientObject, S session);
    public void delete(T persistentObject, S session);
    
    public void createOrUpdate(T instance, S session);
    public List<T> readAll(S session);
}
