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
    protected WebDriver webDriver;
    
    {
        webDriver = null;
    }
    
    @Override
    public void LogIntoAccount(String clientId, String password) {
        EnterLoginPage();
        try {
            InsertClientId(clientId);
            InsertPassword(password);
            LogIn();
            webDriverStatus = SeleniumWebBrowser.WebDriverStatus.LOGGED;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            SeleniumWebDriverFactory.DestroySeleniumWebDriver();
        }
    }
    
    @Override
    public void LogOutFromAccount() {
        try {
            LogOut();
            webDriverStatus = SeleniumWebBrowser.WebDriverStatus.UNLOGGED;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            
        }
    }
    
    @Override
    public void CreateWebDriver() {
        webDriver = SeleniumWebDriverFactory.CreateSeleniumWebDriver();
    }
    
    @Override
    public void DestroyWebDriver() {
        SeleniumWebDriverFactory.DestroySeleniumWebDriver();
        webDriverStatus = SeleniumWebBrowser.WebDriverStatus.UNLOGGED;
        webDriver = null;
    }
    
    protected void EnterLoginPage() {
        if (null == webDriver) {
            return;
        }
        
        String loginPageUrl = GetBankLoginPage();
        webDriver.get(loginPageUrl);
    }
    
    protected abstract void ReturnToHomePage();
    protected abstract void InsertClientId(String clientId);
    protected abstract void InsertPassword(String password);
    protected abstract void LogIn();
    protected abstract void LogOut();
    protected abstract String GetBankLoginPage();
}
