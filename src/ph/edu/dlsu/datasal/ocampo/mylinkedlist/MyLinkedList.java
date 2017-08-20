package ph.edu.dlsu.datasal.ocampo.mylinkedlist;

import ph.edu.dlsu.datasal.ocampo.myinterface.*;
import ph.edu.dlsu.datasal.ocampo.myexception.*;

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

    public boolean contains(E element) {
        Node<E> current = head;
        boolean y = false;
        for (int i = 1; i < numItems; i++) {
            if (current.getItem() == element) {
                y = true;
                break;
            }
        }
        return y;
    }

    public boolean equals(MyLinkedList<E> anotherList) {
        boolean y = true;
        Node<E> current = head;
        if (anotherList.size() == numItems) {
            for (int i = 1; i < numItems; i++) {
                if (current.getItem() != anotherList.get(i)) {
                    y = false;
                    break;
                } else {
                    current = current.getNext();
                }
            }
        } else {
            y = false;
        }
        return y;
    }

    public MyLinkedList<E> intersection(MyLinkedList<E> anotherList) {
        return null;
    }

    public void sort() {
        int low = 0;
        int high = size();
        int mid = (low + high) / 2;

    }

    private MyLinkedList<E> subList(int startIndex) {
        MyLinkedList<E> output = new MyLinkedList();
        output.createList();
        output.head = find(startIndex);
        return output;
    }

}//end class
