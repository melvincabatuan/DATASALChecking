/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.datasal.SIMEON.binarysearchtree;

/**
 *
 * @author jiggy
 */
public class BinaryTree<E> {

    E leaf = null;
    BinaryTree childleft = null;
    BinaryTree childright = null;
    BinaryTree parent = null;
    
    public void BinaryTree(E leaf){
        this.leaf = leaf;
    }
    public E returnLeaf(){
        return leaf;
    }
    
    public void setLeaf(E leaf){
        this.leaf = leaf;
    }
    
    public BinaryTree returnLeft(){
        return childleft;
    }
    
    public BinaryTree returnRight(){
        return childright;
    }
    
    public void setLeft(BinaryTree child){
        childleft= child;
    }
    
    public void setRight(BinaryTree child){
        childright = child;
    }
    
    public void setParent(BinaryTree parenter){
        parent = parenter;
    }
    
    public BinaryTree getParent(){
        return parent;
    }
}

