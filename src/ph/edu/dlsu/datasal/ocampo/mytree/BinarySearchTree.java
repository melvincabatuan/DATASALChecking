/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.ocampo.mytree;

import java.util.*;

/**
 *
 * @author student
 */
public class BinarySearchTree {

    private BinarySearchTree left;
    private BinarySearchTree right;
    private int root;

    public BinarySearchTree(int rootValue) {
        root = rootValue;
        left = null;
        right = null;
    }

    public BinarySearchTree() {
        root = 0;
        left = null;
        right = null;
    }

    //
    public int getRoot() {
        return root;
    }

    public BinarySearchTree getLeft() {
        return left;
    }

    public BinarySearchTree getRight() {
        return right;
    }

    public void setRoot(int newRoot) {
        root = newRoot;
    }

    //
    public void add(int value) {
        if (value < root) {
            if (left == null) {
                left = new BinarySearchTree(value);
            } else {
                left.add(value);
            }
        } else if (value > root) {
            if (right == null) {
                right = new BinarySearchTree(value);
            } else {
                right.add(value);
            }
        }
    }

    public void remove(int value) {

    }

    public LinkedList<Integer> inOrder() {
        LinkedList<Integer> out = new LinkedList();
        if (left != null) {
            left.inOrder();
        }
        out.add(root);
        if (right != null) {
            right.inOrder();
        }
        return out;
    }

    public LinkedList<Integer> postOrder() {
        LinkedList<Integer> out = new LinkedList();
        if (left != null) {
            left.postOrder();
        }
        if (right != null) {
            right.postOrder();
        }
        out.add(root);
        return out;
    }

    public LinkedList<Integer> preOrder() {
        LinkedList<Integer> out = new LinkedList();
        out.add(root);
        if (left != null) {
            left.preOrder();
        }
        if (right != null) {
            right.preOrder();
        }
        return out;
    }
}
