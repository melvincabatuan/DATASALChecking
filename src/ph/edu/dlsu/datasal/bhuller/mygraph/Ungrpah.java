/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.bhuller.mygraph;


import java.util.ArrayDeque;
import java.util.Stack;
import java.util.Queue;
import java.util.ArrayList;


/**
 *
 * @author student
 */
public class Ungrpah{
    private int numOfVertices;
    private ArrayList<String> vertices;
    private ArrayList<ArrayList<String>> adjacency;

    private ArrayList<String> visited;
    
    public Ungrpah(int numOfVertices) {
        this.numOfVertices = numOfVertices;
        vertices = new ArrayList<>();
        adjacency = new ArrayList<>(numOfVertices);
        for (int i = 0; i < numOfVertices; i++) {
            adjacency.add(new ArrayList<>());
        }
        visited = new ArrayList<>();
    }

    public void initnnames() {
        char alphabet = 'A';
        for (int i = 0; i < numOfVertices; ++i) {
            vertices.add(Character.toString(alphabet++));
        }
        System.out.println("Nodes are: " + vertices);
    }

    public void addEdge(String fir, String sec) {
        if (vertices.contains(fir) && vertices.contains(sec)) {
            if (!(adjacency.get(vertices.indexOf(fir)).contains(sec))) {
                adjacency.get(vertices.indexOf(fir)).add(sec);
            }
            if (!(adjacency.get(vertices.indexOf(sec)).contains(fir))) {
                adjacency.get(vertices.indexOf(sec)).add(fir);
            }
        } else {
            System.out.println("Error");
        }
    }

    public void removeEdge(String fir, String sec) {
        if (vertices.contains(fir) && vertices.contains(sec)) {
            adjacency.get(vertices.indexOf(fir)).remove(sec);
            adjacency.get(vertices.indexOf(sec)).remove(fir);
        } else {
            System.out.println("Error");
        }
    }
    
      public void dfs(String start) {
        visited.clear();
        if (!vertices.contains(start)) {
            System.out.println("Node " + start + " not found.");
            return;
        }

        Stack<String> sta = new Stack<>();
        String current;
        boolean hasAdjacentUnvisited;

        sta.push(start);
        while (!sta.empty()) {
            hasAdjacentUnvisited = false;
            current = sta.peek();
            if (!visited.contains(current)) {
                visited.add(current);
            }
            System.out.println("Visited: " + visited);
            for (String element : adjacency.get(vertices.indexOf(current))) {
                if (!visited.contains(element)) {
                    sta.push(element);
                    hasAdjacentUnvisited = true;
                    break;
                }
            }
            if (!hasAdjacentUnvisited) {
                sta.pop();
            }
        }
        System.out.println("Stack is empty");
    }

    public void bfs(String start) {
        visited.clear();
        if (!vertices.contains(start)) {
            System.out.println("Node " + start + " not found.");
            return;
        }

        Queue<String> sta = new ArrayDeque<>();
        String current;

        sta.add(start);
        while (!sta.isEmpty()) {
           
            current = sta.remove();
            if (!visited.contains(current)) {
                visited.add(current);
            }
            System.out.println("Visited: " + visited);
            for (String element : adjacency.get(vertices.indexOf(current))) {
                if (!visited.contains(element)) {
                    visited.add(element);
                    sta.add(element);
                }
            }
        }
        System.out.println("Queue is empty");
    }
    
    public ArrayList<String> visited() {
        return visited;
    }

    public void displayadj() {
        System.out.println("Adjacency: ");
        for (int i = 0; i < adjacency.size(); ++i) {
            System.out.println(vertices.get(i) + ": " + adjacency.get(i));
        }
    }
    
    public static void main(String[] args) {

    }

}

