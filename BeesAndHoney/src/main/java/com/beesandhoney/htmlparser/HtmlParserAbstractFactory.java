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
public interface HtmlParserAbstractFactory {
    HtmlParserInterface CreateJsoupHtmlParser(AccountUtils.BankType bankType);
}
