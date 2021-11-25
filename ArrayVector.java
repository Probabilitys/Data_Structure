public class ArrayVector<E> implements IndexList<E> {

    private E[] A;
    private int capacity = 1;
    private int size = 0;
    
    public ArrayVector() {
        A = (E[]) new Object[capacity];
    }

    /** Initialize array A with length of a give integer. */
    public ArrayVector(int c) {
        capacity = c;
        A = (E[]) new Object[capacity];
    }
    
    /** Returns the number of elements in this list */
    public int size() {
        return size;
    }

    /** Returns whether the list is empty */
    public boolean isEmpty() {
        return size==0;
    }

    /** Returns the element at index i without removing it */
    public E elemAtRank(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size());
        return A[i];
    }

    /** Replaces the element at index i with e and returns the previous element */
    public E replaceAtRank(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i, size());
        E temp = A[i];
        A[i] = e;
        return temp;
    }

    /** Inserts an element e at index i */
    @SuppressWarnings("unckecked")
    public void insertAtRank(int r, E e) throws IndexOutOfBoundsException {
        checkIndex(r, size()+1);
        if (size() == capacity) {
            capacity *= 2;
            E[] S = (E[]) new Object[capacity];
            for (int i=0; i<size; i++) {
                S[i] = A[i];
            }
            A = S;
        }
        for (int i=size(); i>r; i--) {
            A[i] = A[i-1];
        }
        A[r] = e;
        size++;
    }

    /** Removes and returns the element at index i */
    public E removeAtRank(int r) throws IndexOutOfBoundsException {
        checkIndex(r, size());
        E temp = A[r];
        for (int i=r; i<size()-1; i++) {
            A[i] = A[i+1];
        }
        A[size()-1] = null;
        size--;
        return temp;
    }

    /** Private method to check if the given index i is within the range of 0 - s. */
    private void checkIndex(int i, int s) throws IndexOutOfBoundsException {
        if (i<0 || i >= s) 
            throw new IndexOutOfBoundsException("Invalid index.");
    }
}