package ph.edu.dlsu.velasco.mygraph;

import java.util.*;

/**
 *
 * @author NeilOliver
 */
public class myGraph {

    private int numOfVertex;
    private ArrayList<ArrayList<String>> adjacency;
    private ArrayList<String> storedVertex;
    private ArrayList<myEdge> edgeList;

    public myGraph(int numOfVertices) {
        numOfVertex = numOfVertices;
        adjacency = new ArrayList<>(numOfVertices);
        storedVertex = new ArrayList<>();
        edgeList = new ArrayList<>();
        for (int i = 0; i < numOfVertices; i++) {
            adjacency.add(new ArrayList<>());
        }
    }

    /*
     * creates an edge without a weight. Weight is default set to 1.
     */
    public void addEdge(String vert1, String vert2) {
        if (!storedVertex.contains(vert1)) {
            storedVertex.add(vert1);
        }
        if (!storedVertex.contains(vert2)) {
            storedVertex.add(vert2);
        }
        if (adjacency.get(storedVertex.indexOf(vert1)).isEmpty() == false) {
            if (adjacency.get(storedVertex.indexOf(vert1)).contains(vert2)) {
                System.out.println("Already has an edge");
                return;
            }
        }
        myEdge newEdge = new myEdge(vert1, vert2, 1);
        edgeList.add(newEdge);
        adjacency.get(storedVertex.indexOf(vert1)).add(vert2);
        adjacency.get(storedVertex.indexOf(vert2)).add(vert1);
    }

    public void addEdge(String vert1, String vert2, int weight) {
        if (!storedVertex.contains(vert1)) {
            storedVertex.add(vert1);
        }
        if (!storedVertex.contains(vert2)) {
            storedVertex.add(vert2);
        }
        if (!adjacency.get(storedVertex.indexOf(vert1)).isEmpty()) {
            if (adjacency.get(storedVertex.indexOf(vert1)).contains(vert2)) {
                System.out.println("Already has an edge");
                return;
            }
        }
        myEdge newEdge = new myEdge(vert1, vert2, weight);
        edgeList.add(newEdge);
        adjacency.get(storedVertex.indexOf(vert1)).add(vert2);
        adjacency.get(storedVertex.indexOf(vert2)).add(vert1);
    }

    public myEdge getEdge(String vert1, String vert2) {
        int index = indexOfEdges(vert1, vert2);
        return edgeList.get(index);
    }

    public int getNumberOfEdges() {
        return edgeList.size();
    }

    public ArrayList<myEdge> getAllEdges() {
        return edgeList;
    }

    private int indexOfEdges(String vert1, String vert2) {
        for (int i = 0; i < edgeList.size(); i++) {
            myEdge edge = edgeList.get(i);
            if (edge.v1() == vert1 && edge.v2() == vert2) {
                return i;
            }
        }
        return -1;
    }

    public void removeEdge(String vert1, String vert2) {
        if (storedVertex.contains(vert1) && storedVertex.contains(vert2)) {
            adjacency.get(storedVertex.indexOf(vert1)).remove(vert2);
            adjacency.get(storedVertex.indexOf(vert2)).remove(vert1);
        }
    }

    public void depthFirstSearch(String first) {
        ArrayList<String> visited = new ArrayList<>();
        boolean hasAdjacentVisited;
        Stack<String> stack = new Stack<>();
        String current;

        if (!storedVertex.contains(first)) {
            System.out.println("The vertex does not exist");
            return;
        }
        stack.push(first);
        while (!stack.isEmpty()) {
            hasAdjacentVisited = false;
            current = stack.peek();
            if (!visited.contains(current)) {
                visited.add(current);
            }
            for (String element : adjacency.get(storedVertex.indexOf(current))) {
                if (!visited.contains(element)) {
                    stack.push(element);
                    hasAdjacentVisited = true;
                    break;
                }
            }
            if (!hasAdjacentVisited) {
                stack.pop();
            }
        }
    }

    public void breadthFirstSearch(String first) {
        ArrayList<String> visited = new ArrayList<>();
        Queue<String> queue = new ArrayDeque<>();
        String current;

        if (!storedVertex.contains(first)) {
            System.out.println("The vertex does not exist");
            return;
        }
        queue.add(first);
        while (!queue.isEmpty()) {
            current = queue.poll();
            if (!visited.contains(current)) {
                visited.add(current);
            }
            for (String element : adjacency.get(storedVertex.indexOf(current))) {
                if (!visited.contains(element)) {
                    queue.add(element);
                }
            }
        }
    }

}
