/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.DELA_PENA.ADT.MyGraph;
import ph.edu.dlsu.datasal.DELA_PENA.ADT.MyGraph.Graph;
import acm.program.ConsoleProgram;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
/**
 *
 * @author student
 */
public class DFS extends ConsoleProgram{
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
    
    public void run(){
        println("Vertices: " + graph.getVertices());
        println(runDFS("S"));
    }
    
    public ArrayList runDFS(String start){
        boolean hasunvisitedadjecent = false;
        ArrayList<String> result = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        if(graph.contains(start)){
            result.add(start);
            stack.push(start);
            while(!stack.isEmpty()){
                String current = stack.peek();
                hasunvisitedadjecent = false;
                Iterator it = graph.getAdjecency(current).iterator();
                println(current+ " = " + graph.getAdjecency(current));
                while(it.hasNext()){
                    String v=(String)it.next();
                    if(!result.contains(v)){
                        result.add(v);
                        stack.push(v);
                        hasunvisitedadjecent=true;
                        break;
                    }
                }
                if(!hasunvisitedadjecent){
                    current = stack.pop();
                    
                }
            }
        }
       
        
        return result;
    }
    
    public static void main(String[] args){
        new DFS().start(args);
    }
}
