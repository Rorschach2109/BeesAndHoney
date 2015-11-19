/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.webbrowsermanager;

import com.beesandhoney.accountmanager.IpkoAccountUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author azazello
 */
public final class SeleniumIpkoWebBrowser extends SeleniumWebBrowser {

    public SeleniumIpkoWebBrowser() {
    }
    
    @Override
    public ArrayList<String> GetAccountsAmount(WebDriver webDriver) {
        if (null == webDriver || WebDriverStatus.UNLOGGED == webDriverStatus) {
            return new ArrayList<>();
        }
        
        ReturnToHomePage(webDriver);
        System.out.println(GetContentTableFieldText(webDriver));
        
        return new ArrayList<>();
    }
    
    @Override
    public Map<String, String> GetAccountInformation(WebDriver webDriver) {
        if (null == webDriver || WebDriverStatus.UNLOGGED == webDriverStatus) {
            return new HashMap<>();
        }
        
        ReturnToHomePage(webDriver);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        
        jsExecutor.executeScript(IpkoAccountUtils.JS_ACCOUNTS_SECTION_COMMAND);
        jsExecutor.executeScript(IpkoAccountUtils.JS_ACCOUNT_DETAILS_COMMAND);
        
        System.out.println(GetContentTableFieldText(webDriver));
        
        return new HashMap<>();
    }
    
    @Override
    public String GetAccountHistorySection(WebDriver webDriver) {
        if (null == webDriver || WebDriverStatus.UNLOGGED == webDriverStatus) {
            return "";
        }
        
        ReturnToHomePage(webDriver);
        
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        jsExecutor.executeScript(IpkoAccountUtils.JS_HISTORY_COMMAND);
        
        WebElement historyFilterButton = webDriver.findElements(
                By.name(IpkoAccountUtils.FIELD_HISTORY_FILTER_BUTTON_TAG)
        ).get(1);
        historyFilterButton.click();
        
        WebElement contentTable = webDriver.findElements(
                By.name(IpkoAccountUtils.FIELD_CONTENT_TABLE_TAG)
        ).get(1);
        
        System.out.println(contentTable.getText());

        return "";
    }
    
    @Override
    protected void ReturnToHomePage(WebDriver webDriver) {
        if (null == webDriver) {
            return;
        }
        
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        jsExecutor.executeScript(IpkoAccountUtils.JS_HOME_PAGE_COMMAND);
    }
    
    @Override
    protected void InsertClientId(WebDriver webDriver, String clientId) {
        if (null == webDriver) {
            return;
        }
        
        WebElement clientIdFieldElement = webDriver.findElement(
                By.id(IpkoAccountUtils.FIELD_CLIENT_ID_TAG)
        );
        clientIdFieldElement.sendKeys(clientId);
    }
    
    @Override
    protected void InsertPassword(WebDriver webDriver, String password) {
        if (null == webDriver) {
            return;
        }
        
        WebElement passwordFieldElement = webDriver.findElement(
                By.id(IpkoAccountUtils.FIELD_PASSWORD_TAG)
        );
        passwordFieldElement.sendKeys(password);
    }
    
    @Override
    protected void LogIn(WebDriver webDriver) {
        if (null == webDriver) {
            return;
        }
        
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        jsExecutor.executeScript(IpkoAccountUtils.JS_LOGIN_COMMAND);
    }
    
    @Override
    protected String GetBankLoginPage() {
        return IpkoAccountUtils.LOGIN_PAGE;
    }
    
    private String GetContentTableFieldText(WebDriver webDriver) {
        WebElement contentTable = webDriver.findElement(
                By.name(IpkoAccountUtils.FIELD_CONTENT_TABLE_TAG)
        );
        return contentTable.getText();
    }
}
