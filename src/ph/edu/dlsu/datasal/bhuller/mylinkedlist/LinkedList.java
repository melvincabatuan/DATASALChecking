/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.bhuller.mylinkedlist;

/**
 *
 * @author Bhuller
 */
public class LinkedList<E> implements List<E> {

    private Node<E> start;
    private int count;
    private boolean val;

    public LinkedList() {
        createList();
    }

    public void createList() {
        count = 0;
        start = null;
    } //end constructor

    public boolean contains(E item) {
        val = false;
        for (int i = 0; i < count; i++) {
            if (get(i) == item) {
                val = true;
            }
        }
        return val;
    }

    public void removeAll(LinkedList<E> cc) {
        if (cc.size() <= size()) {
            for (int i = 1; i < cc.size(); i++) {
                if (get(i) == cc.get(i)) {
                    remove(i);
                }
            }
        }
    }

    public void addAll(LinkedList<E> cc) {
        if (cc.size() <= size()) {
            for (int i = 1; i < cc.size(); i++) {
                add(i, cc.get(i));
            }
        }
    }

    public boolean containsAll(LinkedList<E> cc) {
        int a = 0;
        if (cc.size() > count) {
            return false;
        }
        for (int i = 1; i <= cc.size(); i++) {
            if (contains(cc.get(i))) {
                a++;
            }
        }
        if (a >= cc.size()) {
            return true;
        } else {
            return false;
        }
    }

    public LinkedList<E> intersection(LinkedList<E> cc) {
        LinkedList<E> result = new LinkedList<E>();
        result.createList();
        int c = 1;
        for (int j = 0; j < cc.size(); j++) {
            if (contains(cc.get(j + 1))) {
                result.add(c, cc.get(j));
                c++;
            }
        }
        return result;
    }

    public boolean equals(LinkedList<E> cc) {
        int a = 0;
        if (count != cc.size()) {
            return false;
        }
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < cc.size(); j++) {
                if (get(i + 1) == cc.get(j + 1)) {
                    a++;
                }
            }
        }
        if (a == count) {
            return true;
        } else {
            return false;
        }
    }

    public void clear() {
        for (int i = 1; i <= count;) {
            remove(i);
        }
    }

    public void add(int index, E item) throws ListIndexOutOfBoundsException {
        if (index > 0 && index <= count + 1) {
            if (index == 1) {
                Node<E> newN = new Node<E>(item);
                newN.setNext(start);
                start = newN;
            } else {
                Node<E> newN = new Node<E>(item);
                Node<E> previous = find(index - 1);
                newN.setNext(previous.getNext());
                previous.setNext(newN);
            }
            count++;
        } else {
            throw new ListIndexOutOfBoundsException("ADD ERROR: List Index Out Of Bounds");
        }
    }

    public void remove(int ind) throws ListIndexOutOfBoundsException {
        if (ind > 0 && ind <= count + 1) {
            if (ind == 1) {
                start = start.getNext();
            } else {
                Node<E> previous = find(ind - 1);
                Node<E> current = previous.getNext();
                previous.setNext(current.getNext());
            }
            count--;
        } else {
            throw new ListIndexOutOfBoundsException("REMOVE ERROR: List Index Out Of Bounds");
        }
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public E get(int ind) throws ListIndexOutOfBoundsException {
        if (ind > 0 && ind <= count + 1) {
            Node<E> current = find(ind);
            E item = current.getItem();
            return item;
        } else {
            throw new ListIndexOutOfBoundsException("GET ERROR: List Index Out Of Bounds");
        }
    }

    public int size() {
        return count;
    }

    private Node<E> find(int ind) {

        Node<E> current = start;

        for (int i = 1; i < ind; i++) {
            current = current.getNext();
        }

        return current;
    }
}
