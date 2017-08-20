package ph.edu.dlsu.datasal.SIMEON.namesurfer;

/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

/* 
* Milestone 1: Add the interactors to the window and create an implementation for the actionPerformed method
* that allows you to check whether you can detect button clicks and read what’s in the text field. 
*/

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {
            

//public class NameSurfer extends Program implements NameSurferConstants {
        private NameSurferDataBase fox = new NameSurferDataBase("names-data.txt"); 
        private NameSurferGraph giraffe;
        private JLabel NAMEZ;
        private JTextField text;
        private JButton NAMEZBUTTON;
        private JButton clear;
	/* Method: init() */
	/**
	 * This method has the responsibility for reading in the data base
	 * and initializing the interactors at the top of the window.
	 */
   
	public void init() {
	    // You fill this in, along with any helper methods //

            NAMEZ = new JLabel("Name: ");
            text = new JTextField(45);
            text.addActionListener(this);
            NAMEZBUTTON = new JButton("Graph");
            clear = new JButton("Clear"); 
            add(NAMEZ,NORTH);
            add(text, NORTH);
            add(NAMEZBUTTON,NORTH);
            add(clear,NORTH);
            
            giraffe = new NameSurferGraph();
            add(giraffe);
            addActionListeners();
	}

	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are
	 * clicked, so you will have to define a method to respond to
	 * button actions.
	 */
	public void actionPerformed(ActionEvent e) {
            if(e.getSource()==text){
                NameSurferEntry entree = fox.findEntry(text.getText());
                giraffe.addEntry(entree);
            }
            else if(e.getSource()==NAMEZBUTTON){
                NameSurferEntry entree = fox.findEntry(text.getText());
                giraffe.addEntry(entree);
                giraffe.update();
            }
            else if(e.getSource()==clear)
                giraffe.clear();
                giraffe.update();
	}
}
