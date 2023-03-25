package be.twofold.common.collect;

import be.twofold.common.*;

import java.util.*;
import java.util.function.*;

public abstract class ImmutableList<E> extends ImmutableCollection<E> implements List<E>, RandomAccess {

    ImmutableList() {
    }


    @SuppressWarnings("unchecked")
    public static <E> ImmutableList<E> of() {
        return (ImmutableList<E>) MultiImmutableList.Empty;
    }

    public static <E> ImmutableList<E> of(E e) {
        return new SingletonList<>(e);
    }

    public static <E> ImmutableList<E> of(E e1, E e2) {
        return new MultiImmutableList<>(e1, e2);
    }

    public static <E> ImmutableList<E> of(E e1, E e2, E e3) {
        return new MultiImmutableList<>(e1, e2, e3);
    }

    public static <E> ImmutableList<E> of(E e1, E e2, E e3, E e4) {
        return new MultiImmutableList<>(e1, e2, e3, e4);
    }

    public static <E> ImmutableList<E> of(E e1, E e2, E e3, E e4, E e5) {
        return new MultiImmutableList<>(e1, e2, e3, e4, e5);
    }

    public static <E> ImmutableList<E> of(E e1, E e2, E e3, E e4, E e5, E e6) {
        return new MultiImmutableList<>(e1, e2, e3, e4, e5, e6);
    }

    public static <E> ImmutableList<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7) {
        return new MultiImmutableList<>(e1, e2, e3, e4, e5, e6, e7);
    }

    public static <E> ImmutableList<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8) {
        return new MultiImmutableList<>(e1, e2, e3, e4, e5, e6, e7, e8);
    }

    public static <E> ImmutableList<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9) {
        return new MultiImmutableList<>(e1, e2, e3, e4, e5, e6, e7, e8, e9);
    }

    public static <E> ImmutableList<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10) {
        return new MultiImmutableList<>(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10);
    }

    @SafeVarargs
    public static <E> ImmutableList<E> of(E... elements) {
        switch (elements.length) {
            case 0:
                return of();
            case 1:
                return of(elements[0]);
            default:
                return new MultiImmutableList<>(elements.clone());
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
        throw new UnsupportedOperationException("add");
    }

    @Override
    @Deprecated
    public final boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("addAll");
    }

    @Override
    @Deprecated
    public final E remove(int index) {
        throw new UnsupportedOperationException("remove");
    }

    @Override
    @Deprecated
    public final void replaceAll(UnaryOperator<E> operator) {
        throw new UnsupportedOperationException("replaceAll");
    }

    @Override
    @Deprecated
    public final E set(int index, E element) {
        throw new UnsupportedOperationException("set");
    }

    @Override
    @Deprecated
    public final void sort(Comparator<? super E> c) {
        throw new UnsupportedOperationException("sort");
    }


    @Override
    public final boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public final int indexOf(Object o) {
        Check.notNull(o);
        for (int i = 0, s = size(); i < s; i++) {
            if (o.equals(get(i))) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public final int lastIndexOf(Object o) {
        Check.notNull(o);
        for (int i = size() - 1; i >= 0; i--) {
            if (o.equals(get(i))) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public final Iterator<E> iterator() {
        return new Itr<>(this, size(), 0);
    }

    @Override
    public final ListIterator<E> listIterator() {
        return listIterator(0);
    }

    @Override
    public final ListIterator<E> listIterator(int index) {
        Check.index(index, size() + 1);
        return new ListItr<>(this, size(), index);
    }

    @Override
    public final List<E> subList(int fromIndex, int toIndex) {
        Check.fromToIndex(fromIndex, toIndex, size());
        return new SubList<>(this, fromIndex, toIndex - fromIndex);
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof List)) return false;

        Iterator<?> it = ((List<?>) obj).iterator();
        for (E element : this) {
            if (!it.hasNext() || !element.equals(it.next())) {
                return false;
            }
        }
        return !it.hasNext();
    }

    @Override
    public final int hashCode() {
        int hashCode = 1;
        for (E element : this) {
            hashCode = 31 * hashCode + element.hashCode();
        }
        return hashCode;
    }


    static class Itr<E> implements Iterator<E> {
        protected final List<E> list;
        protected final int size;
        protected int index;

        Itr(List<E> list, int size, int index) {
            this.list = list;
            this.size = size;
            this.index = index;
        }

        @Override
        public final boolean hasNext() {
            return index < size;
        }

        @Override
        public final E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return list.get(index++);
        }
    }

    static final class ListItr<E> extends Itr<E> implements ListIterator<E> {
        ListItr(List<E> list, int size, int index) {
            super(list, size, index);
        }

        @Override
        public boolean hasPrevious() {
            return index > 0;
        }

        @Override
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            return list.get(--index);
        }

        @Override
        public int nextIndex() {
            return index;
        }

        @Override
        public int previousIndex() {
            return index - 1;
        }

        @Override
        @Deprecated
        public void add(E e) {
            throw new UnsupportedOperationException("add");
        }

        @Override
        @Deprecated
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }

        @Override
        @Deprecated
        public void set(E e) {
            throw new UnsupportedOperationException("set");
        }
    }

    static final class SubList<E> extends ImmutableList<E> {
        private final List<E> list;
        private final int offset;
        private final int size;

        SubList(List<E> list, int offset, int size) {
            this.list = list;
            this.offset = offset;
            this.size = size;
        }

        @Override
        public E get(int index) {
            Check.index(index, size);
            return list.get(offset + index);
        }

        @Override
        public int size() {
            return size;
        }
    }

}
