/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.bhuller.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ph.edu.dlsu.datasal.bhuller.mytree.BinarySearchTree;

/**
 *
 * @author ECE
 */
public class MyBstTest {

    private BinarySearchTree bst;

    public MyBstTest() {
    }

    @Before
    public void setUp() {
        bst = new BinarySearchTree();
        bst.add(8); // root
        bst.add(3);
        bst.add(1);
        bst.add(6);
        bst.add(4);
        bst.add(7);
        bst.add(10);
        bst.add(14);
        bst.add(13);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void bstSearchTest() {
       assertTrue(1 == bst.findMinimum());
       assertTrue(14 == bst.findMaximum());
       assertTrue(bst.contains(13));
       assertFalse(bst.contains(20));
    }

    @Test
    public void bstStructureTest() {
        assertTrue(bst.getRootValue() == 8);
//        assertTrue(bst.left.root == 3);
//        assertTrue(bst.right.root == 10);
    }
}
