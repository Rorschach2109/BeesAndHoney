/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.webbrowsermanager;

/**
 *
 * @author azazello
 */
public class WebBrowserUtils {
    
    public static String USER_AGENT_PREFERENCE_STRING;
    public static String WEB_BROWSER_USER_AGENT;

    static
    {
        USER_AGENT_PREFERENCE_STRING = "general.useragent.override";
        WEB_BROWSER_USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) "
                + "AppleWebKit/537.36 (KHTML, like Gecko) "
                + "Chrome/46.0.2490.86 Safari/537.36";
   }
    
    private WebBrowserUtils() {
    }
}
