package List;


///////////////// Sample Array Iterator ////////////////////
//------------ nested ArrayIterator class ------------------
private class ArrayIterator implements Iterator<E> {

    private int j = 0;
    private boolean removable = false;

    /** Indicates whether the iterator has a next object. */
    public boolean hasNext() { return j<size; }

    /** Returns the next position in the iterator. */
    public E next() throws NoSuchElementException {
        if (j == size) throw new NoSuchElementException("No next element.");
        removable = true;
        return data[j++];
    }

    /** Removes the element returned by most recent call to next. */
    public void remove() throws IllegalStateException {
        if (!removable) throw new IllegalStateException("Nothing to remove.");
        ArrayList.this.remove(j-1);
        j--;
        removable = false;
    }

}  //------------ end of nested ArrayIterator class ------------
public Iterator<E> iterator() { return new Arrayiterator(); }


////////////////////////////////////////////////////////////////////////////
////////////////// A sample Iterator for lists below ///////////////////////


//----------- nested PositionIterator class ----------------
private class PositionIterator<Position<E>> implements Iterator<Position<E>> {

    private Position<E> cursor = first();  // position of the next element to be reported
    private Position<E> recent = null;  // position of last reported element

    public boolean hasNext() { return (cursor != null);}

    /** Returns the next position in the iterator. */
    public Position<E> next() throws NoSuchElementException {
        if (cursor == null) throw new NoSuchElementException("No element left");
        recent = cursor;
        cursor = after(cursor);
        return recent;
    }

    /** Removes the element returned by most recent call to next. */
    public void remove() throws IllegalStateException {
        if(recent == null) throw new IllegalStateException("Nothing to remove");
            DoublyLinkedList.this.remove(recent);
            recent = null;
        }
}  //------------ end of nested PositionIterator class -----------

//---------- nested PositionIterable class ------------
private class PositionIterable implements Iterable<Position<E>> {
    public Iterator<Position<E>> iterator() { return new PositionIterator(); }
}  //------------ end of nested PositionIterable class ------------

    /**
     * positions() method
     * Returns an iterable representation of the lsit's positions.
     */
    public Iterable<Position<E>> positions() {
        return new PositionIterable();
    }

//-------------- nested ElementIterator class -------------
private class ElementIterator<E> implements Iterator<E> {
    Iterator<Positon<E>> posIterator = new PositionIterator();
    public boolean hasNext() { return posIterator.hasNext(); }
    public E next() { return posIterator.next().getElement(); }
    public void remove() { posIterator.remove();}
}

    /** Returns an iterator of the elements stored in the list. */
    public Iterator<E> iterator() { return new ElementIterator(); }



////////////////////////////////////////////////////////////////////
//////////////////// Another implementation ///////////////////////
// Create an iterator for a given list

    /**
     * iterator() method
     * Returns an iterator of all the elements in the list.
     */
    public Iterator<E> iterator() {
        return new ElementIterator<E>(this);
    }

    /**
     * positions() method
     * Returns an iterable collection of all the nodes in the list.
     */
    public Iterable<Position<E>> positions() {     // create a list of posiitons
        PositionList<Position<E>> P = new NodePositionList<Position<E>>();
        if (!isEmpty()) {
            Position<E> p = first();
            while (true) {
                P.addLast(p); // add position p as the last element of list P
                if (p == last())
                    break;
                p = next(p);
            }
        }
        return P; // return P as our Iterable object
    }

public class ElementIterator<E> implements java.util.Iterator<E> {

    protected PositionList<E> list;	// the underlying list
    protected Position<E> cursor; 	// the next position

    /**
     * Creates an element iterator over the given list.
     */
    public ElementIterator(PositionList<E> L) {
        list = L;
        cursor = (list.isEmpty()) ? null : list.first();
    }

    public boolean hasNext() {
        return (cursor != null);
    }

    public E next()
            throws NoSuchElementException {
        if (cursor == null)
            throw new NoSuchElementException("No next element");

        E toReturn = cursor.element();
        cursor = (cursor == list.last()) ? null : list.next(cursor);

        return toReturn;
    }

    public void remove() {
        throw new OperationNotSupportedException("Remove operation not supported!");
    }

}
