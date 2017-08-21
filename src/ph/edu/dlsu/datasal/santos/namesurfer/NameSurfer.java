package ph.edu.dlsu.datasal.santos.namesurfer;

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import acm.util.*;

public class NameSurfer extends Program implements NameSurferConstants {

	private JLabel label;
	private JTextField Name;
	private JButton Graph;
	private JButton Clear;
        private JButton Del;
	private NameSurferDataBase namesdb;
	private NameSurferGraph1 graph;
	
	public void init() {
		
		label = new JLabel("Name ");
		add(label, EAST);
		
		Name = new JTextField(5);
		add(Name, EAST);
		Name.addActionListener(this);
		
		Graph = new JButton("Graph");
		add(Graph, EAST);
		
		Clear = new JButton("Clear");
		add(Clear, EAST);
                
                Del = new JButton("Del");
		add(Del, EAST);
		
		graph = new NameSurferGraph1();
		add(graph);
		
		addActionListeners();
		
		namesdb = new NameSurferDataBase(NAMES_DATA_FILE);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Clear")) {
			graph.clear();
			graph.update();
		}
                else if(e.getActionCommand().equals("Graph")) {
			String enteredName = Name.getText();
			NameSurferEntry rankings = namesdb.findEntry(enteredName);
			if(rankings != null) {
				graph.addEntry(rankings);
				graph.update();
			}
                        else{
                            JOptionPane.showMessageDialog(null, "Name not found");
                        }
		}
                else if(e.getActionCommand().equals("Del")) {
			graph.del();
			graph.update();
		}
	}
    public static void main(String[] args){
                new NameSurfer().start();
            }

}
        
