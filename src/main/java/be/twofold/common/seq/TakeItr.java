package be.twofold.common.seq;

import java.util.*;

final class TakeItr<T> implements Iterator<T> {
    private final Iterator<T> iterator;
    private int left;

    TakeItr(Iterator<T> iterator, int count) {
        this.iterator = iterator;
        this.left = count;
    }

    @Override
    public boolean hasNext() {
        return left > 0 && iterator.hasNext();
    }

    @Override
    public T next() {
        if (left-- == 0) {
            throw new NoSuchElementException();
        }
        return iterator.next();
    }
}
