package be.twofold.common.collect;

import be.twofold.common.*;

final class SingleImmutableList<E> extends ImmutableList<E> {
    private final E element;

    SingleImmutableList(E element) {
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
