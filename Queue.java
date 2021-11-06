/**
 * Queue ADT
 */

public interface Queue<E> {
    
    /** insert an element to the end */
    public void enqueue(E elem);

    /** remove and return the element at the front */
    public E dequeue()
        throws EmptyQueueException;

    /** return the element at the front */
    public E front()
        throws EmptyQueueException;

    /** return the number of element stored */
    public int size();

    /** indicate whether the queue is empty */
    public boolean isEmpty();
}
