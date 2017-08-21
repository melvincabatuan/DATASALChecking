package ph.edu.dlsu.velasco.facepamphlet;

/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */
import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.EventQueue;
import java.awt.event.*;
import javax.swing.*;
import ph.edu.dlsu.velasco.breakout.*;
import ph.edu.dlsu.velasco.calculator.*;
import ph.edu.dlsu.velasco.hangman.*;
import ph.edu.dlsu.velasco.yahtzee.*;
import ph.edu.dlsu.velasco.namesurfer.*;
import ph.edu.dlsu.velasco.mygraph.*;

public class FacePamphlet extends Program implements FacePamphletConstants {

    private FacePamphletCanvas canvas;
    private FacePamphletDatabase database = new FacePamphletDatabase();
    private FacePamphletProfile newProfile = null;

    private myGraph graph = new myGraph(GRAPH_CONSTANT);

    private String nameInput = "";
    private GImage profilePicture;

    private final JLabel labelName = new JLabel("Name");
    private final JButton add = new JButton("Add");
    private final JButton delete = new JButton("Delete");
    private final JButton lookup = new JButton("Lookup");
    private final JTextField enterName = new JTextField(TEXT_FIELD_SIZE);
    private final JTextField enterStatus = new JTextField(TEXT_FIELD_SIZE);
    private final JButton changeStatus = new JButton("Change Status");
    private final JTextField enterPicture = new JTextField(TEXT_FIELD_SIZE);
    private final JButton changePicture = new JButton("Change Picture");
    private final JTextField enterFriend = new JTextField(TEXT_FIELD_SIZE);
    private final JButton addFriend = new JButton("Add Friend");

    private final JLabel space1 = new JLabel(EMPTY_LABEL_TEXT);
    private final JLabel space2 = new JLabel(EMPTY_LABEL_TEXT);
    private final JLabel space3 = new JLabel(EMPTY_LABEL_TEXT);
    private final JLabel space4 = new JLabel(EMPTY_LABEL_TEXT);

    private final JButton saveChanges = new JButton("Save to File");
    private final String[] graphSearch = {"Select Search", "Depth First Search",
         "Breadth First Search"};
    private final JComboBox graphConnect = new JComboBox(graphSearch);
    private final JButton createGraph = new JButton("Display Graph");

    private final JLabel games = new JLabel("Games");
    private final JButton breakoutButton = new JButton("Breakout");
    private final JButton hangmanButton = new JButton("Hangman");
    private final JButton calculatorButton = new JButton("Calculator");
    private final JButton nameSurferButton = new JButton("NameSurfer");
    private final JButton yahtzeeButton = new JButton("Yahtzee");

    /**
     * This method has the responsibility for initializing the interactors in
     * the application, and taking care of any other initialization that needs
     * to be performed.
     */
    public void init() {
        this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
        add(labelName, NORTH);
        add(enterName, NORTH);
        add(add, NORTH);
        add.addActionListener(this);
        add(delete, NORTH);
        delete.addActionListener(this);
        add(lookup, NORTH);
        lookup.addActionListener(this);
        add(enterStatus, WEST);
        enterStatus.addActionListener(this);
        add(changeStatus, WEST);
        add(space1, WEST);
        changeStatus.addActionListener(this);
        add(enterPicture, WEST);
        enterPicture.addActionListener(this);
        add(changePicture, WEST);
        add(space2, WEST);
        changePicture.addActionListener(this);
        add(enterFriend, WEST);
        enterFriend.addActionListener(this);
        add(addFriend, WEST);
        addFriend.addActionListener(this);
        add(space3, WEST);
        add(saveChanges, WEST);
        saveChanges.addActionListener(this);
        add(space4, WEST);
        add(createGraph, WEST);
        createGraph.addActionListener(this);
        add(graphConnect, WEST);
        graphConnect.addActionListener(this);

        canvas = new FacePamphletCanvas();
        add(canvas);

        add(games, EAST);
        add(breakoutButton, EAST);
        breakoutButton.addActionListener(this);
        add(hangmanButton, EAST);
        hangmanButton.addActionListener(this);
        add(calculatorButton, EAST);
        calculatorButton.addActionListener(this);
        add(nameSurferButton, EAST);
        nameSurferButton.addActionListener(this);
        add(yahtzeeButton, EAST);
        yahtzeeButton.addActionListener(this);
        database.toReadFile(graph);
    }

    /**
     * This class is responsible for detecting when the buttons are clicked or
     * interactors are used, so you will have to add code to respond to these
     * actions.
     */
    public void actionPerformed(ActionEvent e) {
        //add command
        if (e.getSource() == add) {
            nameInput = enterName.getText();
            if (nameInput.isEmpty()) {
                canvas.showMessage("Input a name for your profile.");
            } else {
                newProfile = new FacePamphletProfile(nameInput);
                if (database.containsProfile(newProfile.getName()) == false) {
                    database.addProfile(newProfile);
                    canvas.displayProfile(newProfile);
                    canvas.showMessage("You have created a profile");
                } else {
                    canvas.displayProfile(database.getProfile(nameInput));
                    canvas.showMessage("This profile already exists.");
                }
            }

        } //delete command
        else if (e.getSource() == delete) {
            nameInput = enterName.getText();
            System.out.println("You have deleted: " + nameInput);

            System.out.println("");
            //deletes the profile in the database
            if (nameInput.isEmpty()) {
                canvas.showMessage("Enter a name to delete");
            } else {
                database.deleteProfile(nameInput);
                canvas.displayProfile(null);
                canvas.showMessage("You have deleted: " + nameInput);
            }
        } //lookup command
        else if (e.getSource() == lookup) {
            nameInput = enterName.getText();
            if (nameInput.isEmpty()) {
                canvas.showMessage("Enter a name to lookup");
            } else {
                newProfile = database.getProfile(nameInput);
                System.out.println("You have looked up: " + nameInput);
                if (database.containsProfile(nameInput)) {
                    canvas.displayProfile(database.getProfile(nameInput));
                    canvas.showMessage("Displaying " + nameInput + "'s profile");
                } else {
                    canvas.displayProfile(null);
                    canvas.showMessage(nameInput + " is not found.");
                }
            }
        } //update status command
        else if (e.getSource() == enterStatus || e.getSource() == changeStatus) {
            String status = enterStatus.getText();
            if (newProfile == null) {
                canvas.showMessage("Create first a profile to update your status");
            } else {
                if (status.isEmpty()) {
                    canvas.showMessage("Enter a status to update");
                } else {
                    System.out.println("You have updated your status: " + status);
                    database.getProfile(nameInput).setStatus(status);
                    canvas.displayProfile(database.getProfile(nameInput));
                    canvas.showMessage("You have updated your status");
                }
            }
            enterStatus.setText("");
        } //update picture
        else if (e.getSource() == enterPicture || e.getSource() == changePicture) {
            String picture = enterPicture.getText();
            if (newProfile == null) {
                canvas.showMessage("Create a new profile to update your picture");
            } else {
                if (picture.isEmpty()) {
                    canvas.showMessage("Enter a valid file name");
                } else {
                    try {
                        profilePicture = new GImage(picture);
                    } catch (ErrorException ex) {
                        canvas.showMessage("Unable to open image file: " + picture);
                    }
                    if (profilePicture != null) {
                        database.getProfile(nameInput).setImage(profilePicture, picture);
                        canvas.displayProfile(database.getProfile(nameInput));
                        canvas.showMessage("Profile picture updated");
                    }
                    enterPicture.setText("");
                }
            }
        } else if (e.getSource() == enterFriend || e.getSource() == addFriend) {
            if (newProfile == null) {
                canvas.showMessage("Create first a profile to add friends");
            } else {
                if (enterFriend.getText().isEmpty()) {
                    canvas.showMessage("Enter a name to add as a friend");
                } else {
                    boolean test = database.getProfile(nameInput).addFriend(enterFriend.getText());
                    System.out.println(database.getProfile(nameInput).toString());
                    if (test == false) {
                        canvas.showMessage("You are already friends with " + enterFriend.getText());
                    } else {
                        String friend = enterFriend.getText();
                        System.out.println("You have added a friend: " + friend);
                        canvas.displayProfile(database.getProfile(nameInput));
                        canvas.showMessage("You are now friends with " + friend);
                        database.getProfile(friend).addFriend(nameInput);
                        graph.addEdge(nameInput, friend);
                        System.out.println(graph.getEdge(nameInput, friend).v1()
                                + " is now friends with " + graph.getEdge(nameInput, friend).v2());
                    }
                }
            }
            enterFriend.setText("");
        } //show graph connections
        else if (e.getSource() == saveChanges) {
            database.toWriteFile();
            System.out.println("Changes Saved");
        } else if (e.getSource() == graphConnect) {
            if (graphConnect.getSelectedIndex() == 0) {
                System.out.println(graphSearch[0]);
                //do nothing
            } else if (graphConnect.getSelectedIndex() == 1) {
                System.out.println(graphSearch[1]);
                //do Depth First Search
            } else if (graphConnect.getSelectedIndex() == 2) {
                System.out.println(graphSearch[2]);
                //do Breadth First Search
            }
        } else if (e.getSource() == createGraph) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    JFrame frame = new JFrame("Graph Window");
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                    GraphWindow window = new GraphWindow(graph, database);
                    frame.add(window.displayGraph());
                    frame.setLocationByPlatform(true);
                    frame.setVisible(true);
                    frame.setResizable(false);
                    frame.setSize(window.APPLICATION_WIDTH, window.APPLICATION_HEIGHT);
                }
            });

        } else if (e.getSource() == breakoutButton) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    JFrame frame = new JFrame("Breakout");
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                    Breakout breakout = new Breakout();
                    frame.getContentPane().add(breakout);
                    frame.setTitle("Breakout");
                    frame.setLocationByPlatform(true);
                    frame.setVisible(true);
                    frame.setResizable(false);
                    frame.setSize(breakout.APPLICATION_WIDTH, breakout.APPLICATION_HEIGHT);
                    breakout.start();
                }
            });
        } else if (e.getSource() == calculatorButton) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    JFrame frame = new JFrame("Breakout");
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    CalculatorStack calculator = new CalculatorStack();
                    frame.setTitle("Calculator");
                    frame.add(calculator);
                    frame.setLocationByPlatform(true);
                    frame.setVisible(true);
                    frame.setSize(calculator.WIDTH, calculator.HEIGHT);
                    calculator.start(new String[0]);
                }
            });
        } else if (e.getSource() == hangmanButton) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    JFrame frame = new JFrame("Hangman");
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    Hangman hangman = new Hangman();
                    frame.add(hangman);
                    frame.setVisible(true);
                    frame.setSize(hangman.APPLICATION_WIDTH, hangman.APPLICATION_HEIGHT);
                    hangman.start();
                }
            });
        } else if (e.getSource() == yahtzeeButton) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    JFrame frame = new JFrame("Yahtzee");
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    Yahtzee yahtzee = new Yahtzee();
                    frame.add(yahtzee);
                    frame.setVisible(true);
                    frame.setSize(yahtzee.APPLICATION_WIDTH, yahtzee.APPLICATION_HEIGHT);
                    yahtzee.start();
                }
            });
        } else if (e.getSource() == nameSurferButton) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    JFrame frame = new JFrame("NameSurfer");
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    NameSurfer nameSurfer = new NameSurfer();
                    frame.add(nameSurfer);
                    frame.setVisible(true);
                    frame.setSize(nameSurfer.APPLICATION_WIDTH, nameSurfer.APPLICATION_HEIGHT);
                    nameSurfer.start(new String[0]);
                }
            });
        }
    }

    public static void main(String[] args) {
        new FacePamphlet().start(args);
    }
}
