package be.twofold.common.collect;

import java.util.*;

final class MultiImmutableSet<E> extends ImmutableSet<E> {
    static final ImmutableSet<?> Empty = new MultiImmutableSet<>();

    private final E[] elements;
    private final int size;

    @SafeVarargs
    @SuppressWarnings("unchecked")
    MultiImmutableSet(E... elements) {
        E[] table = (E[]) new Object[2 * Math.max(elements.length, 1)];
        for (E element : elements) {
            int index = Math.floorMod(element.hashCode(), table.length);
            while (true) {
                E existing = table[index];
                if (existing == null) {
                    table[index] = element;
                    break;
                }
                if (existing.equals(element)) {
                    throw new IllegalArgumentException("duplicate element: " + element);
                }
                if (++index == table.length) {
                    index = 0;
                }
            }
        }
        this.elements = table;
        this.size = elements.length;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr<>(elements, size);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        int index = Math.floorMod(o.hashCode(), elements.length);
        while (true) {
            E element = elements[index];
            if (element == null) {
                return false;
            }
            if (element.equals(o)) {
                return true;
            }
            if (++index == elements.length) {
                index = 0;
            }
        }
    }

    static final class Itr<E> implements Iterator<E> {
        private final E[] elements;
        private final int size;
        private int count;
        private int index;

        Itr(E[] elements, int size) {
            this.elements = elements;
            this.size = size;
        }

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            while (true) {
                E element = elements[index++];
                if (element != null) {
                    count++;
                    return element;
                }
            }
        }
    }

}
