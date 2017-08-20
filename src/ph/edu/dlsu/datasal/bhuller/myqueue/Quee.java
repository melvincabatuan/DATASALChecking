package ph.edu.dlsu.datasal.bhuller.myqueue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author student
 */
public interface Quee<E> {
    public void create();
    public void add(E elem);
    public void deque();
    public E top();
    public E first();
    public int size();
    public boolean isEmpty();
    public boolean isFull();
    public E get(int i);
}
