/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.webbrowsermanager;

import com.beesandhoney.htmlparser.HtmlParserInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author azazello
 */
public final class SeleniumBzwbkWebBrowser extends SeleniumWebBrowser {

    public SeleniumBzwbkWebBrowser(HtmlParserInterface htmlParser) {
        super(htmlParser);
    }

    @Override
    public ArrayList<String> GetAccountsAmount() {
        if (null == webDriver || WebDriverStatus.UNLOGGED == webDriverStatus) {
            return new ArrayList<>();
        }
        
        return new ArrayList<>();
    }
    
    @Override
    public Map<String, String> GetAccountInformation() {
        if (null == webDriver || WebDriverStatus.UNLOGGED == webDriverStatus) {
            return new HashMap<>();
        }
        
        return new HashMap<>();
    }
    
    @Override
    public String GetAccountHistorySection() {
        if (null == webDriver || WebDriverStatus.UNLOGGED == webDriverStatus) {
            return "";
        }
        
        return "";
    }

    @Override
    protected void ReturnToHomePage() {
        
    }
    
    @Override
    protected void InsertClientId(String clientId) {
        
    }
    
    @Override
    protected void InsertPassword(String password) {
        
    }
    
    @Override
    protected void LogIn() {
        
    }
    
    @Override
    protected void LogOut() {

    }
    
    @Override
    protected String GetBankLoginPage() {
        return "";
    }
}
