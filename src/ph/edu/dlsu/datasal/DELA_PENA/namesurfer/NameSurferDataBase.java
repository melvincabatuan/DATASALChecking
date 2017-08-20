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

package ph.edu.dlsu.datasal.DELA_PENA.namesurfer;
import acm.program.*;
import acm.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class NameSurferDataBase extends Program implements NameSurferConstants {
        private NameSurferEntry[] DataBase;
        private int DataBase_SIZE = 0;
	IODialog dialog = getDialog();
	/* Constructor: NameSurferDataBase(filename) */
	/**
	 * Creates a new NameSurferDataBase and initializes it using the
	 * data in the specified file.  The constructor throws an error
	 * exception if the requested file does not exist or if an error
	 * occurs as the file is being read.
	 */
	public NameSurferDataBase(String filename) {
            String EntryString = "";
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
		while ((EntryString = br.readLine()) != null) {
                    DataBase_SIZE++;
		}

            } catch (IOException e) {
		e.printStackTrace();
            }
            DataBase = new NameSurferEntry[DataBase_SIZE];
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
		for(int i=0; i<DataBase_SIZE; i++){
                    if ((EntryString = br.readLine()) == null || EntryString.equals(""))
                        break;
                    DataBase[i] = new NameSurferEntry(EntryString);
                }

            } catch (IOException e) {
		e.printStackTrace();
            }
            
            dialog.println("Data base made with size: " + DataBase_SIZE+"\nfile: " + filename);
            
	}
	
	/* Method: findEntry(name) */
	/**
	 * Returns the NameSurferEntry associated with this name, if one
	 * exists.  If the name does not appear in the database, this
	 * method returns null.
	 */
	public NameSurferEntry findEntry(String name) {
            NameSurferEntry found = null;
            for(int i=0; i<DataBase_SIZE; i++){
                
                if(name.toUpperCase().equals(DataBase[i].getName().toUpperCase())){
                    found = DataBase[i];
                    break;
                }
            }
	return found;
            
	}
}

