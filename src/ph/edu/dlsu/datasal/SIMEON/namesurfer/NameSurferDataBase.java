package ph.edu.dlsu.datasal.SIMEON.namesurfer;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */
import acm.util.*;
import java.util.*;
import java.io.*;

public class NameSurferDataBase implements NameSurferConstants {
	
	/* Constructor: NameSurferDataBase(filename) */
	/**
	 * Creates a new NameSurferDataBase and initializes it using the
	 * data in the specified file.  The constructor throws an error
	 * exception if the requested file does not exist or if an error
	 * occurs as the file is being read.
	 */
         private HashMap<String, NameSurferEntry> faux = new HashMap<String, NameSurferEntry>();
	public NameSurferDataBase(String filename) {
		// You fill this in //
                try{
                    BufferedReader sheetz = new BufferedReader(new FileReader(filename));
                    while(true){

                        String lies = sheetz.readLine();
                        if(lies==null)
                            break;
                        NameSurferEntry me = new NameSurferEntry(lies);
                        faux.put(me.getName(), me);
                    }
                    sheetz.close();
                }
            catch(IOException ex){
            throw new ErrorException(ex);
        }
	}
	
	/* Method: findEntry(name) */
	/**
	 * Returns the NameSurferEntry associated with this name, if one
	 * exists.  If the name does not appear in the database, this
	 * method returns null.
	 */
	public NameSurferEntry findEntry(String name) {
            char fists = name.charAt(0);
            String rests = name.substring(1);
            if(Character.isLowerCase(fists))
                fists = Character.toUpperCase(fists);
            rests = rests.toLowerCase();
            name = fists + rests;
		// You need to turn this stub into a real implementation //
		return faux.get(name);
	}
}

