/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.DELA_PENA.ADT.MyGraph;

/**
 *
 * @author student
 */
public class Edge <E>{
    private E head = null;
    private int weight = 1;
    
    Edge(E newhead, int newweight){
        head = newhead;
        weight = newweight;
    }
    public void sethead(E newhead){
        head=newhead;
    }
    public void setweight(int newweight){
        weight = newweight;
    }
    
    public E gethead(){
        return head;
    }
    public int getweight(){
        return weight;
    }
}
