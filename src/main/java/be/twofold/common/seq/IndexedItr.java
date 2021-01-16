package be.twofold.common.seq;

import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Map;

final class IndexedItr<E> implements Iterator<Map.Entry<Integer, E>> {
    private final Iterator<E> iterator;
    private int index = 0;

    IndexedItr(Iterator<E> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Map.Entry<Integer, E> next() {
        return new AbstractMap.SimpleImmutableEntry<>(index++, iterator.next());
    }
}
