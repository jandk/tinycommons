package be.twofold.common.collect;

import be.twofold.common.*;

import java.util.*;

final class ImmutableEntry<K, V> implements Map.Entry<K, V> {
    private final K key;
    private final V value;

    ImmutableEntry(K key, V value) {
        this.key = Check.notNull(key);
        this.value = Check.notNull(value);
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        throw new UnsupportedOperationException("setValue");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Map.Entry)) return false;

        Map.Entry<?, ?> other = (Map.Entry<?, ?>) obj;
        return key.equals(other.getKey())
            && value.equals(other.getValue());
    }

    @Override
    public int hashCode() {
        return key.hashCode() ^ value.hashCode();
    }

    @Override
    public String toString() {
        return key + "=" + value;
    }
}
