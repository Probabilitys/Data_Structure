/**
 * IndexList (aka Vector) ADT
 * extends the notion of array by storing a sequence of arbitrary obj
 */

public interface IndexList<E> {

    /** Returns the number of elements in this list */
    public int size();

    /** Returns whether the list is empty */
    public boolean isEmpty();

    /** Inserts an element e at index i */
    public void add(int i, E e)
      throws IndexOutOfBoundsException;

    /** Returns the element at index i without removing it */
    public E get(int i)
      throws IndexOutOfBoundsException;

    /** Removes and returns the element at index i */
    public E remove(int i)
      throws IndexOutOfBoundsException;

    /** Replaces the element at index i with e and returns the previous element */
    public E set(int i, E e)
      throws IndexOutOfBoundsException;

}
