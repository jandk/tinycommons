package be.twofold.common.seq;

import java.util.*;
import java.util.function.*;

final class Iterators {

    private Iterators() {
        throw new UnsupportedOperationException();
    }

    static final class Drop<E> implements Iterator<E> {
        private final Iterator<E> iterator;
        private int n;

        Drop(Iterator<E> iterator, int n) {
            this.iterator = iterator;
            this.n = n;
        }

        @Override
        public boolean hasNext() {
            while (n > 0 && iterator.hasNext()) {
                iterator.next();
                n--;
            }
            return iterator.hasNext();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return iterator.next();
        }
    }

    static final class DropWhile<E> implements Iterator<E> {
        private final Iterator<E> iterator;
        private final Predicate<? super E> predicate;
        private int state; // 0: dropping, 1: yielding, 2: iterating
        private E next;

        DropWhile(Iterator<E> iterator, Predicate<? super E> predicate) {
            this.iterator = iterator;
            this.predicate = predicate;
        }

        @Override
        public boolean hasNext() {
            if (state == 0) {
                while (iterator.hasNext()) {
                    E next = iterator.next();
                    if (!predicate.test(next)) {
                        this.next = next;
                        state = 1;
                        return true;
                    }
                }
                state = 2;
                return false;
            }
            return state == 1 || iterator.hasNext();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (state == 1) {
                E result = next;
                next = null;
                state = 2;
                return result;
            }
            return iterator.next();
        }
    }

    static final class Filter<E> implements Iterator<E> {
        private final Iterator<E> iterator;
        private final Predicate<? super E> predicate;
        private boolean hasNext;
        private E next;

        Filter(Iterator<E> iterator, Predicate<? super E> predicate) {
            this.iterator = iterator;
            this.predicate = predicate;
        }

        @Override
        public boolean hasNext() {
            while (!hasNext && iterator.hasNext()) {
                E element = iterator.next();
                if (predicate.test(element)) {
                    hasNext = true;
                    next = element;
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

    static final class FlatMap<E, R> implements Iterator<R> {
        private final Iterator<E> iterator;
        private final Function<? super E, ? extends Iterable<? extends R>> mapper;
        private Iterator<? extends R> subIterator = Collections.emptyIterator();

        FlatMap(Iterator<E> iterator, Function<? super E, ? extends Iterable<? extends R>> mapper) {
            this.iterator = iterator;
            this.mapper = mapper;
        }

        @Override
        public boolean hasNext() {
            while (true) {
                if (subIterator.hasNext()) {
                    return true;
                }
                if (!iterator.hasNext()) {
                    return false;
                }
                subIterator = mapper.apply(iterator.next()).iterator();
            }
        }

        @Override
        public R next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return subIterator.next();
        }
    }

    static final class Map<E, R> implements Iterator<R> {
        private final Iterator<E> iterator;
        private final Function<? super E, ? extends R> mapper;

        Map(Iterator<E> iterator, Function<? super E, ? extends R> mapper) {
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

    static final class Take<E> implements Iterator<E> {
        private final Iterator<E> iterator;
        private int n;

        Take(Iterator<E> iterator, int n) {
            this.iterator = iterator;
            this.n = n;
        }

        @Override
        public boolean hasNext() {
            return n > 0 && iterator.hasNext();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            n--;
            return iterator.next();
        }
    }

    static final class TakeWhile<E> implements Iterator<E> {
        private final Iterator<E> iterator;
        private final Predicate<? super E> predicate;
        private int state; // 0: not ready, 1: ready, 2: done
        private E next;

        public TakeWhile(Iterator<E> iterator, Predicate<? super E> predicate) {
            this.iterator = iterator;
            this.predicate = predicate;
        }

        @Override
        public boolean hasNext() {
            if (state == 0) {
                if (iterator.hasNext()) {
                    next = iterator.next();
                    if (predicate.test(next)) {
                        state = 1;
                        return true;
                    }
                }
                state = 2;
                return false;
            }
            return state == 1;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            state = 0;
            return next;
        }
    }

}
