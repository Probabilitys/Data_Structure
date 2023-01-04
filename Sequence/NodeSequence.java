package Sequence;

/**
 * This class implements the Sequence class with node structure
 */

public class NodeSequence<E> extends LinkedPositionalList<E>
        implements Sequence<E> {

    /** Private method to check if an index is valid. */
    private void checkIndex(int i) throws BoundaryViolationException {
        if (i >= size() || i < 0)
            throw new BoundaryViolationException("The index is not valid.");
    }

    /** Returns the position containing the element at the given index. */
    public Position<E> atIndex(int i)
            throws BoundaryViolationException {
        checkIndex(i);
        Position<E> current = header.getNext();
        try {
            for (int index = 0; index < i; index++) {
                current = after(current);
            }
            return current;
        } catch(InvalidPositionException err) {
            throw new BoundaryViolationException("The index is not valid.");
        }
    };

    /** Returns the index of a given position. */
    public int indexOf(Position<E> p) throws InvalidPositionException {
        checkPosition(p);
        int i = 0;
        Position<E> current = header.getNext();
        while (current != p) {
            current = after(current);
            i++;
        }
        return i;
    };

    /** Inserts an element e to be at index i, shifting all elements after this. */
    public void addAtIndex(int i, E e)
            throws BoundaryViolationException {
        checkIndex(i);
        Position<E> current = atIndex(i);
        try {
            addBefore(current, e);
        } catch(InvalidPositionException err) {
            throw new BoundaryViolationException("The index is not valid.");
        }

    };

    /** Returns the element at index i, without removing it. */
    public E getAtIndex(int i)
            throws BoundaryViolationException {
        checkIndex(i);
        Position<E> current = atIndex(i);
        return current.getElement();

    };

    /** Removes and returns the element at index i, shifting the elements after this. */
    public E removeAtIndex(int i)
            throws BoundaryViolationException {
        checkIndex(i);
        Position<E> current = atIndex(i);
        try {
            E temp = (E) current.getElement();
            remove(current);
            return temp;
        } catch (InvalidPositionException err) {
            throw new BoundaryViolationException("The index is not valid.");
        }

    };

    /** Replaces the element at index i with e, returning the previous element at i. */
    public E setAtIndex(int i, E e)
            throws BoundaryViolationException {
        checkIndex(i);
        Position<E> current = atIndex(i);
        try {
            E temp = (E) current.getElement();
            set(current, e);
            return temp;
        } catch (InvalidPositionException err) {
            throw new BoundaryViolationException("The index is not valid.");
        }
    };

}