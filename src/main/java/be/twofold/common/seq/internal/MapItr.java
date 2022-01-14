package be.twofold.common.seq.internal;

import java.util.*;
import java.util.function.*;

public final class MapItr<E, R> implements Iterator<R> {
    private final Iterator<E> iterator;
    private final Function<? super E, ? extends R> mapper;

    public MapItr(Iterator<E> iterator, Function<? super E, ? extends R> mapper) {
        this.iterator = iterator;
        this.mapper = mapper;
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
