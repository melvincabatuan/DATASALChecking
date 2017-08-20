package ph.edu.dlsu.datasal.ang.mygraph;

import ph.edu.dlsu.datasal.ang.myexception.InvalidNodeReferenceException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class Dijkstra {

    private int numOfVertices;
    private ArrayList<String> vertices;
    private ArrayList<HashMap<String, Integer>> adjacency;

    private ArrayList<String> visited;

    // Constructor
    public Dijkstra(int numOfVertices) {
        this.numOfVertices = numOfVertices;
        vertices = new ArrayList<>();
        adjacency = new ArrayList<>(numOfVertices);
        for (int i = 0; i < numOfVertices; i++) {
            adjacency.add(new HashMap<>());
        }
        visited = new ArrayList<>();
    }

    public void addNode(String key) {
        vertices.add(key);
    }

    public void removeNode(String key) {
        vertices.remove(key);
        removeAdjacency(key);
    }

    private void removeAdjacency(String key) {
        for (HashMap<String, Integer> element : adjacency) {
            if (element.containsKey(key)) {
                element.remove(key);
            }
        }
    }

    public HashMap<String, Integer> getAdjacency(String key) {
        keyExistenceCheck(key);
        return adjacency.get(vertices.indexOf(key));
    }

    public void addEdge(String from, String to, int weight) {
        nodeExistenceCheck(from, to);
        if (!(adjacency.get(vertices.indexOf(from)).containsKey(to))) {
            adjacency.get(vertices.indexOf(from)).put(to, weight);
        }
    }

    public void removeEdge(String from, String to) {
        nodeExistenceCheck(from, to);
        adjacency.get(vertices.indexOf(from)).remove(to);
    }

    private void nodeExistenceCheck(String from, String to) {
        if (!vertices.contains(from) || !vertices.contains(to)) {
            throw new InvalidNodeReferenceException("ERROR: Either Node " + from + " or Node " + to + " does not exist");
        }
    }

    private void keyExistenceCheck(String key) {
        if (!vertices.contains(key)) {
            throw new InvalidNodeReferenceException("ERROR: Node " + key + " does not exist");
        }
    }

    public void dijkstraShortesPath() {
        ArrayList<String> unCheckedNodes = new ArrayList<>(vertices);
        ArrayList<Integer> distance = new ArrayList<>(numOfVertices);
        ArrayList<Boolean> isChecked = new ArrayList<>(numOfVertices);
        ArrayList<String> path = new ArrayList<>(numOfVertices);

        System.out.println(unCheckedNodes);

        String source = vertices.get(0);

        // Initialize distances with large value
        for (int i = 0; i < numOfVertices; i++) {
            distance.add(Integer.MAX_VALUE);
            isChecked.add(false);
            path.add(source);
        }

        distance.set(0, 0);          // Source distance initiated at 0, while MAX_VALUE for the rest
        System.out.println("Initialization: " + distance);

        // Iterate over unchecked nodes until the destination is reached
        while (!unCheckedNodes.isEmpty()) {
            int minIndex;
            // Choose the minimum distance as the new predecessor
            try {
                minIndex = minUncheckedIndex(distance, isChecked);
            } catch (Exception e) {
                System.out.println("Path from " + source + " to " + unCheckedNodes + " not found.");
                break;
            }
            String predecessor = vertices.get(minIndex);
            System.out.println("Path from " + source + " to " + predecessor + " is " + path.get(minIndex) + " : Length = " + distance.get(minIndex));
            unCheckedNodes.remove(predecessor);
            isChecked.set(vertices.indexOf(predecessor), true);

            // Update the distances with the addition of new node
            HashMap<String, Integer> predecessorAdjacency = getAdjacency(predecessor);
            int predecessorIndex = vertices.indexOf(predecessor);
            for (String current : predecessorAdjacency.keySet()) {
                int newDistance = distance.get(predecessorIndex) + predecessorAdjacency.get(current);
                int currentIndex = vertices.indexOf(current);
                if (unCheckedNodes.contains(current) && newDistance < distance.get(currentIndex)) {
                    distance.set(currentIndex, newDistance);
                    path.set(currentIndex, path.get(predecessorIndex) + current);
                }
            }
            System.out.println(distance);
            //System.out.println("Path to " + vertices.get(minIndex) + ": " + path.get(minIndex));
        }
    }

    private int minUncheckedIndex(ArrayList<Integer> distance, ArrayList<Boolean> isChecked) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < distance.size(); i++) {
            if (!isChecked.get(i) && distance.get(i) < min) {
                min = distance.get(i);
                minIndex = i;
            }
        }
        if (minIndex < 0 || minIndex >= distance.size()) {
            throw new NoSuchElementException("Error: Element not found.");
        }
        return minIndex;
    }

    private void printShortestDistance(ArrayList<Integer> distance) {
        for (int i = 0; i < distance.size(); i++) {
            System.out.println("The shortest path length from node " + vertices.get(0) + " to node " + vertices.get(i) + " is " + distance.get(i));
        }
    }

    public void displayAdjacency() {
        System.out.println("Adjacency list: ");
        for (int i = 0; i < adjacency.size(); ++i) {
            System.out.println(vertices.get(i) + ": " + adjacency.get(i));
        }
    }
}
