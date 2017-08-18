package ph.edu.dlsu.datasal.chan.mygraph;

/* © 2017 by Patrick Matthew Chan */

import acm.graphics.*;
import acm.io.*;
import acm.program.*;
import acm.util.*;
import java.io.*;
import java.applet.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
/* @author Patrick Matthew J. Chan [LBYCP12-EQ1]*/
public class GraphTest /*extends GraphicsProgram*/{
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~ Main Classes ~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    //main classes
    public static void main(String[] args) {
        GraphA<String> g=new GraphA(10);
        g.addNode("A");
        g.addNode("B");
        g.addNode("C");
        g.addNode("D");
        g.addNode("E");
        g.addNode("F");
        g.addNode("G");
        g.addNode("T");
        g.addNode("S");
        g.connect("A","C", 1);
        g.connect("B","D",4);
        g.connect("B","S", 3);
        g.connect("S","B", 3);
        g.connect("S","D", 7);
        g.connect("S","A", 4);
        g.connect("C","D", 3);
        g.connect("C","E", 1);
        g.connect("D","E", 1);
        g.connect("D","F", 5);
        g.connect("D","T", 3);
        g.connect("E","T", 4);
        g.connect("T","F", 5);
        g.connect("G","T", 3);
        g.connect("E","G", 2);
        g.connect("G","E", 2);
        System.out.println(g+"=======DJK=======");
        //System.out.println(g.weight("A","S"));
        
        DIJKSTRA(g,"S");
        System.out.println("djk done!");
        String temp="T";
        while(temp!="S"){
            System.out.print(temp+"<-");
            temp=g.getDpar(temp);
        }
        System.out.println(temp);
        //System.out.println("DONE!\n\n");
        System.out.println("=======DFS=======");
        
        DFS(g, "S");
    }
        /*new GraphTest().start(args);
        }
        public void init(){
        ;
        }
        public void run(){
        ;
        }*/
    //~~~~~~~~~~~~~~~~~~~~~~~~~ Debugging & Misc ~~~~~~~~~~~~~~~~~~~~~~~~~//
    // <editor-fold defaultstate="collapsed" desc="p(),pl(),pel()">
    public static void p(Object a){//debug
        System.out.print(a+"");
    }
    public static void pl(Object a){//debug
        System.out.println(a+"");
    }
    public static void pl(){//debug
        System.out.println();
    }
    public static void pel(){//debug
        System.err.println();
    }
    // </editor-fold>
    //application size  //alternative method is setSize(x,y)
    //public static final int APPLICATION_WIDTH = 400;
    //public static final int APPLICATION_HEIGHT = 650;
    
    
    
    //~~~~~~~~~~~~~~~~~~~~~~~~~ Global Variables ~~~~~~~~~~~~~~~~~~~~~~~~~//
    
    
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Methods ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    public static <E> void DIJKSTRA(GraphA<E> G,E a){
        ArrayList<E> GV=G.V();
        ArrayList<E> C=new ArrayList<>();
        for(E v:GV){//for each vertex v in G.V()
            G.setDdist(v,Double.MAX_VALUE);
            G.setDpar(v, null);
            C.add(v);
        }
        //System.out.println("loop1 done");
        G.setDdist(a, 0);
        while(!C.isEmpty()){
            E curNode=findMinimumDist(C, G);
            for(E v:G.adj(curNode)){
                if(G.weight(v, curNode)+G.getDdist(curNode)<G.getDdist(v)){
                    //System.out.println(curNode+","+v+"wt:"+G.weight(v, curNode));
                    G.setDdist(v, G.weight(v, curNode)+G.getDdist(curNode));
                    G.setDpar(v, curNode);
                }
                //System.out.println("cheking adj of "+v+"[["+G.getDdist(v)+";"+G.getDpar(v));
            }
        }
    }
    public static <E> E findMinimumDist(ArrayList<E> C,GraphA<E> G){
        E ans=C.get(0);
        for(int i=1;i<C.size();i++){
            if(G.getDdist(ans)>G.getDdist(C.get(i))){
                ans=C.get(i);
            }
        }
        C.remove(ans);
        //System.out.println("min="+ans);
        return ans;
    }
    
    
    public static <E> void DFS(GraphA<E> G,E a){
        ArrayList<E> GV=G.V();
        ArrayList<E> Z=new ArrayList<>();
        Stack<E> S=new Stack<>();
        
        for(E v:GV){//for each vertex v in G.V()
            Z.add(v);
        }
        Z.remove(a);
        output(a);
        S.push(a);
        while(!S.isEmpty()){
            //System.out.println("Stack: "+S);
            E curNode=S.peek();
            E next=getNextPriorityNotInSet(G.adj(curNode), Z);
            if(next!=null){
                S.push(next);
                output(next);
            } else {
                S.pop();
            }
        }
    }
    public static <E> void output(E a){
        System.out.println("DFS node: "+a);
    }
    public static <E> E getNextPriorityNotInSet(ArrayList<E> adjacentNodes,ArrayList<E> unvisitedNodes){
        //well, since no criteria
        for(int i=0;i<adjacentNodes.size();i++){
            if(unvisitedNodes.contains(adjacentNodes.get(i))){//not in set
                //System.out.println("long function result: "+adjacentNodes.get(i));
                unvisitedNodes.remove(adjacentNodes.get(i));
                return adjacentNodes.get(i);
            }
        }
        //System.out.println("long function result: null");
        return null;
    }
    
}
