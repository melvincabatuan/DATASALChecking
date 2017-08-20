/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.DELA_PENA.ADT.MyGraph;
import java.util.*;
/**
 *
 * @author Paolo
 */
public class GraphDP <E>{
    private final Map<E, ArrayList> nodes = new HashMap<>();
    private int numnodes = 0;
    private boolean directed = false;
    
    public void addnode(E newnode){
        if(!nodeExists(newnode)){
            ArrayList<E> newlist = new ArrayList<>();
            nodes.put(newnode, newlist);
            numnodes++;
        }
    }
    public void removenode(E remnode){
        if(nodeExists(remnode)){
            nodes.remove(remnode);
            numnodes--;
        }
    }
    
    public void addconnection(E node, E neighbor){
        if(nodeExists(node)){
            if(!hasconnection(node, neighbor)){
                nodes.get(node).add(neighbor);
                if(!directed)
                    nodes.get(neighbor).add(node);
            }
        }
    }
    
    public int getNumNodes(){
        return numnodes;
    }
    
    public Iterator getNodes(){
        return nodes.keySet().iterator();
    }
    
    public ArrayList getConnections(E node){
        return nodes.get(node);
    }
    
    public void removeconnection(E node, E neighbor){
        if(nodeExists(node)){
            if(hasconnection(node, neighbor)){
                nodes.get(node).remove(neighbor);
            }
        }
    }
    
    public boolean nodeExists(E node){
        return nodes.containsKey(node);
    }
    
    public boolean hasconnection(E node, E neighbor){
        if(nodeExists(node))
            return nodes.get(node).contains(neighbor);
        else
            return false;
    }
    
    public void setdirected(boolean truth){
        directed = truth;
    }
}
