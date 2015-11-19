/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.webbrowsermanager;

import java.util.ArrayList;
import java.util.Map;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author azazello
 */
public interface WebBrowserInterface {
    public ArrayList<String> GetAccountsAmount(WebDriver webDriver);
    public Map<String, String> GetAccountInformation(WebDriver webDriver);
    public String GetAccountHistorySection(WebDriver webDriver);
}