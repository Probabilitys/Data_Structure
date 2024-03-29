package PriorityQueue.PositionalList;

/**
 * Position ADT
 */

public interface Position<E> {

    /* returns the element stored at this position */
    E getElement() throws IllegalStateException;
}