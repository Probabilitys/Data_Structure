/** 
  * Deque ADT
  * Both end could be modified
  * a subset of java.util.LinkedList methods
  */

public interface Deque<E> {

    /** return the number of elements */
    public int size();

    /** indicate whether the deque is empty */
    public boolean isEmpty();

    /** return the first element */
    public E getFirst() throws EmptyDequeException;

    /** return the last element */
    public E getLast() throws EmptyDequeException;

    /** insert an element to the front */
    public void addFirst (E element); 

    /** insert an element to the end */
    public void addLast (E element); 

    /** remove and return teh first element */
    public E removeFirst() throws EmptyDequeException;

    /** remove and return the last element */
    public E removeLast() throws EmptyDequeException;
}
