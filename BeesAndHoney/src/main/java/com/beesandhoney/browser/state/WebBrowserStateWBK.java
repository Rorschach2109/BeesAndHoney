/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.browser.state;

import com.beesandhoney.browser.constants.BrowserConstantsManagerWBK;
import com.beesandhoney.model.BankAccountLogin;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;

public class WebBrowserStateWBK extends AbstractWebBrowserState {

    private ChangeListener<Worker.State> changeListener;
    
    private String currentAccountNumber;
    
    private final static String ACCOUNTS_SECTION_NAME;
    private final static String CREDIT_CARDS_SECTION_NAME;
    
    
    private enum EState {
        LOGIN_PAGE,
        PASSWORD_PAGE,
        HOME_PAGE,
        ACCOUNT_PAGE
    };
    private EState innerState;
    
    static
    {
        ACCOUNTS_SECTION_NAME = "avistaAccountsBoxContent";
        CREDIT_CARDS_SECTION_NAME = "creditCardsBoxContent";
        
    }
    
    public WebBrowserStateWBK(String loginPageUrl) {
        super(loginPageUrl, new BrowserConstantsManagerWBK());
        
        this.innerState = EState.LOGIN_PAGE;
    }
    
    @Override
    public void getAccountInformation(BankAccountLogin bankAccountLogin) {
        addLoginListener();
        
        super.getAccountInformation(bankAccountLogin);
    }
    
    @Override
    public void logout() {
        this.innerState = EState.LOGIN_PAGE;
        removeListener();
        
        super.logout();
    }
    
    private void addLoginListener() {
        this.changeListener = new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observable, 
                    Worker.State oldValue, Worker.State newValue) {
                if (Worker.State.SUCCEEDED != newValue) {
                    return;
                }
                
                switch(innerState) {
                    case LOGIN_PAGE: {
                        innerState = EState.PASSWORD_PAGE;
                        insertClientId();
                        break;
                    }
                    
                    case PASSWORD_PAGE: {
                        innerState = EState.HOME_PAGE;
                        if (isOrdinaryPassword()) {
                            insertOrdinaryPassword();
                        } else {
                            insertMaskedPassword();
                        }
                        break;
                    }
                    
                    case HOME_PAGE: {
                        setAccountsCount();
                        addAccountsInformationListener();
                        break;
                    }
                    
                    default: {
                        break;
                    }
                }
            }
        };
        
        this.webEngine.getLoadWorker().stateProperty().addListener(
                this.changeListener
        );
    }
    
    private void addAccountsInformationListener() {
        removeListener();
        
        this.changeListener = new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observable, 
                    Worker.State oldValue, Worker.State newValue) {
                if (Worker.State.SUCCEEDED != newValue) {
                    return;
                }
                
                switch(innerState) {
                    case HOME_PAGE: {
                        innerState = EState.ACCOUNT_PAGE;
                        enterAccountInformationPage();
                        break;
                    }
                    
                    case ACCOUNT_PAGE: {
                        innerState = EState.HOME_PAGE;
                        performParsingInformation();
                        break;
                    }
                    
                    default: {
                        break;
                    }
                }
            }
        };
        
        this.webEngine.getLoadWorker().stateProperty().addListener(
                this.changeListener
        );
        
        enterHomePage();
    }
    
    private void removeListener() {
        if (null != this.changeListener) {
            this.webEngine.getLoadWorker().stateProperty().removeListener(
                    this.changeListener
            );
        }
    }
    
    private void insertClientId() {
        insertClientId(this.currentBankAccountLogin.getClientId());
        clickLoginButton();
    }
    
    private boolean isOrdinaryPassword() {
        return (boolean) executeJavaScriptCommand(
                this.constantsManager.getCommandIsOrdinaryPassword()
        );
    }
    
    private void insertOrdinaryPassword() {
        insertOrdinaryPassword(this.currentBankAccountLogin.getLoginPassword());
        clickLoginButton();
    }
    
    private void insertMaskedPassword() {
        insertMaskedPassword(this.currentBankAccountLogin.getLoginPassword());
        clickLoginButton();
    }
    
    private void captureAccountNumber(String sectionName, int accountIndex) {
        String command = String.format("document.getElementById('%s').getElementsByTagName('tbody')[0].getElementsByClassName('name')[%s].getElementsByTagName('em')[0].innerText;", sectionName, accountIndex);
        this.currentAccountNumber = (String) executeJavaScriptCommand(command);
    }
    
    private boolean isCreditCard() {
        return this.currentAccountIndex >= this.accountsCount;
    }
    
    private void enterAccountInformationPage() {
        int allAccountsCount = setAccountsCount();
        
        if (this.currentAccountIndex >= allAccountsCount) {
            logout();
            return;
        } 
        
        String command = "";
        String sectionName = "";
        int accountIndex = 0;
        
        if (false == isCreditCard()) {
            command = this.constantsManager.getCommandClickAccountDetails();
            sectionName = ACCOUNTS_SECTION_NAME;
            accountIndex = this.currentAccountIndex;
            
        } else {
            sectionName = CREDIT_CARDS_SECTION_NAME;
            command = this.constantsManager.getCommandClickCreditCardAccountDetails();
            accountIndex = this.currentAccountIndex - this.accountsCount;
        }
        
        command = String.format(command, sectionName, accountIndex);
        captureAccountNumber(sectionName, accountIndex);
        
        enterAccountInformation(command);
    }
    
    private void performParsingInformation() {
        if (false == isCreditCard()) {
            parseBankAccount();
        } else {
            parseCreditCardAccount();
        }
        ++this.currentAccountIndex;

        this.stashedBankAccount.setAccountNumber(this.currentAccountNumber);

        notifyObservers();
        enterHomePage();
    }
}
