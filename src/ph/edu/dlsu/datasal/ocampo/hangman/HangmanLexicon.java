/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

package ph.edu.dlsu.datasal.ocampo.hangman;
import acm.util.*;
import java.io.*;
import java.util.logging.*;
import ph.edu.dlsu.datasal.ocampo.mylinkedlist.*;
import java.util.*;

public class HangmanLexicon {

        private MyLinkedList<String> wordList = new MyLinkedList<String>();

        public void loadList() {
            wordList.createList();
            // Read file
            try {
                
                FileReader filereadertxt = new FileReader("HangmanLexicon.txt");
                BufferedReader reader = new BufferedReader(filereadertxt);
                
                try {
                    
                    String word = reader.readLine();
                    while(word!=null) {
                        wordList.add(1, word);
                        word = reader.readLine();
                    }
                    
                    reader.close();
                    
                } catch (IOException ex) {
                    Logger.getLogger(HangmanLexicon.class.getName()).log(Level.SEVERE, null, ex);
                    
                }
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(HangmanLexicon.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("File not found");
            }
        }
        
        /** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return wordList.size();
	}
        
/** Returns the word at the specified index. */
        public String getWord(int index) {
            return wordList.get(index);
        }
        
        /*
	public String getWord(int index) {
		switch (index) {
			case 0: return "BUOY";
			case 1: return "COMPUTER";
			case 2: return "CONNOISSEUR";
			case 3: return "DEHYDRATE";
			case 4: return "FUZZY";
			case 5: return "HUBBUB";
			case 6: return "KEYHOLE";
			case 7: return "QUAGMIRE";
			case 8: return "SLITHER";
			case 9: return "ZIRCON";
			default: throw new ErrorException("getWord: Illegal index");
		}
	}

        */
}
