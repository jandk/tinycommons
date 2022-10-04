package be.twofold.common.collect;

import be.twofold.common.*;

import java.util.*;

final class MultiImmutableMap<K, V> extends ImmutableMap<K, V> {
    static final ImmutableMap<?, ?> Empty = new MultiImmutableMap<>();

    private final Object[] elements;
    private final int size;

    MultiImmutableMap(Object... elements) {
        Object[] table = new Object[Math.max(2 * elements.length, 2)];
        for (int i = 0; i < elements.length; i += 2) {
            Object key = elements[i];
            Object value = Check.notNull(elements[i + 1]);
            int index = Math.floorMod(key.hashCode(), table.length);
            while (true) {
                Object existingKey = table[index];
                if (existingKey == null) {
                    table[index] = key;
                    table[index + 1] = value;
                    break;
                }
                if (existingKey.equals(key)) {
                    throw new IllegalArgumentException("Duplicate key: " + key);
                }
                if ((index += 2) == table.length) {
                    index = 0;
                }
            }
        }
        this.elements = table;
        this.size = elements.length / 2;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return new EntrySet<>(elements, size);
    }

    @Override
    @SuppressWarnings("unchecked")
    public V get(Object key) {
        int index = Math.floorMod(key.hashCode(), elements.length);
        while (true) {
            Object existingKey = elements[index];
            if (existingKey == null) {
                return null;
            }
            if (existingKey.equals(key)) {
                return (V) elements[index + 1];
            }
            if ((index += 2) == elements.length) {
                index = 0;
            }
        }
    }

    static final class EntrySet<K, V> extends ImmutableSet<Entry<K, V>> {
        private final Object[] elements;
        private final int size;

        EntrySet(Object[] elements, int size) {
            this.elements = Check.notNull(elements);
            this.size = size;
        }

        @Override
        public Iterator<Entry<K, V>> iterator() {
            return new Itr<>(elements, size);
        }

        @Override
        public int size() {
            return size;
        }

        static final class Itr<K, V> implements Iterator<Entry<K, V>> {
            private final Object[] elements;
            private final int size;
            private int count;
            private int index;

            Itr(Object[] elements, int size) {
                this.elements = Check.notNull(elements);
                this.size = size;
            }

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            @SuppressWarnings("unchecked")
            public Entry<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                while (true) {
                    K key = (K) elements[index += 2];
                    if (key != null) {
                        count++;
                        return entry(key, (V) elements[index + 1]);
                    }
                }
            }
        }
    }
}
