package Map;

/**
 * A specialization of Linked Binary Tree with supporrt for balancing.
 */

public class BalanceableBinaryTree<K,V> extends LinkedBinaryTree<Entry<K,V>> {


    //--------------- nested BSTNode class --------------
    // this class extends the BinaryTree Node class
    protected static class BSTNode<E> extends BTPosition<E> {
        int aux = 0;  // stores the auxiliary value
        BSTNode(E e, BTPosition<E> parent, BTPosition<E> leftChild, BTPosition<E> rightChild) {
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
    protected BTPosition<Entry<K,V>> createNode(
            Entry<K,V> e,
            BTPosition<Entry<K,V>> parent,
            BTPosition<Entry<K,V>> left,
            BTPosition<Entry<K,V>> right
    ) {
        return new BSTNode<Entry<K,V>>(e, parent, left, right);
    }

    /** Relinks a parent node with its oriented child node. */
    private void relink(BTPosition<Entry<K,V>> parent,
                        BTPosition<Entry<K,V>> child,
                        boolean makeLeftChild)
    {
        child.setParent(parent);
        if(makeLeftChild)
            parent.setLeft(child);
        else
            parent.setRight(child);
    }

    /** Rotates Position p above its parent. */
    public void rotate(BTPosition<Entry<K,V>> p) {
        BTPosition<Entry<K,V>> x = checkPosition(p);
        BTPosition<Entry<K,V>> y = x.getParent();  // assume exists
        BTPosition<Entry<K,V>> z = y.getParent();  // grandparent (possibly null)
        if(z == null) {
            root = x;  // x becomes root of the tree
            x.setParent(null);
        } else {
            relink(z, x, y == z.getLeft());  // x becomes direct child of z
        }
        // rotate x and y, including transfer of middle subtree
        if (x == y.getLeft()) {
            relink(y, x.getRight(), true);  // x’s right child becomes y’s left
            relink(x, y, false);  // y becomes x’s right child
        } else {
            relink(y, x.getLeft(), false);  // x’s left child becomes y’s right
            relink(x, y, true);  // y becomes left child of x
        }
    }

    /** Performs a trinode restructuring of Position x with its parent/grandparent. */
    public BTPosition<Entry<K,V>> restructure(BTPosition<Entry<K,V>> x) {
        BTPosition<Entry<K,V>> y = parent(x);
        BTPosition<Entry<K,V>> z = parent(y);
        if ((x == right(y)) == (y == right(z))) {  // matching alignments
            rotate(y);  // single rotation (of y)
            return y;  // y is new subtree root
        } else {  // opposite alignments
            rotate(x);  // double rotation (of x)
            rotate(x);
            return x;  // x is new subtree root
        }
    }
}
