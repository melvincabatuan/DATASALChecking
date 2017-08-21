/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasal.Narag.myarraylist;

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;


/**
 *
 * @author Administrator
 */
public class ListDemo extends ConsoleProgram {

    private ListCanvas canvas;

    /// Create the list of food (memory allocation)
    private MyArrayList<String> hiScore = new MyArrayList<String>();
    
    public static void main(String[] args){
        new ListDemo().start(args);
    }

    public void init() {
	      canvas = new ListCanvas();
              add(canvas); 
              canvas.reset();
		}

    public void run() {

             /// Create empty list

             hiScore.createList();
	     
             canvas.displayList(hiScore);

             testRemoveFromEmpty();             
 
             testAdd();

             testRemove();
 
        
	}


    private void testAdd(){

             /// Add milk
             println("food.add(1, \"milk\")");
             hiScore.add(1, "milk"); 
             println("food.size()  = " + hiScore.size()); 
             canvas.displayList(hiScore); 
             pause(4000);

             // food.removeAll(); //ACTIVITY 1
           
             /// Add eggs
             println("food.add(1, \"eggs\")");
             hiScore.add(1, "eggs"); 
             println("food.size()  = " + hiScore.size()); 
             canvas.displayList(hiScore);
             pause(4000);  

             /// Add butter
             println("food.add(1, \"butter\")");
             hiScore.add(1, "butter"); 
             println("food.size()  = " + hiScore.size()); 
             canvas.displayList(hiScore);
             pause(4000);  

               /// Add apples
             println("food.add(1, \"apples\")");
             hiScore.add(1, "apples"); 
             println("food.size()  = " + hiScore.size()); 
             canvas.displayList(hiScore);
             pause(4000);  

             /// Add bread
             println("food.add(1, \"bread\")");
             hiScore.add(1, "bread"); 
             println("food.size()  = " + hiScore.size()); 
             canvas.displayList(hiScore);
             pause(4000);

             /// Add chicken
             println("food.add(1, \"chicken\")");
             hiScore.add(1, "chicken"); 
             println("food.size()  = " + hiScore.size()); 
             canvas.displayList(hiScore);
             pause(4000);

             /// Add pork
             println("food.add(7, \"pork\")");
             hiScore.add(7, "pork"); 
             println("food.size()  = " + hiScore.size()); 
             canvas.displayList(hiScore);
             pause(4000);

             /// Add beef
             println("food.add(10, \"beef\")");
             try{
                hiScore.add(10, "beef"); 
             }
             catch (Exception e){
                e.printStackTrace();
             }
             println("food.size()  = " + hiScore.size()); 
             canvas.displayList(hiScore);
             pause(4000);

              /// Add beef
             println("food.add(2, \"beef\")");
             hiScore.add(2, "beef"); 
             println("food.size()  = " + hiScore.size()); 
             canvas.displayList(hiScore);
             pause(4000);

             /// Add tilapia
             println("food.add(2, \"tilapia\")");
             hiScore.add(2, "tilapia"); 
             println("food.size()  = " + hiScore.size()); 
             canvas.displayList(hiScore);
             pause(4000);

             /// Add tuna
             println("food.add(10, \"tuna\")");
             hiScore.add(10, "tuna"); 
             println("food.size()  = " + hiScore.size()); 
             canvas.displayList(hiScore);
             pause(4000);

             /// Add lechon
             println("food.add(10, \"lechon\")");
             try{
             hiScore.add(1, "lechon"); 
             }
             catch (Exception e){
                e.printStackTrace();
             }
             println("food.size()  = " + hiScore.size()); 
             canvas.displayList(hiScore);
             pause(5000);

       }

 
    private void testRemove(){

             /// Remove 5: apples
             println("food.remove(5)");
             hiScore.remove(5); 
             println("food.size()  = " + hiScore.size()); 
             canvas.displayList(hiScore);
             pause(4000);

             /// Remove 10 : ERROR
             println("food.remove(10)");
             try{
             hiScore.remove(10); 
             } 
             catch (Exception e){
                e.printStackTrace();
             } 
             println("food.size()  = " + hiScore.size()); 
             canvas.displayList(hiScore);
             pause(4000);

             /// Remove 9: tuna
             println("food.remove(9)");
             hiScore.remove(9); 
             println("food.size()  = " + hiScore.size()); 
             canvas.displayList(hiScore);
             pause(4000);

             /// Remove 1: chicken
             println("food.remove(1)");
             hiScore.remove(1); 
             println("food.size()  = " + hiScore.size()); 
             canvas.displayList(hiScore);
             pause(4000);

             /// Remove 7: pork
             println("food.remove(7)");
             hiScore.remove(7); 
             println("food.size()  = " + hiScore.size()); 
             canvas.displayList(hiScore);
             pause(4000);
   }

   private void testRemoveFromEmpty(){
             /// Remove 0 : ERROR
             println("food.remove(0)");
             try{
             hiScore.remove(0); 
             } 
             catch (Exception e){
                e.printStackTrace();
             } 
             println("food.size()  = " + hiScore.size()); 
             canvas.displayList(hiScore);
             pause(4000);

             /// Remove 1 : ERROR
             println("food.remove(1)");
             try{
             hiScore.remove(1); 
             } 
             catch (Exception e){
                e.printStackTrace();
             } 
             println("food.size()  = " + hiScore.size()); 
             canvas.displayList(hiScore);
             pause(4000);
  }

   private void testGet(){
             /// Get 1 :  
             println("food.remove(1)");
             try{
             hiScore.remove(1); 
             } 
             catch (Exception e){
                e.printStackTrace();
             } 
             println("food.size()  = " + hiScore.size()); 
             canvas.displayList(hiScore);
             pause(4000);
   }
}
