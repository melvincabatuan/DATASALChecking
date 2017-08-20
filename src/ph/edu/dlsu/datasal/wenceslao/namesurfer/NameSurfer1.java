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

package ph.edu.dlsu.datasal.wenceslao.namesurfer;

// Author: Luis Paolo D. Wenceslao

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import acm.util.*;

public class NameSurfer1 extends Program implements NameSurferConstants {

	/* Private instance variables*/ 
	private JLabel label;
	private JTextField Name;
	private JButton Graph;
	private JButton Clear;
        private JButton Delete;
        private NameSurferEntry rankings;
	private NameSurferDataBase namesdb;
	private NameSurferGraph1 graph;
	
/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
            //Set up initial display with ineractors and canvas
            label = new JLabel("Name ");
            add(label, SOUTH);

            Name = new JTextField(20);
            add(Name, SOUTH);
            Name.addActionListener(this);

            Graph = new JButton("Graph");
            add(Graph, SOUTH);

            Clear = new JButton("Clear");
            add(Clear, SOUTH);
            
            Delete = new JButton("Delete");
            add(Delete, SOUTH);

            graph = new NameSurferGraph1();
            add(graph);

            addActionListeners();

            //reads the file of names and adds to the NameSurferDataBase
            namesdb = new NameSurferDataBase(NAMES_DATA_FILE);
	}
	
/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if(source == Name || source == Graph){
                String enteredName = Name.getText();
                enteredName = enteredName.toLowerCase();
                rankings = namesdb.findEntry(enteredName);
                if(rankings != null) {
                    graph.addEntry(rankings);
                    graph.update();
                    Name.setText(null);
                }
                else{
                    Name.setText(enteredName.substring(0,1).toUpperCase() + enteredName.substring(1) + " is fairly unpopular.");
                }
            }
            if(source == Clear){
                graph.clear();
                graph.update();
                Name.setText(null);
            }
            if(source == Delete){
                graph.deleteEntry(rankings);
                graph.update();
                Name.setText(null);
            }
	}
        
//        public static void main(String[] args) {
//            new NameSurfer1().start(args);
//        }
}