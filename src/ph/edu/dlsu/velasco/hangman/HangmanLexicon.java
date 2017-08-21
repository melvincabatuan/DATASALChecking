package ph.edu.dlsu.velasco.hangman;

/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import ph.edu.dlsu.velasco.mylinkedlist.*;

public class HangmanLexicon {

/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return 121806;
	}

        String thisLine;
        MyLinkedList<String> List = new MyLinkedList();
        
        public void retrieveWord(){
            List.createList();
            try {
                File file = new File("HangmanLexicon.txt");
                FileReader fileReader = new FileReader(file);
                    // open input stream test.txt for reading purpose.
                BufferedReader br = new BufferedReader(fileReader);
                
                int i=1;
                while ((thisLine = br.readLine()) != null) {
                    System.out.println(""+i);
                    List.add(i,thisLine);
                    i++;
                    }
                
                } catch(Exception e) {
                    e.printStackTrace();
                }
        }
        
/** Returns the word at the specified index. */
	public String getWord(int index) {
		/*switch (index) {
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
	};*/
            return List.get(index);
        }
        
}
