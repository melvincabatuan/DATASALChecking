package ph.edu.dlsu.datasal.wenceslao.datasalapp;

// Name:    Luis Paolo D. Wenceslao 
// Section: LBYCP12-EQ1

import ph.edu.dlsu.datasal.wenceslao.myarraylist.MyArrayList;
import acm.program.*;
import acm.graphics.*;
import ph.edu.dlsu.datasal.wenceslao.breakout.*;

public class DatasalApp extends ConsoleProgram {
    
    private MyArrayList<String> list = new MyArrayList<String>();
    
    public void run(){
        
        list.createList();
        
        list.add(1, "1");
        list.add(2, "2");
        list.add(3, "3");
        list.remove(3);
        list.add(3, "4");
        
        for (int i = 1; i <= 3; i++){
            println(i + ". " + list.get(i));
        }
        
        println("List size: " + list.size());
        println("Array contains: " + list.contains("2"));
        
        list.clear();
        
        println("List cleared");
        
        println("List size: " + list.size());
    }
    
    public static void main(String[] args) {
        new DatasalApp().start(args);
    }
    
}