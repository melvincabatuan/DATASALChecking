/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.SIMEON.mylinkedlist;
import ph.edu.dlsu.datasal.SIMEON.myinterface.MyLinkedListInterface;
import ph.edu.dlsu.datasal.SIMEON.myexception.ListFullException;
import ph.edu.dlsu.datasal.SIMEON.myexception.ListIndexOutOfBoundsException;


/**
 *
 * @author jiggy
 */
public class MyLinkedList<E> implements MyLinkedListInterface<E>{
       private Node<E> head=null;
       private int numItems=0;
 
   public void createList(){   
      numItems = 0;
      head = null;
   } //end constructor

   public void add(int index, E item) throws ListIndexOutOfBoundsException {
       if ( index > 0 && index <= numItems + 1){
             if (index == 1){ // Create head
		Node<E> newNode = new Node<E>(item);
		newNode.setNext(head);
		head = newNode;
            }
	    else
            {
		Node<E> newNode = new Node<E>(item);
		Node<E> previous = find(index-1);
		newNode.setNext(previous.getNext());
		previous.setNext(newNode);
            }
	    numItems++;
       	    }
       else
            throw new ListIndexOutOfBoundsException("ADD ERROR: List Index Out Of Bounds");
   }

   public void remove(int index) throws ListIndexOutOfBoundsException{
       if ( index > 0 && index <= numItems + 1){
          if (index == 1){ 
              head = head.getNext(); 
          }
          else{
             Node<E> previous = find(index-1);
             Node<E> current = previous.getNext();
             previous.setNext(current.getNext());
           }
           numItems--;
       }
      else
            throw new ListIndexOutOfBoundsException("REMOVE ERROR: List Index Out Of Bounds");
   }

   public boolean isEmpty(){
           return numItems == 0;          
   }

   public E get(int index) throws ListIndexOutOfBoundsException{
       if ( index > 0 && index <= numItems + 1){
             Node<E> current = find(index);
             E item = current.getItem();
             return item;
       }
       else
            throw new ListIndexOutOfBoundsException("GET ERROR: List Index Out Of Bounds");
   }

   public int size(){
           return numItems;
   }

   /// Locate a specified node in a linked list:
   private Node<E> find(int index){
   // precondition: index is the number of the desired node,
   // precondition: assume 1 ≤ index ≤ numItems+1;
   // postcondition: returns a reference to the desired node.
   Node<E> current = head;
 
   for (int i = 1; i < index; i++)
   { 
      current = current.getNext(); 
   }

   return current;
   } //end find
   public boolean contains(E item){
        for(int i=1;i<=numItems;i++){
            if(get(i)==item){
                return true;
            }
        }
        return false;
    }
    public boolean equals(MyLinkedList<E> list) {
        int b=0;
        if(numItems!=list.size()){
            return false;
        }
        for(int i=1;i<=numItems;i++){
            for(int j =1;j<=list.size();j++){
                if(get(i)==list.get(j)){
                    b++;
                }
            }     
    }
        if(b==numItems){
        return true;
        }
        else
            return false;
    }
    public void sort(){
        
    }
    public void clear(){
        if(!isEmpty()){
            for(int i=1;i<=numItems;){
                remove(i);            
            }
        }
    }
    public void removeAll(MyLinkedList<E> list){
           for(int i=1;i<=numItems;i++){
            for(int j =1;j<=list.size();j++){
                if(get(i)==list.get(j)){
                    remove(i);
                }
            }     
    }
    }
    public void addAll(MyLinkedList<E> list){
        for(int i=1; i<=list.size();i++){
            add((numItems+1),list.get(i));
            
        }
    }
    public boolean containsAll(MyLinkedList<E> list){
        int b=0;
        if(list.size()>size()){
            return false;
        }

            for(int i=1;i<=list.size();i++){
                if(contains(list.get(i))){
                    b++;
                }
            }     
    
        if(b>=list.size()){
        return true;
        }
        else
            return false;
    }
        public MyLinkedList<E> intersection(MyLinkedList<E> list){
        MyLinkedList<E> result = new MyLinkedList<E>();
        result.createList();
        int z=1;

            for(int j=1;j<=list.size();j++){
                if(contains(list.get(j))){
                    result.add(z, list.get(j));
                    z++;
                }
            }
        
        return result;
    }
            public void sort(MyLinkedList<Integer> list){
        int[] temp = new int[list.size()];
       
          for(int i=0;i<list.size();i++){
          temp[i]=list.get(i+1);
    }
         temp=BubbleSort(temp);
         list.clear();
         for(int i=1;i<=temp.length;i++){
             
            list.add(i, temp[i-1]);
         }
         

    }       
    public int[] BubbleSort(int[] temp){
        int tempz;  

               for(int i = 0; i < temp.length - 1; i++){
                    for(int j = 0; j < temp.length - i - 1; j++){
                        if(temp[j] > temp[j+1])
                        { 
                           tempz = temp[j];
                           temp[j] = temp[j+1]; 
                           temp[j+1] = tempz;
                         }
                     
                    } 
                }
               return temp;
    }
}//end class
