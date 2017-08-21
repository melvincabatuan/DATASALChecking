
package ph.edu.dlsu.datasal.reyes.datasalapp.linkedlist;

import java.util.Collection;
import ph.edu.dlsu.datasal.reyes.datasalapp.myexceptions.*;

public class MyLinkedList<E>{
       private Node<E> head;
       private Node<E> tail;
       private int numItems;
 
   public void MyLinkedList(){   
      numItems = 0;
      head = null;
      tail=null;
      
   } //end constructor
   
   public void add(E item){
       add(numItems+1, item);
   }

   public void add(int index, E item) throws ListIndexOutOfBoundsException {
       if ( index > 0 && index <= numItems + 1){
             if (index == 1){ // Create head
		Node<E> newNode = new Node<E>(item);
		newNode.setNext(head);
		head = newNode;
                if(numItems==0){
                    tail=newNode;
                }
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
   
   public void remove(Object o){
       if(!isEmpty()){
           Node<E> current = head;
           while(current!=null){
               if(current.getNext() == o){
                   Node<E> removeme = current.getNext();
                   current.setNext(removeme.getNext());
                   removeme.setNext(null);
                   numItems--;
               }
               current = current.getNext();
           }
       }
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
   
   public boolean contains(Object o){
       boolean contain = false;
       if(!isEmpty()){
           Node<E> current = head;
           do{
               if(current == o){
                   contain = true;
                   break;
               }
               current = current.getNext();
           }while(current.getNext()!=null);
       }
       return contain;
   }
   
   public boolean containsAll(Collection<?> c){
       int nummatch = 0;
       if(!isEmpty()){
           for(Object element : c){
               if(contains(element)){
                  nummatch++;
                  break;
               }
           }
       }
       return nummatch==c.size();
   }
   
   public void addAll(Collection<? extends E> c){
       for(E element : c){
           add(element);
       }
   }
   
   public void removeAll(Collection<?> c){
       if(!isEmpty()&&c.size()!=0){
           for(Object element : c){
               remove(element);
           }
       }
   }
   
   public void clear(){
       head=null;
       numItems=0;
   }
   
   
  
}
