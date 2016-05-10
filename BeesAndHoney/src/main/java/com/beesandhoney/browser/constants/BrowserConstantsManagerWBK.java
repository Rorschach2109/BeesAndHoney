/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.browser.constants;

public class BrowserConstantsManagerWBK extends AbstractBrowserConstantsManager {
    
    public BrowserConstantsManagerWBK() {
        super();
    }
    
    @Override
    public void initialize() {
        initializeJavascriptLoginCommands();
        initializeJavascriptAccountInformationCommands();
    }
    
    private void initializeJavascriptLoginCommands() {
        commandSetClientId = "document.getElementById('input_nik').value = '%s';";
        
        commandIsOrdinaryPassword = "document.getElementsByClassName('passwordItem').length == 0;";
        commandSetOrdinaryPassword = "document.getElementById('ordinarypin').value = '%s';";
        commandSetMaskedPassword = "";
        
        commandClickLoginButton = "document.getElementById('okBtn2').click();";
        commandClickLogoutButton = "document.getElementsByClassName('logout')[0].childNodes[1].click();";
        
        commandClickAccountDetailsMenu = "";
        commandClickAccountDetails = "document.getElementById('%s').getElementsByTagName('tbody')[%s].getElementsByClassName('menu-actions')[0].getElementsByTagName('li')[1].childNodes[1].click();";
        commandClickCreditCardAccountDetails = "document.getElementById('creditCardsBoxContent').getElementsByTagName('tbody')[0].getElementsByClassName('menu-actions')[0].getElementsByTagName('li')[5].childNodes[1].click();";
        commandGetAccountsCount = "document.getElementById('avistaAccountsBoxContent').getElementsByTagName('tbody')[0].getElementsByTagName('tr').length;";
        commandGetCreditCardsAccountsCount = "document.getElementById('creditCardsBoxContent').getElementsByTagName('tbody')[0].getElementsByTagName('tr').length;";
        
        commandEnterHomePage = "document.getElementById('menu_portfel_24').children[0].click();";
    }
    
    private void initializeJavascriptAccountInformationCommands() {
        commandAccountNumber = "";
        commandAccountName = "document.getElementsByClassName('report-table')[0].getElementsByTagName('tbody')[0].children[0].children[1].childNodes[1].innerText;";
        commandCreditCardLimit = "document.getElementsByClassName('report-table')[0].getElementsByTagName('tbody')[0].children[4].children[1].childNodes[1].innerHTML.replace('&nbsp;', '');";
        commandAccountBalance = "document.getElementsByClassName('report-table')[0].getElementsByTagName('tbody')[0].children[2].children[1].childNodes[1].innerHTML.replace('&nbsp;', '');";
        commandAvailableSources = "document.getElementsByClassName('report-table')[0].getElementsByTagName('tbody')[0].children[3].children[1].childNodes[1].innerHTML.replace('&nbsp;', '');";
        commandOwnerNameSurname = "document.getElementsByClassName('report-table')[1].getElementsByTagName('tbody')[0].children[0].children[1].childNodes[1].innerText;";
        commandOwnerAddress = "document.getElementsByClassName('report-table')[1].getElementsByTagName('tbody')[0].children[1].children[1].childNodes[1].innerText + ' ' + "
                + "document.getElementsByClassName('report-table')[1].getElementsByTagName('tbody')[0].children[2].children[1].childNodes[1].innerText + ', ' + "
                + "document.getElementsByClassName('report-table')[1].getElementsByTagName('tbody')[0].children[3].children[1].childNodes[1].innerText + ' ' + "
                + "document.getElementsByClassName('report-table')[1].getElementsByTagName('tbody')[0].children[4].children[1].childNodes[1].innerText;";
    }
}
