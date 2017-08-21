/*
 * File: FacePamphletDatabase.java
 * -------------------------------
 * This class keeps track of the profiles of all users in the
 * FacePamphlet application.  Note that profile names are case
 * sensitive, so that "ALICE" and "alice" are NOT the same name.
 */
package ph.edu.dlsu.datasal.reyes.datasalapp;
import java.util.*;

public class FacePamphletDatabase implements FacePamphletConstants {
        public DatasalApp main;
        public FacePamphletProfile profile;
        public FacePamphletProfile[] memearray= new FacePamphletProfile[100];
        public int size = 0;
        
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the database.
	 */
	public FacePamphletDatabase() {
		// You fill this in
	}
	
	
	/** 
	 * This method adds the given profile to the database.  If the 
	 * name associated with the profile is the same as an existing 
	 * name in the database, the existing profile is replaced by 
	 * the new profile passed in.
	 */
	public void addProfile(FacePamphletProfile profile) {
		 size++;
                 memearray[size]=profile;      
	}

	
	/** 
	 * This method returns the profile associated with the given name 
	 * in the database.  If there is no profile in the database with 
	 * the given name, the method returns null.
	 */
	public FacePamphletProfile getProfile(String name) {
            int found = 0;
            for(int l = 1; l!=size;l++){
                    if(memearray[l].getName().equals(name)){
                    found = l;    
                    break;    
                    }
                }
            return memearray[found];
	}
	
	
	/** 
	 * This method removes the profile associated with the given name
	 * from the database.  It also updates the list of friends of all
	 * other profiles in the database to make sure that this name is
	 * removed from the list of friends of any other profile.
	 * 
	 * If there is no profile in the database with the given name, then
	 * the database is unchanged after calling this method.
	 */
	public void deleteProfile(String name) {
		// You fill this in
	}

	
	/** 
	 * This method returns true if there is a profile in the database 
	 * that has the given name.  It returns false otherwise.
	 */
	public Boolean containsProfile(String name) {
		// You fill this in.  Currently always returns false.
                boolean found= false;
                for(int l = 1; l!=size;l++){
                    if(memearray[l].getName().equals(name)){
                    found = true;    
                    break;    
                    }
                }
            return found;
		
	}

}
