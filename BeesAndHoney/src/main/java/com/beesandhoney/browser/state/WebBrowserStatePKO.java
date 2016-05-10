/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.browser.state;

import com.beesandhoney.browser.constants.BrowserConstantsManagerPKO;
import com.beesandhoney.model.BankAccountLogin;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;

public final class WebBrowserStatePKO extends AbstractWebBrowserState {

    private enum EState {
        LOGIN_PAGE,
        HOME_PAGE,
        ACCOUNTS_MENU_PAGE,
        PARSE_INFORMATION,
        MAX
    };
    private EState innerState;
    
    private ChangeListener<Worker.State> changeListener;
        
    public WebBrowserStatePKO(String loginPageUrl) {
        super(loginPageUrl, new BrowserConstantsManagerPKO());
        
        this.innerState = EState.LOGIN_PAGE;
    }
    
    @Override
    public void getAccountInformation(BankAccountLogin bankAccountLogin) {
        addAccountInformationListener();
        
        super.getAccountInformation(bankAccountLogin);
    }
    
    @Override
    public void logout() {
        this.webEngine.getLoadWorker().stateProperty().removeListener(this.changeListener);
        this.innerState = EState.LOGIN_PAGE;
        
        super.logout();
    }


    private void addAccountInformationListener() {
        this.changeListener = new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observable, 
                    Worker.State oldValue, Worker.State newValue) {
                if (Worker.State.SUCCEEDED != newValue) {
                    return;
                }
                
                switch (innerState) {
                    case LOGIN_PAGE: {
                        innerState = EState.HOME_PAGE;
                        performLogin();
                        break;
                    }
                    
                    case HOME_PAGE: {
                        innerState = EState.ACCOUNTS_MENU_PAGE;
                        enterAccountsInformationMenu();
                        break;
                    }
                    
                    case ACCOUNTS_MENU_PAGE: {
                        innerState = EState.PARSE_INFORMATION;
                        enterAccountInformation();
                        break;
                    }
                    
                    case PARSE_INFORMATION: {
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
    }
        
    private void enterAccountInformation() {
        int allAcountsCount = setAccountsCount();
        
        if(this.currentAccountIndex < allAcountsCount) {
            enterAccountInformation(getEnterAccountInformationCommand());
            ++this.currentAccountIndex;
        } else {
            logout();
        }
    }
    
    private String getEnterAccountInformationCommand() {
        String commandPattern = this.constantsManager.getCommandClickAccountDetails();
        return String.format(commandPattern, Integer.toString(this.currentAccountIndex));
    }
    
    private void performLogin() {
        insertClientId(this.currentBankAccountLogin.getClientId());
        insertOrdinaryPassword(this.currentBankAccountLogin.getLoginPassword());
        clickLoginButton();
    }
    
    private void performParsingInformation() {
        parseBankAccount();
        notifyObservers();
        enterHomePage();
    }
}
