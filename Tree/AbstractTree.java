package Tree;

/**
 * Abtract Tree class providing some funtionality of Tree interface.
 */

public abstract class AbstractTree<E> implements Tree<E> {
    public boolean isInternal(Position<E> p)
            throws InvalidPositionException {
        return numChildren(p) > 0;
    }
    public boolean isExternal(Position<E> p)
            throws InvalidPositionException {
        return numChildren(p) == 0;
    }
    public boolean isRoot(Position<E> p)
            throws InvalidPositionException, EmptyTreeException {
        return p == root();
    }
    public boolean isEmpty() { return size() == 0; }

    /** Compute the depth of position p. */
    public int depth(Position<E> p)
            throws InvalidPositionException,
                    BoundaryViolationException,
                    EmptyTreeException {
        if (isRoot(p))
            return 0;
        else
            return 1+depth(parent(p));
    }

    /** Returns the height of the subtree rooted at position p. */
    public int height(Position<E> p)
            throws InvalidPositionException, BoundaryViolationException{
        int h=0;
        for (Position<E> c: children(p)) {
            h = Math.max(h, 1+height(c));
        }
        return h;
    }

    /** Returns the height of the tree.
     * Runs in O(n^2) worst-case time.
     */
    private int heightBad()
            throws InvalidPositionException,
                    BoundaryViolationException,
                    EmptyTreeException {             // works, but quadratic worst-case time
        int h = 0;
        for (Position<E> p : positions())
            if (isExternal(p))                // only consider leaf positions
                h = Math.max(h, depth(p));
        return h;
    }
}