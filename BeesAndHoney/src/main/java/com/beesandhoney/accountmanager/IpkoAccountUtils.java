/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.accountmanager;

public final class IpkoAccountUtils {
    public static final String LOGIN_PAGE;
    
    public static final String FIELD_CLIENT_ID_TAG;
    public static final String FIELD_PASSWORD_TAG;
    public static final String FIELD_CONTENT_TABLE_TAG;
    public static final String FIELD_HISTORY_FROM_DATE_TAG;
    public static final String FIELD_HISTORY_END_DATE_TAG;
    public static final String FIELD_HISTORY_FILTER_BUTTON_TAG;
    
    public static final String FIELD_ACCOUNT_NUMBER_TAG;
    public static final String FIELD_ACCOUNT_STATE_TAG;
    
    public static final String JS_HOME_PAGE_COMMAND;
    public static final String JS_LOGIN_COMMAND;
    public static final String JS_ACCOUNTS_SECTION_COMMAND;
    public static final String JS_ACCOUNT_DETAILS_COMMAND;
    public static final String JS_HISTORY_COMMAND;
    public static final String JS_LOGOUT_COMMAND;
    
    public static final String PATTERN_ACCOUNT_NUMBER;
    public static final String PATTERN_ACCOUNT_INFORMATION;
    
    static
    {
        LOGIN_PAGE = "https://www.ipko.pl";
        
        FIELD_CLIENT_ID_TAG = "client_id_field";
        FIELD_PASSWORD_TAG = "password_field";
        FIELD_CONTENT_TABLE_TAG = "content_table";
        FIELD_HISTORY_FROM_DATE_TAG = "beg_date";
        FIELD_HISTORY_END_DATE_TAG = "end_date";
        FIELD_HISTORY_FILTER_BUTTON_TAG = "btn_filter";
        
        FIELD_ACCOUNT_NUMBER_TAG = "tableSubsection";
        FIELD_ACCOUNT_STATE_TAG = "tableSubsectionSummaryTd";
        
        JS_HOME_PAGE_COMMAND = "document"
                + ".getElementById('mainmenu')"
                + ".childNodes[0]"
                + ".childNodes[1]"
                + ".click()";
        JS_LOGIN_COMMAND = "document"
                + ".getElementsByName('btn_ok')[1]"
                + ".click();";
        JS_ACCOUNTS_SECTION_COMMAND = "document"
                + ".getElementById('leftmenu')"
                + ".childNodes[0]"
                + ".childNodes[2]"
                + ".click();";
        JS_ACCOUNT_DETAILS_COMMAND = "document"
                + ".getElementById('leftmenu')"
                + ".childNodes[0]"
                + ".childNodes[3]"
                + ".childNodes[0]"
                + ".childNodes[2]"
                + ".click();";
        JS_HISTORY_COMMAND = "document"
                + ".getElementById('leftmenu')"
                + ".childNodes[3]"
                + ".childNodes[2]"
                + ".click();";
        JS_LOGOUT_COMMAND = "document"
                + ".getElementById('inteligomenu')"
                + ".children[1]"
                + ".children[1]"
                + ".click();";
        
        PATTERN_ACCOUNT_NUMBER = "Umowa do rachunku (.*)";
        PATTERN_ACCOUNT_INFORMATION = "Wybrany rachunek   (.*) Nazwa: (.*) "
                + "Środki dostępne:.*Limit dzienny:.*Środki dostępne:.*Nazwa "
                + "klienta  (.*) Typ relacji  Właściciel Dane adresowe Nazwa  "
                + "(.*) Adres  (.*) Wyciągi";
    }
    
    private IpkoAccountUtils() {
        
    }
}
