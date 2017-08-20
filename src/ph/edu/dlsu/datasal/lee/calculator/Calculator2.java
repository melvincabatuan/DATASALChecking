
package ph.edu.dlsu.datasal.lee.calculator;

import acm.io.*;
import acm.program.*;
import acm.util.*;
import acm.graphics.*;  
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import static jdk.nashorn.internal.objects.NativeMath.round;

import ph.edu.dlsu.datasal.lee.mystack.*;

public class Calculator2 extends GraphicsProgram{
        char x,prev=' ';
        int start=0, op=0,place,check=0;
        boolean ex=false,ex1=false,ex2=false;
        double o1,o2;
        String text="",text1="",texta="",textb="",text2="",output="",answer="", y="",y1,y2;        
        public MyStack<Character> list= new MyStack();
        public MyStack<Double> flist= new MyStack();

        public static final int APPLICATION_WIDTH = 588;
        public static final int APPLICATION_HEIGHT = 588;

        GImage box = new GImage("C:\\Users\\iwcnrlee1\\Desktop\\Calculator\\Cover.png");
        GImage n0 = new GImage("C:\\Users\\iwcnrlee1\\Desktop\\Calculator\\0.png");
        GImage n1 = new GImage("C:\\Users\\iwcnrlee1\\Desktop\\Calculator\\1.png");
        GImage n2 = new GImage("C:\\Users\\iwcnrlee1\\Desktop\\Calculator\\2.png");
        GImage n3 = new GImage("C:\\Users\\iwcnrlee1\\Desktop\\Calculator\\3.png");
        GImage n4 = new GImage("C:\\Users\\iwcnrlee1\\Desktop\\Calculator\\4.png");
        GImage n5 = new GImage("C:\\Users\\iwcnrlee1\\Desktop\\Calculator\\5.png");
        GImage n6 = new GImage("C:\\Users\\iwcnrlee1\\Desktop\\Calculator\\6.png");
        GImage n7 = new GImage("C:\\Users\\iwcnrlee1\\Desktop\\Calculator\\7.png");
        GImage n8 = new GImage("C:\\Users\\iwcnrlee1\\Desktop\\Calculator\\8.png");
        GImage n9 = new GImage("C:\\Users\\iwcnrlee1\\Desktop\\Calculator\\9.png");
        GImage add = new GImage("C:\\Users\\iwcnrlee1\\Desktop\\Calculator\\add.png");
        GImage min = new GImage("C:\\Users\\iwcnrlee1\\Desktop\\Calculator\\min.png");
        GImage div = new GImage("C:\\Users\\iwcnrlee1\\Desktop\\Calculator\\div.png");
        GImage mul = new GImage("C:\\Users\\iwcnrlee1\\Desktop\\Calculator\\x.png");
        GImage eq = new GImage("C:\\Users\\iwcnrlee1\\Desktop\\Calculator\\eq.png");
        GImage C = new GImage("C:\\Users\\iwcnrlee1\\Desktop\\Calculator\\C.png");
        GImage dot = new GImage("C:\\Users\\iwcnrlee1\\Desktop\\Calculator\\period.png");
        GImage ans = new GImage("C:\\Users\\iwcnrlee1\\Desktop\\Calculator\\ans.png");
        GImage X = new GImage("C:\\Users\\iwcnrlee1\\Desktop\\Calculator\\X.png");
        GImage back = new GImage("C:\\Users\\iwcnrlee1\\Desktop\\Calculator\\del.png");
        GImage sin = new GImage("C:\\Users\\iwcnrlee1\\Desktop\\Calculator\\sin.png");
        GImage cos = new GImage("C:\\Users\\iwcnrlee1\\Desktop\\Calculator\\cos.png");
        GImage tan= new GImage("C:\\Users\\iwcnrlee1\\Desktop\\Calculator\\tan.png");
        GImage change = new GImage("C:\\Users\\iwcnrlee1\\Desktop\\Calculator\\change.png");
        GLabel dis = new GLabel(" ");
        GLabel dis2 = new GLabel(" ");
        
    public static void main(String[] args) {
        new Calculator2().start(args);
    }
    
    public void run() {
        image(); 
        addMouseListeners();
        
    }
    
    public void image(){
        add(box);
        add(n0,66,463);
        add(n1,66,379);
        add(n4,66,291);
        add(n7,68,205);
        
        add(dot,156,462);
        add(n2,155,380);
        add(n5,157,291);
        add(n8,158,206);
        
        add(eq,245,462);
        add(n3,246 ,378);
        add(n6,245,292);
        add(n9,245,205);
        
        add(min,332,463);
        add(add,332,379);
        add(mul,332,290);
        add(div,332,205);
        
        add(X,422,463);
        add(ans,420,377);
        add(back,421,289);
        add(C,420,205);
        
        add(sin,38,21);
        add(cos,170,21);
        add(tan,305,21);
        add(change,438,21);
    }
    
    public void mouseClicked(MouseEvent e){
        if(MouseInfo.getPointerInfo().getLocation()!=null){
                x=Equal(Button());
                Display(x);
        }
        if(start==1){
            if(ex==true){
                if(ex1==true&&ex2==true){
                    text="";
                    output="";
                }
            }
            else{
                 text="";
                 output="";
            }  
        }
        start=0;
    }
    
    public GObject Button(){
                int y = MouseInfo.getPointerInfo().getLocation().y;
                int x = MouseInfo.getPointerInfo().getLocation().x;
                return getElementAt(x-8,y-50);
    }
    
    public char Equal(GObject but){
        if(but==n0) return '0';
        else if(but==n1) return '1';
        else if(but==n2) return '2';
        else if(but==n3) return '3';
        else if(but==n4) return '4';
        else if(but==n5) return '5';
        else if(but==n6) return '6';
        else if(but==n7) return '7';
        else if(but==n8) return '8';
        else if(but==n9) return '9';
        else if(but==X) return 'X';
        else if(but==dot) return '.' ;
        else if(but==ans) return 'a' ;

        else if(but==sin) return 'S';
        else if(but==cos) return 'C';
        else if(but==tan) return 'T';
        else if(but==change) return '-';
        
        else if(but==add) return '+';
        else if(but==min) return '−';
        else if(but==mul) return '*';
        else if(but==div) return '/';
        
        else if(but==eq){
            start=1;  
            return '=';
        }
        else if(but==C){
            text="";
            output="";
            y="";
            op=0;
            prev=' ';
            return ' ';
        }
        else if(but==back){
            for(int i=0;i<text.length();i++){
                if(text.charAt(i)=='X') ex=false;
            }
            return 'd' ;
        }
        else return ' ';
    }   
    
    public void Display(char x){
        add(dis,60,120);
        dis.setFont("Arial-Bold-50");   
        if(x!=' '&&x!='a'&&x!='d') text=text+x;
        else if(x=='a') text=text+answer; 
        else if(x=='d'){
            text=text.substring(0, text.length() - 1);
        }
        dis.setLabel(text);
        add(dis2,60,170);
        dis2.setFont("Arial-Bold-50");   
        if(x=='='){

            for(int i=0;i<text.length();i++){
                    if(text.charAt(i)=='X'){
                        if(ex!=true){
                            ex=true;
                            break;
                        }
                        else ex1=true;
                        place=text.indexOf("X");
                    }
                    else;
                }
            
                
            if(ex==true){
                if(ex1==true){
                    text1=text.substring(0,text.indexOf("="));
                    text2=text.substring(text.indexOf("=")+1,text.length()-1);
                    text=text.substring(0, text.length() - 1);
                    dis.setLabel(text);
                    int i=-100;
                    while(check==0){
                        texta = text1.replaceAll("X",i+"");
                        textb = text2.replaceAll("X",i+"");
                        texta=texta+"+0";                        
                        textb=textb+"+0";
                        y1=solve(convert(texta+""));
                        println(y1);
                        y2=solve(convert(textb+""));
                        println(y2);
                        o1=Double.parseDouble(y1);
                        o2=Double.parseDouble(y2);
                        if(o1==o2){
                          y="="+i;
                          check=1;
                        }
                        i++;
                    }
                    while(flist.isEmpty()!=true) flist.pop();
                }
            }
            else{
                text=text.substring(0, text.length() - 1);
                dis.setLabel(text);
                 y="="+solve(convert(text+"+0"));
            }
        }
        dis2.setLabel(""+y);
        if(ex1==true){
                ex=false;
                ex1=false;
                ex2=false;
                text="";
                text1="";
                text2="";
                texta="";
                textb="";
                check=0;
                place=0;
                while(flist.isEmpty()!=true) flist.pop();
        }     
    }    
    
    
    public String convert(String text){
        for(int i=0;i<text.length();i++){
        if(text.charAt(i)!='+'&&text.charAt(i)!='−'&&text.charAt(i)!='/'&&text.charAt(i)!='*'){
            if(text.charAt(i)=='π') output=output+3.1416;
            else if(text.charAt(i)=='a') output=output+answer;
            else output=output+text.charAt(i);
        }
        else output=output+",";
        
        if(text.charAt(i)=='*'||text.charAt(i)=='/') list.push(text.charAt(i));
        else if((text.charAt(i)=='+')||(text.charAt(i)=='−')){
            if(list.isEmpty()!=true){
                if((list.top()!='*')||(list.top()!='/')){
                    while(list.isEmpty()!=true){
                        output=output+list.pop();
                    }
                    list.push(text.charAt(i));
                }
            }
            else list.push(text.charAt(i));
        }
        }
        while(list.isEmpty()!=true){
                        output=output+list.pop();
        }
        println(output);
        return output;
    }
    
    public String solve(String text){
        double number=0;        
            String temp="";
            for(int i=0;i<text.length();i++){
                if(text.charAt(i)=='S'||text.charAt(i)=='C'||text.charAt(i)=='T'){
                    prev=text.charAt(i);
                }               
                else if(text.charAt(i)!='+'&&text.charAt(i)!='−'&&text.charAt(i)!='/'&&text.charAt(i)!='*'&&text.charAt(i)!=','&&text.charAt(i)!='='){
                    temp=temp+text.charAt(i);
                }
                else{
                    if(temp!=""){
                        number=Double.parseDouble(temp);
                        
                        if(prev!=' '){ 
                            if(prev=='S')number=Math.sin(number);
                            if(prev=='C')number=Math.cos(number);
                            if(prev=='T')number=Math.tan(number);
                        }                        
                        flist.push(number);
                        number=0;
                        temp="";
                    }
                }
                    if(text.charAt(i)=='+'||text.charAt(i)=='−'||text.charAt(i)=='/'||text.charAt(i)=='*'){
                        number=Oper(text.charAt(i),flist.pop(),flist.pop());
                        flist.push(number);
                        
                    }
                
                
                    else if(text.charAt(i)=='='&&op==0){
                        
                        number=flist.pop();
                        op=1;
                        prev=' ';
                    }
                
            }
        output="";
        number= Math.ceil(number * 10000) / 10000;
        answer=number+"";
        return answer;
    }
    
    public double Oper(char x,double a, double b){
        double total=0;
        if(x=='+'){
            total=b+a;
        }
        if(x=='−'){
            total=b-a;
        }
        
        else if(x=='*'){
            total=b*a;
        }
        else if(x=='/'){
            if (b==0) println("ERROR");
            else total=b/a;
        }
        return total;
    }
}


