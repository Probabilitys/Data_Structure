package Queue;

public class Node<E> {

    private E element;  // store the element
    private Node<E> next;  // reference to the next node

    /** Reference to null */
    public Node() {
        this(null, null);
    }

    /** Reference to the given element and the next node */
    public Node(E e, Node<E> n) {
        element = e;
        next = n;
    }

    /** Return the element */
    public E getElement() {
        return element;
    }

    /** Return the next node */
    public Node<E> getNext() {
        return next;
    }

    /** Modify the element */
    public void setElement(E newElem) {
        element = newElem;
    }

    /** Modify the next node */
    public void setNext(Node<E> newNext) {
        next = newNext;
    }
}