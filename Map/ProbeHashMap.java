package Map;

import java.util.ArrayList;

public class ProbeHashMap<K,V> extends AbstractHashMap<K,V> {
    private MapEntry<K,V>[] table;  // a fixed array of entries
    private MapEntry<K,V> DEFUNCT = new MapEntry<>(null, null);
    public ProbeHashMap() { super(); }
    public ProbeHashMap(int cap) { super(cap); }
    public ProbeHashMap(int cap, int p) { super(cap, p); }

    /** Creates an empty table having length equal to current capacity. */
    protected void createTable() {
        table = (MapEntry<K,V>[]) new MapEntry[capacity];
    }

    /** Returns true if location is either empty or the "defunct" sentinel. */
    private boolean isAvailable(int j) {
        return (table[j] == null || table[j] == DEFUNCT);
    }

    /** Return index with key k, or -(a+1) such that k could be added at index a. */
    private int findSlot(int h, K k) {
        int avail = -1;
        int j = h;
        do {
            if (isAvailable(j)) {
                if (avail == -1) avail = j;
                if (table[j] == null) break;
            } else if (table[j].getKey().equals(k))
                return j;
            j = (j+1) % capacity;
        } while (j != h);
        return -(avail + 1);
    }

    /** Returns value associated with key k in bucket with hash value h, or else null. */
    protected V bucketGet(int h, K k) {
        int j = findSlot(h, k);
        if (j<0) return null;  // no match found
        return table[j].getValue();
    }

    /** Associated key k with value v in bucket with hash value h, or else null. */
    protected V bucketPut(int h, K k, V v) {
        int j = findSlot(h, k);
        if (j>=0)
            return table[j].setValue(v);
        table[-(j+1)] = new MapEntry<>(k,v);
        n++;
        return null;
    }

    /** Removes entry having key k from bucket with hash value h (if any). */
    protected V bucketRemove(int h, K k) {
        int j = findSlot(h, k);
        if (j<0) return null;
        V temp = table[j].getValue();
        table[j] = DEFUNCT;
        n--;
        return temp;
    }

    /** Returns an iterable collection of all key-value entries of the map. */
    public Iterable<Entry<K,V>> entrySet() {
        ArrayList<Entry<K,V>> buffer = new ArrayList<>();
        for (int h=0; h<capacity; h++)
            if (!isAvailable(h)) buffer.add(table[h]);
        return buffer;
    }
}