package ph.edu.dlsu.velasco.mylinkedlist;

import ph.edu.dlsu.velasco.myinterface.*;
import ph.edu.dlsu.velasco.myexception.*;

public class MyLinkedList<E> implements List<E> {

    private Node<E> head;
    private int numItems;
    
    public MyLinkedList(){
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
    
    public void add(E item){
        Node<E> newNode = new Node<E>(item);
        Node<E> previous = find(numItems);
        previous.setNext(newNode);
        newNode.setNext(null);
        numItems++;
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

    public boolean contains(E item) {
        for (int i = 0; i < numItems; i++) {
            if (find(i).getItem() == item) {
                return true;
            }
        }
        return false;
    }

    public boolean containsAll(MyLinkedList list) {
        int count = 0;
        for (int i = 1; i <= numItems; i++) {
            for (int j = 1; j <= list.size(); j++) {
                if (find(i).getItem() == list.get(j)) {
                    count++;
                }
            }
        }
        return count == list.size();
    }

    public void addAll(MyLinkedList list) {
        if (list.isEmpty()) {
            return;
        } else {
            int index = numItems;
            for (int i = 0; i < list.size(); i++) {
                add(index + i, (E) list.get(i+1));
            }
        }
    }

    public void clear() {
        numItems = 0;
    }

    public void removeAll(MyLinkedList list) {
        if (list.size() < numItems) {
            for (int i = 1; i <= list.size(); i++) {
                for (int j = 1; j <= numItems; j++) {
                    if (list.get(i) == find(j).getItem()) {
                        remove(j);
                    }
                }
            }
        } else {
            for (int i = 1; i <= numItems; i++) {
                for (int j = 1; j <= list.size(); j++) {
                    if (find(i).getItem() == list.get(j)) {
                        remove(i);
                    }
                }
            }
        }
    }

}//end class
