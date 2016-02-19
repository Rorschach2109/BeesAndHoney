/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.browser;

import com.beesandhoney.browser.state.WebBrowserStateFactory;
import com.beesandhoney.browser.state.IWebBrowserState;
import com.beesandhoney.controller.BeesAndHoneyMainController;
import com.beesandhoney.controller.IController;
import com.beesandhoney.model.BankAccountLogin;
import com.beesandhoney.utils.ObserverInterface;
import com.beesandhoney.view.BeesAndHoney;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class WebBrowser extends Region implements IWebBrowser, ObserverInterface {
    
    private final WebView webView;
    private final WebEngine engine;
    
    private IWebBrowserState state;
    private BeesAndHoneyMainController mainController;
    
    public WebBrowser() {
        this(null);
    }
    
    public WebBrowser(IWebBrowserState state) {
        this.webView = new WebView();
        this.engine = this.webView.getEngine();

        this.state = state;
    }
    
    @Override
    public void initialize(IController mainController) {
        this.getChildren().add(this.webView);
        this.mainController = (BeesAndHoneyMainController) mainController;
    }

    @Override
    public void setApplication(BeesAndHoney application) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void update() {
        this.mainController.stashAccountInformation(
                this.state.getStashedAccountInformation()
        );
        this.mainController.nextAccountInformation();
    }
    
    @Override
    public void getAccountInformation(BankAccountLogin accountLogin) {
        if (false == setWebBrowserState(accountLogin.getBank().getBankName())) {
            return;
        }
        
        this.state.setWebEngine(this.engine);
        
        this.state.getAccountInformation(accountLogin);
    }

    private boolean setWebBrowserState(String bankName) {
        this.state = WebBrowserStateFactory.createBrowserState(bankName);
        
        if (null == this.state) {
            return false;
        };
        
        this.state.registerObserver(this);
        return true;
    }
}
