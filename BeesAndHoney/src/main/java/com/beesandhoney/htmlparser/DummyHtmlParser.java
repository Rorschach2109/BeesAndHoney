/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.htmlparser;

import com.beesandhoney.datamanager.AccountState;
import java.util.ArrayList;


/**
 *
 * @author azazello
 */
public class DummyHtmlParser extends HtmlParserInterface{
    
    @Override
    public ArrayList<AccountState> GetAccountState(String accountAmountsPage) {
        return new ArrayList<>(0);
    }
    
    @Override
    public void GetAccountInformation(String accountInformationPage) {
        return;
    }
    
    @Override
    public void GetAccountHistory(String accountHistoryInformation) {
        return;
    }
}
