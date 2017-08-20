/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

package ph.edu.dlsu.datasal.lee.hangman;

import ph.edu.dlsu.datasal.lee.mylinkedlist.MyLinkedList;

import acm.util.*;
import java.io.*;


public class HangmanLexicon {
    MyLinkedList<String> LL  = new MyLinkedList<String>();  
    File texts = new File("C:\\Users\\iwcnrlee1\\Documents\\NetBeansProjects\\Hangman\\src\\hangman\\ShorterLexicon.txt");
    String line =null;

    public void List() throws java.io.IOException{
        LL.createList();
        BufferedReader br = new BufferedReader(new FileReader(texts));
        while ((line = br.readLine()) != null) {
                LL.add(1,line);
        }
    }
    
/** Returns the number of words in the lexicon. */
	public int getWordCount() {
            return LL.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
                return LL.get(index);
	}
}

