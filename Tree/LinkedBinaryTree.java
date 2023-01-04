package Tree;

/** An implementation of the BinaryTree interface by means of a linked structure. */

import java.util.Iterator;
import java.util.ArrayList;
import java.lang.Iterable;
import java.util.Random;


public class LinkedBinaryTree<E> extends AbstractBinaryTree<E>
        implements BinaryTree<E> {

    protected BTPosition<E> root; // reference to the root
    protected int size;  // number of nodes

    /**  Creates an empty binary tree. */
    public LinkedBinaryTree() {
        root = null;  // start with an empty tree
        size = 0;
    }

    /** Returns the number of nodes in the tree. */
    public int size() { return size; }

    /** Returns whether the tree is empty. */
    public boolean isEmpty() { return size() == 0; }

    /** Returns whether a node is internal. */
    public boolean isInternal(Position<E> v) throws InvalidPositionException {
        BTPosition<E> vv = checkPosition(v);
        return hasLeft(vv) || hasRight(vv);
    }

    /** Returns whether a given node is external. */
    public boolean isExternal(Position<E> v) throws InvalidPositionException {
        //checkPosition(v);  // auxiliary method
        return !isInternal(v);
    }

    /** Returns whether a node is the root. */
    public boolean isRoot(Position<E> v) throws InvalidPositionException {
        // your work is here.
        BTPosition<E> vv = checkPosition(v);
        return vv == root;
    }

    /** Returns whether a node has a left child. */
    public boolean hasLeft(Position<E> v) throws InvalidPositionException {
        // Your work is here.
        BTPosition<E> vv = checkPosition(v);
        return (vv.getLeft() != null);
    }

    public boolean hasRight(Position<E> v) throws InvalidPositionException {
        BTPosition<E> vv = checkPosition(v);
        return (vv.getRight() != null);
    }

    /** Returns the root of the tree. */
    public Position<E> root() throws EmptyTreeException {
        if (root == null)
            throw new EmptyTreeException("The tree is empty");
        return root;
    }

    /** Returns the left child of a node. */
    public Position<E> left(Position<E> v)
            throws InvalidPositionException, BoundaryViolationException {
        // Your work is here.
        BTPosition<E> vv = checkPosition(v);
        Position<E> leftPos = vv.getLeft();
        if (leftPos == null)
            throw new BoundaryViolationException("No left child");
        return leftPos;
    }

    public Position<E> right(Position<E> v)
            throws InvalidPositionException, BoundaryViolationException {
        BTPosition<E> vv = checkPosition(v);
        Position<E> rightPos = vv.getRight();
        if (rightPos == null)
            throw new BoundaryViolationException("No right child");
        return rightPos;
    }


    /** Returns the parent of a node. */
    public Position<E> parent(Position<E> v)
            throws InvalidPositionException, BoundaryViolationException {
        BTPosition<E> vv = checkPosition(v);
        Position<E> parentPos = vv.getParent();
        if (parentPos == null)
            throw new BoundaryViolationException("No parent");
        return parentPos;
    }

    /** Returns an iterable collection of the children of a node. */
    public Iterable<Position<E>> children(Position<E> v)
            throws InvalidPositionException, BoundaryViolationException {
        ArrayList<Position<E>> children = new ArrayList<Position<E>>();
        if (hasLeft(v))
            children.add(left(v));
        if (hasRight(v))
            children.add(right(v));
        return children;
    }

    /** Returns an iterable collection of the tree nodes. */
    public Iterable<Position<E>> positions() {
        ArrayList<Position<E>> positions = new ArrayList<Position<E>>();
        try {
            if(size != 0)
                inorderPositions(root(), positions);  // assign positions in inorder
            return positions;
        } catch (EmptyTreeException | InvalidPositionException |
                 BoundaryViolationException err) {
            return positions;
        }
    }

    /** Returns an iterator of the elements stored at the nodes */
    public Iterator<E> iterator() {
        Iterable<Position<E>> positions = positions();
        ArrayList<E> elements = new ArrayList<E>();
        for (Position<E> pos: positions)
            elements.add(pos.getElement());
        return elements.iterator();  // An iterator of elements
    }

    /** Replaces the element at a node. */
    public E replace(Position<E> v, E o)
            throws InvalidPositionException {
        BTPosition<E> vv = checkPosition(v);
        E temp = v.getElement();
        vv.setElement(o);
        return temp;
    }

    // Additional accessor method
    /** Return the sibling of a node */
    public Position<E> sibling(Position<E> v)
            throws InvalidPositionException, BoundaryViolationException {
        BTPosition<E> vv = checkPosition(v);
        BTPosition<E> parentPos = vv.getParent();
        if (parentPos != null) {
            BTPosition<E> sibPos;
            BTPosition<E> leftPos = parentPos.getLeft();
            if (leftPos == vv)
                sibPos = parentPos.getRight();
            else
                sibPos = parentPos.getLeft();
            if (sibPos != null)
                return sibPos;
        }
        throw new BoundaryViolationException("No sibling");
    }

    // Additional update methods
    /** Adds a root node to an empty tree */
    public Position<E> addRoot(E e) throws NonEmptyTreeException {
        if(!isEmpty())
            throw new NonEmptyTreeException("Tree already has a root");
        size = 1;
        root = createNode(e,null,null,null);
        return root;
    }

    /** Inserts a left child at a given node. */
    public Position<E> insertLeft(Position<E> v, E e)
            throws InvalidPositionException {
        BTPosition<E> vv = checkPosition(v);
        Position<E> leftPos = vv.getLeft();
        if (leftPos != null)
            throw new InvalidPositionException("Node already has a left child");
        BTPosition<E> ww = createNode(e, vv, null, null);
        vv.setLeft(ww);
        size++;
        return ww;
    }

    /** Inserts a right child at a given node. */
    public Position<E> insertRight(Position<E> v, E e)
            throws InvalidPositionException {
        BTPosition<E> vv = checkPosition(v);
        Position<E> rightPos = vv.getRight();
        if (rightPos != null)
            throw new InvalidPositionException("Node already has a right child");
        BTPosition<E> ww = createNode(e, vv, null, null);
        vv.setRight(ww);
        size++;
        return ww;
    }

    /** Removes a node with zero or one child. */
    public E remove(Position<E> v)
            throws InvalidPositionException {
        BTPosition<E> vv = checkPosition(v);
        BTPosition<E> leftPos = vv.getLeft();
        BTPosition<E> rightPos = vv.getRight();
        if (leftPos != null && rightPos != null)
            throw new InvalidPositionException("Cannot remove node with two children");
        BTPosition<E> ww;  // the only child of v, if any
        if (leftPos != null)
            ww = leftPos;
        else if (rightPos != null)
            ww = rightPos;
        else   // v is a leaf
            ww = null;
        if (vv == root) {  // v is the root
            if (ww != null)
                ww.setParent(null);
            root = ww;
        }
        else {   // v is not the root
            BTPosition<E> uu = vv.getParent();
            if (vv == uu.getLeft())
                uu.setLeft(ww);
            else
                uu.setRight(ww);
            if(ww != null)
                ww.setParent(uu);
        }
        size--;
        return v.getElement();
    }

    /** Attaches two trees to be subtrees of an external node. */
    public void attach(Position<E> v, BinaryTree<E> T1, BinaryTree<E> T2)
            throws InvalidPositionException, EmptyTreeException {
        BTPosition<E> vv = checkPosition(v);
        if (isInternal(v))
            throw new InvalidPositionException("Cannot attach from internal node");
        if (!T1.isEmpty()) {
            BTPosition<E> r1 = checkPosition(T1.root());
            vv.setLeft(r1);
            r1.setParent(vv);  // T1 should be invalidated
        }
        if (!T2.isEmpty()) {
            BTPosition<E> r2 = checkPosition(T2.root());
            vv.setRight(r2);
            r2.setParent(vv);  // T2 should be invalidated
        }

        // update size here
        size += ( T1.size() + T2.size() );

    }
    /** If v is a good binary tree node, cast to BTPosition, else throw exception */
    protected BTPosition<E> checkPosition(Position<E> v)
            throws InvalidPositionException {
        // Your work is here.
        if (v == null)
            throw new InvalidPositionException("Invalid null position.");
        if (!(v instanceof BTNode))
            throw new InvalidPositionException("This is not a tree node.");
        BTNode<E> vv = (BTNode<E>) v;
        return vv;
    }

    /** Creates a new binary tree node */
    protected BTPosition<E> createNode(E element, BTPosition<E> parent,
                                       BTPosition<E> left, BTPosition<E> right) {
        return new BTNode<E>(element, parent, left, right);
    }

    /**
     * Creates a list storing the the nodes in the subtree of a node,
     * ordered according to the ineorder traversal of the subtree.
     */
    protected void inorderPositions(Position<E> v, ArrayList<Position<E>> pos)
            throws InvalidPositionException, BoundaryViolationException {

        // Your work is here.
        checkPosition(v);
        if (hasLeft(v))
            inorderPositions(left(v), pos);
        pos.add(v);
        if (hasRight(v))
            inorderPositions(right(v), pos);
    }


    public int depth (Tree<E> T, Position<E> v)
            throws InvalidPositionException,
                    BoundaryViolationException,
                    EmptyTreeException {
        if (T.isRoot(v))
            return 0;
        else
            return 1 + depth(T, T.parent(v));
    }

    public int height1 (Tree<E> T)
            throws InvalidPositionException,
                    BoundaryViolationException,
                    EmptyTreeException {
        int h = 0;
        for (Position<E> v : T.positions()) {
            if (T.isExternal(v))
                h = Math.max(h, depth(T, v));
        }
        return h;
    }

    public int height2 (Tree<E> T, Position<E> v)
            throws InvalidPositionException,
                    BoundaryViolationException,
                    EmptyTreeException {
        if (T.isExternal(v)) return 0;
        int h = 0;
        for (Position<E> w : T.children(v))
            h = Math.max(h, height2(T, w));
        return 1 + h;
    }


    public static void main(String[] args)
            throws InvalidPositionException, EmptyTreeException,
            BoundaryViolationException, NonEmptyTreeException {

        LinkedBinaryTree <Integer> T = new LinkedBinaryTree<Integer>();
        Random rand = new Random();

        int n = 100;
        int j;

        if (T.isEmpty())
            T.addRoot(rand.nextInt(1000));

        System.out.println("The root element is " + T.root().getElement());

        Position<Integer> p = T.root();

        for (int i = 0; i <= n; i++) {
            j = rand.nextInt(1000);
            if ( j % 2 == 0) {
                T.insertLeft(p, j);
                p = T.left(p);
            }
            else {
                T.insertRight(p, j);
                p = T.right(p);
            }
        }

        System.out.println("The size of tree is " + T.size());
        System.out.println();
        System.out.println("The height1 is " + T.height1(T));
        System.out.println();
        System.out.println("The height2 is " + T.height2(T, T.root()));
        System.out.println();
        System.out.println("The depth of the root is " + T.depth(T, T.root()));
        System.out.println();
        System.out.println("We print all elements in the tree");

        int count = 0;
        for (Integer in: T) {
            System.out.print(in + " ");
            ++count;
            if(count % 10==0)
                System.out.println();
        }
        System.out.println();
        System.out.println();

    }
}

class InvalidPositionException extends Exception {

    // Constructor method
    public InvalidPositionException(String str) {
        super(str);
    }
}

class BoundaryViolationException extends Exception {

    // Constructor method
    public BoundaryViolationException(String str) {
        super(str);
    }
}

class EmptyTreeException extends Exception {

    // Constructor method
    public EmptyTreeException(String str) {
        super(str);
    }
}

class NonEmptyTreeException extends Exception {

    // Constructor method
    public NonEmptyTreeException(String str) {
        super(str);
    }
}