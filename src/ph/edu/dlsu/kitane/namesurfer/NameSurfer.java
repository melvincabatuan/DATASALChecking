/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.kitane.namesurfer;

/**
 *
 * @author ChristophJohnEric
 */
import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {
//public class NameSurfer extends Program implements NameSurferConstants {
        private JLabel name;
        private JTextField nameEntry;
        private String storeName="";
        private JButton graph;
        private JButton clear;
        private char[] cName;
        private NameSurferEntry entry;
        private NameSurferDataBase entrySearch;
        private NameSurferGraph popular;
	/* Method: init() */
	/**
	 * This method has the responsibility for reading in the data base
	 * and initializing the interactors at the top of the window.
	 */
	public void init() {
	    // You fill this in, along with any helper methods //
            popular = new NameSurferGraph();
            name = new JLabel("Name: ");
            graph = new JButton("Graph");
            clear = new JButton("Clear");
            nameEntry = new JTextField(30);
            nameEntry.addActionListener(this);
            add(name,NORTH);
            add(nameEntry,NORTH);
            add(graph,NORTH);
            add(clear,NORTH);
            add(popular);
            addActionListeners();
	}

	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are
	 * clicked, so you will have to define a method to respond to
	 * button actions.
	 */
	public void actionPerformed(ActionEvent e) {
            storeName = stdName(nameEntry.getText());
            if(e.getSource()==graph){
                entrySearch = new NameSurferDataBase(NAMES_DATA_FILE);
                entry = entrySearch.findEntry(storeName);
                popular.addEntry(entry);
                popular.update();
                nameEntry.setText("");
            }else if(e.getSource()==nameEntry){
                entrySearch = new NameSurferDataBase(NAMES_DATA_FILE);
                entry = entrySearch.findEntry(storeName);
                popular.addEntry(entry);
                popular.update();
                nameEntry.setText("");
            }else if(e.getSource()==clear){
                popular.clear();
                popular.update();
            }
	}
        
        
        private String stdName(String s){
            cName = new char[s.length()];
            for(int i=0;i<s.length();i++){
                if(i==0){
                    cName[i] = s.toUpperCase().charAt(i);
                }else{
                    cName[i] = s.toLowerCase().charAt(i);
                }
            }
            return s = String.copyValueOf(cName);
        }
        /*public static void main(String[] args) {
            new NameSurfer().start(args);
        }*/
}
