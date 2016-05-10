/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.browser.constants;

public abstract class AbstractBrowserConstantsManager {
    
    protected String commandSetClientId;
    
    protected String commandIsOrdinaryPassword;
    protected String commandSetOrdinaryPassword;
    protected String commandSetMaskedPassword;

    protected String commandClickLoginButton;
    protected String commandClickLogoutButton;
    
    protected String commandClickAccountDetailsMenu;
    protected String commandClickAccountDetails;
    protected String commandClickCreditCardAccountDetails;
    
    protected String commandGetAccountsCount;
    protected String commandGetCreditCardsAccountsCount;
    
    protected String commandEnterHomePage;
    
    protected String commandAccountNumber;
    protected String commandAccountName;
    protected String commandCreditCardLimit;
    protected String commandAccountBalance;
    protected String commandAvailableSources;
    protected String commandOwnerNameSurname;
    protected String commandOwnerAddress;
    
    public AbstractBrowserConstantsManager() {
        commandGetCreditCardsAccountsCount = "0";
    }

    public abstract void initialize();

    public String getCommandSetClientId() {
        return commandSetClientId;
    }

    public String getCommandIsOrdinaryPassword() {
        return commandIsOrdinaryPassword;
    }
    
    public String getCommandSetOrdinaryPassword() {
        return commandSetOrdinaryPassword;
    }
    
    public String getCommandSetMaskedPassword() {
        return commandSetMaskedPassword;
    }

    public String getCommandClickLoginButton() {
        return commandClickLoginButton;
    }

    public String getCommandClickLogoutButton() {
        return commandClickLogoutButton;
    }

    public String getCommandClickAccountDetailsMenu() {
        return commandClickAccountDetailsMenu;
    }

    public String getCommandClickAccountDetails() {
        return commandClickAccountDetails;
    }

    public String getCommandClickCreditCardAccountDetails() {
        return commandClickCreditCardAccountDetails;
    }

    public String getCommandGetAccountsCount() {
        return commandGetAccountsCount;
    }

    public String getCommandGetCreditCardsAccountsCount() {
        return commandGetCreditCardsAccountsCount;
    }

    public String getCommandEnterHomePage() {
        return commandEnterHomePage;
    }

    public String getCommandAccountNumber() {
        return commandAccountNumber;
    }

    public String getCommandAccountName() {
        return commandAccountName;
    }
    
    public String getCommandCreditCardLimit() {
        return commandCreditCardLimit;
    }

    public String getCommandAccountBalance() {
        return commandAccountBalance;
    }

    public String getCommandAvailableSources() {
        return commandAvailableSources;
    }

    public String getCommandOwnerNameSurname() {
        return commandOwnerNameSurname;
    }

    public String getCommandOwnerAddress() {
        return commandOwnerAddress;
    }
    
}
