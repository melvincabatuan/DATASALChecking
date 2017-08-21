/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.go.bst;

/**
 *
 * @author Go
 */
public class BST {
    public static Node root;
    public BST(){
        root = null;
    }
    public boolean findData(int num){
        Node curr = root;
        while(curr!=null){
            if(curr.data==num)
                return true;
            else if(curr.data>num)
                curr=curr.left;
            else
                curr= curr.right;
        }
        return false;
    }
    
    public int getRootVal(){
        return root.value();
    }
    
    public void add(int num){
        Node newNode = new Node(num);
        if(root!=null){
            root= newNode;
            return;
        }
        Node curr = newNode;
        Node parents=null;
        while(true){
            parents = curr;
            if(num<parents.data){
                curr = curr.left;
                if(curr==null){
                    parents.left = newNode;
                    return;
                }
            }
            else{
                curr = curr.right;
                if(curr==null){
                    parents.right = newNode;
                    return;
                }
            }
        }
    }
    
    public boolean delete(int num){
        Node parents= root;
        Node curr= root;
        boolean isLeft = false;
        while(curr.data!=num){
            parents=curr;
            if(curr.data>num){
                isLeft = true;
                curr = curr.left;
            }else{
                isLeft = false;
                curr = curr.right;
            }
        }
        if(curr==null){
            //return;
            return false;
        }
        
        if(curr.left== null && curr.right==null){//no child
            if(curr==root)
                root=null;
            if(isLeft == true)
                parents.left=null;
            else
                parents.right=null;
            
        }else if(curr.left==null){//one leaf right
            if(curr==root){
                root=curr.right;
            }else if(isLeft){
                parents.left=curr.right;
            }else{
                parents.right=curr.right;
            }
            
        }else if(curr.right==null){//one leaf left
            if(curr==root){
                root=curr.left;
            }else if(isLeft){
                parents.left=curr.left;
            }else{
                parents.right= curr.left;
            }
        }else if(curr.left!=null&&curr.right!=null){//has two children
           //get the next successorr
           Node next = getNext(curr);
           if(curr==root)
               root=next;
           else if(isLeft)
               //parents.right=next;
               parents.left=next;
           else
               parents.right=next;
           next.left= curr.left;//??????
        }
        return true;
    }
    
    public Node getNext(Node deleted){
        Node next=null;
        Node parentofNext=null;
        Node curr= deleted.right;
        while(curr!=null){
            parentofNext= next;
            next=curr;
            curr= curr.left;
        }
        if(next!=deleted.right){
            parentofNext.left=next.right;
            next.right=deleted.right;
        }
        return next;
    }
    public void disp(Node root){
        if(root!=null){
            disp(root.left);
            disp(root.right);
        }
    }
    class Node{//initialization
        int data;
        Node left;
        Node right;
        public Node(int data){
            this.data=data;
            left=null;
            right=null;
        }
        public int value(){
            return data;
        }
    }
    public static void main(String[] args){
        
    }
}
