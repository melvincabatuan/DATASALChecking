/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.santos.graph;

/**
 *
 * @author cobalt
 */
public class DijkstraAlgorithmDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DijkstraAlgorithmDemo app = new DijkstraAlgorithmDemo();
        //app.testQ2Number23();
        //app.testJamesDemo();
        app.testJohnDemo();

    }

    // https://youtu.be/8Ls1RqHCOPw
    // Testing distanceedjohn Dijkstra Demo
    private void testJohnDemo() {
        MyDirectedWeightedGraph graph = new MyDirectedWeightedGraph(8);

        // G = {V, E}
        // V: Nodes
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");
        graph.addNode("F");
        graph.addNode("G");
        graph.addNode("H");

        graph.addEdge("A", "B", 20);
        graph.addEdge("A", "D", 80);
        graph.addEdge("A", "G", 90);
        graph.addEdge("B", "F", 10);
        graph.addEdge("C", "D", 10);
        graph.addEdge("C", "F", 50);
        graph.addEdge("C", "H", 20);
        graph.addEdge("D", "C", 10);
        graph.addEdge("D", "G", 20);
        graph.addEdge("E", "B", 50);
        graph.addEdge("E", "G", 30);
        graph.addEdge("F", "C", 10);
        graph.addEdge("F", "D", 40);
        graph.addEdge("G", "A", 20);

        graph.displayAdjacency();

        graph.dijkstraShortesPath();
    }

    // https://youtu.be/k1kLCB7AZbM
    // Testing Joe James Dijkstra Demo
    private void testJamesDemo() {
        MyDirectedWeightedGraph graph = new MyDirectedWeightedGraph(9);

        // G = {V, E}
        // V: Nodes
        graph.addNode("S");
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");
        graph.addNode("F");
        graph.addNode("G");
        graph.addNode("H");

        // E: Edges
        graph.addEdge("S", "A", 2);
        graph.addEdge("S", "D", 20);
        graph.addEdge("A", "E", 3);
        graph.addEdge("E", "B", 1);
        graph.addEdge("E", "G", 6);
        graph.addEdge("E", "H", 4);
        graph.addEdge("G", "D", 2);
        graph.addEdge("B", "C", 7);
        graph.addEdge("C", "F", 5);
        graph.addEdge("F", "B", 0);
        graph.addEdge("H", "E", 2);
        graph.addEdge("H", "G", 1);

        graph.displayAdjacency();

        graph.dijkstraShortesPath();
    }

    private void testQ2Number23() {
        MyDirectedWeightedGraph graph = new MyDirectedWeightedGraph(9);

        // G = {V, E}
        // V: Nodes
        graph.addNode("S");
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");
        graph.addNode("F");
        graph.addNode("G");
        graph.addNode("T");

        // E: Edges
        graph.addEdge("S", "A", 4);
        graph.addEdge("S", "B", 3);
        graph.addEdge("S", "D", 7);
        graph.addEdge("B", "S", 3);
        graph.addEdge("B", "D", 4);
        graph.addEdge("S", "A", 4);
        graph.addEdge("A", "C", 1);
        graph.addEdge("C", "D", 3);
        graph.addEdge("C", "E", 1);
        graph.addEdge("D", "E", 1);
        graph.addEdge("D", "F", 5);
        graph.addEdge("D", "T", 3);
        graph.addEdge("E", "G", 2);
        graph.addEdge("E", "T", 4);
        graph.addEdge("G", "E", 2);
        graph.addEdge("G", "T", 3);
        graph.addEdge("T", "F", 5);

        graph.displayAdjacency();

        graph.dijkstraShortesPath();
    }

}
