package ph.edu.dlsu.datasal.go.mylinkedlist;

import java.util.Collection;

public class MyLinkedList<E> {

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean add(E e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void sort(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public E contains(int o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}//end class
