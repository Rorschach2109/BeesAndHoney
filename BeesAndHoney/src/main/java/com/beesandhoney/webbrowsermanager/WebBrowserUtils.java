/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.webbrowsermanager;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author azazello
 */
public class WebBrowserUtils {
    
    public static enum BankType {
        IPKO,
        BZ_WBK
    }    
    public static String USER_AGENT_PREFERENCE_STRING;
    public static String WEB_BROWSER_USER_AGENT;
    private static Map<BankType, String> BANK_URL_MAP;

    static
    {
        USER_AGENT_PREFERENCE_STRING = "general.useragent.override";
        WEB_BROWSER_USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) "
                + "AppleWebKit/537.36 (KHTML, like Gecko) "
                + "Chrome/46.0.2490.86 Safari/537.36";
        InitBankUrlMap();
   }

    public static String GetBankLoginPage(BankType bankType) {
        return BANK_URL_MAP.get(bankType);
    }
    
    private WebBrowserUtils() {
    }
    
    private static void InitBankUrlMap() {
        BANK_URL_MAP = new HashMap<>();
        BANK_URL_MAP.put(BankType.IPKO, "https://www.ipko.pl");
        BANK_URL_MAP.put(BankType.BZ_WBK, "https://www.centrum24.pl/centrum24-web/login");
    }
}
