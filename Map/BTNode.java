package Map;

/**
 * This class implements a node of a binary tree
 */

public class BTNode<E> implements BTPosition<E> {

    private BTPosition<E> parent, left, right;
    private E element;

    /** Contruct a new node. */
    public BTNode( E e, BTPosition<E> p, BTPosition<E> l, BTPosition<E> r) {
        element = e;
        parent = p;
        left = l;
        right = r;
    }

    /** accessor methods */
    public BTPosition<E> getParent() { return parent; }
    public BTPosition<E> getLeft() { return left; }
    public BTPosition<E> getRight() { return right; }
    public E getElement() { return element; }

    /** mutator methods */
    public void setParent(BTPosition<E> p) { parent = p; }
    public void setLeft(BTPosition<E> l) { left = l; }
    public void setRight(BTPosition<E> r) { right = r; }
    public void setElement(E e) { element = e; }
}
