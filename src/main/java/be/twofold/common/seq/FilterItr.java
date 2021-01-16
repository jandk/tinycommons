package be.twofold.common.seq;

import java.util.Iterator;
import java.util.function.Predicate;

final class FilterItr<E> extends AbstractItr<E> {
    private final Iterator<E> iterator;
    private final Predicate<? super E> predicate;

    FilterItr(Iterator<E> iterator, Predicate<? super E> predicate) {
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
