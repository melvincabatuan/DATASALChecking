package ph.edu.dlsu.velasco.artistry;

/**
 *
 * @author student
 */
import ph.edu.dlsu.velasco.facepamphlet.FacePamphletConstants;
import acm.graphics.*;
import acm.program.*;
import java.awt.Color;

public class Artistry extends GCanvas implements FacePamphletConstants{
    
    public Artistry(){
        System.out.println("Artistry called");
        this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
        this.setBackground(Color.green);
        GRect rect = new GRect(120,100);
        rect.setFilled(true);
        rect.setFillColor(Color.BLACK);
        add(rect,120,120);
        GOval oval = new GOval(200,200);
        oval.setFilled(true);
        oval.setFillColor(Color.WHITE);
        add(oval,this.getX()/2,this.getY()/2);
        GRoundRect rr = new GRoundRect(40,500);
        rr.setFilled(true);
        rr.setFillColor(Color.yellow);
        add(rr,300,0);
        GRoundRect rr2 = new GRoundRect(500,40);
        rr2.setFilled(true);
        rr2.setFillColor(Color.yellow);
        add(rr2,0,300);
    }
    
    
    //public static void main(String[] args){
        //new Artistry().start(args);
    //}
}
