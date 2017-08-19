/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */
package ph.edu.dlsu.rivera.namesurfer;
import acm.graphics.*;
import acm.util.JTFTools;
import java.awt.event.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class NameSurferGraph extends GCanvas implements NameSurferConstants, ComponentListener {
        boolean isUpdate =false;
        private QueueADT<NameSurferEntry> entries = new QueueADT<>();
        QueueADT<Color> color_queue = new QueueADT<>();
        public String backup;
        NameSurfer nameSurferObject = new NameSurfer();
        private int margin, error,divisions,scope_height,scope_width;
        int space =50;
        JFrame frame1;
	/**
	 * Creates a new NameSurferGraph object that displays the data.
	 */
	public NameSurferGraph(JFrame frame) {
            margin =20;
            error=15;
		frame.addComponentListener(this);
		// You fill in the rest //
                
                 this.frame1=frame;
                initDisplay(frame);
               
           
	}
	
	
	/** 
	 * Clears the list of name surfer entries stored inside this class.
	 */
	public void clear() {
		// You fill this in //
                this.removeAll();
                initDisplay(frame1);
	}
	
	public void displayAll(){
            initColor();
            int length = entries.size();
            if(entries.isEmpty())
                {
                    System.out.println("queue empty");
                }
            for(int i =0; i<length;i++){
                NameSurferEntry temp = entries.deQueue();
                Color temp_color = color_queue.deQueue();
                addEntry(temp, temp_color);
                entries.enQueue(temp);
                JTFTools.pause(10);
                repaint();
                
            }
                
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
	/* Method: addEntry(entry) */
	/**
	 * Adds a new NameSurferEntry to the list of entries on the display.
	 * Note that this method does not actually draw the graph, but
	 * simply stores the entry; the graph is drawn by calling update.
	 */
        
	public void addEntry(NameSurferEntry entry,Color colorObject) {
		// You fill this in //
                GPoint point,last_point;
                double radius = 2.5;
                GOval dot;
                
                Double step = ((double)scope_height-space-2*margin)/1000;
                GLine tracer;
                last_point=null;
                int rank;
                for(int i=0;i<12;i++){
                    String rankString="";
                    rank = entry.getRank(i);
                    rankString = rank+"";
                    if(rank==0){
                        rank=1001;
                        rankString="0";
                    }
                    
                    point = new GPoint(margin + i*divisions-2.5, (2*margin + step*rank-2.5));
                    
                    GLabel dot_label = new GLabel(rankString);
                    dot_label.setColor(colorObject);
                    dot_label.setLocation(point.getX()+radius-dot_label.getWidth()/2, point.getY()-dot_label.getHeight());
                    
                    
                    
                    
                    dot = new GOval(5, 5);
                    dot.setFilled(true);
                    dot.setColor(colorObject);
                    dot.setVisible(true);
                    dot.setLocation(point);
                    
                    add(dot);
                    add(dot_label);
                    if(last_point==null){
                        last_point=point;
                    }
                    tracer = new GLine(last_point.getX()+radius , last_point.getY()+radius , point.getX()+radius , point.getY()+radius );
                    tracer.setVisible(true);
                    tracer.setColor(colorObject);
                    
                    add(tracer);
                    last_point=point;
                    dot.sendToFront();
                }
                GLabel name = new GLabel(entry.getName());
                if (colorObject == Color.RED) {
                name.setLocation(3*margin+scope_width * 0 / 6 + margin, this.getHeight() - name.getHeight());
            }
            if (colorObject == Color.BLUE) {
                name.setLocation(3*margin+scope_width * 1 / 6 + margin, this.getHeight() - name.getHeight());
            }
            if (colorObject == Color.GREEN) {
                name.setLocation(3*margin+scope_width * 2 / 6 + margin, this.getHeight() - name.getHeight());
            }
            if (colorObject == Color.CYAN) {
                name.setLocation(3*margin+scope_width * 3 / 6 + margin, this.getHeight() - name.getHeight());
            }
            if (colorObject == Color.magenta) {
                name.setLocation(3*margin+scope_width * 4 / 6 + margin, this.getHeight() - name.getHeight());
                }
                name.setColor(colorObject);
                
                add(name);
                if(!isUpdate){
                if(entries.isFull()){
                    entries.deQueue();
                    
                }
                System.out.println("added");
                entries.enQueue(entry);
                }
                
	}
	
	
	/**
	 * Updates the display image by deleting all the graphical objects
	 * from the canvas and then reassembling the display according to
	 * the list of entries. Your application must call update after
	 * calling either clear or addEntry; update is also called whenever
	 * the size of the canvas changes.
	 */
	public void update() {
            
		// You fill this in //
                this.removeAll();
                initDisplay(frame1);
                displayAll();
                
                
                
	}
	
	
	/* Implementation of the ComponentListener interface */
        

    private void initDisplay(JFrame frame) {
        this.setSize( frame.getWidth()-2*margin-error, frame.getHeight()-150);
        this.setBackground(Color.white);
                
                int width = this.getWidth();
                int height = this.getHeight();
                scope_width = width - 2*margin;
                scope_height = height -2*margin;
                divisions = scope_width/11;
                for(int i =0;i<12;i++){
                    GLine grid_line = new GLine(margin+i*divisions, 2*margin, margin+i*divisions, height-margin-space);
                    grid_line.setVisible(true);
                    grid_line.setColor(Color.black);
                    GLabel decade = new GLabel((1900+i*10)+"");
                    decade.setColor(Color.BLACK);
                    decade.setLocation(margin+i*divisions-decade.getWidth()/2, height-3-space);
                    add(grid_line);
                    add(decade);
                }
                GRect namebg = new G3DRect(margin, height-30, scope_width, 20);
                namebg.setColor(Color.lightGray);
                namebg.setFilled(true);
                add(namebg);
    }
    public void ownpause(){
         for(int i=0;i<10000;i++){
                for(int j=0;j<100000;j++){
                    
                }
            }
    }
        

    @Override
    public void componentResized(ComponentEvent e) {
        isUpdate = true;
        update();
        isUpdate = false;
        
    }

    @Override
    public void componentMoved(ComponentEvent e) {
       }

    @Override
    public void componentShown(ComponentEvent e) {
       }

    @Override
    public void componentHidden(ComponentEvent e) {
       }
}
