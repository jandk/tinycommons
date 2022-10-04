package be.twofold.common.collect;

import be.twofold.common.*;

import java.util.*;

final class SingletonList<E> extends ImmutableList<E> {
    private final E element;

    SingletonList(E element) {
        this.element = Check.notNull(element);
    }

    @Override
    public E get(int index) {
        Check.index(index, 1);
        return element;
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
}
