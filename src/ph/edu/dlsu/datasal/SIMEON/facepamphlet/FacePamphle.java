/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.SIMEON.facepamphlet;

/**
 *
 * @author jiggy
 */
/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import ph.edu.dlsu.datasal.SIMEON.calculator.Calc;
import ph.edu.dlsu.datasal.SIMEON.hangman.Hangman;
import ph.edu.dlsu.datasal.SIMEON.breakout.Breakout;
import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphle extends Program implements FacePamphletConstants {

    private JLabel name;
    private JTextField nameText;
    private JTextField statusText;
    private JTextField pictureText;
    private JTextField friendText;
    private JButton add;
    private JButton delete;
    private JButton lookup;
    private JButton status;
    private JButton picture;
    private JButton friend;
    private JButton breakoutbutton;
    private JButton calculator;
    private JButton yahtzee;
    private JButton namesurfer;
    private JButton hangman;
    private FacePamphletDatabase data;
    private FacePamphletProfile profile;
    private FacePamphletProfile current;
    private FacePamphletCanvas canvas;
    private Breakout breakout = new Breakout();
    private Calc cal = new Calc();
    private Hangman hang = new Hangman();
    private Thread thread;
    /**
     * This method has the responsibility for initializing the interactors in
     * the application, and taking care of any other initialization that needs
     * to be performed.
     */
    public void init() {

        data = new FacePamphletDatabase();
        canvas = new FacePamphletCanvas();
        name = new JLabel("Name: ");
        add(name, NORTH);
        nameText = new JTextField(TEXT_FIELD_SIZE);
        add(nameText, NORTH);
        nameText.addActionListener(this);
        add = new JButton("Add");
        add(add, NORTH);
        delete = new JButton("Delete");
        add(delete, NORTH);
        lookup = new JButton("Lookup");
        add(lookup, NORTH);
        statusText = new JTextField(TEXT_FIELD_SIZE);
        add(statusText, WEST);
        statusText.addActionListener(this);
        status = new JButton("Change Status");
        add(status, WEST);
        add(new JLabel(EMPTY_LABEL_TEXT), WEST);
        pictureText = new JTextField(TEXT_FIELD_SIZE);
        add(pictureText, WEST);
        pictureText.addActionListener(this);
        picture = new JButton("Change Picture");
        add(picture, WEST);
        add(new JLabel(EMPTY_LABEL_TEXT), WEST);
        friendText = new JTextField(TEXT_FIELD_SIZE);
        add(friendText, WEST);
        friendText.addActionListener(this);
        friend = new JButton("Add Friend");
        add(friend, WEST);
        breakoutbutton = new JButton("Breakout");
        add(breakoutbutton, EAST);
        add(new JLabel(EMPTY_LABEL_TEXT), EAST);
        calculator = new JButton("Calculator");
        add(calculator,EAST);
        add(new JLabel(EMPTY_LABEL_TEXT), EAST);
        yahtzee = new JButton("Yahtzee");
        add(yahtzee,EAST);
        add(new JLabel(EMPTY_LABEL_TEXT), EAST);
        namesurfer = new JButton("Namesurfer");
        add(namesurfer,EAST);
        add(new JLabel(EMPTY_LABEL_TEXT), EAST);
        hangman = new JButton("Hangman");
        add(hangman,EAST);
        addActionListeners();
        add(canvas);
        // You fill this in
    }

    /**
     * This class is responsible for detecting when the buttons are clicked or
     * interactors are used, so you will have to add code to respond to these
     * actions.
     */
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == add && !nameText.getText().isEmpty()) {
            if (data.containsProfile(nameText.getText())) {
                current = data.getProfile(nameText.getText());
                canvas.displayProfile(current);
                canvas.showMessage("A profile with name " + nameText.getText() + " already exists ");
            } else {
                profile = new FacePamphletProfile(nameText.getText().toString());
                data.addProfile(profile);
                canvas.displayProfile(profile);
                canvas.showMessage("New profile created");
                current = data.getProfile(nameText.getText());
            }
        } else if (e.getSource() == delete) {
            canvas.removeAll();
            if (data.containsProfile(nameText.getText())) {
                canvas.showMessage("Profile of " + nameText.getText() + " has been deleted");
                data.deleteProfile(nameText.getText());
                current = null;
            } else {
                canvas.showMessage("A profile with the name of " + nameText.getText() + " does not exist");
                current = null;
            }
        } else if (e.getSource() == lookup) {
            canvas.removeAll();
            if (data.containsProfile(nameText.getText())) {
                current = data.getProfile(nameText.getText());
                canvas.displayProfile(current);
                canvas.showMessage("Looking up " + nameText.getText());
            } else {
                canvas.showMessage("A profile with name of " + nameText.getText() + " does not exist");
                current = null;
            }
        } else if (e.getSource() == breakoutbutton) {
            thread = new Thread() {
            public void run() {
                try {
                    breakout.start();
                    Thread.sleep(1000);
                } catch(InterruptedException v) {
                    println(v);
                }
            }  
        };
            thread.start();
        } else if (e.getSource() == status) {
            if (current != null) {
                current.setStatus(statusText.getText());
                canvas.displayProfile(profile);
                canvas.showMessage("Status updated to " + statusText.getText());
            } else {
                canvas.showMessage("Please select a profile to change the status");
            }
        } else if (e.getSource() == picture) {
            GImage picture = null;
            if (current != null) {
                try {
                    picture = new GImage(pictureText.getText());
                    current.setImage(picture);
                } catch (ErrorException ex) {
                }
                canvas.displayProfile(profile);
                if (current.getImage() == null) {
                    canvas.showMessage("Picture could not be updated to " + pictureText.getText());
                } else {
                    canvas.showMessage("Picture is updated");
                }
            } else {
                canvas.showMessage("Please select a profile to change the image");
            }
        } else if (e.getSource() == friend) {
            if (current != null) {
                if (data.containsProfile(friendText.getText())) {
                    if (current.addFriend(friendText.getText())) {
                        data.getProfile(friendText.getText()).addFriend(current.getName());
                        canvas.displayProfile(current);
                        canvas.showMessage(friendText.getText() + " has been added as your friend");
                    } else {
                        canvas.displayProfile(current);
                        canvas.showMessage(friendText.getText() + " is already your friend");
                    }
                } else {
                    canvas.showMessage(friendText.getText() + " is not in the database");
                    canvas.displayProfile(current);
                }
            } else {
                canvas.showMessage("Please select a profile to add the friend");
            }
        } else if (e.getSource() == calculator ){
            cal.start();
        }
        else if (e.getSource() == yahtzee ){

        }
         else if (e.getSource() == namesurfer ){

        }
        else if (e.getSource() == hangman ){
            thread = new Thread() {
            public void run() {
                try {
                    hang.start();
                    Thread.sleep(1000);
                } catch(InterruptedException v) {
                    println(v);
                }
            }  
        };
            thread.start();
        }
    }
    public static void main(String[] args) {
        new FacePamphle().start(args);
        // TODO code application logic here
    }
}

