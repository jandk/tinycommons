package be.twofold.common.seq;

import java.util.Enumeration;
import java.util.Iterator;

final class EnumItr<E> implements Iterator<E> {
    private final Enumeration<E> enumeration;

    EnumItr(Enumeration<E> enumeration) {
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
