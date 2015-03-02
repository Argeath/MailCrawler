/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argeath.mailcrawler;

/**
 *
 * @author amino_000
 */
public class MailCrawler {
    
    public static void main(String[] args) {
        Thread t;
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                Googler googler = new Googler();
                while(true) {
                    try {
                        googler.run();
                        Thread.sleep(1000);
                    } catch (InterruptedException ignored) {
                    }
                }
            }
        });
        t.start();
    }
    
}
