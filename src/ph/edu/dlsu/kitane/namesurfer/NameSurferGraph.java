/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */
package ph.edu.dlsu.kitane.namesurfer;
import ph.edu.dlsu.kitane.mygraph.myQueueList;
import acm.graphics.*;
import static acm.util.JTFTools.pause;
import java.awt.event.*;
import java.util.*;
import java.awt.*;
import ph.edu.dlsu.kitane.linkedlist.MyLinkedList;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	 * Creates a new NameSurferGraph object that displays the data.
	 */
	/*public NameSurferGraph() {
            addComponentListener(this);
            graphEntry = new MyLinkedList<NameSurferEntry>();
	}*/
        
        /**
	 * Creates a new NameSurferGraph object that displays the data.
         * this is the Queue Version
	 */
	public NameSurferGraph() {
            addComponentListener(this);
            graphEntryQueue = new myQueueList<>();
            graphEntryQueue.createQueue();
            storeData = new myQueueList<>();
            storeData.createQueue();
	}
	
	
	/**
	 * Clears the list of name surfer entries stored inside this class.
	 */
	/*public void clear() {
            for(int i=graphEntry.size();i>=1;i--){
                graphEntry.remove(i);
            }
            update();
	}*/
        
        /**
	 * Clears the list of name surfer entries stored inside this class.
         * This is the Queue Version.
	 */
	public void clear() {
            while(!graphEntryQueue.isEmptyQueue()){
                graphEntryQueue.deleteQueue();
            }
            removeAll();
            drawGraph();
	}
	
	
	/* Method: addEntry(entry) */
	/**
	 * Adds a new NameSurferEntry to the list of entries on the display.
	 * Note that this method does not actually draw the graph, but
	 * simply stores the entry; the graph is drawn by calling update.
	 */
	/*public void addEntry(NameSurferEntry entry) {
            graphEntry.add(graphEntry.size()+1, entry);
	}*/
        
        /* Method: addEntry(entry) */
	/**
	 * Adds a new NameSurferEntry to the list of entries on the display.
	 * Note that this method does not actually draw the graph, but
	 * simply stores the entry; the graph is drawn by calling update.
         * This is the Queue Version.
	 */
	public void addEntry(NameSurferEntry entry) {
            if(graphEntryQueue.isFullQueue()){
                //System.out.println(graphEntryQueue.front());
                //removeEntry(graphEntryQueue.front());
                graphEntryQueue.deleteQueue();
                graphEntryQueue.addQueue(entry);
                //drawEntry(graphEntryQueue.back(),count());
            }else{
                graphEntryQueue.addQueue(entry);
                //System.out.println(graphEntryQueue.back());
                //drawEntry(graphEntryQueue.back(),count());
            }
	}
	
	
	/**
	 * Updates the display image by deleting all the graphical objects
	 * from the canvas and then reassembling the display according to
	 * the list of entries. Your application must call update after
	 * calling either clear or addEntry; update is also called whenever
	 * the size of the canvas changes.
	 */
	/*public void update() {
            removeAll();
            drawGraph();
            
            for(int i=1; i<=graphEntry.size();i++){
                drawEntry(graphEntry.get(i),i);
            }
	}*/
        
        /**
	 * Updates the display image by deleting all the graphical objects
	 * from the canvas and then reassembling the display according to
	 * the list of entries. Your application must call update after
	 * calling either clear or addEntry; update is also called whenever
	 * the size of the canvas changes.
         * This is the Queue Version.
	 */
	public void update() {
            removeAll();
            drawGraph();
            int temp = graphEntryQueue.size();
            System.out.println("temp = "+temp);
            NameSurferEntry[] entry = new NameSurferEntry[4];
            for(int i=0;i<temp;i++){
                entry[i]=graphEntryQueue.front();
                drawEntry(entry[i],i);
                graphEntryQueue.deleteQueue();
            }
            for(int i=0;i<temp;i++){
                System.out.println("entry["+i+"] = "+entry[i].getName());
            }
            for(int i=0;i<temp;i++){
                graphEntryQueue.addQueue(entry[i]);
                System.out.println(graphEntryQueue.back().getName());
                System.out.println(graphEntryQueue.size());
            }
	}
        
        private void drawEntry(NameSurferEntry entry, int color){
            spacing = getWidth()/NDECADES;
            for(int i=0;i<NDECADES-1;i++){
                GLine line = new GLine(spacing*i,value(entry.getRank(i)),spacing*(i+1),value(entry.getRank(i+1)));
                if(color%4==1){
                    line.setColor(Color.BLUE);
                }else if(color%4==2){
                    line.setColor(Color.RED);
                }else if(color%4==3){
                    line.setColor(Color.MAGENTA);
                }else if(color%4==0){
                    line.setColor(Color.BLACK);
                }
                add(line);
            }
            for(int i=0;i<NDECADES;i++){
                String dateRank = "";
                
                if(entry.getRank(i)!=0){
                    dateRank = entry.getName()+" "+entry.getRank(i);
                }else{
                    dateRank = entry.getName()+" *";
                }
                
                GLabel ranking = new GLabel(dateRank,spacing*i,value(entry.getRank(i)));
                if(color%4==1){
                    ranking.setColor(Color.BLUE);
                    GOval circle = new GOval((spacing*i)-3,(value(entry.getRank(i)))-3,6,6);
                    circle.setFilled(true);
                    circle.setFillColor(Color.BLUE);
                    add(circle);
                }else if(color%4==2){
                    ranking.setColor(Color.RED);
                    GRect rekt= new GRect((spacing*i)-3,(value(entry.getRank(i)))-3,6,6);
                    rekt.setFilled(true);
                    rekt.setFillColor(Color.RED);
                    add(rekt);
                }else if(color%4==3){
                    ranking.setColor(Color.MAGENTA);
                    GPolygon pol = new GPolygon((spacing*i),(value(entry.getRank(i))));
                    pol.addVertex(-6/2,0);
                    pol.addVertex(0,-6/2);
                    pol.addVertex(6/2,0);
                    pol.addVertex(0,6/2);
                    pol.setFilled(true);
                    pol.setFillColor(Color.MAGENTA);
                    add(pol);
                }else if(color%4==0){
                    ranking.setColor(Color.BLACK);
                    GPolygon pol = new GPolygon((spacing*i),(value(entry.getRank(i))));
                    pol.addVertex(-4,0);
                    int angle=60;
                    for(int j=0;j<6;j++){
                        pol.addPolarEdge(4,angle);
                        angle-=60;
                    }
                    pol.setFilled(true);
                    pol.setFillColor(Color.BLACK);
                    add(pol);
                }
                add(ranking);
            
            }
        }
        
        /*private void removeEntry(NameSurferEntry entry){
            spacing = getWidth()/NDECADES;
            for(int i=0;i<NDECADES;i++){
                remove(getElementAt(spacing*i,value(entry.getRank(i))));
            }
            for(int i=0;i<NDECADES-1;i++){
                remove(getElementAt(spacing*i,value(entry.getRank(i))));
            }
        }*/
       //for computing the Y-Value of the popularity rank.
        private double value(int rank){
            double dRank = rank;
            if(rank!=0){
                 dRank = dRank/MAX_RANK;
                 dRank = dRank*(getHeight() - 2*GRAPH_MARGIN_SIZE);
                 dRank = dRank + GRAPH_MARGIN_SIZE;
            }else{
                dRank = getHeight()-GRAPH_MARGIN_SIZE;
            }
            return dRank;
        }
	
	void drawGraph(){
            drawHorizontalLines();
            drawVerticalLines();
            drawDateLabels();
        }
        
        private void drawDateLabels(){
            
            spacing = getWidth()/NDECADES;
            for(int i=0;i<NDECADES;i++){
                String date = Integer.toString((i*10) + START_DECADE);
                GLabel dateLabel = new GLabel(date, (i)*spacing,getHeight());
                add(dateLabel);
            }
        }
        
        private void drawHorizontalLines(){
            GLine fLine = new GLine(0,GRAPH_MARGIN_SIZE,getWidth(),GRAPH_MARGIN_SIZE);
            add(fLine);
            GLine sLine = new GLine(0,getHeight()-GRAPH_MARGIN_SIZE,getWidth(),getHeight()-GRAPH_MARGIN_SIZE);
            add(sLine);
        }
        
        private void drawVerticalLines(){
            
            spacing = getWidth()/NDECADES;
            for(int i=0;i<NDECADES;i++){
                GLine vertLine = new GLine((i+1)*spacing,0,(i+1)*spacing,getHeight());
                add(vertLine);
            }
        }
        
        private int count(){
            if(clr==4&&clr==0){
                return clr=0;
            }else{
                return clr++;
            }
        }
        
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
        
        //Variables
        private int clr=0;
        private double spacing;
        private String temp;
        //private MyLinkedList<NameSurferEntry> graphEntry;
        private myQueueList<NameSurferEntry> graphEntryQueue;
        private myQueueList<NameSurferEntry> storeData;
        private GCanvas canvas = new GCanvas();
}
