/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.kitane.myArrayGraph;

import acm.program.*;

/**
 *
 * @author ChristophJohnEric
 */
public class TestGraph extends ConsoleProgram {

    public static void main(String[] args) {
        new TestGraph().start(args);
    }

    public void run() {
        myGraphKitsUnweighted<String> graph;
        graph = new myGraphKitsUnweighted<>(7);

        graph.addVertex("Do");
        println("Do is a vertex? " + graph.containsVertex("Do"));

        graph.addVertex("Re");
        println("Re is a vertex? " + graph.containsVertex("Re"));

        graph.addVertex("Mi");
        println("Mi is a vertex? " + graph.containsVertex("Mi"));

        graph.addVertex("Fa");
        println("Fa is a vertex? " + graph.containsVertex("Fa"));

        graph.addVertex("So");
        println("So is a vertex? " + graph.containsVertex("So"));

        graph.addVertex("La");
        println("La is a vertex? " + graph.containsVertex("La"));

        graph.addVertex("Ti");
        println("Ti is a vertex? " + graph.containsVertex("Ti"));

        for (int i = 0; i < 7; i++) {
            println(graph.getVertex(i) + ":" + graph.getAdjacency(graph.getVertex(i)));
        }

        graph.addEdge("Do", "Re");
        
        graph.addEdge("Re", "Mi");
    
        graph.addEdge("Mi", "Fa");
    
        graph.addEdge("Fa", "So");
    
        graph.addEdge("So", "La");
     
        graph.addEdge("La", "Ti");
        for (int i = 0; i < 7; i++) {
            println(graph.getVertex(i) + ":" + graph.getAdjacency(graph.getVertex(i)));
        }
        //graph.removeVertex("Re");
        graph.removeEdge("Re", "Mi");
        graph.removeEdge("Fa", "So");
        println("Re is a vertex? " + graph.containsVertex("Re") + " " + graph.getAdjacency("Mi"));
        for (int i = 0; i < graph.size(); i++) {
            println(graph.getVertex(i) + ":" + graph.getAdjacency(graph.getVertex(i)));
        }

    }
}
