package ph.edu.dlsu.velasco.facepamphlet;

/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */
import acm.graphics.*;
import static java.awt.Color.*;
import java.util.*;
import ph.edu.dlsu.velasco.artistry.*;

public class FacePamphletCanvas extends GCanvas implements FacePamphletConstants {

    /**
     * Constructor This method takes care of any initialization needed for the
     * display
     */
    public FacePamphletCanvas() {
    }

    /**
     * This method displays a message string near the bottom of the canvas.
     * Every time this method is called, the previously displayed message (if
     * any) is replaced by the new message text passed in.
     */
    public void showMessage(String msg) {
        GLabel message = new GLabel("");
        if (getElementAt((APPLICATION_WIDTH - 250) / 2, 380) != null) {
            remove(getElementAt((APPLICATION_WIDTH - 250) / 2, 380));
        }
        message.setLabel(msg);
        message.setFont(MESSAGE_FONT);
        add(message, ((APPLICATION_WIDTH - 250) / 2) - (message.getWidth() / 2), 380);
    }

    /**
     * This method displays the given profile on the canvas. The canvas is first
     * cleared of all existing items (including messages displayed near the
     * bottom of the screen) and then the given profile is displayed. The
     * profile display includes the name of the user from the profile, the
     * corresponding image (or an indication that an image does not exist), the
     * status of the user, and a list of the user's friends in the social
     * network.
     */
    public void displayProfile(FacePamphletProfile profile) {
        if (profile != null) {
            System.out.println("Not Empty Profile");
            removeAll();

            //Artistry art; 
            //art = new Artistry();
            //add(art);
            GLabel userName = new GLabel(profile.getName());
            userName.setFont(PROFILE_NAME_FONT);
            userName.setColor(BLUE);
            add(userName, LEFT_MARGIN, TOP_MARGIN + 24);

            GRect pictureRect = new GRect(IMAGE_WIDTH, IMAGE_HEIGHT);
            add(pictureRect, LEFT_MARGIN, userName.getY() + IMAGE_MARGIN);

            GLabel statusLabel = new GLabel("No current status");
            statusLabel.setFont(PROFILE_STATUS_FONT);
            if (!"".equals(profile.getStatus())) {
                statusLabel.setLabel(profile.getName() + " is " + profile.getStatus());
            }
            add(statusLabel, LEFT_MARGIN, pictureRect.getY() + IMAGE_HEIGHT + STATUS_MARGIN + 16);

            if (profile.getImage() != null) {

                GImage userImage = profile.getImage();
                userImage.scale(IMAGE_WIDTH / userImage.getWidth(), IMAGE_HEIGHT / userImage.getHeight());
                System.out.println(userImage.getWidth() + ", " + userImage.getHeight());
                add(userImage, pictureRect.getX(), pictureRect.getY());
            } else {
                GLabel noImageLabel = new GLabel("No Image");
                noImageLabel.setFont(PROFILE_IMAGE_FONT);
                add(noImageLabel, pictureRect.getX() + (IMAGE_WIDTH / 4), pictureRect.getY() + (IMAGE_HEIGHT / 2));
            }

            GLabel friendsLabel = new GLabel("Friends:");
            friendsLabel.setFont(PROFILE_FRIEND_LABEL_FONT);
            add(friendsLabel, pictureRect.getX() + IMAGE_WIDTH + 100, pictureRect.getY());
            Iterator<String> it = profile.getFriends();
            if (it.hasNext() == true) {
                System.out.println("adding friends");

                while (it.hasNext()) {
                    int i = 0;
                    GLabel friendsList = new GLabel((String) it.next());
                    if (getElementAt(friendsLabel.getX() + 20, friendsLabel.getY() + 24 + (20 * i)) != null) {
                        remove(getElementAt(friendsLabel.getX() + 20, friendsLabel.getY() + 24 + (20 * i)));
                    }
                    friendsList.setFont(PROFILE_FRIEND_FONT);
                    add(friendsList, friendsLabel.getX(), friendsLabel.getY() + 24 + (20 * i));
                    i++;
                }
            } else {
                GLabel friendsList = new GLabel("No friends");
                friendsList.setFont(PROFILE_FRIEND_FONT);
                add(friendsList, friendsLabel.getX(), friendsLabel.getY() + 24);
            }

        } else {
            System.out.println("Empty Profile");
            removeAll();
            Artistry art;
            art = new Artistry();
        }
    }

}
