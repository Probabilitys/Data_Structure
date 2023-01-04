package Map;

/**
 * This class implements a sorted map using AVL Tre structure
 * which is a specialization of Binary Search Tree
 */

public class AVLTreeMap<K,V> extends BinarySearchTreeMap<K,V> {

    /** Constructs an empty map with default comparator.*/
    public AVLTreeMap() { super(); }
    /** Constructs an empty map with the given comparator. */
    public AVLTreeMap(Comparator<K> comp) { super(comp); }

    /** Returns the height of the given tree position. */
    protected int height(Position<Entry<K,V>> p) {
        return tree.getAux(p);
    }

    /** Recomputes the height of the given position based on its children's heights. */
    protected void recomputeHeight(Position<Entry<K,V>> p) {
        tree.setAux(p, 1+Math.max(height(left(p)), height(right(p))));
    }

    /** Returns whether a position has balance factor between -1 and 1 inclusive. */
    protected boolean isBalanced(Position<Entry<K,V>> p) {
        return Math.abs(height(left(p)) - height(right(p))) <= 1;
    }

    /** Returns a child of p with height no smaller than that of the other child. */
    protected Position<Entry<K,V>> tallerChild(Position<Entry<K,V>> p) {
        if (height(left(p)) > height(right(p))) return left(p);
        if (height(left(p)) < height(right(p))) return right(p);
        // equal height children; break tie while matching parent's orientation
        if (isRoot(p)) return left(p);  // choice is irrelevant
        if (p == left(parent(p))) return left(p);  // return aligned child
        else return right(p);
    }

    /**
     * Utility used to rebalance after an insert or removal operation. This traverses the
     * path upward from p, performing a trinode restructuring when imbalance is found,
     * continuing until balance is restored.
     */
    protected void rebalance(Position<Entry<K,V>> p) {
        int oldHeight, newHeight;
        do {
            oldHeight = height(p);
            if (!isBalanced(p)) {
                // perform trinode restructuring, setting p to resulting root,
                // and recompute new local heights after the restructuring
                p = restructure(tallerChild(tallerChild(p)));
                recomputeHeight(left(p));
                recomputeHeight(right(p));
            }
            recomputeHeight(p);
            newHeight = height(p);
            p = parent(p);
        } while ((oldHeight != newHeight) && (p != null));
    }

    /** Overrides the rebalanceInsert hook. */
    protected void rebalanceInsert(Position<Entry<K,V>> p) {
        rebalance(p);
    }

    /** Overrides the rebalanceDelete hook. */
    protected void rebalanceDelete(Position<Entry<K,V>> p ) {
        if (!isRoot(p))
            rebalance(parent(p));
    }
}
