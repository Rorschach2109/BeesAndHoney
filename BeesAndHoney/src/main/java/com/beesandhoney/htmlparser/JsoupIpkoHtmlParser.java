/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.htmlparser;

import com.beesandhoney.accountmanager.IpkoAccountUtils;
import com.beesandhoney.datamanager.AccountState;
import com.beesandhoney.datamanager.DataManagerFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author azazello
 */
public final class JsoupIpkoHtmlParser extends HtmlParserInterface {
    
    @Override
    public ArrayList<AccountState> GetAccountState(String accountAmountsSection) {
        Document doc = Jsoup.parse(accountAmountsSection);
        Elements accountNumberList = doc.getElementsByClass(
                IpkoAccountUtils.FIELD_ACCOUNT_NUMBER_TAG
        );
        Elements accountsInformationList = doc.getElementsByClass(
                IpkoAccountUtils.FIELD_ACCOUNT_STATE_TAG
        );
        
        HashMap<String, ArrayList<String>> accountsStateMap = 
                GetAccountsStateMap(accountNumberList, accountsInformationList);
        
        ArrayList<AccountState> accountStateList = 
                DataManagerFactory.CreateAccountStateList(accountsStateMap);
        
        return accountStateList;
    }
    
    @Override
    public void GetAccountInformation(String accountInformationSection) {
        
    }
    
    @Override
    public void GetAccountHistory(String accountHistoryInformation) {
        
    }
    
    private HashMap<String, ArrayList<String>> GetAccountsStateMap(
            Elements accountNumberList, Elements accountsInformationList) {
        
        HashMap<String, ArrayList<String>> accountsStateMap = new HashMap<>();
        
        for (int accountNumberListIndex = 0; 
                accountNumberListIndex < accountNumberList.size(); 
                ++accountNumberListIndex) {
            
            String accountNumber = GetValueFromPattern(
                    IpkoAccountUtils.PATTERN_ACCOUNT_NUMBER,
                    accountNumberList.get(accountNumberListIndex).text()
            );
            
            if (!accountNumber.isEmpty()) {
                int startIndex = accountNumberListIndex * 3;
                List<Element> accountStateElementList = accountsInformationList.subList(
                        startIndex, startIndex + 3);
                
                ArrayList<String> accountStateStringList = new ArrayList<>();
                accountStateElementList.forEach((accountStateElement) -> {
                    accountStateStringList.add(accountStateElement.text());
                });
                
                accountsStateMap.put(accountNumber, accountStateStringList);
            }
        }
        
        return accountsStateMap;
    }
}
