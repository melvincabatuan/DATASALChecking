/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.reyes.datasalapp.calculator;


public class stack {
    double Stack[] = new double[2];
    private int max = 2;
    private int size = 0;
    public boolean full(){
        if(size==max) return true;
        else return false;               
    }
     public boolean empty(){
        if(size==0) return true;
        else return false;               
    }
    public double gettop(){
        double top = Stack[size-1];
        return top;
    }
    public void push(double num){
        Stack[size] = num;
        size++;
    }
    public void pop(){
       size--;
    }
}
