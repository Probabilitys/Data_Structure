/**
 * Stack ADT
 */

public interface Stack<E> {

    /** return the number of element */
    public int size();

    /** indicate whether the stack has no element */
    public boolean isEmpty();

    /** return the last inserted element */
    public E top()
      throws EmptyStackException;

    /** insert an element */
    public void push (E element);

    /** return and remove the last inserted element */
    public E pop()
      throws EmptyStackException;
}

