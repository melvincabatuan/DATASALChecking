/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.SIMEON.calculator;
import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 *
 * @author student
 */
public class Calc2 extends GraphicsProgram {
    private int x, y;
    private GRect answer;
    private GLabel cure1=null;
    private String cure;
    private int boxwidth=30;
    private int boxheight=30;
    private Stack<String> operator = new Stack<String>();
    private Stack<Integer> operand = new Stack<Integer>();
    private void drawplus(){
            GRect plus = new GRect(boxwidth,boxheight);
            add(plus,40,60);
             GLabel plus1 = new GLabel("+");
            add(plus1,40-plus1.getWidth()/2+boxwidth/2,60+(boxheight+plus1.getHeight())/2);
            plus1.setFont("COMIC SANS MS-20"); 
            plus.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                cure = "+";
                operator.push(cure);
                drawcure(); 
                
            }

            @Override
            public void mousePressed(MouseEvent e)
            {
            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
            }

            @Override
            public void mouseEntered(MouseEvent e)
            {
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
            }
        });
    }
     private void drawequal(){
            GRect equal = new GRect(3*boxwidth,boxheight);
            add(equal,190-3*boxwidth,190);
            GLabel equal1 = new GLabel("=");
            add(equal1,190-3*boxwidth/2-equal1.getWidth()/2,190+(boxheight+equal1.getHeight())/2);
            equal1.setFont("COMIC SANS MS-20"); 
            equal.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                
                doit();
                
            }

            @Override
            public void mousePressed(MouseEvent e)
            {
            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
            }

            @Override
            public void mouseEntered(MouseEvent e)
            {
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
            }
        });
    }
    private void drawminus(){
            GRect minus = new GRect(boxwidth,boxheight);
            add(minus,40,90);
            GLabel minus1 = new GLabel("-");
            add(minus1,40+boxwidth/2-minus1.getWidth()/2,90+(boxheight+minus1.getHeight())/2);
            minus1.setFont("COMIC SANS MS-20");
            minus.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                cure = "-";
                operator.push(cure);
                drawcure(); 
                
            }

            @Override
            public void mousePressed(MouseEvent e)
            {
            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
            }

            @Override
            public void mouseEntered(MouseEvent e)
            {
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
            }
        });
    }
    private void drawtimes(){
            GRect times = new GRect(boxwidth,boxheight);
            add(times,40,120);
            GLabel times1 = new GLabel("*");
            add(times1,40+boxwidth/2-times1.getWidth()/2,120+(boxheight+times1.getHeight())/2);
            times1.setFont("COMIC SANS MS-20");
            times.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                cure = "*";
                operator.push(cure);
                drawcure(); 
                
            }

            @Override
            public void mousePressed(MouseEvent e)
            {
            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
            }

            @Override
            public void mouseEntered(MouseEvent e)
            {
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
            }
        });
    }
  
    private void plusminus(){
            GRect plusminus = new GRect(boxwidth,boxheight);
            add(plusminus,40,190);
            GLabel plusminus1 = new GLabel("+-");
            add(plusminus1,40+boxwidth/2-plusminus1.getWidth()/2,190+(boxheight+plusminus1.getHeight())/2);
            plusminus1.setFont("COMIC SANS MS-20");
            plusminus.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                int cure3=operand.top();
                operand.pop();
                operand.push(-cure3);
                int cure2=operand.top();
                cure=String.valueOf(cure2);
                drawcure();    
            }
            @Override
            public void mousePressed(MouseEvent e)
            {
            }
            @Override
            public void mouseReleased(MouseEvent e)
            {
            }
            @Override
            public void mouseEntered(MouseEvent e)
            {
            }
            @Override
            public void mouseExited(MouseEvent e)
            {
            }
        });
    }
    private void drawdivide(){
            GRect divide = new GRect(boxwidth,boxheight);
            add(divide,40,150);
            GLabel divides1 = new GLabel("/");
            add(divides1,40+(boxwidth-divides1.getWidth())/2,150+(boxheight+divides1.getHeight())/2);
            divides1.setFont("COMIC SANS MS-20");
            divide.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                cure = "/";
                operator.push(cure);
                drawcure(); 
                
            }

            @Override
            public void mousePressed(MouseEvent e)
            {
            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
            }

            @Override
            public void mouseEntered(MouseEvent e)
            {
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
            }
        });
            
            
    }
    private void drawthree(){
            GRect three = new GRect(boxwidth,boxheight);
            add(three,190-boxwidth,60);
            GLabel three1 = new GLabel("3");
            add(three1,190-(boxwidth+three1.getWidth())/2,60+(boxheight+three1.getHeight())/2);
            three1.setFont("COMIC SANS MS-20");
            three.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                cure = "3";
                operand.push(3);
                drawcure();    
            }
            @Override
            public void mousePressed(MouseEvent e)
            {
            }
            @Override
            public void mouseReleased(MouseEvent e)
            {
            }
            @Override
            public void mouseEntered(MouseEvent e)
            {
            }
            @Override
            public void mouseExited(MouseEvent e)
            {
            }
        });
    }
    private void drawsix(){
            GRect six = new GRect(boxwidth,boxheight);
            add(six,190-boxwidth,90);
            GLabel six1 = new GLabel("6");
            add(six1,190-(boxwidth+six1.getWidth())/2,90+(boxheight+six1.getHeight())/2);
            six1.setFont("COMIC SANS MS-20");
            six.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                cure = "6";
                operand.push(6);
                drawcure();    
            }
            @Override
            public void mousePressed(MouseEvent e)
            {
            }
            @Override
            public void mouseReleased(MouseEvent e)
            {
            }
            @Override
            public void mouseEntered(MouseEvent e)
            {
            }
            @Override
            public void mouseExited(MouseEvent e)
            {
            }
        });
    }
      private void drawnine(){
            GRect nine = new GRect(boxwidth,boxheight);
            add(nine,190-boxwidth,120);
            GLabel nine1 = new GLabel("9");
            add(nine1,190-(boxwidth+nine1.getWidth())/2,120+(boxheight+nine1.getHeight())/2);
            nine1.setFont("COMIC SANS MS-20");
            nine.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                cure = "9";
                operand.push(9);
                drawcure();    
            }
            @Override
            public void mousePressed(MouseEvent e)
            {
            }
            @Override
            public void mouseReleased(MouseEvent e)
            {
            }
            @Override
            public void mouseEntered(MouseEvent e)
            {
            }
            @Override
            public void mouseExited(MouseEvent e)
            {
            }
        });
    }
      private void drawcancel(){
            GRect cancel = new GRect(boxwidth,boxheight);
            add(cancel,190-boxwidth,150);
            GLabel cancel1 = new GLabel("C");
            add(cancel1,190-(boxwidth+cancel1.getWidth())/2,150+(boxheight+cancel1.getHeight())/2);
            cancel1.setFont("COMIC SANS MS-20"); 
    }
      private void drawtwo(){
            GRect two = new GRect(boxwidth,boxheight);
            add(two,190-boxwidth*2,60);
            GLabel two1 = new GLabel("2");
            add(two1,160-(boxwidth+two1.getWidth())/2,60+(boxheight+two1.getHeight())/2);
            two1.setFont("COMIC SANS MS-20");
            two.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                cure = "2";
                operand.push(2);
                drawcure();    
            }
            @Override
            public void mousePressed(MouseEvent e)
            {
            }
            @Override
            public void mouseReleased(MouseEvent e)
            {
            }
            @Override
            public void mouseEntered(MouseEvent e)
            {
            }
            @Override
            public void mouseExited(MouseEvent e)
            {
            }
        });
    }
        private void drawfive(){
            GRect five = new GRect(boxwidth,boxheight);
            add(five,190-boxwidth*2,90);
            GLabel five1 = new GLabel("5");
            add(five1,160-(boxwidth+five1.getWidth())/2,90+(boxheight+five1.getHeight())/2);
            five1.setFont("COMIC SANS MS-20");
            five.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                cure = "5";
                operand.push(5);
                drawcure();    
            }
            @Override
            public void mousePressed(MouseEvent e)
            {
            }
            @Override
            public void mouseReleased(MouseEvent e)
            {
            }
            @Override
            public void mouseEntered(MouseEvent e)
            {
            }
            @Override
            public void mouseExited(MouseEvent e)
            {
            }
        });
    }
        private void draweight(){
            GRect eight = new GRect(boxwidth,boxheight);
            add(eight,190-boxwidth*2,120);
            GLabel eight1 = new GLabel("8");
            add(eight1,160-(boxwidth+eight1.getWidth())/2,120+(boxheight+eight1.getHeight())/2);
            eight1.setFont("COMIC SANS MS-20");
            eight.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                cure = "8";
                operand.push(8);
                drawcure();    
            }
            @Override
            public void mousePressed(MouseEvent e)
            {
            }
            @Override
            public void mouseReleased(MouseEvent e)
            {
            }
            @Override
            public void mouseEntered(MouseEvent e)
            {
            }
            @Override
            public void mouseExited(MouseEvent e)
            {
            }
        });
    }
        private void drawdot(){
            GRect dot = new GRect(boxwidth,boxheight);
            add(dot,190-boxwidth*2,150);
            GLabel dot1 = new GLabel(".");
            add(dot1,160-(boxwidth+dot1.getWidth())/2,150+(boxheight+dot1.getHeight())/2);
            dot1.setFont("COMIC SANS MS-30"); 
    }
        private void drawone(){
            GRect one = new GRect(boxwidth,boxheight);
            add(one,190-boxwidth*3,60);
            GLabel one1 = new GLabel("1");
            add(one1,130-(boxwidth+one1.getWidth())/2,60+(boxheight+one1.getHeight())/2);
            one1.setFont("COMIC SANS MS-20");
            one.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                cure = "1";
                operand.push(1);
                drawcure();    
            }
            @Override
            public void mousePressed(MouseEvent e)
            {
            }
            @Override
            public void mouseReleased(MouseEvent e)
            {
            }
            @Override
            public void mouseEntered(MouseEvent e)
            {
            }
            @Override
            public void mouseExited(MouseEvent e)
            {
            }
        });
    }
        private void drawfour(){
            GRect four = new GRect(boxwidth,boxheight);
            add(four,190-boxwidth*3,90);
            GLabel four1 = new GLabel("4");
            add(four1,130-(boxwidth+four1.getWidth())/2,90+(boxheight+four1.getHeight())/2);
            four1.setFont("COMIC SANS MS-20");
            four.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                cure = "4";
                operand.push(4);
                drawcure();    
            }
            @Override
            public void mousePressed(MouseEvent e)
            {
            }
            @Override
            public void mouseReleased(MouseEvent e)
            {
            }
            @Override
            public void mouseEntered(MouseEvent e)
            {
            }
            @Override
            public void mouseExited(MouseEvent e)
            {
            }
        });
    }
        private void drawseven(){
            GRect seven = new GRect(boxwidth,boxheight);
            add(seven,190-boxwidth*3,120);
            GLabel seven1 = new GLabel("7");
            add(seven1,130-(boxwidth+seven1.getWidth())/2,120+(boxheight+seven1.getHeight())/2);
            seven1.setFont("COMIC SANS MS-20"); 
            seven.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                cure = "7";
                operand.push(7);
                drawcure();

            }

            @Override
            public void mousePressed(MouseEvent e)
            {
            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
            }

            @Override
            public void mouseEntered(MouseEvent e)
            {
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
            }
        });
    }
        private void drawzero(){
            GRect zero = new GRect(boxwidth,boxheight);
            add(zero,190-boxwidth*3,150);
            GLabel zero1 = new GLabel("0");
            add(zero1,130-(boxwidth+zero1.getWidth())/2,150+(boxheight+zero1.getHeight())/2);
            zero1.setFont("COMIC SANS MS-20"); 
            zero.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                cure = "0";
                operand.push(0);
                drawcure();    
            }
            @Override
            public void mousePressed(MouseEvent e)
            {
            }
            @Override
            public void mouseReleased(MouseEvent e)
            {
            }
            @Override
            public void mouseEntered(MouseEvent e)
            {
            }
            @Override
            public void mouseExited(MouseEvent e)
            {
            }
        });
    }
        private void drawcure(){
         if(cure1!= null)
           remove(cure1);
        cure1 = new GLabel(cure);
        cure1.setFont("COMIC SANS MS-30");
 
        add(cure1,160,40);

    }
        private int ze=0;
        private void doit(){
            while(operand.size()!=1){
             x = operand.top();
             operand.pop();
             y = operand.top();
            operand.pop();
            String op =operator.top();
            operator.pop();
            if("+".equals(op)){
             ze=x+y;
            }
            else if("-".equals(op)){
                ze=y-x;
            }
            else if("*".equals(op)){
                ze=y*x;
            }
            else if("/".equals(op)){
                ze=y/x;
            }
            operand.push(ze);
          if(cure1!= null)
           remove(cure1);
        cure1 = new GLabel(String.valueOf(ze));
        cure1.setFont("COMIC SANS MS-30");
             add(cure1,140,40);
            }
            
        }
                        
    public void run(){
            operand.createList();
            operator.createList();
            answer = new GRect(150,40);
            add(answer,40,10);
            addMouseListeners();
                drawplus();
                drawminus();
                drawtimes();
                drawdivide();
                drawthree();
                drawsix();
                drawnine();
                drawcancel();
                drawtwo();
                drawfive();
                draweight();
                drawdot();
                drawone();
                drawfour();
                drawseven();
                drawzero();
                drawequal();
                plusminus();
                


            
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Calc().start(args);
        // TODO code application logic here
    }
    
}
