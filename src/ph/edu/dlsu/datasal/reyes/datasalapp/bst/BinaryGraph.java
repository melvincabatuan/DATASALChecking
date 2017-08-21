/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.reyes.datasalapp.bst;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Paolo
 */
public class BinaryGraph <E>{
    Branch root = null;
    
    public void add(E item){
        if(root==null){
            Branch newbranch = new Branch();
            newbranch.setItem(item);
            root = newbranch;
        }
        else{
            Branch previous = null;
            Branch current = root;
            while(current!=null){
                if((Integer)current.getItem()>(Integer)item){
                    previous = current;
                    current = current.getLeft();
                }
                else{
                    previous = current;
                    current = current.getRight();
                }
            }
            if((Integer)previous.getItem()>(Integer)item){
                Branch newbranch = new Branch();
                newbranch.setItem(item);
                newbranch.setParent(previous);
                previous.setLeft(newbranch);
            }
            else{
                Branch newbranch = new Branch();
                newbranch.setItem(item);
                newbranch.setParent(previous);
                previous.setRight(newbranch);
            }
        }
    }
    
    
    
    public boolean contains(E item){
        return DFS(item)==null;
    }
    
    public Branch DFS(E item){
        Branch result = null;
        boolean hasunvisitedadjecent = false;
        ArrayList<Branch> visited = new ArrayList();
        Stack<Branch> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Branch current = stack.peek();
            hasunvisitedadjecent = false;
            if(current.getItem()==item){
                result = current;
                break;
            }
            if(!visited.contains(current.getLeft())){
                visited.add(current.getLeft());
                stack.push(current.getLeft());
                hasunvisitedadjecent=true;
            }
            else if(!visited.contains(current.getRight())){
                visited.add(current.getRight());
                stack.push(current.getRight());
                hasunvisitedadjecent=true;
            }
            if(!hasunvisitedadjecent)
                stack.pop();
        }
        
        return result;
    }
    
    public Branch BFS(E item){
        Branch result = null;
        boolean hasunvisitedadjecent = false;
        ArrayList<Branch> visited = new ArrayList();
        Stack<Branch> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Branch current = stack.peek();
            hasunvisitedadjecent = false;
            if(current.getItem()==item){
                result = current;
                break;
            }
            if(!visited.contains(current.getLeft())){
                visited.add(current.getLeft());
                stack.push(current.getLeft());
                hasunvisitedadjecent=true;
            }
            if(!visited.contains(current.getRight())){
                visited.add(current.getRight());
                stack.push(current.getRight());
                hasunvisitedadjecent=true;
            }
            if(!hasunvisitedadjecent)
                stack.pop();
        }
        
        return result;
    }
    
    
    
}
