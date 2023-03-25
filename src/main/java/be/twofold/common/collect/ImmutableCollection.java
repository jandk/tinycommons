package be.twofold.common.collect;

import java.util.*;
import java.util.function.*;

public abstract class ImmutableCollection<E> extends AbstractCollection<E> {

    ImmutableCollection() {
    }

    @Override
    @Deprecated
    public final boolean add(E e) {
        throw new UnsupportedOperationException("add");
    }

    @Override
    @Deprecated
    public final boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("addAll");
    }

    @Override
    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException("clear");
    }

    @Override
    @Deprecated
    public final boolean remove(Object o) {
        throw new UnsupportedOperationException("remove");
    }

    @Override
    @Deprecated
    public final boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("removeAll");
    }

    @Override
    @Deprecated
    public final boolean removeIf(Predicate<? super E> filter) {
        throw new UnsupportedOperationException("removeIf");
    }

    @Override
    @Deprecated
    public final boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("retainAll");
    }

}
