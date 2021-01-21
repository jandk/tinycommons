package be.twofold.common.seq;

import java.util.Iterator;
import java.util.Objects;

class IterableSequence<T> extends Sequence<T> {
    private final Iterable<T> iterable;

    IterableSequence(Iterable<T> iterable) {
        this.iterable = Objects.requireNonNull(iterable);
    }

    @Override
    public Iterator<T> iterator() {
        return iterable.iterator();
    }
}
