package datasal.Narag.myLinkedList;

import datasal.Narag.myInterface.LinkedList;
import datasal.Narag.myInterface.List;
import datasal.Narag.myExceptions.ListIndexOutOfBoundsException;
import datasal.Narag.myLinkedList.Node;

public class MyLinkedList<E> implements LinkedList<E> {

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
        if (index > 0 && index < numItems + 1) {
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

    /// Locate a specified node in linked list:
    private Node<E> find(int index) {

        Node<E> current = head;

        for (int i = 1; i < index; i++) {
            current = current.getNext();
        }

        return current;
    } //end find

    public boolean contains(Object o) {
        int flag = 0;
        Node<E> current = head;

        for (int i = 0; i < numItems; i++) {
            if (current.getItem() == o) {
                flag = 1;
                current = current.getNext();
            } else {
                current = current.getNext();
            }
        }

        if (flag != 0) {
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

    public boolean equalslinked(MyLinkedList list) {
        int flag = 1;
        if (numItems != list.size()) {
            return false;
        } else {
            Node<E> current = head;
            for (int index = 0; index < numItems; index++) {
                if ((current.getItem()) != (list.get(index + 1))) {
                    flag = 0;
                    current = current.getNext();
                } else {
                    current = current.getNext();
                }
            }
        }
        if (flag == 1) {
            return true;
        } else {
            return false;
        }

    }

    public boolean containsAll(MyLinkedList list) {
        int flag = 1;
        Node<E> current1 = this.head;
        Node<E> current2 = list.head;

        for (int currentList = 0; currentList < numItems; currentList++) {
            for (int otherList = 0; otherList < list.size(); otherList++) {
                if (current1.getItem() == current2.getItem()) {
                    flag = 1;
                    break;
                }
                flag = 0;
            }

        }
        if (flag != 0) {
            return true;
        } else {
            return false;
        }
    }

    public void addAll(MyLinkedList<E> list) {

        Node<E> current = list.head;

        for (int numList = 0; numList < list.size(); numList++) {
            this.add(numItems + 1, (E) current.getItem());
            current = current.getNext();
        }

    }

    public void removeAll(MyLinkedList<E> list) {
        Node<E> current = list.head;
        Node<E> current2 = this.head;
        int size = numItems;

        for (int numList = 0; numList < list.size(); numList++) {
            current = list.head;
            for (int origcurrent = 0; origcurrent < size; origcurrent++) {
                if (current2.getItem() == current.getItem()) {
                    current2.setItem(null);
                    size--;
                    current = current.getNext();
                }
                current2 = current2.getNext();
            }
        }

    }

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

}//end class
