/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.albaladejo.dfs;
import java.util.ArrayList;
import java.util.Stack;

public class DFS {
    private final int numVertices;
    private final ArrayList<String> vertices;
    private final ArrayList<ArrayList<String>> adj;
    private final ArrayList<String> visited;

    public DFS(int numOfVertices) {
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

    public void iterativeDFS(String startNode) {
        visited.clear();
        if (!vertices.contains(startNode)) {
            System.out.println("error not found");
            return;
        }

        Stack<String> stack = new Stack<>();
        String curr;
        boolean hasadjUNVISITED;
        stack.push(startNode);
        while (!stack.empty()) {
            hasadjUNVISITED = false;
            curr = stack.peek();
            if (!visited.contains(curr)) {
                visited.add(curr);
            }
            for (String element : adj.get(vertices.indexOf(curr))) {
                if (!visited.contains(element)) {
                    stack.push(element);
                    hasadjUNVISITED = true;
                    break;
                }
            }
            if(!hasadjUNVISITED)//if each are visited already
                stack.pop();
        }
    }
    public ArrayList<String> getVisitedNodes() {
        return visited;
    }

    public static void main(String[] args) {
    }

}