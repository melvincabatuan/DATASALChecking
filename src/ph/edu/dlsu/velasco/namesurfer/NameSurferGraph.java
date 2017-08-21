package ph.edu.dlsu.velasco.namesurfer;

/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */
import acm.graphics.*;
import static acm.program.Program.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;
import static java.awt.Color.*;
import javax.swing.*;

public class NameSurferGraph extends GCanvas implements NameSurferConstants, ComponentListener {

    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    private final int X_OFFSET = 5;
    private final int X_SPACING = 65;
    private double height;
    private int counter=1;

    private GLine upBorder = new GLine(X_OFFSET, GRAPH_MARGIN_SIZE, APPLICATION_WIDTH - (3 * X_OFFSET), GRAPH_MARGIN_SIZE);
    private GLine lowBorder = new GLine(X_OFFSET, APPLICATION_HEIGHT - GRAPH_MARGIN_SIZE - 100, APPLICATION_WIDTH - (2 * X_OFFSET), APPLICATION_HEIGHT - GRAPH_MARGIN_SIZE - 100);

    private int[] rankDecades = new int[NDECADES];
    private String[] decadesLabel = new String[NDECADES];

    private ArrayList<GLine[]> graphLine;
    private ArrayList<GLabel[]> rankLabel;

    public NameSurferGraph() {
        add(upBorder);
        add(lowBorder);
        createGrid();
        graphLine = new ArrayList<>();
        rankLabel = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            graphLine.add(new GLine[NDECADES]);
            rankLabel.add(new GLabel[NDECADES]);
        }
    }

    public void createGrid() {
        for (int i = 0; i < NDECADES; i++) {
            GLine divider = new GLine(i * X_SPACING + X_OFFSET, upBorder.getY(), i * X_SPACING + X_OFFSET, lowBorder.getY());
            height = divider.getHeight();
            GLabel decade = new GLabel("" + (START_DECADE + (i * 10)));
            add(divider);
            decade.setFont("MONTSERRAT-12");
            add(decade, (2 * X_OFFSET) + (i * X_SPACING), APPLICATION_HEIGHT - GRAPH_MARGIN_SIZE - 80);
        }
    }

    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        removeAll();
        add(upBorder);
        add(lowBorder);
        createGrid();
    }

    /* Method: addEntry(entry) */
    /**
     * Adds a new NameSurferEntry to the list of entries on the display. Note
     * that this method does not actually draw the graph, but simply stores the
     * entry; the graph is drawn by calling update.
     */
    public Color setColor(int number) {
        switch (number) {
            case 1:
                return BLUE;
            case 2:
                return RED;
            case 3:
                return DARK_GRAY;
            case 4:
                return ORANGE;
        }
        return BLACK;
    }

    public void addEntry(NameSurferEntry entry, int number) {
        System.out.println("" + number);
        for (int i = 0; i < NDECADES; i++) {
            rankDecades[i] = entry.getRank(i);
        }
        for (int i = 1; i < NDECADES; i++) {
            if (rankDecades[i - 1] == 0) {
                rankDecades[i - 1] = 1000;
            }
            if (rankDecades[i] == 0) {
                rankDecades[i] = 1000;
            }
            GLine line = graphLine.get(number)[i];
            line = new GLine((i - 1) * X_SPACING + X_OFFSET, rankDecades[i - 1]
                    * (height / MAX_RANK) + GRAPH_MARGIN_SIZE, (i) * X_SPACING + X_OFFSET,
                    rankDecades[i] * (height / MAX_RANK) + GRAPH_MARGIN_SIZE);
            line.setColor(setColor(number));
            graphLine.get(number)[i] = line;
            add(line);
        }
        for (int i = 1; i <= NDECADES; i++) {
            GLabel rank = rankLabel.get(number)[i - 1];
            rank = new GLabel("");
            rank.setColor(setColor(number));
            decadesLabel[i - 1] = "" + entry.getRank(i - 1);
            if (entry.getRank(i - 1) == 0) {
                decadesLabel[i - 1] = "*";
            }
            rank.setLabel("" + decadesLabel[i - 1] + ", " + entry.getName());
            if (entry.getRank(i - 1) > 950 || entry.getRank(i - 1) == 0) {
                rank.setLocation((i - 1) * X_SPACING + X_OFFSET,
                        rankDecades[i - 1] * (height / MAX_RANK) + GRAPH_MARGIN_SIZE - (rank.getHeight() / 2));
                rankLabel.get(number)[i - 1] = rank;
                add(rank);
            } else if (entry.getRank(i - 1) > 0 && entry.getRank(i - 1) < 50) {
                rank.setLocation((i - 1) * X_SPACING + X_OFFSET,
                        rankDecades[i - 1] * (height / MAX_RANK) + GRAPH_MARGIN_SIZE + rank.getHeight());
                rankLabel.get(number)[i - 1] = rank;
                add(rank);
            } else {
                rank.setLocation((i - 1) * X_SPACING + X_OFFSET,
                        rankDecades[i - 1] * (height / MAX_RANK) + GRAPH_MARGIN_SIZE + (rank.getHeight() / 2));
                rankLabel.get(number)[i - 1] = rank;
                add(rank);
            }
        }
    }

    public void removeEntry(NameSurferEntry entry) {
        if(counter%5 == 0){
            counter = 1;
        }
        for (int i = 1; i < graphLine.get(counter).length; i++) {
            remove(graphLine.get(counter)[i]);
            graphLine.get(counter)[i] = null;
        }
        for (int j = 1; j <= rankLabel.get(counter).length; j++) {
            remove(rankLabel.get(counter)[j - 1]);
            rankLabel.get(counter)[j-1] = null;
        }
        counter++;
        
    }
    

    /**
     * Updates the display image by deleting all the graphical objects from the
     * canvas and then reassembling the display according to the list of
     * entries. Your application must call update after calling either clear or
     * addEntry; update is also called whenever the size of the canvas changes.
     */
    public void update() {
        // You fill this in //
    }

    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        update();
    }

    public void componentShown(ComponentEvent e) {
    }
}
