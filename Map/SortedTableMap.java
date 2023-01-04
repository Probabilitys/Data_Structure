package Map;

/**
 * Implements an unsorted map with an sorted arraylist
 */

import java.util.ArrayList;
import java.util.Comparator;

public class SortedTableMap<K,V> extends AbstractSortedMap<K,V> {
    private ArrayList<MapEntry<K,V>> table = new ArrayList<>();
    public SortedTableMap() { super(); }
    public SortedTableMap(Comparator<K> comp) { super(comp); }

    /** Returns the smallest index for range table[low..high] inclusive storing an entry
     * with a key greater than or equal to k (or else index high+1, by convension).
     */
    private int findIndex(K key, int low, int high) {
        checkKey(key);
        if (high<low) return high + 1;
        int mid = (low + high) / 2;
        int comp = compare(key, table.get(mid));
        if (comp == 0)
            return mid;
        else if (comp < 0)
            return findIndex(key, low, mid-1);
        else
            return findIndex(key, mid+1, high);
    }

    /** Version of find Index that searches the entire table. */
    private int findIndex(K key) throws IllegalArgumentException {
        checkKey(key);
        return findIndex(key, 0, table.size()-1);
    }

    /** Returns the number of entries in the map. */
    public int size() { return table.size(); }

    /** Returns the value associated with the specified key (or else null). */
    public V get(K key) throws IllegalArgumentException {
        int j = findIndex(key);
        if (j == size() || compare(key, table.get(j)) != 0)
            return null;
        return table.get(j).getValue();
    }

    /** Associates the given value with the given key, returning any overridden value. */
    public V put(K key, V value) throws IllegalArgumentException {
        int j = findIndex(key);
        if (j<size() && compare(key, table.get(j)) == 0)
            return table.get(j).setValue(value);
        table.add(j, new MapEntry<K,V>(key, value));
        return null;
    }

    /** Removes the entry having key k (if any) and returns its associated value. */
    public V remove(K key) throws IllegalArgumentException {
        int j = findIndex(key);
        if (j == size() || compare(key, table.get(j)) != 0)
            return null;
        return table.remove(j).getValue();
    }

    /** Utility returns the entry at index j, or else null if j is out of bounds. */
    private Entry<K,V> safeEntry(int j) {
        if (j<0 || j>=table.size()) return null;
        return table.get(j);
    }

    /** Returns the entry having the least key (or null if map is empty). */
    public Entry<K,V> firstEntry() { return safeEntry(0); }

    /** Returns the entry having the greatest key (or null if kmap is empty). */
    public Entry<K,V> lastEntry() { return safeEntry(table.size()-1); }

    /** Returns the entry with least key greater than or equal to given key (if any). */
    public Entry<K,V> ceilingEntry(K key) throws IllegalArgumentException {
        return safeEntry(findIndex(key));
    }

    /** Returns the entry with greatest key less than or equal to given key (if any). */
    public Entry<K,V> floorEntry(K key) throws IllegalArgumentException {
        int j = findIndex(key);
        if (j == size() || !key.equals(table.get(j).getKey()))
            j--;
        return safeEntry(j);
    }

    /** Returns the entry with greatest key strictly less than given key (if any). */
    public Entry<K,V> lowerEntry(K key) throws IllegalArgumentException {
        return safeEntry(findIndex(key) - 1);
    }

    /** Returns the entry with least key strictly greater than given key (if any). */
    public Entry<K,V> higherEntry(K key) throws IllegalArgumentException {
        int j = findIndex(key);
        if (j<size() && key.equals(table.get(j).getKey()))
            j++;
        return safeEntry(j);
    }

    // support for snapshot iterators for entrySet() and subMap() follow
    private Iterable<Entry<K,V>> snapshot(int startIndex, K stop) {
        ArrayList<Entry<K,V>> buffer = new ArrayList<>();
        int j = startIndex;
        while (j<table.size() && (stop == null || compare(stop, table.get(j))>0))
            buffer.add(table.get(j++));
        return buffer;
    }
    public Iterable<Entry<K,V>> entrySet() { return snapshot(0, null); }
    public Iterable<Entry<K,V>> subMap(K fromKey, K toKey) throws IllegalArgumentException {
        checkKey(toKey);
        return snapshot(findIndex(fromKey), toKey);
    }
}