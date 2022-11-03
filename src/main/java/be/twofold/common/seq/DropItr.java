package be.twofold.common.seq;

import be.twofold.common.*;

import java.util.*;

final class DropItr<E> implements Iterator<E> {
    private final Iterator<E> iterator;
    private int count;

    DropItr(Iterator<E> iterator, int count) {
        this.iterator = Check.notNull(iterator, "iterator is null");
        this.count = count;
    }

    @Override
    public boolean hasNext() {
        while (count > 0 && iterator.hasNext()) {
            iterator.next();
            count--;
        }
        return iterator.hasNext();
    }

    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return iterator.next();
    }
}
