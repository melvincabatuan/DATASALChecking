/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.ocampo.myexception;

/**
 * Queue Empty Exception. Thrown when getting or dequeueing from an empty queue,
 */
public class QueueEmptyException extends RuntimeException {
    public QueueEmptyException() {
        super();
    }
    
    public QueueEmptyException(String s) {
        super(s);
    }
}
