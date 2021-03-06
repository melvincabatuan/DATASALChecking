/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.wenceslao.test;

import java.util.ArrayList;
import java.util.Collection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ph.edu.dlsu.datasal.wenceslao.myqueue.MyQueue;


/**
 *
 * @author cobalt
 */
public class MyQueueTest {

    private MyQueue myQueue;

    public MyQueueTest() {
    }

    @Before
    public void setUp() {
        myQueue = new MyQueue();
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
        myQueue.enqueue("Alpha");
        assertEquals("Alpha", myQueue.getFront());
        myQueue.enqueue("Beta");
        assertEquals("Alpha", myQueue.getFront());
        myQueue.enqueue("Gamma");
        myQueue.dequeue();
        assertEquals("Beta", myQueue.getFront());
        myQueue.dequeue();
        assertEquals("Gamma", myQueue.getFront());
    }

    @Test
    public void rearTest() {
        myQueue.enqueue("Alpha");
        assertEquals("Alpha", myQueue.getRear());
        myQueue.enqueue("Beta");
        assertEquals("Beta", myQueue.getRear());
        myQueue.enqueue("Gamma");
        assertEquals("Gamma", myQueue.getRear());
    }

    @Test //(expected = QueueEmptyException.class)
    public void dequeueTest() {
        myQueue.enqueue("Alpha");
        myQueue.enqueue("Beta");
        myQueue.enqueue("Gamma");
        myQueue.dequeue();
        assertEquals("Beta", myQueue.getFront());
        myQueue.dequeue();
        assertEquals("Gamma", myQueue.getFront());
        myQueue.dequeue();
        assertTrue(myQueue.size() == 0);
        myQueue.dequeue();  // QueueEmptyException
    }

    @Test
    public void containsTest() {
        fail("This method is not yet imlemented");
//        myQueue.enqueue("Alpha");
//        myQueue.enqueue("Beta");
//        myQueue.enqueue("Gamma");
//        assertTrue(myQueue.contains("Alpha"));
//        assertTrue(myQueue.contains("Beta"));
//        assertTrue(myQueue.contains("Gamma"));
//        assertFalse(myQueue.contains("Omega"));
    }

    @Test
    public void containsAllTest() {
        fail("This method is not yet implemented");
//        myQueue.enqueue("Alpha");
//        myQueue.enqueue("Beta");
//        myQueue.enqueue("Gamma");
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
        fail("This method is not yet implemented");
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
        fail("This method is not yet implemented");
//        myQueue.enqueue("Alpha");
//        myQueue.enqueue("Beta");
//        myQueue.enqueue("Gamma");
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
        myQueue.enqueue("Alpha");
        myQueue.enqueue("Beta");
        MyQueue sample = new MyQueue();
        sample.enqueue("Alpha");
        sample.enqueue("Beta");
        assertTrue(myQueue.equals(sample));
        assertTrue(sample.equals(myQueue));
        sample.enqueue("Gamma");
        assertFalse(myQueue.equals(sample));
        assertFalse(sample.equals(myQueue));
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
        fail("This method is not yet implemented");
//        QueueInt myStack = new QueueInt();
//        myStack.enqueue(1);
//        myStack.enqueue(5);
//        myStack.enqueue(2);
//        myStack.enqueue(4);
//        myStack.enqueue(3);
       // myStack.sort();       
    }
}
