package be.twofold.common.seq;

import be.twofold.common.*;

import java.util.*;
import java.util.Map.*;
import java.util.concurrent.atomic.*;
import java.util.function.*;
import java.util.stream.*;

public abstract class Seq<T> implements Iterable<T> {

    private static final Seq<?> Empty = seq(Collections::emptyIterator);

    // region Factory Methods

    @SuppressWarnings("unchecked")
    public static <T> Seq<T> empty() {
        return (Seq<T>) Empty;
    }

    @SafeVarargs
    public static <T> Seq<T> of(T... values) {
        if (Check.notNull(values, "values").length == 0) {
            return empty();
        }
        return seq(() -> Arrays.asList(values).iterator());
    }

    public static <T> Seq<T> seq(Enumeration<T> enumeration) {
        Check.notNull(enumeration, "enumeration");
        return seq(() -> new EnumerationItr<>(enumeration)).once();
    }

    public static <T> Seq<T> seq(Iterable<T> iterable) {
        Check.notNull(iterable, "iterable");
        return new Seq<T>() {
            @Override
            public Iterator<T> iterator() {
                return iterable.iterator();
            }
        };
    }

    public static <T> Seq<T> seq(Iterator<T> iterator) {
        return seq(() -> iterator).once();
    }

    public static <T> Seq<T> seq(Stream<T> stream) {
        return seq(stream::iterator).once();
    }

    // endregion

    // region Intermediate Operations

    public final Seq<T> distinct() {
        Set<T> seen = new HashSet<>();
        return filter(seen::add);
    }


    public final Seq<T> filter(Predicate<? super T> predicate) {
        Check.notNull(predicate, "predicate");
        return seq(() -> new FilterItr<>(iterator(), predicate));
    }

    public final Seq<T> filterIndexed(BiPredicate<Integer, ? super T> predicate) {
        Check.notNull(predicate, "predicate");

        AtomicInteger index = new AtomicInteger();
        return filter(t -> predicate.test(index.getAndIncrement(), t));
    }


    public final <R> Seq<R> flatMap(Function<? super T, ? extends Iterable<? extends R>> mapper) {
        Check.notNull(mapper, "mapper");

        return seq(() -> new FlatMapIterator<>(iterator(), mapper));
    }


    public final Seq<Entry<Integer, T>> indexed() {
        AtomicInteger index = new AtomicInteger();
        return map(t -> new AbstractMap.SimpleImmutableEntry<>(index.getAndIncrement(), t));
    }


    public final <R> Seq<R> map(Function<? super T, ? extends R> mapper) {
        Check.notNull(mapper, "mapper");
        return seq(() -> new MapItr<>(iterator(), mapper));
    }

    public final <R> Seq<R> mapIndexed(BiFunction<Integer, ? super T, ? extends R> mapper) {
        Check.notNull(mapper, "mapper");

        AtomicInteger index = new AtomicInteger();
        return map(t -> mapper.apply(index.getAndIncrement(), t));
    }


    public final Seq<T> onEach(Consumer<? super T> action) {
        Check.notNull(action, "action");
        return map(element -> {
            action.accept(element);
            return element;
        });
    }

    public final Seq<T> onEachIndexed(BiConsumer<Integer, ? super T> action) {
        Check.notNull(action, "action");

        AtomicInteger index = new AtomicInteger();
        return map(t -> {
            action.accept(index.getAndIncrement(), t);
            return t;
        });
    }


    public final Seq<T> once() {
        return this instanceof OnceSeq ? this : new OnceSeq<>(this);
    }

    @SuppressWarnings("unchecked")
    public final Seq<T> sorted() {
        return sorted((Comparator<? super T>) Comparator.naturalOrder());
    }

    public final Seq<T> sorted(Comparator<? super T> comparator) {
        Check.notNull(comparator, "comparator");
        return seq(() -> {
            List<T> list = toList();
            list.sort(comparator);
            return list.iterator();
        });
    }


    public final Seq<T> take(int count) {
        Check.argument(count >= 0, "Negative count");
        if (count == 0) {
            return empty();
        } else {
            return seq(() -> new TakeItr<>(iterator(), count));
        }
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
        Check.notNull(consumer, "consumer");

        AtomicInteger index = new AtomicInteger();
        forEach(t -> consumer.accept(index.getAndIncrement(), t));
    }


    public final T reduce(BinaryOperator<T> operation) {
        Check.notNull(operation, "operation");

        Iterator<T> iterator = nonEmptyIterator();
        T result = iterator.next();
        while (iterator.hasNext()) {
            result = operation.apply(result, iterator.next());
        }
        return result;
    }

    public final <R> R reduce(R initial, BiFunction<R, ? super T, R> operation) {
        Check.notNull(operation, "operation");

        R result = initial;
        for (T element : this) {
            result = operation.apply(result, element);
        }
        return result;
    }


    public final <C extends Collection<? super T>> C toCollection(C destination) {
        Check.notNull(destination, "destination");

        for (T element : this) {
            destination.add(element);
        }
        return destination;
    }

    public final List<T> toList() {
        return toCollection(new ArrayList<>());
    }

    public final Set<T> toSet() {
        return toCollection(new HashSet<>());
    }

    public final List<T> toUnmodifiableList() {
        List<T> list = toCollection(new ArrayList<>());
        return CollectionUtils.toUnmodifiableList(list);
    }

    public final Set<T> toUnmodifiableSet() {
        Set<T> set = toCollection(new HashSet<>());
        return CollectionUtils.toUnmodifiableSet(set);
    }

    public final <K, V, M extends Map<K, V>> M toMap(
        Function<? super T, ? extends K> keyMapper,
        Function<? super T, ? extends V> valueMapper,
        M destination
    ) {
        Check.notNull(keyMapper, "keyMapper");
        Check.notNull(valueMapper, "valueMapper");
        Check.notNull(destination, "destination");

        for (T element : this) {
            K key = keyMapper.apply(element);
            V value = valueMapper.apply(element);
            V oldValue = destination.putIfAbsent(key, value);
            if (oldValue != null) {
                throw new IllegalStateException("Duplicate key: " + key);
            }
        }

        return destination;
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
