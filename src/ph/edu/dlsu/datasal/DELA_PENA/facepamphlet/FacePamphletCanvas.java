/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */
package ph.edu.dlsu.datasal.DELA_PENA.facepamphlet;
import ph.edu.dlsu.datasal.DELA_PENA.ADT.MyGraph.GraphDP;
import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas implements FacePamphletConstants {
	private GLabel name;
        private GImage profpic;
        private GLabel status;
        private GLabel message;
        private GLabel friends = new GLabel("Friends:");
        private GLabel friendname;
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		// You fill this in
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
            if(message!=null)
                remove(message);
            message = new GLabel(msg);
            message.setFont(MESSAGE_FONT);
            add(message, (getWidth() - message.getWidth())/2, getHeight() - BOTTOM_MESSAGE_MARGIN);
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
            GRect image;
            name = new GLabel(profile.getName());
            name.setColor(Color.blue);
            name.setFont(PROFILE_NAME_FONT);
            name.setLocation(LEFT_MARGIN, TOP_MARGIN + name.getHeight());
            add(name);
            
            if(profile.getImage() == null){
                image = new GRect(200, 200);
                add(image, LEFT_MARGIN, TOP_MARGIN + name.getHeight() + IMAGE_MARGIN);
                GLabel noimage = new GLabel("No Image");
                noimage.setFont(MESSAGE_FONT);
                add(noimage, LEFT_MARGIN + (IMAGE_WIDTH - noimage.getWidth())/2, TOP_MARGIN + name.getHeight() + (IMAGE_MARGIN) + (IMAGE_HEIGHT/2));
            }
            else{
                profpic = profile.getImage();
                profpic.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
                add(profpic, LEFT_MARGIN, TOP_MARGIN + name.getHeight() + IMAGE_MARGIN);
            }
            
            status = new GLabel(profile.getStatus());
            status.setFont(PROFILE_STATUS_FONT);
            add(status, LEFT_MARGIN, TOP_MARGIN + name.getHeight() + IMAGE_MARGIN + IMAGE_HEIGHT + STATUS_MARGIN + status.getHeight());
            
            friends.setFont(PROFILE_FRIEND_LABEL_FONT);
            add(friends, LEFT_MARGIN + IMAGE_WIDTH + PROFILE_FRIEND_LEFT_MARGIN, TOP_MARGIN + name.getHeight() + IMAGE_MARGIN);
            
            Iterator it = profile.getFriends();
            double x = LEFT_MARGIN + IMAGE_WIDTH + PROFILE_FRIEND_LEFT_MARGIN;
            double y = TOP_MARGIN + name.getHeight() + IMAGE_MARGIN;
            while(it.hasNext()){
                friendname = new GLabel((String)it.next());
                friendname.setFont(PROFILE_FRIEND_FONT);
                y+=friendname.getHeight();
                add(friendname, x, y);
            }
            
            showMessage("Displaying "+profile.getName());
	}
        
        public void displayGraph(GraphDP graph){
            removeAll();
            Iterator it;
            Map<FacePamphletProfile, double[]> nodes = new HashMap();
            
            int nodecount = graph.getNumNodes();
            double imgsize = 50;
            double xcenter = getWidth()/2;
            double ycenter = getHeight()/2;
            double radius = 0;
            double degreeshift;
            double angle = 0;
            if(getHeight()>getWidth())
                radius = (getWidth()-(TOP_MARGIN*2) - imgsize)/2;
            else
                radius = (getHeight()-(TOP_MARGIN*2) - imgsize)/2;
            
            degreeshift = (2*3.14)/nodecount;
            
            it = graph.getNodes();
            while(it.hasNext()){
                FacePamphletProfile currentnode = (FacePamphletProfile)it.next();
                double[] coords = new double[2];
                coords[0] = xcenter + (radius*Math.cos(angle));
                coords[1] = ycenter + (radius*Math.sin(angle));
                nodes.put(currentnode, coords);
                angle+=degreeshift;
                
            }
            
            it = graph.getNodes();
            while(it.hasNext()){
                FacePamphletProfile currentnode = (FacePamphletProfile)it.next();
                GImage nodeimg = currentnode.getImage();
                
                Iterator connections = graph.getConnections(currentnode).iterator();
                while(connections.hasNext()){
                    FacePamphletProfile currentconnection = (FacePamphletProfile)connections.next();
                    GLine connectline = new GLine(nodes.get(currentnode)[0], nodes.get(currentnode)[1], nodes.get(currentconnection)[0], nodes.get(currentconnection)[1]);
                    add(connectline);
                }
                if(nodeimg!=null){
                    nodeimg.setSize(imgsize, imgsize);
                    add(nodeimg, nodes.get(currentnode)[0]-(nodeimg.getWidth()/2), nodes.get(currentnode)[1] - (nodeimg.getHeight()/2));
                }
                else{
                    GLabel name = new GLabel(currentnode.getName());
                    name.setFont(PROFILE_FRIEND_FONT);
                    add(name, nodes.get(currentnode)[0], nodes.get(currentnode)[1]);
                }
            }
            
        }

	
}
