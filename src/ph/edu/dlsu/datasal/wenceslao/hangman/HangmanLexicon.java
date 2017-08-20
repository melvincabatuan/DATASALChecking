/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

package ph.edu.dlsu.datasal.wenceslao.hangman;

import acm.util.*;
import java.io.*;
import java.util.*;

public class HangmanLexicon {

    private ArrayList<String> wordList=new ArrayList<String>();

    public HangmanLexicon(){
        try{
            BufferedReader rd = new BufferedReader(new FileReader("ShorterLexicon.txt"));
            while(true){
                String line = rd.readLine();
                if(line == null)  
                    break;
                wordList.add(line);
            }
            rd.close();
        }catch(IOException ex){
            throw new ErrorException(ex);
        }
    }

    /** Returns the number of words in the lexicon. */
    public int getWordCount(){
        return wordList.size();
    }

    /** Returns the word at the specified index. */
    public String getWord(int index){
        return wordList.get(index);
    }
    
///** Returns the number of words in the lexicon. */
//    public int getWordCount() {
//        return 10;
//    }
//
///** Returns the word at the specified index. */
//    public String getWord(int index) {
//        switch (index) {
//            case 0: return "BUOY";
//            case 1: return "COMPUTER";
//            case 2: return "CONNOISSEUR";
//            case 3: return "DEHYDRATE";
//            case 4: return "FUZZY";
//            case 5: return "HUBBUB";
//            case 6: return "KEYHOLE";
//            case 7: return "QUAGMIRE";
//            case 8: return "SLITHER";
//            case 9: return "ZIRCON";
//            default: throw new ErrorException("getWord: Illegal index");
//        }
//    };
    
}