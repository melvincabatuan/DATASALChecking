package datasal.Narag.hangman;

import acm.program.*;
import acm.util.*;
import java.util.*;
import java.io.*;

public class HangmanLexicon {
        
	 public HangmanLexicon() { 
		 readWordFile();
	 }
	
	
	public int getWordCount() {
		return wordList.size();
	}

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
		}*/
		return wordList.get(index);
		
	};

	private void readWordFile() {
		BufferedReader rd = null;
		try {
			rd = new BufferedReader(new FileReader(WORD_FILENAME));
		} catch (IOException ex) {
		}
		
		try {
			while(true) {
				String line = rd.readLine();
				if (line == null) break;
				wordList.add(line);
			}
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
		
	}
	private ArrayList<String> wordList = new ArrayList<>(); 
	
	private static final String WORD_FILENAME = "HangmanLexicon.txt";
	
}