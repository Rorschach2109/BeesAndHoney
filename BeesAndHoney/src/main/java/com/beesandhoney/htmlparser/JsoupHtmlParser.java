/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.htmlparser;

import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author azazello
 */
public class JsoupHtmlParser implements HtmlParserInterface {
    
    @Override
    public void GetAccountAmounts(String accountAmountsSection) {
        System.out.println(accountAmountsSection);
        Document doc = Jsoup.parse(accountAmountsSection);
//        Elements accountsNumberList = doc.getElementsByClass("tableSubsection");
        Elements accountsNumberList = doc.select("tr.medium td.tableSubsection");
        Elements accountsInformationList = doc.getElementsByClass("tableSubsectionSummaryTd");
        
        System.out.println(accountsNumberList.size());
        System.out.println(accountsInformationList.size());
        
        accountsNumberList.stream().forEach((e) -> {
            System.out.println(e.text());
        });
        accountsInformationList.stream().forEach((e) -> {
            System.out.println(e.text());
        });
//        System.out.println(accountAmountsSection);
    }
    
    @Override
    public void GetAccountInformation(String accountInformationSection) {
        
    }
    
    @Override
    public void GetAccountHistory(String accountHistoryInformation) {
        
    }
}
