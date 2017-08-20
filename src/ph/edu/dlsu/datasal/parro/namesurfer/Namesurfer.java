package ph.edu.dlsu.datasal.parro.namesurfer;

import ph.edu.dlsu.datasal.parro.myinterface.NameSurferConstants;
import acm.program.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import acm.util.*;

public class Namesurfer extends Program implements NameSurferConstants {

	/* Private instance variables*/ 
	private JLabel label;
	private JTextField Name;
	private JButton Graph;
	private JButton Clear;
        private JButton Delete;
	private NameSurferDataBase namesdb;
	private NameSurferGraph graph;
	
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
		
		graph = new NameSurferGraph();
		add(graph);
		
		addActionListeners();
		
		//reads the file of names and adds to the NameSurferDataBase
		namesdb = new NameSurferDataBase(NAMES_DATA_FILE);
	}
	
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("Clear")) {
                graph.clear();
            }
            else if(e.getActionCommand().equals("Graph")) { 
                String enteredName = Name.getText();
                NameSurferEntry rankings = namesdb.findEntry(enteredName);
                if(rankings != null) { 
                    graph.addEntry(rankings); 
                    graph.update();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Name not in Database");
                }
            }
            else {
            graph.removeEntry();
            //graph.a = graph.a+1;
            }
        }
        
        public static void main(String[] args){
            new Namesurfer().start();
        }
}

