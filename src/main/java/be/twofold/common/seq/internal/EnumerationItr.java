package be.twofold.common.seq.internal;

import java.util.*;

public final class EnumerationItr<E> implements Iterator<E> {
    private final Enumeration<E> enumeration;

    public EnumerationItr(Enumeration<E> enumeration) {
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
