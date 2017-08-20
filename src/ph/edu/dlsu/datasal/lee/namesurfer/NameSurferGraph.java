package ph.edu.dlsu.datasal.lee.namesurfer;

import acm.graphics.*;
import java.awt.Color;
import java.awt.event.*;
import java.util.*;

import ph.edu.dlsu.datasal.lee.myqueue.myQueue;

public class NameSurferGraph extends GCanvas implements NameSurferConstants, ComponentListener {
        int[] val= new int[12];
        int[] rocval= new int[12];
        int[] position= new int[12];

        String nam, data, tempName, tempVal, reset="";
        int select=0;
        Color tempCol;
        
        myQueue<String> listV = new myQueue();
        myQueue<String> listN= new  myQueue();
        myQueue<Color> listC= new  myQueue();
        myQueue<Color> newC = new myQueue();
        myQueue<String> newV = new myQueue();
        myQueue<String> newN= new myQueue();

	public NameSurferGraph() {
		addComponentListener(this);
	}
	NameSurfer M= new NameSurfer();
        
	public void clear() {
                removeAll();
                screen();
                while(listV.isEmpty()!=true){
                        listV.dequeue();
                        listN.dequeue();
                        listC.dequeue();
                }
                while(newV.isEmpty()!=true){
                        newV.dequeue();
                        newN.dequeue();
                        newC.dequeue();
                }
            }
                
	public void addEntry(String entry, String name, Color objCol) {
            while(newN.isEmpty()==false){
                    listV.enqueue(newV.dequeue());
                    listN.enqueue(newN.dequeue());
                    listC.enqueue(newC.dequeue());                    
                }   
                if(!name.equals("")){
                    listV.enqueue(entry);
                    listN.enqueue(name);
                    listC.enqueue(objCol);
                }
                    
            if(select==0||select==2){
                while(listN.isEmpty()==false){
                    data=listV.dequeue();   
                    tempCol=listC.dequeue();
                    nam=listN.dequeue();
                    val=convert(data);
                    if(select==2){
                        rocval[0]=val[0];
                        for(int i=1;i<NDECADES;i++){
                            rocval[i]=(val[i]-val[i-1]);
                        }
                    }
                    
                    position[11]=0;
                    if(select==0){
                        for(int i=0;i<NDECADES-1;i++){
                            if(val[i]<=val[i+1]) position[i]=0;
                            else position[i]=1;
                        }
                        for(int i=0;i<NDECADES;i++){
                            setObjects(val[i],i,nam,tempCol,select);
                        }
                    }
                    if(select==2){
                        for(int i=0;i<NDECADES-2;i++){
                            if(rocval[i]>=rocval[i+1]) position[i]=0;
                            else position[i]=1;
                        }
                        for(int i=0;i<NDECADES;i++){
                            setObjects(rocval[i],i,nam,tempCol,select);
                        }
                    }
                    newV.enqueue(data);
                    newN.enqueue(nam);
                    newC.enqueue(tempCol);
                    }
                }
            
            if(select==1){
                int j=0;
                while(listN.isEmpty()==false){
                    data=listV.dequeue();
                    tempCol=listC.dequeue();
                    nam=listN.dequeue();
                    val=convert(data);
                    GLabel n = new GLabel(nam);
                    n.setFont("Calibri-14");
                    n.setColor(Color.WHITE);                    
                    add(n,10,25+25*(j+1));
                    for(int i=0;i<NDECADES;i++){
                        chart(val[i],i,j);
                    }
                    newV.enqueue(data);
                    newN.enqueue(nam);
                    newC.enqueue(tempCol);                    
                    j++;
                }
            }
	}
	
        public void chart(int val, int x, int y){
            GLabel year = new GLabel(""+val);
            year.setFont("Calibri-20");            
            add(year,((getWidth()*(x+2)/13)-.08*getWidth()+15),25+25*(y+1));
            year.setColor(Color.WHITE);            
            
        }
        
        public int[] convert(String text){
            int[] temp= new int[12];
            String tem;
            StringTokenizer st= new StringTokenizer(text," ");
            for(int i=0;i<NDECADES;i++){
                tem=st.nextToken();
                temp[i]= Integer.parseInt(tem);
            }
            return temp;
        }
	
	public void update(int sel) {
               setBackground(Color.BLACK);
               select=sel;
               screen();
               addEntry("","",null);
	}
        
        int allover;
        public int y2=-500,yfinal=0;
        public void setObjects(int y, int i, String name, Color objCol, int sel){
                GOval dot= new GOval(5,5);
                GLabel value= new GLabel(""+y+","+name);
                value.setColor(Color.RED);
                value.setFont("Calibri-BOLD-20");
                if(y==0&&sel==0) yfinal=getHeight()-30;
                else if(sel==0) yfinal=y*getHeight()/1200+30;
                else if(sel==2) yfinal=(getHeight()/2)-y*getHeight()/2400;
                GLine line = new GLine((getWidth()*(i)/12)-.08*getWidth()+10,y2,(getWidth()*(i+1)/12)-.08*getWidth()+10,yfinal);
                GLine line2 = new GLine((getWidth()*(i)/12)-.08*getWidth()+10,y2+1,(getWidth()*(i+1)/12)-.08*getWidth()+10,yfinal+1);
                line.setColor(objCol);
                line2.setColor(objCol);
                dot.setColor(objCol);
                add(line);
                add(line2);
                dot.setFilled(true); 
                add(dot,(getWidth()*(i+1)/12)-.08*getWidth()+10,yfinal);
                if(position[i]==0)add(value,(getWidth()*(i+1)/12)-.08*getWidth()+20,yfinal-5);
                else if(position[i]==1) add(value,(getWidth()*(i+1)/12)-.08*getWidth()+20,yfinal+15);
                y2=yfinal;
        }
        
        public void screen(){
            removeAll();
		if(select==0||select==2){
                    for(int i=0;i<NDECADES;i++){
                        GLine grp = new GLine((getWidth()*(i+1)/12)-.08*getWidth()+10,0,(getWidth()*(i+1)/12)-.08*getWidth()+10,getHeight());
                        grp.setColor(Color.WHITE);
                        add(grp);
                    }
                    GLine grp = new GLine(0,getHeight()-30,getWidth(),getHeight()-30);  
                    add(grp);
                    grp.setColor(Color.WHITE);
                    
                    for(int i=0;i<NDECADES;i++){
                        GLabel year = new GLabel(""+(1900+i*10));
                        year.setColor(Color.WHITE);
                        add(year,((getWidth()*(i+1)/12)-.08*getWidth()+15),getHeight()-10);
                    }
                }
                if(select==1){
                        for(int i=0;i<=NDECADES;i++){
                            GLine grp = new GLine((getWidth()*(i+1)/13),0,(getWidth()*(i+1)/13),getHeight());
                            grp.setColor(Color.WHITE);
                            add(grp);
                            
                        }
                        GLine grp = new GLine(0,30,getWidth(),30);  
                        add(grp);
                        grp.setColor(Color.WHITE);
                        
                        for(int i=0;i<NDECADES;i++){
                            GLabel year = new GLabel(""+(1900+i*10));
                            year.setFont("Calibri-20");
                            year.setColor(Color.WHITE);                            
                            add(year,((getWidth()*(i+2)/13)-.08*getWidth()+15),25);
                        }
                        GLabel n = new GLabel("Name");
                        n.setFont("Calibri-20");
                        n.setColor(Color.WHITE);                        
                        add(n,((getWidth()*(1)/13)-.08*getWidth()+10),25);
                }
        }

        public void delete(String name){
            while(newN.isEmpty()==false){
                   tempName=newN.dequeue();
                   tempVal=newV.dequeue();
                   tempCol=newC.dequeue();
                   if(!tempName.equals(name)){
                       listV.enqueue(tempVal);
                       listN.enqueue(tempName);
                       listC.enqueue(tempCol);
                   }
            }
            update(select);
        }
        
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(select); }
	public void componentShown(ComponentEvent e) { }
}
