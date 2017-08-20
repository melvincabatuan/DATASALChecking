package ph.edu.dlsu.datasal.lee.namesurfer;

import acm.program.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;



public class NameSurfer extends Program implements NameSurferConstants {
//public class NameSurfer extends Program implements NameSurferConstants {
    JButton gBtn = new JButton("Graph");
    JButton cBtn = new JButton("Clear");
    JButton tBtn = new JButton("Table");
    JButton dBtn = new JButton("Delete");
    JButton rBtn = new JButton("Graph(Change)");
    JFrame Frame = new JFrame("NameSurfer");
    JLabel Label = new JLabel("Name:");
    
    int select=0, counter=1, check=0;
    Color objCol=Color.WHITE;
    
    TextField text = new TextField(20);
    String year="";
    String dyear="0 0 0 0 0 0 0 0 0 0 0 0";

    File texts= new File("C:\\Users\\iwcnrlee1\\Documents\\NetBeansProjects\\NameSurfer\\src\\names-data.txt");
    BufferedReader br;
    
    public static void main(String[] args ) {
            new NameSurfer().start(args);
        }
    
    
    public NameSurferGraph graph;
	public void init() {
            graph = new NameSurferGraph();
            gBtn.addActionListener(this);
            gBtn.setActionCommand("gBtn");
            cBtn.addActionListener(this);
            cBtn.setActionCommand("cBtn");
            tBtn.addActionListener(this);
            tBtn.setActionCommand("tBtn");
            dBtn.addActionListener(this);
            dBtn.setActionCommand("dBtn");
            rBtn.addActionListener(this);
            rBtn.setActionCommand("rBtn");
            add(Label, NORTH);
            add(text, NORTH);
            add(graph);
            add(gBtn,NORTH);
            add(cBtn,NORTH);   
            add(tBtn,NORTH);
            add(dBtn,NORTH);
            add(rBtn,NORTH);

	}
        
        public void run(){
                while(true){
                    addActionListeners();
                }
        }
        
        String line="";
	public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("gBtn")){
               select=0;
               try {
                    input();
                    counter++;            
                    } catch (IOException ex) {
                            Logger.getLogger(NameSurfer.class.getName()).log(Level.SEVERE, null, ex);
                    }
            } 
            if(e.getActionCommand().equals("tBtn")){
               select=1;
               try {
                    input();
                    counter++;
                    } catch (IOException ex) {
                            Logger.getLogger(NameSurfer.class.getName()).log(Level.SEVERE, null, ex);
                    }
            } 
            if(e.getActionCommand().equals("cBtn")){
                graph.clear();
                counter=0;
            } 
            if(e.getActionCommand().equals("dBtn")){
                graph.delete(text.getText());
                text.setText("");                
            } 
            if(e.getActionCommand().equals("rBtn")){
                select=2;
                try {
                    input();
                    counter++;
                    } catch (IOException ex) {
                            Logger.getLogger(NameSurfer.class.getName()).log(Level.SEVERE, null, ex);
                    }                
            } 
        }

        
        
        public void input() throws IOException{
            switch(counter%5){
                case 1:objCol=Color.CYAN; break;
                case 2:objCol=Color.GREEN; break; 
                case 3:objCol=Color.MAGENTA; break;
                case 4:objCol=Color.YELLOW; break;
                case 0:objCol=Color.ORANGE; break;
            }
            
            br = new BufferedReader(new FileReader(texts));
                while((line=br.readLine())!=null){
                    NameSurferEntry entry = new NameSurferEntry(line+" ");
                    if((text.getText()).equals(entry.getName())||(text.getText()).equals("")){
                        for(int i=0;i<NDECADES;i++){
                            year=(year+entry.getRank(i)+" ");
                        }
                        System.out.println(entry.toString());
                        graph.update(select);
                        graph.addEntry(year,text.getText(),objCol);
                        year="";
                        text.setText("");
                        check=1;
                        break;
                    }
                }
                        graph.update(select);
                        graph.addEntry(dyear,text.getText(),objCol);
                        text.setText("");
                }
}
