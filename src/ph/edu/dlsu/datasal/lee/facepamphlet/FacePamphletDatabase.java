package ph.edu.dlsu.datasal.lee.facepamphlet;

/*
 * File: FacePamphletDatabase.java
 * -------------------------------
 * This class keeps track of the profiles of all users in the
 * FacePamphlet application.  Note that profile names are case
 * sensitive, so that "ALICE" and "alice" are NOT the same name.
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class FacePamphletDatabase implements FacePamphletConstants {
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the database.
	 */
	public FacePamphletDatabase() {
		
	}
	ArrayList<FacePamphletProfile> entry= new ArrayList();
        int number=0, comFriends=0;
	
	/** 
	 * This method adds the given profile to the database.  If the 
	 * name associated with the profile is the same as an existing 
	 * name in the database, the existing profile is replaced by 
	 * the new profile passed in.
	 */
        
	public void addProfile(FacePamphletProfile profile) {
		entry.add(profile);
	}

	
	/** 
	 * This method returns the profile associated with the given name 
	 * in the database.  If there is no profile in the database with 
	 * the given name, the method returns null.
	 */
	public FacePamphletProfile getProfile(String name){ 
                for(int i=0;i<entry.size();i++){
                    if((entry.get(i).getName()).equals(name)) return entry.get(i);
                }
                return null;
	}
	
	public int getInt(String name){ 
                for(int i=0;i<entry.size();i++){
                    if((entry.get(i).getName()).equals(name)) return i;
                }
                return 0;
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
		for(int i=0;i<entry.size();i++){
                    if((entry.get(i).getName()).equals(name)) entry.remove(i);
                }
	}

	/** 
	 * This method returns true if there is a profile in the database 
	 * that has the given name.  It returns false otherwise.
	 */
	public boolean containsProfile(String name) {
		for(int i=0;i<entry.size();i++){
                    if((entry.get(i).getName()).equals(name)) return true;
                }
		return false;
	}
        
        Iterator it1, it2;
        ArrayList friendlist1 = new ArrayList();
        ArrayList friendlist2 = new ArrayList();
        
        public int compareFriends(String name, String friend){
            comFriends=0;
            it1=getProfile(name).getFriends();
            while(it1.hasNext()){
                friendlist1.add(0,(String) it1.next());
            }
            it2=getProfile(friend).getFriends();
            while(it2.hasNext()){
                friendlist2.add(0,(String)it2.next());
            }
            
            for(int i=0;i<friendlist1.size();i++){
                for(int j=0;j<friendlist2.size();j++){
                    if(friendlist1.get(i).equals(friendlist2.get(j))){
                        comFriends++;
                    }   
                }
            }
            friendlist1.clear();
            friendlist2.clear();
            return comFriends;
            
        }
        int temp;
        public void save() throws FileNotFoundException{
            PrintWriter out = new PrintWriter("C:\\Users\\iwcnrlee1\\Documents\\NetBeansProjects\\FacePamphlet\\src\\Save.txt");
            
            for(int i=0;i<entry.size();i++){
                out.print(entry.get(i).getName()+"|"+entry.get(i).getStatus()+"|"+entry.get(i).getImgFile()+"|");
                for(int j=0;j<entry.get(i).friendList.size();j++){
                    out.print(entry.get(i).friendList.get(j)+"|");
                    temp=j+1;
                }
                out.println();
            }        
            out.close();
        }

}
