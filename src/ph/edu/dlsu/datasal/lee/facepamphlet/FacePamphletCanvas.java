package ph.edu.dlsu.datasal.lee.facepamphlet;

/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import acm.util.RandomGenerator;
import java.lang.Object.*;
import java.awt.*;
import java.awt.event.ComponentListener;
import java.util.*;
import ph.edu.dlsu.datasal.lee.artistry.Artistry;

import ph.edu.dlsu.datasal.lee.mygraph.*;

public class FacePamphletCanvas extends GCanvas implements FacePamphletConstants {
	GLabel message= new GLabel("") ;
        GImage image;
        GLabel name, status;
        Iterator friendlist;
        int counter=0;
        Graph network;
        LinkedList<GOval> dot= new LinkedList();
        Artistry back = new Artistry();
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
            back.run();
            //background();
        }

	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
                add(message,getWidth()/3,getHeight()-BOTTOM_MESSAGE_MARGIN);
                message.setFont(MESSAGE_FONT);
                message.setLabel(msg);
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
            removeAll();
            if (profile.getImage()!=null){
                image=profile.getImage();
                image.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
                add(image,LEFT_MARGIN,TOP_MARGIN+IMAGE_MARGIN);

            }
            else{
                GRect square = new GRect(IMAGE_WIDTH,IMAGE_HEIGHT);
                add(square,LEFT_MARGIN,TOP_MARGIN+IMAGE_MARGIN);
                square.setFilled(true);
                square.setFillColor(Color.white);
                GLabel nImage = new GLabel("No Image");
                nImage.setFont("Calibri-18");
                add(nImage,LEFT_MARGIN+60,TOP_MARGIN+IMAGE_MARGIN+IMAGE_HEIGHT/2);
            }
            
            name = new GLabel(profile.getName());
            if(profile.getStatus()!=null) status = new GLabel(profile.getStatus());
            else status = new GLabel("");
            name.setFont(PROFILE_NAME_FONT);
            status.setFont(PROFILE_STATUS_FONT);
            add(name,LEFT_MARGIN,TOP_MARGIN+5);
            add(status,LEFT_MARGIN,TOP_MARGIN+IMAGE_HEIGHT+2*STATUS_MARGIN);
            
            friendlist= profile.getFriends();
            GLabel friends = new GLabel("Friend List");
            friends.setFont("Dialog-16-bold");
            add(friends,4*LEFT_MARGIN+IMAGE_WIDTH,2*TOP_MARGIN);            
            
            while(friendlist.hasNext()){
                GLabel friend = new GLabel(((String)friendlist.next()+" "));
                friend.setFont("Dialog-16");
                add(friend,4*LEFT_MARGIN+IMAGE_WIDTH,TOP_MARGIN*(counter+2)+20);
                counter++;
            }
            counter=0;
	}

        RandomGenerator rgen = new RandomGenerator();
        int x,y;
        public void Graph(Graph network, FacePamphletDatabase entry){
            removeAll();
            for(int i=0;i<entry.entry.size();i++){
                x = rgen.nextInt(20,getWidth()-20);
                y= rgen.nextInt(20,getHeight()-20);
                setCircle(i,x,y,entry.entry.get(i).getName());
            }
            if(network.G.length==0);
            else{
                for(int j=0;j<network.G.length;j++){
                    for(int i=0;i<network.G[j].size();i++){
                         setLines(network.G[j].get(i),network.G[j].get(i).w);
                    }
                }
            }
            dot.clear();
        }
        
        public void setCircle(int i,int x, int y, String name){
            GOval circle= new GOval(10,10);
            GLabel disName= new GLabel(name);
            disName.setFont("Calibri-24");
            circle.setFillColor(Color.BLACK);
            circle.setFilled(true);
            add(circle,x,y);
            add(disName,x-10,y-10);
            dot.add(circle);
            
        }
        
        int node1,node2;
        public void setLines(Edge line, int w){
            GLine connect = new GLine(dot.get(line.u).getX()+5,dot.get(line.u).getY()+5,dot.get(line.v).getX()+5,dot.get(line.v).getY()+5);
            GLabel weight = new GLabel(""+w);
            weight.setFont("Calibri-15-BOLD");
            weight.setColor(Color.BLUE);
            add(weight,(dot.get(line.u).getX()+dot.get(line.v).getX()+2)/2-5,(dot.get(line.u).getY()+dot.get(line.v).getY()+2)/2-5);
            add(connect);
        }
        
        public void background(){
            System.out.println(back.collect.size());
            for(int i=0;i<back.collect.size();i++){
                GObject object = back.collect.get(i);
                add(object);
                object.setLocation(object.getX(), object.getY());
            }
            
        }
	
}
