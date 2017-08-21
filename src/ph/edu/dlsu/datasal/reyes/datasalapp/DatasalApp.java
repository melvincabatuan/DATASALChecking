/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.reyes.datasalapp;

/**
 *
 * @author student
 */
import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;
import ph.edu.dlsu.datasal.reyes.datasalapp.calculator.*;
import ph.edu.dlsu.datasal.reyes.datasalapp.breakout.*;


public class DatasalApp extends Program 
					implements FacePamphletConstants {
    public FacePamphletCanvas canvas;
    public FacePamphletDatabase database = new FacePamphletDatabase();
    public FacePamphletProfile profile;
    public JLabel label;
    public JTextField input;
    public JTextField statinput;
    public JTextField picinput;
    public JTextField frdinput;
    public JButton add;
    public JButton delete;
    public JButton lookup;
    public JButton status;
    public JButton picture;
    public JButton friend;
    public JButton brick;
    public JButton hang;
    public JButton calc;
    private Thread thread;
    private Calculator calcu = new Calculator();
    private Breakouttest brout = new Breakouttest();
    
	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
            canvas = new FacePamphletCanvas();
            add(canvas);
            //art.start();
            label = new JLabel("Name");
            add(label,NORTH);
            input = new JTextField(20);
            statinput  = new JTextField(10);
            picinput  = new JTextField(10);
            frdinput  = new JTextField(10);
            input.addActionListener(this);
            statinput.addActionListener(this);
            picinput.addActionListener(this);
            frdinput.addActionListener(this);
            add(input,NORTH);
            
            add = new JButton("Add");
            delete = new JButton("Delete");
            lookup = new JButton("lookup");
            status = new JButton("Change Status");
            picture = new JButton("Change picture");
            friend = new JButton("Add friend");
            brick = new JButton("breakout");
            hang = new JButton("hangman");
            calc = new JButton("calculator");
            add(add,NORTH);
            add(delete,NORTH);
            add(lookup,NORTH);
            add(statinput,WEST);
            add(status,WEST);
            add(picinput,WEST);
            add(picture,WEST);
            add(frdinput,WEST);
            add(friend,WEST);
            add(brick,EAST);
            add(hang,EAST);
            add(calc,EAST);
            addActionListeners();
            
           
    }
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		// You fill this in as well as add any additional methods
            if(e.getSource()==add){
                println("add:"+input.getText());
                profile = new FacePamphletProfile(input.getText());
                database.addProfile(profile);
                canvas.displayname.setLabel("Name: "+profile.getName());
                canvas.displaystatus.setLabel("status: ");
                canvas.displayimage.setImage("noimage.png");
            }
            if(e.getSource()==delete){
                println("delete:"+input.getText());
            }
            if(e.getSource()==lookup){
                println("lookup:"+input.getText());
                for(int l = 1; l!=database.size+1;l++){
                    println(database.memearray[l].profilename);
                }
                if(database.containsProfile(input.getText())){
                canvas.displayname.setLabel("Name: "+database.getProfile(input.getText()).profilename);
                canvas.displaystatus.setLabel("status: "+database.getProfile(input.getText()).getStatus());
                canvas.displayimage.setImage(database.getProfile(input.getText()).profileimage.getImage());
                }
                else println("failed");
            }
            if(e.getSource()==status){
                println("status:"+statinput.getText());
                profile.setStatus(statinput.getText());
                canvas.displaystatus.setLabel("status: "+profile.getStatus());
            }
            if(e.getSource()==picture){
                println("picture:"+statinput.getText());
                GImage newpic = new GImage(picinput.getText());
                profile.setImage(newpic);
                
                canvas.displayimage.setImage(newpic.getImage());
            }
            if(e.getSource()==friend){
                println("Added "+frdinput.getText());
            }
            if(e.getSource()==brick){
              thread = new Thread() {
            public void run() {
                try {
                    brout.start();
                    Thread.sleep(1000);
                } catch(InterruptedException v) {
                    println(v);
                }
            }  
        };
                thread.start();   
            }
            if(e.getSource()==hang){
                
            }
            if(e.getSource()==calc){
                thread = new Thread() {
            public void run() {
                try {
                    calcu.start();
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
        // TODO code application logic here
        new DatasalApp().start(args);
        }

}

