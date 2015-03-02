/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argeath.mailcrawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author amino_000
 */
public class Googler {
    Dictionary dictionary;
    List<Site> sites;
    
    public Googler() {
        dictionary = new Dictionary();
        sites = new ArrayList<>();
    }
    
    public void getSites() {
        try {
            String word = dictionary.getRandomWord();
            
            List<String> returns = new ArrayList<>();
            
            Document doc = Jsoup.connect("http://szukaj.onet.pl/wyniki.html?qt=" + word).get();
            
            Elements els = doc.select("div.box div.link a[href]");
            for(Element e: els) {
                String url = e.attr("href");
                if(url != null && ! url.isEmpty()) {
                    Site s = new Site(url);
                    s.start();
                    sites.add(s);
                }
            }
        } catch (IOException ignored) {
            // Wywala exception gdy w word są polskie znaki.
        }
    }
    
    public boolean hasFinished() {
        if(sites.isEmpty()) {
            return true;
        }
        for(Site site: sites) {
            if(site.finished == false) {
                return false;
            }
        }
        return true;
    }
    
    public void run() {
        if(hasFinished()) {
            getSites();
        }
    }
}
