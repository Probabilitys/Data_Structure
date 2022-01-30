
/**
 * This class implements a sorted map using Binary Search Tree structure
 */

public class BinarySearchTreeMap<K,V> extends AbstractSortedMap<K,V> {


    //---------------- nested BalanceableBinaryTree class ----------------
    public class BalanceableBinaryTree<K,V> extends LinkedBinaryTree<Entry<K,V>> {
    
        //--------------- nested BSTNode class --------------
        // this class extends the BinaryTree Node class
        protected static class BSTNode<E> extends BTNode<E> {
            int aux = 0;  // stores the auxiliary value
            BSTNode(E e, Node<E> parent, Node<E> leftChild, Node<E> rightChild) {
                super(e, parent, leftChild, rightChild);
            }
            public int getAux() { return aux; }
            public void setAux(int a) { aux = a; }
        }  // ----------- end of nested BSTNode class -----------
        
    
        // position-based methods related to aux field
        public int getAux(BTPosition<Entry<K,V>> p) {
            checkPosition(p);
            return ((BSTNode<Entry<K,V>>) p).getAux();
        }
        public void setAux(BTPosition<Entry<K,V>> p, int value) {
            checkPosition(p);
            ((BSTNode<Entry<K,V>>) p).setAux(value);
        }
    
        // Overrides node factory function to produce a BSTNode (rather than a Node)
        protected BTNode<Entry<K,V>> createNode(
            Entry<K,V> e, 
            BTNode<Entry<K,V>> parent, 
            BTNode<Entry<K,V>> left,
            BTNode<Entry<K,V>> right
        ) {
            return new BSTNode<>(e, parent, left, right);
        }
    
        /** Relinks a parent node with its oriented child node. */
        private void relink(BTNode<Entry<K,V>> parent,
            BTNode<Entry<K,V>> child, boolean makeLeftChild)
        {
            child.setParent(parent);
            if(makeLeftChild)
                parent.setLeft(child);
            else
                parent.setRight(child);
        }
    
        /** Rotates Position p above its parent. */
        public void rotate(BTPosition<Entry<K,V>> p) {
            BTNode<Entry<K,V>> x = checkPosition(p);
            BTNode<Entry<K,V>> y = x.getParent();  // assume exists
            BTNode<Entry<K,V>> z = y.getParent();  // grandparent (possibly null)
            if(z == null) {
                root = x;  // x becomes root of the tree
                x.setParent(null);
            } else {
                relink(z, x, y == z.getLeft());  // x becomes direct child of z
            }
            // rotate x and y, including transfer of middle subtree
            if (x == y.getLeft()) {
                relink(y, x.getRight(), true);
                relink(x, y, false);
            } else {
                relink(y, x.getLeft(), false);
                relink(x, y, true);
            }
        }
    
        /** Performs a trinode restructuring of Position x with its parent/grandparent. */
        public BTPosition<Entry<K,V>> restructure(BTPosition<Entry<K,V>> x) {
            BTPosition<Entry<K,V>> y = parent(x);
            BTPosition<Entry<K,V>> z = parent(y);
            if ((x == right(y)) == (y == right(z))) {
                rotate(y);
                return y;
            } else {
                rotate(x);
                rotate(x);
                return x;
            }
        }
    } //----------- end of nested BalanceableBinaryTree class -----------
    
    
    // Use a specialized subclass of the LinkedBinaryTree class named
    // BalanceableBinaryTree to represent the structure
    protected BalanceableBinaryTree<K,V> tree = new BalanceableBinaryTree<>();

    /** Constructs an empty map using default comparator. */
    public BinarySearchTreeMap() {
        super();
        tree.addRoot(null);
    }
    /** Constructs and empty map using the given comparator. */
    public BinarySearchTreeMap(Comparator<K> comp) {
        super(comp);
        tree.addRoot(null);
    }

    /** Returns teh numbers of entries in the map. */
    public int size() {
        return (tree.size()-1) / 2;
    }

    /** Utility to insert a new entry at a leaf. */
    private void expandExternal(Position<Entry<K,V>> p, Entry<K,V> entry) {
        tree.set(p. entry);
        tree.addLeft(p, null);
        tree.addRight(p, null);
    }


    /**
     * Provides a series of protected methods that provide notational shorthands to wrap
     * operations on the underlying linked binary tree
     */
    protected Position<Entry<K,V>> root() { return tree.root(); }
    protected Position<Entry<K,V>> parent(Position<Entry<K,V>> p) { return tree.parent(p); }
    protected Position<Entry<K,V>> left(Position<Entry<K,V>> p) { return tree.left(p); }
    protected Position<Entry<K,V>> right(Position<Entry<K,V>> p) { return tree.right(p); }
    protected Position<Entry<K,V>> sibling(Position<Entry<K,V>> p) { return tree.sibling(p); }
    protected boolean isRoot(Position<Entry<K,V>> p) { return tree.isRoot(p); }
    protected boolean isExternal(Position<Entry<K,V>> p) { return tree.isExternal(p); }
    protected boolean isInternal(Position<Entry<K,V>> p) { return tree.isInternal(p); }
    protected void set(Position<Entry<K,V>> p, Entry<K,V> e) { tree.set(p, e); }
    protected Entry<K,V> remove(Position<Entry<K,V>> p) { return tree.remove(p); }
    protected void rotate(Position<Entry<K,V>> p) { tree.rotate(p); }
    protected Position<Entry<K,V>> restructure(Position<Entry<K,V>> x) { return tree.restructure(x); }


    /** Returns the position in p's subtree having the given key (or else the terminal leaf). */
    private Position<Entry<K,V>> treeSearch(Position<Entry<K,V>> p, K key) {
        if (isExternal(p))
            return p;
        int comp = compare(key, p.getElement());
        if (comp == 0)
            return p;
        else if (comp < 0)
            return treeSearch(left(p), key);
        else
            return treeSearch(right(p), key);
    }

    /** Returns the value associated with the specified key (or else null). */
    public V get(K key) throws IllegalArgumentException {
        checkKey(key);
        Position<Entry<K,V>> p = treeSearch(root(), key);
        rebalanceAccess(p);
        if(isExternal(p)) return null;
        return p.getElement().getValue();
    }

    /** Associates the given value with the given key, returning any overriden value. */
    public V put(K key,  V value) throws IllegalArgumentException {
        checkKey(key);
        Entry<K,V> newEntry = new MapEntry<>(key, value);
        Position<Entry<K,V>> p = treeSearch(root(), key);
        if(isExternal(p)) {
            expandExternal(p, newEntrt);
            rebalanceInsert(p);
            return null;
        } else {
            V old = p.getElement().getValue();
            set(p, newEntry);
            rebalanceAccess(p);
            return old;
        }
    }

    /** Removes the entry having key k (if any) and returns its associated value. */
    public V remove(K key) throws IllegalArgumentException {
        checkKey(key);
        Position<Entry<K,V>> p = treeSearch(root(), key);
        if(isExternal(p)) {  // key not found
            rebalanceAccess(p);  // hook for balanced tree subclass
            return null;
        } else {
            V old = p.getElement().getValue();
            if (isInternal(left(p)) && isInternal(right(p))) { // both children are internal
                Position<Entry<K,V>> replacement = treeMax(left(p));
                set(p, replacement.getElement());
                p = replacement;
            }  // now p has at most one child that is an internal node
            Position<Entry<K,V>> leaf = (isExternal(left(p)) ? left(p) : right(p));
            Position<Entry<K,V>> sib = sibling(leaf);
            remove(leaf);
            remove(p);  // sib is promoted in p's place
            rabalanceDelete(sib);  // hook for balanced tree subclasses
            return old;
        }
    }

    /** Returns the position with the maximum key in subtree rooted at Position p. */
    protected Position<Entry<K,V>> treeMax(Position<Entry<K,V>> p) {
        Position<Entry<K,V>> walk = p;
        while (isInternal(walk))
            walk = right(walk);
        return parent(walk);
    }

    /** Returns the entry having the greatest key (or null if map is empty). */
    public Entry<K,V> lastEntry() {
        if (isEmpty()) return null;
        return treeMax(root()).getElement();
    }

    /** Returns the entry with greatest key less than or equal to given key (if any). */
    public Entry<K,V> floorEntry(K key) throws IllegalArgumentException {
        checkKey(key);
        Position<Entry<K,V>> p = treeSearch(root(), key);
        if (isInteranl(p)) return p.getElement();  // exact match
        while (!isRoot(p)) {
            if(p == right(parent(p)))
                return parent(p).getElement();  // parent has next lesser key
            else
                p = parent(p);
        }
        return null;  // no such floor exists
    }

    /** Returns the entry with greatest key strictly less than given key (if any). */
    public Entry<K,V> lowerEntry(K key) throws IllegalArgumentException {
        checkKey(key);
        Position<Entry<K,V>> p = treeSearch(root(), key);
        if (isInternal(p) && isInternal(left(p)))
            return treeMax(left(p)).getElement();  // predecessor to p
        // otherwise, we had failed search, or match no left child
        while (!isRoot(p)) {
            if (p == right(parent(p)))
                return parent(p).getElement();
            else
                p = parent(p);
        }
    }

    /** Returns an iterable collection of all key-value entries of the map. */
    public Iterable<Entry<K,V>> entrySet() {
        ArrayList<Entry<K,V>> buffer = new ArrayList<>(size());
        for (Position<Entry<K,V>> p: tree.inorder())
            if (isInternal(p))
                buffer.add(p.getElement());
        return buffer;
    }

    /** Returns an iterable of entries with keys in range [fromKey, toKey). */
    public Iterable<Entry<K,V>> subMap(K fromKey, K toKey) {
        ArrayList<Entry<K,V>> buffer = new ArrayList<>(size());
        if (compare(fromKey, toKey) < 0)
            subMapRecurse(fromKey, toKey, root(), buffer);
        return buffer;
    }
    private void subMapRecurse(K fromKey, K toKey,
        Position<Entry<K,V>> p, ArrayList<Entry<K,V>> buffer) {
        if (isInternal(p)) {
            if (compare(p.getElement(), fromKey) < 0) {
                // p's key is less than fromKey, so any relevant entries are to the rihgt
                subMapRecurse(fromKey, toKey, right(p), buffer);
            } else {
                subMapRecurse(fromKey, toKey, left(p), buffer);  // first consider left subtree
                if (compare(p.getElement(), toKey) < 0) {  // p is within range
                    buffer.add(p.getElement());  // so add it to buffer, and consider right subtree
                    subMapRecurse(fromKey, toKey, right(p), buffer);
                }
            }            
        }
    }

    /** 
     * Serves as hooks for rebalancing algorithms.
     * To be overriden by sub classes.
     */
    protected void rebalanceInsert(Position<Entry<K,V>> p) { }
    protected void rebalanceDelete(Position<Entry<K,V>> p) { }
    protected void rebalanceAccess(Position<Entry<K,V>> p) { }
}
