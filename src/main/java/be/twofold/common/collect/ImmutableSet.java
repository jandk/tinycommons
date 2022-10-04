package be.twofold.common.collect;

import java.util.*;

public abstract class ImmutableSet<E> extends ImmutableCollection<E> implements Set<E> {

    ImmutableSet() {
    }


    public static <E> ImmutableSet<E> of() {
        throw new UnsupportedOperationException();
    }

    public static <E> ImmutableSet<E> of(E e) {
        return new SingletonSet<>(e);
    }

    public static <E> ImmutableSet<E> of(E e1, E e2) {
        throw new UnsupportedOperationException();
    }

    public static <E> ImmutableSet<E> of(E e1, E e2, E e3) {
        throw new UnsupportedOperationException();
    }

    public static <E> ImmutableSet<E> of(E e1, E e2, E e3, E e4) {
        throw new UnsupportedOperationException();
    }

    public static <E> ImmutableSet<E> of(E e1, E e2, E e3, E e4, E e5) {
        throw new UnsupportedOperationException();
    }

    public static <E> ImmutableSet<E> of(E e1, E e2, E e3, E e4, E e5, E e6) {
        throw new UnsupportedOperationException();
    }

    public static <E> ImmutableSet<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7) {
        throw new UnsupportedOperationException();
    }

    public static <E> ImmutableSet<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8) {
        throw new UnsupportedOperationException();
    }

    public static <E> ImmutableSet<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9) {
        throw new UnsupportedOperationException();
    }

    public static <E> ImmutableSet<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10) {
        throw new UnsupportedOperationException();
    }

    @SafeVarargs
    public static <E> ImmutableSet<E> of(E... elements) {
        switch (elements.length) {
            case 0:
                return of();
            case 1:
                return of(elements[0]);
            default:
                throw new UnsupportedOperationException();
        }
    }

    @SuppressWarnings("unchecked")
    public static <E> ImmutableSet<E> copyOf(Collection<? extends E> collection) {
        if (collection instanceof ImmutableSet) {
            return (ImmutableSet<E>) collection;
        }
        return (ImmutableSet<E>) of(collection.toArray());
    }

}
