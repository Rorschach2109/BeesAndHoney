/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.browser.state;

import com.beesandhoney.browser.constants.AbstractBrowserConstantsManager;
import com.beesandhoney.model.BankAccount;
import com.beesandhoney.model.BankAccountLogin;
import com.beesandhoney.model.BankAccountOwner;
import com.beesandhoney.model.ModelFactory;
import com.beesandhoney.utils.constants.BankAccountConstants.BankAccountType;
import com.beesandhoney.utils.ObserverInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.web.WebEngine;

public abstract class AbstractWebBrowserState implements IWebBrowserState {

    protected String loginPageUrl;
    protected WebEngine webEngine;
    
    protected AbstractBrowserConstantsManager constantsManager;
    
    protected BankAccountLogin currentBankAccountLogin;
    protected BankAccount stashedBankAccount;
    
    protected int accountsCount;
    protected int creditCardsAccountsCount;
    protected int currentAccountIndex;
    
    protected String accountSectionString;
    private boolean isLogged;
    
    private final List<ObserverInterface> observers;
    
    public AbstractWebBrowserState(String loginPageUrl,
            AbstractBrowserConstantsManager constantsManager) {
        this.loginPageUrl = loginPageUrl;
        
        this.constantsManager = constantsManager;
        this.constantsManager.initialize();
        
        this.accountsCount = 0;
        this.creditCardsAccountsCount = 0;
        this.currentAccountIndex = 0;
        
        this.isLogged = false;
        
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(ObserverInterface observer) {
        this.observers.add(observer);
    }

    @Override
    public void deleteObserver(ObserverInterface observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (ObserverInterface observer : observers) {
            observer.update();
        }
    }
    
    @Override
    public void setWebEngine(WebEngine webEngine) {
        this.webEngine = webEngine;
    }
    
    @Override
    public void getAccountInformation(BankAccountLogin bankAccountLogin) {
        this.currentBankAccountLogin = bankAccountLogin;
        enterLoginPage();
    }
    
    @Override
    public void logout() {
        this.accountsCount = 0;
        this.currentAccountIndex = 0;
        this.isLogged = false;
        
        executeJavaScriptCommand(this.constantsManager.getCommandClickLogoutButton());
        
        notifyObservers();
    }

    @Override
    public BankAccount getStashedAccountInformation() {
        return this.stashedBankAccount;
    }
    
    @Override
    public boolean isLogged() {
        return this.isLogged;
    }
    
    protected void insertClientId(String clientId) {
        String commandPattern = this.constantsManager.getCommandSetClientId();
        String command = String.format(commandPattern, clientId);
        executeJavaScriptCommand(command);
    }
    
    protected void insertOrdinaryPassword(String password) {
        String commandPattern = this.constantsManager.getCommandSetOrdinaryPassword();
        insertPassword(commandPattern, password);
    }
    
    protected void insertMaskedPassword(String password) {
        String commandPattern = this.constantsManager.getCommandSetMaskedPassword();
        insertPassword(commandPattern, password);
    }
    
    protected void clickLoginButton() {
        executeJavaScriptCommand(this.constantsManager.getCommandClickLoginButton());
        this.isLogged = true;
    }
    
    protected void enterHomePage() {
        executeJavaScriptCommand(this.constantsManager.getCommandEnterHomePage());
    }
    
    protected void enterAccountsInformationMenu() {
        executeJavaScriptCommand(this.constantsManager.getCommandClickAccountDetailsMenu());
    }
    
    protected void enterAccountInformation(String command) {
        executeJavaScriptCommand(command);
    }
    
    protected int setAccountsCount() {
        this.accountsCount = (Integer) executeJavaScriptCommand(
                this.constantsManager.getCommandGetAccountsCount());
        this.creditCardsAccountsCount = (Integer) executeJavaScriptCommand(
                this.constantsManager.getCommandGetCreditCardsAccountsCount());
        
        return this.accountsCount + this.creditCardsAccountsCount;
    }
    
    protected void parseBankAccount() {
        createNoTypeBankAccount();
    
        this.stashedBankAccount.setBankAccountType(BankAccountType.BANK_ACCOUNT);
    }
    
    protected void parseCreditCardAccount() {
        createNoTypeBankAccount();
        
        this.stashedBankAccount.setBankAccountType(BankAccountType.CREDIT_CARD);
        
        String creditCardLimitString = (String) executeJavaScriptCommand(
                this.constantsManager.getCommandCreditCardLimit());
        double creditCardLimit = convertStringToDouble(creditCardLimitString);
        this.stashedBankAccount.setAccountLimit(creditCardLimit);
    }
    
    private void createNoTypeBankAccount() {
        String accountNameString = (String) executeJavaScriptCommand(
                this.constantsManager.getCommandAccountName());
        String accountNumberString = (String) executeJavaScriptCommand(
                this.constantsManager.getCommandAccountNumber());
        String accountBalanceString = (String) executeJavaScriptCommand(
                this.constantsManager.getCommandAccountBalance());
        String availableSourcesString = (String) executeJavaScriptCommand(
                this.constantsManager.getCommandAvailableSources());
        String ownerNameSurnameString = (String) executeJavaScriptCommand(
                this.constantsManager.getCommandOwnerNameSurname());
        String ownerAddressString = (String) executeJavaScriptCommand(
                this.constantsManager.getCommandOwnerAddress());

        double accountBalance = convertStringToDouble(accountBalanceString);
        double availableSources = convertStringToDouble(availableSourcesString);
        String currency = getCurrency(accountBalanceString);
        
        BankAccount bankAccount = ModelFactory.createBankAccountModel(
                accountNumberString, accountNameString, availableSources, 
                accountBalance, currency);
        BankAccountOwner bankAccountOwner = ModelFactory.createBankAccountOwnerModel(
                ownerNameSurnameString, ownerAddressString);
        
        bankAccount.setBankAccountOwner(bankAccountOwner);
        bankAccount.setBankAccountLogin(this.currentBankAccountLogin);
        
        this.stashedBankAccount = bankAccount;
    }
    
    private void enterLoginPage() {
        this.webEngine.load(this.loginPageUrl);
    }
    
    private double convertStringToDouble(String text) {
        Pattern pattern = Pattern.compile("([0-9,\\- ]*).*");
        Matcher matcher = pattern.matcher(text);
        
        double returnValue = 0.0;
        
        if (false == text.isEmpty() && matcher.find()) {
            String numberString = matcher.group(1).replaceAll(",", ".").replaceAll(" ", "");
            returnValue = Double.parseDouble(numberString);
        }
        
        return returnValue;
    }
    
    private String getCurrency(String text) {
        Pattern pattern = Pattern.compile(".* ([a-zA-Z]+)");
        Matcher matcher = pattern.matcher(text);
        
        String currency = "";
        if (matcher.find()) {
            currency = matcher.group(1);
        }
        
        return currency;
    }
    
    private void insertPassword(String pattern, String password) {
        String command = String.format(pattern, password);
        executeJavaScriptCommand(command);        
    }
    
    protected Object executeJavaScriptCommand(String filledCommand) {
        return this.webEngine.executeScript(filledCommand);
    }
}
