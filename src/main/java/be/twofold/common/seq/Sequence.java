package be.twofold.common.seq;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.*;
import java.util.stream.Stream;

public abstract class Sequence<T> implements Iterable<T> {

    private static final Sequence<?> Empty = sequence(Collections::emptyIterator);

    // region Factory Methods

    @SuppressWarnings("unchecked")
    public static <T> Sequence<T> empty() {
        return (Sequence<T>) Empty;
    }

    @SafeVarargs
    public static <T> Sequence<T> of(T... values) {
        if (Objects.requireNonNull(values).length == 0) {
            return empty();
        }
        return sequence(() -> Arrays.asList(values).iterator());
    }

    public static <T> Sequence<T> sequence(Enumeration<T> enumeration) {
        Objects.requireNonNull(enumeration);
        return sequence(() -> new EnumerationIterator<>(enumeration)).once();
    }

    public static <T> Sequence<T> sequence(Iterable<T> iterable) {
        Objects.requireNonNull(iterable);
        return new Sequence<T>() {
            @Override
            public Iterator<T> iterator() {
                return iterable.iterator();
            }
        };
    }

    public static <T> Sequence<T> sequence(Iterator<T> iterator) {
        return sequence(() -> iterator).once();
    }

    public static <T> Sequence<T> sequence(Stream<T> stream) {
        return sequence(stream::iterator).once();
    }

    // endregion

    // region Intermediate Operations

    public final Sequence<T> distinct() {
        Set<T> seen = new HashSet<>();
        return filter(seen::add);
    }


    public final Sequence<T> filter(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        return sequence(() -> new FilterIterator<>(iterator(), predicate));
    }

    public final Sequence<T> filterIndexed(BiPredicate<Integer, ? super T> predicate) {
        Objects.requireNonNull(predicate);

        AtomicInteger index = new AtomicInteger();
        return filter(t -> predicate.test(index.getAndIncrement(), t));
    }


    public final Sequence<Entry<Integer, T>> indexed() {
        AtomicInteger index = new AtomicInteger();
        return map(t -> new AbstractMap.SimpleImmutableEntry<>(index.getAndIncrement(), t));
    }


    public final <R> Sequence<R> map(Function<? super T, ? extends R> mapper) {
        Objects.requireNonNull(mapper);
        return sequence(() -> new MapIterator<>(iterator(), mapper));
    }

    public final <R> Sequence<R> mapIndexed(BiFunction<Integer, ? super T, ? extends R> mapper) {
        Objects.requireNonNull(mapper);

        AtomicInteger index = new AtomicInteger();
        return map(t -> mapper.apply(index.getAndIncrement(), t));
    }


    public final Sequence<T> onEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        return map(element -> {
            action.accept(element);
            return element;
        });
    }

    public final Sequence<T> onEachIndexed(BiConsumer<Integer, ? super T> action) {
        Objects.requireNonNull(action);

        AtomicInteger index = new AtomicInteger();
        return map(t -> {
            action.accept(index.getAndIncrement(), t);
            return t;
        });
    }


    public final Sequence<T> once() {
        return this instanceof OnceSequence ? this : new OnceSequence<>(this);
    }

    @SuppressWarnings("unchecked")
    public final Sequence<T> sorted() {
        return sorted((Comparator<? super T>) Comparator.naturalOrder());
    }

    public final Sequence<T> sorted(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return sequence(() -> {
            List<T> list = toList();
            list.sort(comparator);
            return list.iterator();
        });
    }


    public final Sequence<T> take(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Negative count");
        } else if (count == 0) {
            return empty();
        } else {
            return sequence(() -> new TakeIterator<>(iterator(), count));
        }
    }

    // endregion

    // region Terminal Operations

    public final boolean all(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);

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
        return filter(predicate).any();
    }

    public final boolean none() {
        return !iterator().hasNext();
    }

    public final boolean none(Predicate<? super T> predicate) {
        return filter(predicate).none();
    }


    public final int count() {
        int count = 0;
        for (T element : this) {
            count++;
        }
        return count;
    }

    public final int count(Predicate<? super T> predicate) {
        return filter(predicate).count();
    }


    public final T first() {
        Iterator<T> iterator = nonEmptyIterator();
        return iterator.next();
    }

    public final T first(Predicate<? super T> predicate) {
        return filter(predicate).first();
    }

    public final T last() {
        Iterator<T> iterator = nonEmptyIterator();
        T last = iterator.next();
        while (iterator.hasNext()) {
            last = iterator.next();
        }
        return last;
    }

    public final T last(Predicate<? super T> predicate) {
        return filter(predicate).last();
    }


    public final void forEachIndexed(BiConsumer<Integer, ? super T> consumer) {
        Objects.requireNonNull(consumer);

        AtomicInteger index = new AtomicInteger();
        forEach(t -> consumer.accept(index.getAndIncrement(), t));
    }


    public final T reduce(BinaryOperator<T> operation) {
        Objects.requireNonNull(operation);

        Iterator<T> iterator = nonEmptyIterator();
        T result = iterator.next();
        while (iterator.hasNext()) {
            result = operation.apply(result, iterator.next());
        }
        return result;
    }

    public final <R> R reduce(R initial, BiFunction<R, ? super T, R> operation) {
        Objects.requireNonNull(operation);

        R result = initial;
        for (T element : this) {
            result = operation.apply(result, element);
        }
        return result;
    }


    public final <C extends Collection<? super T>> C toCollection(C collection) {
        Objects.requireNonNull(collection);

        for (T element : this) {
            collection.add(element);
        }
        return collection;
    }

    public final List<T> toList() {
        return toCollection(new ArrayList<>());
    }

    public final Set<T> toSet() {
        return toCollection(new HashSet<>());
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

    public final Set<T> toUnmodifiableSet() {
        Set<T> set = toCollection(new HashSet<>());
        switch (set.size()) {
            case 0:
                return Collections.emptySet();
            case 1:
                return Collections.singleton(set.iterator().next());
            default:
                return Collections.unmodifiableSet(set);
        }
    }

    // endregion

    // region Helpers

    private Iterator<T> nonEmptyIterator() {
        Iterator<T> iterator = iterator();
        if (!iterator.hasNext()) {
            throw new NoSuchElementException("Empty sequence");
        }
        return iterator;
    }

    // endregion

}
