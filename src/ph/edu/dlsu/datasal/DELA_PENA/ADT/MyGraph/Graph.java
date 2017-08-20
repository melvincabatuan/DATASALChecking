/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.DELA_PENA.ADT.MyGraph;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author student
 */
public class Graph <E>{
    int MAX_V = 100;
    private ArrayList<E> vertices = new ArrayList<>();
    private int[][] edges = new int[MAX_V][MAX_V];
    int numvertex = 0;
    
    public void Graph(){
        for(int i=0; i<MAX_V; i++){
            for(int j=0; j<MAX_V; j++){
                edges[i][j] = 0;
            }
        }
    }
    
    public void addVertex(E newvert){
        if(!vertices.contains(newvert)){
            vertices.add(newvert);
            numvertex++;
        }
    }
    
    public void addconnection(E tail, E head, int weight){
        if(vertices.contains(tail) && vertices.contains(head)){
            edges[vertices.indexOf(tail)][vertices.indexOf(head)] = weight;
        }
    }
    
    public void removeconnection(E tail, E head){
        if(vertices.contains(tail) && vertices.contains(head)){
            edges[vertices.indexOf(tail)][vertices.indexOf(head)] = 0;
        }
    }
    
    public boolean hasedge(E tail, E head){
        return edges[vertices.indexOf(tail)][vertices.indexOf(head)] > 0;
    }
    
    public int getedgeweight(E tail, E head){
        if(hasedge(tail, head)){
            return edges[vertices.indexOf(tail)][vertices.indexOf(head)];
        }
        else
            return Integer.MAX_VALUE;
    }
    
    public ArrayList getAdjecency(E tail){
        ArrayList<E> nextto = new ArrayList<>();
        int i=vertices.indexOf(tail);
        for(int j=0; j<vertices.size(); j++){
            if(edges[i][j]>0){
                nextto.add(vertices.get(j));
            }
        }
        
        return nextto;
        
    }
    
    public ArrayList getVertices(){
        return vertices;
    }
    
    public int getnumvert(){
        return numvertex;
    }
    public int getIndexV(E vert){
        return vertices.indexOf(vert);
    }
    
    public boolean contains(E vert){
        return vertices.contains(vert);
    }
    
}
