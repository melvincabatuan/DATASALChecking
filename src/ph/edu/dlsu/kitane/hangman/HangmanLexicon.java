/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */
package ph.edu.dlsu.kitane.hangman;
import acm.util.*;
import java.io.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ph.edu.dlsu.kitane.linkedlist.MyLinkedList;


//@author Christoph Kitane 
public class HangmanLexicon {
    private MyLinkedList<String> lexicon = new MyLinkedList<String>();
    private String inLine;
/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return lexicon.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
            System.out.println(lexicon.get(index));
            return lexicon.get(index);
	}
        public HangmanLexicon(){
            
            try {
                BufferedReader inFile = new BufferedReader(new FileReader("HangmanLexicon.txt"));
                int x=1;
                while((inLine = inFile.readLine())!=null){
                    lexicon.add(x, inLine);
                    System.out.println(x);
                    x++;
                }
                inFile.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(lexicon.size());
        }
}
