package be.twofold.common.seq;

import be.twofold.common.*;

import java.util.*;
import java.util.function.*;

final class FilterItr<E> implements Iterator<E> {
    private final Iterator<E> iterator;
    private final Predicate<? super E> predicate;
    private boolean hasNext;
    private E next;

    FilterItr(Iterator<E> iterator, Predicate<? super E> predicate) {
        this.iterator = Check.notNull(iterator, "iterator is null");
        this.predicate = Check.notNull(predicate, "predicate is null");
    }

    @Override
    public boolean hasNext() {
        while (!hasNext && iterator.hasNext()) {
            E next = iterator.next();
            if (predicate.test(next)) {
                hasNext = true;
                this.next = next;
            }
        }
        return hasNext;
    }

    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        hasNext = false;
        E result = next;
        next = null;
        return result;
    }
}
