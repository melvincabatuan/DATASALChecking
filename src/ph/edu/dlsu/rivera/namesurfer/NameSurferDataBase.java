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
package ph.edu.dlsu.rivera.namesurfer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;  
public class NameSurferDataBase implements NameSurferConstants {
	
	/* Constructor: NameSurferDataBase(filename) */
	/**
	 * Creates a new NameSurferDataBase and initializes it using the
	 * data in the specified file.  The constructor throws an error
	 * exception if the requested file does not exist or if an error
	 * occurs as the file is being read.
	 */
         String[] names_data = new String[4514];
	public NameSurferDataBase(String filename) throws IOException {
		// You fill this in //
                File f = new File(filename);
        FileReader source = new FileReader(f);
        BufferedReader  br = new BufferedReader(source);
        

        String temp = br.readLine();
        int i=1;
        while (temp!=null){
            names_data[i-1]=temp;
            i++;
            
           temp = br.readLine();
        }   
                System.out.println("Done reading data base from file!");
	}
	
	/* Method: findEntry(name) */
	/**
	 * Returns the NameSurferEntry associated with this name, if one
	 * exists.  If the name does not appear in the database, this
	 * method returns null.
     * @param name
     * @return 
	 */
	public NameSurferEntry findEntry(String name) {
		// You need to turn this stub into a real implementation //
                
                for(int i=0;i<names_data.length;i++){
                    
                    StringTokenizer tokenString = new StringTokenizer(names_data[i], " ");
                    String temp = tokenString.nextToken();
                    if(temp.equals(name)){
                        NameSurferEntry entry = new NameSurferEntry(names_data[i]);
                        
                        return entry;
                    }
//                    System.out.println("reading");
                }
		return null;
	}
}

