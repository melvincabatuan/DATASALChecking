/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.ocampo.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ph.edu.dlsu.datasal.ocampo.mytree.BinarySearchTree;

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
        bst = new BinarySearchTree(8);
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
        fail("This method is not yet implemented");
//        BinarySearchTree query = bst.search(13);
//        assertTrue(query.root == 13);
//        query = bst.search(8);
//        assertTrue(query.root == 8);
//        query = bst.search(1);
//        assertTrue(query.root == 1);
//        query = bst.search(20);
//        assertTrue(query == null);
    }

    @Test
    public void bstStructureTest() {
        assertTrue(bst.getRoot() == 8);
        assertTrue((bst.getLeft()).getRoot() == 3);
        assertTrue((bst.getRight()).getRoot() == 10);
    }
}
