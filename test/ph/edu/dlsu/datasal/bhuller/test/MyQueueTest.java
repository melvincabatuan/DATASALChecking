/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.bhuller.test;

import java.util.ArrayList;
import java.util.Collection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ph.edu.dlsu.datasal.bhuller.myqueue.Queue;

/**
 *
 * @author cobalt
 */
public class MyQueueTest {

    private Queue myQueue;

    public MyQueueTest() {
    }

    @Before
    public void setUp() {
        myQueue = new Queue();
    }

    @After
    public void tearDown() {
        myQueue = null;
    }

    @Test
    public void initializationTest() {
        assertTrue("Queue should be empty!", myQueue.isEmpty());
        myQueue.add("Alpha");
        assertTrue("Queue should not be empty!", !myQueue.isEmpty());
    }

    @Test
    public void addTest() {
        assertTrue(myQueue.size() == 0);
        myQueue.add("Alpha");
        assertTrue(myQueue.size() == 1);
        myQueue.add("Beta");
        assertTrue(myQueue.size() == 2);
    }

    @Test
    public void frontTest() {
        fail("This method is not implemented");
//        myQueue.add("Alpha");
//        assertEquals("Alpha", myQueue.front());
//        myQueue.add("Beta");
//        assertEquals("Alpha", myQueue.front());
//        myQueue.add("Gamma");
//        myQueue.dequeue();
//        assertEquals("Beta", myQueue.front());
//        myQueue.dequeue();
//        assertEquals("Gamma", myQueue.front());
    }

    @Test
    public void rearTest() {
         fail("This method is not implemented");
//        myQueue.add("Alpha");
//        assertEquals("Alpha", myQueue.rear());
//        myQueue.add("Beta");
//        assertEquals("Beta", myQueue.rear());
//        myQueue.add("Gamma");
//        assertEquals("Gamma", myQueue.rear());
    }

    @Test // (expected = QueueEmptyException.class)
    public void dequeueTest() {
        myQueue.add("Alpha");
        myQueue.add("Beta");
        myQueue.add("Gamma");
        assertTrue(myQueue.size() == 3);
        myQueue.deque();
        assertTrue(myQueue.size() == 2);
        myQueue.deque();
        assertTrue(myQueue.size() == 1);
        myQueue.deque();
        assertTrue(myQueue.size() == 0);
    }

    @Test
    public void containsTest() {
        myQueue.add("Alpha");
        myQueue.add("Beta");
        myQueue.add("Gamma");
        assertTrue(myQueue.contains("Alpha"));
        assertTrue(myQueue.contains("Beta"));
        assertTrue(myQueue.contains("Gamma"));
        assertFalse(myQueue.contains("Omega"));
    }

    @Test
    public void containsAllTest() {
        myQueue.add("Alpha");
        myQueue.add("Beta");
        myQueue.add("Gamma");
        Queue<String> sample = new Queue<>();
        sample.add("Alpha");
        sample.add("Beta");
        assertTrue(sample.containsAll(sample));
        assertTrue(myQueue.containsAll(sample));
        sample.add("Omega");
        assertFalse(myQueue.containsAll(sample));
    }

    @Test
    public void addAllTest() {
        Queue<String> sample = new Queue<>();
        sample.add("Alpha");
        sample.add("Beta");
        myQueue.addAll(sample);
        assertTrue(myQueue.size() == 2);
        assertTrue(myQueue.contains("Alpha"));
        assertTrue(myQueue.contains("Beta"));
    }

    @Test
    public void removeAllTest() {
        myQueue.add("Alpha");
        myQueue.add("Beta");
        myQueue.add("Gamma");
        Queue<String> sample = new Queue<>();
        sample.add("Alpha");
        sample.add("Beta");
        myQueue.removeAll(sample);
        assertTrue(myQueue.size() == 1);
        assertFalse(myQueue.contains("Alpha"));
        assertFalse(myQueue.contains("Beta"));
        assertTrue(myQueue.contains("Gamma"));
    }

    @Test
    public void equalsTest() {
        myQueue.add("Alpha");
        myQueue.add("Beta");
        Queue sample = new Queue();
        sample.add("Alpha");
        sample.add("Beta");
        assertTrue(myQueue.equals(sample));
        assertTrue(sample.equals(myQueue));
        sample.add("Gamma");
        assertFalse(myQueue.equals(sample));
        assertFalse(sample.equals(myQueue));
    }

    @Test
    public void intersectionTest() {
        myQueue.add("Alpha");
        myQueue.add("Beta");
        Queue sample = new Queue();
        sample.add("Alpha");
        sample.add("Beta");
        sample.add("Gamma");
        assertEquals(myQueue, myQueue.intersection(sample));
        assertEquals(myQueue, sample.intersection(myQueue));
        assertEquals(myQueue, myQueue.intersection(myQueue));
    }
    
    @Test
    public void sortTest() {
        fail("This method is not yet implemented");
    }
}
