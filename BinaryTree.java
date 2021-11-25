/**
 * Binary Tree ADT
 */

public interface BinaryTree<E> extends Tree<E> {
    
    /** Returns the position of p's left child, or null if not exists. */
    Position<E> left(Position<E> p) throws IllegalArgumentException;

    /** Returns the position of p's right child, or null if not exists. */
    Position<E> right(Position<E> p) throws IllegalArgumentException;

    /** Returns true if position p has a left child. */
    boolean hasLeft(Position<E> p) throws IllegalArgumentException;

    /** Returns true if position p has a right child. */
    boolean hasRight(Position<E> p) throws IllegalArgumentException;

    /** Returns the position of p's sibling, or null if not exists. */
    Position<E> sibling(Position<E> p) throws IllegalArgumentException;

}
