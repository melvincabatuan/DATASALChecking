/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

package ph.edu.dlsu.datasal.ocampo.namesurfer;

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

        private JButton graphButton = new JButton("Graph");
        private JButton clearButton = new JButton("Clear");
        private JLabel status1 = new JLabel("Names Graphed: 0  ");
        
        private JTextField nameField = new JTextField(25);
        
        private NameSurferDataBase nameDatabase = new NameSurferDataBase("names-data.txt");
        private NameSurferEntry entry;
        
        private NameSurferGraph display = new NameSurferGraph();
        
	/* Method: init() */
	/**
	 * This method has the responsibility for reading in the data base
	 * and initializing the interactors at the top of the window.
	 */
	public void setUp() {
	    add(new JLabel("Enter name:"), NORTH);
            nameField.addActionListener(this);
            add(status1, SOUTH);
            add(nameField, NORTH); 
            add(graphButton, NORTH);
            add(clearButton, NORTH);
            add(display);
            display.update();
            addActionListeners();
	}

	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are
	 * clicked, so you will have to define a method to respond to
	 * button actions.
	 */
	public void actionPerformed(ActionEvent e) {
                Object src = e.getSource();
		if(src==graphButton || src==nameField) {
                    entry = nameDatabase.findEntry(nameField.getText());
                    if(entry!=null) {
                        display.addEntry(entry);
                        display.update();
                    }
                    nameField.selectAll();
                    status1.setText("Names graphed: " + String.valueOf(display.getNumberOfNamesGraphed()));
                }
                else if(src==clearButton) {
                    display.clear();
                    display.update();
                    status1.setText("Names graphed: " + String.valueOf(display.getNumberOfNamesGraphed()));
                }
	}
        
}
