package be.twofold.common.seq;

import java.util.*;
import java.util.function.*;

final class FilterItr<T> implements Iterator<T> {
    private final Iterator<T> iterator;
    private final Predicate<? super T> predicate;
    private int state; // 0 = not ready, 1 = ready, 2 = done
    private T next;

    FilterItr(Iterator<T> iterator, Predicate<? super T> predicate) {
        this.iterator = iterator;
        this.predicate = predicate;
    }

    @Override
    public final boolean hasNext() {
        if (state != 0) {
            return state == 1;
        }

        while (iterator.hasNext()) {
            T element = iterator.next();
            if (predicate.test(element)) {
                next = element;
                state = 1;
                return true;
            }
        }
        next = null;
        state = 2;
        return false;
    }

    @Override
    public final T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        state = 0;
        return next;
    }
}
