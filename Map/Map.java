package Map;

/**
 * Map ADT
 * Stores a collection of key-value pairs
 */

public interface Map<K,V> {

    /** Returns the number of entries. */
    public int size();

    /** Indicates whether the map is empty. */
    public boolean isEmpty();

    /** Returns the value v associated with key k, or null if none exist. */
    public V get(K key);

    /**
     * Adds entry (k,v) and return null if no such entry.
     * Else replaces v and returns the old value.
     */
    public V put(K key, V value);

    /** Removes the entry with key k and returns its value v, or null if no such entry. */
    public V remove(K key);

    /** Returns an iterable collection of all keys. */
    public Iterable<K> keySet();

    /** Returns an iterable colleciton of all values. */
    public Iterable<V> values();

    /** Returns an iterable collection of all key-value entries. */
    public Iterable<Entry<K, V>> entrySet();
}
