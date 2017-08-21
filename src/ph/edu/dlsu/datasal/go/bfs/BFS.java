/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.go.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class BFS {

    private int numVertices;
    private ArrayList<String> vertices;
    private ArrayList<ArrayList<String>> adjacencyy;
    private ArrayList<String> visited;

    public BFS(int numOfVertices) {
        this.numVertices = numOfVertices;
        vertices = new ArrayList<>();
        adjacencyy = new ArrayList<>(numOfVertices);
        for (int i = 0; i < numOfVertices; i++) {
            adjacencyy.add(new ArrayList<>());
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
            if (!(adjacencyy.get(vertices.indexOf(u)).contains(v))) {
                adjacencyy.get(vertices.indexOf(u)).add(v);
            }
            if (!(adjacencyy.get(vertices.indexOf(v)).contains(u))) {
                adjacencyy.get(vertices.indexOf(v)).add(u);
            }
        } else {
            System.out.println("error not found");
        }
    }

    public void removeEdge(String u, String v) {
        if (vertices.contains(u) && vertices.contains(v)) {
            adjacencyy.get(vertices.indexOf(u)).remove(v);
            adjacencyy.get(vertices.indexOf(v)).remove(u);
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
            for (String element : adjacencyy.get(vertices.indexOf(curr))) {
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
