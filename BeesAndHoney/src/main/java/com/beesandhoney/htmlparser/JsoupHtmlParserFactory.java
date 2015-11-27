/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.htmlparser;

import com.beesandhoney.accountmanager.AccountUtils;

/**
 *
 * @author azazello
 */
public class JsoupHtmlParserFactory implements HtmlParserAbstractFactory {
    
    @Override
    public HtmlParserInterface CreateJsoupHtmlParser(AccountUtils.BankType bankType) {
        HtmlParserInterface htmlParser = null;
        
        switch(bankType) {
            case PKO_BP:
                htmlParser = new JsoupIpkoHtmlParser();
                break;
            case BZ_WBK:
                break;
            default:
                htmlParser = new DummyHtmlParser();
                break;
        }
        
        return htmlParser;
    }
}
