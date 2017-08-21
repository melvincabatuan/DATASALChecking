package ph.edu.dlsu.datasal.santos.facepamphlet;
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
	private String profilename;
        private GImage img = null;
        private String  stat= "No current status";
		private ArrayList <String> friends = new ArrayList<String>();
    
	public FacePamphletProfile(String name) {
		profilename=name;
	}

	public String getName() {
		return profilename;
	}

	public GImage getImage() {
		if(img == null) {
			return null;
		}
		else{
			return img;
		}
	}

	/** This method sets the image associated with the profile. */ 
	public void setImage(GImage image) {
		img = image;
	}
	
	public String getStatus() {
        return stat;
	}
	 
	public void setStatus(String status) {
		stat = status;
	}

	public boolean addFriend(String friend) {
		if(friends.contains(friend)) {
			return false;
		}
		else{
			friends.add(friend);
			return true;
		}
	}

	public boolean removeFriend(String friend) {
		if(friends.contains(friend)) {
			friends.remove(friends.indexOf(friend));
			return true;
		}
		else{
			return false;
		}
	}

	public Iterator<String> getFriends() {
		return friends.iterator();
	}
	
	public String toString() {
		String profile = profilename + " (" + stat + "): ";
		Iterator<String>it = friends.iterator();
		while(it.hasNext()) {
			profile += it.next() + ", ";
		}
		return profile;
	}
	
}
