package be.twofold.common.seq;

import be.twofold.common.*;
import be.twofold.common.seq.internal.*;
import be.twofold.common.tuple.*;

import java.util.*;
import java.util.concurrent.atomic.*;
import java.util.function.*;
import java.util.stream.*;

public interface Seq<T> extends Iterable<T> {

    static <T> Seq<T> empty() {
        return Collections::emptyIterator;
    }

    @SafeVarargs
    static <T> Seq<T> of(T... values) {
        if (values == null || values.length == 0) {
            return empty();
        }
        return () -> Arrays.asList(values).iterator();
    }

    static <T> Seq<T> seq(Iterator<T> iterator) {
        Check.notNull(iterator, "iterator");
        return ((Seq<T>) () -> iterator).once();
    }

    static <T> Seq<T> seq(Iterable<T> iterable) {
        Check.notNull(iterable, "iterable");
        return iterable::iterator;
    }

    static <T> Seq<T> seq(Stream<T> stream) {
        return ((Seq<T>) stream::iterator).once();
    }

    // region Intermediate Operations

    default Seq<T> distinct() {
        Set<T> seen = new HashSet<>();
        return filter(seen::add);
    }


    default Seq<T> filter(Predicate<? super T> predicate) {
        Check.notNull(predicate, "predicate");

        return () -> new FilterItr<>(iterator(), predicate);
    }

    default Seq<T> filterIndexed(BiPredicate<Integer, ? super T> predicate) {
        Check.notNull(predicate, "predicate");

        AtomicInteger index = new AtomicInteger();
        return filter(t -> predicate.test(index.getAndIncrement(), t));
    }


    default <R> Seq<R> flatMap(Function<? super T, ? extends Iterable<? extends R>> mapper) {
        Check.notNull(mapper, "mapper");

        return () -> new FlatMapItr<>(iterator(), mapper);
    }

    default <R> Seq<R> flatMapIndexed(BiFunction<Integer, ? super T, ? extends Iterable<? extends R>> mapper) {
        Check.notNull(mapper, "mapper");

        AtomicInteger index = new AtomicInteger();
        return flatMap(t -> mapper.apply(index.getAndIncrement(), t));
    }


    default Seq<Pair<Integer, T>> indexed() {
        AtomicInteger index = new AtomicInteger();
        return map(t -> Pair.of(index.getAndIncrement(), t));
    }


    default <R> Seq<R> map(Function<? super T, ? extends R> mapper) {
        Check.notNull(mapper, "mapper");

        return () -> new MapItr<>(iterator(), mapper);
    }

    default <R> Seq<R> mapIndexed(BiFunction<Integer, ? super T, ? extends R> mapper) {
        Check.notNull(mapper, "mapper");

        AtomicInteger index = new AtomicInteger();
        return map(t -> mapper.apply(index.getAndIncrement(), t));
    }


    default Seq<T> onEach(Consumer<? super T> action) {
        Check.notNull(action, "action");

        return map(element -> {
            action.accept(element);
            return element;
        });
    }

    default Seq<T> onEachIndexed(BiConsumer<Integer, ? super T> action) {
        Check.notNull(action, "action");

        AtomicInteger index = new AtomicInteger();
        return map(t -> {
            action.accept(index.getAndIncrement(), t);
            return t;
        });
    }


    default Seq<T> once() {
        return this instanceof OnceSeq ? this : new OnceSeq<>(this);
    }


    @SuppressWarnings("unchecked")
    default Seq<T> sorted() {
        return sorted((Comparator<? super T>) Comparator.naturalOrder());
    }

    default Seq<T> sorted(Comparator<? super T> comparator) {
        Check.notNull(comparator, "comparator");

        return () -> {
            List<T> list = toList();
            list.sort(comparator);
            return list.iterator();
        };
    }


    default Seq<T> drop(int count) {
        Check.argument(count >= 0, "Negative count");
        if (count == 0) {
            return this;
        }
        return () -> new DropItr<>(iterator(), count);
    }

    default Seq<T> take(int count) {
        Check.argument(count >= 0, "Negative count");
        if (count == 0) {
            return empty();
        }
        return () -> new TakeItr<>(iterator(), count);
    }

    // endregion

    // region Terminal Operations

    default boolean all(Predicate<? super T> predicate) {
        Check.notNull(predicate, "predicate");

        for (T element : this) {
            if (!predicate.test(element)) {
                return false;
            }
        }
        return true;
    }

    default boolean any() {
        return iterator().hasNext();
    }

    default boolean any(Predicate<? super T> predicate) {
        return filter(predicate).any();
    }

    default boolean none() {
        return !iterator().hasNext();
    }

    default boolean none(Predicate<? super T> predicate) {
        return filter(predicate).none();
    }


    default int count() {
        int count = 0;
        for (T ignored : this) {
            count++;
        }
        return count;
    }

    default int count(Predicate<? super T> predicate) {
        return filter(predicate).count();
    }


    default T first() {
        return nonEmptyIterator().next();
    }

    default T first(Predicate<? super T> predicate) {
        return filter(predicate).first();
    }

    default T last() {
        Iterator<T> iterator = nonEmptyIterator();
        T last = iterator.next();
        while (iterator.hasNext()) {
            last = iterator.next();
        }
        return last;
    }

    default T last(Predicate<? super T> predicate) {
        return filter(predicate).last();
    }


    default void forEach(Consumer<? super T> consumer) {
        Check.notNull(consumer, "consumer");

        for (T element : this) {
            consumer.accept(element);
        }
    }

    default void forEachIndexed(BiConsumer<Integer, ? super T> consumer) {
        Check.notNull(consumer, "consumer");

        AtomicInteger index = new AtomicInteger();
        forEach(t -> consumer.accept(index.getAndIncrement(), t));
    }


    default T reduce(BinaryOperator<T> operation) {
        Check.notNull(operation, "operation");

        Iterator<T> iterator = nonEmptyIterator();
        T result = iterator.next();
        while (iterator.hasNext()) {
            result = operation.apply(result, iterator.next());
        }
        return result;
    }

    default <R> R reduce(R initial, BiFunction<R, ? super T, R> operation) {
        Check.notNull(operation, "operation");

        R result = initial;
        for (T element : this) {
            result = operation.apply(result, element);
        }
        return result;
    }

    // endregion

    // region Collectors

    default <C extends Collection<? super T>> C toCollection(C destination) {
        Check.notNull(destination, "destination");

        for (T element : this) {
            destination.add(element);
        }
        return destination;
    }

    default List<T> toList() {
        return toCollection(new ArrayList<>());
    }

    default Set<T> toSet() {
        return toCollection(new HashSet<>());
    }

    default List<T> toUnmodifiableList() {
        return List.copyOf(toList());
    }

    default Set<T> toUnmodifiableSet() {
        return Set.copyOf(toList());
    }

    default <K, V, M extends Map<K, V>> M toMap(
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

    private Iterator<T> nonEmptyIterator() {
        Iterator<T> iterator = iterator();
        if (!iterator.hasNext()) {
            throw new NoSuchElementException("Empty seq");
        }
        return iterator;
    }

}
