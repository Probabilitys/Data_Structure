

/** 
 * Implements an unsorted map with an unsorted arraylist
 */

import java.util.ArrayList;

public class UnsortedTableMap<K,V> extends AbstractMap<K,V> {
    
    /** arraylist to store entries */
    private ArrayList<Entry<K,V>> table = new ArrayList<>();

    // constructor
    public UnsortedTableMap() {}

    // private utility
    /** Returns the index of an entry with eky, or -1 if none found. */
    private int findIndex(K key) {
        int n = table.size();
        for (int j=0; j<n; j++) {
            if (table.get(j).getKey().equals(key))
                return j;
        }
        return -1;
    }

    /** Returns the number of entries. */
    public int size() { return table.size(); }
    
    /** Returns the value v associated with key k, or null if none exist. */
    public V get(K key) {
        int j = findIndex(key);
        if (j == -1) return null;
        return table.get(j).getValue();
    }

    /** 
     * Adds entry (k,v) and return null if no such entry.
     * Else replaces v and returns the old value.
     */
    public V put(K key, V value) {
        int j = findIndex(key);
        if (j==-1) {
            table.add(new MapEntry<K,V>(key, value));
            return null;
        }
        else
            return table.get(j).getValue();
    }

    /** Removes the entry with key k and returns its value v, or null if no such entry. */
    public V remove(K key) {
        int j = findIndex(key);
        if (j==-1) return null;
        int n = size();
        V temp = table.get(j).getValue();
        if (j != n-1) 
            table.set(j, table.get(n-1));
        table.remove(n-1);
        return temp;
    }


    // nonpublic EntryIterator class
    private class EntryIterator implements Iterator<Entry<K,V>> {
        private int j=0;
        public boolean hasNext() { return j<table.size(); }
        public Entry<K,V> next() {
            if (j == table.size()) throw new NoSuchElementException();
            return table.get(j++);
        }
        public void remove() { throw new UnsupportedOperationException(); }
    }
    private class EntryIterable implements Itearable<Entry<K,V>> {
        public Iterator<Entry<K,V>> itarator() { return new EntryIterator(); }
    }
    /** Returns an iterable collection of all entires. */
    public Iterable<Entry<K,V>> entrySet() { return new EntryIterable();}

}
