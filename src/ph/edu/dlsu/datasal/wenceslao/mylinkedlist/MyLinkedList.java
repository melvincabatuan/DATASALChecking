/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.wenceslao.mylinkedlist;

import java.util.*;
import ph.edu.dlsu.datasal.wenceslao.myinterface.List;
import ph.edu.dlsu.datasal.wenceslao.myexception.ListIndexOutOfBoundsException;

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

    public boolean contains(Object o) {
        int x = 0;
        Node<E> current = head;
        for (int i = 0; i < numItems; i++) {
            if (current.getItem() == o) {
                x = 1;
                current = current.getNext();
            } else {
                current = current.getNext();
            }
        }
        if (x != 0) {
            return true;
        } else {
            return false;
        }
    }

    public void clear() {
        while (numItems != 0) {
            this.remove(1);
        }
    }

    public void set(int x, E item) throws ListIndexOutOfBoundsException {
        if (x > 0 && x <= numItems + 1) {
            Node<E> current = find(x);
            current.setItem(item);
        } else {
            throw new ListIndexOutOfBoundsException("ERROR: List Index Out Of Bounds");
        }
    }

    public boolean containsAll(List<E> record) {
        int x = 1;
        for (int i = 0; i < record.size(); i++) {
            if (!contains(record.get(i + 1))) {
                x = 0;
            }
        }
        if (x == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void addAll(List<E> record) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void removeAll(List<E> record) {
        for (int i = 1; i <= record.size(); i++) {
            if (contains(record.get(i))) {
                remove(i);
            }
        }
    }

    @Override
    public void sort(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}//end class
