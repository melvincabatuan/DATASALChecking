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
import java.awt.event.*;
import java.awt.*;

public class Calculator extends GraphicsProgram{
    stack s = new stack();
    stack s2 = new stack();
    private calculatorcanvas canvas;
    String let ="";
    double l=0;
    public double res(char let,double x,double y){
        if(let=='+'){   
            double res = x+y;
            return res;    
        }
        else if(let=='-'){   
            double res = x-y;
            return res;    
        }
        else if(let=='*'){   
            double res = x*y;
            return res;
        }
        else if(let=='/'){   
            double res = x/y;
            return res;
        }
        else return 0;
    }
    public void mousePressed(MouseEvent me){
        GObject test = getElementAt(me.getX(),me.getY());
        if(test == canvas.one){
            if(s2.empty())s2.push(1);
            else{
                if(s2.gettop()>=0) l =(s2.gettop()*10)+1;
                else l =(s2.gettop()*10)-1;
                s2.pop();
                s2.push(l);
            }
            canvas.one.setFilled(true);
            canvas.one.setFillColor(Color.gray);
        }
        else if(test == canvas.two){
            if(s2.empty())s2.push(2);
            else{
                if(s2.gettop()>=0) l =(s2.gettop()*10)+2;
                else l =(s2.gettop()*10)-2;
                s2.pop();
                s2.push(l);
            }
            canvas.two.setFilled(true);
            canvas.two.setFillColor(Color.gray);
        }
        else if(test == canvas.three){
            if(s2.empty())s2.push(3);
            else{
                if(s2.gettop()>=0) l =(s2.gettop()*10)+3;
                else l =(s2.gettop()*10)-3;
                s2.pop();
                s2.push(l);
            }
            canvas.three.setFilled(true);
            canvas.three.setFillColor(Color.gray);
        }
        else if(test == canvas.four){
            if(s2.empty())s2.push(4);
            else{
                if(s2.gettop()>=0) l =(s2.gettop()*10)+4;
                else l =(s2.gettop()*10)-4;
                s2.pop();
                s2.push(l);
            }
            canvas.four.setFilled(true);
            canvas.four.setFillColor(Color.gray);
        }
        else if(test == canvas.five){
            if(s2.empty())s2.push(5);
            else{
                if(s2.gettop()>=0) l =(s2.gettop()*10)+5;
                else l =(s2.gettop()*10)-5;
                s2.pop();
                s2.push(l);
            }
            canvas.five.setFilled(true);
            canvas.five.setFillColor(Color.gray);
        }
        else if(test == canvas.six){
            if(s2.empty())s2.push(6);
            else{
                if(s2.gettop()>=0) l =(s2.gettop()*10)+6;
                else l =(s2.gettop()*10)-6;
                s2.pop();
                s2.push(l);
            }
            canvas.six.setFilled(true);
            canvas.six.setFillColor(Color.gray);
        }
        else if(test == canvas.seven){
            if(s2.empty())s2.push(7);
            else{
                if(s2.gettop()>=0) l =(s2.gettop()*10)+7;
                else l =(s2.gettop()*10)-7;
                s2.pop();
                s2.push(l);
            }
            canvas.seven.setFilled(true);
            canvas.seven.setFillColor(Color.gray);
        }
        else if(test == canvas.eight){
            if(s2.empty())s2.push(8);
            else{
                if(s2.gettop()>=0) l =(s2.gettop()*10)+8;
                else l =(s2.gettop()*10)-8;
                s2.pop();
                s2.push(l);
            }
            canvas.eight.setFilled(true);
            canvas.eight.setFillColor(Color.gray);
        }
        else if(test == canvas.nine){
            if(s2.empty())s2.push(9);
            else{
                if(s2.gettop()>=0) l =(s2.gettop()*10)+9;
                else l =(s2.gettop()*10)-9;
                s2.pop();
                s2.push(l);
            }
            canvas.nine.setFilled(true);
            canvas.nine.setFillColor(Color.gray);
        }
        else if(test == canvas.zero){
            if(s2.empty())s2.push(0);
            else{
                double x =(s2.gettop()*10);
                s2.pop();
                s2.push(x);
            }
        }
        else if(test==canvas.plus){
           canvas.display.setLabel(""+0);
           if(s.empty()){
            s.push(s2.gettop());
           }
           s2.pop();
           let = "+";
        }
        else if(test==canvas.minus){
           canvas.display.setLabel(""+0);
           if(s.empty()){
           s.push(s2.gettop()); 
           }
           s2.pop();
           let = "-";
        }
        else if(test==canvas.times){
           canvas.display.setLabel(""+0);
           if(s.empty()){
           s.push(s2.gettop()); 
           }
           s2.pop();
           let = "*";
        }
        else if(test==canvas.divide){
           canvas.display.setLabel(""+0);
           if(s.empty()){
           s.push(s2.gettop()); 
           }
           s2.pop();
           let = "/";
        }
        else if(test==canvas.equals){
          
          s.push(s2.gettop());
          s2.pop();
           double y = s.gettop();
           s.pop();
           double x = s.gettop();
           s.pop();
           s2.push(res(let.charAt(0),x,y));
        }
        if(test!=canvas.plus && test!= canvas.equals && test!=canvas.minus && test!=canvas.times && test!=canvas.divide){
            canvas.display.setLabel(""+s2.gettop());
        }
        else if(test==canvas.equals){
            canvas.display.setLabel(""+s2.gettop());
        }
    }
    public void mouseReleased(MouseEvent me){
      canvas.one.setFilled(false);
      canvas.two.setFilled(false); 
      canvas.three.setFilled(false); 
      canvas.four.setFilled(false); 
      canvas.five.setFilled(false); 
      canvas.six.setFilled(false); 
      canvas.seven.setFilled(false); 
      canvas.eight.setFilled(false); 
      canvas.nine.setFilled(false); 
      canvas.one.setFilled(false); 
    }
    public void drawdisplayWall(){
              for(int i=0; i<4; i++)
                for(int j = 0; j<4;j++)
                    drawdisplay(i,j);
            }
    public GLabel drawdisplay(int row, int col){
        double x ,y;
        GLabel num = new GLabel("");
        num.setFont("COMIC SANS MS-30");
        x = 25 + 50*row;
        y = 130 + 50*col;
        setnum(num,col,row);
        add(num,x,y);
        return num;
    }
    public void setnum(GLabel num,int row,int col){
       if(row==0&&col==0) num.setLabel("1");
       if(row==0&&col==1) num.setLabel("2");
       if(row==0&&col==2) num.setLabel("3");
       if(row==0&&col==3) num.setLabel("+");
       if(row==1&&col==0) num.setLabel("4");
       if(row==1&&col==1) num.setLabel("5");
       if(row==1&&col==2) num.setLabel("6");
       if(row==1&&col==3) num.setLabel("-");
       if(row==2&&col==0) num.setLabel("7");
       if(row==2&&col==1) num.setLabel("8");
       if(row==2&&col==2) num.setLabel("9");
       if(row==2&&col==3) num.setLabel("*");
       if(row==3&&col==1) num.setLabel("0");
       if(row==3&&col==3) num.setLabel("/");
    }
    public void init(){
        canvas = new calculatorcanvas();
        //add(canvas);
        addMouseListeners();
        drawdisplayWall();
        add(canvas.one);
        add(canvas.two);
        add(canvas.three);
        add(canvas.four);
        add(canvas.five);
        add(canvas.six);
        add(canvas.seven);
        add(canvas.eight);
        add(canvas.nine);
        add(canvas.zero);
        add(canvas.plus);
        add(canvas.equals);
        add(canvas.minus);
        add(canvas.times);
        add(canvas.divide);
        add(canvas.display,10,100);
        
    }
    public void run(){
       /*s.push(readInt());
       String operand = readLine();
       s.push(readInt());
           double x = s.gettop();
           s.pop();
           double y = s.gettop();
           s.pop();
           
          for(;;){
           double res= res(operand.charAt(0),x,y);
           println(res);
           s.push(res);
           operand = readLine();
           s.push(readInt());
           x = s.gettop();
           s.pop();
           y = s.gettop();
           s.pop();
       }*/
       //canvas testing below
       for(;;){
           
       }
       
    }
    public static void main(String[] args) {
        // TODO code application logic here
        new Calculator().start(args);
    }
    
}
