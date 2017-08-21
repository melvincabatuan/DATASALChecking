/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.albaladejo.myarraylist;

import ph.edu.dlsu.datasal.albaladejo.myexception.ListFullException;
import ph.edu.dlsu.datasal.albaladejo.myexception.ListIndexOutOfBoundsException;

/**
 *
 * @author student
 * @param <E>
 */
public abstract class MyArrayList<E> {

    private final int MAX_LIST = 5;     // max length of list
    private E[] items;                     // array of list items
    private int NumItems;                  // current size of list

    public void createList() {
        items = (E[]) new Object[MAX_LIST];
        NumItems = 0;
    }

    public int size() {
        return NumItems;
    }

    public boolean isEmpty() {
        return NumItems == 0;
    }

    public boolean contains(Object o) {
//        boolean isEqual = false;
//        if (o != null && o instanceof MyArrayList) {
//        }
        throw new UnsupportedOperationException();
    }

    public void add(int index, E item) throws ListIndexOutOfBoundsException, ListFullException {
        if (index > 0 && index <= NumItems + 1) {
            if (NumItems == MAX_LIST) {
                throw new ListFullException("ERROR: List Already Full");
            } else { // insert the element
                int j = NumItems;
                while (j >= index) {
                    items[j] = items[j - 1];
                    j--;
                }
                items[index - 1] = item;
                NumItems++;
            }
        } else {
            throw new ListIndexOutOfBoundsException("ERROR: List Index Out Of Bounds");
        }
    }

    public boolean remove(int index) {
//        if (index > 0 && index <= NumItems) {
//            for (int i = index; i < NumItems; i++) {
//                items[i - 1] = items[i];
//            }
//            NumItems--;
//        } else {
//            throw new ListIndexOutOfBoundsException("ERROR: List Index Out Of Bounds");
//        }
        throw new UnsupportedOperationException("Missing return statement");
    }

    public boolean containsAll(Object c) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(Object c) {
        throw new UnsupportedOperationException();
    }

    public boolean removeAll(Object c) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        throw new UnsupportedOperationException();
    }

    public void sort(Object o) {
        throw new UnsupportedOperationException();
    }

}
