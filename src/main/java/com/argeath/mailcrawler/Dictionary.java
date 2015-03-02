/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argeath.mailcrawler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author amino_000
 */
public class Dictionary {
    Scanner s;
    File f;
    List<String> list;
    
    public Dictionary() {
        f = new File("pl.txt");
        list = new ArrayList<>();
    }
    
    public void getWords() {
        try {
            reinitalize();
            
            while(list.size() < 200) {
                int skip = randInt(5, 20);
                for(int i = 0; i<=skip; i++) {
                    if(s.hasNext()) {
                        s.next();
                    } else {
                        reinitalize();
                    }
                }
                if(s.hasNext()) {
                    list.add(s.next());
                } else {
                    reinitalize();
                }
            }
            
            s.close();
        } catch (Exception ex) {
            Logger.getLogger(Dictionary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getRandomWord() {
        if(list.isEmpty()) {
            getWords();
        }
        int index = randInt(0, list.size()-1);
        String word = list.get(index);
        list.remove(index);
        return word;
    }
    
    public void reinitalize() throws FileNotFoundException {
        if(s != null) {
            s.close();
        }
        s = new Scanner(f);
    }
    
    public int randInt(int min, int max) {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
