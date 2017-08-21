/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.kitane.datasalapp;
import acm.graphics.*;
import acm.program.*;
/**
 *
 * @author ChristophJohnEric
 */
public class TestEntry extends ConsoleProgram {
    private FacePamphletProfile profile = new FacePamphletProfile("Harry");
    private GImage image4;
    
    public void run(){
        profile.setImage(image4);
        profile.setStatus("Selling");
        profile.addFriend("Hermione");
        profile.addFriend("Ron");
        profile.addFriend("Ginger");
        profile.addFriend("Hagrid");
        
        println(profile.toString());
    }
    
    public static void main(String args[]){
        new TestEntry().start(args);
    }
}
