package ph.edu.dlsu.datasal.SIMEON.namesurfer;

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

	/* Constructor: NameSurferEntry(line) */
	/**
	 * Creates a new NameSurferEntry from a data line as it appears
	 * in the data file.  Each line begins with the name, which is
	 * followed by integers giving the rank of that name for each
	 * decade.
	 */
        
	public NameSurferEntry(String line) {
            namez = line.substring(0, line.indexOf(" "));
            numz=line.substring(line.indexOf(" ")).trim();
                for(int i = 0; i<NDECADES-1;i++){
                    decada[i] = Integer.parseInt(numz.substring(0, numz.indexOf(" ")));
                    numz = numz.substring(numz.indexOf(" ")+1);
                }
                decada[NDECADES-1] = Integer.parseInt(numz);
		// You fill this in //
	}

	/* Method: getName() */
	/**
	 * Returns the name associated with this entry.
	 */
	public String getName() {
		// You need to turn this stub into a real implementation //
		return namez;
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
                return decada[decade];
	}

	/* Method: toString() */
	/**
	 * Returns a string that makes it easy to see the value of a
	 * NameSurferEntry.
	 */
	public String toString() {
		// You need to turn this stub into a real implementation //
                String lozol = "";
                lozol +=  "\"" + namez+ "[";
                for(int i = 0; i<NDECADES-1;i++){
                    lozol = lozol + decada[i]+ " "; 
                }
                lozol = lozol +decada[NDECADES-1]+"]\"";
		return lozol;
	}
        private String namez = "";
        private String numz = "";
        private int[] decada = new int[NDECADES];
}

