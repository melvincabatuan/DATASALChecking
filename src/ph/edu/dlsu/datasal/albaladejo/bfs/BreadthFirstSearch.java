/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.albaladejo.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class BreadthFirstSearch{

    private int numVertices;
    private ArrayList<String> vertices;
    private ArrayList<ArrayList<String>> adj; //adjacency
    private ArrayList<String> visited;

    public BreadthFirstSearch(int numOfVertices) {
        this.numVertices = numOfVertices;
        vertices = new ArrayList<>();
        adj = new ArrayList<>(numOfVertices);
        for (int i = 0; i < numOfVertices; i++) {
            adj.add(new ArrayList<>());
        }
        visited = new ArrayList<>();
    }

    public void initNodes() {
        char alph = 'A';
        for (int i = 0; i < numVertices; ++i) {
            vertices.add(Character.toString(alph++));
        }
    }

    public void addEdge(String u, String v) {
        if (vertices.contains(u) && vertices.contains(v)) {
            if (!(adj.get(vertices.indexOf(u)).contains(v))) {
                adj.get(vertices.indexOf(u)).add(v);
            }
            if (!(adj.get(vertices.indexOf(v)).contains(u))) {
                adj.get(vertices.indexOf(v)).add(u);
            }
        } else {
            System.out.println("error not found");
        }
    }

    public void removeEdge(String u, String v) {
        if (vertices.contains(u) && vertices.contains(v)) {
            adj.get(vertices.indexOf(u)).remove(v);
            adj.get(vertices.indexOf(v)).remove(u);
        } else {
            System.out.println("error not found");
        }
    }

    public void iterativeBFS(String startNode) {
        visited.clear();
        if (!vertices.contains(startNode)) {
            System.out.println("error not found");
            return;
        }
        Queue<String> queue = new ArrayDeque<>();
        String curr;
        queue.add(startNode);
        while(!queue.isEmpty()) {
            curr = queue.remove();
            if (!visited.contains(curr)) {
                visited.add(curr);
            }
            for (String element : adj.get(vertices.indexOf(curr))) {
                if (!visited.contains(element)) {
                    visited.add(element);
                    queue.add(element);
                }
            }
        }
    }
    public ArrayList<String> getVisitedNodes() {
        return visited;
    }

    public static void main(String[] args) {
    }

}