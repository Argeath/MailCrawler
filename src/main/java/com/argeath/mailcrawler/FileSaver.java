/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argeath.mailcrawler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author amino_000
 */
public class FileSaver {
    
    public static void saveToFile(String str) {
        try {
            String fileName = getFileName(str);
            PrintWriter wrt = new PrintWriter(new FileOutputStream(new File(fileName), true)); 
            wrt.println(str);
            wrt.close();
        } catch (Exception ignored) {
        }
    }
    
    public static boolean isSaved(String str) {
        try {
            String fileName = getFileName(str);
            File file = new File(fileName);

            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(line.equals(str)) {
                    return true;
                }
            }
            return false;
        } catch(Exception ignored) {
        }
        return false;
    }
    
    private static String getFileName(String str) {
        String dir = "mails/";
        if(str.isEmpty()) {
            return dir + "ChujWie.txt";
        }
        return dir + Character.toUpperCase(str.charAt(0)) + ".txt";
    }
    
}
