package be.twofold.common.collect;

import java.util.*;
import java.util.function.*;

public abstract class ImmutableList<E> extends ImmutableCollection<E> implements List<E>, RandomAccess {

    ImmutableList() {
    }


    public static <E> ImmutableList<E> of() {
        throw new UnsupportedOperationException();
    }

    public static <E> ImmutableList<E> of(E e) {
        return new SingletonList<>(e);
    }

    public static <E> ImmutableList<E> of(E e1, E e2) {
        throw new UnsupportedOperationException();
    }

    public static <E> ImmutableList<E> of(E e1, E e2, E e3) {
        throw new UnsupportedOperationException();
    }

    public static <E> ImmutableList<E> of(E e1, E e2, E e3, E e4) {
        throw new UnsupportedOperationException();
    }

    public static <E> ImmutableList<E> of(E e1, E e2, E e3, E e4, E e5) {
        throw new UnsupportedOperationException();
    }

    public static <E> ImmutableList<E> of(E e1, E e2, E e3, E e4, E e5, E e6) {
        throw new UnsupportedOperationException();
    }

    public static <E> ImmutableList<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7) {
        throw new UnsupportedOperationException();
    }

    public static <E> ImmutableList<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8) {
        throw new UnsupportedOperationException();
    }

    public static <E> ImmutableList<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9) {
        throw new UnsupportedOperationException();
    }

    public static <E> ImmutableList<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10) {
        throw new UnsupportedOperationException();
    }

    @SafeVarargs
    public static <E> ImmutableList<E> of(E... elements) {
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
    public static <E> ImmutableList<E> copyOf(Collection<? extends E> collection) {
        if (collection instanceof ImmutableList) {
            return (ImmutableList<E>) collection;
        }
        return (ImmutableList<E>) of(collection.toArray());
    }


    @Override
    @Deprecated
    public final void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public final boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public final E remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public final void replaceAll(UnaryOperator<E> operator) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public final E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public final void sort(Comparator<? super E> c) {
        throw new UnsupportedOperationException();
    }

}
