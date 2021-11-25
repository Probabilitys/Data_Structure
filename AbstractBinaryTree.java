/** 
 * Abtract Tree class providing some funtionality of Tree interface. 
 * Implemented methods sibling(p), numChildren(p), children(p).
 */

public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {
    
    /** Returns the position of p's sibling, or null if not exists. */
    public Position<E> sibling(Position<E> p) {
        Position<E> parent = parent(p);
        if (parent == null) return null;
        return (p == left(parent)) ? right(parent) : left(parent);
    }

    /** Returns the number of children of position p */
    public int numChildren(Position<E> p) {
        int count=0;
        if (left(p) != null) count++;
        if (right(p) != null) count++;
        return count;
    }

    /** Returns an iterable collection of the Positions representing p's children */
    public Iterabale<Position<E>> children(Position<E> p) {
        List<Position<E>> snapshot = new ArrayList<>(2);
        if (left(p) != null)
            snapshot.add(left(p));
        if (right(p) != null)
            snapshot.add(right(p));
        return snapshot;
    }
}
