/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.browser.constants;

public final class BrowserConstantsManagerPKO extends AbstractBrowserConstantsManager {
    
    public BrowserConstantsManagerPKO() {
        super();
    }
    
    @Override
    public void initialize() {
        initializeJavascriptLoginCommands();
        initializeJavascriptAccountInformationCommands();
    }
    
    private void initializeJavascriptLoginCommands() {
        commandSetClientId = "document.getElementById('client_id_field').value = '%s';";
        commandSetPassword = "document.getElementById('password_field').value = '%s';";
        
        commandClickLoginButton = "document.getElementsByName('btn_ok')[1].click();";
        commandClickLogoutButton = "document.getElementById('tm5').click()";
        
        commandClickAccountDetailsMenu = "document.getElementById('leftmenu').children[0].children[1].click();";
        commandClickAccountDetails = "document.getElementsByClassName('dark dark')[%s].children[0].children[1].click();";
        commandGetAccountsCount = "document.getElementsByClassName('dark dark').length";
        
        commandEnterHomePage = "document.getElementById('mainmenu').children[0].children[0].click();";
    }
    
    private void initializeJavascriptAccountInformationCommands() {
        commandAccountNumber = "document.getElementsByName('content_table')[0].children[0].children[2].children[1].childNodes[0].wholeText";
        commandAccountName = "document.getElementsByClassName('insideTable')[0].children[0].children[0].children[1].childNodes[0].innerText";
        commandAccountBalance = "document.getElementsByName('content_table')[0].children[0].children[4].children[1].childNodes[0].wholeText";
        commandAvailableSources = "document.getElementsByClassName('insideTable')[1].children[0].children[0].children[1].childNodes[0].innerText";
        commandOwnerNameSurname = "document.getElementsByName('content_table')[0].children[0].children[13].children[1].childNodes[0].wholeText";
        commandOwnerAddress = "document.getElementsByName('content_table')[0].children[0].children[17].children[1].childNodes[0].wholeText + "
                + "' ' + document.getElementsByName('content_table')[0].children[0].children[17].children[1].childNodes[2].wholeText";
    }
}
