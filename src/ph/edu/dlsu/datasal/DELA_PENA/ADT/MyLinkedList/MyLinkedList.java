
package ph.edu.dlsu.datasal.DELA_PENA.ADT.MyLinkedList;
import ph.edu.dlsu.datasal.DELA_PENA.MyException.*;

public class MyLinkedList<E> implements List<E>{
       private Node<E> head = null;
       private int numItems = 0;
 
   public void createList(){
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
   
   public void set(int index, E item) throws ListIndexOutOfBoundsException{
       if ( index > 0 && index <= numItems){
             if (index == 1){ // Create head
		head.setItem(item);
            }
	    else
            {
		Node<E> node = find(index);
		node.setItem(item);
            }
	    numItems++;
       	    }
       else
            throw new ListIndexOutOfBoundsException("ADD ERROR: List Index Out Of Bounds");
   }
   
    public void remove(Object o){
       if(!isEmpty()){
           Node<E> current = head;
           while(current.getNext()!=null){
               if(current.getNext().getItem() == o){
                   Node<E> removeme = current.getNext();
                   current.setNext(removeme.getNext());
                   removeme.setNext(null);
                   numItems--;
               }
               current = current.getNext();
           }
       }
   }
   public void remove(int index) throws ListIndexOutOfBoundsException{
       if ( index > 0 && index <= numItems){
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
   
   public void clear(){
       head=null;
       numItems=0;
   }
   
   public boolean contains(E item){
       boolean contain = false;
       if(!this.isEmpty()){
           Node<E> node = head;
           do{
               if(node.getItem()==item){
                   contain = true;
                   break;
               }
               else
                   node = node.getNext();
           }while(node!=null);
       }
       return contain;
   }
   
   public boolean containsAll(MyLinkedList c){
       int nummatch = 0;
       if(!this.isEmpty()){
           for(int i=1; i<=c.size(); i++){
               if(this.contains((E)c.get(i))){
                   nummatch++;
               }
           }
       }
       return nummatch==c.size();
   }
   
   public void removeAll(MyLinkedList c){
       if(!this.isEmpty()){
           for(int i=1; i<=c.size(); i++){
               if(this.contains((E)c.get(i))){
                   this.remove(c.get(i));
               }
           }
       }
   }
   
   public void addAll(MyLinkedList c){
       
    for(int i=1; i<=c.size(); i++){
        this.add((E)c.get(i));
    }
       
   }
   
   public MyLinkedList intersection(MyLinkedList c){
       MyLinkedList result = new MyLinkedList();
       if(!this.isEmpty()){
           for(int i=1; i<=c.size(); i++){
               if(this.contains((E)c.get(i))){
                   result.add(c.get(i));
               }
           }
       }
       return result;
   }
   
   public void sort(){
       
   }
   
   
   
}//end class
