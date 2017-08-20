package ph.edu.dlsu.datasal.wenceslao.calculator;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.graphics.GRoundRect;
import acm.program.*;
import java.awt.*;
import java.awt.event.MouseAdapter; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener;

// Author: Luis Paolo D. Wenceslao

public class Calculator_LPDW extends GraphicsProgram{
    
    private GLabel show = new GLabel("", 35, 35);
    private String acc = "";
    private String infix = "";
    private ToPostFix InFixToPostFix;
    private String postfix;
    
    public void init(){
        setBackground(Color.BLACK);
        setSize(237, 342);
        Display();
        Add();
        Subt();
        Mult();
        Div();
        Polarity();
        Equal();
        Dot();
        Clear();
        Num1();
        Num2();
        Num3();
        Num4();
        Num5();
        Num6();
        Num7();
        Num8();
        Num9();
        Num0();
    }
    
    public synchronized void addMouseListener(MouseListener l){
        super.addMouseListener(l);
    }
    
    private void Display(){
        GRect screen = new GRect(20, 15, 183, 30);
        screen.setFilled(true);
        screen.setFillColor(Color.WHITE);
        screen.setColor(Color.BLACK);
        add(screen);
        addMouseListeners();
    }
    
    private void  Add() {
        GRoundRect sign = new GRoundRect(20, 58, 40, 40);
        sign.setFilled(true);
        sign.setColor(Color.YELLOW);
        sign.setFillColor(Color.ORANGE);
        add(sign);
        GLabel label = new GLabel("+", 37, 85);
        label.setColor(Color.WHITE);
        add(label);
        sign.addMouseListener(new MouseAdapter() {  
            @Override
            public void mousePressed(MouseEvent e) { 
                sign.setFillColor(Color.YELLOW);
            }
            @Override
            public void  mouseReleased(MouseEvent e) {
                sign.setFillColor(Color.ORANGE);
            }
        });
    }
    
    private void  Subt() {
        GRoundRect sign = new GRoundRect(20, 98, 40, 40);
        sign.setFilled(true);
        sign.setColor(Color.YELLOW);
        sign.setFillColor(Color.ORANGE);
        add(sign);
        GLabel label = new GLabel("-", 39, 125);
        label.setColor(Color.WHITE);
        add(label);
        sign.addMouseListener(new MouseAdapter() {  
            @Override
            public void mousePressed(MouseEvent e) { 
                sign.setFillColor(Color.YELLOW);
            }
            @Override
            public void  mouseReleased(MouseEvent e) {
                sign.setFillColor(Color.ORANGE);
            }
        });
    }
    
    private void  Mult() {
        GRoundRect sign = new GRoundRect(20, 138, 40, 40);
        sign.setFilled(true);
        sign.setColor(Color.YELLOW);
        sign.setFillColor(Color.ORANGE);
        add(sign);
        GLabel label = new GLabel("x", 39, 165);
        label.setColor(Color.WHITE);
        add(label);
        sign.addMouseListener(new MouseAdapter() {  
            @Override
            public void mousePressed(MouseEvent e) { 
                sign.setFillColor(Color.YELLOW);
            }
            @Override
            public void  mouseReleased(MouseEvent e) {
                sign.setFillColor(Color.ORANGE);
            }
        });
    }
    
    private void  Div() {
        GRoundRect sign = new GRoundRect(20, 178, 40, 40); 
        sign.setFilled(true);
        sign.setColor(Color.YELLOW);
        sign.setFillColor(Color.ORANGE);
        add(sign);
        GLabel label = new GLabel("/", 39, 205);
        label.setColor(Color.WHITE); 
        add(label);
        sign.addMouseListener(new MouseAdapter() {  
            @Override
            public void mousePressed(MouseEvent e) { 
                sign.setFillColor(Color.YELLOW);
            }
            @Override
            public void  mouseReleased(MouseEvent e) {
                sign.setFillColor(Color.ORANGE);
            }
        });
    }
    
    private void  Polarity() {
        GRoundRect sign = new GRoundRect(20, 228, 40, 40);
        sign.setFilled(true);
        sign.setColor(Color.YELLOW);
        sign.setFillColor(Color.ORANGE);
        add(sign);
        GLabel label = new GLabel("±", 37, 255);
        label.setColor(Color.WHITE);
        add(label);
        sign.addMouseListener(new MouseAdapter() {  
            @Override
            public void mousePressed(MouseEvent e) { 
                sign.setFillColor(Color.YELLOW);
            }
            @Override
            public void  mouseReleased(MouseEvent e) {
                sign.setFillColor(Color.ORANGE);
            }
        });
    }
    
    private void  Equal() {
        GRoundRect sign = new GRoundRect(80, 228, 120, 40);
        sign.setFilled(true);
        sign.setColor(Color.YELLOW);
        sign.setFillColor(Color.ORANGE);
        add(sign);
        GLabel label = new GLabel("=", 138, 255);
        label.setColor(Color.WHITE);
        add(label);
        sign.addMouseListener(new MouseAdapter() {  
            @Override
            public void mousePressed(MouseEvent e) { 
                sign.setFillColor(Color.YELLOW);
            }
            @Override
            public void  mouseReleased(MouseEvent e) {
                sign.setFillColor(Color.ORANGE);
            }
        });
    }
    
    private void  Dot() {
        GRoundRect symbol = new GRoundRect(120, 178, 40, 40); 
        symbol.setFilled(true);
        symbol.setColor(Color.WHITE);
        symbol.setFillColor(Color.GRAY);
        add(symbol);
        GLabel label = new GLabel(".", 140, 205);
        label.setColor(Color.WHITE);
        add(label);
        symbol.addMouseListener(new MouseAdapter() {  
            @Override
            public void mousePressed(MouseEvent e) { 
                symbol.setFillColor(Color.LIGHT_GRAY);
            }
            @Override
            public void  mouseReleased(MouseEvent e) {
                symbol.setFillColor(Color.GRAY);
            }
        });
    }
    
    private void  Clear() {
        GRoundRect symbol = new GRoundRect(160, 178, 40, 40); 
        symbol.setFilled(true);
        symbol.setColor(Color.WHITE);
        symbol.setFillColor(Color.DARK_GRAY);
        add(symbol);
        GLabel label = new GLabel("C", 178, 205);
        label.setColor(Color.WHITE);
        add(label);
        symbol.addMouseListener(new MouseAdapter() {  
            @Override
            public void mousePressed(MouseEvent e) { 
                symbol.setFillColor(Color.LIGHT_GRAY);
            }
            @Override
            public void  mouseReleased(MouseEvent e) {
                symbol.setFillColor(Color.DARK_GRAY);
            }
        });
    }
    
    private void  Num1() {
        GRoundRect num = new GRoundRect(80, 58, 40, 40); 
        num.setFilled(true);
        num.setColor(Color.WHITE);
        num.setFillColor(Color.GRAY);
        add(num);
        GLabel label = new GLabel("1", 98, 85);
        label.setColor(Color.WHITE); 
        add(label);
        num.addMouseListener(new MouseAdapter() {  
            @Override
            public void mousePressed(MouseEvent e) { 
                num.setFillColor(Color.LIGHT_GRAY);
            }
            @Override
            public void  mouseReleased(MouseEvent e) {
                num.setFillColor(Color.GRAY);
            }
        });
    }
    
    private void  Num2() {
        GRoundRect num = new GRoundRect(120, 58, 40, 40); 
        num.setFilled(true);
        num.setColor(Color.WHITE);
        num.setFillColor(Color.GRAY);
        add(num);
        GLabel label = new GLabel("2", 138, 85); 
        label.setColor(Color.WHITE);
        add(label);
        num.addMouseListener(new MouseAdapter() {  
            @Override
            public void mousePressed(MouseEvent e) { 
                num.setFillColor(Color.LIGHT_GRAY);
            }
            @Override
            public void  mouseReleased(MouseEvent e) {
                num.setFillColor(Color.GRAY);
            }
        });
    }
    
    private void  Num3() {
        GRoundRect num = new GRoundRect(160, 58, 40, 40); 
        num.setFilled(true);
        num.setColor(Color.WHITE);
        num.setFillColor(Color.GRAY);
        add(num);
        GLabel label = new GLabel("3", 178, 85);
        label.setColor(Color.WHITE); 
        add(label);
        num.addMouseListener(new MouseAdapter() {  
            @Override
            public void mousePressed(MouseEvent e) { 
                num.setFillColor(Color.LIGHT_GRAY);
            }
            @Override
            public void  mouseReleased(MouseEvent e) {
                num.setFillColor(Color.GRAY);
            }
        });
    }
    
    private void  Num4() {
        GRoundRect num = new GRoundRect(80, 98, 40, 40); 
        num.setFilled(true);
        num.setColor(Color.WHITE);
        num.setFillColor(Color.GRAY);
        add(num);
        GLabel label = new GLabel("4", 98, 125);
        label.setColor(Color.WHITE); 
        add(label);
        num.addMouseListener(new MouseAdapter() {  
            @Override
            public void mousePressed(MouseEvent e) { 
                num.setFillColor(Color.LIGHT_GRAY);
            }
            @Override
            public void  mouseReleased(MouseEvent e) {
                num.setFillColor(Color.GRAY);
            }
        });
    }
    
    private void  Num5() {
        GRoundRect num = new GRoundRect(120, 98, 40, 40); 
        num.setFilled(true);
        num.setColor(Color.WHITE);
        num.setFillColor(Color.GRAY);
        add(num);
        GLabel label = new GLabel("5", 138, 125);
        label.setColor(Color.WHITE); 
        add(label);
        num.addMouseListener(new MouseAdapter() {  
            @Override
            public void mousePressed(MouseEvent e) { 
                num.setFillColor(Color.LIGHT_GRAY);
            }
            @Override
            public void  mouseReleased(MouseEvent e) {
                num.setFillColor(Color.GRAY);
            }
        });
    }
    
    private void  Num6() {
        GRoundRect num = new GRoundRect(160, 98, 40, 40); 
        num.setFilled(true);
        num.setColor(Color.WHITE);
        num.setFillColor(Color.GRAY);
        add(num);
        GLabel label = new GLabel("6", 178, 125);
        label.setColor(Color.WHITE); 
        add(label);
        num.addMouseListener(new MouseAdapter() {  
            @Override
            public void mousePressed(MouseEvent e) { 
                num.setFillColor(Color.LIGHT_GRAY);
            }
            @Override
            public void  mouseReleased(MouseEvent e) {
                num.setFillColor(Color.GRAY);
            }
        });
    }
    
    private void  Num7() {
        GRoundRect num = new GRoundRect(80, 138, 40, 40); 
        num.setFilled(true);
        num.setColor(Color.WHITE);
        num.setFillColor(Color.GRAY);
        add(num);
        GLabel label = new GLabel("7", 98, 165); 
        label.setColor(Color.WHITE);
        add(label);
        num.addMouseListener(new MouseAdapter() {  
            @Override
            public void mousePressed(MouseEvent e) { 
                num.setFillColor(Color.LIGHT_GRAY);
            }
            @Override
            public void  mouseReleased(MouseEvent e) {
                num.setFillColor(Color.GRAY);
            }
        });
    }
    
    private void  Num8() {
        GRoundRect num = new GRoundRect(120, 138, 40, 40); 
        num.setFilled(true);
        num.setColor(Color.WHITE);
        num.setFillColor(Color.GRAY);
        add(num);
        GLabel label = new GLabel("8", 138, 165); 
        label.setColor(Color.WHITE);
        add(label);
        num.addMouseListener(new MouseAdapter() {  
            @Override
            public void mousePressed(MouseEvent e) { 
                num.setFillColor(Color.LIGHT_GRAY);
            }
            @Override
            public void  mouseReleased(MouseEvent e) {
                num.setFillColor(Color.GRAY);
            }
        });
    }
    
    private void  Num9() {
        GRoundRect num = new GRoundRect(160, 138, 40, 40); 
        num.setFilled(true);
        num.setColor(Color.WHITE);
        num.setFillColor(Color.GRAY);
        add(num);
        GLabel label = new GLabel("9", 178, 165); 
        label.setColor(Color.WHITE);
        add(label);
        num.addMouseListener(new MouseAdapter() {  
            @Override
            public void mousePressed(MouseEvent e) { 
                num.setFillColor(Color.LIGHT_GRAY);
            }
            @Override
            public void  mouseReleased(MouseEvent e) {
                num.setFillColor(Color.GRAY);
            }
        });
    }
    
    private void  Num0() {
        GRoundRect num = new GRoundRect(80, 178, 40, 40); 
        num.setFilled(true);
        num.setColor(Color.WHITE);
        num.setFillColor(Color.GRAY);
        add(num);
        GLabel label = new GLabel("0", 98, 205);
        label.setColor(Color.WHITE); 
        add(label);
        num.addMouseListener(new MouseAdapter() {  
            @Override
            public void mousePressed(MouseEvent e) { 
                num.setFillColor(Color.LIGHT_GRAY);
            }
            @Override
            public void  mouseReleased(MouseEvent e) {
                num.setFillColor(Color.GRAY);
            }
        });
    }
    
    public void mouseClicked(MouseEvent me) {
        if ( 20 <= me.getX() && me.getX() <= 60 ) {
            if ( 58 <= me.getY() && me.getY() <= 98 ) {
                remove( show );
                infix = infix + "+" ;
                acc = acc + "+" ;
                show.setLabel(acc + "");
                add( show );
            }
            else if ( 98 <= me.getY() && me.getY() <= 138 ) {
                remove( show );
                infix = infix + "-" ;
                acc = acc + "-" ;
                show.setLabel(acc + "");
                add( show );
            }
            else if ( 138 <= me.getY() && me.getY() <= 178 ) {
                remove( show );
                infix = infix + "*" ;
                acc = acc + "*" ;
                show.setLabel(acc + "");
                add( show );
            }
            else if ( 178 <= me.getY() && me.getY() <= 218 ) {
                remove( show );
                infix = infix + "/" ;
                acc = acc + "/" ;
                show.setLabel(acc + "");
                add( show );
            }
        }
        
        if ( 80 <= me.getX() && me.getX() <= 120 ) {
            if ( 58 <= me.getY() && me.getY() <= 98 ) {
                remove( show );
                infix = infix + "1" ;
                acc = acc + "1" ;
                show .setLabel( acc + "" );
                add( show );
            }
            if ( 98 <= me.getY() && me.getY() <= 138 ) {
                remove( show );
                infix = infix + "4" ;
                acc = acc + "4" ;
                show .setLabel( acc + "" );
                add( show );
            }
            if ( 138 <= me.getY() && me.getY() <= 178 ) {
                remove( show );
                infix = infix + "7" ;
                acc = acc + "7" ;
                show .setLabel( acc + "" );
                add( show );
            }
            if ( 178 <= me.getY() && me.getY() <= 218 ) {
                remove( show );
                infix = infix + "0" ;
                acc = acc + "0" ;
                show .setLabel( acc + "" );
                add( show );
            }
            if ( 218 <= me.getY() && me.getY() <= 258 ) {
                remove( show );
                InFixToPostFix = new ToPostFix();
                postfix = InFixToPostFix .calcValue( infix );
                acc = "" + postfix ;
                show .setLabel( acc );
                add( show );
            }
        }
        
        if ( 120 <= me.getX() && me.getX() <= 160 ) {
            if ( 58 <= me.getY() && me.getY() <= 98 ) {
                remove( show );
                infix = infix + "2" ;
                acc = acc + "2" ;
                show .setLabel( acc + "" );
                add( show );
            }
            if ( 98 <= me.getY() && me.getY() <= 138 ) {
                remove( show );
                infix = infix + "5" ;
                acc = acc + "5" ;
                show .setLabel( acc + "" );
                add( show );
            }
            if ( 138 <= me.getY() && me.getY() <= 178 ) {
                remove( show );
                infix = infix + "8" ;
                acc = acc + "8" ;
                show .setLabel( acc + "" );
                add( show );
            }
            if ( 178 <= me.getY() && me.getY() <= 218 ) {
                remove( show );
                infix = infix + "." ;
                acc = acc + "." ;
                show .setLabel( acc + "" );
                add( show );
            }
            if ( 218 <= me.getY() && me.getY() <= 258 ) {
                remove( show );
                InFixToPostFix = new ToPostFix();
                postfix = InFixToPostFix.calcValue( infix );
                acc = "" + postfix ;
                show .setLabel( acc );
                add( show );
            }
        }
        
        if ( 160 <= me.getX() && me.getX() <= 200 ) {
            if ( 58 <= me.getY() && me.getY() <= 98 ) {
                remove( show );
                infix = infix + "3" ;
                acc = acc + "3" ;
                show .setLabel( acc + "" );
                add( show );
            }
            if ( 98 <= me.getY() && me.getY() <= 138 ) {
                remove( show );
                infix = infix + "6" ;
                acc = acc + "6" ;
                show .setLabel( acc + "" );
                add( show );
            }
            if ( 138 <= me.getY() && me.getY() <= 178 ) {
                remove( show );
                infix = infix + "9" ;
                acc = acc + "9" ;
                show .setLabel( acc + "" );
                add( show );
            }
            if ( 178 <= me.getY() && me.getY() <= 218 ) {
                remove( show );
                GLabel show = new GLabel( "" , 155 , 35 );
                infix = "" ;
                acc = "" ;
                show.setLabel( "" );
                add(show);
            }
            if ( 218 <= me.getY() && me.getY() <= 258 ) {
                remove( show );
                InFixToPostFix = new ToPostFix();
                postfix = InFixToPostFix .calcValue( infix );
                acc = "" + postfix ;
                show .setLabel( acc );
                add( show );
            }
        }
    }
    
//    public static void main(String[] args) {
//        new Calculator_LPDW().start(args);
//    }
    
}