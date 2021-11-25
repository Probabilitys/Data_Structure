/**
 * Abtract Tree class providing some funtionality of Tree interface.
 */

public abstract class AbstractTree<E> implements Tree<E> {
    public boolean isInternal(Position<E> p) { return numChildren(p) > 0; }
    public boolean isExternal(Position<E> p) { return numChildren(p) == 0; }
    public boolean isRoot(Position<E> p) { return p == root( ); }
    public boolean isEmpty( ) { return size( ) == 0; }

    /** Compute the depth of position p. */
    public int depth(Position<E> p) {
        if (isRoot(p)) 
            return 0;
        else
            return 1+depth(parent(p));
    }

    /** Returns the height of the subtree rooted at position p. */
    public int height(Position<E> p) {
        int h=0;
        for (Position<E> c: children(p)) {
            h = Math.max(h, 1+height(c));
        }
        return h;
    }
}
