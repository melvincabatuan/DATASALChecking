/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.kitane.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ph.edu.dlsu.kitane.myexception.ListIndexOutOfBoundsException;
import ph.edu.dlsu.kitane.linkedlist.MyLinkedList;

/**
 *
 * @author cobalt
 */
public class MyLinkedListTest {

    private MyLinkedList list;

    @Before
    public void setUp() {
        list = new MyLinkedList<>();
    }

    @Test
    public void isEmptyTest() {
        assertTrue(list.isEmpty());
    }

    @Test
    public void initialSizeTest() {
        assertTrue(list.size() == 0);
    }

    @Test(expected = ListIndexOutOfBoundsException.class)
    public void removeWithEmptyListTest() {
        list.remove(1);
    }

    @Test(expected = ListIndexOutOfBoundsException.class)
    public void addGetTest() {
        /* Invalid since our list starts at 1: */
        list.add(0, "Zero");
        /* Invalid since our list has no elements yet */
        list.add(2, "Two");

        /* Adding three elements */
        list.add(1, "Alpha");
        list.add(2, "Beta");
        list.add(3, "Gamma");

        assertEquals("Alpha", list.get(1));
        assertEquals("Beta", list.get(2));
        assertEquals("Gamma", list.get(3));

        list.add(1, "Omega");

        assertEquals("Omega", list.get(1));
        assertEquals("Alpha", list.get(2));
        assertEquals("Beta", list.get(3));
        assertEquals("Gamma", list.get(4));

        /* Test size increment from addition */
        assertTrue(list.size() == 4);

        list.add(5, "Kappa");
        assertEquals("Kappa", list.get(5));

        /* Test size increment from addition */
        assertTrue(list.size() == 5);
    }

    @Test
    public void containsTest() {
        fail("This method is not yet implemented");
//        list.add(1, "Alpha");
//        list.add(2, "Beta");
//        list.add(3, "Gamma");
//        assertTrue(list.contains("Alpha"));
//        assertTrue(list.contains("Beta"));
//        assertTrue(list.contains("Gamma"));
//        assertFalse(list.contains("Omega"));
    }

    @Test
    public void removeElementTest() {
        list.add(1, "Alpha");
        list.add(2, "Beta");
        list.add(3, "Gamma");
        list.remove(2);
        assertEquals("Gamma", list.get(2));
        assertTrue(list.size() == 2);
    }

    @Test
    public void clearTest() {
        fail("This method is not yet implemented");
//        list.add(1, "Alpha");
//        list.add(2, "Beta");
//        list.add(3, "Gamma");
//        list.clear();
//        assertTrue(list.size() == 0);
    }

    @Test
    public void containsAllTest() {
        fail("This method is not yet implemented");
//        list.add(1, "Alpha");
//        list.add(2, "Beta");
//        list.add(3, "Gamma");
//        MyLinkedList sample = new MyLinkedList<>();
//        sample.add(1, "Alpha");
//        sample.add(2, "Beta");
//        assertTrue(list.containsAll(sample));
//        assertTrue(sample.containsAll(sample));
//        assertFalse(sample.containsAll(list));
//        sample.add("Omega");
//        assertFalse(list.containsAll(sample));
    }

    @Test
    public void addAllTest() {
        fail("This method is not yet implemented");
//        MyLinkedList sample = new MyLinkedList<>();
//        sample.add(1, "Alpha");
//        sample.add(2, "Beta");
//        list.addAll(sample);
//        assertTrue(list.size() == 2);
//        assertTrue(list.contains("Alpha"));
//        assertTrue(list.contains("Beta"));
    }

    @Test
    public void removeAllTest() {
        fail("This method is not yet implemented");
//        list.add(1, "Alpha");
//        list.add(2, "Beta");
//        list.add(3, "Gamma");
//        MyLinkedList sample = new MyLinkedList<>();
//        sample.add("Alpha");
//        sample.add("Beta");
//        list.removeAll(sample);
//        assertTrue(list.size() == 1);
//        assertFalse(list.contains("Alpha"));
//        assertFalse(list.contains("Beta"));
//        assertTrue(list.contains("Gamma"));
    }

    @Test
    public void equalsTest() {
        list.add(1, "Alpha");
        list.add(2, "Beta");
        MyLinkedList sample = new MyLinkedList<>();
        sample.add(1, "Alpha");
        sample.add(2, "Beta");
        assertTrue(list.equals(sample));
        assertTrue(sample.equals(list));
        sample.add(3, "Gamma");
        assertFalse(list.equals(sample));
        assertFalse(sample.equals(list));
    }

    @Test
    public void intersectionTest() {
        fail("This method is not yet implemented");
//        list.add("Alpha");
//        list.add("Beta");
//        MyLinkedList sample = new MyLinkedList<>();
//        sample.add("Alpha");
//        sample.add("Beta");
//        sample.add("Gamma");
//        assertEquals(list, list.intersection(sample));
//        assertEquals(list, sample.intersection(list));
//        assertEquals(list, list.intersection(list));
    }

    @Test
    public void sortTest() {
        fail("This method is not yet implemented");
//        MyLinkedList<Integer> intList = new MyLinkedList<>();
//        intList.add(1);
//        intList.add(5);
//        intList.add(2);
//        intList.add(4);
//        intList.add(3);
//        intList.sort();
//        assertTrue(intList.get(1) == 1);
//        assertTrue(intList.get(2) == 2);
//        assertTrue(intList.get(3) == 3);
//        assertTrue(intList.get(4) == 4);
//        assertTrue(intList.get(5) == 5);
    }

}
