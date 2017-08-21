/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.kitane.datasalapp;

/**
 *
 * @author Christoph Kitane
 */
import acm.graphics.*;
import java.util.*;
import ph.edu.dlsu.kitane.linkedlist.MyLinkedList;

public class FacePamphletProfile implements FacePamphletConstants {
    private String storeName;
    private String profileStatus;
    private ArrayList<String> friends;
    private GImage profilePicture = null;
    /** 
    * Constructor
    * This method takes care of any initialization needed for
    * the profile.
    */
    public FacePamphletProfile(String name) {
        storeName = name;
        profileStatus = "No Status";
        friends = new ArrayList<>();
        profilePicture = null;
    }

    /** This method returns the name associated with the profile. */ 
    public String getName() {
        // You fill this in.  Currently always returns the empty string.
        return storeName;
    }

    /** 
    * This method returns the image associated with the profile.  
    * If there is no image associated with the profile, the method
    * returns null. */
    public GImage getImage() {
        if(profilePicture!=null){
            return profilePicture;
        }else{
            return null;
        }
    }

    /** This method sets the image associated with the profile. */ 
    public void setImage(GImage image) {
        profilePicture = image;
    }
	
    /** 
    * This method returns the status associated with the profile.
    * If there is no status associated with the profile, the method
    * returns the empty string ("").
    */ 
    public String getStatus() {
        if(profileStatus!="No Status"){
            return profileStatus;
        }
        return "No Status";
    }
	
    /** This method sets the status associated with the profile. */ 
    public void setStatus(String status) {
        profileStatus = status;
        //System.out.println("You have set your status to be: "+ profileStatus);
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
        if(friends.contains(friend)){
            //System.out.println("You are already friends with: "+ friend);
            return false;
        }else{
            friends.add(friend);
            return true;
        }
        
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
        if(friends.contains(friend)){
            friends.remove(friend);
            //System.out.println("You are friends with: "+ friend+" and you now removed that friend.");
            return true;
        }else{
            return false;
        }
        
    }

    /** 
    * This method returns an iterator over the list of friends 
    * associated with the profile.
    */ 
    public Iterator<String> getFriends() {
        return friends.iterator();
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
        String result = getName()+" ("+getStatus()+") : ";
        Iterator<String> it = friends.iterator();
        while(it.hasNext()){
            result += it.next();
            if(it.hasNext()) result +=", ";
        }
	return result;
    }
	
}