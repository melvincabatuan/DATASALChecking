package ph.edu.dlsu.datasal.lee.mygraph;

import java.util.*;
public class Graph{
    
	public List<Edge> G[];
	public Graph(int n){
		G=new LinkedList[n];
		for(int i=0;i<G.length;i++)
			G[i]=new LinkedList<Edge>();
	}
        
	public boolean isConnected(int u,int v){
                for(int i=0;i<G.length;i++){
			if(G[u].get(i).v==v) return true;
                }
		return false;
	}
        
	public void addEdge(int u,int v, int w){
		G[u].add(0,new Edge(u,v,w)); 
	}
        
	public String toString(){
		String result="";
		for(int i=0;i<G.length;i++)
			result+=i+"=>"+G[i]+"\n";
		return result;
	}
}
