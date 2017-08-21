

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;
//import com.sybit.airtable.exception.AirtableException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
//import org.apache.http.client.HttpResponseException;
//import ph.edu.dlsu.datasal.facepamphlet.Profile;
//import ph.edu.dlsu.datasal.facepamphlet.ProfileDatabase;

public class FacePamphlet extends Program
        implements FacePamphletConstants {

    private JTextField NameTextField;
    private JTextField statusTextField;
    private JTextField picTextField;
    private JTextField addFriendTextField;
    private JTextField quoteTextField;
    private FacePamphletDatabase profileInfo = new FacePamphletDatabase();
    private FacePamphletCanvas canvas = new FacePamphletCanvas();
    private FacePamphletProfile currentProfile = null;
    private myGraph graphh;

    public void init() {
        add(new JLabel("Name "), NORTH);
        NameTextField = new JTextField(TEXT_FIELD_SIZE);
        add(NameTextField, NORTH);
        add(new JButton("Add"), NORTH);
        add(new JButton("Delete"), NORTH);
        add(new JButton("Lookup"), NORTH);

        statusTextField = new JTextField(TEXT_FIELD_SIZE);
        add(statusTextField, WEST);
        add(new JButton("Change Status"), WEST);
        add(new JLabel(EMPTY_LABEL_TEXT), WEST);

        picTextField = new JTextField(TEXT_FIELD_SIZE);
        add(picTextField, WEST);
        add(new JButton("Change Picture"), WEST);
        add(new JLabel(EMPTY_LABEL_TEXT), WEST);

        addFriendTextField = new JTextField(TEXT_FIELD_SIZE);
        add(addFriendTextField, WEST);
        add(new JButton("Add Friend"), WEST);
        add(new JLabel(EMPTY_LABEL_TEXT), WEST);

        quoteTextField = new JTextField(TEXT_FIELD_SIZE);
        add(quoteTextField, WEST);
        add(new JButton("Change Quote"), WEST);
        add(new JLabel(EMPTY_LABEL_TEXT), WEST);

        addActionListeners();
        statusTextField.addActionListener(this);
        picTextField.addActionListener(this);
        addFriendTextField.addActionListener(this);
        quoteTextField.addActionListener(this);
        add(canvas);
        graphh = new myGraph(100);
    }

    /**
     * This class is responsible for detecting when the buttons are clicked or
     * interactors are used, so you will have to add code to respond to these
     * actions.
     */
    public void actionPerformed(ActionEvent e) {

        String enteredName = NameTextField.getText();
        if (e.getActionCommand().equals("Add") && !NameTextField.getText().equals("")) {
            if (profileInfo.containsProfile(enteredName) == false) {
                FacePamphletProfile profile = new FacePamphletProfile(enteredName);
                profileInfo.addProfile(profile);
                canvas.displayProfile(profile);
                canvas.showMessage("New profile created");
                currentProfile = profile;

                /* if(graphh.getMark(i) == null){
                    graphh.setMark(i, enteredName);
                    System.out.println("Added " + enteredName + " at Node " + i);
                    i++;
                    if (i == 50){
                        i = 0;
                    }
                }
                else{
                    i = 0;
                    do{
                        i++;
                        if (i == 100){
                            canvas.showMessage("The Database is full.");
                            return;
                        }
                    }
                    while(graphh.getMark(i) != null);
                }
                 */
            } else {
                FacePamphletProfile profile = profileInfo.getProfile(enteredName);
                canvas.displayProfile(profile);
                canvas.showMessage("A profile with name " + enteredName + " already exists.");
                currentProfile = profile;
            }
        } else if (e.getActionCommand().equals("Delete") && !NameTextField.getText().equals("")) {
            canvas.removeAll();
            currentProfile = null;
            if (profileInfo.containsProfile(enteredName) == true) {
                profileInfo.deleteProfile(enteredName);
                canvas.showMessage("Profile of " + enteredName + " deleted");
                /*canvas.showMessage("Deleted " + enteredName + " at Node " + graphh.getIndex(enteredName));
            graphh.setMark(graphh.getIndex(enteredName), null);
                 */
            } else {
                canvas.showMessage("A profile with name " + enteredName + " does not exist.");
            }
        } else if (e.getActionCommand().equals("Lookup") && !NameTextField.getText().equals("")) {
            canvas.removeAll();
            if (profileInfo.containsProfile(enteredName) == true) {
                FacePamphletProfile profile = profileInfo.getProfile(enteredName);
                canvas.displayProfile(profile);
                canvas.showMessage("Displaying " + enteredName);
                currentProfile = profile;
                //graphh.setEdge(enteredName,profile);   
            } else {
                canvas.showMessage("A profile with name " + enteredName + " does not exist.");
                currentProfile = null;
            }
        } else if (e.getActionCommand().equals("Change Status") || e.getSource() == statusTextField && !statusTextField.getText().equals("")) {
            String statusMessage = statusTextField.getText();
            if (currentProfile != null) {
                FacePamphletProfile profile = profileInfo.getProfile(currentProfile.getName());
                profile.setStatus(profile.getName() + " is " + statusMessage);
                canvas.displayProfile(profile);
                canvas.showMessage("Status updated to " + statusMessage);
            } else {
                canvas.showMessage("Please select a profile to change status");
            }
        } else if (e.getActionCommand().equals("Change Picture") || e.getSource() == picTextField && !picTextField.getText().equals("")) {
            String filename = picTextField.getText();
            if (currentProfile != null) {
                FacePamphletProfile profile = profileInfo.getProfile(currentProfile.getName());
                GImage image = null;
                try {
                    image = new GImage(filename);
                    profile.setImage(image);
                } catch (ErrorException ex) {
                    image = null;
                }
                canvas.displayProfile(profile);
                if (image == null) {
                    canvas.showMessage("Unable to open the file " + filename);
                } else {
                    canvas.showMessage("Picture updated");
                }
            } else {
                println("Please select a profile to change picture");
            }
        } else if (e.getActionCommand().equals("Add Friend") || e.getSource() == addFriendTextField && !addFriendTextField.getText().equals("")) {
            String friendName = addFriendTextField.getText();
            if (currentProfile != null) {
                FacePamphletProfile profile = profileInfo.getProfile(currentProfile.getName());
                if (profile.getName().equals(friendName)) {
                    canvas.showMessage("You cannot friend yourself");
                } else if (profileInfo.containsProfile(friendName)) {
                    FacePamphletProfile friendProfile = profileInfo.getProfile(friendName);
                    if (profile.addFriend(friendName) == true) {//still not friends
                        profile.addFriend(friendName);
                        friendProfile.addFriend(enteredName);
                        canvas.displayProfile(profile);
                        canvas.showMessage(friendName + " added as a friend.");
                        //graphh.setEdge(profile, );
                    } else {
                        canvas.showMessage(profile.getName() + " already has " + friendName + " as a friend.");
                    }
                } else {
                    canvas.showMessage(friendName + " does not exist.");
                }
            } else {
                canvas.showMessage("Please select a profile to add friend");
            }
        } 
        /*
        private void aAddFriend(String entryFriend){
                    //FOR GRAPH
                    int weight = 0;
                    graphh.setEdge(graphh.getMark(currentProfile.getName()),graphh.getMark(entryFriend), weight);
                    System.out.println("added edge to " + currentProfile.getName() + 
                            "(node " + graphh.getIndex(currentProfile.getName()) + ") and " + entryFriend + "(node " + FPGraph.getIndex(entryFriend) + ");
                }
                else {                      
                    graphh.showMessage(currentProfile.getName() + " already has " + entryFriend + " as friend."); 
                }
            } 
            else{
                graphh.displayProfile(currentProfile);
                graphh.showMessage("Please select an existing profile in the database for a friend.");
                }
        }
        else {
            graphh.displayProfile(currentProfile);
            graphh.showMessage("Please select a profile to add friend.");
        }  
    }
         */ else if (e.getActionCommand().equals("Change Quote") || e.getSource() == quoteTextField && !quoteTextField.getText().equals("")) {
            String quoteMessage = quoteTextField.getText();
            if (currentProfile != null) {
                FacePamphletProfile profile = profileInfo.getProfile(currentProfile.getName());
                profile.setQuote(" " + quoteMessage + " ");
                canvas.displayProfile(profile);
                canvas.showMessage("Quote updated to " + quoteMessage);
            } else {
                canvas.showMessage("Please select a profile to change quote");
            }
        }
    }
}
