package ph.edu.dlsu.datasal.SIMEON.namesurfer;

/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import ph.edu.datasal.SIMEON.myqueue.Queue;
/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {
public Queue<NameSurferEntry> ADT = new Queue <NameSurferEntry>();

	/**
	 * Creates a new NameSurferGraph object that displays the data.
	 */
	public NameSurferGraph() {
		addComponentListener(this);
                ADT.createList();
                ;
                
		// You fill in the rest //
	}
	
	
	/**
	 * Clears the list of name surfer entries stored inside this class.
	 */
	public void clear() {
            ADT.clear();
            update();
		// You fill this in //
	}
	
	
	/* Method: addEntry(entry) */
	/**
	 * Adds a new NameSurferEntry to the list of entries on the display.
	 * Note that this method does not actually draw the graph, but
	 * simply stores the entry; the graph is drawn by calling update.
	 */
	public void addEntry(NameSurferEntry entry) {
            ADT.enqueue(entry);
		// You fill this in //
	}
	
	
	/**
	 * Updates the display image by deleting all the graphical objects
	 * from the canvas and then reassembling the display according to
	 * the list of entries. Your application must call update after
	 * calling either clear or addEntry; update is also called whenever
	 * the size of the canvas changes.
	 */
        private int z=0;
	public void update() {
            
            GLine top = new GLine(0,GRAPH_MARGIN_SIZE,getWidth(),GRAPH_MARGIN_SIZE);
            GLine bottom = new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(),getHeight() - GRAPH_MARGIN_SIZE);
            add(top);
            add(bottom);
            double spacee = getWidth() / NDECADES;
            for( int i=0;i<NDECADES;i++){
            GLine vertical = new GLine( (i + 1) * spacee,0, (i + 1) * spacee,getHeight() );
            add(vertical);
            }
            for(int i=0;i<NDECADES;i++){
            String datez = Integer.toString(i*10+START_DECADE);
            GLabel dateLabel = new GLabel(datez,i*spacee,getHeight() );
            add(dateLabel);
            }

            for(int i=0;i<ADT.size();i++){
                for(int j=0;j<NDECADES-1;j++){
                    GLine line = new GLine(spacee * j, vertValue((ADT.peek()).getRank(j)),spacee * (j + 1),vertValue((ADT.peek()).getRank(j +1)));
                            if(z% 4 == 0)
                                    line.setColor(Color.BLUE);
                            else if(z % 4 == 1)
                                    line.setColor(Color.RED);
                            else if(z % 4 == 2)
                                    line.setColor(Color.MAGENTA);
                            else if(z % 4 == 3)
                                    line.setColor(Color.BLACK);
                    
                    add(line);
                  }
                for(int j=0;j<NDECADES;j++){
                    String LabelEntry = "";
                    if((ADT.peek()).getRank(j)!=0)
                        LabelEntry = (ADT.peek()).getName()+" "+(ADT.peek()).getRank(j);
                    else
                        LabelEntry = (ADT.peek()).getName()+" *";
                    
                    GLabel help = new GLabel(LabelEntry,spacee * j,vertValue((ADT.peek()).getRank(j)));
                    if(z%4 == 0){
                    help.setColor(Color.BLUE);
                    }else if(z%4 == 1){
                    help.setColor(Color.RED);
                    }else if(z%4 == 2){
                    help.setColor(Color.MAGENTA);
                    }else if(z% 4 == 3)
                    help.setColor(Color.BLACK);
                 
                    add(help);
            }
                z++;
                ADT.dequeue();
        }
                
        }

        private double vertValue(int rank){
            double rankDouble = rank;
            if(rankDouble!=0){
                rankDouble = rankDouble/MAX_RANK;
                rankDouble = rankDouble*(getHeight()-2*GRAPH_MARGIN_SIZE);
                rankDouble = rankDouble + GRAPH_MARGIN_SIZE;
            }
            else 
                rankDouble = getHeight()-GRAPH_MARGIN_SIZE;
            return rankDouble;
        }
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }

}

