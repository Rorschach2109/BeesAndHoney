package com.beesandhoney.webbrowsermanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public final class SeleniumWebDriverFactory {
    
    private static WebDriver webDriver = null;
    
    public static WebDriver CreateSeleniumWebDriver() {
        if (null != webDriver) {
            return webDriver;
        }
        
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        
        firefoxProfile.setPreference(WebBrowserUtils.USER_AGENT_PREFERENCE_STRING, 
                WebBrowserUtils.WEB_BROWSER_USER_AGENT_STRING);
        webDriver = new FirefoxDriver(firefoxProfile);

        return webDriver;
    }
    
    public static void DestroySeleniumWebDriver() {
        webDriver.close();
        webDriver = null;
    }
}
