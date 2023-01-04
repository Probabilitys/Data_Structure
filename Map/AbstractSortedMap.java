package Map;

import java.util.Comparator;

public abstract class AbstractSortedMap<K,V>
        extends AbstractMap<K,V> implements SortedMap<K,V> {

    private Comparator<K> comp;

    // Constructor
    protected AbstractSortedMap(Comparator<K> c) { comp = c;}
    protected AbstractSortedMap() { this(new DefaultComparator<K>());}

    /** Compares two entries according to key. */
    protected int compare(Entry<K,V> a, Entry<K,V> b) {
        return comp.compare(a.getKey(), b.getKey());
    }

    /** Compares a key and an entry's key. */
    protected int compare(K k, Entry<K,V> e) {
        return comp.compare(k, e.getKey());
    }

    /** Compares two keys. */
    protected int compare(K a, K b) {
        return comp.compare(a, b);
    }

    /** Checks whether a key is valid. */
    protected boolean checkKey(K key) throws IllegalArgumentException {
        try {
            // see if key can be compared to itself
            return (comp.compare(key, key)==0);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Invalid key.");
        }
    }
}