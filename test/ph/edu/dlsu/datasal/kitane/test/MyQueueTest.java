/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.kitane.test;

import java.util.ArrayList;
import java.util.Collection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ph.edu.dlsu.kitane.myexception.QueueEmptyException;
import ph.edu.dlsu.kitane.mygraph.myQueueList;


/**
 *
 * @author cobalt
 */
public class MyQueueTest {

    private myQueueList myQueue;

    public MyQueueTest() {
    }

    @Before
    public void setUp() {
        myQueue = new myQueueList();
    }

    @After
    public void tearDown() {
        myQueue = null;
    }

    @Test
    public void initializationTest() {
        assertTrue("Queue should be empty!", myQueue.isEmptyQueue());
        myQueue.addQueue("Alpha");
        assertTrue("Queue should not be empty!", !myQueue.isEmptyQueue());
    }

    @Test
    public void addQueueTest() {
        assertTrue(myQueue.size() == 0);
        myQueue.addQueue("Alpha");
        assertTrue(myQueue.size() == 1);
        myQueue.addQueue("Beta");
        assertTrue(myQueue.size() == 2);
    }

    @Test
    public void frontTest() {
        myQueue.addQueue("Alpha");
        assertEquals("Alpha", myQueue.front());
        myQueue.addQueue("Beta");
        assertEquals("Alpha", myQueue.front());
        myQueue.addQueue("Gamma");
        myQueue.deleteQueue();
        assertEquals("Beta", myQueue.front());
        myQueue.deleteQueue();
        assertEquals("Gamma", myQueue.front());
    }

    @Test
    public void rearTest() {
        myQueue.addQueue("Alpha");
        assertEquals("Alpha", myQueue.back());
        myQueue.addQueue("Beta");
        assertEquals("Beta", myQueue.back());
        myQueue.addQueue("Gamma");
        assertEquals("Gamma", myQueue.back());
    }

    @Test(expected = QueueEmptyException.class)
    public void popTest() {
        myQueue.addQueue("Alpha");
        myQueue.addQueue("Beta");
        myQueue.addQueue("Gamma");
        myQueue.deleteQueue();
        assertEquals("Beta", myQueue.front());
        myQueue.deleteQueue();
        assertEquals("Gamma", myQueue.front());
        myQueue.deleteQueue();
        assertTrue(myQueue.size() == 0);
        myQueue.deleteQueue();  // QueueEmptyException
    }

    @Test
    public void containsTest() {
        fail("This is not yet implemented");
//        myQueue.addQueue("Alpha");
//        myQueue.addQueue("Beta");
//        myQueue.addQueue("Gamma");
//        assertTrue(myQueue.contains("Alpha"));
//        assertTrue(myQueue.contains("Beta"));
//        assertTrue(myQueue.contains("Gamma"));
//        assertFalse(myQueue.contains("Omega"));
    }

    @Test
    public void containsAllTest() {
        fail("This is not yet implemented");
//        myQueue.addQueue("Alpha");
//        myQueue.addQueue("Beta");
//        myQueue.addQueue("Gamma");
//        Collection<String> sample = new ArrayList<>();
//        sample.add("Alpha");
//        sample.add("Beta");
//        assertTrue(sample.containsAll(sample));
//        assertTrue(myQueue.containsAll(sample));
//        sample.add("Omega");
//        assertFalse(myQueue.containsAll(sample));
    }

    @Test
    public void addAllTest() {
        fail("This is not yet implemented");
//        Collection<String> sample = new ArrayList<>();
//        sample.add("Alpha");
//        sample.add("Beta");
//        myQueue.addAll(sample);
//        assertTrue(myQueue.size() == 2);
//        assertTrue(myQueue.contains("Alpha"));
//        assertTrue(myQueue.contains("Beta"));
    }

    @Test
    public void removeAllTest() {
        fail("This is not yet implemented");
//        myQueue.addQueue("Alpha");
//        myQueue.addQueue("Beta");
//        myQueue.addQueue("Gamma");
//        Collection<String> sample = new ArrayList<>();
//        sample.add("Alpha");
//        sample.add("Beta");
//        myQueue.removeAll(sample);
//        assertTrue(myQueue.size() == 1);
//        assertFalse(myQueue.contains("Alpha"));
//        assertFalse(myQueue.contains("Beta"));
//        assertTrue(myQueue.contains("Gamma"));
    }

    @Test
    public void equalsTest() {
        fail("This is not yet implemented");
//        myQueue.addQueue("Alpha");
//        myQueue.addQueue("Beta");
//        Queue sample = new Queue();
//        sample.addQueue("Alpha");
//        sample.addQueue("Beta");
//        assertTrue(myQueue.equals(sample));
//        assertTrue(sample.equals(myQueue));
//        sample.addQueue("Gamma");
//        assertFalse(myQueue.equals(sample));
//        assertFalse(sample.equals(myQueue));
    }

    @Test
    public void intersectionTest() {
        fail("This method is not yet implemented");
//        myQueue.addQueue("Alpha");
//        myQueue.addQueue("Beta");
//        Queue sample = new Queue();
//        sample.addQueue("Alpha");
//        sample.addQueue("Beta");
//        sample.addQueue("Gamma");
//        assertEquals(myQueue, myQueue.intersection(sample));
//        assertEquals(myQueue, sample.intersection(myQueue));
//        assertEquals(myQueue, myQueue.intersection(myQueue));
    }
    
    @Test
    public void sortTest() {
        fail("This method is not yet implemented");
//        QueueInt myStack = new QueueInt();
//        myStack.addQueue(1);
//        myStack.addQueue(5);
//        myStack.addQueue(2);
//        myStack.addQueue(4);
//        myStack.addQueue(3);
       // myStack.sort();       
    }
}
