import java.nio.file.InvalidPathException;

/**
 * Concrete implementation of a binary tree, using a node-based, linked structure
 */

public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {
    

    //---------nested node class----------------
    protected static class Node<E> implements Position<E> {

        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;
        private E element;

        /** Contruct a new node. */
        public Node(Node<E> p, Node<E> l, Node<E> r, E e) {
            parent = p;
            left = l;
            right = r;
            element = e;
        }

        /** accessor methods */
        public Node<E> getParent() { return parent; }
        public Node<E> getLeft() { return left; }
        public Node<E> getRight() { return right; }
        public E getElement() { return element; }

        /** mutator methods */
        public void setParent(Node<E> p) { parent = p; }
        public void setLeft(Node<E> l) { left = l; }
        public void setRight(Node<E> r) { right = r; }
        public void setElement(E e) { element = e; }
    }  //------------ end of Nested Node class


    /** Factory function to create a new node storing element e. */
    protected Node<E> createNode(Node<E> p, Node<E> l, Node<E> r, E e) {
        return new Node<E>(p, l, r, e);
    }

    // instance variables
    protected Node<E> root;
    private int size;

    // constructor
    public LinkedBinaryTree() {
        root = null;
        size = 0;
    }

    // nonpublic utility
    /** Validate the position and return it as a node. */
    protected Node<E> checkPosition(Position<E> p) throws InvalidPositionException {
        if (!(p instanceof Node))
            throw new InvalidPositionException("Invalid position.");
        Node<E> node = (Node<E>) p;
        if (node.getParent() == node)
            throw new InvalidPositionException("Position is no longer in the tree.");
        return node;
    }

    /** Returns the number of elements in the tree. */
    public int size() { return size; }

    /** Returns true if there is no elements storing in the tree. */
    public boolean isEmpty() { return size==0; }

    /** Returns the root of tree as a position, or null if the tree is empty. */
    public Position<E> root() { return root; }

    /** Returns the position of p's parent, or null if p is the root. */
    public Position<E> parent(Position<E> p) throws InvalidPositionException {
        Node<E> v = checkPosition(p);
        return p.getParent();
    }

    /** Returns the position of p's left child. */
    public Position<E> left(Position<E> p) throws InvalidPositionException {
        Node<E> v = checkPosition(p);
        return p.getLeft();
    }

    /** Returns the position of p's right child. */
    public Position<E> right(Position<E> p) throws InvalidPositionException {
        Node<E> v = checkPosition(p);
        return p.getRight();
    }

    /** Returns true if position p has a left child. */
    boolean hasLeft(Position<E> p) throws IllegalArgumentException {
        return left(p) != null;
    }

    /** Returns true if position p has a right child. */
    boolean hasRight(Position<E> p) throws IllegalArgumentException {
        return right(p) != null;
    }

    /** Place element e at root of an empty tree and return its new position. */
    public Position<E> addRoot(E e) throws IllegalStateException {
        if (!isEmpty()) throw new IllegalStateException("The tree is not empty.");
        root = createNode(null, null, null, e);
        size++;
        return root();
    }

    /** Create a new left child of position p storing element e and returns its new position. */
    public Position<E> addLeft(Position<E> p, E e)
                        throws InvalidPositionException {
        Node<E> parent = checkPosition(p);
        if (parent.getLeft() != null)
            throw new InvalidPositionException("The position already has a left child.");
        Node<E> leftChild = createNode(parent, null, null, e);
        parent.setLeft(leftChild);
        size++;
        return leftChild;
    }

    /** Create a new right child of position p storingn element e and returns its new position. */
    public Position<E> addRight(Position<E> p, E e) 
                        throws InvalidPositionException {
        Node<E> parent = checkPosition(p);
        if (parent.getRight() != null)
            throw new InvalidPositionException("The position already has a right child.");
        Node<E> rightChild = createNode(parent, null, null, e);
        parent.setRight(rightChild);
        size++;
        return rightChild;
    }

    /** Replaces the element at position p with a new element e and returns the replaced element. */
    public E replace(Position<E> p, E e) throws InvalidPositionException {
        Node<E> node = checkPosition(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }
    
    /** Attaches trees t1 and t2 as left and right subtrees of external p. */
    public void attach(Position<E> p, LinkedBinaryTree<E> t1,
        LinkedBinaryTree<E> t2) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if (isInternal(p)) throw new IllegalArgumentException("p must be a leaf");
        size += t1.size( ) + t2.size( );
        if (!t1.isEmpty()) {
            Node<E> r1 = checkPosition(t1.root());
            // attach t1 as left subtree of node
            r1.setParent(node);
            node.setLeft(r1);
            t1.size = 0;
        }
        if (!t2.isEmpty( )) { 
            Node<E> r2 = checkPosition(t2.root());
            // attach t2 as right subtree of node
            r2.setParent(node);
            node.setRight(r2);
            t2.size = 0;
        }
    }

    /** Removes the node at Position p and replaces it with its child, if any. */
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if (numChildren(p) == 2)
            throw new IllegalArgumentException("p has two children");
        Node<E> child = (node.getLeft( ) != null ? node.getLeft( ) : node.getRight( ) );
        if (child != null)
            child.setParent(node.getParent( )); // child’s grandparent becomes its parent
        if (node == root)
            root = child; // child becomes root
        else {
            Node<E> parent = node.getParent( );
            if (node == parent.getLeft( ))
                parent.setLeft(child);
            else
                parent.setRight(child);
        }
        size--;
        E temp = node.getElement( );
        node.setElement(null); // help garbage collection
        node.setLeft(null);
        node.setRight(null);
        node.setParent(node); // our convention for defunct node
        return temp;
    }


    /** TODO Returns all elements as a iterable collection. */
    /** TODO Returns all positions as a iterable collection. */

}
