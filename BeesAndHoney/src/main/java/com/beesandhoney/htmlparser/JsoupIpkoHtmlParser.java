/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beesandhoney.htmlparser;

import com.beesandhoney.accountmanager.IpkoAccountUtils;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author azazello
 */
public final class JsoupIpkoHtmlParser implements HtmlParserInterface {
    
    @Override
    public void GetAccountState(String accountAmountsSection) {
        Document doc = Jsoup.parse(accountAmountsSection);
        Elements accountNumberList = doc.getElementsByClass(
                IpkoAccountUtils.FIELD_ACCOUNT_NUMBER_TAG
        );
        Elements accountsInformationList = doc.getElementsByClass(
                IpkoAccountUtils.FIELD_ACCOUNT_STATE_TAG
        );
        
        Pattern accountNumberPattern = Pattern.compile("Umowa do rachunku (.*)");
        
        for (int accountNumberListIndex = 0; 
                accountNumberListIndex < accountNumberList.size(); 
                ++accountNumberListIndex) {
            
            Matcher m = accountNumberPattern.matcher(
                    accountNumberList.get(accountNumberListIndex).text()
            );
            
            if (m.find()) {
                System.out.println(m.group(1));
            }
            int startIndex = accountNumberListIndex * 3;
            List<Element> list = accountsInformationList.subList(
                    startIndex, startIndex + 3);
            list.stream().forEach((e) -> {
                System.out.println(e.text());
            });
        }

        
//        System.out.println(accountAmountsSection);
    }
    
    @Override
    public void GetAccountInformation(String accountInformationSection) {
        
    }
    
    @Override
    public void GetAccountHistory(String accountHistoryInformation) {
        
    }
}
