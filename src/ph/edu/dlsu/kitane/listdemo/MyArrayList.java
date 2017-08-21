/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.kitane.listdemo;

/*
 * File: MyList.java
 * ------------------
 * This is the List ADT implementation
 */
import ph.edu.dlsu.kitane.myexception.ListIndexOutOfBoundsException;
import ph.edu.dlsu.kitane.myexception.ListFullException;
import ph.edu.dlsu.kitane.myinterface.List;
import acm.program.*;
import acm.util.*;
import java.util.Arrays;
import java.util.Collection;

public class MyArrayList<E> {

    /// private data fields
    private final int MAX_LIST = 10;     // max length of list
    private E[] items;                     // array of list items
    private int NumItems;                  // current size of list
    
    public MyArrayList(){
        createList();
    }

    /// list items are already allocated above with T items[MAX_LIST]
    @SuppressWarnings("unchecked")
    public void createList() {
        items = (E[]) new Object[MAX_LIST];
        NumItems = 0;
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

    /**
     * Ensures that the ArrayList contains the specified element (optional
     * operation). Returns <tt>true</tt> if this collection changed as a result
     * of the call. (Returns <tt>false</tt> if this collection does not permit
     * duplicates and already contains the specified element.)
     * <p>
     *
     * Collections that support this operation may place limitations on what
     * elements may be added to this collection. In particular, some collections
     * will refuse to add <tt>null</tt> elements, and others will impose
     * restrictions on the type of elements that may be added. Collection
     * classes should clearly specify in their documentation any restrictions on
     * what elements may be added.<p>
     *
     * If a collection refuses to add a particular element for any reason other
     * than that it already contains the element, it <i>must</i> throw an
     * exception (rather than returning <tt>false</tt>). This preserves the
     * invariant that a collection always contains the specified element after
     * this call returns.
     *
     * @param int element whose presence in this collection is to be ensured
     * @return <tt>true</tt> if this collection changed as a result of the call
     * @throws UnsupportedOperationException if the <tt>add</tt> operation is
     * not supported by this collection
     * @throws ClassCastException if the class of the specified element prevents
     * it from being added to this collection
     * @throws NullPointerException if the specified element is null and this
     * collection does not permit null elements
     * @throws IllegalArgumentException if some property of the element prevents
     * it from being added to this collection
     * @throws IllegalStateException if the element cannot be added at this time
     * due to insertion restrictions
     */
    public void add(E item) {
        if (NumItems != MAX_LIST) {
            items[NumItems++] = item;
        } else if (NumItems == MAX_LIST) {
            throw new ListFullException("List is full");
        }
    }

    /**
     * Returns <tt>true</tt> if this collection contains the specified element.
     * More formally, returns <tt>true</tt> if and only if this collection
     * contains at least one element <tt>e</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
     *
     * @param int element whose presence in this collection is to be tested
     * @return <tt>true</tt> if this collection contains the specified element
     * @throws ClassCastException if the type of the specified element is
     * incompatible with this collection (optional)
     * @throws NullPointerException if the specified element is null and this
     * collection does not permit null elements (optional)
     */
    public boolean contains(E item) {
        for (int i = 0; i < NumItems; i++) {
            if (items[i] == item) {
                return true;
            }
        }
        return false;
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

    /**
     * Removes a single instance of the specified element from this collection,
     * if it is present (optional operation). More formally, removes an element
     * <tt>e</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>, if this
     * collection contains one or more such elements. Returns
     * <tt>true</tt> if this collection contained the specified element (or
     * equivalently, if this collection changed as a result of the call).
     *
     * @param item element to be removed from this collection, if present
     * @return <tt>true</tt> if an element was removed as a result of this call
     * @throws ClassCastException if the type of the specified element is
     * incompatible with this collection (optional)
     * @throws NullPointerException if the specified element is null and this
     * collection does not permit null elements (optional)
     * @throws UnsupportedOperationException if the <tt>remove</tt> operation is
     * not supported by this collection
     */
    public void remove(E item) {
        int instance = 0;
        if (contains(item)) {
            for (int i = 0; i < NumItems; i++) {
                if (items[i] == item) {
                    instance = i;
                    for (int j = instance; j < NumItems; j++) {
                        items[j] = items[j + 1];
                    }
                    break;
                }
            }
            NumItems--;
        } else {
            throw new UnsupportedOperationException(item + " does not exist in the list");
        }
    }

    /**
     * Returns <tt>true</tt> if this collection contains all of the elements in
     * the specified collection. Elements are separated by a comma and no
     * spaces.
     *
     * @param c collection to be checked for containment in this collection.
     * @return <tt>true</tt> if this collection contains all of the elements in
     * the specified collection
     * @throws ClassCastException if the types of one or more elements in the
     * specified collection are incompatible with this collection (optional)
     * @throws NullPointerException if the specified collection contains one or
     * more null elements and this collection does not permit null elements
     * (optional), or if the specified collection is null
     * @see #contains(Object)
     */
    public boolean containsAll(MyArrayList<E> c) {
        for (int i = 1; i <= c.size(); i++) {
            if (!contains(c.get(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds all of the elements in the specified String to this collection
     * (optional operation). The behavior of this operation is undefined if the
     * specified collection is modified while the operation is in progress.
     * (This implies that the behavior of this call is undefined if the
     * specified collection is this collection, and this collection is
     * nonempty.). <b>Elements to be added are separated by commas with no
     * spaces.</b>
     *
     * @param c collection containing elements to be added to this collection
     * @return <tt>true</tt> if this collection changed as a result of the call
     * @throws UnsupportedOperationException if the <tt>addAll</tt> operation is
     * not supported by this collection
     * @throws ClassCastException if the class of an element of the specified
     * collection prevents it from being added to this collection
     * @throws NullPointerException if the specified collection contains a null
     * element and this collection does not permit null elements, or if the
     * specified collection is null
     * @throws IllegalArgumentException if some property of an element of the
     * specified collection prevents it from being added to this collection
     * @throws IllegalStateException if not all the elements can be added at
     * this time due to insertion restrictions
     * @see #add(Object)
     */
    public void addAll(MyArrayList<E> c) {
        for (int i = 1; i <= c.size(); i++) {
            if (NumItems == MAX_LIST) {
                throw new ListFullException("List is Full!");
            }
            items[NumItems++] = c.get(i);
        }
    }

    /**
     * Removes all of this collection's elements that are also contained in the
     * specified String (optional operation). After this call returns, this
     * collection will contain no elements in common with the specified
     * collection. <b>Elements to be added are separated by commas with no
     * spaces.</b>
     *
     * @param c collection containing elements to be removed from this
     * collection
     * @return <tt>true</tt> if this collection changed as a result of the call
     * @throws UnsupportedOperationException if the <tt>removeAll</tt> method is
     * not supported by this collection
     * @throws ClassCastException if the types of one or more elements in this
     * collection are incompatible with the specified collection (optional)
     * @throws NullPointerException if this collection contains one or more null
     * elements and the specified collection does not support null elements
     * (optional), or if the specified collection is null
     * @see #remove(Object)
     * @see #contains(Object)
     */
    public void removeAll(MyArrayList<E> c) {
        if (containsAll(c)) {
            for (int i = 1; i <= c.size(); i++) {
                remove(c.get(i));
            }
        } else {
            throw new RuntimeException("some or all elements dont exist in List.");
        }
    }

    /**
     * Removes all of the elements from this collection (optional operation).
     * The collection will be empty after this method returns.
     *
     * @throws UnsupportedOperationException if the <tt>clear</tt> operation is
     * not supported by this collection
     */
    public void clear() {
        items = Arrays.copyOf((E[]) new Object[items.length], items.length);
        NumItems = 0;
    }

    /**
     * Compares the specified String with this collection for equality.
     * <p>
     *
     * While the <tt>Collection</tt> interface adds no stipulations to the
     * general contract for the <tt>Object.equals</tt>, programmers who
     * implement the <tt>Collection</tt> interface "directly" (in other words,
     * create a class that is a <tt>Collection</tt> but is not a <tt>Set</tt>
     * or a <tt>List</tt>) must exercise care if they choose to override the
     * <tt>Object.equals</tt>. It is not necessary to do so, and the simplest
     * course of action is to rely on <tt>Object</tt>'s implementation, but the
     * implementor may wish to implement a "value comparison" in place of the
     * default "reference comparison." (The <tt>List</tt> and
     * <tt>Set</tt> interfaces mandate such value comparisons.)
     * <p>
     *
     * The general contract for the <tt>Object.equals</tt> method states that
     * equals must be symmetric (in other words, <tt>a.equals(b)</tt> if and
     * only if <tt>b.equals(a)</tt>). The contracts for <tt>List.equals</tt>
     * and <tt>Set.equals</tt> state that lists are only equal to other lists,
     * and sets to other sets. Thus, a custom <tt>equals</tt> method for a
     * collection class that implements neither the <tt>List</tt> nor
     * <tt>Set</tt> interface must return <tt>false</tt> when this collection is
     * compared to any list or set. (By the same logic, it is not possible to
     * write a class that correctly implements both the <tt>Set</tt> and
     * <tt>List</tt> interfaces.)
     *
     * @param o object to be compared for equality with this collection
     * @return <tt>true</tt> if the specified object is equal to this collection
     *
     * @see Object#equals(Object)
     * @see Set#equals(Object)
     * @see List#equals(Object)
     */
    public boolean equals(MyArrayList<E> object) {
        if (this.size() == object.size() && containsAll(object)) {
            return true;
        }

        return false;
    }

    /**
     * Returns <tt>true</tt> if this collection contains no elements.
     *
     * @return <tt>true</tt> if this collection contains no elements
     */
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

    /**
     * Returns the number of elements in this collection. If this collection
     * contains more than <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * @return the number of elements in this collection
     */
    public int size() {
        if (NumItems > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return NumItems;
    }

    /*public void bubbleSort(){
        for(int i=1;i<NumItems-1;i++){
            for(int j=1;j<NumItems-i;j++){
                if(items[j]>items[j+1]){
                    int temp = items[j+1];
                    items[j+1] = items[j];
                    items[j] = temp;
                }
            }
        }
        System.out.print(items);
    }*/
    public String toString() {
        String result = "[";
        for (int i = 1; i <= this.size() - 1; i++) {
            result += this.get(i) + ",";
        }
        result += this.get(this.size()) + "]";
        return result;
    }

    public MyArrayList<E> intersection(MyArrayList<E> list) {
        MyArrayList<E> result = new MyArrayList<>();
        MyArrayList<E> smaller = new MyArrayList<>();
        MyArrayList<E> larger = new MyArrayList<>();
        result.createList();
        smaller.createList();
        larger.createList();
        int size = 0;
        if (list.size() <= this.size()) {
            size = list.size();
            smaller = list;
            larger = this;
        } else if (this.size() < list.size()) {
            size = this.size();
            smaller = this;
            larger = list;
        }
        for (int i = 1; i <= size; i++) {
            if (larger.contains(smaller.get(i))) {
                result.add(smaller.get(i));
            }
        }
        return result;
    }
    
    public static void main(String[] args){
        MyArrayList<String> test = new MyArrayList<>();
        test.createList();
        test.add("Alpha");
        test.add("Beta");
        test.add("Gamma");
        MyArrayList<String> subtest = new MyArrayList<>();
        subtest.createList();
        subtest.add("Alpha");
        subtest.add("Beta");
        System.out.println(test);
        System.out.println(test.intersection(test));
        System.out.println(subtest);
        System.out.println(test.intersection(subtest));
        System.out.println(subtest.intersection(test));
    }
}
