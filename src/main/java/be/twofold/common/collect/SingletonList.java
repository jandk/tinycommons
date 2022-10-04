package be.twofold.common.collect;

import be.twofold.common.*;

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
}
