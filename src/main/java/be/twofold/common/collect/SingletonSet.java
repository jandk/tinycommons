package be.twofold.common.collect;

import be.twofold.common.*;

import java.util.*;

final class SingletonSet<E> extends ImmutableSet<E> {
    private final E element;

    SingletonSet(E element) {
        this.element = Check.notNull(element);
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr<>(element);
    }

    @Override
    public int size() {
        return 1;
    }

    private static class Itr<E> implements Iterator<E> {
        private final E element;
        private boolean hasNext = true;

        private Itr(E element) {
            this.element = Check.notNull(element);
        }

        @Override
        public boolean hasNext() {
            return hasNext;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            hasNext = false;
            return element;
        }
    }
}
