//-------------nested ElementIterator Class---------------
/** This class adapts the iteration produced by position() to return elements */
private class ElementIterator implements Iterator<E> {
    Iterator<Position<E>> posIterator = positions().iterator();
    public boolean hasNext() { return posIterator.hasNext(); }
    public E next() { return posIterator.next().getElement(); }
    public void remove() { posIterator.remove(); }
}

/** Returns an iterator of the elements stored in the tree. */
public Iterator<E> iterator() { return new ElementIterator(); }

// The method of traversal could be varied.
public Iterable<Position<E>> positions() { return preorder(); }


// preorder traversal
// can be used as part of (Abstract)Tree class
/** Adds positions of the subtree rooted at Position p to the given snapshot by preorder traversal. */
private void preorderSubtree(Position<E> p, List<Position<E>> snapshot) {
    snapshot.add(p);
    for (Position<E> c: children(p))
        preorderSubtree(c, snapshot);
}
/** Returns an iterable collection of positions of thetree by preorder traversal. */
public Iterable<Position<E>> preorder() {
    List<Position<E>> snapshot = new ArrayList<>();
    if (!isEmpty())
        preorderSubtree(root(), snapshot);
    return snapshot;
}


// postorder traversal
/** Adds positions of the subtree rooted at Position p to the given snapshot by postorder traversal. */
private void postorderSubtree(Position<E> p, List<Position<E>> snapshot) {
    for (Position<E> c: children(p))
        postorderSubtree(c, snapshot);
    snapshot.add(p);
}
/** Returns an iterable collection of positions of thetree by preorder traversal. */
public Iterable<Position<E>> postorder() {
    List<Position<E>> snapshot = new ArrayList<>();
    if (!isEmpty())
        postorderSubtree(root(), snapshot);
    return snapshot;
}


// inorder traversal
/** Adds positions of the subtree rooted at Positon p to the given shopshot by inorder traversal. */
private void inorderSubtree(Position<E> p, List<Position<E>> snapshot) {
    if (hasLeft(p)) 
        inorderSubtree(left(p), snapshot);
    snapshot.add(p);
    if (hasRight(p)) 
        inorderSubtree(right(p), snapshot);
}
/** Returns an iterable collection of positions of the tree by inorder traversal */
public Iterable<Position<E>> inorder() {
    List<Position<E>> snapshot = new ArrayList<>();
    if(!isEmpty())
        inorderSubtree(root(), snapshot);
    return snapshot;
}
/** Overrides positions to change traversal order. */
public Iterable<Position<E>> position() {
    return inorder();  // can use other traversal mehtods
}

// breadth first order
/** Returns an iterable collection of positions of the tree in breadth-first order. */
public Iterable<Position<E>> breadthfirst() {
    List<Position<E>> snapshot = new ArrayList<>();
    if (!isEmpty()) {
        Queue<Position<E>> fringe = new LinkedQueue<>();
        fringe.enqueue(root());
        while(!fringe.isEmpty()) {
            Positon<E> p = fringe.dequeue();
            snapshot.add(p);
            for (Position<E> c: children(p))
                fringe.enqueue(c);
    }
    return snapshot;
 }
