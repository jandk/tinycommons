package be.twofold.common.collect;

import be.twofold.common.*;

import java.util.*;

final class SingletonMap<K, V> extends ImmutableMap<K, V> {
    private final K key;
    private final V value;

    SingletonMap(K key, V value) {
        this.key = Check.notNull(key);
        this.value = Check.notNull(value);
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return ImmutableSet.of(entry(key, value));
    }
}
