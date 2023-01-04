package PriorityQueue;

import java.util.Comparator;

public class HeapAdaptablePriorityQueue<K,V> extends HeapPriorityQueue<K,V>
        implements AdaptablePriorityQueue<K,V> {

    //------------nested AdaptablePQEntry class------------
    /** Extends PQEntry and include location information. */
    protected static class AdaptablePQEntry<K,V> extends PQEntry<K,V> {
        private int index;
        // constructor method to set key, value and indexf
        public AdaptablePQEntry(K key, V value, int j) {
            super(key, value);
            index = j;
        }
        public int getIndex() { return index; }
        public void setIndex(int j) { index = j; }
    }  //--------end of nested AdaptablePQEntry class-------

    /** Constructor uses default comaprator. */
    public HeapAdaptablePriorityQueue() { super(); }

    /** Constructor uses the given comparator. */
    public HeapAdaptablePriorityQueue(Comparator<K> comp) { super(comp); }

    // protected utilities
    /** Check the location of an entry. */
    protected AdaptablePQEntry<K,V> validate(Entry<K,V> entry)
            throws IllegalArgumentException {
        if(!(entry instanceof AdaptablePQEntry))
            throw new IllegalArgumentException("Invalid entry.");
        AdaptablePQEntry<K,V> locator = (AdaptablePQEntry<K,V>) entry;
        int j = locator.getIndex();
        if (j >= heap.size() || heap.get(j) != locator)
            throw new IllegalArgumentException("Invalid entry.");
        return locator;
    }

    /** Swap two entries at indices i and j. */
    protected void swap(int i, int j) {
        super.swap(i, j);
        ((AdaptablePQEntry<K,V>) heap.get(i)).setIndex(i);
        ((AdaptablePQEntry<K,V>) heap.get(j)).setIndex(j);

    }

    /** Restores the heap property. */
    protected void bubble(int j) {
        if (j>0 && compare(heap.get(j), heap.get(parent(j))) < 0)
            upheap(j);
        else
            downheap(j);
    }

    /** Inserts a key-value pair and returns teh new entry. */
    public Entry<K,V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        Entry<K,V> newEntry = new AdaptablePQEntry<>(key, value, heap.size());
        heap.add(newEntry);
        upheap(heap.size()-1);
        return newEntry;
    }

    /** Removes the entry e. */
    public void remove(Entry<K,V> e) throws IllegalArgumentException {
        AdaptablePQEntry<K,V> locator = validate(e);
        int j = locator.getIndex();

        if (j == heap.size() - 1)
            heap.remove(heap.size() - 1);  // remove the entry if it is at last position
        else {
            swap(j, heap.size() - 1);  // swap entry to last position
            heap.remove(heap.size() - 1);  // remove it
            bubble(j);  // restore heap property
        }
    }

    /** Replaces the key of existing entry e with k. */
    public void replaceKey(Entry<K,V> e, K k) throws IllegalArgumentException {
        AdaptablePQEntry<K,V> locator = validate(e);
        checkKey(k);
        locator.setKey(k);
        bubble(locator.getIndex());
    }

    /** Replaces the value of existing entry e with v. */
    public void replaceValue(Entry<K,V> e, V v) throws IllegalArgumentException {
        AdaptablePQEntry<K,V> locator = validate(e);
        locator.setValue(v);
    }
}