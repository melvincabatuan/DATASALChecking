/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.kitane.graphics;
import acm.graphics.*;
import acm.program.*;
import java.awt.*;
import ph.edu.dlsu.kitane.datasalapp.FacePamphletConstants;
/**
 *
 * @author ChristophJohnEric
 */
public class Graphics extends GraphicsProgram implements FacePamphletConstants{
    private double getXCenter(GObject g){
        return (APPLICATION_WIDTH-g.getWidth())/2;
    }
    private double getYCenter(GObject g){
        return (APPLICATION_HEIGHT-g.getHeight())/2;
    }
    public void run(){
        System.out.println("Graphics has been called.");
        setBackground(Color.GRAY);
        //1
        GRect r1 = new GRect(300,300);
        r1.setColor(Color.yellow);
        r1.setFilled(true);
        add(r1,getXCenter(r1)-r1.getWidth()/2,getYCenter(r1));
        //4
        GOval o1 = new GOval(50,50);
        o1.setColor(Color.BLACK);
        add(o1,getXCenter(o1)-r1.getWidth()/2,getYCenter(o1));
        //5
        GOval o2 = new GOval(48,48);
        o2.setColor(Color.BLACK);
        add(o2,getXCenter(o2)-r1.getWidth()/2,getYCenter(o2));
        //6
        GArc a1 = new GArc(0,0,200,200,0,-180);
        a1.setColor(Color.BLACK);
        a1.setFilled(true);
        add(a1,getXCenter(a1)-r1.getWidth()/2,getYCenter(a1)-10);
        //7
        GRect r4 = new GRect(25,50);
        r4.setColor(Color.white);
        r4.setFilled(true);
        add(r4,getXCenter(r4)-(r1.getWidth()/2)-r4.getWidth(),getYCenter(r4)+getYCenter(r4)/5);
        //8
        GRect r5 = new GRect(25,50);
        r5.setColor(Color.white);
        r5.setFilled(true);
        add(r5,getXCenter(r5)-(r1.getWidth()/2)+r5.getWidth(),getYCenter(r5)+getYCenter(r5)/5);
        //11
        GOval o3 = new GOval(80,80);
        o3.setColor(Color.white);
        o3.setFilled(true);
        add(o3,getXCenter(o3)-(r1.getWidth()/2)+(o3.getWidth()/2),getYCenter(r1)+(o3.getHeight()/2));
        //12
        GOval o4 = new GOval(80,80);
        o4.setColor(Color.white);
        o4.setFilled(true);
        add(o4,getXCenter(o4)-(r1.getWidth()/2)-(o4.getWidth()/2),getYCenter(r1)+(o4.getHeight()/2));
        //13
        GOval o5 = new GOval(80,80);
        o5.setColor(Color.BLACK);
        add(o5,getXCenter(o5)-(r1.getWidth()/2)-(o5.getWidth()/2),getYCenter(r1)+(o5.getHeight()/2));
        //15
        GOval o7 = new GOval(80,80);
        o7.setColor(Color.BLACK);
        add(o7,getXCenter(o7)-(r1.getWidth()/2)+(o7.getWidth()/2),getYCenter(r1)+(o7.getHeight()/2));
        //17
        GOval o9 = new GOval(30,30);
        o9.setColor(Color.black);
        o9.setFilled(true);
        add(o9,(o4.getX()+o4.getWidth()/2),(o4.getY()+o4.getHeight()/3));
        //18
        GOval o10 = new GOval(30,30);
        o10.setColor(Color.black);
        o10.setFilled(true);
        add(o10,(o3.getX()+o3.getWidth()/6),(o3.getY()+o3.getHeight()/3));
        //19
        GOval o11 = new GOval(24,24);
        o11.setColor(Color.blue);
        o11.setFilled(true);
        add(o11,(o4.getX()+o4.getWidth()/2)+3,(o4.getY()+o4.getHeight()/3)+3);
        //20
        GOval o12 = new GOval(24,24);
        o12.setColor(Color.blue);
        o12.setFilled(true);
        add(o12,(o3.getX()+o3.getWidth()/6)+3,(o3.getY()+o3.getHeight()/3)+3);
        //21
        GOval o13 = new GOval(16,16);
        o13.setColor(Color.black);
        o13.setFilled(true);
        add(o13,(o4.getX()+o4.getWidth()/2)+7,(o4.getY()+o4.getHeight()/3)+7);
        //22
        GOval o14 = new GOval(16,16);
        o14.setColor(Color.black);
        o14.setFilled(true);
        add(o14,(o3.getX()+o3.getWidth()/6)+7,(o3.getY()+o3.getHeight()/3)+7);
        //@author: Christoph Kitane
    }
    /*public static void main(String[] args) {
        new Graphics().start(args);
    }*/
    
}
