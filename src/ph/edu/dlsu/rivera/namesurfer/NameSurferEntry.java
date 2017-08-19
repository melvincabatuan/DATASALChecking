/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */
package ph.edu.dlsu.rivera.namesurfer;
import acm.util.*;
import java.util.*;
import java.util.StringTokenizer;  

public class NameSurferEntry implements NameSurferConstants {

	/* Constructor: NameSurferEntry(line) */
	/**
	 * Creates a new NameSurferEntry from a data line as it appears
	 * in the data file.  Each line begins with the name, which is
	 * followed by integers giving the rank of that name for each
	 * decade.
	 */
        private StringTokenizer tokenString;
        private String name;
        private int[] ranks = new int[12];
        private String entry;
	public NameSurferEntry(String line) {
		// You fill this in //
                
              entry = line;
                 tokenString = new StringTokenizer(line, " ");
                name = tokenString.nextToken();
                for(int i=0;i<12;i++){
                    ranks[i] = Integer.parseInt(tokenString.nextToken());
                }
	}

	/* Method: getName() */
	/**
	 * Returns the name associated with this entry.
	 */
	public String getName() {
		// You need to turn this stub into a real implementation //
                
		return name;
	}

	/* Method: getRank(decade) */
	/**
	 * Returns the rank associated with an entry for a particular
	 * decade.  The decade value is an integer indicating how many
	 * decades have passed since the first year in the database,
	 * which is given by the constant START_DECADE.  If a name does
	 * not appear in a decade, the rank value is 0.
	 */
	public int getRank(int decade) {
		// You need to turn this stub into a real implementation //
		return ranks[decade];
	}

	/* Method: toString() */
	/**
	 * Returns a string that makes it easy to see the value of a
	 * NameSurferEntry.
	 */
	public String toString() {
		// You need to turn this stub into a real implementation //
                String temp = name;
                temp = temp.concat(" [");
                for(int i =0; i<12;i++){
                    temp =temp.concat(ranks[i]+"");
                    if(i!=11){
                       temp =temp.concat(" "); 
                    }else{
                        temp =temp.concat("]"); 
                    }
                }
                
		return temp;
	}
}

