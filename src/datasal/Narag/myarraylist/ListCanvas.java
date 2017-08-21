/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasal.Narag.myarraylist;

/*
 * File: ListCanvas.java
 * ------------------------
 * This file keeps track of the List display.
 */

import acm.graphics.*;
import java.awt.*;

public class ListCanvas<E> extends GCanvas {

/** Resets the display */
	public void reset() {
		 removeAll();
	}

/**
 * Updates the list on the screen.
 */
	public void displayList(MyArrayList list) {
                setBackground(Color.BLACK);
                removeAll();
                int offset = 3;
		for(int i = 1; i <= list.size(); i++){
                   GLabel item = new GLabel(""+ i +". " + list.get(i));
                   item.setFont("Century Schoolbook L-32-italic");
                   item.setColor(Color.GREEN);
                   add(item, (getWidth() - item.getWidth())/2, getWidth()/10 + i * (item.getHeight() + offset)); 
                }
	}
}
