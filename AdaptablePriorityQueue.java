/**
 * Interface for the Adaptable Priority Queue ADT
 */

public interface AdaptablePriorityQueue<K,V> extends PriorityQueue<K,V> {
    
    /** Removes the entry e. */
    public void remove(Entry<K,V> e) throws IllegalArgumentException;

    /** Replaces the key of existing entry e with k. */
    public void replaceKey(Entry<K,V> e, K k) throws IllegalArgumentException;

    /** Replaces the value of existing entry e with v. */
    public void replaceValue(Entry<K,V> e, V v) throws IllegalArgumentException;
}
