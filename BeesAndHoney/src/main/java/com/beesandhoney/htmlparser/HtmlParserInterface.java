/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.htmlparser;

/**
 *
 * @author azazello
 */
public interface HtmlParserInterface {
    public void GetAccountState(String accountAmountsSection);
    public void GetAccountInformation(String accountInformationSection);
    public void GetAccountHistory(String accountHistoryInformation);
}
