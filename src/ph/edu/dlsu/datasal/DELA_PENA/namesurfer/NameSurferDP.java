
package ph.edu.dlsu.datasal.DELA_PENA.namesurfer;
import ph.edu.dlsu.datasal.DELA_PENA.ADT.MyQueue.QueueDP;

import acm.program.*;
import acm.io.*;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class NameSurferDP extends Program implements NameSurferConstants {
        private final NameSurferGraph graph = new NameSurferGraph();
        private final JLabel NameLabel = new JLabel("Name");
        private final TextField InputBox = new TextField();
        private final JButton GraphButton = new JButton("Graph");
        private final JButton ClearButton = new JButton("Clear");
        private final int InputCharMax = 40;
        public IODialog dialog = getDialog();
        
        private final String FILENAME = ".\\src\\" + NAMES_DATA_FILE;
        private QueueDP<NameSurferEntry> DataEntries = new QueueDP<>();
        private final NameSurferDataBase Database = new NameSurferDataBase(FILENAME);
    
	/* Method: init() */
	/**
	 * This method has the responsibility for reading in the data base
	 * and initializing the interactors at the top of the window.
	 */
	public void init(){
            add(graph);
            add(NameLabel, NORTH);
            InputBox.setColumns(InputCharMax);
            add(InputBox, NORTH);
            InputBox.addActionListener(this);
            add(GraphButton, NORTH);
            GraphButton.addActionListener(this);
            add(ClearButton, NORTH);
            ClearButton.addActionListener(this);
            graph.drawgraph(graph.getWidth(), graph.getHeight());
            //NameSurferEntry temp = Database.findEntry("Aaliyah");
            //dialog.println(temp.toString());
            //graph.addEntry(temp);
	}
        

	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are
	 * clicked, so you will have to define a method to respond to
	 * button actions.
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==GraphButton || e.getSource()==InputBox){
                    NameSurferEntry entry =Database.findEntry(InputBox.getText());
                    if(entry!=null){
                        graph.addEntry(entry);
                        graph.update();
                        dialog.println(Database.findEntry(InputBox.getText()).getName());
                    }
                    else
                        dialog.println("Entry not found");
                }
                else if(e.getSource() == ClearButton){
                    graph.clear();
                
                }
                
	}
        
        public static void main(String[] args){
            new NameSurferDP().start(args);
        }
}
