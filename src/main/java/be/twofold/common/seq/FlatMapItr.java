package be.twofold.common.seq;

import java.util.*;
import java.util.function.*;

final class FlatMapItr<E, R> implements Iterator<R> {

    private final Iterator<E> iterator;
    private final Function<? super E, ? extends Iterable<? extends R>> mapper;
    private Iterator<? extends R> itemIterator = Collections.emptyIterator();

    FlatMapItr(Iterator<E> iterator, Function<? super E, ? extends Iterable<? extends R>> mapper) {
        this.iterator = iterator;
        this.mapper = mapper;
    }

    @Override
    public boolean hasNext() {
        while (true) {
            if (itemIterator.hasNext()) {
                return true;
            } else if (!iterator.hasNext()) {
                return false;
            }
            itemIterator = mapper.apply(iterator.next()).iterator();
        }
    }

    @Override
    public R next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return itemIterator.next();
    }
}
