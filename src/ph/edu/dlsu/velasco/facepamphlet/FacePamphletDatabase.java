package ph.edu.dlsu.velasco.facepamphlet;

/*
 * File: FacePamphletDatabase.java
 * -------------------------------
 * This class keeps track of the profiles of all users in the
 * FacePamphlet application.  Note that profile names are case
 * sensitive, so that "ALICE" and "alice" are NOT the same name.
 */
import java.io.*;
import java.util.*;
import java.util.logging.*;
import acm.graphics.*;
import ph.edu.dlsu.velasco.mygraph.*;

public class FacePamphletDatabase implements FacePamphletConstants {

    private HashMap<String, FacePamphletProfile> database;

    /**
     * Constructor This method takes care of any initialization needed for the
     * database.
     */
    public FacePamphletDatabase() {
        database = new HashMap<>();
    }

    /**
     * This method adds the given profile to the database. If the name
     * associated with the profile is the same as an existing name in the
     * database, the existing profile is replaced by the new profile passed in.
     */
    public void addProfile(FacePamphletProfile profile) {
        database.put(profile.getName(), profile);
    }

    /**
     * This method returns the profile associated with the given name in the
     * database. If there is no profile in the database with the given name, the
     * method returns null.
     */
    public FacePamphletProfile getProfile(String name) {
        Iterator<String> i = database.keySet().iterator();
        while (i.hasNext()) {
            String current = i.next();
            if (current.equals(name)) {
                return database.get(current);
            }
        }
        return null;
    }

    public String getName(int vertex) {
        Iterator<String> it = database.keySet().iterator();
        int count = 0;
        while (it.hasNext()) {
            if (vertex == count) {
                return it.next();
            }
            count++;
        }
        return null;
    }

    /**
     * This method removes the profile associated with the given name from the
     * database. It also updates the list of friends of all other profiles in
     * the database to make sure that this name is removed from the list of
     * friends of any other profile.
     *
     * If there is no profile in the database with the given name, then the
     * database is unchanged after calling this method.
     */
    public void deleteProfile(String name) {
        database.remove(name);
        Iterator<String> i = database.keySet().iterator();
        while (i.hasNext()) {
            String current = i.next();
            database.get(current).removeFriend(name);
        }
    }

    /**
     * This method returns true if there is a profile in the database that has
     * the given name. It returns false otherwise.
     */
    public boolean containsProfile(String name) {
        Iterator<String> i = database.keySet().iterator();
        while (i.hasNext()) {
            String current = i.next();
            if (current.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void toWriteFile() {
        try {
            File file = new File("save.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(String.valueOf(database.size()));
            bw.newLine();
            bw.newLine();
            Iterator<String> it = database.keySet().iterator();
            while (it.hasNext()) {
                String name = it.next();
                bw.write(name);
                bw.newLine();
                if (database.get(name).getImage() != null) {
                    bw.write(database.get(name).getImageFile());
                } else {
                    bw.newLine();
                }
                bw.newLine();
                if (database.get(name).getStatus().isEmpty()) {
                    bw.newLine();
                } else {
                    bw.write(database.get(name).getStatus());
                }
                bw.newLine();
                Iterator<String> friends = database.get(name).getFriends();
                while (friends.hasNext()) {
                    bw.write(friends.next());
                    bw.newLine();
                }
                bw.newLine();
            }
            bw.close();
            System.out.println("Successful saving");
        } catch (IOException ex) {
            System.out.println("File saving error");
            Logger.getLogger(FacePamphletDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void toReadFile(myGraph graph) {
        try {
            File file = new File("save.txt");
            if (file.exists()) {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String thisLine;
                thisLine = br.readLine();
                if (thisLine.equals("")) {
                    //end
                } else {
                    System.out.println(thisLine + " profiles");
                    while ((thisLine = br.readLine()) != null) {
                        br.readLine();
                        System.out.println("Inputting another profile");
                        String name = thisLine;
                        System.out.println("Inputting "+name);
                        FacePamphletProfile newProfile = new FacePamphletProfile(name);
                        String imageName = br.readLine();
                        if (!imageName.isEmpty()) {
                            GImage image = new GImage(imageName);
                            newProfile.setImage(image, imageName);
                        }
                        String statusName = br.readLine();
                        if (!statusName.isEmpty()) {
                            newProfile.setStatus(statusName);
                        }
                        String friends;
                        while (!(friends = br.readLine()).equals("")) {
                            newProfile.addFriend(friends);
                            graph.addEdge(name, friends);
                            System.out.println(graph.getEdge(name, friends).v1() + ", " + graph.getEdge(name, friends).v2());
                        }
                        System.out.println("Done inputting " + newProfile.getName());
                        database.put(newProfile.getName(), newProfile);
                    }
                    System.out.println("Done reading the files");
                    br.close();
                }
            } else {
                System.out.println("No file exists.");
            }

        } catch (Exception e) {
            System.out.println("File reading error");
        }
    }

    public Iterator<FacePamphletProfile> toGetAllProfiles() {
        return database.values().iterator();
    }

    public Iterator<String> getAllNames() {
        return database.keySet().iterator();
    }

    public int getSize() {
        return database.size();
    }
}
