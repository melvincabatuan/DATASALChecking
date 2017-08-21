/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.albaladejo.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ph.edu.dlsu.datasal.albaladejo.mytree.BinarySearchTree;

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
        bst.add(8);
        bst.add(3);
        bst.add(1);
        bst.add(6);
        bst.add(4);
        bst.add(7);           // left subtree
        bst.add(10);
        bst.add(14);
        bst.add(13);            // right subtree
    }

    @After
    public void tearDown() {
    }

    @Test
    public void bstSearchTest() {
        assertTrue(bst.findData(13));
        assertFalse(bst.findData(20));
        assertTrue(bst.findData(8));
        assertFalse(bst.findData(30));
    }

    @Test
    public void bstStructureTest() {
        assertTrue(bst.getRootVal() == 8);
    }
}
