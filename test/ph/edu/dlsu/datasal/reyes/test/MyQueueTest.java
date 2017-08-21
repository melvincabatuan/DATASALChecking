/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.reyes.test;

import java.util.ArrayList;
import java.util.Collection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ph.edu.dlsu.datasal.reyes.datasalapp.queue.queue;

/**
 *
 * @author cobalt
 */
public class MyQueueTest {

    private queue myQueue;

    public MyQueueTest() {
    }

    @Before
    public void setUp() {
        myQueue = new queue();
    }

    @After
    public void tearDown() {
        myQueue = null;
    }

    @Test
    public void initializationTest() {
        assertTrue("Queue should be empty!", myQueue.isEmpty());
        myQueue.enqueue("Alpha");
        assertTrue("Queue should not be empty!", !myQueue.isEmpty());
    }

    @Test
    public void enqueueTest() {
        assertTrue(myQueue.size() == 0);
        myQueue.enqueue("Alpha");
        assertTrue(myQueue.size() == 1);
        myQueue.enqueue("Beta");
        assertTrue(myQueue.size() == 2);
    }

    @Test
    public void frontTest() {
        fail();
//        myQueue.enqueue("Alpha");
//        assertEquals("Alpha", myQueue.peek());
//        myQueue.enqueue("Beta");
//        assertEquals("Alpha", myQueue.peek());
//        myQueue.enqueue("Gamma");
//        myQueue.dequeue();
//        assertEquals("Beta", myQueue.peek());
//        myQueue.dequeue();
//        assertEquals("Gamma", myQueue.peek());
    }

    @Test
    public void rearTest() {
        fail("This method is not yet implemented");
//        myQueue.enqueue("Alpha");
//        assertEquals("Alpha", myQueue.rear());
//        myQueue.enqueue("Beta");
//        assertEquals("Beta", myQueue.rear());
//        myQueue.enqueue("Gamma");
//        assertEquals("Gamma", myQueue.rear());
    }

    @Test //(expected = QueueEmptyException.class)
    public void dequeueTest() {
        myQueue.enqueue("Alpha");
        myQueue.enqueue("Beta");
        myQueue.enqueue("Gamma");
        assertTrue(myQueue.size() == 3);
        myQueue.dequeue();
        assertTrue(myQueue.size() == 2);
        myQueue.dequeue();
        assertTrue(myQueue.size() == 1);
        myQueue.dequeue();
        assertTrue(myQueue.size() == 0);
        // myQueue.dequeue();  // QueueEmptyException
    }

    @Test
    public void containsTest() {
        myQueue.enqueue("Alpha");
        myQueue.enqueue("Beta");
        myQueue.enqueue("Gamma");
        assertTrue(myQueue.contains("Alpha"));
        assertTrue(myQueue.contains("Beta"));
        assertTrue(myQueue.contains("Gamma"));
        assertFalse(myQueue.contains("Omega"));
    }

    @Test
    public void containsAllTest() {
        fail();
//        myQueue.enqueue("Alpha");
//        myQueue.enqueue("Beta");
//        myQueue.enqueue("Gamma");
//        Queue sample = new Queue<>();
//        sample.enqueue("Alpha");
//        sample.enqueue("Beta");
//        assertTrue(sample.containsAll(sample));
//        assertTrue(myQueue.containsAll(sample));
//        sample.enqueue("Omega");
//        assertFalse(myQueue.containsAll(sample));
    }

    @Test
    public void addAllTest() {
        fail();
//        Queue sample = new Queue<>();
//        sample.enqueue("Alpha");
//        sample.enqueue("Beta");
//        myQueue.addAll(sample);
//        assertTrue(myQueue.size() == 2);
//        assertTrue(myQueue.contains("Alpha"));
//        assertTrue(myQueue.contains("Beta"));
    }

    @Test
    public void removeAllTest() {
        fail();
//        myQueue.enqueue("Alpha");
//        myQueue.enqueue("Beta");
//        myQueue.enqueue("Gamma");
//        Queue sample = new Queue<>();
//        sample.enqueue("Alpha");
//        sample.enqueue("Beta");
//        myQueue.removeAll(sample);
//        assertTrue(myQueue.size() == 1);
//        assertFalse(myQueue.contains("Alpha"));
//        assertFalse(myQueue.contains("Beta"));
//        assertTrue(myQueue.contains("Gamma"));
    }

    @Test
    public void equalsTest() {
        fail();
//        myQueue.enqueue("Alpha");
//        myQueue.enqueue("Beta");
//        Queue sample = new Queue();
//        sample.enqueue("Alpha");
//        sample.enqueue("Beta");
//        assertTrue(myQueue.equals(sample));
//        assertTrue(sample.equals(myQueue));
//        sample.enqueue("Gamma");
//        assertFalse(myQueue.equals(sample));
//        assertFalse(sample.equals(myQueue));
    }

    @Test
    public void intersectionTest() {
        fail("This method is not yet implemented");
//        myQueue.enqueue("Alpha");
//        myQueue.enqueue("Beta");
//        Queue sample = new Queue();
//        sample.enqueue("Alpha");
//        sample.enqueue("Beta");
//        sample.enqueue("Gamma");
//        assertEquals(myQueue, myQueue.intersection(sample));
//        assertEquals(myQueue, sample.intersection(myQueue));
//        assertEquals(myQueue, myQueue.intersection(myQueue));
    }

    @Test
    public void sortTest() {
        fail();
//        Queue<Integer> intQ = new Queue<>();
//        intQ.enqueue(1);
//        intQ.enqueue(5);
//        intQ.enqueue(2);
//        intQ.enqueue(4);
//        intQ.enqueue(3);
//        intQ.sort(intQ);
//        assertTrue(intQ.peek() == 1);
//        intQ.dequeue();
//        assertTrue(intQ.peek() == 2);
//        intQ.dequeue();
//        assertTrue(intQ.peek() == 3);
//        intQ.dequeue();
//        assertTrue(intQ.peek() == 4);
//        intQ.dequeue();
//        assertTrue(intQ.peek() == 5);
    }
}