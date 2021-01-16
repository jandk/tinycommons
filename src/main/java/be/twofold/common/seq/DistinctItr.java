package be.twofold.common.seq;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

final class DistinctItr<E> extends AbstractItr<E> {
    private final Set<E> seen = new HashSet<>();
    private final Iterator<E> iterator;

    DistinctItr(Iterator<E> iterator) {
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
