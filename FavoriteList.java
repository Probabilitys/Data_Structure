import java.util.ArrayList;

public class FavoriteList<E> {

    /** ----------- nested class for entry ------------- */
    private class Entry<E> {
        private int count;
        private E value;
        public Entry(E e) {
            count = 1;
            value = e;
        }
        public int getKey() { return count; }
        public void setKey(int c) { count = c; }
        public E getValue() { return value; }
        public void setValue(E e) { value = e; }
    }
    /** ----------- end nested class Entry ------------- */

    protected PositionList<Entry<E>> fList; // List of entries

    /** Constructor; O(1) time */
    public FavoriteList() { fList = new NodePositionList<Entry<E>>(); }

    /** Returns the number of elements in this list. */
    public int size() { return fList.size(); }

    /** Returns whether the list is empty. */
    public boolean isEmpty() { return fList.isEmpty(); }

    /** 
     * Private method to find an element, returns the position contains the element. 
     * Returns null if element is not found or empty list.
     */
    private Position<Entry<E>> find(E e) throws EmptyListException, 
    BoundaryViolationException, InvalidPositionException {

        try {
            Position<Entry<E>> current = fList.first();
            while(current != null) {
                if (current.getElement().getValue().equals(e)) {
                    return current;
                }
                current = fList.after(current);
            }
            return null;
        } catch(EmptyListException err) {
            return null;
        }

    }

    /** Return an iterable collection of k most accessed elements. */
    public Iterable<E> top(int k) throws EmptyListException, 
        BoundaryViolationException, InvalidPositionException {

        if (k > fList.size()) 
            throw new BoundaryViolationException("The input has excessed the size.");

        NodePositionList<Entry<E>> C = new NodePositionList<Entry<E>>();
        Position<Entry<E>> current = fList.first();
        // copy all elements to the list C
        while (current != null) {
            C.addLast(current.getElement());
            current = fList.after(current);
        }
        // an arraylist to contain the elements to be returned
        ArrayList<E> temp = new ArrayList<E>();
        // find the max from list C then remove it from C
        for (int i = 0; i < k; i++){
            Position<Entry<E>> walk = C.first();
            Position<Entry<E>> max = C.first();
            while(walk != null) {
                if (walk.getElement().getKey() > max.getElement().getKey()) {
                    max = walk;
                }
                walk = C.after(walk);
            }
            temp.add(max.getElement().getValue());
            C.remove(max);
        }
        return temp;
        
    }

    /**  
     * Access the element e, incrementing its access count, and adding it to 
     * the favorite list if it is not already present 
     */
    public void access(E e) throws EmptyListException, 
        BoundaryViolationException, InvalidPositionException {

        Position<Entry<E>> current = find(e);
        if (current != null) {
            Entry<E> entry = current.getElement();
            entry.setKey(entry.getKey()+1);
        } else {
            fList.addLast(new Entry<E>(e));
        }
    }

    /** Remove element e from the favorite list, provided it is already there. */
    public void remove(E e) throws EmptyListException, 
        BoundaryViolationException, InvalidPositionException {

        Position<Entry<E>> current = find(e);

        if (current != null) {
            fList.remove(current);
        } 

    }

    public static void main(String[] args) throws EmptyListException,
    BoundaryViolationException, InvalidPositionException {

        FavoriteList<String> myList = new FavoriteList<String>();
        // access e1 three times
        myList.access("e1");
        myList.access("e1");
        myList.access("e1");
        // access e2 twice
        myList.access("e2");
        myList.access("e2");
        // access e1 once
        myList.access("e3");
        // display the result
        for (String i: myList.top(3)) {
            System.out.println(i);
        }

        // then remove e2
        myList.remove("e2");
        // then access e3 three times
        myList.access("e3");
        myList.access("e3");
        myList.access("e3");
        System.out.println("After removing e2 and accessing e3:");
        // display the result
        for (String i: myList.top(2)) {
            System.out.println(i);
        }
    }

}