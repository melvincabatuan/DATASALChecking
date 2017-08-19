/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

package ph.edu.dlsu.rivera.namesurfer;
import acm.graphics.GCanvas;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.program.*;
import java.awt.Color;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import acm.io.IODialog;
import java.awt.*;
import java.awt.Graphics;
import java.util.StringTokenizer; 

public class NameSurfer extends Program implements NameSurferConstants {
        String backup ="";
        NameSurferEntry[] entry_array = new NameSurferEntry[5];
            NameSurferEntry query,temp_query;
            private int margin=20, error=15;
            IODialog dialog;
            JFrame frame = new JFrame("Name Surfer by Maverick Rivera");
            JButton graph = new JButton("graph");
            JButton clear = new JButton("clear");
            JTextField textField = new JTextField("Enter name here");
            JLabel name = new JLabel("Name:");
            String filename=System.getProperty("user.dir")+ "\\src\\ph\\edu\\dlsu\\rivera\\namesurfer\\names-data.txt";
            private String search;
            private NameSurferDataBase database;
            private NameSurferGraph visualization;
            QueueADT<NameSurferEntry> entry_queue = new QueueADT<>();
            QueueADT<NameSurferEntry> temp_queue = new QueueADT<>();
            QueueADT<java.awt.Color> color_queue = new QueueADT<>();
            QueueADT<NameSurferEntry> save_queue = new QueueADT<>();
            Color temp_color;
            GCanvas border;
            
            
	/* Method: init() */
	/**
	 * This method has the responsibility for reading in the data base
	 * and initializing the interactors at the top of the window.
	 */
            
	public void init() {
	    // You fill this in, along with any helper methods //
            
            addKeyListener(this);
            displayUI();    
             initColor();
                try {
                    initDataBase(filename);
                } catch (IOException ex) {
                    Logger.getLogger(NameSurfer.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            
	}
        
        public void displayUI(){
            int margin = 20, error=15; 
            int startY=30;
            frame.setSize(1000,700);
            frame.addMouseListener(this);
            visualization = new NameSurferGraph(frame);
            textField.setToolTipText("Enter the name here.");
            graph.setBounds(500, startY, 100, 30);
            clear.setBounds(620, startY, 100, 30);
            textField.setBounds(100,startY,380,30);
            name.setSize(50, 30);
            name.setLocation(textField.getX()-name.getWidth(), 70);
            visualization.setLocation(margin, startY+margin+graph.getY());
            
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(visualization);
            frame.add(name);
            frame.add(textField);
            frame.add(clear);
            frame.add(graph);
            
            frame.setLayout(null);
            frame.setVisible(true);
            
            frame.addComponentListener(visualization);
            name.setLocation(textField.getX()-name.getWidth(), 70);
            
            //action listeners
            clear.addActionListener(this);
            graph.addActionListener(this);
            clear.setActionCommand("clear");
            graph.setActionCommand("graph");
            frame.getRootPane().setDefaultButton(graph);
            visualization.addMouseListener(this);
//            System.out.println("pogi si mave");
//            println("pogi si mavee");
            
            
            
        }
	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are
	 * clicked, so you will have to define a method to respond to
	 * button actions.
	 */
        
        @Override
        public void mouseClicked(MouseEvent me){
            try {
                double x = me.getX();
            double y = me.getY();
            System.out.println("clicked "+x+"  "+y);
        GObject temp = visualization.getElementAt(x, y);
                if((GOval)temp!=null){
                    
                }
            } catch (Exception e) {
            }
            
        }
        
        
        
            @Override
	public void actionPerformed(ActionEvent e) {
		// You fill this in //
                String temp = e.getActionCommand();
                
		if (temp.equals("clear")) {
//			System.out.println("clear");
            //                        visualization.clear();
                    manyentry(backup);
		}else if(temp.equals("graph")){
                    if(multipleInput()){
//                       System.out.println("Multiple input");
                        
                            manyentry(textField.getText());
                       
                    }else{
                        oneentry(textField.getText());
                }
                
	}
        }
        public void manyentry(String text)  {
            StringTokenizer token = new StringTokenizer(text, " ");
                        while (token.hasMoreTokens()) {                            
                            oneentry(token.nextToken());
                            repaint();
                            pause(100);
                            
                        }
        }
        public void myownpause(){
            for(int i=0;i<10000;i++){
                for(int j=0;j<100000;j++){
                    
                }
            }
        }
        public void oneentry(String text){
            String first_letter;
//                    System.out.println("graph");
                    search = text;
                    search = search.toLowerCase();
                    first_letter=search.substring(0, 1);
                    first_letter= first_letter.toUpperCase();
                    search = first_letter + search.substring(1);
//                    System.out.println(search);
                    query=null;
                    try{
                    query = database.findEntry(search);
                    }catch(NullPointerException error){
//                        dialog = getDialog();
//                        dialog.println("The name entered is not found on the database please try again.");
                    }
                    if (query == null) {
                        System.out.println("Cannot find name!");
                    }else{
                        visualization.addEntry(query, color_queue.deQueue());
//                        visualization.clear();
//                        
//                        if(entry_queue.isFull()){
//                            entry_queue.deQueue();
//                        }
//                        
//                        entry_queue.enQueue(query);
//                        println(entry_queue.size());
//                        
//                        backup="";
//                        while(!entry_queue.isEmpty()){
//                            
//                            temp_query = entry_queue.deQueue();
//                            temp_color  = color_queue.deQueue();
//                           
//                            visualization.addEntry(temp_query,temp_color);
//                            
//                            if(!entry_queue.isEmpty()){
//                            backup = backup.concat(temp_query.getName()+" ");}
//                            else{
//                                backup = backup.concat(temp_query.getName());
//                            }
//                            
//                            temp_queue.enQueue(temp_query);
//                        }
//                        println(backup+"end");
//                        visualization.backup = backup;
//                        while(!temp_queue.isEmpty()){
//                            temp_query = temp_queue.deQueue();
//                            entry_queue.enQueue(temp_query);
//                            
//                        }
//                        
//                        
//                        initColor();
                        
                        
                    }
                    }
        
        @Override
        public void run(){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(NameSurfer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
        public static void main(String[] args){
            new NameSurfer().start();
        }

    private void initDataBase(String filename) throws IOException {
         database = new NameSurferDataBase(filename);
        }

    private void initColor() {
        while(!color_queue.isEmpty()){
            color_queue.deQueue();
        }
        color_queue.enQueue(Color.RED);
        color_queue.enQueue(Color.BLUE);
        color_queue.enQueue(Color.GREEN);
        color_queue.enQueue(Color.CYAN);
        color_queue.enQueue(Color.magenta);
    }
    
    public void queueToArray(QueueADT queue,NameSurferEntry[] array){
        backup="";
            int size=queue.size();
            for(int i=0;i<size;i++){
                array[i]=(NameSurferEntry) queue.deQueue();
                backup = backup.concat(array[i].getName()+" ");
            }
            for(int i=0;i<size;i++){
                queue.enQueue(array[i]);
            }
            
        }
    
	public void componentShown(ComponentEvent e) { }

    private boolean multipleInput() {
            StringTokenizer token = new StringTokenizer(textField.getText(), " ");
            if(token.countTokens()>1){
                return true;
            }else{
                return false;
            }
    
    }
    
}
