/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.reyes.datasalapp.graphs;
import ph.edu.dlsu.datasal.reyes.datasalapp.graph.*;
import ph.edu.dlsu.datasal.reyes.datasalapp.queue.queue;
import java.util.ArrayList;

public class bfs {
    private Graph<String> graph = new Graph<>();
     public void init(){
        graph.addVertex("A");
        graph.addVertex("C");
        graph.addVertex("E");
        graph.addVertex("G");
        graph.addVertex("S");
        graph.addVertex("D");
        graph.addVertex("T");
        graph.addVertex("B");
        graph.addVertex("F");
        
        graph.addconnection("A", "C", 1);
        graph.addconnection("C", "E", 1);
        graph.addconnection("C", "D", 3);
        graph.addconnection("E", "G", 2);
        graph.addconnection("E", "T", 4);
        graph.addconnection("G", "E", 2);
        graph.addconnection("S", "A", 4);
        graph.addconnection("S", "D", 7);
        graph.addconnection("S", "B", 3);
        graph.addconnection("D", "E", 1);
        graph.addconnection("D", "F", 5);
        graph.addconnection("D", "T", 3);
        graph.addconnection("T", "F", 5);
        graph.addconnection("B", "S", 3);
        graph.addconnection("B", "D", 4);
    }
    public void iterativeBFS(String start) {
        queue<String> queue = new queue<>();
        ArrayList<String> visited = new ArrayList<>();
        String current;
        boolean hasAdjacentUnvisited;
        queue.enqueue(start);
        while (!queue.isEmpty()) {
            System.out.println("Queue: " + queue);
            hasAdjacentUnvisited = false;
            current = queue.dequeue();
            if (!visited.contains(current)) {
                visited.add(current);
            }
            System.out.println("Visited: " + visited);
            // adjacency not found
//            for (String element : adjacency.get(vertices.indexOf(current))) {
//                if (!visited.contains(element)) {
//                    visited.add(element);
//                    queue.queue(element);
//                }
//            }
        }
        System.out.println("the queue is empty");
    }
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
