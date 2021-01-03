package be.twofold.common.seq;

import java.util.*;
import java.util.concurrent.atomic.*;
import java.util.function.*;

final class Sequences {
    private Sequences() {
        throw new UnsupportedOperationException();
    }

    // region Iterators

    static abstract class AbstractIterator<E> implements Iterator<E> {

        private static final int Failed = -1;
        private static final int NotReady = 0;
        private static final int Ready = 1;
        private static final int Done = 2;

        private int state = NotReady;
        private E next = null;

        @Override
        public boolean hasNext() {
            switch (state) {
                case Failed:
                    throw new IllegalStateException();
                case NotReady:
                    return tryComputeNext();
                case Ready:
                    return true;
                case Done:
                    return false;
            }
            throw new UnsupportedOperationException();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            state = NotReady;
            E next = this.next;
            this.next = null;
            return next;
        }

        private boolean tryComputeNext() {
            state = Failed;
            computeNext();
            return state == Ready;
        }

        protected abstract void computeNext();

        protected void setNext(E next) {
            this.next = next;
            state = Ready;
        }

        protected void done() {
            state = Done;
        }

    }

    static final class DistinctIterator<E> extends AbstractIterator<E> {
        private final Set<E> seen = new HashSet<>();
        private final Iterator<E> iterator;

        DistinctIterator(Iterator<E> iterator) {
            this.iterator = iterator;
        }

        @Override
        protected void computeNext() {
            while (iterator.hasNext()) {
                E element = iterator.next();
                if (seen.add(element)) {
                    setNext(element);
                    return;
                }
            }
            done();
        }
    }

    static final class EnumerationIterator<E> implements Iterator<E> {
        private final Enumeration<E> enumeration;

        EnumerationIterator(Enumeration<E> enumeration) {
            this.enumeration = enumeration;
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

    static final class FilterIterator<E> extends AbstractIterator<E> {
        private final Iterator<E> iterator;
        private final Predicate<? super E> predicate;

        FilterIterator(Iterator<E> iterator, Predicate<? super E> predicate) {
            this.iterator = iterator;
            this.predicate = predicate;
        }

        @Override
        protected void computeNext() {
            while (iterator.hasNext()) {
                E element = iterator.next();
                if (predicate.test(element)) {
                    setNext(element);
                    return;
                }
            }
            done();
        }
    }

    static final class IndexedIterator<E> implements Iterator<Map.Entry<Integer, E>> {
        private final Iterator<E> iterator;
        private int index = 0;

        IndexedIterator(Iterator<E> iterator) {
            this.iterator = iterator;
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public Map.Entry<Integer, E> next() {
            return new AbstractMap.SimpleImmutableEntry<>(index++, iterator.next());
        }
    }

    static final class MapIterator<E, R> implements Iterator<R> {
        private final Iterator<E> iterator;
        private final Function<? super E, ? extends R> mapper;

        MapIterator(Iterator<E> iterator, Function<? super E, ? extends R> mapper) {
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

    static final class TakeIterator<T> implements Iterator<T> {
        private final Iterator<T> iterator;
        private int left;

        public TakeIterator(Iterator<T> iterator, int count) {
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
