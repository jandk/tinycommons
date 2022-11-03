package be.twofold.common.seq;

import be.twofold.common.*;

import java.util.*;

final class TakeItr<E> implements Iterator<E> {
    private final Iterator<E> iterator;
    private int count;

    TakeItr(Iterator<E> iterator, int count) {
        this.iterator = Check.notNull(iterator, "iterator is null");
        this.count = count;
    }

    @Override
    public boolean hasNext() {
        return count > 0 && iterator.hasNext();
    }

    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        count--;
        return iterator.next();
    }
}
