package PriorityQueue;

/**
 * Implements priority queue using an array-based heap.
 */

import java.util.ArrayList;
import java.util.Comparator;

public class HeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

    /** primary collection of priority queue entries */
    protected ArrayList<Entry<K,V>> heap = new ArrayList<>();

    /** Constructor using default comparator. */
    public HeapPriorityQueue() { super(); }

    /** Constructor using a given comparator. */
    public HeapPriorityQueue(Comparator<K> comp) { super(comp); }

    /** Creates a priority queue initialized with the given key-value pairs. */
    public HeapPriorityQueue(K[] keys, V[] values) {
        super();
        for (int j=0; j<Math.min(keys.length, values.length); j++)
            heap.add(new PQEntry<>(keys[j], values[j]));
        heapify();
    }

    /** Performs a bottom-up construction of the heap in linear time. */
    protected void heapify() {
        int startIndex = parent(size() - 1);
        for (int j=startIndex; j>=0; j--)
            downheap(j);
    }

    // protected utilities
    protected int parent(int j) { return (j-1)/2; }
    protected int left(int j) { return 2*j+1; }
    protected int right(int j) { return 2*j+2; }
    protected boolean hasLeft(int j) { return left(j)<heap.size(); }
    protected boolean hasRight(int j) { return right(j)<heap.size(); }

    /** Swaps entries at indices i and j. */
    protected void swap(int i, int j) {
        Entry<K,V> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    /** Upheap operation to restore the heap property. */
    protected void upheap(int j) {
        while (j>0) {  // continue until reaching root
            int p = parent(j);
            if (compare(heap.get(j), heap.get(p)) >= 0) break;
            swap(j, p);
            j = p;
        }
    }

    /** Downheap operation to restore the heap propety. */
    protected void downheap(int j) {
        while (hasLeft(j)) {  // continue to bottom
            int leftIndex = left(j);
            int smallChildIndex = leftIndex;
            if (hasRight(j)) {
                int rightIndex = right(j);
                if (compare(heap.get(leftIndex), heap.get(rightIndex)) > 0)
                    smallChildIndex = rightIndex;
            }
            if (compare(heap.get(smallChildIndex), heap.get(j)) >= 0)
                break;
            swap(j, smallChildIndex);
            j = smallChildIndex;  // continue at position of the child
        }
    }

    // public  methods
    /** Returns the number of items in the priority queue. */
    public int size() { return heap.size(); }
    /** Returns the entry with smallest key if any. */
    public Entry<K,V> min() {
        if (heap.isEmpty()) return null;
        return heap.get(0);
    }
    /** Inserts a key-value pair and returns the entry created. */
    public Entry<K,V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        Entry<K,V> newEntry = new PQEntry<>(key, value);
        heap.add(newEntry);
        upheap(heap.size() - 1);
        return newEntry;
    }
    /** Removes and returns the entry with smallest key (if any). */
    public Entry<K,V> removeMin() {
        if (heap.isEmpty()) return null;
        Entry<K,V> min = heap.get(0);
        swap(0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        downheap(0);
        return min;
    }
}