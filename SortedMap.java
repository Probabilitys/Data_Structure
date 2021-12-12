/**
 * Sorted Map ADT
 */

public interface SortedMap<K,V> {

    /** Returns the entry having the least key (or null if map is empty). */
    Entry<K,V> firstEntry();
    /** Returns the entry having the greatest key (or null if kmap is empty). */
    Entry<K,V> lastEntry();
    /** Returns the entry with least key greater than or equal to given key (if any). */
    Entry<K,V> ceilingEntry(K key) throws IllegalArgumentException;
    /** Returns the entry with greatest key less than or equal to given key (if any). */
    Entry<K,V> floorEntry(K key) throws IllegalArgumentException;
    /** Returns the entry with greatest key strictly less than given key (if any). */
    Entry<K,V> lowerEntry(K key) throws IllegalArgumentException;
    /** Returns the entry with least key strictly greater than given key (if any). */
    Entry<K,V> higherEntry(K key) throws IllegalArgumentException;
    /** Return an iterable collection in the range from 'fromKey'(inclusive) to 'toKey'(exclusive). */
    Itarable<Entry<K,V>> subMap(K fromKey, K toKey) throws IllegalArgumentException;
}
