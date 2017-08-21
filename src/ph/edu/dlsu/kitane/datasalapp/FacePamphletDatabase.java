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
import acm.graphics.GImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FacePamphletDatabase implements FacePamphletConstants {
    private int size;
    private String inLine;
    private ArrayList<String> names;
    private ArrayList<String> storage;
    private ArrayList<String> images;
    private HashMap<String, FacePamphletProfile> profileDB;
    private File originalFile;
    private File tempFile;
    private Graph friendGraph;
    /** 
    * Constructor
    * This method takes care of any initialization needed for 
    * the database.
    */
    public FacePamphletDatabase() {
        profileDB = new HashMap<String, FacePamphletProfile>();
        storage = new ArrayList<>();
        names = new ArrayList<>();
        images = new ArrayList<>();
        friendGraph = new Graph<String>();
        originalFile = new File("sample-network.txt");
        try {
                BufferedReader inFile = new BufferedReader(new FileReader("sample-network.txt"));
                while((inLine = inFile.readLine())!=null){
                    storage.add(inLine);
                    //System.out.println(storage.get(storage.size()-1));
                }
                inFile.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        size = Integer.parseInt(storage.get(0));
        storage.remove(storage.get(0));
        storeNames();
    }
    
    private void storeNames(){
        int name=0;
        GImage image = null;
        while(name<storage.size()){
                FacePamphletProfile profile = new FacePamphletProfile(storage.get(name));
                addProfile(profile);
                image = new GImage(storage.get(name+1));
                images.add(storage.get(name+1));
                profile.setImage(image);
                profile.setStatus(storage.get(name+2));
                name += 3;
                while(!storage.get(name).isEmpty()){
                    profile.addFriend(storage.get(name));
                    friendGraph.addVertex(storage.get(name));
                    //System.out.println("Adding friend: "+storage.get(name)+" as Vertex. "+friendGraph.vertexCount());
                    friendGraph.addEdge(profile.getName(), storage.get(name));
                    //System.out.println("Edge created with: "+storage.get(name)+". Edgecount: "+friendGraph.edgeCount());
                    name++;
                }
                if(storage.get(name).isEmpty()){
                name++;
                }
                //System.out.println(profile.toString());
        }
        //System.out.println(names.toString());
    }
	
    /** 
    * This method adds the given profile to the database.  If the 
    * name associated with the profile is the same as an existing 
    * name in the database, the existing profile is replaced by 
    * the new profile passed in.
    */
    public void addProfile(FacePamphletProfile profile) {
        if(profileDB.containsKey(profile.getName())){
            System.out.println("Error: Profile already exists for that person.");
        }else{
            names.add(profile.getName());
            profileDB.put(profile.getName(), profile);
            friendGraph.addVertex(profile.getName());
            //System.out.println("Vertex Count(adding "+profile.getName()+" in the Graph): "+friendGraph.vertexCount());
        }
        //System.out.println(names.toString()+" size: "+names.size());
    }
    
    /**
     * This Method stores the filename of the image to be placed in the text file later once everything is saved.
     * @param image file name of the image used.
     */
    public void storeImage(FacePamphletProfile profile, String image){
        if(profile.getImage()!=null){
            int index = names.indexOf(profile.getName());
            System.out.println("old picture: "+images.get(index));
            images.remove(index);
            images.add(index, image);
            System.out.println("new picture: "+images.get(index));
        }else{
            images.add(image);
        }
    }

	
    /** 
    * This method returns the profile associated with the given name 
    * in the database.  If there is no profile in the database with 
    * the given name, the method returns null.
    */
    public FacePamphletProfile getProfile(String name) {
        // You fill this in.  Currently always returns null.
        if(profileDB.containsKey(name)){
            return profileDB.get(name);
        }else{
            return null;
        }
    }
	
	
    /** 
    * This method removes the profile associated with the given name
    * from the database.  It also updates the list of friends of all
    * other profiles in the database to make sure that this name is
    * removed from the list of friends of any other profile.
    * If there is no profile in the database with the given name, then
    * the database is unchanged after calling this method.
    */
    public void deleteProfile(String name) {
        if(profileDB.containsKey(name)){
            int index = names.indexOf(name);
            names.remove(name);
            //System.out.println(index);
            images.remove(index);
            profileDB.remove(name);
            //System.out.println("Vertices before: "+friendGraph.vertexCount());
            //System.out.println("Edges before: "+friendGraph.edgeCount());
            friendGraph.removeVertex(name);
            //System.out.println("Vertices after: "+friendGraph.vertexCount());
            //System.out.println("Edges after: "+friendGraph.edgeCount());
            Iterator<String> it = profileDB.keySet().iterator();
            //System.out.println(profileDB.keySet());
            while(it.hasNext()){
                profileDB.get(it.next()).removeFriend(name);
            }
        }
    }

	
    /** 
    * This method returns true if there is a profile in the database 
    * that has the given name.  It returns false otherwise.
    */
    public boolean containsProfile(String name) {
        if(profileDB.containsKey(name)){
            return true;
        }
        return false;
    }
    
    /**
     * This method stores all changes and additions that occurred in the application in a new next file.
     */
    public void storeUponExit(){
        tempFile = new File("temporaryFile.txt");
        try {
            BufferedWriter newFile = new BufferedWriter(new FileWriter(tempFile));
            int times=names.size(), input=0;
            newFile.write(""+names.size());
            newFile.newLine();
            while(times>0){
                FacePamphletProfile profile;
                newFile.write(names.get(input));
                newFile.newLine();
                profile = getProfile(names.get(input));
                if(profile.getImage()!=null){
                    newFile.write(images.get(input));
                    newFile.newLine();
                }else{
                    newFile.newLine();
                }
                if(profile.getStatus()!="No Status"){
                    newFile.write(profile.getStatus());
                    newFile.newLine();
                }else{
                    newFile.newLine();
                }
                Iterator<String> it = profile.getFriends();
                while(it.hasNext()){
                    newFile.write(it.next());
                    newFile.newLine();
                }
                newFile.newLine();
                times--;
                input++;
            }
            newFile.close();
            if (!originalFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }

            // Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(originalFile)){
                System.out.println("Could not rename file");
            }
        } catch (IOException ex) {
            Logger.getLogger(FacePamphletDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
