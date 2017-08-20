/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.ang.namesurfer;

import ph.edu.dlsu.datasal.ang.myinterface.NameSurferConstants;

import java.awt.event.ActionEvent;

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import acm.util.*;

public class NameSurferAng extends ConsoleProgram implements NameSurferConstants {

	/* Private instance variables*/ 
	private JLabel label;
	private JTextField Name;
	private JButton Graph;
	private JButton Clear;
        private JButton Add;
        private JButton Exit;
	private NameSurferDataBase namesdb;
	private NameSurferGraph graph;
	public int z=0; // z = nameCounter
/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
		
		//Set up initial display with ineractors and canvas
		label = new JLabel("Name ");
		add(label, NORTH);
		
		Name = new JTextField(20);
		add(Name, NORTH);
		Name.addActionListener(this);
		
                Add = new JButton("Add");
		add(Add, NORTH);
                
		Graph = new JButton("Graph");
		add(Graph, NORTH);
		
		Clear = new JButton("Clear");
		add(Clear, NORTH);
                
                Exit = new JButton("Exit");
		add(Exit, NORTH);
		
		graph = new NameSurferGraph();
		add(graph);
		
		addActionListeners();
		
		//reads the file of names and adds to the NameSurferDataBase
		namesdb = new NameSurferDataBase(NAMES_DATA_FILE);
                
                menu();
                

            
            
        }
                
                
	
	
/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */

public void menu(){
println("Welcome to Name Surfer v2.11");
println("\n");
println("Please input the names in the text field above.");
println("Entered names will be added to a queue and graphed in the order they were entered.");
println("\n");
println("ADD - Adds name to Queue.");
println("GRAPH - Graphs data values of each name in Queue.");
println("CLEAR - Clears the graph.");
println("EXIT - Closes the program.");
println("\n");
/*println("Once you are done inputting names, please select an option below:");
println("1 - Graph Name Data");
println("2 - Clear Queue");
println("3 - Exit");
int sel = readInt("Selection: ");
println("\n");*/
println("Names Entered:");

    
}

 private ph.edu.dlsu.datasal.ang.myqueue.MyQueue<String> nameLineUp = new ph.edu.dlsu.datasal.ang.myqueue.MyQueue<String>();
 
	public void actionPerformed(ActionEvent e) {
            String enteredName="noName";
            int nameCounter = 0;
            //int x=nameCounter;
            
		if(e.getActionCommand().equals("Clear")) {
			graph.clear();
			graph.update();
		}
                else if(e.getActionCommand().equals("Exit")) {
                    exit();
                }
                
		else if(e.getActionCommand().equals("Graph")) {
			
                        //DEQUEUE ALL NAMES AND GRAPH
                        
                        
                        //place within loop based on entered name counter
                        
                        //for(int x=10; x>0; x--)){
                    while(z!=0){
                        enteredName = nameLineUp.getFront();
                        //enteredName = nameLineUp.getNext(z);
                        nameLineUp.dequeue();
                        z--;
                        //nameCounter = nameCounter - 1;//POSSIBLE ERROR
                        println(z);
			NameSurferEntry rankings = namesdb.findEntry(enteredName);
			if(rankings != null) {
				graph.addEntry(rankings);
				graph.update();
			}}
		}
                 else if(e.getActionCommand().equals("Add")) {
                     enteredName = Name.getText(); 
                     println(enteredName);
                     nameLineUp.createQueue();
                    nameLineUp.enqueue(enteredName);
                    //nameCounter++; //POSSIBLE ERROR
                    z++;
                    println(z);
                    
                  }
                  
                 else {
                    println("Error. No Selection.");
                }
	}
        
         public static void main(String[] args) {
        new NameSurferAng().start();
}
}
        
       
 