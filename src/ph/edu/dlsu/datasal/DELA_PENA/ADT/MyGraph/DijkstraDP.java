/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.DELA_PENA.ADT.MyGraph;
import acm.program.ConsoleProgram;
import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author student
 */
public class DijkstraDP <E> extends ConsoleProgram{
    private Graph<String> graph = new Graph<>();
    private int[] currentdistance;
    private String[] parent;
    private String Vstart = "S";
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
        
        currentdistance = new int[graph.getnumvert()];
        parent = new String[graph.getnumvert()];
        for(int i=0; i>graph.getnumvert(); i++){
            currentdistance[i] = Integer.MAX_VALUE;
        }
        for(int i=0; i>graph.getnumvert(); i++){
            parent[i] = "";
        }
    }
    public void run(){
        println(runsearch(Vstart));
        
    }
    public ArrayList runsearch(String start){
        currentdistance[graph.getIndexV(start)] = 0;
        String current = start;
        ArrayList tobechecked = graph.getVertices();
        tobechecked.remove(start);
        ArrayList<String> result = new ArrayList<>();
        result.add(start);
        while(!tobechecked.isEmpty()){
            Iterator BigV = result.iterator();
            while(BigV.hasNext()){
                String v = (String)BigV.next();
                Iterator BigU = graph.getAdjecency(v).iterator();
                while(BigU.hasNext()){
                    String u = (String)BigU.next();
                    if(currentdistance[graph.getIndexV(u)] > currentdistance[graph.getIndexV(v)] + graph.getedgeweight(v, u)){
                        currentdistance[graph.getIndexV(u)] = currentdistance[graph.getIndexV(v)] + graph.getedgeweight(v, u);
                        parent[graph.getIndexV(u)] = v;
                    }
                }
            }
        }
        return result;
    }
}
