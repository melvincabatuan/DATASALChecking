/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.bhuller.mygraph;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author student
 */
public class Dgraph {
     private int numOfVertices;
    private ArrayList<String> vertices;
    private ArrayList<HashMap<String, Integer>> adj;
    private ArrayList<String> visited;

    public Dgraph(int numOfVertices) {
        this.numOfVertices = numOfVertices;
        vertices = new ArrayList<>();
        visited = new ArrayList<>();
        adj = new ArrayList<>(numOfVertices);
        for (int i = 0; i < numOfVertices; i++) {
            adj.add(new HashMap<>());
        } 
    }

    public void addNode(String item) {
        vertices.add(item);
    }

    public void remnode(String item) {
        vertices.remove(item);
        removeadj(item);
    }

    private void removeadj(String item) {
        for (HashMap<String, Integer> element : adj) {
            if (element.containsKey(item)) {
                element.remove(item);
            }
        }
    }

    public HashMap<String, Integer> getAdjacency(String key) {
        if(!vertices.contains(key)){
            return null;
        }
        return adj.get(vertices.indexOf(key));
    }

    public void addEdge(String fir, String sec, int wht) {
         if (vertices.contains(fir) && vertices.contains(sec)){
        if (!(adj.get(vertices.indexOf(fir)).containsKey(sec))) {
            adj.get(vertices.indexOf(fir)).put(sec, wht);
        }
         }
    }

    public void removeEdge(String fir, String sec) {
         if (vertices.contains(fir) && vertices.contains(sec)){
        adj.get(vertices.indexOf(fir)).remove(sec);
    }
    }
    
     private int minUncheckedIndex(ArrayList<Integer> dist, ArrayList<Boolean> ische) {
        int min = Integer.MAX_VALUE;
        int minind = -1;
        for (int i = 0; i < dist.size(); i++) {
            if (!ische.get(i) && dist.get(i) < min) {
                min = dist.get(i);
                minind = i;
            }
        }
        if (minind >= dist.size()) {
          System.out.println("Error");
        }
        return minind;
    }
     
      public void dijkstraShortesPath() {
        ArrayList<String> unCheckedNodes = new ArrayList<>(vertices);
        ArrayList<Integer> distance = new ArrayList<>(numOfVertices);
        ArrayList<Boolean> isChecked = new ArrayList<>(numOfVertices);
        ArrayList<String> path = new ArrayList<>(numOfVertices);

        System.out.println(unCheckedNodes);

        String source = vertices.get(0);

        
        for (int i = 0; i < numOfVertices; i++) {
            distance.add(Integer.MAX_VALUE);
            isChecked.add(false);
            path.add(source);
        }

        distance.set(0, 0);         

        while (!unCheckedNodes.isEmpty()) {
            int minIndex;
       
            try {
                minIndex = minUncheckedIndex(distance, isChecked);
            } catch (Exception e) {
                System.out.println("Path not found.");
                break;
            }
            String pred = vertices.get(minIndex);
            unCheckedNodes.remove(pred);
            isChecked.set(vertices.indexOf(pred), true);

          
            HashMap<String, Integer> predadj = getAdjacency(pred);
            int predint = vertices.indexOf(pred);
            for (String cur : predadj.keySet()) {
                int newDistance = distance.get(predint) + predadj.get(cur);
                int curIndex = vertices.indexOf(cur);
                if (unCheckedNodes.contains(cur) && newDistance < distance.get(curIndex)) {
                    distance.set(curIndex, newDistance);
                    path.set(curIndex, path.get(predint) + cur);
                }
            }
            System.out.println(distance);
 
        }
    }
    public void display() {
        System.out.println("Adjacency: ");
        for (int i = 0; i < adj.size(); ++i) {
            System.out.println(vertices.get(i) + "= " + adj.get(i));
        }
    }
}
