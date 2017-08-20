/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.bhuller.mystack;

/**
 *
 * @author Bhuller
 */
public interface Stack <E> {
    
    public E peek();
    public boolean full();
    public boolean empty();
    public void push(E item);
    public E pop();
}