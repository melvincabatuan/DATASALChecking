/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.SIMEON.myarraylist;
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
    private MyArrayList<Integer> score = new MyArrayList<Integer>();
    private MyArrayList<Integer> scorez = new MyArrayList<Integer>();
     private MyArrayList<Integer> scorea = new MyArrayList<Integer>();
    public void run(){
        score.createList();
        scorez.createList();
        score.add(1, 4);
        score.add(2, 5);
        score.add(3, 1);
        score.add(4,68);
        scorez.add(1, 1);
        scorez.add(2, 5);
        scorez.add(3, 4);

        
        score.sort(score);

        for(int i=1;i<=score.size();i++){
            println(score.get(i));
        }
        println(score.containsAll(scorez));
        println(score.contains(4));
        println(score.containsAll(scorez));
        score.clear();
        println(score.contains(5));
        println(score.isEmpty());
        score.addAll(scorez);
        println(score.size());
                scorez.add(3, 9);
               
        score.removeAll(scorez);
        println(scorez.size());
       
    }
    
        public static void main(String[] args) {
        // TODO code application logic here
        new TestList().start(args);
    }
}
