/*
 * File: FacePamphletDatabase.java
 * -------------------------------
 * This class keeps track of the profiles of all users in the
 * FacePamphlet application.  Note that profile names are case
 * sensitive, so that "ALICE" and "alice" are NOT the same name.
 */
package ph.edu.dlsu.datasal.DELA_PENA.facepamphlet;
import ph.edu.dlsu.datasal.DELA_PENA.ADT.MyGraph.GraphDP;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FacePamphletDatabase implements FacePamphletConstants {
    private final ArrayList<FacePamphletProfile> Database;
    private int database_size = 0;
    private GraphDP<FacePamphletProfile> socialnetwork = new GraphDP<>();
    
    private final String profileStart = "===START===";
    private final String nameHeader = "Name: ";
    private final String imgfileHeader = "Profile Pic: ";
    private final String statusHeader = "Status: ";
    private final String friendsHeader = "Friends: ";
    private final String friendsFooter = "******";
    private final String profileEnd = "===END===";
    
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the database.
	 */
         
        public void readNetworkFile(String fileLoc){
            String CurrentLine = "";
            FacePamphletProfile currentProfRead = null;
            //WORDS.createList();
            try (BufferedReader br = new BufferedReader(new FileReader(fileLoc))) {	
                while((CurrentLine=br.readLine()) != null){
                    if(CurrentLine.equals(profileStart)){
                        currentProfRead = new FacePamphletProfile("temp");
                        break;
                    }
                    else if(CurrentLine.equals(nameHeader)){
                        currentProfRead.setName(CurrentLine.substring(nameHeader.length()));
                    }
                    else if(CurrentLine.equals(imgfileHeader)){
                        currentProfRead.setImage(CurrentLine.substring(imgfileHeader.length()));
                    }
                    else if(CurrentLine.equals(statusHeader)){
                        currentProfRead.setStatus(CurrentLine.substring(statusHeader.length()));
                    }
                    else if(CurrentLine.equals(friendsHeader)){
                        while((CurrentLine=br.readLine()) != friendsFooter){
                            currentProfRead.addFriend(CurrentLine);
                        }
                    }
                    else if(CurrentLine.equals(profileEnd))
                        addProfile(currentProfRead);
                }

            } catch (IOException e) {
		e.printStackTrace();
            }
        }
        
        public void updateNetworkFile(String fileLoc){
            FacePamphletProfile currentProf = null;
            
            try (BufferedWriter br = new BufferedWriter(new FileWriter(fileLoc))) {	
                for(int i=0; i<Database.size(); i++){
                    currentProf = Database.get(i);
                }
                
                
            } catch (IOException e) {
		e.printStackTrace();
            }
        }
        
	public FacePamphletDatabase() {
		Database = new ArrayList<>();
	}
	
	
	/** 
	 * This method adds the given profile to the database.  If the 
	 * name associated with the profile is the same as an existing 
	 * name in the database, the existing profile is replaced by 
	 * the new profile passed in.
	 */
	public void addProfile(FacePamphletProfile profile) {
		Database.add(profile);
                database_size++;
                socialnetwork.addnode(profile);
	}

	
	/** 
	 * This method returns the profile associated with the given name 
	 * in the database.  If there is no profile in the database with 
	 * the given name, the method returns null.
	 */
	public FacePamphletProfile getProfile(String name) {
            FacePamphletProfile found = null;
            if(!Database.isEmpty()){
            for(int i=0; i<Database.size(); i++){
                if(name.toUpperCase().equals(Database.get(i).getName().toUpperCase())){
                    found = Database.get(i);
                    break;
                }
            }
            }
            return found;
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
            if(!Database.isEmpty()){
            for(int i=0; i<Database.size(); i++){
                if(name.toUpperCase().equals(Database.get(i).getName().toUpperCase())){
                    socialnetwork.removenode(Database.get(i));
                    Database.remove(i);
                    database_size--;
                    break;
                }
            }
            }
	}

	
	/** 
	 * This method returns true if there is a profile in the database 
	 * that has the given name.  It returns false otherwise.
	 */
	public boolean containsProfile(String name) {
            boolean found = false;
            if(!Database.isEmpty()){
            for(int i=0; i<Database.size(); i++){
                if(name.toUpperCase().equals(Database.get(i).getName().toUpperCase())){
                    found = true;
                    break;
                }
            }
            }
            return found;
	}
        
        public GraphDP getGraph(){
            return socialnetwork;
        }
        
        public Iterator getAdjecency(String name){
            Iterator it = null;
            if(!Database.isEmpty()){
            for(int i=0; i<Database.size(); i++){
                if(name.toUpperCase().equals(Database.get(i).getName().toUpperCase())){
                    it = socialnetwork.getConnections(Database.get(i)).iterator();
                }
            }
            }
            return it;
        }
        
        public void updateNetwork(){
            if(!Database.isEmpty()){
                for(int i=0; i<Database.size(); i++){
                    if(!socialnetwork.nodeExists(Database.get(i))){
                        socialnetwork.addnode(Database.get(i));
                    }
                    Iterator<String> it = Database.get(i).getFriends();
                    while(it.hasNext()){
                        String currentfriend = it.next();
                        if(!socialnetwork.hasconnection(Database.get(i), getProfile(currentfriend))){
                            socialnetwork.addconnection(Database.get(i), getProfile(currentfriend));
                        }
                    }
                }
            }
        }

}
