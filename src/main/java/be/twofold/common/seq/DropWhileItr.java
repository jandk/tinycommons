package be.twofold.common.seq;

import be.twofold.common.*;

import java.util.*;
import java.util.function.*;

final class DropWhileItr<E> implements Iterator<E> {
    private final Iterator<E> iterator;
    private final Predicate<? super E> predicate;
    private E next = null;
    private int state = -1;

    DropWhileItr(Iterator<E> iterator, Predicate<? super E> predicate) {
        this.iterator = Check.notNull(iterator, "iterator is null");
        this.predicate = Check.notNull(predicate, "predicate is null");
    }

    @Override
    public boolean hasNext() {
        if (state == -1) {
            drop();
        }
        return state == 1 || iterator.hasNext();
    }

    @Override
    public E next() {
        if (state == -1) {
            drop();
        }
        if (state == 1) {
            E result = this.next;
            this.next = null;
            state = 0;
            return result;
        }
        return iterator.next();
    }

    private void drop() {
        while (iterator.hasNext()) {
            E next = iterator.next();
            if (!predicate.test(next)) {
                this.next = next;
                state = 1;
                break;
            }
        }
    }
}
