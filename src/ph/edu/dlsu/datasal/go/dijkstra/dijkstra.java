package ph.edu.dlsu.datasal.go.dijkstra;

import java.util.ArrayList;
import java.util.HashMap;

public class dijkstra {

    private final int numVertices;
    private final ArrayList<String> vertices;
    private final ArrayList<HashMap<String, Integer>> adjacencyy;
    private final ArrayList<String> visited;

    public dijkstra(int numOfVertices) {
        numVertices = numOfVertices;
        vertices = new ArrayList<>();
        adjacencyy = new ArrayList<>(numOfVertices);
        for (int i = 0; i < numOfVertices; i++) {
            adjacencyy.add(new HashMap<>());
        }
        visited = new ArrayList<>();
    }

    public void addNode(String data) {
        vertices.add(data);
    }

    public void removeNode(String data) {
        vertices.remove(data);
        removeAdjacency(data);
    }

    public void addEdge(String u, String v, int weight) {
        if (!(adjacencyy.get(vertices.indexOf(u)).containsKey(v))) {
            adjacencyy.get(vertices.indexOf(u)).put(v, weight);
        }
    }

    public void removeEdge(String u, String v) {
        adjacencyy.get(vertices.indexOf(u)).remove(v);
    }

    public HashMap<String, Integer> getAdjacency(String data) {
        return adjacencyy.get(vertices.indexOf(data));
    }

    private void removeAdjacency(String data) {
        for (HashMap<String, Integer> element : adjacencyy) {
            if (element.containsKey(data)) {
                element.remove(data);
            }
        }
    }

    public void dijkstra() {
        ArrayList<String> unChecked = new ArrayList<>(vertices);
        ArrayList<Integer> distance = new ArrayList<>(numVertices);
        ArrayList<String> path = new ArrayList<>(numVertices);

        ArrayList<Boolean> isChecked = new ArrayList<>(numVertices);
        String source = vertices.get(0);

        for (int i = 0; i < numVertices; i++) {
            distance.add(Integer.MAX_VALUE);
            isChecked.add(false);
            path.add(source);
        }

        distance.set(0, 0);
        while (!unChecked.isEmpty()) {
            int minIndex;
            try {
                minIndex = minnotChecked(distance, isChecked);
            } catch (Exception e) {
                System.out.println("error not found");
                break;
            }
            String next = vertices.get(minIndex);
            unChecked.remove(next);
            isChecked.set(vertices.indexOf(next), true);

            HashMap<String, Integer> adjtoNext = getAdjacency(next);
            int indexNext = vertices.indexOf(next);
            for (String current : adjtoNext.keySet()) {
                int newDistance = distance.get(indexNext) + adjtoNext.get(current);
                int currI = vertices.indexOf(current);
                if (unChecked.contains(current) && newDistance < distance.get(currI)) {
                    distance.set(currI, newDistance);
                    path.set(currI, path.get(indexNext) + current);
                }
                //else
            }
        }
    }

    private int minnotChecked(ArrayList<Integer> distance, ArrayList<Boolean> isChecked) {
        int min = Integer.MAX_VALUE;

        int minIndex = -1;
        for (int i = 0; i < distance.size(); i++) {
            if (!isChecked.get(i) && distance.get(i) < min) {
                min = distance.get(i);
                minIndex = i;
            }
        }
        return minIndex;
    }

    public static void main(String[] args) {

    }
}
