package Deque;


/**
 * Node class for Doubly Linked List
 */

public class DLNode<E> {

    protected E element;  // store the element
    protected DLNode<E>  next, prev;	// reference to the next and previous node

    /** create a node */
    public DLNode(E e, DLNode<E> p, DLNode<E> n) {
        element = e;
        prev = p;
        next = n;
    }

    /** return the element of this node */
    public E getElement() { return element; }

    /** return the previous node*/
    public DLNode<E> getPrev() { return prev; }

    /** return the next node*/
    public DLNode<E> getNext() { return next; }

    /** set element */
    public void setElement(E newElem) { element = newElem; }

    /** set the previous node */
    public void setPrev(DLNode<E> newPrev) { prev = newPrev; }

    /** set the next node */
    public void setNext(DLNode<E> newNext) { next = newNext; }

}