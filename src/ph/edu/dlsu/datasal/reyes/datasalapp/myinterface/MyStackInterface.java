package ph.edu.dlsu.datasal.reyes.datasalapp.myinterface;

import ph.edu.dlsu.datasal.reyes.datasalapp.myexceptions.*;





public interface MyStackInterface<E>{


  public void createList(); 
  // precondition: none
  // postcondition: Create an empty list


  public void push(E item) throws ListIndexOutOfBoundsException; 
// precondition: index (to be added) is within the position of the list of items, 1<=index<=size()+1
// postcondition: Insert item at position index of a list
// if 1<=index<= size()+1. If index <= size(), items
// at position index onwards are shifted one position
// to the right. Throws an exception when index is out of range.



  public void pop() throws ListIndexOutOfBoundsException; 
// precondition: index (to be removed) is within the position of the list of items, 1<=index<=size()
// postcondition: Remove item at position index of a list
// if 1<=index<= size(). Items at position
// index+1 onwards are shifted one position to the left
// Throws an exception when index is out of range, or if list is empty.



 public boolean isEmpty(); 
// precondition: none
// postcondition: Determine if a list is empty



public E top() throws ListIndexOutOfBoundsException; 
// precondition: index is within the position of the list of items, 1<=index<=size()
// postcondition: Returns item at position index of
// a list if 1<=index<=size(). Throws an exception if index is out of range.



public int size();  
// precondition: none
// postcondition: Returns number of items in a list
 
}
