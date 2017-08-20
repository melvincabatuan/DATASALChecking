/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.wenceslao.artistry;

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

/**
 *
 * @author Luis Paolo D. Wenceslao
 */
public class Artistry extends GraphicsProgram {
    public static final int APPLICATION_WIDTH = 640; 
    public static final int APPLICATION_HEIGHT = 480;
    
    public void run(){
        putHouse();
        putRoof();
        putLand();
        putSky();
        putSun();
    }

    private void putHouse(){
        int x = 100;
        int y = 100;
        GRect Square = new GRect(x, y);
        Square.setColor(Color.ORANGE);
        Square.setFilled(true);
        Square.setFillColor(Color.YELLOW);
        add(Square, 270, 300);
    }
    
    private void putRoof(){
        GPolygon Triangle = new GPolygon();
        Triangle.addVertex(0, 0);
        Triangle.addVertex(-82, 50);
        Triangle.addVertex(82, 50);
        Triangle.setColor(Color.YELLOW);
        Triangle.setFilled(true);
        Triangle.setFillColor(Color.ORANGE);
        add(Triangle, 320, 270);
    }
    
    private void putLand(){
        int x = 700;
        int y = 100;
        GRect Square = new GRect(x, y);
        Square.setColor(Color.GREEN);
        Square.setFilled(true);
        Square.setFillColor(Color.GREEN);
        add(Square, 0, 400);
    }
    
    private void putSky(){
        GLabel myLabel = new GLabel("Basic Art"); 
        myLabel.setFont("Arial-bold-24"); 
        myLabel.setColor(Color.DARK_GRAY);
        add(myLabel, 20, 40);
        setBackground(Color.CYAN);
    }
    
    private void putSun(){
        GOval Circle = new GOval(100, 100);
        Circle.setColor(Color.yellow);
        Circle.setFilled(true);
        Circle.setFillColor(Color.yellow);
        add(Circle, 530, 5);
    }
        
//        public static void main(String [] args) {
//        new Artistry().start(args);
//    }
}
