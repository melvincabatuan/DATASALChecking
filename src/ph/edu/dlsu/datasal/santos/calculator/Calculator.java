package ph.edu.dlsu.datasal.santos.calculator;

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.util.Stack;
import java.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Calculator extends GraphicsProgram {
    
    private final int WIDTH = 260;
    private final int HEIGHT = 430;
    private static final int PAUSE = 1;
    private GLabel show = new GLabel("", 13,50);
    private String acc="";
    private String inf="";
    private String post;
    
    public void init(){
        show.setFont("Arial-40");
        show.setColor(Color.DARK_GRAY);
        this.resize(WIDTH,HEIGHT);
        pause(PAUSE);
        screen();
        addbuttons();
    }
    
    public void run(){
        
    }
    
    private void screen(){
        GRect scr= new GRect(225,50);
        scr.setFillColor(Color.LIGHT_GRAY);
        scr.setFilled(true);
        scr.setColor(Color.GRAY);
        add(scr, 10, 10);
        addMouseListeners();
        add(show);
    }
    
    public synchronized void addMouseListener(MouseListener l){
        super.addMouseListener(l);
    }
    
    
    
    public void mouseClicked(MouseEvent me){
        
    }
    
    public void addbuttons(){
        plus();
        min();
        mul();
        div();
        cha();
        n1();
        n2();
        n3();
        n4();
        n5();
        n6();
        n7();
        n8();
        n9();
        n0();
        dot();
        c();
        eq2();
    }
    
    private void plus(){
        GImage img = new GImage("plus.png");
        add(img, 10, 80);
        img.addMouseListener(new MouseAdapter(){
        
        @Override
        public void mousePressed(MouseEvent e){
            img.setImage("clicked.png");
        }
        @Override
        public void mouseReleased(MouseEvent e){
            img.setImage("plus.png");
            remove(show);
            inf = inf + "+";
            acc = acc + "+";
            show.setLabel(acc + "");
            add(show);
        }
        });
    }
    
    private void min(){
        GImage img = new GImage("min.png");
        add(img, 10, 134);
        img.addMouseListener(new MouseAdapter(){
        
        @Override
        public void mousePressed(MouseEvent e){
            img.setImage("clicked.png");
        }
        @Override
        public void mouseReleased(MouseEvent e){
            img.setImage("min.png");
            remove(show);
            inf = inf + "-";
            acc = acc + "-";
            show.setLabel(acc + "");
            add(show);
        }
        });
    }    
    
    private void mul(){
        GImage img = new GImage("mul.png");
        add(img, 10, 188);
        img.addMouseListener(new MouseAdapter(){
        
        @Override
        public void mousePressed(MouseEvent e){
            img.setImage("clicked.png");
        }
        @Override
        public void mouseReleased(MouseEvent e){
            img.setImage("mul.png");
            remove(show);
            inf = inf + "*";
            acc = acc + "*";
            show.setLabel(acc + "");
            add(show);
        }
        });
    }  
    
    private void div(){
        GImage img = new GImage("div.png");
        add(img, 10, 242);
        img.addMouseListener(new MouseAdapter(){
        
        @Override
        public void mousePressed(MouseEvent e){
            img.setImage("clicked.png");
        }
        @Override
        public void mouseReleased(MouseEvent e){
            img.setImage("div.png");
            remove(show);
            inf = inf + "/";
            acc = acc + "/";
            show.setLabel(acc + "");
            add(show);
        }
        });
    }   
    
    private void cha(){
        GImage img = new GImage("cha.png");
        add(img, 10, 298);
        img.addMouseListener(new MouseAdapter(){
        
        @Override
        public void mousePressed(MouseEvent e){ 
            img.setImage("clicked.png");
        }
        @Override
        public void mouseReleased(MouseEvent e){
            img.setImage("cha.png");
        }
        });
    }       
    
    private void n1(){
        GImage img = new GImage("1.png");
        add(img, 68, 80);
        img.addMouseListener(new MouseAdapter(){

        @Override
        public void mousePressed(MouseEvent e){
            img.setImage("clicked.png");
        }
        @Override
        public void mouseReleased(MouseEvent e){
            img.setImage("1.png");
            remove(show);
            inf = inf + "1";
            acc = acc + "1";
            show.setLabel(acc + "");
            add(show);
        }
        });
    }        
    
    private void n2(){
        GImage img = new GImage("2.png");
        add(img, 124, 80);
        img.addMouseListener(new MouseAdapter(){
        
        @Override
        public void mousePressed(MouseEvent e){
            img.setImage("clicked.png");
        }
        @Override
        public void mouseReleased(MouseEvent e){
            img.setImage("2.png");
            remove(show);
            inf = inf + "2";
            acc = acc + "2";
            show.setLabel(acc + "");
            add(show);
        }
        });
    }    
    
    private void n3(){
        GImage img = new GImage("3.png");
        add(img, 180, 80);
        img.addMouseListener(new MouseAdapter(){
        
        @Override
        public void mousePressed(MouseEvent e){
            img.setImage("clicked.png");
        }
        @Override
        public void mouseReleased(MouseEvent e){
            img.setImage("3.png");
            remove(show);
            inf = inf + "3";
            acc = acc + "3";
            show.setLabel(acc + "");
            add(show);
        }
        });
    } 
    
    private void n4(){
        GImage img = new GImage("4.png");
        add(img, 68, 134);
        img.addMouseListener(new MouseAdapter(){
        
        @Override
        public void mousePressed(MouseEvent e){
            img.setImage("clicked.png");
        }
        @Override
        public void mouseReleased(MouseEvent e){
            img.setImage("4.png");
            remove(show);
            inf = inf + "4";
            acc = acc + "4";
            show.setLabel(acc + "");
            add(show);
        }
        });
    }   
    
    private void n5(){
        GImage img = new GImage("5.png");
        add(img, 124, 134);
        img.addMouseListener(new MouseAdapter(){
        
        @Override
        public void mousePressed(MouseEvent e){
            img.setImage("clicked.png");
        }
        @Override
        public void mouseReleased(MouseEvent e){
            img.setImage("5.png");
            remove(show);
            inf = inf + "5";
            acc = acc + "5";
            show.setLabel(acc + "");
            add(show);
        }
        });
    } 
    
    private void n6(){
        GImage img = new GImage("6.png");
        add(img, 180, 134);
        img.addMouseListener(new MouseAdapter(){
        
        @Override
        public void mousePressed(MouseEvent e){
            img.setImage("clicked.png");
        }
        @Override
        public void mouseReleased(MouseEvent e){
            img.setImage("6.png");
            remove(show);
            inf = inf + "6";
            acc = acc + "6";
            show.setLabel(acc + "");
            add(show);
        }
        });
    } 
    
    private void n7(){
        GImage img = new GImage("7.png");
        add(img, 68, 188);
        img.addMouseListener(new MouseAdapter(){
        
        @Override
        public void mousePressed(MouseEvent e){
            img.setImage("clicked.png");
        }
        @Override
        public void mouseReleased(MouseEvent e){
            img.setImage("7.png");
            remove(show);
            inf = inf + "7";
            acc = acc + "7";
            show.setLabel(acc + "");
            add(show);
        }
        });
    } 
    
    private void n8(){
        GImage img = new GImage("8.png");
        add(img, 124, 188);
        img.addMouseListener(new MouseAdapter(){
        
        @Override
        public void mousePressed(MouseEvent e){
            img.setImage("clicked.png");
        }
        @Override
        public void mouseReleased(MouseEvent e){
            img.setImage("8.png");
            remove(show);
            inf = inf + "8";
            acc = acc + "8";
            show.setLabel(acc + "");
            add(show);
        }
        });
    } 
    
    private void n9(){
        GImage img = new GImage("9.png");
        add(img, 180, 188);
        img.addMouseListener(new MouseAdapter(){
        
        @Override
        public void mousePressed(MouseEvent e){
            img.setImage("clicked.png");
        }
        @Override
        public void mouseReleased(MouseEvent e){
            img.setImage("9.png");
            remove(show);
            inf = inf + "9";
            acc = acc + "9";
            show.setLabel(acc + "");
            add(show);
        }
        });
    } 
    
    private void n0(){
        GImage img = new GImage("0.png");
        add(img, 68, 242);
        img.addMouseListener(new MouseAdapter(){
        
        @Override
        public void mousePressed(MouseEvent e){
            img.setImage("clicked.png");
        }
        @Override
        public void mouseReleased(MouseEvent e){
            img.setImage("0.png");
            remove(show);
            inf = inf + "0";
            acc = acc + "0";
            show.setLabel(acc + "");
            add(show);
        }
        });
    }    
    
    private void dot(){
        GImage img = new GImage("dot.png");
        add(img, 124, 242);
        img.addMouseListener(new MouseAdapter(){
        
        @Override
        public void mousePressed(MouseEvent e){
            img.setImage("clicked.png");
        }
        @Override
        public void mouseReleased(MouseEvent e){
            img.setImage("dot.png");
            remove(show);
            inf = inf + ".";
            acc = acc + ".";
            show.setLabel(acc + "");
            add(show);
        }
        });
    }     
    
    private void c(){
        GImage img = new GImage("c.png");
        add(img, 180, 242);
        img.addMouseListener(new MouseAdapter(){
        
        @Override
        public void mousePressed(MouseEvent e){
            img.setImage("clicked.png");
        }
        @Override
        public void mouseReleased(MouseEvent e){
            img.setImage("c.png");
            remove(show);
            inf = "";
            acc = "";
            show.setLabel("");
            add(show);
        }
        });
    }  
    
    private void eq2(){
        GImage img = new GImage("eq2.png");
        add(img, 68, 298);
        img.addMouseListener(new MouseAdapter(){
        
        @Override
        public void mousePressed(MouseEvent e){
            img.setImage("clicked2.png");
        }
        @Override
        public void mouseReleased(MouseEvent e){
            img.setImage("eq2.png");
        }
        });
    }  
    
    public static void main(String[] args) {
       new Calculator().start(args);
    }
}
