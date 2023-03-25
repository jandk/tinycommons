package be.twofold.common.collect;

import java.util.*;

public abstract class ImmutableSet<E> extends ImmutableCollection<E> implements Set<E> {

    ImmutableSet() {
    }


    @SuppressWarnings("unchecked")
    public static <E> ImmutableSet<E> of() {
        return (ImmutableSet<E>) MultiImmutableSet.Empty;
    }

    public static <E> ImmutableSet<E> of(E e) {
        return new SingleImmutableSet<>(e);
    }

    public static <E> ImmutableSet<E> of(E e1, E e2) {
        return new MultiImmutableSet<>(e1, e2);
    }

    public static <E> ImmutableSet<E> of(E e1, E e2, E e3) {
        return new MultiImmutableSet<>(e1, e2, e3);
    }

    public static <E> ImmutableSet<E> of(E e1, E e2, E e3, E e4) {
        return new MultiImmutableSet<>(e1, e2, e3, e4);
    }

    public static <E> ImmutableSet<E> of(E e1, E e2, E e3, E e4, E e5) {
        return new MultiImmutableSet<>(e1, e2, e3, e4, e5);
    }

    public static <E> ImmutableSet<E> of(E e1, E e2, E e3, E e4, E e5, E e6) {
        return new MultiImmutableSet<>(e1, e2, e3, e4, e5, e6);
    }

    public static <E> ImmutableSet<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7) {
        return new MultiImmutableSet<>(e1, e2, e3, e4, e5, e6, e7);
    }

    public static <E> ImmutableSet<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8) {
        return new MultiImmutableSet<>(e1, e2, e3, e4, e5, e6, e7, e8);
    }

    public static <E> ImmutableSet<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9) {
        return new MultiImmutableSet<>(e1, e2, e3, e4, e5, e6, e7, e8, e9);
    }

    public static <E> ImmutableSet<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10) {
        return new MultiImmutableSet<>(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10);
    }

    @SafeVarargs
    public static <E> ImmutableSet<E> of(E... elements) {
        switch (elements.length) {
            case 0:
                return of();
            case 1:
                return of(elements[0]);
            default:
                return new MultiImmutableSet<>(elements);
        }
    }

    @SuppressWarnings("unchecked")
    public static <E> ImmutableSet<E> copyOf(Collection<? extends E> collection) {
        if (collection instanceof ImmutableSet) {
            return (ImmutableSet<E>) collection;
        }
        return (ImmutableSet<E>) of(collection.toArray());
    }


    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Set)) return false;

        Collection<?> collection = (Collection<?>) obj;
        if (collection.size() != size()) {
            return false;
        }

        for (Object element : collection) {
            if (element == null || !contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public final int hashCode() {
        int hashCode = 0;
        for (E obj : this) {
            hashCode += obj.hashCode();
        }
        return hashCode;
    }

}
