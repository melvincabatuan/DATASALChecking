package ph.edu.dlsu.velasco.namesurfer;

import acm.util.ErrorException;
import acmx.export.java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import ph.edu.dlsu.velasco.mylinkedlist.*;
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

public class NameSurferDataBase implements NameSurferConstants {
	
    MyLinkedList<String> SurferList = new MyLinkedList<>();
    MyLinkedList<String> NameList = new MyLinkedList<>();
    int index = 0;
    
	/* Constructor: NameSurferDataBase(filename) */
	/**
	 * Creates a new NameSurferDataBase and initializes it using the
	 * data in the specified file.  The constructor throws an error
	 * exception if the requested file does not exist or if an error
	 * occurs as the file is being read.
	 */
	public NameSurferDataBase(String filename) {
            try{
                BufferedReader br = new BufferedReader (new FileReader(filename));
                
                String line;
                while((line = br.readLine())!=null){
                    int i = 1;
                    NameSurferEntry nameSurf = new NameSurferEntry(line);
                    NameList.add(i, nameSurf.getName());
                    SurferList.add(i, nameSurf.toString());
                    i++;
                }
                br.close();
                    
            }catch(IOException exception){
                throw new ErrorException(exception);
            }
	}
	
	/* Method: findEntry(name) */
	/**
	 * Returns the NameSurferEntry associated with this name, if one
	 * exists.  If the name does not appear in the database, this
	 * method returns null.
	 */
        public boolean NameListCheck(String name){
            for(int i = 1; i <= NameList.size(); i++){
                if (NameList.get(i).equalsIgnoreCase(name)){
                    index = i;
                    return true;
                }
            }
            return false;
        }
        
	public NameSurferEntry findEntry(String name) {
            if(NameListCheck(name)){
                NameSurferEntry currentSurfer = new NameSurferEntry(SurferList.get(index));
                return currentSurfer;
            }
            else return null;
	}
}

