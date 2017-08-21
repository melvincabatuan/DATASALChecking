package ph.edu.dlsu.velasco.calculator;

/**
 * @author NeilOliver
 */
import acm.program.*;
import acm.graphics.*;
import java.awt.Color;
import java.awt.event.MouseEvent;
import ph.edu.dlsu.velasco.mystack.*;

public class CalculatorStack extends GraphicsProgram{

    String concat = "";
    String input = "";
    String post = "";
    String number = "";
    String postnum = "";
    
    myStackOpe ope = new myStackOpe(2);
    MyStack num = new MyStack(50);
    
    char[] InputArray = new char[50];
    char[] PostArray = new char[50];
    GLabel displayAns = new GLabel("0");
    
    private static final int APPLICATION_WIDTH = 380;
    private static final int APPLICATION_HEIGHT = 650;
    
    public static final int WIDTH = APPLICATION_WIDTH;
    public static final int HEIGHT = APPLICATION_HEIGHT;
    
    private static final int PAUSE = 400;
    
    private final int BOX_LENGTH = 75;
    private final int DISPLAY_LENGTH = 340;
    
    private final int X_LOCATION = 10;
    private final int Y_LOCATION = 10;
    private final int XY_OFFSET = 10;
    private final int EQUALS_LENGTH = 245;
    public final int X_LABEL_OFFSET = 30;
    public final int Y_LABEL_OFFSET = 50;
    
    GRect background = new GRect(WIDTH,HEIGHT);
    GRoundRect button1 = new GRoundRect(BOX_LENGTH, BOX_LENGTH);
    GRoundRect button2 = new GRoundRect(BOX_LENGTH, BOX_LENGTH);
    GRoundRect button3 = new GRoundRect(BOX_LENGTH, BOX_LENGTH);
    GRoundRect button4 = new GRoundRect(BOX_LENGTH, BOX_LENGTH);
    GRoundRect button5 = new GRoundRect(BOX_LENGTH, BOX_LENGTH);
    GRoundRect button6 = new GRoundRect(BOX_LENGTH, BOX_LENGTH);
    GRoundRect button7 = new GRoundRect(BOX_LENGTH, BOX_LENGTH);
    GRoundRect button8 = new GRoundRect(BOX_LENGTH, BOX_LENGTH);
    GRoundRect button9 = new GRoundRect(BOX_LENGTH, BOX_LENGTH);
    GRoundRect button0 = new GRoundRect(BOX_LENGTH, BOX_LENGTH);
    GRoundRect sqroot = new GRoundRect(BOX_LENGTH, BOX_LENGTH/2);
    GRoundRect sin = new GRoundRect(BOX_LENGTH, BOX_LENGTH/2);
    GRoundRect cos = new GRoundRect(BOX_LENGTH, BOX_LENGTH/2);
    GRoundRect ln = new GRoundRect(BOX_LENGTH, BOX_LENGTH/2); 
    GRoundRect clear = new GRoundRect(BOX_LENGTH, BOX_LENGTH);
    GRoundRect deci = new GRoundRect(BOX_LENGTH, BOX_LENGTH);
    GRoundRect zero = new GRoundRect(BOX_LENGTH, BOX_LENGTH);
    GRoundRect add = new GRoundRect(BOX_LENGTH, BOX_LENGTH);
    GRoundRect subs = new GRoundRect(BOX_LENGTH, BOX_LENGTH);
    GRoundRect mult = new GRoundRect(BOX_LENGTH, BOX_LENGTH);
    GRoundRect div = new GRoundRect(BOX_LENGTH, BOX_LENGTH);
    GRoundRect equals = new GRoundRect(EQUALS_LENGTH, BOX_LENGTH);
    GRoundRect posneg = new GRoundRect(BOX_LENGTH, BOX_LENGTH);
    GRoundRect display = new GRoundRect(DISPLAY_LENGTH, BOX_LENGTH);
    GLabel one = new GLabel("1");
    GLabel two = new GLabel("2");
    GLabel three = new GLabel("3");
    GLabel four = new GLabel("4");
    GLabel five = new GLabel("5");
    GLabel six = new GLabel("6");
    GLabel seven = new GLabel("7");
    GLabel eight = new GLabel("8");
    GLabel nine = new GLabel("9");
    GLabel zeronum = new GLabel("0");
    GLabel plus = new GLabel("+");
    GLabel minus = new GLabel("-");
    GLabel times = new GLabel("x");
    GLabel divide = new GLabel("/");
    GLabel equalsign = new GLabel("=");
    GLabel changeSign = new GLabel("+-");
    GLabel sqrt = new GLabel("sqrt(x)");
    GLabel sinx = new GLabel("sin(x)");
    GLabel cosx = new GLabel ("cos(x)");
    GLabel lnx = new GLabel ("ln(x)");
    GLabel c = new GLabel("CA");
    GLabel point = new GLabel(".");
    GLabel displayAnswer = new GLabel("0");
    
    public void displayCalculator(){
        background.setFilled(true);
        background.setFillColor(Color.DARK_GRAY);
        add(background);
        
        display.setFilled(true);
        display.setFillColor(Color.WHITE);
        add(display,X_LOCATION,Y_LOCATION);
        
        sqroot.setFilled(true);
        sqroot.setFillColor(Color.WHITE);
        add(sqroot,display.getX(),display.getY()+XY_OFFSET+BOX_LENGTH);
        sqrt.setFont("MONTSERRAT-15");
        add(sqrt,sqroot.getX()+X_LABEL_OFFSET/2,sqroot.getY()+Y_LABEL_OFFSET/2);
        
        sin.setFilled(true);
        sin.setFillColor(Color.WHITE);
        add(sin,sqroot.getX()+(2*XY_OFFSET)+BOX_LENGTH,sqroot.getY());
        sinx.setFont("MONTSERRAT-18");
        add(sinx,sin.getX()+X_LABEL_OFFSET/2,sin.getY()+Y_LABEL_OFFSET/2);
        
        cos.setFilled(true);
        cos.setFillColor(Color.WHITE);
        add(cos,sin.getX()+XY_OFFSET+BOX_LENGTH,sin.getY());
        cosx.setFont("MONTSERRAT-18");
        add(cosx,cos.getX()+X_LABEL_OFFSET/2-5,cos.getY()+Y_LABEL_OFFSET/2);
        
        ln.setFilled(true);
        ln.setFillColor(Color.WHITE);
        add(ln,cos.getX()+XY_OFFSET+BOX_LENGTH,cos.getY());
        lnx.setFont("MONTSERRAT-18");
        add(lnx,ln.getX()+X_LABEL_OFFSET/2,ln.getY()+Y_LABEL_OFFSET/2);
        
        add.setFilled(true);
        add.setFillColor(Color.WHITE);
        add(add,X_LOCATION,sqroot.getY()+sqroot.getHeight()+XY_OFFSET);
        plus.setFont("MONTSERRAT-30");
        add(plus,add.getX()+X_LABEL_OFFSET,add.getY()+Y_LABEL_OFFSET);
        
        subs.setFilled(true);
        subs.setFillColor(Color.WHITE);
        add(subs,X_LOCATION,add.getY()+BOX_LENGTH+XY_OFFSET);
        minus.setFont("MONTSERRAT-30");
        add(minus,subs.getX()+X_LABEL_OFFSET,subs.getY()+Y_LABEL_OFFSET);
        
        mult.setFilled(true);
        mult.setFillColor(Color.WHITE);
        add(mult,X_LOCATION,subs.getY()+BOX_LENGTH+XY_OFFSET);
        times.setFont("MONTSERRAT-30");
        add(times,mult.getX()+X_LABEL_OFFSET,mult.getY()+Y_LABEL_OFFSET);
        
        div.setFilled(true);
        div.setFillColor(Color.WHITE);
        add(div,X_LOCATION,mult.getY()+BOX_LENGTH+XY_OFFSET);
        divide.setFont("MONTSERRAT-30");
        add(divide,div.getX()+X_LABEL_OFFSET,div.getY()+Y_LABEL_OFFSET);
        
        posneg.setFilled(true);
        posneg.setFillColor(Color.WHITE);
        add(posneg,X_LOCATION,div.getY()+BOX_LENGTH+XY_OFFSET);
        changeSign.setFont("MONTSERRAT-30");
        add(changeSign,posneg.getX()+X_LABEL_OFFSET-8,posneg.getY()+Y_LABEL_OFFSET);
        
        button1.setFilled(true);
        button1.setFillColor(Color.WHITE);
        add(button1,add.getX()+(2*XY_OFFSET)+BOX_LENGTH,add.getY());
        one.setFont("MONTSERRAT-30");
        add(one,button1.getX()+X_LABEL_OFFSET,add.getY()+Y_LABEL_OFFSET);
        
        button2.setFilled(true);
        button2.setFillColor(Color.WHITE);
        add(button2,button1.getX()+BOX_LENGTH+XY_OFFSET,button1.getY());
        two.setFont("MONTSERRAT-30");
        add(two,button2.getX()+X_LABEL_OFFSET,button2.getY()+Y_LABEL_OFFSET);
        
        button3.setFilled(true);
        button3.setFillColor(Color.WHITE);
        add(button3,button2.getX()+BOX_LENGTH+XY_OFFSET,button2.getY());
        three.setFont("MONTSERRAT-30");
        add(three,button3.getX()+X_LABEL_OFFSET,button3.getY()+Y_LABEL_OFFSET);
        
        button4.setFilled(true);
        button4.setFillColor(Color.WHITE);
        add(button4,button1.getX(),button1.getY()+BOX_LENGTH+XY_OFFSET);
        four.setFont("MONTSERRAT-30");
        add(four,button4.getX()+X_LABEL_OFFSET,button4.getY()+Y_LABEL_OFFSET);
        
        button5.setFilled(true);
        button5.setFillColor(Color.WHITE);
        add(button5,button2.getX(),button2.getY()+BOX_LENGTH+XY_OFFSET);
        five.setFont("MONTSERRAT-30");
        add(five,button5.getX()+X_LABEL_OFFSET,button5.getY()+Y_LABEL_OFFSET);
        
        button6.setFilled(true);
        button6.setFillColor(Color.WHITE);
        add(button6,button3.getX(),button3.getY()+BOX_LENGTH+XY_OFFSET);
        six.setFont("MONTSERRAT-30");
        add(six,button6.getX()+X_LABEL_OFFSET,button6.getY()+Y_LABEL_OFFSET);
        
        button7.setFilled(true);
        button7.setFillColor(Color.WHITE);
        add(button7,button4.getX(),button4.getY()+BOX_LENGTH+XY_OFFSET);
        seven.setFont("MONTSERRAT-30");
        add(seven,button7.getX()+X_LABEL_OFFSET,button7.getY()+Y_LABEL_OFFSET);
        
        button8.setFilled(true);
        button8.setFillColor(Color.WHITE);
        add(button8,button5.getX(),button5.getY()+BOX_LENGTH+XY_OFFSET);
        eight.setFont("MONTSERRAT-30");
        add(eight,button8.getX()+X_LABEL_OFFSET,button8.getY()+Y_LABEL_OFFSET);
        
        button9.setFilled(true);
        button9.setFillColor(Color.WHITE);
        add(button9,button6.getX(),button6.getY()+BOX_LENGTH+XY_OFFSET);
        nine.setFont("MONTSERRAT-30");
        add(nine,button9.getX()+X_LABEL_OFFSET,button9.getY()+Y_LABEL_OFFSET);
        
        zero.setFilled(true);
        zero.setFillColor(Color.WHITE);
        add(zero,button7.getX(),button7.getY()+BOX_LENGTH+XY_OFFSET);
        zeronum.setFont("MONTSERRAT-30");
        add(zeronum,zero.getX()+X_LABEL_OFFSET,zero.getY()+Y_LABEL_OFFSET);
        
        deci.setFilled(true);
        deci.setFillColor(Color.WHITE);
        add(deci,button8.getX(),zero.getY());
        point.setFont("MONTSERRAT-30");
        add(point,deci.getX()+X_LABEL_OFFSET,deci.getY()+Y_LABEL_OFFSET);
        
        clear.setFilled(true);
        clear.setFillColor(Color.WHITE);
        add(clear,button9.getX(),zero.getY());
        c.setFont("MONTSERRAT-30");
        add(c,clear.getX()+X_LABEL_OFFSET/2,clear.getY()+Y_LABEL_OFFSET);
        
        equals.setFilled(true);
        equals.setFillColor(Color.WHITE);
        add(equals,zero.getX(),posneg.getY());
        equalsign.setFont("MONTSERRAT-30");
        add(equalsign,equals.getX()+EQUALS_LENGTH/2-5,equals.getY()+Y_LABEL_OFFSET);
    }
    
    public boolean isClickedOn1(double x, double y){
        return (x >= button1.getX() && x<= button1.getX()+BOX_LENGTH && y >= button1.getY() && y <= button1.getY()+BOX_LENGTH);
    }
    
    public boolean isClickedOn2(double x, double y){
        return (x >= button2.getX() && x<= button2.getX()+BOX_LENGTH && y >= button2.getY() && y <= button2.getY()+BOX_LENGTH);
    }
    
    public boolean isClickedOn3(double x, double y){
        return (x >= button3.getX() && x<= button3.getX()+BOX_LENGTH && y >= button3.getY() && y <= button3.getY()+BOX_LENGTH);
    }
    
    public boolean isClickedOn4(double x, double y){
        return (x >= button4.getX() && x<= button4.getX()+BOX_LENGTH && y >= button4.getY() && y <= button4.getY()+BOX_LENGTH);
    }
    
    public boolean isClickedOn5(double x, double y){
        return (x >= button5.getX() && x<= button5.getX()+BOX_LENGTH && y >= button5.getY() && y <= button5.getY()+BOX_LENGTH);
    }
    
    public boolean isClickedOn6(double x, double y){
        return (x >= button6.getX() && x<= button6.getX()+BOX_LENGTH && y >= button6.getY() && y <= button6.getY()+BOX_LENGTH);
    }
    
    public boolean isClickedOn7(double x, double y){
        return (x >= button7.getX() && x<= button7.getX()+BOX_LENGTH && y >= button7.getY() && y <= button7.getY()+BOX_LENGTH);
    }
    
    public boolean isClickedOn8(double x, double y){
        return (x >= button8.getX() && x<= button8.getX()+BOX_LENGTH && y >= button8.getY() && y <= button8.getY()+BOX_LENGTH);
    }
    
    public boolean isClickedOn9(double x, double y){
        return (x >= button9.getX() && x<= button9.getX()+BOX_LENGTH && y >= button9.getY() && y <= button9.getY()+BOX_LENGTH);
    }
    
    public boolean isClickedOn0(double x, double y){
        return (x >= zero.getX() && x<= zero.getX()+BOX_LENGTH && y >= zero.getY() && y <= zero.getY()+BOX_LENGTH);
    }
    
    public boolean isClickedOnAdd(double x, double y){
        return (x >= add.getX() && x<= add.getX()+BOX_LENGTH && y >= add.getY() && y <= add.getY()+BOX_LENGTH);
    }
    
    public boolean isClickedOnSubtract(double x, double y){
        return (x >= subs.getX() && x<= subs.getX()+BOX_LENGTH && y >= subs.getY() && y <= subs.getY()+BOX_LENGTH);
    }
    
    public boolean isClickedOnMultiply(double x, double y){
        return (x >= mult.getX() && x<= mult.getX()+BOX_LENGTH && y >= mult.getY() && y <= mult.getY()+BOX_LENGTH);
    }
    
    public boolean isClickedOnDivide(double x, double y){
        return (x >= div.getX() && x<= div.getX()+BOX_LENGTH && y >= div.getY() && y <= div.getY()+BOX_LENGTH);
    }
    
    public boolean isClickedOnEquals(double x, double y){
        return (x >= equals.getX() && x<= equals.getX()+EQUALS_LENGTH && y >= equals.getY() && y <= equals.getY()+BOX_LENGTH);
    }
    
    public boolean isClickedOnPosneg(double x, double y){
        return (x >= posneg.getX() && x<= posneg.getX()+BOX_LENGTH && y >= posneg.getY() && y <= posneg.getY()+BOX_LENGTH);
    }
    
    public boolean isClickedOnClear(double x, double y){
        return (x >= clear.getX() && x<= clear.getX()+BOX_LENGTH && y >= clear.getY() && y <= clear.getY()+BOX_LENGTH);
    }
    
    public boolean isClickedOnDeci(double x, double y){
        return (x >= deci.getX() && x<= deci.getX()+BOX_LENGTH && y >= deci.getY() && y <= deci.getY()+BOX_LENGTH);
    }
    
    public boolean isClickedOnSqrt(double x, double y){
        return (x >= sqroot.getX() && x<= sqroot.getX()+BOX_LENGTH && y >= sqroot.getY() && y <= sqroot.getY()+(BOX_LENGTH/2));
    }
    
    public boolean isClickedOnSin(double x, double y){
        return (x >= sin.getX() && x<= sin.getX()+BOX_LENGTH && y >= sin.getY() && y <= sin.getY()+(BOX_LENGTH/2));
    }
    
    public boolean isClickedOnCos(double x, double y){
        return (x >= cos.getX() && x<= cos.getX()+BOX_LENGTH && y >= cos.getY() && y <= cos.getY()+(BOX_LENGTH/2));
    }
    
    public boolean isClickedOnLn(double x, double y){
        return (x >= ln.getX() && x<= ln.getX()+BOX_LENGTH && y >= ln.getY() && y <= ln.getY()+(BOX_LENGTH/2));
    }
    
    public void displayAnswer(String c){
        displayAnswer.setFont("MONTSERRAT-30");
        displayAnswer.setLabel(c);
        add(displayAnswer,display.getX()+X_LABEL_OFFSET,display.getY()+Y_LABEL_OFFSET);
    }
    
    public void mouseClicked(MouseEvent me){
        if(isClickedOn0(me.getX(), me.getY())){
            concat = concat + "0";
            displayAnswer(concat);
        }
        else if(isClickedOn1(me.getX(), me.getY())){
            concat = concat + "1";
            displayAnswer(concat);
        }
        else if(isClickedOn2(me.getX(), me.getY())){
            concat = concat + "2";
            displayAnswer(concat);
        }
        else if(isClickedOn3(me.getX(), me.getY())){
            concat = concat + "3";
            displayAnswer(concat);
        }
        else if(isClickedOn4(me.getX(), me.getY())){
            concat = concat + "4";
            displayAnswer(concat);
        }
        else if(isClickedOn5(me.getX(), me.getY())){
            concat = concat + "5";
            displayAnswer(concat);
        }
        else if(isClickedOn6(me.getX(), me.getY())){
            //button6.setFillColor(Color.BLACK);
            //button1.setFilled(true);
            concat = concat + "6";
            displayAnswer(concat);
        }
        else if(isClickedOn7(me.getX(), me.getY())){
            //button7.setFillColor(Color.BLACK);
            //button1.setFilled(true);
            concat = concat + "7";
            displayAnswer(concat);
        }
        else if(isClickedOn8(me.getX(), me.getY())){
            //button8.setFillColor(Color.BLACK);
            //button1.setFilled(true);
            concat = concat + "8";
            displayAnswer(concat);
        }
        else if(isClickedOn9(me.getX(), me.getY())){
            //button9.setFillColor(Color.BLACK);
            //button1.setFilled(true);
            concat = concat + "9";
            displayAnswer(concat);
        }
        else if(isClickedOnAdd(me.getX(), me.getY())){
            //add.setFillColor(Color.BLACK);
            //button1.setFilled(true);
            displayAnswer("+");
            toInput(concat,"+");
            concat="";
        }
        else if(isClickedOnSubtract(me.getX(), me.getY())){
            //subs.setFillColor(Color.BLACK);
            //button1.setFilled(true);
            displayAnswer("-");
            toInput(concat,"-");
            concat="";
        }
        else if(isClickedOnMultiply(me.getX(), me.getY())){
            //mult.setFillColor(Color.BLACK);
            //button1.setFilled(true);
            displayAnswer("x");
            toInput(concat,"*");
            concat="";
        }
        else if(isClickedOnDivide(me.getX(), me.getY())){
            //div.setFillColor(Color.BLACK);
            //button1.setFilled(true);
            displayAnswer("/");
            toInput(concat,"/");
            concat="";
        }
        else if(isClickedOnClear(me.getX(), me.getY())){
            concat = "";
            displayAnswer("0");
            resetInput();
        }
        else if(isClickedOnEquals(me.getX(),me.getY())){
            toInput(concat,"=");
            InputArray = input.toCharArray(); 
            InputToPost();
        }
        else if(isClickedOnPosneg(me.getX(),me.getY())){
            int temp = Integer.parseInt(concat);
            temp = temp * (-1);
            displayAnswer(""+temp);
            temp = temp * (-1);
            concat = "0-"+temp;
        }
        else if(isClickedOnDeci(me.getX(),me.getY())){
            concat = concat + ".";
            displayAnswer(concat);
        }
        else if(isClickedOnSqrt(me.getX(),me.getY())){
            double x = Double.parseDouble(concat);
            double ans = Math.sqrt(x);
            concat = ""+ans;
            displayAnswer(concat);
        }
        else if(isClickedOnSin(me.getX(),me.getY())){
            double x = Double.parseDouble(concat);
            x = Math.toRadians(x);
            double ans = Math.sin(x);
            concat = ""+ans;
            displayAnswer(concat);
        }
        else if(isClickedOnCos(me.getX(),me.getY())){
            double x = Double.parseDouble(concat);
            x = Math.toRadians(x);
            double ans = Math.cos(x);
            concat = ""+ans;
            displayAnswer(concat);
        }
        else if(isClickedOnLn(me.getX(),me.getY())){
            double x = Double.parseDouble(concat);
            double ans = Math.log(x);
            concat = ""+ans;
            displayAnswer(concat);
        }
    } 
    
    public void toInput(String number, String oper){
        input = input + number + oper;
    }
    
    public void resetInput(){
        input = "";
    }
    
    public boolean InputChecker(int i){
        return(InputArray[i]=='+' || InputArray[i]=='-' || InputArray[i]=='*' || InputArray[i]=='/' || InputArray[i]=='=');
    }
    
    public boolean PostChecker(int i){
        return(PostArray[i]=='+' || PostArray[i]=='-' || PostArray[i]=='*' || PostArray[i]=='/' || PostArray[i]=='=');
    }
    
    public int preference(char c){
        switch(c){
            case '+': 
            case '-':
                return 0;
            case '*':
            case '/':
                return 1;
        }
        return 0;
    }
    
    public void InputToPost(){
        System.out.println(input);
        for(int i=0; i<input.length(); i++){
            if (!InputChecker(i)){
                number = number + InputArray[i];
            }
            else if(InputChecker(i) && ope.isEmpty()){
                //ope.push(InputArray[i]);
                post = post + number;
                number = "";
            }
            else if(InputArray[i]=='='){
                post = post +" "+number;
                number = "";
                post = post + ope.top();
                ope.pop();
                while(ope.isEmpty()==false){
                    post = post + ope.top();
                    ope.pop();
                }
                post = post + "=";
                PostToAns();
            }
            else if(InputChecker(i) && ope.isEmpty()==false) {
                post = post + " "+number;
                number = "";
                if(preference(InputArray[i]) == preference(ope.top())){
                    post = post + ope.top();
                    //ope.pop();
                    //ope.push(InputArray[i]);
                }
                else if(preference(InputArray[i]) < preference(ope.top())){
                    while(ope.isEmpty()==false && preference(InputArray[i]) < preference(ope.top())){
                        post = post + ope.top();
                        ope.pop();
                    }
                    //ope.push(InputArray[i]);
                }
                else if(preference(InputArray[i]) > preference(ope.top())){
                    ope.push(InputArray[i]);
                }
            }
        } 
    }
    
    public void PostToAns(){
        System.out.println(post);
        PostArray = post.toCharArray();
        for(int i=0; i < post.length(); i++){
            if(PostArray[i]!=' ' && PostChecker(i)==false){
                postnum = postnum + PostArray[i];
            }
            else if(PostArray[i]==' ' && num.isEmpty()){
                num.push(Double.parseDouble(postnum));
                postnum="";
            }
            else if(PostArray[i]==' ' && num.isEmpty()==false && PostChecker(i-1)==false){
                num.push(Double.parseDouble(postnum));
                postnum="";
            }
            else if(PostChecker(i) && PostChecker(i-1)==false){
               num.push(Double.parseDouble(postnum));
               postnum="";
               Computer(PostArray[i]); 
            }
            else if(PostChecker(i) && PostChecker(i-1) && PostArray[i]!='='){
                Computer(PostArray[i]);
            }
            else if(PostArray[i]=='='){
                double answer = (double)num.top();
                num.pop();
                displayAnswer(""+answer);
                concat = ""+answer;
                reset();
            }
        }
    }
    
    public void Computer(char c){
        switch(c){
            case '+':{
                double x = (double)num.top();
                num.pop();
                double y = (double)num.top();
                num.pop();
                double answer = x + y;
                num.push(answer);
                break;
            }
            case '-':{
                double x = (double)num.top();
                num.pop();
                double y = (double)num.top();
                num.pop();
                double answer = y - x;
                num.push(answer);
                break;
            }
            case '*':{
                double x = (double)num.top();
                num.pop();
                double y = (double)num.top();
                num.pop();
                double answer = x*y;
                num.push(answer);
                break;
            }
            case '/':{
                double x = (double)num.top();
                num.pop();
                double y = (double)num.top();
                num.pop();
                double answer = y/x;
                num.push(answer);
                break;
            }
        }
    }
    
    public void reset(){
        input = "";
        post = "";
        number = "";
        postnum = "";
    }
    
    public void init(){
        this.resize(WIDTH, HEIGHT);
        displayCalculator();
        displayAnswer("0");
        addMouseListeners();
    }

    
}
