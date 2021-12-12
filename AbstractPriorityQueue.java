/**
 * An abstract class to assist implementations of the Priority Queue.
 */

public abstract class AbstractPriorityQueue<K,V> implements PriorityQueue<K,V> {
    
    //-----------nested PQEntry class-----------
    protected static class PQEntry<K,V> implements Entry<K,V> {
        private K k;  // key
        private V v;  // value
        public PQEntry(K key, V value) {
            k = key;
            v = value;
        }
        // accesor methods
        public K getKey() { return k; }
        public v getValue() { return v; }
        // mutator methods
        protected void setKey(K key) { k = key; }
        protected void setValue(V value) { v = value; }
    }

    // instance variables
    private Comparator<K> comp;  // A comparator used to compare keys
    
    // Constructors
    protected AbstractPriorityQueue() { this(new DefaultComparator<K>()); }
    protected AbstractPriorityQueue(Comprator<K> c) { comp = c; }

    /** Compares two entries by comparing keys. */
    protected int compare(Entry<K,V> a, Entry<K,V> b) {
        return comp.compare(a.getKey(), b.getKey());
    }

    /** Checks whether the key is valid. */
    protected boolean checkKey(K key) throws IllegalArgumentException {
        try {
            return (compare(key, key) == 0);  // check if key can compare to itself
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Incompatible key");
        }
    }

}
