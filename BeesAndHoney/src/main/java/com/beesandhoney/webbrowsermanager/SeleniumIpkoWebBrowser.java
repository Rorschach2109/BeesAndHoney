/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.webbrowsermanager;

import com.beesandhoney.accountmanager.IpkoAccountUtils;
import com.beesandhoney.datamanager.AccountState;
import com.beesandhoney.htmlparser.HtmlParserInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

/**
 *
 * @author azazello
 */
public final class SeleniumIpkoWebBrowser extends SeleniumWebBrowser {

    public SeleniumIpkoWebBrowser(HtmlParserInterface htmlParser) {
        super(htmlParser);
    }
    
    @Override
    public ArrayList<AccountState> GetAccountsAmount() {
        if (null == webDriver || WebDriverStatus.UNLOGGED == webDriverStatus) {
            return new ArrayList<>();
        }
        
        ReturnToHomePage();

        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        jsExecutor.executeScript(IpkoAccountUtils.JS_ACCOUNTS_SECTION_COMMAND);
        
        return htmlParser.GetAccountState(webDriver.getPageSource());
    }
    
    @Override
    public Map<String, String> GetAccountInformation() {
        if (null == webDriver || WebDriverStatus.UNLOGGED == webDriverStatus) {
            return new HashMap<>();
        }
        
        ReturnToHomePage();
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        
        jsExecutor.executeScript(IpkoAccountUtils.JS_ACCOUNTS_SECTION_COMMAND);
        jsExecutor.executeScript(IpkoAccountUtils.JS_ACCOUNT_DETAILS_COMMAND);
        
        System.out.println(GetContentTableFieldHtml());
        
        return new HashMap<>();
    }
    
    @Override
    public String GetAccountHistorySection() {
        if (null == webDriver || WebDriverStatus.UNLOGGED == webDriverStatus) {
            return "";
        }
        
        ReturnToHomePage();
        
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        jsExecutor.executeScript(IpkoAccountUtils.JS_HISTORY_COMMAND);
        
        WebElement historyFilterButton = webDriver.findElements(
                By.name(IpkoAccountUtils.FIELD_HISTORY_FILTER_BUTTON_TAG)
        ).get(1);
        historyFilterButton.click();
        
        WebElement contentTable = webDriver.findElements(
                By.name(IpkoAccountUtils.FIELD_CONTENT_TABLE_TAG)
        ).get(1);
        
        System.out.println(contentTable.getAttribute(WebBrowserUtils.INNER_HTML_STRING));

        return "";
    }
    
    @Override
    protected void ReturnToHomePage() {
        if (null == webDriver || WebDriverStatus.UNLOGGED == webDriverStatus) {
            return;
        }
        
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        jsExecutor.executeScript(IpkoAccountUtils.JS_HOME_PAGE_COMMAND);
    }
    
    @Override
    protected void InsertClientId(String clientId) {
        if (null == webDriver) {
            return;
        }
        
        WebElement clientIdFieldElement = webDriver.findElement(
                By.id(IpkoAccountUtils.FIELD_CLIENT_ID_TAG)
        );
        clientIdFieldElement.sendKeys(clientId);
    }
    
    @Override
    protected void InsertPassword(String password) {
        if (null == webDriver) {
            return;
        }
        
        WebElement passwordFieldElement = webDriver.findElement(
                By.id(IpkoAccountUtils.FIELD_PASSWORD_TAG)
        );
        passwordFieldElement.sendKeys(password);
    }
    
    @Override
    protected void LogIn() {
        if (null == webDriver) {
            return;
        }
        
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        jsExecutor.executeScript(IpkoAccountUtils.JS_LOGIN_COMMAND);
    }
    
    @Override
    protected void LogOut() {
        if (null == webDriver || WebDriverStatus.UNLOGGED == webDriverStatus) {
            return;
        }
        
        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        jsExecutor.executeScript(IpkoAccountUtils.JS_LOGOUT_COMMAND);
    }
    
    @Override
    protected String GetBankLoginPage() {
        return IpkoAccountUtils.LOGIN_PAGE;
    }
    
    private String GetContentTableFieldHtml() {
        if (null == webDriver || WebDriverStatus.UNLOGGED == webDriverStatus) {
            return "";
        }
        
        WebElement contentTable = webDriver.findElement(
                By.name(IpkoAccountUtils.FIELD_CONTENT_TABLE_TAG)
        );
        return contentTable.getAttribute(WebBrowserUtils.INNER_HTML_STRING);
    }
}
