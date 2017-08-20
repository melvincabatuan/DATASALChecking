package ph.edu.dlsu.datasal.SIMEON.facepamphlet;

/*
 * File: FacePamphletDatabase.java
 * -------------------------------
 * This class keeps track of the profiles of all users in the
 * FacePamphlet application.  Note that profile names are case
 * sensitive, so that "ALICE" and "alice" are NOT the same name.
 */

import java.util.*;

public class FacePamphletDatabase implements FacePamphletConstants {
        private HashMap <String,FacePamphletProfile> Hash = new HashMap<String,FacePamphletProfile>();
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
            if(Hash.containsKey(profile.getName())){
                Hash.remove(profile.getName());
                Hash.put(profile.getName(), profile);
            }
            else
               Hash.put(profile.getName(), profile);
		// You fill this in
	}

	
	/** 
	 * This method returns the profile associated with the given name 
	 * in the database.  If there is no profile in the database with 
	 * the given name, the method returns null.
	 */
	public FacePamphletProfile getProfile(String name) {
            if(Hash.containsKey(name))
                return Hash.get(name);
		// You fill this in.  Currently always returns null.
            else
		return null;
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
            if(Hash.containsKey(name)){
                Hash.remove(name);
                Iterator<String> iterator = Hash.keySet().iterator();
                while(iterator.hasNext()){
                    Hash.get(iterator.next()).removeFriend(name);
                }
            }

		// You fill this in
	}

	
	/** 
	 * This method returns true if there is a profile in the database 
	 * that has the given name.  It returns false otherwise.
	 */
	public boolean containsProfile(String name) {
                if(Hash.containsKey(name))
                    return true;
                else
                    return false;
		// You fill this in.  Currently always returns false.
		
	}

}
