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
package ph.edu.dlsu.datasal.ocampo.namesurfer;
import java.util.*;
import java.io.*;

public class NameSurferDataBase implements NameSurferConstants {
	
        
        private LinkedList<String> nameData;
	/* Constructor: NameSurferDataBase(filename) */
	/**
	 * Creates a new NameSurferDataBase and initializes it using the
	 * data in the specified file.  The constructor throws an error
	 * exception if the requested file does not exist or if an error
	 * occurs as the file is being read.
	 */
	public NameSurferDataBase(String filename) {
                nameData = new LinkedList<>();
                
                try {
                    FileReader f = new FileReader(filename);
                    
                    try {
                        BufferedReader bf = new BufferedReader(f);
                        String line = bf.readLine();
                        while(line!=null) {
                            nameData.addLast(line);
                            line = bf.readLine();
                        }
                    }
                    catch(IOException ex) {
                        System.out.println("Input error");
                    }
                }
                catch(FileNotFoundException ex) {
                    System.out.println("File not found");
                }
	}
	
	/* Method: findEntry(name) */
	/**
	 * Returns the NameSurferEntry associated with this name, if one
	 * exists.  If the name does not appear in the database, this
	 * method returns null.
	 */
	public NameSurferEntry findEntry(String name) {
                NameSurferEntry result = null;
                
                String current="";
                for(int i=0; i<nameData.size(); i++) {
                    current = (nameData.get(i)).split(" ")[0];
                    if(current.equalsIgnoreCase(name)) result = new NameSurferEntry(nameData.get(i));
                }
		return result;
	}
}

