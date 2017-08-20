/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.ang.mylinkedlist;

import java.util.Collection;
import ph.edu.dlsu.datasal.ang.myinterface.List;
import ph.edu.dlsu.datasal.ang.myexception.ListFullException;
import ph.edu.dlsu.datasal.ang.myexception.ListIndexOutOfBoundsException;

public class MyLinkedList<E> implements List<E> {

    private Node<E> head;
    private int numItems;

    public MyLinkedList() {
        createList();
    }

    public void createList() {
        numItems = 0;
        head = null;
    } //end constructor

    public void add(int index, E item) throws ListIndexOutOfBoundsException {
        if (index > 0 && index <= numItems + 1) {
            if (index == 1) { // Create head
                Node<E> newNode = new Node<E>(item);
                newNode.setNext(head);
                head = newNode;
            } else {
                Node<E> newNode = new Node<E>(item);
                Node<E> previous = find(index - 1);
                newNode.setNext(previous.getNext());
                previous.setNext(newNode);
            }
            numItems++;
        } else {
            throw new ListIndexOutOfBoundsException("ADD ERROR: List Index Out Of Bounds");
        }
    }

    public void remove(int index) throws ListIndexOutOfBoundsException {
        if (index > 0 && index <= numItems + 1) {
            if (index == 1) {
                head = head.getNext();
            } else {
                Node<E> previous = find(index - 1);
                Node<E> current = previous.getNext();
                previous.setNext(current.getNext());
            }
            numItems--;
        } else {
            throw new ListIndexOutOfBoundsException("REMOVE ERROR: List Index Out Of Bounds");
        }
    }

    public boolean isEmpty() {
        return numItems == 0;
    }

    public E get(int index) throws ListIndexOutOfBoundsException {
        if (index > 0 && index <= numItems + 1) {
            Node<E> current = find(index);
            E item = current.getItem();
            return item;
        } else {
            throw new ListIndexOutOfBoundsException("GET ERROR: List Index Out Of Bounds");
        }
    }

    public int size() {
        return numItems;
    }

    /// Locate a specified node in a linked list:
    private Node<E> find(int index) {
        // precondition: index is the number of the desired node,
        // precondition: assume 1 ≤ index ≤ numItems+1;
        // postcondition: returns a reference to the desired node.
        Node<E> current = head;

        for (int i = 1; i < index; i++) {
            current = current.getNext();
        }

        return current;
    } //end find

    @Override
    public boolean contains(Object o) {
        int x = 0;
        for (int i = 0; i < numItems; i++) {
            Node<E> current = find(i);
            if (o == current.getItem()) {
                x = i;
            }
        }
        Node<E> found = find(x);
        return o == found.getItem();
    }

    /*
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    @Override

    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    /*
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     */
    public void clear() {
        for (int i = 0; i < numItems; i++) {
            head = head.getNext();
        }
        numItems = 0;
    }

    @Override
    public void add(E e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void set(int index, E item) throws ListIndexOutOfBoundsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sort() {
        int swaps = 1;
        while (swaps > 0) {
            swaps = 0;
            for (int a = 1; a < numItems; a++) {
                int first = ((Integer) this.get(a));
                int second = ((Integer) this.get(a + 1));
                if (first > second) {
                    E temp;
                    temp = this.get(a);
                    this.find(a).setItem(this.get(a + 1));
                    this.find(a + 1).setItem(temp);
                    swaps++;
                }
            }
        }
    }

    @Override
    public void intersection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}//end class
