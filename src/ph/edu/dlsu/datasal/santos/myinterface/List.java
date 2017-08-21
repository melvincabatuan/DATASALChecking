package ph.edu.dlsu.datasal.santos.myinterface;

import java.util.Collection;
import ph.edu.dlsu.datasal.santos.myexception.*;

public interface List<E> {

    public void createList();

    public void add(int index, E item) throws ListIndexOutOfBoundsException;

    public void remove(int index) throws ListIndexOutOfBoundsException;

    public boolean isEmpty();

    public E get(int index) throws ListIndexOutOfBoundsException;

    public int size();

    public void set(int index, E item) throws ListIndexOutOfBoundsException;

    public void add(E e);

    public boolean contains(Object o);

    public boolean containsAll(List<E> c);

    public void addAll(List<E> c);

    public void removeAll(List<E> c);

    public void clear();

    public boolean equals(Object o);

    public void sort();

    public boolean intersection(List<E> list);
}
