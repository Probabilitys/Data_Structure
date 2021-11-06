public class Node<E> {

  private E element;  // store the element
  private Node<E> next;  // reference to the next node

  /** reference to null */
  public Node() {
    this(null, null);
  }

  /** reference to the given element and the next node */
  public Node(E e, Node<E> n) {
    element = e;
    next = n;
  }

  // return the element
  public E getElement() {
    return element;
  }

  // return the next node
  public Node<E> getNext() {
    return next;
  }

  // modify the element
  public void setElement(E newElem) {
    element = newElem;
  }

  // modify the next node
  public void setNext(Node<E> newNext) {
    next = newNext;
  }
}
