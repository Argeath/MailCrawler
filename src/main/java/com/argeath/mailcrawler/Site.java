/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argeath.mailcrawler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author amino_000
 */
public class Site implements Runnable {
    public Thread thread;
    public String url;
    public volatile boolean finished;
    
    private static final String EMAIL_PATTERN = 
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    public Site(String url) {
        this.url = url;
        finished = false;
    }

    @Override
    public void run() {
        try {
            Document doc = Jsoup.connect(url).get();
            Pattern pattern = Pattern.compile(EMAIL_PATTERN);
            Elements els = doc.getElementsMatchingOwnText(pattern);
            
            for(Element el: els) {
                String txt = el.text();
                Matcher matcher = pattern.matcher(txt);
                
                while(matcher.find()) {
                    String email = matcher.group();
                    if( ! FileSaver.isSaved(email)) {
                        FileSaver.saveToFile(email);
                        System.out.println(email);
                    }
                }
            }
            
            finished = true;
        } catch (Exception ex) {
            finished = true;
        }
    }
    
    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }
    
}
