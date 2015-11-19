/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.webbrowsermanager;

import org.openqa.selenium.WebDriver;

/**
 *
 * @author azazello
 */
public abstract class SeleniumWebBrowser implements WebBrowserInterface {

    protected enum WebDriverStatus {
        LOGGED,
        UNLOGGED
    }
    
    protected SeleniumWebBrowser.WebDriverStatus webDriverStatus = 
            SeleniumWebBrowser.WebDriverStatus.UNLOGGED;
    
    @Override
    public void LogInToAccount(WebDriver webDriver, String clientId, String password) {
        EnterLoginPage(webDriver);
        try {
            InsertClientId(webDriver, clientId);
            InsertPassword(webDriver, password);
            LogIn(webDriver);
            webDriverStatus = SeleniumWebBrowser.WebDriverStatus.LOGGED;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            SeleniumWebDriverFactory.DestroyWebDriver();
        }
    }
    
    @Override
    public WebDriver CreateWebDriver() {
        return SeleniumWebDriverFactory.CreateWebDriver();
    }
    
    @Override
    public void DestroyWebDriver() {
        webDriverStatus = SeleniumWebBrowser.WebDriverStatus.UNLOGGED;
    }
    
    protected void EnterLoginPage(WebDriver webDriver) {
        if (null == webDriver) {
            return;
        }
        
        String loginPageUrl = GetBankLoginPage();
        webDriver.get(loginPageUrl);
    }
    
    protected abstract void ReturnToHomePage(WebDriver webDriver);
    protected abstract void InsertClientId(WebDriver webDriver, String clientId);
    protected abstract void InsertPassword(WebDriver webDriver, String password);
    protected abstract void LogIn(WebDriver webDriver);
    protected abstract String GetBankLoginPage();
}
