/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */
package ph.edu.dlsu.kitane.namesurfer;
import acm.util.*;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

	/* Constructor: NameSurferEntry(line) */
	/**
	 * Creates a new NameSurferEntry from a data line as it appears
	 * in the data file.  Each line begins with the name, which is
	 * followed by integers giving the rank of that name for each
	 * decade.
	 */
    private String name = "";
    private String year = "";
    private int[] decArray = new int[NDECADES]; 
	public NameSurferEntry(String line) {
            name = line.substring(0, line.indexOf(" "));
            year = line.substring(line.indexOf(" ")).trim();
            separate(year);
	}
        
        private void separate(String year){
            for(int i=0;i<NDECADES-1;i++){
                decArray[i] = Integer.parseInt(year.substring(0,year.indexOf(" ")));
                year = year.substring(year.indexOf(" ")+1);
                //System.out.println(year);
            }
            decArray[NDECADES-1] = Integer.parseInt(year);
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
		return decArray[decade];
	}

	/* Method: toString() */
	/**
	 * Returns a string that makes it easy to see the value of a
	 * NameSurferEntry.
	 */
	public String toString() {
            String result = "";
            result += "\""+ name + " [";
            for(int i=0;i<NDECADES-1;i++){
                result += decArray[i] + " ";
            }
            result += decArray[NDECADES-1] + "]\"";
            System.out.println(result);
            return result;
	}
}

