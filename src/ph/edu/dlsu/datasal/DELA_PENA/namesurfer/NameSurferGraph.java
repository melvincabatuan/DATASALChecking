/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */
package ph.edu.dlsu.datasal.DELA_PENA.namesurfer;
import ph.edu.dlsu.datasal.DELA_PENA.ADT.MyArrayList.MyArrayList;
import ph.edu.dlsu.datasal.DELA_PENA.ADT.MyQueue.QueueDP;
import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;
import acm.program.*;
import acm.io.*;

public class NameSurferGraph extends GCanvas implements NameSurferConstants, ComponentListener {
    private MyArrayList<NameSurferEntry> DataEntries = new MyArrayList<>();
    private QueueDP<NameSurferEntry> printQ = new QueueDP<>();
    
    
    
	/**
	 * Creates a new NameSurferGraph object that displays the data.
	 */
    
        public void init(){
            
        }
	public NameSurferGraph() {
		addComponentListener(this);
                DataEntries.createList();
                
	}
        
        public void drawgraph(int width, int height){
            int spacing = width/NDECADES;
            int x=0;
            int year=START_DECADE;
            GLine line;
            GLabel axis;
            GRect bg = new GRect(0,0, width, height); 
            bg.setFilled(true);
            bg.setColor(Color.DARK_GRAY);
            add(bg);
            for(int i=0; i<NDECADES; i++){
                line = new GLine(x, 0, x, getHeight());
                add(line);
                x+=spacing;
            }
            x=0;
            for(int i=0; i<NDECADES; i++){
                axis = new GLabel(""+year);
                axis.setLocation(x, height-GRAPH_MARGIN_SIZE+axis.getHeight());
                add(axis);
                year+=10;
                x+=spacing;
            }
            line = new GLine(0, GRAPH_MARGIN_SIZE, width, GRAPH_MARGIN_SIZE);
            add(line);
            line = new GLine(0, height-GRAPH_MARGIN_SIZE, width, height-GRAPH_MARGIN_SIZE);
            add(line);
            
            
        }
	
        public void drawseries(int width, int height, NameSurferEntry entry, Color color){
            int bottom = height-GRAPH_MARGIN_SIZE;
            int top = GRAPH_MARGIN_SIZE;
            int xdecade=0;
            int xspacing = width/NDECADES;
            int tail=bottom - (entry.getRank(0) * (bottom-top)/1000);
            int head=0;
            String name = entry.getName().substring(0, 1) + entry.getName().substring(1).toLowerCase();
            
            GLine series;
            GLabel point;
            
            point = new GLabel(name + ": "+entry.getRank(0));
            point.setLocation(xdecade, tail);
            point.setColor(color);
            add(point);
            for(int decade=1; decade<NDECADES; decade++){
                head = bottom - (entry.getRank(decade) * (bottom-top)/1000);
                series = new GLine(xdecade, tail, xdecade+xspacing, head);
                series.setColor(color);
                add(series);
                point = new GLabel(name + ": "+entry.getRank(decade));
                point.setLocation(xdecade+xspacing, head);
                point.setColor(color);
                add(point);
                tail=head;
                xdecade+=xspacing;
            }
            
        }
	
	/**
	 * Clears the list of name surfer entries stored inside this class.
	 */
	public void clear() {
		if(!DataEntries.isEmpty())
                    DataEntries.remove(1);
                update();
            }
	
	
	/* Method: addEntry(entry) */
	/**
	 * Adds a new NameSurferEntry to the list of entries on the display.
	 * Note that this method does not actually draw the graph, but
	 * simply stores the entry; the graph is drawn by calling update.
	 */
	public void addEntry(NameSurferEntry entry) {
            DataEntries.add(DataEntries.size()+1, entry);
	}
	
	
	/**
	 * Updates the display image by deleting all the graphical objects
	 * from the canvas and then reassembling the display according to
	 * the list of entries. Your application must call update after
	 * calling either clear or addEntry; update is also called whenever
	 * the size of the canvas changes.
	 */
	public void update() {
            int height = getHeight();
            int width = getWidth();
            removeAll();
            drawgraph(width, height);
            queueprint();
            int linecolor=0;
            while(!printQ.isEmpty()){
                drawseries(width, height, printQ.deQueue(), setColor(linecolor));
                linecolor++;
            }
	}
        
        private void queueprint(){
            for(int i=0; i<DataEntries.size(); i++){
                printQ.enQueue(DataEntries.get(i+1));
            }
        }
        
	public Color setColor(int i){
            Color color = Color.red;
            switch(i){
                case 0:
                    color = Color.BLUE;
                    break;
                case 1:
                    color = Color.CYAN;
                    break;
                case 2:
                    color = Color.GREEN;
                    break;
                case 3:
                    color = Color.orange;
                    break;
                case 4:
                    color = Color.pink;
                    break;
            }
            return color;
        }
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
        
        
}
