/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.santos.myarraylist;

import ph.edu.dlsu.datasal.santos.myexception.*;
import ph.edu.dlsu.datasal.santos.myinterface.*;

public class MyArrayList<E> implements List<E> {

    /// private data fields
    private final int MAX_LIST = 100;     // max length of list
    private E[] items;                     // array of list items
    private int NumItems;                  // current size of list

    public MyArrayList() {
        createList();
    }

    /// list items are already allocated above with T items[MAX_LIST]
    @SuppressWarnings("unchecked")
    public void createList() {
        items = (E[]) new Object[MAX_LIST];
        NumItems = 0;
    }

    public void add(int index, E item) throws ListIndexOutOfBoundsException, ListFullException {
        if (items == null) {
            createList();
        }
        if (index > 0 && index <= NumItems + 1) {
            if (NumItems == MAX_LIST) {
                throw new ListFullException("ERROR: List Already Full");
            } else {
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

    public void remove(int index) throws ListIndexOutOfBoundsException {
        if (index > 0 && index <= NumItems) {
            for (int i = index; i < NumItems; i++) {
                items[i - 1] = items[i];
            }
            NumItems--;
        } else {
            throw new ListIndexOutOfBoundsException("ERROR: List Index Out Of Bounds");
        }
    }

    public boolean isEmpty() {
        return NumItems == 0;
    }

    public E get(int index) throws ListIndexOutOfBoundsException {
        if (index > 0 && index <= NumItems) {
            return items[index - 1];
        } else {
            throw new ListIndexOutOfBoundsException("ERROR: List Index Out Of Bounds");
        }
    }

    public int size() {
        return NumItems;
    }

    public boolean contains(Object o) {
        int x = 0;
        for (int i = 0; i < NumItems; i++) {
            if (o == items[i]) {
                x = i;
            }
        }
        return o == items[x];
    }

    public boolean containsAll(List<E> c) {
        int containsall = 1;
        for (int i = 0; i < c.size(); i++) {
            if (!contains(c.get(i + 1))) {
                containsall = 0;
            }
        }
        if (containsall == 1) {
            return true;
        } else {
            return false;
        }
    }

    public void addAll(List<E> c) {
        for (int i = 0; i < c.size(); i++) {
            add(c.get(i + 1));
        }
    }

    public void removeAll(List<E> c) {
        for (int i = 1; i <= c.size(); i++) {
            if (contains(c.get(i))) {
                remove(i);
            }
        }
    }

    public void clear() {
        for (int i = 0; i < NumItems; i++) {
            items[i] = null;
        }
        NumItems = 0;
    }

    public boolean equals(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void sort() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void set(int index, E item) throws ListIndexOutOfBoundsException {
        if (NumItems != MAX_LIST) {
            items[index - 1] = item;
        }
    }

    public void add(E e) {
        if (items == null) {
            createList();
        }
        if (NumItems != MAX_LIST && NumItems != 0) {
            items[NumItems] = e;
            NumItems++;
        }
    }

    public boolean intersection(List<E> list) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
