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

}
