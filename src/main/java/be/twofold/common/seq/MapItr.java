package be.twofold.common.seq;

import be.twofold.common.*;

import java.util.*;
import java.util.function.*;

final class MapItr<E, R> implements Iterator<R> {
    private final Iterator<E> iterator;
    private final Function<? super E, ? extends R> mapper;

    MapItr(Iterator<E> iterator, Function<? super E, ? extends R> mapper) {
        this.iterator = Check.notNull(iterator, "iterator is null");
        this.mapper = Check.notNull(mapper, "mapper is null");
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public R next() {
        return mapper.apply(iterator.next());
    }
}
