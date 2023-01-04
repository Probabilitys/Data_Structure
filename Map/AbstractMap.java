package Map;

/**
 * Abstract implementation of Map ADT
 */

import java.util.Iterator;

public abstract class AbstractMap<K, V> implements Map<K, V> {

    /** Indicates whether the map is empty. */
    public boolean isEmpty() { return size() == 0; }

    //---------nested MapEntry class----------
    protected static class MapEntry<K,V> implements Entry<K,V> {
        private K k;  // key
        private V v;  // value
        public MapEntry(K key, V value) {
            k = key;
            v = value;
        }
        // public accessor methods
        public K getKey() { return k; }
        public V getValue() { return v; }
        // nonpublic mutator methods
        protected void setKey(K key) { k = key; }
        protected V setValue(V value) {
            V old = v;
            v = value;
            return old;
        }
    }  //-------end of nested MapEntry class--------

    // Iterator for keys
    private class KeyIterator implements Iterator<K> {
        private Iterator<Entry<K,V>> entries = entrySet().iterator();
        public boolean hasNext() { return entries.hasNext(); }
        public K next() { return entries.next().getKey(); }
        public void remove() { throw new UnsupportedOperationException(); }
    }
    private class KeyIterable implements Iterable<K> {
        public Iterator<K> iterator() { return new KeyIterator(); }
    }
    // keySet() method
    public Iterable<K> keySet() { return new KeyIterable(); }

    // Iterator for values
    private class ValueIterator implements Iterator<V> {
        private Iterator<Entry<K,V>> entries = entrySet().iterator();
        public boolean hasNext() { return entries.hasNext(); }
        public V next() { return entries.next().getValue(); }
        public void remove() { throw new UnsupportedOperationException(); }
    }
    private class ValueIterable implements Iterable<V> {
        public Iterator<V> iterator() { return new ValueIterator(); }
    }
    // values() methods
    public Iterable<V> values() { return new ValueIterable(); }
}