package be.twofold.common.seq;

import java.util.*;

final class EnumerationItr<E> implements Iterator<E> {
    private final Enumeration<E> enumeration;

    EnumerationItr(Enumeration<E> enumeration) {
        this.enumeration = enumeration;
    }

    @Override
    public boolean hasNext() {
        return enumeration.hasMoreElements();
    }

    @Override
    public E next() {
        return enumeration.nextElement();
    }
}
