/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */
package ph.edu.dlsu.datasal.ocampo.namesurfer;

import ph.edu.dlsu.datasal.ocampo.myqueue.*;
import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;


public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

        private GLine[] verticalGrid;
        private GLine[] horizontalGrid;
        private GLabel[] years;
        private GLabel[] nameAtDecadeN;
        
        private double graphHeight;
        private MyDynamicQueue<NameSurferEntry> nameQueue;
        private final int MAX_QUEUE_SIZE;
        
        private void drawGrid() {
            double hGridLength = getWidth()/12;
            for(int i=0; i<NDECADES+1; i++) {
                verticalGrid[i] = new GLine(0, 0, 0, getHeight());
                add(verticalGrid[i], i*hGridLength, 0);
                if(i<NDECADES) {
                    years[i] = new GLabel(String.valueOf(i*10 + START_DECADE));
                    add(years[i], i*hGridLength, getHeight());
                }
                if(i<2) {
                    horizontalGrid[i] = new GLine(0, 0, getWidth(), 0);
                }
            }
            add(horizontalGrid[0], 0, GRAPH_MARGIN_SIZE);
            add(horizontalGrid[1], 0, getHeight()-GRAPH_MARGIN_SIZE);
        }
        
        private Color setColor(int colorNum) {
            Color color = null;
            switch(colorNum) {
                case 1: color = Color.RED; break;
                case 2: color = Color.CYAN; break;
                case 3: color = Color.GREEN; break;
                case 4: color = Color.BLUE; break;
                case 5: color = Color.ORANGE; break;
            }
            return color;
        }
        
        private void drawGraph(NameSurferEntry entry) { 
            GPoint[] pts = new GPoint[NDECADES];
            GLabel[] ptNames = new GLabel[NDECADES];
            GLine[] trends = new GLine[NDECADES-1];
            
            // Extra figures
            GOval[] ptFigures = new GOval[NDECADES];
            double hGridLength = getWidth()/12;
            
            double gPtY = GRAPH_MARGIN_SIZE;
            
            // Set up points
            for(int i=0; i<NDECADES; i++) {
                pts[i] = new GPoint();
                nameAtDecadeN[i] = new GLabel(entry.getName() + " " + ( (entry.getRank(i)==0)?"*":String.valueOf(entry.getRank(i)) ));
                ptFigures[i] = new GOval(3, 3);
                ptNames[i] = new GLabel(entry.getName() + " " + ( (entry.getRank(i)==0)?"*":String.valueOf(entry.getRank(i)) ));
                ptFigures[i].setFilled(true);
                
                ptNames[i].setColor((setColor(nameQueue.positionOf(entry))!=null) ? setColor(nameQueue.positionOf(entry)) : Color.BLACK);
                nameAtDecadeN[i].setColor((setColor(nameQueue.positionOf(entry))!=null) ? setColor(nameQueue.positionOf(entry)) : Color.BLACK);
                
                gPtY = (entry.getRank(i)>0 && entry.getRank(i)<=1000)?(GRAPH_MARGIN_SIZE + entry.getRank(i)*graphHeight/1000):(getHeight() - GRAPH_MARGIN_SIZE);
                pts[i].setLocation(i*hGridLength, gPtY);
                
                add(ptNames[i], pts[i]);
                add(ptFigures[i], pts[i].getX()-ptFigures[i].getWidth()/2, pts[i].getY()-ptFigures[i].getHeight()/2);
            }
            // Set up lines
            for(int i=0; i<NDECADES-1; i++) {
                trends[i] = new GLine(pts[i].getX(), pts[i].getY(), pts[i+1].getX(), pts[i+1].getY());
                trends[i].setColor((setColor(nameQueue.positionOf(entry))!=null) ? setColor(nameQueue.positionOf(entry)) : Color.BLACK);
                add(trends[i]);
            }
        }
        
	/**
	 * Creates a new NameSurferGraph object that displays the data.
	 */
	public NameSurferGraph() {
                verticalGrid = new GLine[NDECADES+1];
                horizontalGrid = new GLine[2];
                years = new GLabel[NDECADES];
                graphHeight = getHeight()-2*GRAPH_MARGIN_SIZE;
                nameQueue = new MyDynamicQueue<>();
                nameAtDecadeN = new GLabel[NDECADES];
                MAX_QUEUE_SIZE = 5;
		addComponentListener(this);
	}
	
	
	/**
	 * Clears the list of name surfer entries stored inside this class.
	 */
	public void clear() {
		if(!nameQueue.isEmpty()) nameQueue.deQueueAll();
	}
	
	
	/* Method: addEntry(entry) */
	/**
	 * Adds a new NameSurferEntry to the list of entries on the display.
	 * Note that this method does not actually draw the graph, but
	 * simply stores the entry; the graph is drawn by calling update.
	 */
	public void addEntry(NameSurferEntry entry) {
		if(!nameQueue.contains(entry)) nameQueue.enQueue(entry);
                if(nameQueue.size()>MAX_QUEUE_SIZE) nameQueue.deQueue();
	}
	
	
	/**
	 * Updates the display image by deleting all the graphical objects
	 * from the canvas and then reassembling the display according to
	 * the list of entries. Your application must call update after
	 * calling either clear or addEntry; update is also called whenever
	 * the size of the canvas changes.
	 */
	public void update() {
                LinkedList<NameSurferEntry> queueList = nameQueue.toDynamicList();
                
                removeAll();
                graphHeight = getHeight() - 2*GRAPH_MARGIN_SIZE;
		drawGrid();
                
                for(int i=0; i<queueList.size(); i++) {
                    drawGraph(queueList.get(i));
                }
	}
	
        // Additional components
        public int getNumberOfNamesGraphed() {
            return nameQueue.size();
        }
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
        
        private boolean isMouseInComponent(int x, int y) {
            return (x>=0 && x<getWidth() && y>=0 && y<getHeight());
        }
}
