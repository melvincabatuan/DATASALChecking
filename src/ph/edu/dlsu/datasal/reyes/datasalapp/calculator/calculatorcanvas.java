/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.reyes.datasalapp.calculator;
import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class calculatorcanvas extends GraphicsProgram{
    stack s = new stack();
    GRect one = new GRect(10,100,50,50);
    GRect two = new GRect(60,100,50,50);
    GRect three = new GRect(110,100,50,50);
    GRect four = new GRect(10,150,50,50);
    GRect five = new GRect(60,150,50,50);
    GRect six = new GRect(110,150,50,50);
    GRect seven= new GRect(10,200,50,50);
    GRect eight = new GRect(60,200,50,50);
    GRect nine = new GRect(110,200,50,50);
    GRect zero = new GRect(60,250,50,50);
    GRect plus = new GRect(160,100,50,50);
    GRect minus = new GRect(160,150,50,50);
    GRect times = new GRect(160,200,50,50);
    GRect divide = new GRect(160,250,50,50);
    GRect equals = new GRect(10,300,150,50);
    GLabel display = new GLabel("");
    
    
    
    public void calc(){
        addMouseListeners();
        add(one);
        add(two);
        add(three);
        add(four);
        add(five);
        add(six);
        add(display,10,100);
    }
    
}
