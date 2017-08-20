/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.ang.test;

import java.util.ArrayList;
import java.util.Collection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ph.edu.dlsu.datasal.ang.mystack.MyStack;


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
        myStack.push("Alpha");
        assertEquals("Alpha", myStack.top());
        myStack.push("Beta");
        assertEquals("Beta", myStack.top());
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
        Collection<String> sample = new ArrayList<>();
        sample.add("Alpha");
        sample.add("Beta");
        assertTrue(sample.containsAll(sample));
        assertTrue(myStack.containsAll(sample));
        // assertFalse(sample.containsAll(myStack));
        sample.add("Omega");
        assertFalse(myStack.containsAll(sample));
    }

    @Test
    public void addAllTest() {
        fail("This method is not implemented yet");
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
        fail("This method is not implemented yet");
//        myStack.push("Alpha");
//        myStack.push("Beta");
//        myStack.push("Gamma");
//        Stack sample = new Stack();
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
        fail("This method is not implemented yet");
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
        fail("This method is not implemented yet");
//        myStack.push("Alpha");
//        myStack.push("Beta");
//        Stack sample = new Stack();
//        sample.push("Alpha");
//        sample.push("Beta");
//        sample.push("Gamma");
//        assertEquals(myStack, myStack.intersection(sample));
//        assertEquals(myStack, sample.intersection(myStack));
//        assertEquals(myStack, myStack.intersection(myStack));
    }

    @Test
    public void sortTest() {
        fail("This method is not implemented yet");
//        StackInt myStack = new StackInt();
//        myStack.push(1);
//        myStack.push(5);
//        myStack.push(2);
//        myStack.push(4);
//        myStack.push(3);
//        myStack.sort();
        
        // if highest is TOS
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
