/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.webbrowsermanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

/**
 *
 * @author azazello
 */
public abstract class SeleniumWebBrowser implements WebBrowserInterface {

    protected enum WebDriverStatus {
        LOGGED,
        UNLOGGED
    }
    
    protected static WebDriverStatus webDriverStatus = WebDriverStatus.UNLOGGED;
    private static WebDriver webDriver = null;
    
    public static WebDriver CreateWebDriver() {        
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        
        firefoxProfile.setPreference(WebBrowserUtils.USER_AGENT_PREFERENCE_STRING, 
                WebBrowserUtils.WEB_BROWSER_USER_AGENT);
        webDriver = new FirefoxDriver(firefoxProfile);

        return webDriver;
    }
    
    public static void DestroyWebDriver() {
        webDriver = null;
        webDriverStatus = WebDriverStatus.UNLOGGED;
    }
    
    protected static void EnterLoginPage(WebBrowserUtils.BankType bankType) {
        if (null != webDriver) {
            return;
        }
        
        String loginPageUrl = WebBrowserUtils.GetBankLoginPage(bankType);
        webDriver.get(loginPageUrl);
        
        webDriverStatus = WebDriverStatus.LOGGED;
    }
}
