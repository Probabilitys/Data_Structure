/**
 * Tree ADT (general)
 */

public interface Tree<E> extends Iterable<E> {

    /** Returns the position of the root of the tree. */
    Posotion<E> root();

    /** Returns the position of the parent of position, or null is p is the root. */
    Position<E> parent(Position<E> p) throws IllegalArgumentException;

    /** Returns an iterable collection containing the children of position p. */
    Iterable<Position<E>> children(Position<E> p)
                            throws IllegalArgumentException;

    /** Returns the number of children of position p */
    int numChildren(Position<E> p) throws IllegalArgumentException;
    
    /** Return true if position p has a least one child. */
    boolean isInternal(Position<E> p) throws IllegalArgumentException;
    
    /** Return true if position p has no children. */
    boolean isExternal(Position<E> p) throws IllegalArgumentException;
    
    /** Returns true if position p is the root of the tree. */
    boolean isRoot(Position<E> p) throws IllegalArgumentException;
    
    /** Returns the number of positions containing in the tree. */
    int size();

    /** Indicates whether the tree contains no positions. */
    boolean isEmpty();

    /** Returns an iterator for all elements. */
    Iterator<E> iterator();

    /** Returns an iterable collection of all positions. */
    Iterable<Position<E>> positions();
}