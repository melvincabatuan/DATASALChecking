/*
 * File: StackADT.java
 * Name: Maverick C. Rivera
 * Section Leader:
 * ==========================================================
 *This represents my emotion most of the time. hehe. 
 */

package ph.edu.dlsu.rivera.calculator;

import acm.graphics.GCanvas;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.io.IODialog;
import acm.program.GraphicsProgram;
import java.awt.Color;
import java.awt.event.MouseEvent;

/**
 *
 * @author student
 */
public class StackADT extends GraphicsProgram{
    boolean withExponent=false;
    String saved;
    String[] infix,postfix;
    private String operator;
    Stack<String> current = new Stack<>();
    Stack<Integer> current_int = new Stack<>();
    String current_label="0";
    GLabel label = new GLabel("0", 250, 100);
    GLabel brand = new GLabel("Cashew", 120, 28);
    IODialog dialog = getDialog();
    GRect screen = new GRect(20, 30, 250, 90);
    GRect border = new GRect(0, 10, 290, 470);
    
    
    
    public void init(){
        setSize((int)border.getWidth()+20, (int)border.getHeight()+60);
        addMouseListeners();
        displayScreen();
        setBackground(Color.WHITE);
        CalcuGUI();
        
    }
    public void run(){
        
    }
    public void mouseClicked(MouseEvent me){
        try{
        int x,y;
        x=me.getX();
        y=me.getY();
       GImage temp; 
       GObject temp1;
        temp1 = getElementAt(x, y);
        if((GImage)temp1!=null){
        for(int i=0;i<25;i++){
            temp = (GImage)temp1;
            if(temp==buttons[i]){
                if(current_label.equalsIgnoreCase("0")&&i<9){
                    current_label=(i+1)+"";
                }else if(i==9){
                    
                    current_label=current_label.concat("0");
                }else if(i==10){
                    current_label=current_label.concat(".");
                    
                }
                else if(i==11){
                    saved = current_label;
                    
                }else if(i==12){
                     current_label = current_label.substring(0, current_label.length()-1);
                    if(current_label.length()==0){
                        current_label="0";
                    }
                    
                }else if(i==13){
                    current_label="0";
                    
                }else if(i==14){
                    //mult
                    current_label=current_label.concat("x");
                    
                }else if(i==15){
                    //div
//                    current_int.push(Integer.parseInt(current_label));
//                    current_label="0";
//                    operator="/";
                    current_label=current_label.concat("/");
                }else if(i==16){
                    //addition
//                    current_int.push(Integer.parseInt(current_label));
//                    current.push(current_label);
//                    System.out.println(current_int.top());
//                    current_label="0";
//                    operator="+";
                        current_label=current_label.concat("+");
                }else if(i==17){
                    //subraction 
//                    current_int.push(Integer.parseInt(current_label));
//                    current.push(current_label);
//                    System.out.println(current_int.top());
//                    current_label="0";
//                    operator="+";
                        current_label=current_label.concat("-");
                }else if(i==18){
                    current_label=current_label.concat(saved);
                }
                else if(i==19){
                    //equals
//                    current_int.push(Integer.parseInt(current_label));
                    calculate();
                }else if(i==20){
                    if(current_label.equalsIgnoreCase("0")){
                        current_label="s";
                    }else{
                    current_label=current_label.concat("s");}
                }else if(i==21){
                    if(current_label.equalsIgnoreCase("0")){
                        current_label="c";
                    }
                    else{
                    current_label=current_label.concat("c");}
                }else if(i==22){
                    if(current_label.equalsIgnoreCase("0")){
                        current_label="t";
                    }else{
                    current_label=current_label.concat("t");}
                }else if(i==23){
                    if(current_label.equalsIgnoreCase("0")){
                        current_label="^";
                        withExponent = true;
                    }else{
                    current_label=current_label.concat("^");}
                }else if(i==24){
                    if(current_label.equalsIgnoreCase("0")){
                        current_label="~";
                    }else{
                    current_label=current_label.concat("~");}
                }
                    else{
                    current_label=current_label.concat((i+1)+"");
                }
            }
            
        }
        label.setLabel(current_label);
        label.setLocation(screen.getX()+screen.getWidth()-20-label.getWidth(), label.getY());
        
    } }catch (java.lang.ClassCastException e){
        
    }}
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        new StackADT().start();
    }
    GImage[] buttons = new GImage[25];
    public void CalcuGUI(){
        
        double xoffset = 30,yoffset=240,width,space=5 ;
        
        for( int i = 0 ; i<25;i++ ){
            GImage temp = new GImage("c"+(i+1)+".jpg");
            buttons[i]=  temp;
            buttons[i].setSize(50, 50);
        }
        width = buttons[0].getWidth();
        //digits, decimal, equal
        for( int i = 0 ; i<25;i++ ){
            
            
            if(i<12){
            buttons[i].setLocation( 2*xoffset + width*(i%3-1)+space*((i)%3), yoffset+width*((i)/3)+space*(i/3));
            
           
            }
            if(i>=12 && i < 20){
                buttons[i].setLocation(2*xoffset + 2*width+3*space+(i-12)%2*(width+space) ,yoffset+(space+width)*((i-12)/2));
            }
            if(i>=20){
                buttons[i].setLocation(2*xoffset + width*(i-21)+space*((i-20)),yoffset-width-20);
            }
             add(buttons[i]);
        }
}

    private void displayScreen() {
        border.setFilled(true);
        border.setFillColor(Color.BLACK);
        add(label);
        add(border);
         screen.setFilled(true);
        screen.setFillColor(Color.GRAY);
        screen.sendToFront();
        add(screen);
        label.setFont("Digital 7-bold-25");
         label.setColor(Color.WHITE);
         label.sendToFront();
         brand.setFont("Serif-bold-20");
         brand.setColor(Color.LIGHT_GRAY);
         brand.sendToFront();
         add(brand);
    }

    private void calculate() {
        try{
        Stack<String> operators = new Stack<>();
        int length =determineLength();
        System.out.println("length = "+ length);
        infix = new String[length];
        postfix = new String[length];
        String temp="";
        char[] converted = current_label.toCharArray();
        int i=0;
        
            for(int j=0;j<converted.length;j++){
                if(!isOperator(converted[j])){
                    temp=temp.concat(converted[j]+"");
                }
                if(isOperator(converted[j])){
                    infix[i]=temp;
                    temp="";
                    infix[i+1]=converted[j]+"";
                    i+=2;
                }
            }
            infix[i]=temp;
            for(int j=0;j<infix.length;j++){
                System.out.println(infix[j]);
            }
            
            //infix to postfix
            int p=0;
           for(int j=0;j<infix.length;j++){
               if(isOperator(infix[j].charAt(0)) && operators.isEmpty()){
                   
                   operators.push(infix[j]);
               }else if(isOperator(infix[j].charAt(0)) && !operators.isEmpty()){
                   
                   if(("+".equals(infix[j])||"-".equals(infix[j])) && ("x".equals(operators.top())||"/".equals(operators.top()))){
                       
                       postfix[p]=operators.pop();
                       p++;
                       if(operators.isEmpty()){
                           operators.push(infix[j]);
                       }else{
                        postfix[p]=operators.pop();
                       p++;}
                   }else if(("/".equals(infix[j])||"x".equals(infix[j])) && ("-".equals(operators.top())||"+".equals(operators.top()))){
                       operators.push(infix[j]);
                   }
                   else if(("+".equals(infix[j])||"-".equals(infix[j]))&& ("-".equals(operators.top())||"+".equals(operators.top()))){
                       postfix[p]=operators.pop();
                       p++;
                       operators.push(infix[j]);
                   }else if(("/".equals(infix[j])||"x".equals(infix[j])) &&("x".equals(operators.top())||"/".equals(operators.top()))){
                       postfix[p]=operators.pop();
                       p++;
                       operators.push(infix[j]);
                   }
                   else{
                           
                       postfix[p]=operators.pop();
                       operators.push(infix[j]);
                       p++;
                   }
               }
               else{
                   postfix[p]=infix[j];
                   p++;
               }
           }
           while(!operators.isEmpty()){
               System.out.println("while loop");
           postfix[p]=operators.pop();
           p++;
           }
        System.out.println("Start of postfix");
        for(int j=0;j<postfix.length;j++){
            System.out.println(postfix[j]);
        }
         System.out.println("End  of postfix");
        //post fix to answer
        Stack<Double> calculation = new Stack<>();
        for(int j=0;j<postfix.length;j++){
            
            //System.out.println(postfix[j]+"mark");
            
            if(checkExponent(postfix[j]) ){
                String[] base = postfix[j].split("\\^");
                postfix[j]=Math.pow(Double.parseDouble(base[0]), Double.parseDouble(base[1]))+"";
                
               
            
            }
            if(postfix[j].charAt(0)=='s'||postfix[j].charAt(0)=='c'||postfix[j].charAt(0)=='t'||postfix[j].charAt(0)=='~'){
               
                
                if(postfix[j].charAt(0)=='s'){
                    String sine = String.valueOf(postfix[j]);
                    sine = sine.substring(1);
                    postfix[j]=Math.sin(Math.toRadians(Double.parseDouble(sine)))+"";
                    postfix[j] = Math.floor(Double.parseDouble(postfix[j])* 1000000) / 1000000+"";
                            
                                    
                    
                }
                if(postfix[j].charAt(0)=='c'){
                    String cosine = String.valueOf(postfix[j]);
                    cosine = cosine.substring(1);
                    postfix[j]=Math.cos(Math.toRadians(Double.parseDouble(cosine)))+"";
                    postfix[j] = Math.floor(Double.parseDouble(postfix[j])* 1000000) / 1000000+"";
                    
                }
                if(postfix[j].charAt(0)=='t'){
                    String tan = String.valueOf(postfix[j]);
                    tan = tan.substring(1);
                    
                    postfix[j]=Math.tan(Math.toRadians(Double.parseDouble(tan)))+"";
                    postfix[j] = Math.floor(Double.parseDouble(postfix[j])* 1000000) / 1000000+"";
                    
                }
                
            }
            if(isOperator(postfix[j].charAt(0))){
                
                 
                Double temp1 = calculation.pop();
                Double temp2 = calculation.pop();
                
                switch (postfix[j].charAt(0)) {
                    case '+':
                        calculation.push(temp1+temp2);
                        break;
                    case '-':
                        calculation.push(temp2-temp1);
                        break;
                    case 'x':
                        calculation.push(temp2*temp1);
                        break;
                    case '/':
                        calculation.push(temp2/temp1);
                        break;
                    default:
                        break;
                }
            }else{
                Double calctemp;
                
                if(postfix[j].charAt(0)=='~'){
                    System.out.println(postfix[j]+"marker");
                    String tempvar;
//                    tempvar = postfix[j].substring(1);
                    String negate = postfix[j].substring(1);
                    
                    calctemp=(Double.parseDouble(negate)*(-1));
                    System.out.println(calctemp+"   marker2");
                    
                }else{
                    calctemp = Double.parseDouble(postfix[j]);
                }
                 
                calculation.push(calctemp);
                System.out.println(calctemp);
            }
        }
        
        //setting the label
        current_label=calculation.pop()+"";
        label.setLabel(current_label);
        }
        catch(NumberFormatException e){
            current_label="Syntax Error";
            label.setLabel(current_label);
        }
        catch(java.lang.StringIndexOutOfBoundsException e){
            current_label="Syntax Error";
            label.setLabel(current_label);
        }
}   
    
    
    
    
    
    
    
    private boolean isOperator(char c){
        if(c=='+'||c=='-'||c=='/'||c=='x'){
            return true;
        }
        else{
            return false;
        }
    }

    private int determineLength() {
    char [] temp = current_label.toCharArray();
    int length=0;
    for(int i=0;i<temp.length;i++ ){
        if(isOperator(temp[i])){
            length ++;
        }
    }
    return 2*length+1 ;
    }

    private boolean checkExponent(String string) {
        
        for(int i=0;i<string.length();i++){
            if(string.charAt(i)=='^'){
                return true;
            }
        }
        
           return false;
    }
   
}
