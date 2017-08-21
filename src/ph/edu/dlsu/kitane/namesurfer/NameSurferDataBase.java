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
package ph.edu.dlsu.kitane.namesurfer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import ph.edu.dlsu.kitane.linkedlist.MyLinkedList;

public class NameSurferDataBase implements NameSurferConstants {
    private MyLinkedList<String> nameSurf = new MyLinkedList<String>();
    private String inLine;
	/* Constructor: NameSurferDataBase(filename) */
	/**
	 * Creates a new NameSurferDataBase and initializes it using the
	 * data in the specified file.  The constructor throws an error
	 * exception if the requested file does not exist or if an error
	 * occurs as the file is being read.
	 */
	public NameSurferDataBase(String filename) {
            try {
                BufferedReader inFile = new BufferedReader(new FileReader(filename));
                int x=1;
                while((inLine = inFile.readLine())!=null){
                    nameSurf.add(x, inLine);
                    //System.out.println(nameSurf.get(x).substring(0,nameSurf.get(x).indexOf(" ")));
                    x++;
                }
                inFile.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            //System.out.println(nameSurf.size());
	}
	
	/* Method: findEntry(name) */
	/**
	 * Returns the NameSurferEntry associated with this name, if one
	 * exists.  If the name does not appear in the database, this
	 * method returns null.
	 */
	public NameSurferEntry findEntry(String name) {
		for(int i=1;i<=nameSurf.size();i++){
                    //System.out.println("nameSurf.get("+i+").contains("+name+") = "+nameSurf.get(i).substring(0, nameSurf.get(i).indexOf(" ")).contains(name));
                    if(nameSurf.get(i).contains(name)){
                        NameSurferEntry temp;
                        temp = new NameSurferEntry(nameSurf.get(i));
                        return temp;
                    }
                }
                
		return null;
	}
}

