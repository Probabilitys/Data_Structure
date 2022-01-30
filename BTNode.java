/**
 * This class implements a node of a binary tree
 */

public class BTNode<E> implements BTPosition<E> {

    private Node<E> parent;
    private Node<E> left;
    private Node<E> right;
    private E element;

    /** Contruct a new node. */
    public Node( E e, Node<E> p, Node<E> l, Node<E> r) {
        element = e;
        parent = p;
        left = l;
        right = r;
    }

    /** accessor methods */
    public Node<E> getParent() { return parent; }
    public Node<E> getLeft() { return left; }
    public Node<E> getRight() { return right; }
    public E getElement() { return element; }

    /** mutator methods */
    public void setParent(Node<E> p) { parent = p; }
    public void setLeft(Node<E> l) { left = l; }
    public void setRight(Node<E> r) { right = r; }
    public void setElement(E e) { element = e; }
}