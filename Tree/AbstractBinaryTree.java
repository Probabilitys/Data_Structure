package Tree;

/**
 * Abtract Tree class providing some funtionality of Tree interface.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Abtract Tree class providing some funtionality of Tree interface.
 * Implemented methods sibling(p), numChildren(p), children(p).
 */

public abstract class AbstractBinaryTree<E>
        extends AbstractTree<E> implements BinaryTree<E> {

    /** Returns the position of p's sibling, or null if not exists. */
    public Position<E> sibling(Position<E> p)
            throws InvalidPositionException, BoundaryViolationException {
        Position<E> parent = parent(p);
        if (parent == null) return null;
        return (p == left(parent)) ? right(parent) : left(parent);
    }

    /** Returns the number of children of position p */
    public int numChildren(Position<E> p)
            throws InvalidPositionException {
        int count=0;
        if (hasLeft(p)) count++;
        if (hasRight(p)) count++;
        return count;
    }

    /** Returns an iterable collection of the Positions representing p's children */
    public Iterable<Position<E>> children(Position<E> p)
            throws InvalidPositionException, BoundaryViolationException {
        List<Position<E>> snapshot = new ArrayList<>(2);
        if (left(p) != null)
            snapshot.add(left(p));
        if (right(p) != null)
            snapshot.add(right(p));
        return snapshot;
    }
}