/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.browser.state;

import com.beesandhoney.utils.constants.BankConstants;

public final class WebBrowserStateFactory {
    
    private WebBrowserStateFactory() {
        
    }
    
    public static IWebBrowserState createBrowserState(String bankName) {
        switch (bankName) {
            case BankConstants.BankPKO.NAME: {
                return new WebBrowserStatePKO(BankConstants.BankPKO.URL);
            }
            
            case BankConstants.BankBZWBK.NAME: {
                return new WebBrowserStateWBK(BankConstants.BankBZWBK.URL);
            }
            
            default: {
                return null;
            }
        }
    }
}
