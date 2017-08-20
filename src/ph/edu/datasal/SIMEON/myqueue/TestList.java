/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.datasal.SIMEON.myqueue;

import ph.edu.dlsu.datasal.SIMEON.mylinkedlist.*;
import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author student
 */
public class TestList extends ConsoleProgram {
    private Queue<Integer> score = new Queue<Integer>();
    private Queue<Integer> scorez = new Queue<Integer>();
    public void run(){

        score.enqueue(4);
        score.enqueue(5);
        score.enqueue(1);
        scorez.enqueue(1);
        scorez.enqueue(5);
        scorez.enqueue(4);
        println(score.equals(scorez));
        println(scorez.equals(score));
        score.sort(score);
        for(int i=1;i<=score.size();i++){
            println(score.get(i));
        }
        println(score.containsAll(scorez));
        score.addAll(scorez);
        println(score.size());
        score.removeAll(scorez);
        println(score.size());
        println(scorez.containsAll(score));
score.clear();
        println(score.contains(5));
        println(score.isEmpty());
        score.addAll(scorez);
        println(score.size());
                scorez.enqueue(9);
               
        scorez.removeAll(score);
        println(scorez.size());
       
    }
    
        public static void main(String[] args) {
        // TODO code application logic here
        new TestList().start(args);
    }
}
