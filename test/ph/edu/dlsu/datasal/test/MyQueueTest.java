/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.test;

import java.util.ArrayList;
import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ph.edu.dlsu.datasal.chan.myexception.QueueEmptyException;
import ph.edu.dlsu.datasal.chan.myqueue.Queue;

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
        myQueue.push("Alpha");
        assertTrue("Queue should not be empty!", !myQueue.isEmpty());
    }

    @Test
    public void pushTest() {
        assertTrue(myQueue.size() == 0);
        myQueue.push("Alpha");
        assertTrue(myQueue.size() == 1);
        myQueue.push("Beta");
        assertTrue(myQueue.size() == 2);
    }

    @Test
    public void frontTest() {
        myQueue.push("Alpha");
        assertEquals("Alpha", myQueue.front());
        myQueue.push("Beta");
        assertEquals("Alpha", myQueue.front());
        myQueue.push("Gamma");
        myQueue.pop();
        assertEquals("Beta", myQueue.front());
        myQueue.pop();
        assertEquals("Gamma", myQueue.front());
    }

    @Test
    public void rearTest() {
        myQueue.push("Alpha");
        assertEquals("Alpha", myQueue.rear());
        myQueue.push("Beta");
        assertEquals("Beta", myQueue.rear());
        myQueue.push("Gamma");
        assertEquals("Gamma", myQueue.rear());
    }

    @Test(expected = QueueEmptyException.class)
    public void popTest() {
        myQueue.push("Alpha");
        myQueue.push("Beta");
        myQueue.push("Gamma");
        myQueue.pop();
        assertEquals("Beta", myQueue.front());
        myQueue.pop();
        assertEquals("Gamma", myQueue.front());
        myQueue.pop();
        assertTrue(myQueue.size() == 0);
        myQueue.pop();  // QueueEmptyException
    }

    @Test
    public void containsTest() {
        myQueue.push("Alpha");
        myQueue.push("Beta");
        myQueue.push("Gamma");
        assertTrue(myQueue.contains("Alpha"));
        assertTrue(myQueue.contains("Beta"));
        assertTrue(myQueue.contains("Gamma"));
        assertFalse(myQueue.contains("Omega"));
    }
    
//    @Test
//    public void containsAllTest() {
//        myQueue.push("Alpha");
//        myQueue.push("Beta");
//        myQueue.push("Gamma");
//        Queue sample = new Queue();
//        sample.push("Alpha");
//        sample.push("Beta");
//        assertTrue(sample.containsAll(sample));
//        assertTrue(myQueue.containsAll((Collection) sample));
//        assertFalse(sample.containsAll((Collection) myQueue));
//        sample.push("Omega");
//        assertFalse(myQueue.containsAll((Collection) sample));
//    }
}
