package ph.edu.dlsu.datasal.parro.calculator;

import acm.graphics.GLabel; 
import acm.graphics.GObject; 
import acm.graphics.GRect; 
import acm.graphics.GRoundRect; 
import acm.program.*;

import java.awt.*;
import java.awt.event.MouseAdapter; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener;


public class Calculator extends GraphicsProgram{
    
    private GLabel show = new GLabel("",  35, 35); 
    private String acc  = "";
    
    public synchronized void addMouseListener(MouseListener l) {
        super.addMouseListener(l);
    }
    
    public void mouseClicked(MouseEvent me){
        
        if (20 <= me.getX() && me.getX() <= 55) {

            if (47 <= me.getY() && me.getY() <= 82) {
            remove(show); 
            //infix = infix + "+"; 
            acc  = "";
            //show.setLabel(acc);
            add(show);
            }

            else if (82 <= me.getY() && me.getY() <= 117) {
            remove(show); 
            //infix = infix + "-";
            acc  =  "";
            //show.setLabel(acc);
            add(show);
            }

            else if (117 <= me.getY() && me.getY() <= 152) {
            remove(show); 
            //infix = infix + "*"; 
            acc  = "";
            //show.setLabel(acc);
            add(show);
            }

            else if (152 <= me.getY() && me.getY() <= 187) {
            remove(show); 
            //infix = infix + "/"; 
            acc  =  "";
            // show.setLabel(acc);
            add(show);
            }
        }//show.setLabel(acc) -> normal
    
        if (62 <= me.getX() && me.getX() <= 97) {
            
            if (47 <= me.getY() && me.getY() <= 82) {
            remove(show); 
            //infix = infix + "1"; 
            acc  = acc  + "1";
            show.setLabel(acc + "");
            add(show);
            }
            
            if (82 <= me.getY() && me.getY() <= 117) {
            remove(show); 
            //infix = infix + "4"; 
            acc  = acc  + "4";
            show.setLabel(acc + "");
            add(show);
            }
            
            if (117 <= me.getY() && me.getY() <= 152) {
            remove(show); 
            //infix = infix + "7"; 
            acc  = acc  + "7";
            show.setLabel(acc + "");
            add(show);
            }
            
            if (152 <= me.getY() && me.getY() <= 187) {
            remove(show); 
            //infix = infix + "0"; 
            acc  = acc  + "0";
            show.setLabel(acc + "");
            add(show);
            }

            if (194 <= me.getY() && me.getY() <= 229) {
            remove(show);
            //infix2Postfix = new ToPostFix();
            //postfix = infix2Postfix.calcValue(infix);
            //acc  = "" + postfix; show.setLabel(acc); add(show);
            } 
        }
        
        if (97 <= me.getX() && me.getX() <= 132) {
            
            if (47 <= me.getY() && me.getY() <= 82) {
            remove(show); 
            //infix = infix + "2"; 
            acc  = acc  + "2";
            show.setLabel(acc + "");
            add(show);
            }
            
            if (82 <= me.getY() && me.getY() <= 117) {
            remove(show); 
            //infix = infix + "5"; 
            acc  = acc  + "5";
            show.setLabel(acc + "");
            add(show);
            }
            
            if (117 <= me.getY() && me.getY() <= 152) {
            remove(show); 
            //infix = infix + "8"; 
            acc  = acc  + "8";
            show.setLabel(acc + "");
            add(show);
            }
            
            if (152 <= me.getY() && me.getY() <= 187) {
            remove(show); 
            //infix = infix + "."; 
            acc  = acc  + ".";
            show.setLabel(acc + "");
            add(show);
            }
            
            if (194 <= me.getY() && me.getY() <= 229) {
            remove(show);
            //infix2Postfix = new ToPostFix();
            //postfix = infix2Postfix.calcValue(infix);
            //acc  = "" + postfix; 
            show.setLabel(acc); 
            add(show);
            }
        }
    
        if (132 <= me.getX() && me.getX() <= 167) {

            if (47 <= me.getY() && me.getY() <= 82) {
            remove(show); 
            //infix = infix + "3"; 
            acc  = acc  + "3";
            show.setLabel(acc + "");
            add(show);
            }

            if (82 <= me.getY() && me.getY() <= 117) {
            remove(show); 
            //infix = infix + "6"; 
            acc  = acc  + "6";
            show.setLabel(acc + "");
            add(show);
            }

            if (117 <= me.getY() && me.getY() <= 152) {
            remove(show); 
            //infix = infix + "9"; 
            acc  = acc  + "9";
            show.setLabel(acc + "");
            add(show);
            } 

            if (152 <= me.getY() && me.getY() <= 187) {
            remove(show);
            GLabel show = new GLabel("",  155, 35);
            //infix = ""; 
            acc  = ""; 
            show.setLabel(""); 
            add(show);
            }

            if (194 <= me.getY() && me.getY() <= 229) {
            remove(show);
            //infix2Postfix = new ToPostFix();
            //postfix = infix2Postfix.calcValue(infix);
            //acc  = "" + postfix; 
            show.setLabel(acc); 
            add(show);
            }
        }
    }
    
    public void init(){
        setBackground(Color.BLACK); 
        setSize(205, 310);
        Screen();
        Plus();
        Minus();
        Multiply();
        Division();
        PlusMinus();
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
        Poi();
        Clr();
        Equ();
    }
    
    private void Screen() {
        GRect screen = new GRect(20, 10, 147, 30); 
        screen.setFilled(true); 
        screen.setFillColor(Color.WHITE); 
        add(screen);
        screen.setColor(Color.GRAY);
        addMouseListeners();
    }
    
    
    private void Plus()  {
        final GRoundRect sym  = new GRoundRect(20, 47, 35, 35);
        sym.setFilled(true);
        sym.setFillColor(Color.ORANGE); 
        add(sym);
        GLabel sym1 = new GLabel("+",  32, 72);
        sym1.setFont("Arial-20");
        add(sym1); sym1.setColor(Color.WHITE);
        sym.setColor(Color.BLACK);

        sym.addMouseListener(new MouseAdapter() {

        @Override
        public void mousePressed(MouseEvent e) {
        sym.setFillColor(Color.GREEN);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        sym.setFillColor(Color.ORANGE);
        }
        });    
    }
    
    private void Minus(){
        final GRoundRect sym  = new GRoundRect(20, 82, 35, 35);
        sym.setFilled(true);
        sym.setFillColor(Color.ORANGE);
        add(sym);
        GLabel sym1 = new GLabel("-", 35, 105);
        sym1.setFont("Arial-20");
        add(sym1); sym1.setColor(Color.WHITE);
        sym.setColor(Color.BLACK);

        sym.addMouseListener(new MouseAdapter() {

        @Override
        public void mousePressed(MouseEvent e) {
        sym.setFillColor(Color.GREEN);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        sym.setFillColor(Color.ORANGE);
        }
        });    
    }
    
    private void Multiply() {
        final GRoundRect sym  = new GRoundRect(20, 117, 35, 35);
        sym.setFilled(true); 
        sym.setFillColor(Color.ORANGE); 
        add(sym);
        GLabel sym1 = new GLabel("x", 33, 141);
        sym1.setFont("Arial-20"); add(sym1); 
        sym1.setColor(Color.WHITE); 
        sym.setColor(Color.BLACK);

        sym.addMouseListener(new MouseAdapter() {

        @Override
        public void mousePressed(MouseEvent e) {
        sym.setFillColor(Color.GREEN);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        sym.setFillColor(Color.ORANGE);
        }
        });
    }
    
    private void Division() { 
        final GRoundRect sym  = new GRoundRect(20, 152, 35, 35);
        sym.setFilled(true); 
        sym.setFillColor(Color.ORANGE); 
        add(sym);
        GLabel sym1 = new GLabel("÷", 32, 177);
        sym1.setFont("Arial-20");
        add(sym1); 
        sym1.setColor(Color.WHITE); 
        sym.setColor(Color.BLACK);

        sym.addMouseListener(new MouseAdapter() {

        @Override
        public void mousePressed(MouseEvent e) {
        sym.setFillColor(Color.GREEN);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        sym.setFillColor(Color.ORANGE);
        }
        });
    }
    
    private void PlusMinus() {
        final GRoundRect sym  = new GRoundRect(20, 194, 35, 35);
        sym.setFilled(true); 
        sym.setFillColor(Color.ORANGE); 
        add(sym);
        GLabel sym1 = new GLabel("±", 31, 220);
        sym1.setFont("Arial-20"); 
        add(sym1); 
        sym1.setColor(Color.WHITE); 
        sym.setColor(Color.BLACK);

        sym.addMouseListener(new MouseAdapter() {

        @Override
        public void mousePressed(MouseEvent e) {
        sym.setFillColor(Color.GREEN);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        sym.setFillColor(Color.ORANGE);
        }
        });
    }

    private void Num1() {
        final GRoundRect num = new GRoundRect(62, 47, 35, 35); 
        num.setFilled(true); num.setFillColor(Color.GRAY);
        add(num);
        GLabel num1 = new GLabel("1", 75, 72);
        num1.setFont("Arial-20"); 
        add(num1); 
        num1.setColor(Color.WHITE); 
        num.setColor(Color.BLACK);

        num.addMouseListener(new MouseAdapter() {
        @Override

        public void mousePressed(MouseEvent e) {
        num.setFillColor(Color.LIGHT_GRAY);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        num.setFillColor(Color.GRAY);
        }
        });  
    
    }
    
    private void Num2() {
        final GRoundRect num = new GRoundRect(97, 47, 35, 35); 
        num.setFilled(true); 
        num.setFillColor(Color.GRAY);
        add(num);
        GLabel num1 = new GLabel("2", 110, 72);
        num1.setFont("Arial-20"); 
        add(num1);
        num1.setColor(Color.WHITE); 
        num.setColor(Color.BLACK);

        num.addMouseListener(new MouseAdapter() {
        @Override

        public void mousePressed(MouseEvent e) {
        num.setFillColor(Color.LIGHT_GRAY);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        num.setFillColor(Color.GRAY);
        }
        });  
    }
    
    private void Num3() {
        final GRoundRect num = new GRoundRect(132, 47, 35, 35); 
        num.setFilled(true); 
        num.setFillColor(Color.GRAY);
        add(num);
        GLabel num1 = new GLabel("3", 145, 72);
        num1.setFont("Arial-20"); 
        add(num1); 
        num1.setColor(Color.WHITE); 
        num.setColor(Color.BLACK);

        num.addMouseListener(new MouseAdapter() {
        @Override

        public void mousePressed(MouseEvent e) {
        num.setFillColor(Color.LIGHT_GRAY);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        num.setFillColor(Color.GRAY);
        }
        });
    }
    
    private void Num4() {
        final GRoundRect num = new GRoundRect(62, 82, 35, 35); 
        num.setFilled(true); 
        num.setFillColor(Color.GRAY);
        add(num);
        GLabel num1 = new GLabel("4", 75, 107);
        num1.setFont("Arial-20");
        add(num1); 
        num1.setColor(Color.WHITE);
        num.setColor(Color.BLACK);

        num.addMouseListener(new MouseAdapter() {
        @Override

        public void mousePressed(MouseEvent e) {
        num.setFillColor(Color.LIGHT_GRAY);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        num.setFillColor(Color.GRAY);
        }
        });
    }
    
    private void Num5() {
        final GRoundRect num = new GRoundRect(97, 82, 35, 35); 
        num.setFilled(true);
        num.setFillColor(Color.GRAY);
        add(num);
        GLabel num1 = new GLabel("5", 110, 107);
        num1.setFont("Arial-20"); 
        add(num1); 
        num1.setColor(Color.WHITE); 
        num.setColor(Color.BLACK);

        num.addMouseListener(new MouseAdapter() {
        @Override

        public void mousePressed(MouseEvent e) {
        num.setFillColor(Color.LIGHT_GRAY);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        num.setFillColor(Color.GRAY);
        }
        });
    }
    
    private void Num6() {
        final GRoundRect num = new GRoundRect(132, 82, 35, 35); 
        num.setFilled(true); 
        num.setFillColor(Color.GRAY);
        add(num);
        GLabel num1 = new GLabel("6", 145, 107);
        num1.setFont("Arial-20"); 
        add(num1); 
        num1.setColor(Color.WHITE); 
        num.setColor(Color.BLACK);

        num.addMouseListener(new MouseAdapter() {
        @Override

        public void mousePressed(MouseEvent e) {
        num.setFillColor(Color.LIGHT_GRAY);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        num.setFillColor(Color.GRAY);
        }
        });
    }
    
    private void Num7() {
        final GRoundRect num = new GRoundRect(62, 117, 35, 35); 
        num.setFilled(true); 
        num.setFillColor(Color.GRAY);
        add(num);
        GLabel num1 = new GLabel("7", 75, 142);
        num1.setFont("Arial-20"); 
        add(num1); 
        num1.setColor(Color.WHITE); 
        num.setColor(Color.BLACK);

        num.addMouseListener(new MouseAdapter() {
        @Override

        public void mousePressed(MouseEvent e) {
        num.setFillColor(Color.LIGHT_GRAY);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        num.setFillColor(Color.GRAY);
        }
        });
    }
    
    private void Num8() {
        final GRoundRect num = new GRoundRect(97, 117, 35, 35); 
        num.setFilled(true); 
        num.setFillColor(Color.GRAY);
        add(num);
        GLabel num1 = new GLabel("8", 110, 142);
        num1.setFont("Arial-20"); add(num1); 
        num1.setColor(Color.WHITE); 
        num.setColor(Color.BLACK);

        num.addMouseListener(new MouseAdapter() {
        @Override

        public void mousePressed(MouseEvent e) {
        num.setFillColor(Color.LIGHT_GRAY);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        num.setFillColor(Color.GRAY);
        }
        });
    }
    
    private void Num9() {
        final GRoundRect num = new GRoundRect(132, 117, 35, 35); 
        num.setFilled(true); 
        num.setFillColor(Color.GRAY);
        add(num);
        GLabel num1 = new GLabel("9", 145, 142);
        num1.setFont("Arial-20"); 
        add(num1); 
        num1.setColor(Color.WHITE); 
        num.setColor(Color.BLACK);

        num.addMouseListener(new MouseAdapter() {
        @Override

        public void mousePressed(MouseEvent e) {
        num.setFillColor(Color.LIGHT_GRAY);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        num.setFillColor(Color.GRAY);
        }
        });
    }
    
    private void Num0() {
        final GRoundRect num = new GRoundRect(62, 152, 35, 35); 
        num.setFilled(true); 
        num.setFillColor(Color.GRAY);
        add(num);
        GLabel num1 = new GLabel("0", 75, 177);
        num1.setFont("Arial-20"); 
        add(num1); 
        num1.setColor(Color.WHITE); 
        num.setColor(Color.BLACK);

        num.addMouseListener(new MouseAdapter() {
        @Override

        public void mousePressed(MouseEvent e) {
        num.setFillColor(Color.LIGHT_GRAY);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        num.setFillColor(Color.GRAY);
        }
        });
    }
    
    private void Poi() {
        final GRoundRect poi = new GRoundRect(97, 152, 35, 35);
        poi.setFilled(true); 
        poi.setFillColor(Color.GRAY); 
        add(poi);
        GLabel num1 = new GLabel(".", 112, 177);
        num1.setFont("Arial-20"); 
        add(num1); 
        num1.setColor(Color.WHITE); 
        poi.setColor(Color.BLACK);

        poi.addMouseListener(new MouseAdapter() {
        @Override

        public void mousePressed(MouseEvent e) {
        poi.setFillColor(Color.LIGHT_GRAY);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        poi.setFillColor(Color.GRAY);
        }
        });
    }

    private void Clr() {
        final GRoundRect clr = new GRoundRect(132, 152, 35, 35);
        clr.setFilled(true); 
        clr.setFillColor(Color.GRAY); 
        add(clr);
        GLabel num1 = new GLabel("C", 145, 177);
        num1.setFont("Arial-20"); add(num1); num1.setColor(Color.WHITE); 
        clr.setColor(Color.BLACK);

        clr.addMouseListener(new MouseAdapter() {
        @Override

        public void mousePressed(MouseEvent e) {
        clr.setFillColor(Color.LIGHT_GRAY);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        clr.setFillColor(Color.GRAY);
        }
        });
    }

    private void Equ()  {
        final GRoundRect equ = new GRoundRect(62, 194, 105, 35);
        equ.setFilled(true); 
        equ.setFillColor(Color.BLUE); 
        equ.setColor(Color.BLUE); 
        add(equ);
        GLabel num1 = new GLabel("=",  110, 220); 
        num1.setFont("Arial-20"); 
        add(num1); 
        num1.setColor(Color.WHITE);

        equ.addMouseListener(new MouseAdapter() {

        @Override
        public void mousePressed(MouseEvent e) { 
            equ.setFillColor(Color.CYAN); 
            equ.setColor(Color.CYAN);
        }
        @Override
        public void mouseReleased(MouseEvent e) {
            equ.setFillColor(Color.BLUE);
        }
        });

    }
    
    public static void main(String args[]){
        new Calculator().start();
    }


}
