/**
 * IndexList (aka Vector) ADT
 * extends the notion of array by storing a sequence of arbitrary object
 */

public interface IndexList<E> {

    /** Returns the number of elements in this list */
    public int size();

    /** Returns whether the list is empty */
    public boolean isEmpty();

    /** Returns the element at index i without removing it */
    public E elemAtRank(int i) throws IndexOutOfBoundsException;

    /** Replaces the element at index i with e and returns the previous element */
    public E replaceAtRank(int i, E e) throws IndexOutOfBoundsException;

    /** Inserts an element e at index i */
    public void insertAtRank(int i, E e) throws IndexOutOfBoundsException;

    /** Removes and returns the element at index i */
    public E removeAtRank(int i) throws IndexOutOfBoundsException;

}
