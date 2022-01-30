/**
 * This class implements a sorted map using a splay tree.
 */

public class SplayTreeMap<K,V> extends BinarySearchTreeMap<K,V> {
    
    /** Constructs an empty map with default comparator.*/
    public SplayTreeMap() { super(); }
    /** Constructs an empty map with the given comparator. */
    public SplayTreeMap(Comparator<K> comp) { super(comp); }

    /** Untility used to rebalance after a map operation. */
    private void splay(Position<Entry<K,V>> p) {
        checkPosition(p);
        while (!isRoot(p)) {
            Position<Entry<K,V>> parent = parent(p);
            Position<Entry<K,V>> grand = parent(parent);
            if (grand == null) {  // zig case
                rotate(p);
            } else if ((parent == left(grand)) == (p == left(parent))) {
                // zig-zig case
                rotate(parent); // move "parent" upward
                rotate(p);  // move p upward
            } else {  // zig-zag case
                rotate(p);  // move p upward
                rotate(p);  // move p upward again
            }
        }
    }

    // Overrides the rebalancing hooks to perform the appropriate splay
    protected void rebalanceAccess(Position<Entry<K,V>> p) {
        checkPosition(p);
        if (isExternal(p))
            p = parent(p);
        if (p != null) 
            splay(p);
    }
    protected void rebalanceInsert(Position<Entry<K,V>> p) {
        splay(p);
    }
    protected void rebalanceDelete(Position<Entry<K,V>> p) {
        if (!isRoot(p))
            splay(parent(p));
    }
}

