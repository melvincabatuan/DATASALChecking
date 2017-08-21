package datasal.Narag.calculator;

import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import java.awt.event.*;

// author: G E R S H O M   N A R A G

public class Calculator_Gran extends GraphicsProgram{
    
    
    
    
    
    //variables and stuff
    private GLabel show = new GLabel ("", 35, 35);
    private String input = "";
    private String infix = "";
    private ToPostFix if2pf;
    private String postfix;
    
    
    
    
    public void init(){
        setBackground(Color.PINK);
        setSize(235, 340);
        //numkeys
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
        
        //display
        disp();
        
        //operations
        plus();
        min();
        mult();
        div();
        sign();
        eq();
        decimal();
        clr();
        
    }
    
    public synchronized void addMouseListener(MouseListener l){
        super.addMouseListener(l);
    }
    
    public void mouseClicked(MouseEvent me){
        
        if (40<=me.getX()&&me.getX()<=80){
            
            if(58 <= me.getY()&&me.getY() <= 98){
                remove(show);
                infix = infix +"+";
                input="";
                add(show);
            }
            
            else if (98<=me.getY()&&me.getY()<=138){
                remove(show);
                infix = infix + "-";
                input = "";
                add(show);
            }
            
            else if (138<=me.getY()&&me.getY()<=178){
                remove(show);
                infix = infix + "*";
                input = "";
                add(show);
            }
            
            else if(178 <=me.getY()&&me.getY()<=218){
                remove(show);
                infix = infix + "/";
                input = "/";
                add(show);
            }
        }
        
        if (80<=me.getX()&&me.getX()<=120){
            
            if(58 <= me.getY()&&me.getY() <= 98){
                remove(show);
                show.setFont("Impact");
                infix = infix +"1";
                input=input+"1";
                show.setLabel(input + "");
                add(show);
            }
            
            else if (98<=me.getY()&&me.getY()<=138){
                remove(show);
                show.setFont("Impact");
                infix = infix + "4";
                input = input + "4";
                show.setLabel(input + "");
                add(show);
            }
            
            else if (138<=me.getY()&&me.getY()<=178){
                remove(show);
                show.setFont("Impact");
                infix = infix + "7";
                input = input + "7";
                show.setLabel(input + "");
                add(show);
            }
            
            else if(178 <=me.getY()&&me.getY()<=218){
                remove(show);
                show.setFont("Impact");
                infix = infix + "0";
                input = input + "0";
                show.setLabel(input + "");
                add(show);
            }
        }
        
        if (120<=me.getX()&&me.getX()<=160){
            
            if(58 <= me.getY()&&me.getY() <= 98){
                remove(show);
                show.setFont("Impact");
                infix = infix +"2";
                input=input+"2";
                show.setLabel(input + "");
                add(show);
            }
            
            else if (98<=me.getY()&&me.getY()<=138){
                remove(show);
                show.setFont("Impact");
                infix = infix + "5";
                input = input + "5";
                show.setLabel(input + "");
                add(show);
            }
            
            else if (138<=me.getY()&&me.getY()<=178){
                remove(show);
                show.setFont("Impact");
                infix = infix + "8";
                input = input + "8";
                show.setLabel(input + "");
                add(show);
            }
            
            else if(178 <=me.getY()&&me.getY()<=218){
                remove(show);
                show.setFont("Impact");
                infix = infix + ".";
                input = input + ".";
                show.setLabel(input + "");
                add(show);
            }
        }
        
        if (160<=me.getX()&&me.getX()<=200){
            
            if(58 <= me.getY()&&me.getY() <= 98){
                remove(show);
                show.setFont("Impact");
                infix = infix +"3";
                input=input+"3";
                show.setLabel(input + "");
                add(show);
            }
            
            else if (98<=me.getY()&&me.getY()<=138){
                remove(show);
                show.setFont("Impact");
                infix = infix + "6";
                input = input + "6";
                show.setLabel(input + "");
                add(show);
            }
            
            else if (138<=me.getY()&&me.getY()<=178){
                remove(show);
                show.setFont("Impact");
                infix = infix + "9";
                input = input + "9";
                show.setLabel(input + "");
                add(show);
            }
            
            else if (178<=me.getY()&&me.getY()<=218){
                remove(show);
                show.setFont("Impact");
                GLabel show = new GLabel ("", 155, 35);
                infix = "";
                input = "";
                show.setLabel("");
                add(show);
            }
        }
        
    if (80<=me.getX() && me.getX()<=200 && me.getY()>=228 && me.getY()<=268){
            remove(show);
            if2pf = new ToPostFix();
            postfix = if2pf.calcValue(infix);
            input = ""+postfix;
            show.setLabel(input);
            add(show);
        }       
        
        
    }
    
    private void disp(){
        GRect screen = new GRect(20, 15, 180, 30);
        screen.setFilled(true);
        screen.setFillColor(Color.WHITE);
        screen.setColor(Color.BLACK);
        add(screen);
        addMouseListeners();
    }
    
    private void  plus() {
        GRoundRect button = new GRoundRect(20, 58, 40, 40);
        button.setFilled(true);
        button.setFillColor(Color.WHITE);
        add(button);
        GLabel label = new GLabel("+", 37, 85);
        add(label);
        button.addMouseListener(new MouseAdapter() {  
            @Override
            public void mousePressed(MouseEvent e) { 
                button.setFillColor(Color.YELLOW);
            }
            @Override
            public void  mouseReleased(MouseEvent e) {
                button.setFillColor(Color.WHITE);
            }
        });
    }
    
    private void  min() {
        GRoundRect sign = new GRoundRect(20, 98, 40, 40);
        sign.setFilled(true);
        sign.setFillColor(Color.WHITE);
        add(sign);
        GLabel label = new GLabel("-", 39, 125);
        add(label);
        sign.addMouseListener(new MouseAdapter() {  
            @Override
            public void mousePressed(MouseEvent e) { 
                sign.setFillColor(Color.YELLOW);
            }
            @Override
            public void  mouseReleased(MouseEvent e) {
                sign.setFillColor(Color.WHITE);
            }
        });
    }
    
    private void  mult() {
        GRoundRect sign = new GRoundRect(20, 138, 40, 40);
        sign.setFilled(true);
        sign.setFillColor(Color.WHITE);
        add(sign);
        GLabel label = new GLabel("x", 35, 165);
        add(label);
        sign.addMouseListener(new MouseAdapter() {  
            @Override
            public void mousePressed(MouseEvent e) { 
                sign.setFillColor(Color.YELLOW);
            }
            @Override
            public void  mouseReleased(MouseEvent e) {
                sign.setFillColor(Color.WHITE);
            }
        });
    }
    
    private void  div() {
        GRoundRect sign = new GRoundRect(20, 178, 40, 40); 
        sign.setFilled(true);
        sign.setFillColor(Color.WHITE);
        add(sign);
        GLabel label = new GLabel("/", 35, 205); 
        add(label);
        sign.addMouseListener(new MouseAdapter() {  
            @Override
            public void mousePressed(MouseEvent e) { 
                sign.setFillColor(Color.YELLOW);
            }
            @Override
            public void  mouseReleased(MouseEvent e) {
                sign.setFillColor(Color.WHITE);
            }
        });
    }
    
    private void  sign() {
        GRoundRect sign = new GRoundRect(20, 228, 40, 40);
        sign.setFilled(true);
        sign.setFillColor(Color.WHITE);
        add(sign);
        GLabel label = new GLabel("+-", 35, 255);
        add(label);
        sign.addMouseListener(new MouseAdapter() {  
            @Override
            public void mousePressed(MouseEvent e) { 
                sign.setFillColor(Color.YELLOW);
            }
            @Override
            public void  mouseReleased(MouseEvent e) {
                sign.setFillColor(Color.WHITE);
            }
        });
    }
    
    private void  eq() {
        GRoundRect sign = new GRoundRect(80, 228, 120, 40);
        sign.setFilled(true);
        sign.setFillColor(Color.WHITE);
        add(sign);
        GLabel label = new GLabel("=", 138, 255);
        add(label);
        sign.addMouseListener(new MouseAdapter() {  
            @Override
            public void mousePressed(MouseEvent e) { 
                sign.setFillColor(Color.YELLOW);
            }
            @Override
            public void  mouseReleased(MouseEvent e) {
                sign.setFillColor(Color.WHITE);
            }
        });
    }
    
    private void  decimal() {
        GRoundRect symbol = new GRoundRect(120, 178, 40, 40); 
        symbol.setFilled(true);
        symbol.setFillColor(Color.LIGHT_GRAY);
        add(symbol);
        GLabel label = new GLabel(".", 138, 205); 
        add(label);
        symbol.addMouseListener(new MouseAdapter() {  
            @Override
            public void mousePressed(MouseEvent e) { 
                symbol.setFillColor(Color.WHITE);
            }
            @Override
            public void  mouseReleased(MouseEvent e) {
                symbol.setFillColor(Color.LIGHT_GRAY);
            }
        });
    }
    
    private void  clr() {
        GRoundRect symbol = new GRoundRect(160, 178, 40, 40); 
        symbol.setFilled(true);
        symbol.setFillColor(Color.LIGHT_GRAY);
        add(symbol);
        GLabel label = new GLabel("C", 178, 205); 
        add(label);
        symbol.addMouseListener(new MouseAdapter() {  
            @Override
            public void mousePressed(MouseEvent e) { 
                symbol.setFillColor(Color.WHITE);
            }
            @Override
            public void  mouseReleased(MouseEvent e) {
                symbol.setFillColor(Color.LIGHT_GRAY);
            }
        });
    }
    
    private void  Num1() {
        GRoundRect num = new GRoundRect(80, 58, 40, 40); 
        num.setFilled(true);
        num.setFillColor(Color.LIGHT_GRAY);
        add(num);
        GLabel label = new GLabel("1", 98, 85); 
        add(label);
        num.addMouseListener(new MouseAdapter() {  
            @Override
            public void mousePressed(MouseEvent e) { 
                num.setFillColor(Color.WHITE);
            }
            @Override
            public void  mouseReleased(MouseEvent e) {
                num.setFillColor(Color.LIGHT_GRAY);
            }
        });
    }
    
    private void  Num2() {
        GRoundRect num = new GRoundRect(120, 58, 40, 40); 
        num.setFilled(true);
        num.setFillColor(Color.LIGHT_GRAY);
        add(num);
        GLabel label = new GLabel("2", 138, 85); 
        add(label);
        num.addMouseListener(new MouseAdapter() {  
            @Override
            public void mousePressed(MouseEvent e) { 
                num.setFillColor(Color.WHITE);
            }
            @Override
            public void  mouseReleased(MouseEvent e) {
                num.setFillColor(Color.LIGHT_GRAY);
            }
        });
    }
    
    private void  Num3() {
        GRoundRect num = new GRoundRect(160, 58, 40, 40); 
        num.setFilled(true);
        num.setFillColor(Color.LIGHT_GRAY);
        add(num);
        GLabel label = new GLabel("3", 178, 85); 
        add(label);
        num.addMouseListener(new MouseAdapter() {  
            @Override
            public void mousePressed(MouseEvent e) { 
                num.setFillColor(Color.WHITE);
            }
            @Override
            public void  mouseReleased(MouseEvent e) {
                num.setFillColor(Color.LIGHT_GRAY);
            }
        });
    }
    
    private void  Num4() {
        GRoundRect num = new GRoundRect(80, 98, 40, 40); 
        num.setFilled(true);
        num.setFillColor(Color.LIGHT_GRAY);
        add(num);
        GLabel label = new GLabel("4", 98, 125); 
        add(label);
        num.addMouseListener(new MouseAdapter() {  
            @Override
            public void mousePressed(MouseEvent e) { 
                num.setFillColor(Color.WHITE);
            }
            @Override
            public void  mouseReleased(MouseEvent e) {
                num.setFillColor(Color.LIGHT_GRAY);
            }
        });
    }
    
    private void  Num5() {
        GRoundRect num = new GRoundRect(120, 98, 40, 40); 
        num.setFilled(true);
        num.setFillColor(Color.LIGHT_GRAY);
        add(num);
        GLabel label = new GLabel("5", 138, 125); 
        add(label);
        num.addMouseListener(new MouseAdapter() {  
            @Override
            public void mousePressed(MouseEvent e) { 
                num.setFillColor(Color.WHITE);
            }
            @Override
            public void  mouseReleased(MouseEvent e) {
                num.setFillColor(Color.LIGHT_GRAY);
            }
        });
    }
    
    private void  Num6() {
        GRoundRect num = new GRoundRect(160, 98, 40, 40); 
        num.setFilled(true);
        num.setFillColor(Color.LIGHT_GRAY);
        add(num);
        GLabel label = new GLabel("6", 178, 125); 
        add(label);
        num.addMouseListener(new MouseAdapter() {  
            @Override
            public void mousePressed(MouseEvent e) { 
                num.setFillColor(Color.WHITE);
            }
            @Override
            public void  mouseReleased(MouseEvent e) {
                num.setFillColor(Color.LIGHT_GRAY);
            }
        });
    }
    
    private void  Num7() {
        GRoundRect num = new GRoundRect(80, 138, 40, 40); 
        num.setFilled(true);
        num.setFillColor(Color.LIGHT_GRAY);
        add(num);
        GLabel label = new GLabel("7", 98, 165); 
        add(label);
        num.addMouseListener(new MouseAdapter() {  
            @Override
            public void mousePressed(MouseEvent e) { 
                num.setFillColor(Color.WHITE);
            }
            @Override
            public void  mouseReleased(MouseEvent e) {
                num.setFillColor(Color.LIGHT_GRAY);
            }
        });
    }
    
    private void  Num8() {
        GRoundRect num = new GRoundRect(120, 138, 40, 40); 
        num.setFilled(true);
        num.setFillColor(Color.LIGHT_GRAY);
        add(num);
        GLabel label = new GLabel("8", 138, 165); 
        add(label);
        num.addMouseListener(new MouseAdapter() {  
            @Override
            public void mousePressed(MouseEvent e) { 
                num.setFillColor(Color.WHITE);
            }
            @Override
            public void  mouseReleased(MouseEvent e) {
                num.setFillColor(Color.LIGHT_GRAY);
            }
        });
    }
    
    private void  Num9() {
        GRoundRect num = new GRoundRect(160, 138, 40, 40); 
        num.setFilled(true);
        num.setFillColor(Color.LIGHT_GRAY);
        add(num);
        GLabel label = new GLabel("9", 178, 165); 
        add(label);
        num.addMouseListener(new MouseAdapter() {  
            @Override
            public void mousePressed(MouseEvent e) { 
                num.setFillColor(Color.WHITE);
            }
            @Override
            public void  mouseReleased(MouseEvent e) {
                num.setFillColor(Color.LIGHT_GRAY);
            }
        });
    }
    
    private void  Num0() {
        GRoundRect num = new GRoundRect(80, 178, 40, 40); 
        num.setFilled(true);
        num.setFillColor(Color.LIGHT_GRAY);
        add(num);
        GLabel label = new GLabel("0", 98, 205); 
        add(label);
        num.addMouseListener(new MouseAdapter() {  
            @Override
            public void mousePressed(MouseEvent e) { 
                num.setFillColor(Color.WHITE);
            }
            @Override
            public void  mouseReleased(MouseEvent e) {
                num.setFillColor(Color.LIGHT_GRAY);
            }
        });
    }
    
    public void runCalc() {
        new Calculator_Gran().start();
    }
    
}