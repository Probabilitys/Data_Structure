package Map;

/**
 * This class implements a node as a position of a binary tree
 */

public interface BTPosition<E> extends Position<E> {

    /** Returns the element stored at this position. */
    public E getElement();

    /** Returns the left child of this position */
    public BTPosition<E> getLeft();

    /** Returns the right child of this position */
    public BTPosition<E> getRight();

    /** Returns the parent of this position */
    public BTPosition<E> getParent();

    /** Sets the element stored at this position */
    public void setElement(E o);

    /** Sets the left child of this position */
    public void setLeft(BTPosition<E> v);

    /** Sets the right child of this position */
    public void setRight(BTPosition<E> v);

    /** Sets the parent of this position */
    public void setParent(BTPosition<E> v);

}