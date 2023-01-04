package PriorityQueue;

/**
 * Implements Priority Queue with a sorted list.
 */

import PriorityQueue.PositionalList.*;

import java.util.Comparator;

public class SortedPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {

    // primary collection of priority queue entries
    private PositionalList<Entry<K,V>> list = new LinkedPositionalList<>();

    /** Constructors */
    // use default comparator
    public SortedPriorityQueue() { super(); }
    // use the given comparator
    public SortedPriorityQueue(Comparator<K> comp) { super(comp); }

    /** Returns the number of entry in this priority queue.*/
    public int size() { return list.size(); }

    /** Returns whether the priority queue is empty. */
    public boolean isEmpty() { return size()==0; }

    /** Inserts and returns an entry with key k and value v. */
    public Entry<K,V> insert(K k, V v) throws IllegalArgumentException {
        checkKey(k);
        Entry<K,V> en = new PQEntry<>(k, v);
        Position<Entry<K,V>> walk = list.last();
        // find the smallest key
        while (walk != null && compare(en, walk.getElement())<0)
            walk = list.before(walk);
        if (walk == null)
            list.addFirst(en);
        else
            list.addAfter(walk, en);
        return en;

    }

    /** Removes and returns the entry with the smallest key. */
    /** Returns null if the priority queue is empty. */
    public Entry<K,V> removeMin() {
        if (list.isEmpty()) return null;
        return list.remove(list.first());
    }

    /** Returns the entry with the smallest key without removing it. */
    /** Returns null if the priority queue is empty. */
    public Entry<K,V> min() {
        if (list.isEmpty()) return null;
        return list.first().getElement();
    }
}