package be.twofold.common.seq;

import java.util.*;

final class ItrDrop<E> implements Iterator<E> {
    private final Iterator<E> iterator;
    private int left;

    ItrDrop(Iterator<E> iterator, int left) {
        this.iterator = iterator;
        this.left = left;
    }


    @Override
    public boolean hasNext() {
        drop();

        return iterator.hasNext();
    }

    @Override
    public E next() {
        drop();

        return iterator.next();
    }

    private void drop() {
        while (left > 0 && iterator.hasNext()) {
            left--;
            iterator.next();
        }
    }
}
