/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.velasco.myexception;

import ph.edu.dlsu.velasco.namesurfer.*;

/**
 *
 * @author NeilOliver
 */
public class QueueEmptyException extends RuntimeException{
    public QueueEmptyException(){
        super();
    }
     
    public QueueEmptyException(String message){
        super(message);
    }
     
    public QueueEmptyException(String message, Throwable cause){
        super(message, cause);
    }
}
