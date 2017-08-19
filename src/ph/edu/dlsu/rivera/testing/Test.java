/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.rivera.testing;

import acm.program.GraphicsProgram;
import ph.edu.dlsu.rivera.breakout.*;
import ph.edu.dlsu.rivera.yahtzee.Yahtzee;



/**
 *
 * @author Rivera
 */
public class Test extends GraphicsProgram{
    @Override
    public void run(){
        System.out.println("Running");
//        Breakout trial = new Breakout();
Yahtzee trial = new Yahtzee();
        trial.start();
        
    }
     public static void main(String[] args){
         new Test().start(args);
     }
}
