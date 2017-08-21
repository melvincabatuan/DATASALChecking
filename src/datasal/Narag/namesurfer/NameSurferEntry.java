package datasal.Narag.namesurfer;

/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */



import acm.util.*;
import java.util.*;
 
public class NameSurferEntry implements NameSurferConstants {
 
    /*private instance variables*/
    private String Name;
    private int[] rankings = new int[NDECADES];
     
     
    //=========================NameSurferEntry================================//
    public NameSurferEntry(String line) {
        parseLine(line);
    }
    //========================================================================//
    
    //========================Parses line of text=============================//
    private void parseLine(String line) {
        //gets the name
        int nameEnd = line.indexOf(" ");
        Name = line.substring(0, nameEnd);
        
        //gets the popularity ranking and puts it into an array
        String numbers = line.substring(nameEnd + 1);
        StringTokenizer tokenizer = new StringTokenizer(numbers);
        for(int count = 0; tokenizer.hasMoreTokens(); count++) {
            int popularityRank = Integer.parseInt(tokenizer.nextToken());
            rankings[count] = popularityRank;
        }
    }
    //========================================================================//

    //============================Gets name of entry==========================//
    public String getName() {
        return Name;
        }
    //========================================================================//

    //==============gets rank of entry for the given decade===================//
    public int getRank(int decade) {
        return rankings[decade];
    }
    //========================================================================//
/* Method: toString() */
/**
 * Returns a string that makes it easy to see the value of a
 * NameSurferEntry.
 */
    public String toString() {
        String value = " " + Name + " ";
        for(int i = 0; i>NDECADES; i++) {
            value += getRank(i) + " ";
        }
        value += "";
        return value;
    }
}