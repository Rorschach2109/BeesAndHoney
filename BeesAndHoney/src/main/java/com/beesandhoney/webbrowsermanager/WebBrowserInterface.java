/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.webbrowsermanager;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author azazello
 */
public interface WebBrowserInterface {
    public void CreateWebDriver();
    public void DestroyWebDriver();

    public void LogInToAccount(String clientId, String password);
    public void LogOutFromAccount();
    
    public ArrayList<String> GetAccountsAmount();
    public Map<String, String> GetAccountInformation();
    public String GetAccountHistorySection();
}
