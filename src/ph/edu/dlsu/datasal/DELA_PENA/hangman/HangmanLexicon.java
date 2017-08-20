/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */
package ph.edu.dlsu.datasal.DELA_PENA.hangman;
import acm.util.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.awt.*;

public class HangmanLexicon{
private int count=0;
private String sCurrentLine;
//private MyLinkedList<String> WORDS = new MyLinkedList<String>();

    public void createLexicon(){
        
                String FILENAME = ".\\src\\HangmanLexicon.txt";
                //WORDS.createList();
		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

			
                        int i=1;
			while ((sCurrentLine = br.readLine()) != null) {
				//WORDS.add(i, sCurrentLine);
                                i++;
                                count++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
    
    }

/** Returns the number of words in the lexicon. */
	public int getWordCount(){
            return count;
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		//return WORDS.get(index);
            String FILENAME = "D:\\Paolo_MassStorage\\Documents\\NetBeans Projects\\HangmanDP (06-07-2017)v2\\HangmanDP\\src\\hangmandp\\HangmanLexicon.txt";
                //WORDS.createList();
		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

			
                        int i=1;
			while (i<=index) {
                            sCurrentLine = br.readLine();
				//WORDS.add(i, sCurrentLine);
                                i++;
                                
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
            return sCurrentLine;
	}
        

}
