package be.twofold.common.seq;

import be.twofold.common.*;
import be.twofold.common.collect.*;
import be.twofold.common.tuple.*;

import java.util.*;
import java.util.concurrent.atomic.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * A {@link Seq} is a sequence, supporting aggregate operations, like {@link Stream}.
 * <p>
 * Unlike Stream, it is only sequential.
 *
 * @param <T> The type of elements in this Seq
 */
public interface Seq<T> extends Iterable<T> {

    /**
     * Creates a new empty Seq
     *
     * @param <T> The type of the elements
     * @return The new Seq
     */
    @SuppressWarnings("unchecked")
    static <T> Seq<T> of() {
        return (Seq<T>) SeqHelpers.Empty;
    }

    /**
     * Creates a new Seq from the specified elements.
     *
     * @param <T> The type of the elements
     * @return The new Seq
     */
    @SafeVarargs
    static <T> Seq<T> of(T... elements) {
        Check.notNull(elements, "elements is null");
        if (elements.length == 0) {
            return of();
        }
        return () -> Arrays.asList(elements).iterator();
    }

    /**
     * Creates a new Seq from an existing iterable
     */
    static <T> Seq<T> seq(Iterable<T> iterable) {
        Check.notNull(iterable, "iterable is null");
        return iterable::iterator;
    }

    static <T> Seq<T> seq(Iterator<T> iterator) {
        Check.notNull(iterator, "iterator is null");
        return ((Seq<T>) () -> iterator).once();
    }

    static <T> Seq<T> seq(Stream<T> stream) {
        Check.notNull(stream, "stream is null");
        return ((Seq<T>) stream::iterator).once();
    }

    default Stream<T> asStream() {
        return StreamSupport.stream(
            () -> Spliterators.spliteratorUnknownSize(iterator(), Spliterator.ORDERED),
            Spliterator.ORDERED,
            false
        );
    }

    /**
     * Returns a sequence containing only distinct elements from this sequence.
     * The elements are compared using {@link Object#equals(Object)}.
     *
     * @return The new sequence.
     */
    default Seq<T> distinct() {
        Set<T> seen = new HashSet<>();
        return filter(seen::add);
    }

    /**
     * Drops the first {@code count} elements of this sequence.
     *
     * @param count The number of elements to drop.
     * @return The new sequence.
     */
    default Seq<T> drop(int count) {
        Check.argument(count >= 0, "Negative count");

        if (count == 0) {
            return this;
        }
        return () -> new Iterators.Drop<>(iterator(), count);
    }

    /**
     * Drop elements while the predicate is true.
     *
     * @param predicate The predicate to match.
     * @return The new sequence.
     */
    default Seq<T> dropWhile(Predicate<? super T> predicate) {
        Check.notNull(predicate, "predicate");

        return () -> new Iterators.DropWhile<>(iterator(), predicate);
    }

    /**
     * Returns a sequence containing only the elements matching the given predicate.
     *
     * @param predicate The predicate to match.
     * @return The new sequence.
     */
    default Seq<T> filter(Predicate<? super T> predicate) {
        Check.notNull(predicate, "predicate");

        return () -> new Iterators.Filter<>(iterator(), predicate);
    }

    /**
     * Returns a sequence containing only the elements matching the given predicate.
     *
     * @param predicate The predicate to match.
     * @return The new sequence.
     */
    default Seq<T> filterIndexed(BiPredicate<Integer, ? super T> predicate) {
        Check.notNull(predicate, "predicate");

        AtomicInteger index = new AtomicInteger();
        return filter(t -> predicate.test(index.getAndIncrement(), t));
    }

    /**
     * Returns a sequence containing only elements of the given type.
     *
     * @param clazz The type to filter.
     * @param <R>   The type of the elements.
     * @return The new sequence.
     */
    default <R> Seq<R> filterIsInstance(Class<R> clazz) {
        Check.notNull(clazz, "clazz");

        return filter(clazz::isInstance).map(clazz::cast);
    }

    /**
     * Returns a sequence containing only the elements not matching the given predicate.
     *
     * @param predicate The predicate to not match.
     * @return The new sequence.
     */
    default Seq<T> filterNot(Predicate<? super T> predicate) {
        Check.notNull(predicate, "predicate");

        return filter(predicate.negate());
    }

    /**
     * Returns a sequence containing only non-null elements.
     */
    default Seq<T> filterNotNull() {
        return filter(Objects::nonNull);
    }

    /**
     * Returns a single sequence of all elements from results of applying
     * the given function to each element of this sequence.
     *
     * @param mapper The function to apply to each element.
     * @param <R>    The type of the elements of the new sequence.
     * @return The new sequence.
     */
    default <R> Seq<R> flatMap(Function<? super T, ? extends Iterable<? extends R>> mapper) {
        Check.notNull(mapper, "mapper");

        return () -> new Iterators.FlatMap<>(iterator(), mapper);
    }

    /**
     * Returns a single sequence of all elements from results of applying
     * the given function to each element of this sequence.
     *
     * @param mapper The function to apply to each element.
     * @param <R>    The type of the elements of the new sequence.
     * @return The new sequence.
     */
    default <R> Seq<R> flatMapIndexed(BiFunction<Integer, ? super T, ? extends Iterable<? extends R>> mapper) {
        Check.notNull(mapper, "mapper");

        AtomicInteger index = new AtomicInteger();
        return flatMap(t -> mapper.apply(index.getAndIncrement(), t));
    }

    /**
     * Returns a sequence of pairs of indexes and elements of this sequence.
     *
     * @return The new sequence.
     */
    default Seq<Pair<Integer, T>> indexed() {
        AtomicInteger index = new AtomicInteger();
        return map(t -> Pair.of(index.getAndIncrement(), t));
    }

    /**
     * Returns a sequence containing the results of applying the given function to each element of this sequence.
     *
     * @param mapper The function to apply to each element.
     * @param <R>    The type of the elements of the new sequence.
     * @return The new sequence.
     */
    default <R> Seq<R> map(Function<? super T, ? extends R> mapper) {
        Check.notNull(mapper, "mapper");

        return () -> new Iterators.Map<>(iterator(), mapper);
    }

    /**
     * Returns a sequence containing the results of applying the given function to each element of this sequence.
     *
     * @param mapper The function to apply to each element.
     * @param <R>    The type of the elements of the new sequence.
     * @return The new sequence.
     */
    default <R> Seq<R> mapIndexed(BiFunction<Integer, ? super T, ? extends R> mapper) {
        Check.notNull(mapper, "mapper");

        AtomicInteger index = new AtomicInteger();
        return map(t -> mapper.apply(index.getAndIncrement(), t));
    }

    /**
     * Returns a sequence that can be iterated over only once.
     *
     * @return The new sequence.
     */
    default Seq<T> once() {
        if (this instanceof Seqs.Once) {
            return this;
        }
        return new Seqs.Once<>(this);
    }

    /**
     * Perform the given action for each element in the sequence, returning the sequence itself.
     *
     * @param action The action to be performed for each element.
     */
    default Seq<T> onEach(Consumer<? super T> action) {
        Check.notNull(action, "action");

        return map(element -> {
            action.accept(element);
            return element;
        });
    }

    /**
     * Perform the given action for each element in the sequence, returning the sequence itself.
     *
     * @param action The action to be performed for each element.
     * @return The sequence itself.
     */
    default Seq<T> onEachIndexed(BiConsumer<Integer, ? super T> action) {
        Check.notNull(action, "action");

        AtomicInteger index = new AtomicInteger();
        return map(t -> {
            action.accept(index.getAndIncrement(), t);
            return t;
        });
    }

    /**
     * Returns a sorted sequence containing the elements of this sequence, using the natural ordering.
     *
     * @return The new sequence.
     */
    @SuppressWarnings("unchecked")
    default Seq<T> sorted() {
        return sorted((Comparator<? super T>) Comparator.naturalOrder());
    }

    /**
     * Returns a sorted sequence containing the elements of this sequence, using the given comparator.
     *
     * @param comparator The comparator to use to compare elements.
     * @return The new sequence.
     */
    default Seq<T> sorted(Comparator<? super T> comparator) {
        Check.notNull(comparator, "comparator");

        return () -> {
            List<T> list = toList();
            list.sort(comparator);
            return list.iterator();
        };
    }

    /**
     * Returns a sequence containing the first {@code count} elements.
     *
     * @param count The number of elements to take.
     * @return The new sequence.
     */
    default Seq<T> take(int count) {
        Check.argument(count >= 0, "Negative count");
        if (count == 0) {
            return of();
        }
        return () -> new Iterators.Take<>(iterator(), count);
    }

    default Seq<T> takeWhile(Predicate<? super T> predicate) {
        Check.notNull(predicate, "predicate");
        return () -> new Iterators.TakeWhile<>(iterator(), predicate);
    }

    default boolean all(Predicate<? super T> predicate) {
        Check.notNull(predicate, "predicate");

        for (T element : this) {
            if (!predicate.test(element)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if the sequence has at least one element.
     */
    default boolean any() {
        return iterator().hasNext();
    }

    /**
     * Returns true if the sequence has at least one element that matches the given predicate.
     *
     * @param predicate The predicate to match.
     */
    default boolean any(Predicate<? super T> predicate) {
        return filter(predicate).any();
    }

    default double average(ToIntFunction<? super T> mapper) {
        return SeqHelpers.average(SeqHelpers.nonEmpty(this), mapper);
    }

    default double average(ToLongFunction<? super T> mapper) {
        return SeqHelpers.average(SeqHelpers.nonEmpty(this), mapper);
    }

    default double average(ToDoubleFunction<? super T> mapper) {
        return SeqHelpers.average(SeqHelpers.nonEmpty(this), mapper);
    }

    default OptionalDouble averageOptional(ToIntFunction<? super T> mapper) {
        return SeqHelpers.optional(this).map(it -> OptionalDouble.of(SeqHelpers.average(it, mapper))).orElse(OptionalDouble.empty());
    }

    default OptionalDouble averageOptional(ToLongFunction<? super T> mapper) {
        return SeqHelpers.optional(this).map(it -> OptionalDouble.of(SeqHelpers.average(it, mapper))).orElse(OptionalDouble.empty());
    }

    default OptionalDouble averageOptional(ToDoubleFunction<? super T> mapper) {
        return SeqHelpers.optional(this).map(it -> OptionalDouble.of(SeqHelpers.average(it, mapper))).orElse(OptionalDouble.empty());
    }

    /**
     * Returns true if the sequence contains the given element.
     *
     * @param element The element to search for.
     */
    default boolean contains(T element) {
        return indexOf(element) >= 0;
    }

    /**
     * Returns the number of elements in the sequence.
     */
    default int count() {
        int count = 0;
        for (T ignored : this) {
            count++;
        }
        return count;
    }

    /**
     * Returns the number of elements in the sequence that match the given predicate.
     *
     * @param predicate The predicate to match.
     */
    default int count(Predicate<? super T> predicate) {
        return filter(predicate).count();
    }

    /**
     * Returns the first element in the sequence.
     */
    default T first() {
        return SeqHelpers.nonEmpty(this).next();
    }

    /**
     * Returns the first element in the sequence that matches the given predicate.
     *
     * @param predicate The predicate to match.
     */
    default T first(Predicate<? super T> predicate) {
        return filter(predicate).first();
    }

    /**
     * Returns the first element in the sequence,
     * or an empty {@link Optional} if the sequence is empty.
     */
    default Optional<T> firstOptional() {
        return SeqHelpers.optional(this).map(Iterator::next);
    }

    /**
     * Returns the first element in the sequence that matches the given predicate,
     * or an empty {@link Optional} if the sequence is empty.
     *
     * @param predicate The predicate to match.
     */
    default Optional<T> firstOptional(Predicate<? super T> predicate) {
        return filter(predicate).firstOptional();
    }

    /**
     * Perform the given action for each element in the sequence.
     *
     * @param consumer The action to be performed for each element
     */
    default void forEach(Consumer<? super T> consumer) {
        Check.notNull(consumer, "consumer");

        for (T element : this) {
            consumer.accept(element);
        }
    }

    /**
     * Performs the given action for each element in the sequence, providing a sequential index.
     *
     * @param consumer The action to be performed for each element
     */
    default void forEachIndexed(BiConsumer<Integer, ? super T> consumer) {
        Check.notNull(consumer, "consumer");

        AtomicInteger index = new AtomicInteger();
        forEach(t -> consumer.accept(index.getAndIncrement(), t));
    }

    /**
     * Accumulates the elements of the sequence into a single value.
     *
     * @param initial   The initial value.
     * @param operation The operation to perform on each element.
     * @param <R>       The type of the accumulated value.
     * @return The accumulated value.
     */
    default <R> R fold(R initial, BiFunction<R, ? super T, ? extends R> operation) {
        return SeqHelpers.fold(iterator(), initial, operation);
    }

    /**
     * Groups the elements of the sequence by the key given by the key selector function,
     * into a map with each key mapping to a list of values.
     *
     * @param keyMapper The function to transform the elements into keys.
     * @param <K>       The type of the keys.
     * @return The map with each key mapping to a list of values.
     */
    default <K> Map<K, List<T>> groupBy(Function<? super T, ? extends K> keyMapper) {
        return groupBy(keyMapper, Function.identity());
    }

    /**
     * Groups the elements of the sequence transformed by the value selector function,
     * by the key given by the key selector function, into a map with each key mapping to a list of values.
     *
     * @param keyMapper   The function to transform the elements into keys.
     * @param valueMapper The function to transform the elements into values.
     * @param <K>         The type of the keys.
     * @param <V>         The type of the values.
     * @return The map with each key mapping to a list of values.
     */
    default <K, V> Map<K, List<V>> groupBy(
        Function<? super T, ? extends K> keyMapper,
        Function<? super T, ? extends V> valueMapper
    ) {
        Check.notNull(keyMapper, "keyMapper");
        Check.notNull(valueMapper, "valueMapper");

        Map<K, List<V>> result = new HashMap<>();
        for (T element : this) {
            K key = keyMapper.apply(element);
            V value = valueMapper.apply(element);
            result
                .computeIfAbsent(key, __ -> new ArrayList<>())
                .add(value);
        }
        return result;
    }

    /**
     * Returns the index of the first occurrence of the specified element in the sequence,
     * or -1 if the sequence does not contain the element.
     *
     * @param element The element to search for.
     */
    default int indexOf(T element) {
        return indexOf(t -> Objects.equals(t, element));
    }

    /**
     * Returns the index of the first element matching the given predicate,
     * or -1 if the sequence does not contain any element matching the predicate.
     *
     * @param predicate The predicate to match.
     */
    default int indexOf(Predicate<? super T> predicate) {
        int index = 0;
        for (T element : this) {
            if (predicate.test(element)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    /**
     * Returns the last element in the sequence.
     */
    default T last() {
        return SeqHelpers.last(SeqHelpers.nonEmpty(this));
    }

    /**
     * Returns the last element in the sequence that matches the given predicate.
     *
     * @param predicate The predicate to match.
     */
    default T last(Predicate<? super T> predicate) {
        return filter(predicate).last();
    }

    /**
     * Returns the last element in the sequence,
     * or an empty {@link Optional} if the sequence is empty.
     */
    default Optional<T> lastOptional() {
        return SeqHelpers.optional(this).map(SeqHelpers::last);
    }

    /**
     * Returns the last element in the sequence that matches the given predicate,
     * or an empty {@link Optional} if the sequence is empty.
     *
     * @param predicate The predicate to match.
     */
    default Optional<T> lastOptional(Predicate<? super T> predicate) {
        return filter(predicate).lastOptional();
    }

    /**
     * Returns the index of the first occurrence of the specified element in the sequence,
     * or -1 if the sequence does not contain the element.
     *
     * @param element The element to search for.
     */
    default int lastIndexOf(T element) {
        return lastIndexOf(t -> Objects.equals(t, element));
    }

    /**
     * Returns the index of the first element matching the given predicate,
     * or -1 if the sequence does not contain any element matching the predicate.
     *
     * @param predicate The predicate to match.
     */
    default int lastIndexOf(Predicate<? super T> predicate) {
        int index = 0;
        int lastIndex = -1;
        for (T element : this) {
            if (predicate.test(element)) {
                lastIndex = index;
            }
            index++;
        }
        return lastIndex;
    }

    /**
     * Returns the maximum element in the sequence.
     */
    default T max() {
        return SeqHelpers.max(SeqHelpers.nonEmpty(this));
    }

    /**
     * Returns the maximum element in the sequence, using the given comparator.
     *
     * @param comparator The comparator to use.
     */
    default T max(Comparator<? super T> comparator) {
        return SeqHelpers.max(SeqHelpers.nonEmpty(this), comparator);
    }

    /**
     * Returns the maximum element in the sequence,
     * or an empty {@link Optional} if the sequence is empty.
     */
    default Optional<T> maxOptional() {
        return SeqHelpers.optional(this).map(SeqHelpers::max);
    }

    /**
     * Returns the maximum element in the sequence, using the given comparator,
     * or an empty {@link Optional} if the sequence is empty.
     *
     * @param comparator The comparator to use.
     */
    default Optional<T> maxOptional(Comparator<? super T> comparator) {
        return SeqHelpers.optional(this).map(it -> SeqHelpers.max(it, comparator));
    }

    /**
     * Returns the minimum element in the sequence.
     */
    default T min() {
        return SeqHelpers.min(SeqHelpers.nonEmpty(this));
    }

    /**
     * Returns the minimum element in the sequence, using the given comparator.
     *
     * @param comparator The comparator to use.
     */
    default T min(Comparator<? super T> comparator) {
        return SeqHelpers.min(SeqHelpers.nonEmpty(this), comparator);
    }

    /**
     * Returns the minimum element in the sequence,
     * or an empty {@link Optional} if the sequence is empty.
     */
    default Optional<T> minOptional() {
        return SeqHelpers.optional(this).map(SeqHelpers::min);
    }

    /**
     * Returns the minimum element in the sequence, using the given comparator,
     * or an empty {@link Optional} if the sequence is empty.
     *
     * @param comparator The comparator to use.
     */
    default Optional<T> minOptional(Comparator<? super T> comparator) {
        return SeqHelpers.optional(this).map(it -> SeqHelpers.min(it, comparator));
    }

    /**
     * Returns {@code true} if the sequence contains no elements.
     */
    default boolean none() {
        return !iterator().hasNext();
    }

    /**
     * Returns {@code true} if the sequence contains no elements that match the given predicate.
     *
     * @param predicate The predicate to match.
     */
    default boolean none(Predicate<? super T> predicate) {
        return filter(predicate).none();
    }

    default T reduce(BinaryOperator<T> operation) {
        return SeqHelpers.reduce(SeqHelpers.nonEmpty(this), operation);
    }

    default Optional<T> reduceOptional(BinaryOperator<T> operation) {
        return SeqHelpers.optional(this).map(it -> SeqHelpers.reduce(it, operation));
    }

    /**
     * Returns the single element in the sequence,
     * or throws an exception if there is not exactly one element.
     *
     * @return The single element.
     */
    default T single() {
        return SeqHelpers.single(SeqHelpers.nonEmpty(this), true);
    }

    /**
     * Returns the single element in the sequence that matches the given predicate,
     * or throws an exception if there is not exactly one element.
     *
     * @param predicate The predicate to match.
     * @return The single element.
     */
    default T single(Predicate<? super T> predicate) {
        return filter(predicate).single();
    }

    /**
     * Returns the single element in the sequence,
     * or an empty {@link Optional} if the sequence does not contain exactly one element.
     *
     * @return The single element.
     */
    default Optional<T> singleOptional() {
        return SeqHelpers.optional(this).map(it -> SeqHelpers.single(it, false));
    }

    /**
     * Returns the single element in the sequence that matches the given predicate,
     * or an empty {@link Optional} if the sequence does not contain exactly one element.
     *
     * @param predicate The predicate to match.
     * @return The single element.
     */
    default Optional<T> singleOptional(Predicate<? super T> predicate) {
        return filter(predicate).singleOptional();
    }

    /**
     * Returns the sum of the elements in the sequence, applying the given function to each element.
     *
     * @param mapper The function to apply to each element.
     * @return The sum.
     */
    default int sum(ToIntFunction<? super T> mapper) {
        return SeqHelpers.fold(iterator(), mapper, 0, Integer::sum);
    }

    /**
     * Returns the sum of the elements in the sequence, applying the given function to each element.
     *
     * @param mapper The function to apply to each element.
     * @return The sum.
     */
    default long sum(ToLongFunction<? super T> mapper) {
        return SeqHelpers.fold(iterator(), mapper, 0, Long::sum);
    }

    /**
     * Returns the sum of the elements in the sequence, applying the given function to each element.
     *
     * @param mapper The function to apply to each element.
     * @return The sum.
     */
    default double sum(ToDoubleFunction<? super T> mapper) {
        return SeqHelpers.fold(iterator(), mapper, 0, Double::sum);
    }

    /**
     * Returns the summary of the elements in the sequence, applying the given function to each element.
     *
     * @param mapper The function to apply to each element.
     */
    default IntSummaryStatistics summary(ToIntFunction<? super T> mapper) {
        return SeqHelpers.fold(iterator(), new IntSummaryStatistics(), (statistics, element) -> {
            statistics.accept(mapper.applyAsInt(element));
            return statistics;
        });
    }

    /**
     * Returns the summary of the elements in the sequence, applying the given function to each element.
     *
     * @param mapper The function to apply to each element.
     */
    default LongSummaryStatistics summary(ToLongFunction<? super T> mapper) {
        return SeqHelpers.fold(iterator(), new LongSummaryStatistics(), (statistics, element) -> {
            statistics.accept(mapper.applyAsLong(element));
            return statistics;
        });
    }

    /**
     * Returns the summary of the elements in the sequence, applying the given function to each element.
     *
     * @param mapper The function to apply to each element.
     */
    default DoubleSummaryStatistics summary(ToDoubleFunction<? super T> mapper) {
        return SeqHelpers.fold(iterator(), new DoubleSummaryStatistics(), (statistics, element) -> {
            statistics.accept(mapper.applyAsDouble(element));
            return statistics;
        });
    }

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

    default List<T> toImmutableList() {
        return ImmutableList.copyOf(toList());
    }

    default Set<T> toImmutableSet() {
        return ImmutableSet.copyOf(toList());
    }

    default <K, V, M extends Map<K, V>> M toMap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends V> valueMapper, M destination) {
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

}
