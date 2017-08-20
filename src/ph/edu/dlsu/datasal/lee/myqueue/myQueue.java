package ph.edu.dlsu.datasal.lee.myqueue;



public class myQueue<E>{
private int total;

    private Node first, last;

    private class Node {
        private E object;
        private Node next;
    }


    public myQueue<E> enqueue(E object)
    {
        Node current = last;
        last = new Node();
        last.object = object;

        if (total++ == 0) first = last;
        else current.next = last;
        return this;
    }

    public E dequeue()
    {  if (total == 0) throw new java.util.NoSuchElementException();
        E object = first.object;
        first = first.next;
        if (--total == 0) last = null;
        return object;
    }

    public boolean isEmpty()
    {
        if (total == 0) return true;
        else return false;
    }
}