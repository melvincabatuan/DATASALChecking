/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.kitane.datasalapp;

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;
import ph.edu.dlsu.kitane.breakout.*;
import ph.edu.dlsu.kitane.graphics.*;
import ph.edu.dlsu.kitane.hangman.*;
import ph.edu.dlsu.kitane.namesurfer.*;
import ph.edu.dlsu.kitane.stackcalculator.*;
import ph.edu.dlsu.kitane.yahtzee.*;

/**
 *
 * @author Christoph Kitane
 */
public class DatasalApp extends Program implements FacePamphletConstants {

    private JLabel name;
    private JTextField enterName;
    private JButton add;
    private JButton delete;
    private JButton lookup;
    private JButton saveChanges;
    private JTextField statusChange;
    private JButton changeStatus;
    private JTextField pictureChange;
    private JButton changePicture;
    private JTextField friendAdd;
    private JButton addFriend;
    private JLabel activities;
    private JButton breakout;
    private JButton hangman;
    private JButton calculator;
    private JButton yahtzee;
    private JButton nameSurfer;

    //FacePamphlet variables
    private FacePamphletDatabase dataBase;
    private FacePamphletProfile currentProfile;
    private FacePamphletCanvas canvas;

    //Other programs variables.
    private Breakout breakoutGame;
    private Hangman hangmanGame;
    private StackCalculator stackCalculator;
    private Yahtzee yahtzeeGame;
    private NameSurfer nameSurferProgram;
    

    /**
     * This method has the responsibility for initializing the interactors in
     * the application, and taking care of any other initialization that needs
     * to be performed.
     */
    public void init() {

        //Creates the North Layout.
        name = new JLabel("Name:");
        add(name, NORTH);
        enterName = new JTextField(TEXT_FIELD_SIZE);
        enterName.addActionListener(this);
        add(enterName, NORTH);
        add = new JButton("Add");
        add(add, NORTH);
        delete = new JButton("Delete");
        add(delete, NORTH);
        lookup = new JButton("Lookup");
        add(lookup, NORTH);

        //Creates the West Layout.
        statusChange = new JTextField(TEXT_FIELD_SIZE);
        statusChange.addActionListener(this);
        add(statusChange, WEST);
        changeStatus = new JButton("Change Status");
        add(changeStatus, WEST);
        add(new JLabel(EMPTY_LABEL_TEXT), WEST);
        pictureChange = new JTextField(TEXT_FIELD_SIZE);
        pictureChange.addActionListener(this);
        add(pictureChange, WEST);
        changePicture = new JButton("Change Picture");
        add(changePicture, WEST);
        add(new JLabel(EMPTY_LABEL_TEXT), WEST);
        friendAdd = new JTextField(TEXT_FIELD_SIZE);
        friendAdd.addActionListener(this);
        add(friendAdd, WEST);
        addFriend = new JButton("Add Friend");
        add(addFriend, WEST);
        add(new JLabel(EMPTY_LABEL_TEXT), WEST);
        saveChanges = new JButton("Save Changes");
        add(saveChanges, WEST);
        dataBase = new FacePamphletDatabase();
        canvas = new FacePamphletCanvas();
        add(canvas);

        //Creates the East Layout.
        activities = new JLabel("Activities:");
        breakout = new JButton("Breakout");
        hangman = new JButton("Hangman");
        calculator = new JButton("Calculator");
        yahtzee = new JButton("Yahtzee");
        nameSurfer = new JButton("Name Surfer");
        add(activities, EAST);
        add(breakout, EAST);
        add(hangman, EAST);
        add(calculator, EAST);
        add(yahtzee, EAST);
        add(nameSurfer, EAST);

        //initialize all external programs
        breakoutGame = new Breakout();
        stackCalculator = new StackCalculator();
        yahtzeeGame = new Yahtzee();
        nameSurferProgram = new NameSurfer();

        //addListeners
        addActionListeners();
    }

    /**
     * This class is responsible for detecting when the buttons are clicked or
     * interactors are used, so you will have to add code to respond to these
     * actions.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            if (enterName.getText().equals("")) {
                System.out.println("Error: no name placed...");
            } else if (dataBase.containsProfile(enterName.getText())) {
                //System.out.println("Add profile for: "+ enterName.getText()+ " already exists: "+dataBase.getProfile(enterName.getText().toString()));
                currentProfile = dataBase.getProfile(enterName.getText());
                canvas.displayProfile(currentProfile);
                displayProfile();
            } else {
                FacePamphletProfile profile = new FacePamphletProfile(enterName.getText());
                dataBase.addProfile(profile);
                //System.out.println("Add profile for: "+ enterName.getText());
                currentProfile = dataBase.getProfile(enterName.getText());
                canvas.displayProfile(currentProfile);
                displayProfile();
            }
            enterName.setText("");
        } else if (e.getSource() == delete) {
            if (dataBase.containsProfile(enterName.getText())) {
                dataBase.deleteProfile(enterName.getText());
                //System.out.println("Delete: profile of "+ enterName.getText()+ " is deleted.");
                currentProfile = null;
                canvas.deleteProfile(enterName.getText());
                displayProfile();
            } else {
                //System.out.println("Delete: profile with the name "+enterName.getText()+" does not exist.");
                currentProfile = null;
                canvas.deleteProfileError(enterName.getText());
                displayProfile();
            }
            enterName.setText("");
        } else if (e.getSource() == lookup) {
            if (dataBase.containsProfile(enterName.getText())) {
                //System.out.println("Lookup: "+dataBase.getProfile(enterName.getText().toString()));
                currentProfile = dataBase.getProfile(enterName.getText());
                canvas.displayProfile(currentProfile);
                displayProfile();
            } else {
                //System.out.println("Lookup: profile with name "+enterName.getText()+" does not exist.");
                currentProfile = null;
                canvas.showMessage("Profile with name " + enterName.getText() + " does not exist.");
            }
            enterName.setText("");
        } else if (e.getSource() == saveChanges) {
            dataBase.storeUponExit();
        } else if (e.getSource() == statusChange || e.getSource() == changeStatus) {
            if (currentProfile != null) {
                currentProfile.setStatus(statusChange.getText());
                //System.out.println("Status updated to: "+statusChange.getText());
                canvas.displayProfile(currentProfile);
                //canvas.showMessage("Status updated to: "+statusChange.getText());
                displayProfile();
            } else {
                //System.out.println("no currrent profile, Please Select...");
                canvas.displayProfile(currentProfile);
                canvas.showMessage("no currrent profile, Please Select...");
                displayProfile();
            }
            statusChange.setText("");
        } else if (e.getSource() == pictureChange || e.getSource() == changePicture) {
            //System.out.println("you've changed the picture into: "+ pictureChange.getText());
            GImage image = null;
            if (currentProfile != null) {
                try {
                    image = new GImage(pictureChange.getText());
                    dataBase.storeImage(currentProfile, pictureChange.getText());
                    currentProfile.setImage(image);
                } catch (ErrorException ex) {
                    image = null;
                }
                if (currentProfile.getImage() == null) {
                    //System.out.println("Picture could not be updated.");
                    canvas.showMessage("Picture not updated: picture not found.");
                } else {
                    //System.out.println("Picture updated");
                    canvas.showMessage("Picture Changed!");
                }
                canvas.displayProfile(currentProfile);
                displayProfile();
            } else {
                canvas.displayProfile(currentProfile);
                displayProfile();
                //System.out.println("No current profile, please select a profile to change image.");
            }
            pictureChange.setText("");
        } else if (e.getSource() == friendAdd || e.getSource() == addFriend) {
            if (currentProfile != null) {
                if (dataBase.containsProfile(friendAdd.getText())) {
                    if (currentProfile.addFriend(friendAdd.getText())) {
                        //System.out.println(friendAdd.getText()+" added as a friend.");
                        dataBase.getProfile(friendAdd.getText()).addFriend(currentProfile.getName());
                        canvas.displayProfile(currentProfile);
                        canvas.showMessage(friendAdd.getText() + " added as a friend.");
                        displayProfile();
                    } else {
                        //System.out.println(friendAdd.getText()+" is already your friend.");
                        canvas.showMessage(friendAdd.getText() + " is already your friend.");
                    }
                } else {
                    //System.out.println("The friend "+friendAdd.getText()+" is not in the database.");
                    canvas.displayProfile(currentProfile);
                    canvas.showMessage("The friend " + friendAdd.getText() + " is not in the database.");
                }
            } else {
                //System.out.println("Please select a profile to add friend.");
                canvas.displayProfile(currentProfile);
                canvas.showMessage("Please select a profile to add friend.");
                displayProfile();
            }
            friendAdd.setText("");
        } else if (e.getSource() == breakout) {
            System.out.println("breakoutgame clicked.");
            JFrame frame = new JFrame("Breakout");
            frame.setVisible(true);
            frame.setSize(breakoutGame.WIDTH+20, breakoutGame.HEIGHT+20);
            frame.getContentPane().add(breakoutGame);
            breakoutGame.start();
        } else if (e.getSource() == hangman) {
            hangmanGame = new Hangman();
            JFrame frame = new JFrame("Hangman");
            frame.setVisible(true);
            frame.setSize(hangmanGame.WIDTH+20, hangmanGame.HEIGHT+20);
            frame.getContentPane().add(hangmanGame);
            hangmanGame.start();
        } else if (e.getSource() == calculator) {
            System.out.println("Calculator clicked.");
            JFrame frame = new JFrame("Calculator");
            frame.setVisible(true);
            frame.setSize(hangmanGame.WIDTH+20, hangmanGame.HEIGHT+20);
            frame.getContentPane().add(hangmanGame);
            hangmanGame.start();
        } else if (e.getSource() == yahtzee) {
            System.out.println("Yahtzee clicked.");
            yahtzeeGame.start(new String[0]);
        } else if (e.getSource() == nameSurfer) {
            System.out.println("Name Surfer clicked.");
            nameSurferProgram.start();
        }
    }

    private void displayProfile() {
        if (currentProfile != null) {
            System.out.println("--> Current profile: " + currentProfile.toString());
        } else {
            System.out.println("--> No current profile exists.");
        }
    }

    public static void main(String[] args) {
        new DatasalApp().start(args);
    }

}
