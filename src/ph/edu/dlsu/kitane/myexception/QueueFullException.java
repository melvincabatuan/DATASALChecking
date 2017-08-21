/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.kitane.myexception;

/**
 *
 * @author ChristophJohnEric
 */
public class QueueFullException extends RuntimeException{
    public QueueFullException(String s){
        super(s);
    }
}
