package be.twofold.common.seq;

import be.twofold.common.*;

import java.util.*;
import java.util.concurrent.atomic.*;

final class Sequences {
    private Sequences() {
        throw new UnsupportedOperationException();
    }

    // region Iterators

    static final class EnumerationIterator<E> implements Iterator<E> {
        private final Enumeration<E> enumeration;

        EnumerationIterator(Enumeration<E> enumeration) {
            this.enumeration = Check.notNull(enumeration, "enumeration");
        }

        @Override
        public boolean hasNext() {
            return enumeration.hasMoreElements();
        }

        @Override
        public E next() {
            return enumeration.nextElement();
        }
    }

    // endregion

    // region Sequences

    static final class OnlyOnceSequence<T> implements Sequence<T> {
        private final AtomicReference<Sequence<T>> reference;

        OnlyOnceSequence(Sequence<T> sequence) {
            this.reference = new AtomicReference<>(sequence);
        }

        @Override
        public Iterator<T> iterator() {
            Sequence<T> sequence = reference.getAndSet(null);
            if (sequence == null) {
                throw new IllegalStateException("Sequence can only be iterated once");
            }
            return sequence.iterator();
        }
    }

    // endregion

}
