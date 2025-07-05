package be.twofold.common;

import java.util.*;

public interface PeekingIterator<E> extends Iterator<E> {
    E peek();

    static <E> PeekingIterator<E> wrap(Iterator<? extends E> iterator) {
        Check.notNull(iterator, "iterator");

        return new PeekingIterator<E>() {
            private E peeked;

            @Override
            public E peek() {
                if (peeked == null) {
                    peeked = Check.notNull(iterator.next());
                }
                return peeked;
            }

            @Override
            public boolean hasNext() {
                return peeked != null || iterator.hasNext();
            }

            @Override
            public E next() {
                if (peeked == null) {
                    return Check.notNull(iterator.next());
                }

                E result = peeked;
                peeked = null;
                return result;
            }
        };
    }
}
