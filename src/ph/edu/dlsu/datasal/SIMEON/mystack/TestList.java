/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.SIMEON.mystack;


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
    private Stack<Integer> score = new Stack<Integer>();
    private Stack<Integer> scorez = new Stack<Integer>();
    public void run(){
        score.push(7);

        score.push(5);
        score.push(1);
        scorez.push(1);
        scorez.push(5);
        scorez.push(4);
        println(score.intersection(scorez).size());
        println(score.containsAll(scorez));
        println(score.contains(4));
        score.pop();
        println(score.containsAll(scorez));
        score.clear();
        println(score.size());
        println(score.contains(5));
        println(score.isEmpty());
        score.addAll(scorez);
        println(score.size());
        scorez.push(9);              
        score.removeAll(scorez);
        println(score.size());
       
    }
    
        public static void main(String[] args) {
        // TODO code application logic here
        new TestList().start(args);
    }
}
