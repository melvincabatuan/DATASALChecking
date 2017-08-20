package ph.edu.dlsu.datasal.lee.facepamphlet;
/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import ph.edu.dlsu.datasal.lee.breakout.*;
import ph.edu.dlsu.datasal.lee.calculator.*;
import ph.edu.dlsu.datasal.lee.hangman.*;
import ph.edu.dlsu.datasal.lee.mygraph.Graph;
import ph.edu.dlsu.datasal.lee.namesurfer.*;
import ph.edu.dlsu.datasal.lee.yahtzee.*;

public class FacePamphlet extends Program implements FacePamphletConstants {
        
	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
        int noProfiles=0, comFriends; 
        String friendname="";
        ArrayList Storage = new ArrayList();
        
        FacePamphletProfile profile;
        FacePamphletProfile current;
        FacePamphletDatabase entry = new FacePamphletDatabase();
                
        JButton SBtn = new JButton("Graph");
        JButton addBtn = new JButton("Add");
        JButton deleteBtn = new JButton("Delete");
        JButton lookupBtn = new JButton("Lookup");
        JFrame Frame = new JFrame("FacePamphlet");
        JLabel nameLabel = new JLabel("Name:");
        
        JButton statusBtn = new JButton("Change Status");
        JButton pictureBtn = new JButton("Change Picture");
        JButton addFriendBtn = new JButton("Add Friend");
        JButton delFriendBtn = new JButton("Delete Friend");
        JButton netBtn = new JButton("Display Friend Network");

        
        JTextField nameText = new JTextField(TEXT_FIELD_SIZE);
        JTextField statusText = new JTextField(TEXT_FIELD_SIZE);
        JTextField pictureText = new JTextField(TEXT_FIELD_SIZE);
        JTextField addText = new JTextField(TEXT_FIELD_SIZE);
        JTextField delText = new JTextField(TEXT_FIELD_SIZE);
        
        Graph network;
        
        String message="",line="",imagename="";
        
        public FacePamphletCanvas canvas = new FacePamphletCanvas();
        
   
        public static void main(String[] args) {
                new FacePamphlet().start(args);
            }
        
	public void init() {
            Frame.setSize(APPLICATION_WIDTH,APPLICATION_HEIGHT);
            add(nameLabel, NORTH);
            add(nameText, NORTH);
            add(addBtn,NORTH);
            add(deleteBtn,NORTH);   
            add(lookupBtn,NORTH);
            add(statusText, WEST);
            add(statusBtn,WEST);
            add(new JButton("Breakout"),EAST);
            add(new JButton("Hangman"),EAST);
            add(new JButton("Yahtzee"),EAST);
            add(new JButton("NameSurfer"),EAST);
            add(new JButton("Calculator"),EAST);
            add(new JLabel(EMPTY_LABEL_TEXT),WEST);            
            add(pictureText, WEST);
            add(pictureBtn,WEST);
            add(new JLabel(EMPTY_LABEL_TEXT),WEST);            
            add(addText, WEST);
            add(addFriendBtn,WEST);
            add(new JLabel(EMPTY_LABEL_TEXT),WEST);            
            add(delText, WEST);
            add(delFriendBtn,WEST);
            add(netBtn,WEST);
            nameText.addActionListener(this);
            statusText.addActionListener(this); 
            pictureText.addActionListener(this); 
            addText.addActionListener(this); 
            delText.addActionListener(this); 
            nameText.setActionCommand("Add"); 
            statusText.setActionCommand("Change Status"); 
            pictureText.setActionCommand("Change Picture"); 
            addText.setActionCommand("Add Friend"); 
            delText.setActionCommand("Delete Friend"); 
            add(canvas);
            try {
                recall();
            } catch (IOException ex) {
                Logger.getLogger(FacePamphlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                entry.save();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FacePamphlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
  
        public void recall() throws FileNotFoundException, IOException{
            BufferedReader br =   br = new BufferedReader(new FileReader("C:\\Users\\iwcnrlee1\\Documents\\NetBeansProjects\\FacePamphlet\\src\\Save.txt"));
                while ((line = br.readLine()) != null) {
                    Storage.add(line);
                }
            for(int i=0;i<Storage.size();i++){
                noProfiles++;
                StringTokenizer st= new StringTokenizer((String)Storage.get(i),"|");
                profile= new FacePamphletProfile(st.nextToken());
                profile.setStatus(st.nextToken());
                imagename=st.nextToken(); 
                if(!imagename.equals("null")){              
                    profile.saveImage(imagename);
                    GImage profilePic= new GImage(imagename);
                    profile.setImage(profilePic);
                }
                while(st.hasMoreTokens()){
                    profile.addFriend(st.nextToken());
                }
                entry.addProfile(profile);
            }
        }
        
        public void run(){
            while(true){
                addActionListeners();   
                
            }
        }
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
        
    public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("Add")) {
                if(!nameText.getText().equals("")){
                    if(entry.containsProfile(nameText.getText())){
                        current=entry.getProfile(nameText.getText());
                        Iterator it =current.getFriends();
                        while(it.hasNext()){
                             (entry.getProfile((String) it.next())).removeFriend(nameText.getText());
                        }
                        entry.deleteProfile(nameText.getText());
                    }
                    else noProfiles++;
                    profile= new FacePamphletProfile(nameText.getText());
                    System.out.println("Add: "+nameText.getText());
                    System.out.println(profile.toString());
                    entry.addProfile(profile);
                    current=profile;
                    message= "Add the New Profile " + nameText.getText();
                }
                else message= "Please enter a valid profile name";
                
            }
            
            else if(e.getActionCommand().equals("Delete")) {
                if(!nameText.getText().equals("")&&entry.containsProfile(nameText.getText())){  
                    current=entry.getProfile(nameText.getText());                    
                    Iterator<String> friends =current.getFriends();
                    while(friends.hasNext()){
                        (entry.getProfile(friends.next())).removeFriend(current.getName());
                    }    
                    entry.deleteProfile(nameText.getText());
                    System.out.println("Remove: "+nameText.getText());
                    canvas.removeAll();
                    current=null;
                    message= "Deleted the profile of " + nameText.getText();
                    noProfiles--;
                }
                else message= "Please enter a valid profile name";                
            }
            
            else if(e.getActionCommand().equals("Lookup")) {
                if(!nameText.getText().equals("")&&entry.containsProfile(nameText.getText())){
                    current=entry.getProfile(nameText.getText());
                    if(current!=null) System.out.println(current.toString());
                }
                else{
                    message= "Please enter a valid profile name";
                }                
            }
            else if(e.getActionCommand().equals("Add Friend")) {
                if(!addText.getText().equals("")&&entry.containsProfile(addText.getText())&&!addText.getText().equals(nameText.getText())){    
                    entry.getProfile(addText.getText()).addFriend(current.getName());
                    current.addFriend(addText.getText());
                    System.out.println("Add Friend: "+addText.getText());
                    message= "Add to friendlist " + addText.getText();
                }
                else message= "Please enter a valid name";                                
            }
            else if(e.getActionCommand().equals("Delete Friend")) {
                if(!addText.getText().equals("")&&entry.containsProfile(addText.getText())){    
                    entry.getProfile(delText.getText()).removeFriend(current.getName());
                    current.removeFriend(delText.getText());
                    System.out.println("Remove Friend: "+addText.getText());
                    message= "Remove from friendlist " + delText.getText();
                }
                else message= "Please enter a valid name";                                
            }
            else if(e.getActionCommand().equals("Change Picture")) {
                current.saveImage("C:\\Users\\iwcnrlee1\\Documents\\NetBeansProjects\\FacePamphlet\\src\\images\\"+pictureText.getText());
                if(!pictureText.getText().equals("")){  
                        BufferedImage img = null;
                    try {
                        img = ImageIO.read(new File("C:\\Users\\iwcnrlee1\\Documents\\NetBeansProjects\\FacePamphlet\\src\\images\\"+pictureText.getText()));
                    } catch (IOException ex) {
                        Logger.getLogger(FacePamphlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    GImage profilePic= new GImage(pictureText.getText());
                    current.setImage(profilePic);
                    System.out.println("Change Picture: "+pictureText.getText());
                    message= "Change picture to " + pictureText.getText();
                }
                else message= "Please enter a valid image filename"; 
            }
            
            else if(e.getActionCommand().equals("Change Status")) {
                current.setStatus(statusText.getText());
                System.out.println("Change Status: "+statusText.getText());
                message= "Change status to " + statusText.getText();
            }
            else if(e.getActionCommand().equals("Display Friend Network")) {
                network=new Graph(noProfiles);
                for(int i=0;i<entry.entry.size();i++){
                    Iterator it =entry.entry.get(i).getFriends();
                    while(it.hasNext()){
                        friendname=(String) it.next();
                        comFriends = entry.compareFriends(entry.entry.get(i).getName(), friendname);
                        network.addEdge(i, entry.getInt(friendname), comFriends);
                    }
                }
                
                System.out.println(network.toString());
                canvas.Graph(network, entry);
                message= "Displaying Friend Network";
            }
            else if(current!=null&&!e.getActionCommand().equals("Display Friend Network")) canvas.displayProfile(current);
            //Other Applications
            if(e.getActionCommand().equals("Breakout")){
                JFrame frame = new JFrame("Breakout");
                frame.setVisible(true);
                Breakout breakout= new Breakout();
                frame.setSize(breakout.WIDTH+20, breakout.HEIGHT+20);
                frame.getContentPane().add(breakout);
                breakout.start();
            }
            
            if(e.getActionCommand().equals("Hangman")){
                JFrame frame = new JFrame("Hangman");
                frame.setVisible(true);
                Hangman hangman= new Hangman();
                frame.setSize(800,500+32);
                frame.getContentPane().add(hangman);
                hangman.start();
                hangman.init();
            }
            
            if(e.getActionCommand().equals("Yahtzee")){
                JFrame frame = new JFrame("Yahtzee");
                frame.setVisible(true);
                Yahtzee yahtzee= new Yahtzee();
                frame.setSize(800,500+32);
                frame.getContentPane().add(yahtzee);
                yahtzee.start();
            }
            if(e.getActionCommand().equals("Calculator")){
                JFrame frame = new JFrame("Calculator");
                frame.setVisible(true);
                Calculator2 calculator= new Calculator2();
                frame.setSize(calculator.APPLICATION_WIDTH, calculator.APPLICATION_HEIGHT+22);
                frame.getContentPane().add(calculator);
                calculator.start();
                   
            }
            if(e.getActionCommand().equals("NameSurfer")){
                JFrame frame = new JFrame("NameSurfer");
                frame.setVisible(true);
                NameSurfer namesurfer= new NameSurfer();
                frame.setSize(namesurfer.APPLICATION_WIDTH, namesurfer.APPLICATION_HEIGHT+22);
                frame.getContentPane().add(namesurfer);
                namesurfer.init();
                namesurfer.start();
            }
            
            if(!e.getActionCommand().equals("Display Friend Network")&&!e.getActionCommand().equals("Delete")&&!nameText.getText().equals(""))canvas.displayProfile(current);
            canvas.showMessage(""+message);   
            try {
                entry.save();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FacePamphlet.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
    
        
}
