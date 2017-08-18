/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.test;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import ph.edu.dlsu.datasal.chan.myarraylist.MyArrayList;
import ph.edu.dlsu.datasal.chan.myexception.ListIndexOutOfBoundsException;

/**
 *
 * @author cobalt
 */
public class MyArrayListTest {

    private MyArrayList<String> list;

    public MyArrayListTest() {
    }

    @Before
    public void setUp() {
        list = new MyArrayList<>();
        //list.createList();
    }

    @After
    public void tearDown() {
        list = null;
    }

    @Test
    public void createListTest() {
        assertTrue(list.isEmpty());
        assertTrue(list.size() == 0);
    }

    @Test(expected = ListIndexOutOfBoundsException.class)
    public void removeWithEmptyListTest() {
        list.remove(1);
    }

    @Test(expected = ListIndexOutOfBoundsException.class)
    public void invalidAddTest1() {
        list.add(0, "Zero");
    }

    @Test(expected = ListIndexOutOfBoundsException.class)
    public void invalidAddTest2() {
        list.add(2, "Two");
    }

    @Test
    public void addTest() {
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

        assertTrue(list.size() == 4);

        list.add("Kappa");
        assertEquals("Kappa", list.get(5));
        assertTrue(list.size() == 5);
    }

    @Test
    public void addAllTest() {
        list.clear();
        assertTrue(list.size() == 0);
        MyArrayList<String> sample = new MyArrayList<>();
        sample.add("Alpha");
        sample.add("Beta");
        list.addAll(sample);
        assertTrue(list.size() == 2);
        assertTrue(list.contains("Alpha"));      
        assertTrue(list.contains("Beta"));
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
        list.add(1, "Alpha");
        list.add(2, "Beta");
        list.add(3, "Gamma");

        list.clear();
        assertTrue(list.size() == 0);
    }

    @Test
    public void containsTest() {
        list.clear();
        list.add(1, "Alpha");
        list.add(2, "Beta");
        list.add(3, "Gamma");
        assertTrue(list.contains("Alpha"));
        assertFalse(list.contains("Omega"));
    }

    @Test
    public void containsAllTest() {
        list.clear();
        list.add(1, "Alpha");
        list.add(2, "Beta");
        list.add(3, "Gamma");
        MyArrayList<String> sample = new MyArrayList<>();
        sample.add("Alpha");
        sample.add("Beta");
        assertTrue(list.containsAll(sample));
        sample.add("Omega");
        assertFalse(list.containsAll(sample));
    }
}
