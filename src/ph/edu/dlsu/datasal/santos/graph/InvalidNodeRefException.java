/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.datasal.santos.graph;

/**
 *
 * @author cobalt
 */
public class InvalidNodeRefException extends RuntimeException {

    public InvalidNodeRefException(String error) {
        super(error);
    }

}
