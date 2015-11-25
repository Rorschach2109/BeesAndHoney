/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.htmlparser;

import com.beesandhoney.datamanager.AccountState;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author azazello
 */
public abstract class HtmlParserInterface {
    public abstract ArrayList<AccountState> GetAccountState(String accountAmountsSection);
    public abstract void GetAccountInformation(String accountInformationSection);
    public abstract void GetAccountHistory(String accountHistoryInformation);
    
    protected final ArrayList<String> GetValuesListFromPattern(String pattern, 
            String text) {
        Pattern accountNumberPattern = Pattern.compile(pattern);

        Matcher matcher = accountNumberPattern.matcher(text);
        
        ArrayList<String> returnValue = new ArrayList<>();
        if (matcher.find()) {
            for (int matcherGroupIndex = 1; 
                    matcherGroupIndex <= matcher.groupCount(); 
                    ++matcherGroupIndex) {
                returnValue.add(matcher.group(matcherGroupIndex));
            }
        }
        
        return returnValue;
    }
}
