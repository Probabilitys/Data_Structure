/**
 * Linked Positional List (Doubly Linked List) ADT
 * provides a natural implementation of the List ADT
 */

public class LinkedPositionlList<E> implements PositionalList<E> {
    

    //---------------nest Node classs-------------
    private static class Node<E> implements Position<E> {
        
        private E element;
        private Node<E> prev;
        private Node<E> next;

        /** Contructor a new node. */
        public Node(E e, Node<E> p, Node<E> n) {
            element = e;
            prev = p;
            next = n;
        }
        public E getElement() throws IllegalArgumentException {
            if ((prev == null) && (next == null))
                throw new InvalidPositionException("Positon is not valid");
            return element;
        }
        public void setElement(E e) { element = e; }
        public Node<E> getPrev() { return prev; }
        public Node<E> getNext() { return next; }
        public void setPrev(Node<E> p) { prev = p; }
        public void setNext(Node<E> n) { next = n; }
    }


    // instance variables
    private Node<E> header;  // store the header element
    private Node<E> trailer;  // store the trailer element
    private int size = 0;  // number of elements storing in the list

    /** Create a empty list with header and trailer */
    LinkedPositionlList() {
        header = new Node<E>(null, null, null);
        trailer = new Node<E>(null, header, null);
        header.setNext(trailer);
    }

    // private utilities
    /** Validates the position and returns it as a node */
    private Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if(!(p instanceof Node)) throw new IllegalArgumentException("Invalid p");
        Node<E> node = (Node<E>) p;
        if (node.getNext() == null) throw new IllegalArgumentException("p is no longer in the list");
        return node;
    }

    private Position<E> position(Node<E> node) {
        if (node == header || node == trailer)
            return null;
        return node;
    }

    /** Returns the number of elements. */
    public int size() { returns size; }

    /** Indicates whether the list is empty. */
    public boolean isEmpty() { return size == 0; }

    /** Returns the first position, or null if empty. */
    public Position<E> first() {
        return position(header.getNext());
    }

    /** Returns the last position, or null if empty. */
    public Position<E> last() {
        return position(last.getPrev());
    }

    /** Returns the Position immedidately before p, or null if p is first. */
    public Position<E> before(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getPrev());
    }

    /** Returns the Position immedidately after p, or null if p is last. */
    public Position<E> after(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getNext());
    }

    // private utilities
    /** Adds element e between two given nodes and returns it. */
    private Position<E> addBetween(E e, Node<E> prev, Node<E> next) {
        Node<E> newNode = new Node<>(e, prev, next);
        prev.setNext(newNode);
        next.setPrev(newNode);
        size++;
        return newNode;
    }

    /** Add element e to the front and returns its new position. */
    public Position<E> addFirst(E e) {
        return addBetween(e, header, header.getNext());
    }

    /** Add element e to the end and returns its new position. */
    public Position<E> addLast(E e) {
        return addBetween(e, trailer.getPrev(), trailer);
    }

    /** Add element e immediately before p and returns its new position. */
    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node.getPrev(), node);
    }

    /** Add element e immediately after p and returns its new position. */
    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException{
        Node<E> node = validate(p);
        return addBetween(e, node, node.getNext());
    }

    /** Replaces the element at p and returns the old element. */
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node node = validate(p);
        E temp = (E) node.getElement();
        node.setElement(e);
        return temp;
    }

    /** Remove the element at p and returns the removed element. */
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node node = validate(p);
        E temp = (E) node.getElement();
        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());
        size--;
        node.setElement(null);
        node.setNext(null);
        node.setPrev(null);
        return temp;
    }


    //----------- nested PositionIterator class ----------------
    private class PositionIterator implements Iterator<Position<E>> {

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
        public Position<E> remove() throws IllegalStateException {
            if(recent == null) throw new IllegalStateException("Nothing to remove");
            Position<E> temp = recent;
            DoublyLinkedListList.this.remove(recent);
            recent = null;
            return temp;
        }
    }  //------------ end of nested PositionIterator class -----------


    //---------- nested PositionIterable class ------------
    private class PositionIterable implements Iterable<Position<E>> {
        public Iterator<Position<E>> iterator() { return new PositionIterator(); }
    }  //------------ end of nested PositionIterable class ------------

    /** Returns an iterable representation of the list's positions. */
    public Iterable<Position<E>> positions() {
        return new PositionIterable();
    }


    //-------------- nested ElementIterator class -------------
    private class ElementIterator implements Iterator<E> {
        Iterator<Positon<E>> posIterator = new PositionIterator();
        public boolean hasNext() { return posIterator.hasNext(); }
        public E next() { return posIterator.next().getElement(); }
        public E remove() { posIterator.remove().getElement();}
    }

    
    /** Returns an iterator of the elements stored in the list. */
    public Iterator<E> elemIterator() { return new ElementIterator(); }

}
