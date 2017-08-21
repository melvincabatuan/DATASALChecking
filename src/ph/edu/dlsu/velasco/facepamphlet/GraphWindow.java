package ph.edu.dlsu.velasco.facepamphlet;

/**
 * @author NeilOliver
 */
import acm.graphics.*;
import acm.program.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.*;
import java.util.*;
import ph.edu.dlsu.velasco.mygraph.*;

public class GraphWindow extends GraphicsProgram {

    private FacePamphletDatabase database;
    private myGraph graph;
    private GOval[] nodes;
    private GLine[] edges;
    private ArrayList<String> nodeList;
    private ArrayList<myEdge> edgeList;

    private final double RADIUS = 75 / 2;
    public final int APPLICATION_WIDTH = 500;
    public final int APPLICATION_HEIGHT = 600;

    public GraphWindow(myGraph graph, FacePamphletDatabase database) {
        this.database = database;
        this.graph = graph;
        edgeList = graph.getAllEdges();
        nodeList = new ArrayList<>();
    }

    public Component displayGraph() {
        GCanvas canvas = new GCanvas();
        nodes = new GOval[database.getSize()];
        edges = new GLine[graph.getNumberOfEdges()];
        setNodes(database.getAllNames());
        int x = 50;
        int y = 50;
        int size = database.getSize();
        for (int i = 0; i < size; i++) {
            nodes[i] = new GOval(RADIUS * 2, RADIUS * 2);
            canvas.add(nodes[i], x, y + (i * 100));
            GLabel label = new GLabel(nodeList.get(i));
            label.setFont("MONTSERRAT-9");
            canvas.add(label, (nodes[i].getX() + (nodes[i].getWidth() / 2)) - (label.getWidth() / 2),
                    nodes[i].getY() + (nodes[i].getHeight() / 2));
            if (i % 4 == 0 && i != 0) {
                x = x + 100;
            }
        }
        for (int i = 0; i < edgeList.size(); i++) {
            myEdge edge = edgeList.get(i);
            int index1 = getNodeIndex(edge.v1());
            int index2 = getNodeIndex(edge.v2());
            edges[i] = new GLine(nodes[index1].getX() + RADIUS, nodes[index1].getY() + RADIUS,
                    nodes[index2].getX() + RADIUS, nodes[index2].getY() + RADIUS);
            edges[i].setColor(Color.BLACK);
            canvas.add(edges[i]);
        }
        return canvas;
    }

    public void setNodes(Iterator<String> names) {
        while (names.hasNext()) {
            nodeList.add(names.next());
        }
    }

    public int getNodeIndex(String name) {
        return nodeList.indexOf(name);
    }

    public void mousePressed(MouseEvent e) {
        if (getElementAt(e.getX(), e.getY()) != null) {
            getElementAt(e.getX(), e.getY()).move(e.getX(), e.getY());
        }
    }

    public void init() {
        displayGraph();
        addMouseListeners();
    }

}
