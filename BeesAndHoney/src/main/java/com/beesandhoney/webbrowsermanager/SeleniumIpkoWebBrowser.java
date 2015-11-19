/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.webbrowsermanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author azazello
 */
public final class SeleniumIpkoWebBrowser extends SeleniumWebBrowser {
        
    @Override
    public ArrayList<String> GetAccountsAmount(WebDriver webDriver) {
        if (null == webDriver || WebDriverStatus.UNLOGGED == webDriverStatus) {
            return new ArrayList<>();
        }
        
        return new ArrayList<>();
    }
    
    @Override
    public Map<String, String> GetAccountInformation(WebDriver webDriver) {
        if (null == webDriver || WebDriverStatus.UNLOGGED == webDriverStatus) {
            return new HashMap<>();
        }
        
        return new HashMap<>();
    }
    
    @Override
    public String GetAccountHistorySection(WebDriver webDriver) {
        if (null == webDriver || WebDriverStatus.UNLOGGED == webDriverStatus) {
            return "";
        }
        
        return "";
    }
}
