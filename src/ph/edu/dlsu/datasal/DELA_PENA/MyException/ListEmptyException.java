/*
 * File: ListEmptyException.java
 * ------------------
 * This class handles a Empty list 
 */
package ph.edu.dlsu.datasal.DELA_PENA.MyException;
public class ListEmptyException extends RuntimeException{
    public ListEmptyException(String s){ 
        super(s);
    }//end constructor
} //end ListException
