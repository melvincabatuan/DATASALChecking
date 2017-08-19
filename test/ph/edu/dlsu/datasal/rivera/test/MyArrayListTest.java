/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.rivera.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ph.edu.dlsu.rivera.myarraylist.MyList;
import ph.edu.dlsu.rivera.myexception.ListIndexOutOfBoundsException;

/**
 *
 * @author cobalt
 */
public class MyArrayListTest {

    private MyList list;

    public MyArrayListTest() {
    }

    @Before
    public void setUp() {
        list = new MyList<>();
    }

    @After
    public void tearDown() {
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

        list.add("Kappa");
        assertEquals("Kappa", list.get(5));

        /* Test size increment from addition */
        assertTrue(list.size() == 5);
    }

    @Test
    public void containsTest() {
        list.add(1, "Alpha");
        list.add(2, "Beta");
        list.add(3, "Gamma");
        assertTrue(list.contains("Alpha"));
        assertTrue(list.contains("Beta"));
        assertTrue(list.contains("Gamma"));
        assertFalse(list.contains("Omega"));
    }

    @Test
    public void removeElementTest() {
        list.add(1, "Alpha");
        list.add(2, "Beta");
        list.add(3, "Gamma");
        list.remove("Beta");
        assertEquals("Gamma", list.get(2));
        assertTrue(list.size() == 2);
    }

    @Test
    public void clearTest() {
        list.add(1, "Alpha");
        list.add(2, "Beta");
        list.add(3, "Gamma");
        list.clear();
        assertTrue(list.size() == 0);
    }

    @Test
    public void containsAllTest() {
        list.add(1, "Alpha");
        list.add(2, "Beta");
        list.add(3, "Gamma");
        MyList sample = new MyList<>();
        sample.add("Alpha");
        sample.add("Beta");
        assertTrue(list.containsAll(sample));
        assertTrue(sample.containsAll(sample));
        assertFalse(sample.containsAll(list));
        sample.add("Omega");
        assertFalse(list.containsAll(sample));
    }

    @Test
    public void addAllTest() {
        MyList sample = new MyList<>();
        sample.add("Alpha");
        sample.add("Beta");
        list.addAll(sample);
        assertTrue(list.size() == 2);
        assertTrue(list.contains("Alpha"));
        assertTrue(list.contains("Beta"));
    }

    @Test
    public void removeAllTest() {
        list.add(1, "Alpha");
        list.add(2, "Beta");
        list.add(3, "Gamma");
        MyList sample = new MyList<>();
        sample.add("Alpha");
        sample.add("Beta");
        list.removeAll(sample);
        assertTrue(list.size() == 1);
        assertFalse(list.contains("Alpha"));
        assertFalse(list.contains("Beta"));
        assertTrue(list.contains("Gamma"));
    }

    @Test
    public void equalsTest() {
        list.add("Alpha");
        list.add("Beta");
        MyList sample = new MyList<>();
        sample.add("Alpha");
        sample.add("Beta");
        assertTrue(list.isEqual(sample));
        assertTrue(sample.isEqual(list));
        sample.add("Gamma");
        assertFalse(list.isEqual(sample));
        assertFalse(sample.isEqual(list));
    }

    @Test
    public void intersectionTest() {
        list.add("Alpha");
        list.add("Beta");
        MyList sample = new MyList<>();
        sample.add("Alpha");
        sample.add("Beta");
        sample.add("Gamma");
        assertTrue(list.isEqual(list.intersection(sample)));
        assertTrue(list.isEqual(sample.intersection(list)));
        assertTrue(list.isEqual(list.intersection(list)));
    }

    @Test
    public void sortTest() {
        MyList<Integer> intList = new MyList<>(); 
        list.add(1);
        list.add(5);
        list.add(2);
        list.add(4);
        list.add(3);
        list.sort();
        assertEquals(list.get(1), 1);
        assertEquals(list.get(2), 2);
        assertEquals(list.get(3), 3);
        assertEquals(list.get(4), 4);
        assertEquals(list.get(5), 5);
    }

}
