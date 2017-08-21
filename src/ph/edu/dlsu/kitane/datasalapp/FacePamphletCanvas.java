/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.kitane.datasalapp;

import acm.graphics.*;
import java.awt.Color;
import java.util.*;
import ph.edu.dlsu.kitane.graphics.Graphics;
//@author Christoph Kitane

public class FacePamphletCanvas extends GCanvas implements FacePamphletConstants {

    private GLabel message = new GLabel("");
    private GLabel name = new GLabel("");
    private GLabel status = new GLabel("");
    private GLabel friendLabel = new GLabel("Friends:");
    private GLabel noImage;
    private GLabel friends;
    private GRect imageBorder;
    double center;

    //Graphics = Artistry.
    private Graphics background;
    /**
     * Constructor This method takes care of any initialization needed for the
     * display
     */
    public FacePamphletCanvas() {
        center = (getWidth() / 2) - message.getWidth();
        add(message, center, BOTTOM_MESSAGE_MARGIN);
        noImage = new GLabel("No Image");
        imageBorder = new GRect(IMAGE_WIDTH, IMAGE_HEIGHT);
        backGround();
    }

    /**
     * This method displays a message string near the bottom of the canvas.
     * Every time this method is called, the previously displayed message (if
     * any) is replaced by the new message text passed in.
     */
    public void showMessage(String msg) {
        center = 0;
        message.setLabel(msg);
        message.setFont(MESSAGE_FONT);
        center = (getWidth() / 2) - (message.getWidth() / 2);
        message.setLocation(center, getHeight() - BOTTOM_MESSAGE_MARGIN);
        add(message);
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
        removeAll();
        double noImageX = 0;
        //setting the image border.
        imageBorder.setLocation(LEFT_MARGIN, (TOP_MARGIN + (IMAGE_MARGIN * 2)));
        add(imageBorder);
        noImage.setFont("Comic Sans-24");
        noImageX = (LEFT_MARGIN) + (imageBorder.getWidth() / 2) - (noImage.getWidth() / 2);
        noImage.setLocation(noImageX, (TOP_MARGIN + IMAGE_MARGIN) + (imageBorder.getHeight() / 2));
        add(noImage);

        //setting the name.
        name.setLabel(profile.getName());
        name.setFont(PROFILE_NAME_FONT);
        name.setColor(Color.BLUE);
        name.setLocation(LEFT_MARGIN, TOP_MARGIN + name.getHeight());
        add(name);

        //setting the image if the profile has any.
        if (profile.getImage() != null) {
            profile.getImage().setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
            profile.getImage().setLocation(LEFT_MARGIN, (TOP_MARGIN + (IMAGE_MARGIN * 2)));
            remove(noImage);
            add(profile.getImage());
        }

        //setting the profile's status
        if (!status.equals("") || !status.equals(null)) {
            status.setLabel(profile.getStatus());
            status.setFont(PROFILE_STATUS_FONT);
            status.setLocation(LEFT_MARGIN, (TOP_MARGIN + IMAGE_MARGIN + (2 * STATUS_MARGIN) + imageBorder.getHeight()));
        } else {
            status.setLabel("No Status");
            status.setFont(PROFILE_STATUS_FONT);
            status.setLocation(LEFT_MARGIN, (TOP_MARGIN + IMAGE_MARGIN + (2 * STATUS_MARGIN) + imageBorder.getHeight()));
        }
        add(status);

        //setting the profile's friends list
        friendLabel.setFont(PROFILE_FRIEND_LABEL_FONT);
        friendLabel.setLocation((LEFT_MARGIN * 4) + imageBorder.getWidth(), (TOP_MARGIN + IMAGE_MARGIN));
        add(friendLabel);

        int spacing = 1;
        Iterator<String> it = profile.getFriends();
        while (it.hasNext()) {
            friends = new GLabel("");
            friends.setFont(PROFILE_FRIEND_FONT);
            friends.setLabel(it.next());
            friends.setLocation((LEFT_MARGIN * 4) + imageBorder.getWidth(), (TOP_MARGIN + IMAGE_MARGIN) + (friends.getHeight() + IMAGE_MARGIN) * spacing);
            add(friends);
            spacing++;
        }

        showMessage("Displaying " + profile.getName());
    }

    /**
     * Removes the recent components whenever the user deletes a profile.
     */
    public void deleteProfile(String name) {
        removeAll();
        showMessage("Profile " + name + " has been deleted.");
    }

    public void deleteProfileError(String name) {
        removeAll();
        showMessage("Profile " + name + " does not exist.");
    }
    public void backGround(){
        background = new Graphics();
        add(background);
        background.run();
        background.setVisible(true);
    }
}
