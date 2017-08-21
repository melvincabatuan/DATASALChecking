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
package datasal.Narag.namesurfer;

import acm.program.*;
import acm.util.*;
import acm.io.*;
import acm.graphics.*;
import java.awt.event.*;
import javax.swing.*;
import datasal.Narag.myQueue.MyQueue;

public class NameSurfer extends ConsoleProgram implements NameSurferConstants {
//public class NameSurfer extends Program implements NameSurferConstants {
    //================================Variable stuff goes here===============================//
        private JButton graph = new JButton ("Graph");
        private JButton clear = new JButton ("Clear");
        private JButton queue = new JButton("Queue");
        private JTextField textfield = new JTextField(40);
        private JLabel label = new JLabel("Name");
        private NameSurferDataBase namesdb = new NameSurferDataBase(NAMES_DATA_FILE);
        private NameSurferGraph grapher = new NameSurferGraph();
        private MyQueue history = new MyQueue();
        private MyQueue listofnames = new MyQueue();
    //=======================================================================================//  
    
    //===============================Action listeners go here================================//
        ActionListener graphact = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String line = textfield.getText();
                NameSurferEntry rankings = namesdb.findEntry(line);
                /**if (listofnames.isEmpty()==false){
                    for(int a = listofnames.size(); a>0; a--){
                        grapher.addEntry(NameSurferEntry(listofnames.getFront()));
                        grapher.update();
                        println(listofnames.getFront().toString()+" added to graph.");
                        println("");
                        grapher.update();
                        listofnames.dequeue();
                    }
                  }**/
                
                //else{
                    if(rankings != null) {
                    grapher.addEntry(rankings);
                    grapher.update();
                    }
                    //============puts entered name in history queue==============//
                    if (history.isFull()){
                    
                     }
                     else{
                    history.enqueue(line);
                    println(history.getRear());
                    println("");
                }
                //============================================================//
                }
                
                
                //}
            };
        

        
        ActionListener clearact = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               grapher.clear();
               grapher.update();
               for(int a=0;a<40;a++){
                   println("");
               }
            }
        };
        
        ActionListener queueact = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String line = textfield.getText();
                NameSurferEntry rankings = namesdb.findEntry(line);
            if(rankings != null) {
                listofnames.enqueue(rankings);
                println(line+" added to queue.");
                println("");
                }
            
            }
        };
    //=======================================================================================//    
        
        
      //init method//
	public void init() {
	    // You fill this in, along with any helper methods //
            add(label, NORTH);
            add(textfield, NORTH);
            graph.addActionListener(graphact);
            clear.addActionListener(clearact);
            queue.addActionListener(queueact);
            add(graph, NORTH);
            add(clear, NORTH);
            add(queue, NORTH);
            add(grapher);
            history.createQueue();
            listofnames.createQueue();
	}
        
        public void run(){
    
        }
        
        //=======================================================================================//    
        public void runNameSurfer(){
            new NameSurfer().start();
        }
        //=======================================================================================//

}
