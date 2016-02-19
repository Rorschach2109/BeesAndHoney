/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.browser;

import com.beesandhoney.controller.IController;
import com.beesandhoney.model.BankAccountLogin;

public interface IWebBrowser extends IController {
    
    public void initialize(IController mainController);
    public void getAccountInformation(BankAccountLogin accountLogin);
}
