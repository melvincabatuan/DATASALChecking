

/*
 * File: FacePamphletDatabase.java
 * -------------------------------
 * This class keeps track of the profiles of all users in the
 * FacePamphlet application.  Note that profile names are case
 * sensitive, so that "ALICE" and "alice" are NOT the same name.
 */

import java.util.*;

public class FacePamphletDatabase implements FacePamphletConstants {
    private Map<String, FacePamphletProfile> profiles = new HashMap<String, FacePamphletProfile>();

    public FacePamphletDatabase() {
        // You fill this in
    }

    public void addProfile(FacePamphletProfile profile) {
        if (!profiles.containsKey(profile.getName())) {
            profiles.put(profile.getName(), profile);
        } else {
            profiles.remove(profile.getName());
            profiles.put(profile.getName(), profile);
        }
    }

    public FacePamphletProfile getProfile(String name) {
        if (profiles.containsKey(name)) {
            return profiles.get(name);
        } else {
            return null;
        }

    }

    public void deleteProfile(String name) {
        if (profiles.containsKey(name)) {
            FacePamphletProfile profileToRemove = profiles.get(name);
            Iterator<String> it = profileToRemove.getFriends();
            while (it.hasNext()) {
                String friendName = it.next();
                FacePamphletProfile friendsProfile = profiles.get(friendName);
                friendsProfile.removeFriend(name);
            }
            profiles.remove(name);
        }
    }

    public boolean containsProfile(String name) {
        if (profiles.containsKey(name)) {
            return true;
        } else {
            return false;
        }
    }

}
