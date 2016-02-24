/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.browser.state;

import com.beesandhoney.model.BankAccount;
import com.beesandhoney.model.BankAccountLogin;
import com.beesandhoney.utils.ObservableInterface;
import com.beesandhoney.utils.ObserverInterface;
import javafx.scene.web.WebEngine;

public interface IWebBrowserState extends ObservableInterface {
    
    public void getAccountInformation(BankAccountLogin bankAccountLogin);
    public BankAccount getStashedAccountInformation();
    
    public void setWebEngine(WebEngine webEngine);
    
    public void logout();
    
    @Override
    public void registerObserver(ObserverInterface observer);
    
    @Override
    public void deleteObserver(ObserverInterface observer);
    
    @Override
    public void notifyObservers();
    
    public boolean isLogged();
}
