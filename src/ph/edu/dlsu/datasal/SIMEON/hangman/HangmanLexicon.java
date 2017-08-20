/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.SIMEON.hangman;
/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */
/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import ph.edu.dlsu.datasal.SIMEON.mylinkedlist.MyLinkedList;
import acm.util.*;
import java.io.*;
import java.util.*;

public class HangmanLexicon {
        private int i = 1;
        private MyLinkedList<String> words = new MyLinkedList<String>();
/** Returns the number of words in the lexicon. */
        public HangmanLexicon(){
            words.createList();
            
            try{
                
                BufferedReader poo = new BufferedReader(new FileReader("ShorterLexicon.txt"));
                while(true){
                    String line = poo.readLine();
                    if(line==null)
                        break;
                    
                    words.add(i, line);
                    i++;
                }
                poo.close();
            }
            catch(IOException ex){
            throw new ErrorException(ex);
        }
        }
	public int getWordCount() {
		return words.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
            return words.get(index);
	};
}
