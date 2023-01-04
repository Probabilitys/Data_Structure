package Sequence;

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
    public E getAtIndex(int i) throws BoundaryViolationException;

    /** Replaces the element at index i with e and returns the previous element */
    public E setAtIndex(int i, E e) throws BoundaryViolationException;

    /** Inserts an element e at index i */
    public void addAtIndex(int i, E e) throws BoundaryViolationException;

    /** Removes and returns the element at index i */
    public E removeAtIndex(int i) throws BoundaryViolationException;

}