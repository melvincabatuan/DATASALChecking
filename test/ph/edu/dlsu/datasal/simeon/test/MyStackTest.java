/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.simeon.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ph.edu.dlsu.datasal.SIMEON.mystack.Stack;

/**
 *
 * @author cobalt
 */
public class MyStackTest {
    
    private Stack myStack;

    public MyStackTest() {
    }

    @Before
    public void setUp() {
        myStack = new Stack();
    }

    @After
    public void tearDown() {
        myStack = null;
    }

    @Test
    public void initializationTest() {        
        assertTrue("Stack should be empty!", myStack.isEmpty());
        myStack.push("Alpha");
        assertTrue("Stack should not be empty!", !myStack.isEmpty());
    }

    @Test
    public void pushTest() {
        assertTrue(myStack.size() == 0);
        myStack.push("Alpha");
        assertTrue(myStack.size() == 1);
        myStack.push("Beta");
        assertTrue(myStack.size() == 2);
    }

    @Test
    public void topTest() {
        myStack.push("Alpha");
        assertEquals("Alpha", myStack.top());
        myStack.push("Beta");
        assertEquals("Beta", myStack.top());
    }

    @Test //(expected = StackEmptyException.class)
    public void popTest() {
        myStack.push("Alpha");
        myStack.push("Beta");
        myStack.push("Gamma");
        myStack.pop();
        assertEquals("Beta", myStack.top());
        myStack.pop();
        assertEquals("Alpha", myStack.top());
        myStack.pop();
        assertTrue(myStack.size() == 0);
    }

    @Test
    public void containsTest() {
        myStack.push("Alpha");
        myStack.push("Beta");
        myStack.push("Gamma");
        assertTrue(myStack.contains("Alpha"));
        assertTrue(myStack.contains("Beta"));
        assertTrue(myStack.contains("Gamma"));
        assertFalse(myStack.contains("Omega"));
    }

    @Test
    public void containsAllTest() {
        myStack.push("Alpha");
        myStack.push("Beta");
        myStack.push("Gamma");
        Stack sample = new Stack();
        sample.push("Alpha");
        sample.push("Beta");
        assertTrue(sample.containsAll(sample));
        assertTrue(myStack.containsAll(sample));
        assertFalse(sample.containsAll(myStack));
        sample.push("Omega");
        assertFalse(myStack.containsAll(sample));
    }

    @Test
    public void addAllTest() {
        Stack sample = new Stack();
        sample.push("Alpha");
        sample.push("Beta");
        myStack.addAll(sample);
        assertTrue(myStack.size() == 2);
        assertTrue(myStack.contains("Alpha"));
        assertTrue(myStack.contains("Beta"));
    }

    @Test
    public void removeAllTest() {
        myStack.push("Alpha");
        myStack.push("Beta");
        myStack.push("Gamma");
        Stack sample = new Stack();
        sample.push("Alpha");
        sample.push("Beta");
        myStack.removeAll(sample);
        assertTrue(myStack.size() == 1);
        assertFalse(myStack.contains("Alpha"));
        assertFalse(myStack.contains("Beta"));
        assertTrue(myStack.contains("Gamma"));
    }

    @Test
    public void equalsTest() {
        myStack.push("Alpha");
        myStack.push("Beta");
        Stack sample = new Stack();
        sample.push("Alpha");
        sample.push("Beta");
        assertTrue(myStack.equals(sample));
        assertTrue(sample.equals(myStack));
        sample.push("Gamma");
        assertFalse(myStack.equals(sample));
        assertFalse(sample.equals(myStack));
    }

    @Test
    public void intersectionTest() {
        myStack.push("Alpha");
        myStack.push("Beta");
        Stack sample = new Stack();
        sample.push("Alpha");
        sample.push("Beta");
        sample.push("Gamma");
        assertTrue(myStack.equals(myStack.intersection(sample)));
        assertTrue(myStack.equals(sample.intersection(myStack)));
        assertTrue(myStack.equals(myStack.intersection(myStack)));
    }

    @Test
    public void sortTest() {
        myStack.push(1);
        myStack.push(5);
        myStack.push(2);
        myStack.push(4);
        myStack.push(3);
        myStack.sort(myStack);
        
        // if highest is TOS
        assertEquals(myStack.top(), 5);
        myStack.pop();
        assertEquals(myStack.top(), 4);
        myStack.pop();
        assertEquals(myStack.top(), 3);
        myStack.pop();
        assertEquals(myStack.top(), 2);
        myStack.pop();
        assertEquals(myStack.top(), 1);
    }
}
