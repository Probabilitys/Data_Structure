package PriorityQueue;

/**
 * An implementation of Priority Queue with a unsorted list.
 */

import PriorityQueue.PositionalList.*;

import java.util.Comparator;

public class UnsortedPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {

    // Collection of entries
    private PositionalList<Entry<K,V>> list = new LinkedPositionalList<>();

    // Constructors
    public UnsortedPriorityQueue() { super(); }
    public UnsortedPriorityQueue(Comparator<K> comp) { super(comp); }

    /** Returns the number of entry in this priority queue.*/
    public int size() { return list.size(); }

    /** Returns whether the priority queue is empty. */
    public boolean isEmpty() { return size()==0; }

    /** Inserts and returns an entry with key k and value v. */
    public Entry<K,V> insert(K k, V v) throws IllegalArgumentException {
        checkKey(k);
        Entry<K,V> en = new PQEntry<>(k, v);
        list.addLast(en);
        return en;
    }

    /** Removes and returns the entry with the smallest key. */
    /** Returns null if the priority queue is empty. */
    public Entry<K,V> removeMin() {
        return isEmpty() ? null : list.remove(findMin());
    }

    /** Returns the entry with the smallest key without removing it. */
    /** Returns null if the priority queue is empty. */
    public Entry<K,V> min() {
        return isEmpty() ? null : findMin().getElemment();
    }

    /** Nonpublic methods to find and return the position of teh entry with the minimal key. */
    private Position<Entry<K,V>> findMin() {
        Position<Entry<K,V>> min = list.first();
        for (Position<Entry<K,V>> p: list.positions()) {
            if (compare(min.getElemment() ,p.getElemment())<0)
                min = p;
        }
        return min;
    }
}
