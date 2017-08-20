/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.DELA_PENA.ADT.MyGraph;

/**
 *
 * @author Paolo
 */
public class Branch <E>{
    E item = null;
    Branch leftbranch = null;
    Branch rightbranch = null;
    Branch parent = null;
    
    public void BinaryBranch(E item){
        this.item = item;
    }
    
    public E getItem(){
        return item;
    }
    
    public void setItem(E item){
        this.item = item;
    }
    
    public Branch getLeft(){
        return leftbranch;
    }
    
    public Branch getRight(){
        return rightbranch;
    }
    
    public void setLeft(Branch left){
        leftbranch = left;
    }
    
    public void setRight(Branch right){
        rightbranch = right;
    }
    
    public void setParent(Branch par){
        parent = par;
    }
    
    public Branch getParent(){
        return parent;
    }
}
