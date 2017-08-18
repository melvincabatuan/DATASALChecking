package ph.edu.dlsu.datasal.chan.myexception;

/* © 2017 by Patrick Matthew Chan */

/* @author Patrick Matthew J. Chan [LBYCP12-EQ1]*/
public class QueueEmptyException extends RuntimeException{
    public QueueEmptyException(String s){
        super(s);
    }//end constructor
}
