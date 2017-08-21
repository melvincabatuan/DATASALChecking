/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.kitane.stackcalculator;
import acm.program.*;
import acm.graphics.*;
import acm.gui.*;
import java.awt.Color;
import java.awt.event.MouseEvent;
/**
 *
 * @author Christoph Kitane
 */
public class StackCalculator extends GraphicsProgram{
    //Stack Arrays for Program
    private myStackNum in = new myStackNum(2);
    private myStackOp operator = new myStackOp(20);
    
    //Dimensions of Application
    public static final int APPLICATION_WIDTH = 307;
    public static final int APPLICATION_HEIGHT = 469;
    
    //Short version for coding
    private static final int WIDTH = APPLICATION_WIDTH;
    private static final int HEIGHT = APPLICATION_HEIGHT;
    
    private static final int BUTTON_HEIGHT = 70;
    private static final int BUTTON_WIDTH = 70;
    
    private static final int DISPLAY_WIDTH = WIDTH-10;
    private static final int DISPLAY_HEIGHT = BUTTON_WIDTH;
    private static final int DISPLAY_Y_OFFSET = 10;
    
    private static final int BROWS = 5;
    private static final int BCOLS = 3;
    
    
    
    private GRect button;
    private GRect buttonPlus;
    private GRect buttonMinus;
    private GRect buttonEquals;
    private GRect display;
    private GLabel label;
    private GLabel labelDisplay;
    
    private String def="";
    private String input;
    private int op1=0,op2=0,out=0;
    
    
    public void init(){
        addMouseListeners();
        displayNumbers();
        buttonNumbers();
        buttonPlus();
        buttonMinus();
        buttonEquals();
        setLabelsGrid();
    }
    private void buttonNumbers(){
        for (int r =0; r < BROWS; r++){
                for (int c=0; c < BCOLS;c++){
                    numberSetup(r,c);
                }
            }
    }
    
    private void displayNumbers(){
        display=new GRect(DISPLAY_WIDTH,DISPLAY_HEIGHT);
        display.setFilled(true);
        display.setFillColor(Color.LIGHT_GRAY);
        add(display,(WIDTH-DISPLAY_WIDTH)/2,DISPLAY_Y_OFFSET);
        
        labelDisplay = new GLabel("0");
        labelDisplay.setFont("Arial-38-*");
        add(labelDisplay,WIDTH-labelDisplay.getWidth()-display.getX(),DISPLAY_HEIGHT);
    }
    
    private void numberSetup(int row,int col){
        double x,y;
        button = new GRect(BUTTON_WIDTH,BUTTON_HEIGHT);
        x=col*(BUTTON_WIDTH+display.getX())+display.getX();
        y=row*(BUTTON_WIDTH+display.getX())+DISPLAY_HEIGHT+(2*DISPLAY_Y_OFFSET);
        button.setFilled(true);
        button.setFillColor(Color.LIGHT_GRAY);
        add(button,x,y);
        System.out.println("Button("+row+","+col+")= ("+x+","+y+")");
    }
    
    private void buttonPlus(){
        double x,y;
        label = new GLabel("+");
        label.setFont("Arial-30-*");
        buttonPlus = new GRect(BUTTON_WIDTH,BUTTON_HEIGHT*2+DISPLAY_Y_OFFSET/2);
        x=WIDTH-BUTTON_WIDTH-display.getX();
        y=DISPLAY_HEIGHT+(2*DISPLAY_Y_OFFSET);
        buttonPlus.setFilled(true);
        buttonPlus.setFillColor(Color.LIGHT_GRAY);
        add(buttonPlus,x,y);
        add(label,x+((BUTTON_WIDTH/2)-(label.getWidth()/2)),y+(buttonPlus.getHeight()/2));
        System.out.println(label.getLabel()+"= ("+x+","+y+")");
    }
    
    private void buttonMinus(){
        double x,y;
        buttonMinus = new GRect(BUTTON_WIDTH,BUTTON_HEIGHT);
        x=WIDTH-BUTTON_WIDTH-display.getX();
        y=buttonPlus.getY()+buttonPlus.getHeight()+display.getX();
        buttonMinus.setFilled(true);
        buttonMinus.setFillColor(Color.LIGHT_GRAY);
        add(buttonMinus,x,y);
        label = new GLabel("-");
        label.setFont("Arial-30-*");
        add(label,x+(BUTTON_WIDTH/2-label.getWidth()/2),y+(BUTTON_HEIGHT/2)+(display.getX()*2));
        System.out.println(label.getLabel()+"= ("+x+","+y+")");
    }
    
    private void buttonEquals(){
        double x,y;
        buttonEquals = new GRect(BUTTON_WIDTH,BUTTON_HEIGHT*2+DISPLAY_Y_OFFSET/2);
        x=WIDTH-BUTTON_WIDTH-display.getX();
        y=buttonMinus.getY()+buttonMinus.getHeight()+display.getX();
        buttonEquals.setFilled(true);
        buttonEquals.setFillColor(Color.LIGHT_GRAY);
        add(buttonEquals,x,y);
        label = new GLabel("=");
        label.setFont("Arial-30-*");
        add(label,x+((BUTTON_WIDTH/2)-(label.getWidth()/2)),y+(buttonEquals.getHeight()/2)+display.getX());
        System.out.println(label.getLabel()+"= ("+x+","+y+")");
    }
    
    private void setLabelsGrid(){
        for (int r =0; r < BROWS; r++){
                for (int c=0; c < BCOLS;c++){
                    setLabel(r,c);
                }
            }
    }
    
    private void setLabel(int row,int col){
        double x,y;
        label = new GLabel("C");
        label.setFont("Arial-30-*");
        x=col*(BUTTON_WIDTH+display.getX())+display.getX()+((BUTTON_WIDTH/2)-(label.getWidth()/2));
        y=row*(BUTTON_WIDTH+display.getX())+DISPLAY_HEIGHT*2;
        if(row==0&&col==0){
            label.setLabel("C");
            add(label,x,y);
        }
        if(row==0&&col==1){
            label.setLabel("/");
            add(label,x,y);
        }
        if(row==0&&col==2){
            label.setLabel("x");
            add(label,x,y);
        }
        if(row==1&&col==0){
            label.setLabel("7");
            add(label,x,y);
        }
        if(row==1&&col==1){
            label.setLabel("8");
            add(label,x,y);
        }
        if(row==1&&col==2){
            label.setLabel("9");
            add(label,x,y);
        }
        if(row==2&&col==0){
            label.setLabel("4");
            add(label,x,y);
        }
        if(row==2&&col==1){
            label.setLabel("5");
            add(label,x,y);
        }
        if(row==2&&col==2){
            label.setLabel("6");
            add(label,x,y);
        }
        if(row==3&&col==0){
            label.setLabel("1");
            add(label,x,y);
        }
        if(row==3&&col==1){
            label.setLabel("2");
            add(label,x,y);
        }
        if(row==3&&col==2){
            label.setLabel("3");
            add(label,x,y);
        }
        if(row==4&&col==0){
            label.setLabel("+/-");
            add(label,col*(BUTTON_WIDTH+display.getX())+display.getX()+((BUTTON_WIDTH/2)-(label.getWidth()/2)),y);
        }
        if(row==4&&col==1){
            label.setLabel("0");
            add(label,x,y);
        }
        if(row==4&&col==2){
            label.setLabel(".");
            add(label,x,y);
        }
        
    }
    
    private boolean isMouseOnC(double x,double y){
        return (x>=5.0&& x<=(5.0+button.getWidth()) && y>=(90.0) && y<=(90.0+button.getHeight()));

    }
    
    private boolean isMouseOnDivide(double x,double y){
        return (x>=80.0&& x<=(80.0+button.getWidth()) && y>=(90.0) && y<=(90.0+button.getHeight()));

    }
    
    private boolean isMouseOnMultiply(double x,double y){
        return (x>=155.0&& x<=(155.0+button.getWidth()) && y>=(90.0) && y<=(90.0+button.getHeight()));

    }
    
    private boolean isMouseOn7(double x,double y){
        return ((x>=5.0&& x<=(5.0+button.getWidth())) && (y>=(165.0) && y<=(165.0+button.getHeight())));

    }
    
    private boolean isMouseOn8(double x,double y){
        return ((x>=80.0&& x<=(80.0+button.getWidth())) && (y>=(165.0) && y<=(165.0+button.getHeight())));

    }
    
    private boolean isMouseOn9(double x,double y){
        return ((x>=155.0&& x<=(155.0+button.getWidth())) && (y>=(165.0) && y<=(165.0+button.getHeight())));

    }
    
    private boolean isMouseOn4(double x,double y){
        return ((x>=5.0&& x<=(5.0+button.getWidth())) && (y>=(240.0) && y<=(240.0+button.getHeight())));

    }
    
    private boolean isMouseOn5(double x,double y){
        return ((x>=80.0&& x<=(80.0+button.getWidth())) && (y>=(240.0) && y<=(240.0+button.getHeight())));

    }
    
    private boolean isMouseOn6(double x,double y){
        return ((x>=155.0&& x<=(155.0+button.getWidth())) && (y>=(240.0) && y<=(240.0+button.getHeight())));

    }
    
    private boolean isMouseOn1(double x,double y){
        return ((x>=5.0&& x<=(5.0+button.getWidth())) && (y>=(315.0) && y<=(315.0+button.getHeight())));

    }
    
    private boolean isMouseOn2(double x,double y){
        return ((x>=80.0&& x<=(80.0+button.getWidth())) && (y>=(315.0) && y<=(315.0+button.getHeight())));

    }
    
    private boolean isMouseOn3(double x,double y){
        return ((x>=155.0&& x<=(155.0+button.getWidth())) && (y>=(315.0) && y<=(315.0+button.getHeight())));

    }
    
    private boolean isMouseOnSignChange(double x,double y){
        return ((x>=5.0&& x<=(5.0+button.getWidth())) && (y>=(390.0) && y<=(390.0+button.getHeight())));
    }
    
    private boolean isMouseOn0(double x,double y){
        return ((x>=80.0&& x<=(80.0+button.getWidth())) && (y>=(390.0) && y<=(390.0+button.getHeight())));

    }
    
    private boolean isMouseOnDPoint(double x,double y){
        return ((x>=155.0&& x<=(155.0+button.getWidth())) && (y>=(390.0) && y<=(390.0+button.getHeight())));

    }
    
    private boolean isMouseOnPlus(double x,double y){
        return (x>=232.0&& x<=(232.0+button.getWidth()) && y>=(90.0) && y<=(90.0+button.getHeight()));

    }
    
    private boolean isMouseOnMinus(double x,double y){
        return (x>=232.0&& x<=(232.0+button.getWidth()) && y>=(240.0) && y<=(240.0+button.getHeight()));

    }
    
    private boolean isMouseOnEquals(double x,double y){
        return (x>=232.0&& x<=(232.0+button.getWidth()) && y>=(315.0) && y<=(315.0+button.getHeight()));

    }
    
    public void mouseClicked(MouseEvent me){
        if(isMouseOnC(me.getX(),me.getY())){
            def="";
            labelDisplay.setLabel("0");
            labelDisplay.setLocation(WIDTH-labelDisplay.getWidth()-display.getX(),DISPLAY_HEIGHT);
            while(!in.isEmpty()){
                in.pop();
            }
            while(!operator.isEmpty()){
                operator.pop();
            }
        }
        else if(isMouseOn7(me.getX(),me.getY())){
            labelDisplay.setVisible(false);
            labelDisplay.setVisible(true);
            input="7";
            def=def.concat(input);
            labelDisplay.setLabel(def);
            labelDisplay.setLocation(WIDTH-labelDisplay.getWidth()-display.getX(),DISPLAY_HEIGHT);
        }
        else if(isMouseOn8(me.getX(),me.getY())){
            input="8";
            def=def.concat(input);
            labelDisplay.setLabel(def);
            labelDisplay.setLocation(WIDTH-labelDisplay.getWidth()-display.getX(),DISPLAY_HEIGHT);
        }
        else if(isMouseOn9(me.getX(),me.getY())){
            input="9";
            def=def.concat(input);
            labelDisplay.setLabel(def);
            labelDisplay.setLocation(WIDTH-labelDisplay.getWidth()-display.getX(),DISPLAY_HEIGHT);
        }
        else if(isMouseOn4(me.getX(),me.getY())){
            input="4";
            def=def.concat(input);
            labelDisplay.setLabel(def);
            labelDisplay.setLocation(WIDTH-labelDisplay.getWidth()-display.getX(),DISPLAY_HEIGHT);
        }
        else if(isMouseOn5(me.getX(),me.getY())){
            input="5";
            def=def.concat(input);
            labelDisplay.setLabel(def);
            labelDisplay.setLocation(WIDTH-labelDisplay.getWidth()-display.getX(),DISPLAY_HEIGHT);
        }
        else if(isMouseOn6(me.getX(),me.getY())){
            input="6";
            def=def.concat(input);
            labelDisplay.setLabel(def);
            labelDisplay.setLocation(WIDTH-labelDisplay.getWidth()-display.getX(),DISPLAY_HEIGHT);
        }
        else if(isMouseOn1(me.getX(),me.getY())){
            input="1";
            def=def.concat(input);
            labelDisplay.setLabel(def);
            labelDisplay.setLocation(WIDTH-labelDisplay.getWidth()-display.getX(),DISPLAY_HEIGHT);
        }
        else if(isMouseOn2(me.getX(),me.getY())){
            input="2";
            def=def.concat(input);
            labelDisplay.setLabel(def);
            labelDisplay.setLocation(WIDTH-labelDisplay.getWidth()-display.getX(),DISPLAY_HEIGHT);
        }
        else if(isMouseOn3(me.getX(),me.getY())){
            input="3";
            def=def.concat(input);
            labelDisplay.setLabel(def);
            labelDisplay.setLocation(WIDTH-labelDisplay.getWidth()-display.getX(),DISPLAY_HEIGHT);
        }
        else if(isMouseOn0(me.getX(),me.getY())){
            input="0";
            def=def.concat(input);
            labelDisplay.setLabel(def);
            labelDisplay.setLocation(WIDTH-labelDisplay.getWidth()-display.getX(),DISPLAY_HEIGHT);
        }
        else if(isMouseOnDPoint(me.getX(),me.getY())){
            input=".";
            def=def.concat(input);
            labelDisplay.setLabel(def);
            labelDisplay.setLocation(WIDTH-labelDisplay.getWidth()-display.getX(),DISPLAY_HEIGHT);
        }
        else if(isMouseOnDivide(me.getX(),me.getY())){
            in.push(Integer.parseInt(def));
            if(!in.isFull()){
                labelDisplay.setLabel(def);
                def="";
                labelDisplay.setLocation(WIDTH-labelDisplay.getWidth()-display.getX(),DISPLAY_HEIGHT);
            }
            else if(in.isFull()){
                operate();
                in.push(Integer.parseInt(def));
                def="";
            }
            System.out.println("divide is clicked");
            operator.push('/');
        }
        else if(isMouseOnMultiply(me.getX(),me.getY())){
            in.push(Integer.parseInt(def));
            if(!in.isFull()){
                labelDisplay.setLabel(def);
                def="";
                labelDisplay.setLocation(WIDTH-labelDisplay.getWidth()-display.getX(),DISPLAY_HEIGHT);
            }
            else if(in.isFull()){
                operate();
                in.push(Integer.parseInt(def));
                def="";
            }
            System.out.println("multiply is clicked");
            operator.push('*');
        }
        
        else if(isMouseOnPlus(me.getX(),me.getY())){
            in.push(Integer.parseInt(def));
            if(!in.isFull()){
                labelDisplay.setLabel(def);
                def="";
                labelDisplay.setLocation(WIDTH-labelDisplay.getWidth()-display.getX(),DISPLAY_HEIGHT);
            }
            else if(in.isFull()){
                operate();
                in.push(Integer.parseInt(def));
                def="";
            }
            System.out.println("plus is clicked");
            operator.push('+');
        }
        else if(isMouseOnMinus(me.getX(),me.getY())){
            in.push(Integer.parseInt(def));
            if(!in.isFull()){
                labelDisplay.setLabel(def);
                def="";
                labelDisplay.setLocation(WIDTH-labelDisplay.getWidth()-display.getX(),DISPLAY_HEIGHT);
            }
            else if(in.isFull()){
                operate();
                in.push(Integer.parseInt(def));
                def="";
            }
            System.out.println("minus is clicked");
            operator.push('-');
        }
        else if(isMouseOnEquals(me.getX(),me.getY())){
            in.push(Integer.parseInt(def));
            operate();
            System.out.println("equals is clicked");
        }
    }
    
    
    
    public void mousePressed(MouseEvent me){
            button=(GRect) getElementAt(me.getX(),me.getY());
            button.setFillColor(Color.yellow);
    }
    
    public void mouseReleased(MouseEvent me){
            button=(GRect) getElementAt(me.getX(),me.getY());
            button.setFillColor(Color.LIGHT_GRAY);
    }
    
    private void operate(){
        switch(operator.top()){
            case '+':
                operator.pop();
                add();
                break;
            case '-':
                operator.pop();
                subtract();
                break;
            case '*':
                operator.pop();
                multiply();
                break;
            case '/':
                operator.pop();
                divide();
                break;
        }
    }
    
    private void add(){
        op2=in.pop();
        op1=in.pop();
        out=op1+op2;
        println(op1+" + "+op2+" = "+out);
        def=Integer.toString(out);
        labelDisplay.setLabel(def);
        labelDisplay.setLocation(WIDTH-labelDisplay.getWidth()-display.getX(),DISPLAY_HEIGHT);
    }
    
    private void subtract(){
        op2=in.pop();
        op1=in.pop();
        out=op1-op2;
        println(op1+" - "+op2+" = "+out);
        def=Integer.toString(out);
        labelDisplay.setLabel(def);
        labelDisplay.setLocation(WIDTH-labelDisplay.getWidth()-display.getX(),DISPLAY_HEIGHT);
    }
    
    private void multiply(){
        op2=in.pop();
        op1=in.pop();
        out=op1*op2;
        println(op1+" * "+op2+" = "+out);
        def=Integer.toString(out);
        labelDisplay.setLabel(def);
        labelDisplay.setLocation(WIDTH-labelDisplay.getWidth()-display.getX(),DISPLAY_HEIGHT);
    }
    
    private void divide(){
        op2=in.pop();
        op1=in.pop();
        out=op1/op2;
        println(op1+" / "+op2+" = "+out);
        def=Integer.toString(out);
        labelDisplay.setLabel(def);
        labelDisplay.setLocation(WIDTH-labelDisplay.getWidth()-display.getX(),DISPLAY_HEIGHT);
    }
    
    /**
     * @param args the command line arguments
     */
    /*public static void main(String[] args) {
        new StackCalculator().start(args);
    }*/
    
}
