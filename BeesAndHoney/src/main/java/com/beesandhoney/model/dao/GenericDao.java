/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.model.dao;

import java.util.List;

public interface GenericDao <T, PK extends java.io.Serializable> {
    public PK create(T newInstance);
    public T read(PK primaryKey);
    public void update(T transientObject);
    public void delete(T persistentObject);
    
    public void createOrUpdate(T instance);
    public List<T> readAll();
}
