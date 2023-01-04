package PriorityQueue.PositionalList;


/**
 * Iterator Interface
 */

interface Iterator<E> {

    /** Returns true if there is at least one element following, and false otherwise */
    boolean hasNext();

    /** Returns the next element */
    E next() throws BoundaryViolationException;

    /** Removes and returns the element in the most recent call to next() */
    void remove() throws IllegalStateException;
}