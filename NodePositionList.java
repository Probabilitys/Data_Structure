/**
 * This class implemented the PositionList interface with node structure
 */

public class NodePositionList<E> implements PositionList<E> {


    //---------------nest Node classs-------------
    protected static class Node<E> implements Position<E> {
        
        private E element;
        private Node<E> prev;
        private Node<E> next;

        /** Contructor a new node. */
        public Node(E e, Node<E> p, Node<E> n) {
            element = e;
            prev = p;
            next = n;
        }

        public E getElement() { return element; }
        public void setElement(E e) { element = e; }
        public Node<E> getPrev() { return prev; }
        public Node<E> getNext() { return next; }
        public void setPrev(Node<E> p) { prev = p; }
        public void setNext(Node<E> n) { next = n; }
    }


    // instance variables
    protected Node<E> header;  // store the header element
    protected Node<E> trailer;  // store the trailer element
    protected int size = 0;  // number of elements storing in the list

    /** Create a empty list with header and trailer */
    public NodePositionList() {
        header = new Node<E>(null, null, null);
        trailer = new Node<E>(null, header, null);
        header.setNext(trailer);
    }

    /** Private method to check if a provided position is valid. */
    protected Node<E> checkPosition(Position<E> p) throws InvalidPositionException {
        if(!(p instanceof Node)) 
            throw new InvalidPositionException("Invalid position.");
        Node<E> node = (Node<E>) p;
        if (node.getNext() == null) 
            throw new InvalidPositionException("p is not in the list.");
        return node;
    }

    protected Position<E> position(Node<E> node) {
        if (node == header || node == trailer)
            return null;
        return node;
    }

    /** Returns the number of elements in the list. */
    public int size() { return size; }

    /** Returns whether the list is empty. */
    public boolean isEmpty() { return size == 0; }

    /** Returns the first position. */
    public Position<E> first() throws EmptyListException {
        if (isEmpty())
            throw new EmptyListException("The list has no element.");
        return position(header.getNext());
    }

    /** Returns the last position. */
    public Position<E> last() throws EmptyListException {
        if (isEmpty())
            throw new EmptyListException("The list has no element.");
        return position(trailer.getPrev());
    }

    /** Returns the Position immedidately before p, or null if p is first. */
    public Position<E> before(Position<E> p) throws InvalidPositionException {
        Node<E> node = checkPosition(p);
        return position(node.getPrev());
    }

    /** Returns the Position immedidately after p, or null if p is last. */
    public Position<E> after(Position<E> p) throws InvalidPositionException {
        Node<E> node = checkPosition(p);
        return position(node.getNext());
    }

    // private method
    /** Adds element e between two given nodes and returns it. */
    protected Position<E> addBetween(E e, Node<E> prev, Node<E> next) {
        Node<E> newNode = new Node<E>(e, prev, next);
        prev.setNext(newNode);
        next.setPrev(newNode);
        size++;
        return newNode;
    }

    /** Add element e to the front and returns its new position. */
    public Position<E> addFirst(E e) {
        Position<E> newPosition = addBetween(e, header, header.getNext());
        return newPosition;
    }

    /** Add element e to the end and returns its new position. */
    public Position<E> addLast(E e) {
        Position<E> newPosition = addBetween(e, trailer.getPrev(), trailer);
        return newPosition;
    }

    /** Add element e immediately before p and returns its new position. */
    public Position<E> addBefore(Position<E> p, E e) throws InvalidPositionException {
        Node<E> node = checkPosition(p);
        Position<E> newPosition = addBetween(e, node.getPrev(), node);
        return newPosition;
    }

    /** Add element e immediately after p and returns its new position. */
    public Position<E> addAfter(Position<E> p, E e) throws InvalidPositionException{
        Node<E> node = checkPosition(p);
        Position<E> newPosition = addBetween(e, node, node.getNext());
        return newPosition;
    }

    /** Replaces the element at p and returns the old element. */
    public E set(Position<E> p, E e) throws InvalidPositionException {
        Node<E> node = checkPosition(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }

    /** Remove the element at p and returns the removed element. */
    public E remove(Position<E> p) throws InvalidPositionException {
        Node<E> node = checkPosition(p);
        size--;
        E temp = (E) node.getElement();
        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());
        node.setElement(null);
        node.setNext(null);
        node.setPrev(null);
        return temp;
    }
    
    /**
     * Returns a textual representation of a given node list
     */
    public static <E> String toString(PositionList<E> l) {
       Iterator<E> it = l.iterator();
       String s = "[";
       while (it.hasNext()) {
	  s += it.next();	// implicit cast of the next element to String
	  if (it.hasNext())
	      s += ", ";
       }
       return s + "]";
    }

    public String toString () {
	 return toString (this);
    }


    //----------- nested PositionIterator class ----------------
    private class PositionIterator implements Iterator<Position<E>> {

        protected Position<E> cursor;  // position of the next element to be reported

        public PositionIterator() {
            cursor = isEmpty() ? null : position(header.getNext());
        }

        public boolean hasNext() { return (cursor != null);}

        /** Returns the next position in the iterator. */
        public Position<E> next() throws BoundaryViolationException {
            if (cursor == null) throw new BoundaryViolationException("No element in the next.");
            Position<E> temp = cursor;
            try {
                cursor = after(cursor);
                return temp;
            } catch(InvalidPositionException err) {
                throw new BoundaryViolationException("No element in the next.");
            }
        }

    }  //------------ end of nested PositionIterator class -----------


    //----------- nested ElementIterator class ----------------
    private class ElementIterator implements Iterator<E> {

        protected Position<E> cursor;  // position of the next element to be reported

        public ElementIterator() {
            cursor = isEmpty() ? null : position(header.getNext());
        }

        public boolean hasNext() { return (cursor != null);}

        /** Returns the next position in the iterator. */
        public E next() throws BoundaryViolationException {
            if (cursor == null) throw new BoundaryViolationException("No element in the next.");
            E temp = cursor.getElement();
            try {
                cursor = after(cursor);
                return temp;
            } catch(InvalidPositionException err) {
                throw new BoundaryViolationException("No element in the next.");
            }
        }

    }  //------------ end of nested ElementIterator class -----------


    /** Returns an iterator for all the elements. */
    public Iterator<Position<E>> positions() {
        return new PositionIterator();
    }

    /** Return an iterator for all the positions. */
    public Iterator<E> elements() {
        return new ElementIterator();
    }


}
