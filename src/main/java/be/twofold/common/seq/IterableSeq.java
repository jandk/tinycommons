package be.twofold.common.seq;

import java.util.Iterator;
import java.util.Objects;

class IterableSeq<T> extends Sequence<T> {
    private final Iterable<T> iterable;

    IterableSeq(Iterable<T> iterable) {
        this.iterable = Objects.requireNonNull(iterable);
    }

    @Override
    public Iterator<T> iterator() {
        return iterable.iterator();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj instanceof IterableSeq
                && iterable == ((IterableSeq<?>) obj).iterable;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(iterable);
    }
}
