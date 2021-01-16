package be.twofold.common.seq;

import be.twofold.common.Check;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.*;
import java.util.stream.Stream;

public abstract class Sequence<T> implements Iterable<T> {

    // region Factory Methods

    public static <T> Sequence<T> emptySequence() {
        return wrap(Collections::emptyIterator);
    }

    @SafeVarargs
    public static <T> Sequence<T> sequenceOf(T... values) {
        if (values == null || values.length == 0) {
            return emptySequence();
        }
        return wrap(() -> Arrays.asList(values).iterator());
    }

    public static <T> Sequence<T> sequence(Enumeration<T> enumeration) {
        return wrap(() -> new EnumItr<>(enumeration)).once();
    }

    public static <T> Sequence<T> sequence(Iterable<T> iterable) {
        return wrap(iterable);
    }

    public static <T> Sequence<T> sequence(Iterator<T> iterator) {
        return wrap(() -> iterator).once();
    }

    public static <T> Sequence<T> sequence(Stream<T> stream) {
        return wrap(stream::iterator).once();
    }

    private static <T> Sequence<T> wrap(Iterable<T> iterable) {
        return new Wrap<>(iterable);
    }

    // endregion

    // region Intermediate Operations

    public final Sequence<T> distinct() {
        return wrap(() -> new DistinctItr<>(iterator()));
    }


    public final Sequence<T> filter(Predicate<? super T> predicate) {
        Check.notNull(predicate, "predicate");
        return wrap(() -> new FilterItr<>(iterator(), predicate));
    }

    public final Sequence<T> filterIndexed(BiPredicate<Integer, ? super T> predicate) {
        Check.notNull(predicate, "predicate");
        return indexed()
                .filter(e -> predicate.test(e.getKey(), e.getValue()))
                .map(Entry::getValue);
    }


    public final Sequence<Entry<Integer, T>> indexed() {
        return wrap(() -> new IndexedItr<>(iterator()));
    }


    public final <R> Sequence<R> map(Function<? super T, ? extends R> mapper) {
        Check.notNull(mapper, "mapper");
        return wrap(() -> new MapItr<>(iterator(), mapper));
    }

    public final <R> Sequence<R> mapIndexed(BiFunction<Integer, ? super T, ? extends R> mapper) {
        Check.notNull(mapper, "mapper");
        return indexed()
                .map(e -> mapper.apply(e.getKey(), e.getValue()));
    }


    public final Sequence<T> onEach(Consumer<? super T> action) {
        Check.notNull(action, "action");
        return map(element -> {
            action.accept(element);
            return element;
        });
    }

    public final Sequence<T> onEachIndexed(BiConsumer<Integer, ? super T> action) {
        Check.notNull(action, "action");
        return indexed()
                .map(e -> {
                    action.accept(e.getKey(), e.getValue());
                    return e.getValue();
                });
    }


    public final Sequence<T> once() {
        return this instanceof Once ? this : new Once<>(this);
    }

    @SuppressWarnings("unchecked")
    public final Sequence<T> sorted() {
        return sorted((Comparator<? super T>) Comparator.naturalOrder());
    }

    public final Sequence<T> sorted(Comparator<? super T> comparator) {
        Check.notNull(comparator, "comparator");
        return wrap(() -> {
            List<T> list = toList();
            list.sort(comparator);
            return list.iterator();
        });
    }


    public final Sequence<T> take(int count) {
        Check.argument(count >= 0);
        if (count == 0) {
            return emptySequence();
        }
        return wrap(() -> new TakeItr<>(iterator(), count));
    }

    // endregion

    // region Terminal Operations

    public final boolean all(Predicate<? super T> predicate) {
        Check.notNull(predicate, "predicate");

        for (T element : this) {
            if (!predicate.test(element)) {
                return false;
            }
        }
        return true;
    }


    public final boolean any() {
        return iterator().hasNext();
    }

    public final boolean any(Predicate<? super T> predicate) {
        Check.notNull(predicate, "predicate");

        for (T element : this) {
            if (predicate.test(element)) {
                return true;
            }
        }
        return false;
    }


    public final int count() {
        int count = 0;
        for (T ignored : this) {
            count++;
        }
        return count;
    }

    public final int count(Predicate<? super T> predicate) {
        Check.notNull(predicate, "predicate");

        int count = 0;
        for (T element : this) {
            if (predicate.test(element)) {
                count++;
            }
        }
        return count;
    }


    public final T first() {
        Iterator<T> iterator = iterator();
        if (!iterator.hasNext()) {
            throw new NoSuchElementException("Empty sequence");
        }
        return iterator.next();
    }

    public final Optional<T> firstOptional() {
        Iterator<T> iterator = iterator();
        if (!iterator.hasNext()) {
            return Optional.empty();
        }
        return Optional.of(iterator.next());
    }

    public final T first(Predicate<? super T> predicate) {
        Check.notNull(predicate, "predicate");

        for (T element : this) {
            if (predicate.test(element)) {
                return element;
            }
        }
        throw new NoSuchElementException("No matching element in sequence");
    }

    public final Optional<T> firstOptional(Predicate<? super T> predicate) {
        Check.notNull(predicate, "predicate");

        for (T element : this) {
            if (predicate.test(element)) {
                return Optional.of(element);
            }
        }
        return Optional.empty();
    }


    public final T last() {
        Iterator<T> iterator = iterator();
        if (!iterator.hasNext()) {
            throw new NoSuchElementException("Empty sequence");
        }
        T last = iterator.next();
        while (iterator.hasNext()) {
            last = iterator.next();
        }
        return last;
    }

    public final Optional<T> lastOptional() {
        Iterator<T> iterator = iterator();
        if (!iterator.hasNext()) {
            return Optional.empty();
        }
        T last = iterator.next();
        while (iterator.hasNext()) {
            last = iterator.next();
        }
        return Optional.of(last);
    }

    public final T last(Predicate<? super T> predicate) {
        Check.notNull(predicate, "predicate");

        T last = null;
        boolean found = false;
        for (T element : this) {
            if (predicate.test(element)) {
                last = element;
                found = true;
            }
        }

        if (!found) {
            throw new NoSuchElementException("No matching element in sequence");
        }
        return last;
    }

    public final Optional<T> lastOptional(Predicate<? super T> predicate) {
        Check.notNull(predicate, "predicate");

        T last = null;
        boolean found = false;
        for (T element : this) {
            if (predicate.test(element)) {
                last = element;
                found = true;
            }
        }
        return found ? Optional.of(last) : Optional.empty();
    }


    public final void forEach(Consumer<? super T> consumer) {
        Check.notNull(consumer, "consumer");

        for (T element : this) {
            consumer.accept(element);
        }
    }

    public final void forEachIndexed(BiConsumer<Integer, ? super T> consumer) {
        Check.notNull(consumer);

        int count = 0;
        for (T element : this) {
            consumer.accept(count++, element);
        }
    }


    public final boolean none() {
        return !iterator().hasNext();
    }

    public final boolean none(Predicate<? super T> predicate) {
        Check.notNull(predicate, "predicate");

        for (T element : this) {
            if (predicate.test(element)) {
                return false;
            }
        }
        return true;
    }


    public final T reduce(BinaryOperator<T> operation) {
        Check.notNull(operation, "operation");

        Iterator<T> iterator = iterator();
        if (!iterator.hasNext()) {
            throw new NoSuchElementException("Empty sequence");
        }

        T result = iterator.next();
        while (iterator.hasNext()) {
            result = operation.apply(result, iterator.next());
        }
        return result;
    }

    public final <R> R reduce(R identity, BiFunction<R, ? super T, R> operation) {
        Check.notNull(operation, "operation");

        R result = identity;
        for (T element : this) {
            result = operation.apply(result, element);
        }
        return result;
    }


    public final <C extends Collection<? super T>> C toCollection(C collection) {
        Check.notNull(collection, "collection");

        for (T element : this) {
            collection.add(element);
        }
        return collection;
    }

    public final List<T> toList() {
        return toCollection(new ArrayList<>());
    }

    public final List<T> toUnmodifiableList() {
        ArrayList<T> list = toCollection(new ArrayList<>());
        switch (list.size()) {
            case 0:
                return Collections.emptyList();
            case 1:
                return Collections.singletonList(list.get(0));
            default:
                list.trimToSize();
                return Collections.unmodifiableList(list);
        }
    }

    public final Set<T> toSet() {
        return toCollection(new HashSet<>());
    }

    public final Set<T> toUnmodifiableSet() {
        HashSet<T> set = toCollection(new HashSet<>());
        switch (set.size()) {
            case 0:
                return Collections.emptySet();
            case 1:
                return Collections.singleton(set.iterator().next());
            default:
                return Collections.unmodifiableSet(set);
        }
    }

    static class Wrap<T> extends Sequence<T> {
        private final Iterable<T> iterable;

        Wrap(Iterable<T> iterable) {
            this.iterable = Objects.requireNonNull(iterable);
        }

        @Override
        public Iterator<T> iterator() {
            return iterable.iterator();
        }

        @Override
        public boolean equals(Object obj) {
            return this == obj || obj instanceof Wrap
                    && iterable == ((Wrap<?>) obj).iterable;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(iterable);
        }
    }

    // endregion

    // region Helper Classes

    static abstract class Itr<E> implements Iterator<E> {
        private static final int Failed = -1;
        private static final int NotReady = 0;
        private static final int Ready = 1;
        private static final int Done = 2;

        private int state = NotReady;
        private E next = null;

        @Override
        public boolean hasNext() {
            switch (state) {
                case Failed:
                    throw new IllegalStateException();
                case NotReady:
                    return tryComputeNext();
                case Ready:
                    return true;
                case Done:
                    return false;
            }
            throw new UnsupportedOperationException();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            state = NotReady;
            E next = this.next;
            this.next = null;
            return next;
        }

        private boolean tryComputeNext() {
            state = Failed;
            computeNext();
            return state == Ready;
        }

        protected abstract void computeNext();

        protected void setNext(E next) {
            this.next = next;
            state = Ready;
        }

        protected void done() {
            state = Done;
        }

    }

    static final class DistinctItr<E> extends Itr<E> {
        private final Set<E> seen = new HashSet<>();
        private final Iterator<E> iterator;

        DistinctItr(Iterator<E> iterator) {
            this.iterator = iterator;
        }

        @Override
        protected void computeNext() {
            while (iterator.hasNext()) {
                E element = iterator.next();
                if (seen.add(element)) {
                    setNext(element);
                    return;
                }
            }
            done();
        }
    }

    static final class EnumItr<E> implements Iterator<E> {
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

    static final class FilterItr<E> extends Itr<E> {
        private final Iterator<E> iterator;
        private final Predicate<? super E> predicate;

        FilterItr(Iterator<E> iterator, Predicate<? super E> predicate) {
            this.iterator = iterator;
            this.predicate = predicate;
        }

        @Override
        protected void computeNext() {
            while (iterator.hasNext()) {
                E element = iterator.next();
                if (predicate.test(element)) {
                    setNext(element);
                    return;
                }
            }
            done();
        }
    }

    static final class IndexedItr<E> implements Iterator<Entry<Integer, E>> {
        private final Iterator<E> iterator;
        private int index = 0;

        IndexedItr(Iterator<E> iterator) {
            this.iterator = iterator;
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public Entry<Integer, E> next() {
            return new SimpleImmutableEntry<>(index++, iterator.next());
        }
    }

    static final class MapItr<E, R> implements Iterator<R> {
        private final Iterator<E> iterator;
        private final Function<? super E, ? extends R> mapper;

        MapItr(Iterator<E> iterator, Function<? super E, ? extends R> mapper) {
            this.iterator = iterator;
            this.mapper = mapper;
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public R next() {
            return mapper.apply(iterator.next());
        }
    }

    static final class TakeItr<T> implements Iterator<T> {
        private final Iterator<T> iterator;
        private int left;

        public TakeItr(Iterator<T> iterator, int count) {
            this.iterator = iterator;
            this.left = count;
        }

        @Override
        public boolean hasNext() {
            return left > 0 && iterator.hasNext();
        }

        @Override
        public T next() {
            if (left-- == 0) {
                throw new NoSuchElementException();
            }
            return iterator.next();
        }
    }

    static final class Once<T> extends Sequence<T> {
        private final AtomicReference<Sequence<T>> reference;

        Once(Sequence<T> sequence) {
            this.reference = new AtomicReference<>(sequence);
        }

        @Override
        public Iterator<T> iterator() {
            Sequence<T> sequence = reference.getAndSet(null);
            if (sequence == null) {
                throw new IllegalStateException("Sequence can only be iterated once");
            }
            return sequence.iterator();
        }
    }

    // endregion

}
