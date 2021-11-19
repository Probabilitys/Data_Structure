/** Realization of an indexed list by means of an array, which is doubled
  * when the size of the indexed list exceeds the capacity of the array.
  */

public class ArrayIndexList<E> implements IndexList<E> {

    private E[] A;	      // an array stores the elements
    private int capacity = 1;  // initial the length
    private int size = 0;	      // numbers of elements

    public ArrayIndexList() {
        A = (E[]) new Object[capacity];  // initialize the array
    }

    public int size() { return size; }

    public boolean isEmpty() { return size() == 0; }

    /** Inserts an element e at index r */
    public void add(int r, E e) throws IndexOutOfBoundsException {
        checkIndex(r, size() + 1);
        if (size() == capacity) {  // need to double the size
            capacity *= 2;
            E[] B =(E[]) new Object[capacity];
            for (int i=0; i<size; i++)
                B[i] = A[i];
            A = B;
        }
        for (int i=size() -1; i>=r; i--)	// shift elements up
            A[i+1] = A[i];
        A[r] = e;
        size++;
    }

    public E get (int i) throws IndexOutOfBoundsException {
        checkIndex(i, size());
        return A[i];
    }

    /** Removes the element stored at the given index. */
    public E remove(int r) throws IndexOutOfBoundsException {
        checkIndex(r, size());
        E temp = A[r];
        for (int i=r; i<size-1; i++)	// shift elements down
            A[i] = A[i+1];
        size--;
        return temp;
    }

    public E set(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i, size());;
        E temp = A[i];
        A[i] = e;
        return temp;
    }

    private void checkIndex(int r, int s) {
        if(r < 0 || r > s - 1) throw new IndexOutOfBoundsException
        ("Out of range of Index");
    }

    public static void main(String[] args) {
        ArrayIndexList<Integer> S = new ArrayIndexList<Integer>();
        for (int i = 0; i <= 10; i++)
            S.add(i, i + 1);
        for (int i = 10; i > 0; i--)
            System.out.println (S.remove(i));
    }

}