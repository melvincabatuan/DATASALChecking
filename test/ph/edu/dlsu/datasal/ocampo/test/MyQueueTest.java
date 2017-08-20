/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.ocampo.test;

import java.util.ArrayList;
import java.util.Collection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ph.edu.dlsu.datasal.ocampo.myexception.QueueEmptyException;
import ph.edu.dlsu.datasal.ocampo.myqueue.MyDynamicQueue;

/**
 *
 * @author cobalt
 */
public class MyQueueTest {

    private MyDynamicQueue myQueue;

    public MyQueueTest() {
    }

    @Before
    public void setUp() {
        myQueue = new MyDynamicQueue();
    }

    @After
    public void tearDown() {
        myQueue = null;
    }

    @Test
    public void initializationTest() {
        assertTrue("Queue should be empty!", myQueue.isEmpty());
        myQueue.enQueue("Alpha");
        assertTrue("Queue should not be empty!", !myQueue.isEmpty());
    }

    @Test
    public void enQueueTest() {
        assertTrue(myQueue.size() == 0);
        myQueue.enQueue("Alpha");
        assertTrue(myQueue.size() == 1);
        myQueue.enQueue("Beta");
        assertTrue(myQueue.size() == 2);
    }

    @Test
    public void getFrontTest() {
        myQueue.enQueue("Alpha");
        assertEquals("Alpha", myQueue.getFront());
        myQueue.enQueue("Beta");
        assertEquals("Alpha", myQueue.getFront());
        myQueue.enQueue("Gamma");
        myQueue.deQueue();
        assertEquals("Beta", myQueue.getFront());
        myQueue.deQueue();
        assertEquals("Gamma", myQueue.getFront());
    }

    @Test
    public void rearTest() {
        fail("This method is not yet implemented");
//        myQueue.enQueue("Alpha");
//        assertEquals("Alpha", myQueue.rear());
//        myQueue.enQueue("Beta");
//        assertEquals("Beta", myQueue.rear());
//        myQueue.enQueue("Gamma");
//        assertEquals("Gamma", myQueue.rear());
    }

    @Test(expected = QueueEmptyException.class)
    public void deQueueTest() {
        myQueue.enQueue("Alpha");
        myQueue.enQueue("Beta");
        myQueue.enQueue("Gamma");
        myQueue.deQueue();
        assertEquals("Beta", myQueue.getFront());
        myQueue.deQueue();
        assertEquals("Gamma", myQueue.getFront());
        myQueue.deQueue();
        assertTrue(myQueue.size() == 0);
        myQueue.deQueue();  // QueueEmptyException
    }

    @Test
    public void containsTest() {
        myQueue.enQueue("Alpha");
        myQueue.enQueue("Beta");
        myQueue.enQueue("Gamma");
        assertTrue(myQueue.contains("Alpha"));
        assertTrue(myQueue.contains("Beta"));
        assertTrue(myQueue.contains("Gamma"));
        assertFalse(myQueue.contains("Omega"));
    }

    @Test
    public void containsAllTest() {
        fail("This method is not yet implemented");
//        myQueue.enQueue("Alpha");
//        myQueue.enQueue("Beta");
//        myQueue.enQueue("Gamma");
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
//        myQueue.enQueue("Alpha");
//        myQueue.enQueue("Beta");
//        myQueue.enQueue("Gamma");
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
        myQueue.enQueue("Alpha");
        myQueue.enQueue("Beta");
        MyDynamicQueue sample = new MyDynamicQueue();
        sample.enQueue("Alpha");
        sample.enQueue("Beta");
        assertTrue(myQueue.equals(sample));
        assertTrue(sample.equals(myQueue));
        sample.enQueue("Gamma");
        assertFalse(myQueue.equals(sample));
        assertFalse(sample.equals(myQueue));
    }

    @Test
    public void intersectionTest() {
        myQueue.enQueue("Alpha");
        myQueue.enQueue("Beta");
        MyDynamicQueue sample = new MyDynamicQueue();
        sample.enQueue("Alpha");
        sample.enQueue("Beta");
        sample.enQueue("Gamma");
        assertTrue(myQueue.equals(myQueue.intersection(sample)));
        assertTrue(myQueue.equals(sample.intersection(myQueue)));
        assertTrue(myQueue.equals(myQueue.intersection(myQueue)));
    }

    @Test
    public void sortTest() {
        fail("This method is not yet implemented");
//        QueueInt myStack = new QueueInt();
//        myStack.enQueue(1);
//        myStack.enQueue(5);
//        myStack.enQueue(2);
//        myStack.enQueue(4);
//        myStack.enQueue(3);
        // myStack.sort();       
    }
}
