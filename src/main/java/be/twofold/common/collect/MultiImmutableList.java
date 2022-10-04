package be.twofold.common.collect;

import be.twofold.common.*;

final class MultiImmutableList<E> extends ImmutableList<E> {
    static final ImmutableList<?> Empty = new MultiImmutableList<>();

    private final E[] elements;

    @SafeVarargs
    MultiImmutableList(E... elements) {
        for (E element : elements) {
            Check.notNull(element);
        }
        this.elements = elements;
    }

    @Override
    public E get(int index) {
        return elements[index];
    }

    @Override
    public int size() {
        return elements.length;
    }
}
