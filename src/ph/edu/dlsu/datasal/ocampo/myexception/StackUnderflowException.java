package ph.edu.dlsu.datasal.ocampo.myexception;
/**
 * This exception is thrown when popping or getting from an empty stack.
 */

public class StackUnderflowException extends RuntimeException {
    public StackUnderflowException(String s) {
        super(s);
    }
}
