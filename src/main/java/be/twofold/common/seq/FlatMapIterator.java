package be.twofold.common.seq;

import java.util.*;
import java.util.function.*;

final class FlatMapIterator<E, R> implements Iterator<R> {

    private final Iterator<E> iterator;
    private final Function<? super E, ? extends Iterable<? extends R>> mapper;
    private Iterator<? extends R> itemIterator;

    FlatMapIterator(Iterator<E> iterator, Function<? super E, ? extends Iterable<? extends R>> mapper) {
        this.iterator = iterator;
        this.mapper = mapper;
    }

    @Override
    public boolean hasNext() {
        if (itemIterator != null && !iterator.hasNext()) {
            itemIterator = null;
        }
        while (itemIterator == null) {
            if (!iterator.hasNext()) {
                return false;
            }
            E element = iterator.next();
            Iterator<? extends R> nextItemIterator = mapper.apply(element).iterator();
            if (nextItemIterator.hasNext()) {
                itemIterator = nextItemIterator;
                return true;
            }
        }
        return true;
    }

    @Override
    public R next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return itemIterator.next();
    }
}
