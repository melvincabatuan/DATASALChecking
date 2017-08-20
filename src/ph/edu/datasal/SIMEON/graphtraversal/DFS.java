package ph.edu.datasal.SIMEON.graphtraversal;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Tiber
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;
import java.util.*;
import ph.edu.dlsu.datasal.SIMEON.mystack.Stack;

public class DFS extends ConsoleProgram {
         
        /* Private instance variables */
        private int[] nodes= new int[10];
        private int[] visited= new int[10];
        private int[][] edge = new int[10][10] ;
        private int[][] adjacent = new int[10][10] ; 
        private int numVisited = 0;

        Stack<Integer> stack = new Stack<Integer>();
       
        public boolean isAdjacent(int u, int v){
            int i,j,a=0,b=0;
            for(i=0;i<8;i++){
                if(u==nodes[i]){
                 a=i;
                }
            }
            for(j=0;j<8;j++){
                if(v==nodes[j]){
                 b=j;
                         }
            }
            return adjacent[u-1][v-1]==1;
        }
        public boolean isVisited(int v){
            for(int i=0;i<numVisited;i++){
                if(v==visited[i])
                    return true;
            }
            return false;
        }
        public boolean setEdge(int u, int v){
            int i,j,a=0,b=0;
            for(i=0;i<8;i++){
                if(u==nodes[i]){
                     a=i;
                     
                 break;
                }
            }
            for(j=0;j<8;j++){
                if(v==nodes[j]){
                     b=j;
                 break;
        }
            }
            adjacent[u-1][v-1]=1;
            adjacent[v-1][u-1]=1;
            return true;
        }

	public void run() {
            stack.createList();
            int nodes[] = {1,2,3,4,5,6,7,8};

            for(int p=0;p<8;p++){
                for(int o=0;o<8;o++){
                    adjacent[p][o]=0;
                }
            }
            setEdge(1,2);
            setEdge(1,4);
            setEdge(1,7);
            setEdge(2,5);
            setEdge(2,6);
            setEdge(5,7);
            setEdge(7,1);
            setEdge(4,6);
            setEdge(3,8);
            setEdge(3,6);
            int start=nodes[0];
                        stack.push(start);
            visited[numVisited]=start;
            numVisited++;
            while(!stack.isEmpty()){
                int h=1;
                start=stack.top();
                boolean hasAdjacentUnvisited = false;
               for(int a=1;h<8;h++){
                   
                if(isAdjacent(nodes[h],start)&&!isVisited(nodes[h])){
                    stack.push(nodes[h]);
                    start=nodes[h];
                    visited[numVisited]=start;
                    numVisited++;
                    hasAdjacentUnvisited = true;
                     h=1;

            }
               }
            if(hasAdjacentUnvisited==false){
                stack.pop();
                
                
            }
            
	}
             println("result: ");
            for(int z=0;z<numVisited;z++){
               println(visited[z]);
            }


            
	
}
}