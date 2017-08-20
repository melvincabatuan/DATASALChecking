package ph.edu.dlsu.datasal.lee.facepamphlet;

/*
 * File: FacePamphletProfile.java
 * ------------------------------
 * This class keeps track of all the information for one profile
 * in the FacePamphlet social network.  Each profile contains a
 * name, an image (which may not always be set), a status (what 
 * the person is currently doing, which may not always be set),
 * and a list of friends.
 */

import acm.graphics.*;
import java.util.*;

public class FacePamphletProfile implements FacePamphletConstants {

        String storedName,storedfile;
        GImage storedPic;
        String storedStatus;
        ArrayList friendList= new ArrayList();
        int number=0, call=0;
        
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for
	 * the profile.
	 */
        
	public FacePamphletProfile(String name) {
                storedName=name;
	}

	/** This method returns the name associated with the profile. */ 
	public String getName(){
		return storedName;
	}

	/** 
	 * This method returns the image associated with the profile.  
	 * If there is no image associated with the profile, the method
	 * returns null. */ 
	public GImage getImage() {
            return storedPic;
	}

	/** This method sets the image associated with the profile. */ 
	public void setImage(GImage image) {
		storedPic=image;
	}
	
	/** 
	 * This method returns the status associated with the profile.
	 * If there is no status associated with the profile, the method
	 * returns the empty string ("").
	 */ 
	public String getStatus() {
		return storedStatus;
	}
	
	/** This method sets the status associated with the profile. */ 
	public void setStatus(String status) {
		storedStatus=status;
	}

	/** 
	 * This method adds the named friend to this profile's list of 
	 * friends.  It returns true if the friend's name was not already
	 * in the list of friends for this profile (and the name is added 
	 * to the list).  The method returns false if the given friend name
	 * was already in the list of friends for this profile (in which 
	 * case, the given friend name is not added to the list of friends 
	 * a second time.)
	 */
	public boolean addFriend(String friend) {
		friendList.add(friend);
		return true;
	}

	/** 
	 * This method removes the named friend from this profile's list
	 * of friends.  It returns true if the friend's name was in the 
	 * list of friends for this profile (and the name was removed from
	 * the list).  The method returns false if the given friend name 
	 * was not in the list of friends for this profile (in which case,
	 * the given friend name could not be removed.)
	 */
	public boolean removeFriend(String friend) {
		friendList.remove(friend);
		return false;
	}

	/** 
	 * This method returns an iterator over the list of friends 
	 * associated with the profile.
	 */ 
	public Iterator<String> getFriends() {
		Iterator it = friendList.iterator();
		return it;
	}
	
	/** 
	 * This method returns a string representation of the profile.  
	 * This string is of the form: "name (status): list of friends", 
	 * where name and status are set accordingly and the list of 
	 * friends is a comma separated list of the names of all of the 
	 * friends in this profile.
	 * 
	 * For example, in a profile with name "Alice" whose status is 
	 * "coding" and who has friends Don, Chelsea, and Bob, this method 
	 * would return the string: "Alice (coding): Don, Chelsea, Bob"
	 */ 
	public String toString() {
            String text;
                text=(getName()+"("+getStatus()+"):");
                Iterator it =getFriends();
                while(it.hasNext()){
                        text=text+((String)it.next()+" ");
                }
		return text;
	}
        
        public void saveImage(String filename){
            storedfile=filename;
        }
        
        public String getImgFile(){
            return storedfile;
        }
	
}
