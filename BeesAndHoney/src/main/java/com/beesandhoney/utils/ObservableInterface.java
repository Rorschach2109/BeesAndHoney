/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.utils;

public interface ObservableInterface {
    public void registerObserver(ObserverInterface observer);
    public void deleteObserver(ObserverInterface observer);
    public void notifyObservers();
}
