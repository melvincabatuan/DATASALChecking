/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.lee.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ph.edu.dlsu.datasal.lee.mystack.MyStack;


/**
 *
 * @author cobalt
 */
public class MyStackTest {
    
    private MyStack myStack;

    public MyStackTest() {
    }

    @Before
    public void setUp() {
        myStack = new MyStack();
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
        fail("This method is not yet supported");
//        myStack.push("Alpha");
//        myStack.push("Beta");
//        myStack.push("Gamma");
//        assertTrue(myStack.contains("Alpha"));
//        assertTrue(myStack.contains("Beta"));
//        assertTrue(myStack.contains("Gamma"));
//        assertFalse(myStack.contains("Omega"));
    }

    @Test
    public void containsAllTest() {
        fail("This method is not yet supported");
//        myStack.push("Alpha");
//        myStack.push("Beta");
//        myStack.push("Gamma");
//        MyStack sample = new MyStack();
//        sample.push("Alpha");
//        sample.push("Beta");
//        assertTrue(sample.containsAll(sample));
//        assertTrue(myStack.containsAll(sample));
//        assertFalse(sample.containsAll(myStack));
//        sample.push("Omega");
//        assertFalse(myStack.containsAll(sample));
    }

    @Test
    public void addAllTest() {
        fail("This method is not yet supported");
//        Stack sample = new Stack();
//        sample.push("Alpha");
//        sample.push("Beta");
//        myStack.addAll(sample);
//        assertTrue(myStack.size() == 2);
//        assertTrue(myStack.contains("Alpha"));
//        assertTrue(myStack.contains("Beta"));
    }

    @Test
    public void removeAllTest() {
        fail("This method is not yet supported");
//        myStack.push("Alpha");
//        myStack.push("Beta");
//        myStack.push("Gamma");
//        MyStack sample = new MyStack();
//        sample.push("Alpha");
//        sample.push("Beta");
//        myStack.removeAll(sample);
//        assertTrue(myStack.size() == 1);
//        assertFalse(myStack.contains("Alpha"));
//        assertFalse(myStack.contains("Beta"));
//        assertTrue(myStack.contains("Gamma"));
    }

    @Test
    public void equalsTest() {
        fail("This method is not yet supported");
//        myStack.push("Alpha");
//        myStack.push("Beta");
//        Stack sample = new Stack();
//        sample.push("Alpha");
//        sample.push("Beta");
//        assertTrue(myStack.equals(sample));
//        assertTrue(sample.equals(myStack));
//        sample.push("Gamma");
//        assertFalse(myStack.equals(sample));
//        assertFalse(sample.equals(myStack));
    }

    @Test
    public void intersectionTest() {
        fail("This method is not yet supported");
//        myStack.push("Alpha");
//        myStack.push("Beta");
//        MyStack sample = new MyStack();
//        sample.push("Alpha");
//        sample.push("Beta");
//        sample.push("Gamma");
//        assertEquals(myStack, myStack.intersection(sample));
//        assertEquals(myStack, sample.intersection(myStack));
//        assertEquals(myStack, myStack.intersection(myStack));
    }

    @Test
    public void sortTest() {
        fail("This method is not yet supported");
//        StackInt myStack = new StackInt();
//        myStack.push(1);
//        myStack.push(5);
//        myStack.push(2);
//        myStack.push(4);
//        myStack.push(3);
//        myStack.sort();
//        
//        // if highest is TOS
//        assertTrue(myStack.top() == 5);
//        myStack.pop();
//        assertTrue(myStack.top() == 4);
//        myStack.pop();
//        assertTrue(myStack.top() == 3);
//        myStack.pop();
//        assertTrue(myStack.top() == 2);
//        myStack.pop();
//        assertTrue(myStack.top() == 1);
    }
}
