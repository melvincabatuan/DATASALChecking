/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.bhuller.mytree;

/**
 *
 * @author Bhuller
 */
public class BinarySearchTree {

    public static Node root;

    public BinarySearchTree() {
        this.root = null;
    }
    
    public int getRootValue(){
        return root.dat;
    }

    public void add(int val) {
        Node newNode = new Node(val);
        if (root == null) {
            root = newNode;
            return;
        }
        Node current = root;
        Node par = null;
        while (true) {
            par = current;
            //comparison for which side to add on
            if (val < current.dat) {
                current = current.lef;
                if (current == null) {
                    par.lef = newNode;
                    return;
                }
            } else {
                current = current.rig;
                if (current == null) {
                    par.rig = newNode;
                    return;
                }
            }
        }
    }

    public void display(Node start) {
        if (start != null) {
            display(start.lef);
            System.out.print(" " + start.dat);
            display(start.rig);
        }
    }

    public boolean contains(int val) {
        Node cur = root;
        while (cur != null) {
            if (cur.dat == val) {
                return true;
            } else if (cur.dat > val) {
                cur = cur.lef;
            } else {
                cur = cur.rig;
            }
        }
        return false;
    }

    public int findMinimum() {
        if (root == null) {
            return 0;
        }
        Node curr = root;
        while (curr.lef != null) {
            curr = curr.lef;
        }
        return curr.dat;
    }

    public int findMaximum() {
        if (root == null) {
            return 0;
        }

        Node curr = root;
        while (curr.rig != null) {
            curr = curr.rig;
        }
        return curr.dat;
    }

   
}

 class Node {

        public int dat;
        Node lef;
        Node rig;

        public Node(int res) {
            this.dat = res;
            lef = null;
            rig = null;
        }
        
        

    }
