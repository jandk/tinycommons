package be.twofold.common.seq;

import be.twofold.common.*;
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
public abstract class Seq<T> implements Iterable<T> {

    private static final Seq<?> Empty = seq(Collections::emptyIterator);

    // Construction

    private Seq() {
    }

    /**
     * Creates a new empty Seq
     *
     * @param <T> The type of the elements
     * @return The new Seq
     */
    @SuppressWarnings("unchecked")
    public static <T> Seq<T> empty() {
        return (Seq<T>) Empty;
    }

    /**
     * Creates a new Seq from the specified elements.
     *
     * @param <T> The type of the elements
     * @return The new Seq
     */
    @SafeVarargs
    public static <T> Seq<T> of(T... elements) {
        Check.notNull(elements, "elements is null");
        if (elements.length == 0) {
            return empty();
        }
        return seq(() -> Arrays.asList(elements).iterator());
    }

    /**
     * Creates a new Seq from an existing iterable
     */
    public static <T> Seq<T> seq(Iterable<T> iterable) {
        return new IterableSeq<>(iterable);
    }

    public static <T> Seq<T> seq(Iterator<T> iterator) {
        Check.notNull(iterator, "iterator is null");
        return seq(() -> iterator).once();
    }

    public static <T> Seq<T> seq(Stream<T> stream) {
        return seq(stream::iterator).once();
    }

    // Intermediate Operations

    /**
     * Returns a sequence containing only distinct elements from this sequence.
     * The elements are compared using {@link Object#equals(Object)}.
     *
     * @return The new sequence.
     */
    public final Seq<T> distinct() {
        Set<T> seen = new HashSet<>();
        return filter(seen::add);
    }

    /**
     * Drops the first {@code count} elements of this sequence.
     *
     * @param count The number of elements to drop.
     * @return The new sequence.
     */
    public final Seq<T> drop(int count) {
        Check.argument(count >= 0, "Negative count");
        if (count == 0) {
            return this;
        }
        return seq(() -> new DropIterator<>(iterator(), count));
    }

    /**
     * Returns a sequence containing only the elements matching the given predicate.
     *
     * @param predicate The predicate to match.
     * @return The new sequence.
     */
    public final Seq<T> filter(Predicate<? super T> predicate) {
        Check.notNull(predicate, "predicate");

        return seq(() -> new FilterIterator<>(iterator(), predicate));
    }

    /**
     * Returns a sequence containing only the elements matching the given predicate.
     *
     * @param predicate The predicate to match.
     * @return The new sequence.
     */
    public final Seq<T> filterIndexed(BiPredicate<Integer, ? super T> predicate) {
        Check.notNull(predicate, "predicate");

        AtomicInteger index = new AtomicInteger();
        return filter(t -> predicate.test(index.getAndIncrement(), t));
    }

    /**
     * Returns a single sequence of all elements from results of applying
     * the given function to each element of this sequence.
     *
     * @param mapper The function to apply to each element.
     * @param <R>    The type of the elements of the new sequence.
     * @return The new sequence.
     */
    public final <R> Seq<R> flatMap(Function<? super T, ? extends Iterable<? extends R>> mapper) {
        Check.notNull(mapper, "mapper");

        return seq(() -> new FlatMapIterator<>(iterator(), mapper));
    }

    /**
     * Returns a single sequence of all elements from results of applying
     * the given function to each element of this sequence.
     *
     * @param mapper The function to apply to each element.
     * @param <R>    The type of the elements of the new sequence.
     * @return The new sequence.
     */
    public final <R> Seq<R> flatMapIndexed(BiFunction<Integer, ? super T, ? extends Iterable<? extends R>> mapper) {
        Check.notNull(mapper, "mapper");

        AtomicInteger index = new AtomicInteger();
        return flatMap(t -> mapper.apply(index.getAndIncrement(), t));
    }

    /**
     * Returns a sequence of pairs of indexes and elements of this sequence.
     *
     * @return The new sequence.
     */
    public final Seq<Pair<Integer, T>> indexed() {
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
    public final <R> Seq<R> map(Function<? super T, ? extends R> mapper) {
        Check.notNull(mapper, "mapper");

        return seq(() -> new MapIterator<>(iterator(), mapper));
    }

    /**
     * Returns a sequence containing the results of applying the given function to each element of this sequence.
     *
     * @param mapper The function to apply to each element.
     * @param <R>    The type of the elements of the new sequence.
     * @return The new sequence.
     */
    public final <R> Seq<R> mapIndexed(BiFunction<Integer, ? super T, ? extends R> mapper) {
        Check.notNull(mapper, "mapper");

        AtomicInteger index = new AtomicInteger();
        return map(t -> mapper.apply(index.getAndIncrement(), t));
    }

    /**
     * Returns a sequence that can be iterated over only once.
     *
     * @return The new sequence.
     */
    public final Seq<T> once() {
        return this instanceof OnceSeq ? this : new OnceSeq<>(this);
    }

    /**
     * Perform the given action for each element in the sequence, returning the sequence itself.
     *
     * @param action The action to be performed for each element.
     */
    public final Seq<T> onEach(Consumer<? super T> action) {
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
    public final Seq<T> onEachIndexed(BiConsumer<Integer, ? super T> action) {
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
    public final Seq<T> sorted() {
        return sorted((Comparator<? super T>) Comparator.naturalOrder());
    }

    /**
     * Returns a sorted sequence containing the elements of this sequence, using the given comparator.
     *
     * @param comparator The comparator to use to compare elements.
     * @return The new sequence.
     */
    public final Seq<T> sorted(Comparator<? super T> comparator) {
        Check.notNull(comparator, "comparator");

        return seq(() -> {
            List<T> list = toList();
            list.sort(comparator);
            return list.iterator();
        });
    }

    /**
     * Returns a sequence containing the first {@code count} elements.
     *
     * @param count The number of elements to take.
     * @return The new sequence.
     */
    public final Seq<T> take(int count) {
        Check.argument(count >= 0, "Negative count");
        if (count == 0) {
            return empty();
        }
        return seq(() -> new TakeIterator<>(iterator(), count));
    }

    //
    // Terminal Operations
    //

    // all

    public final boolean all(Predicate<? super T> predicate) {
        Check.notNull(predicate, "predicate");

        for (T element : this) {
            if (!predicate.test(element)) {
                return false;
            }
        }
        return true;
    }


    // any

    /**
     * Returns true if the sequence has at least one element.
     */
    public final boolean any() {
        return iterator().hasNext();
    }

    /**
     * Returns true if the sequence has at least one element that matches the given predicate.
     *
     * @param predicate The predicate to match.
     */
    public final boolean any(Predicate<? super T> predicate) {
        return filter(predicate).any();
    }


    // average

    public final double average(ToIntFunction<? super T> mapper) {
        return average(nonEmptyIterator(), mapper);
    }

    public final double average(ToLongFunction<? super T> mapper) {
        return average(nonEmptyIterator(), mapper);
    }

    public final double average(ToDoubleFunction<? super T> mapper) {
        return average(nonEmptyIterator(), mapper);
    }

    public final OptionalDouble averageOptional(ToIntFunction<? super T> mapper) {
        return optionalIterator()
            .map(it -> OptionalDouble.of(average(it, mapper)))
            .orElse(OptionalDouble.empty());
    }

    public final OptionalDouble averageOptional(ToLongFunction<? super T> mapper) {
        return optionalIterator()
            .map(it -> OptionalDouble.of(average(it, mapper)))
            .orElse(OptionalDouble.empty());
    }

    public final OptionalDouble averageOptional(ToDoubleFunction<? super T> mapper) {
        return optionalIterator()
            .map(it -> OptionalDouble.of(average(it, mapper)))
            .orElse(OptionalDouble.empty());
    }

    private static <T> double average(Iterator<T> iterator, ToIntFunction<? super T> mapper) {
        double sum = 0;
        int count = 0;
        while (iterator.hasNext()) {
            sum += mapper.applyAsInt(iterator.next());
            count++;
        }
        return sum / count;
    }

    private static <T> double average(Iterator<T> iterator, ToLongFunction<? super T> mapper) {
        double sum = 0;
        int count = 0;
        while (iterator.hasNext()) {
            sum += mapper.applyAsLong(iterator.next());
            count++;
        }
        return sum / count;
    }

    private static <T> double average(Iterator<T> iterator, ToDoubleFunction<? super T> mapper) {
        double sum = 0;
        int count = 0;
        while (iterator.hasNext()) {
            sum += mapper.applyAsDouble(iterator.next());
            count++;
        }
        return sum / count;
    }


    // contains

    /**
     * Returns true if the sequence contains the given element.
     *
     * @param element The element to search for.
     */
    public final boolean contains(T element) {
        return indexOf(element) >= 0;
    }


    // count

    /**
     * Returns the number of elements in the sequence.
     */
    public final int count() {
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
    public final int count(Predicate<? super T> predicate) {
        return filter(predicate).count();
    }


    // first

    /**
     * Returns the first element in the sequence.
     */
    public final T first() {
        return nonEmptyIterator().next();
    }

    /**
     * Returns the first element in the sequence that matches the given predicate.
     *
     * @param predicate The predicate to match.
     */
    public final T first(Predicate<? super T> predicate) {
        return filter(predicate).first();
    }

    /**
     * Returns the first element in the sequence,
     * or an empty {@link Optional} if the sequence is empty.
     */
    public final Optional<T> firstOptional() {
        return optionalIterator().map(Iterator::next);
    }

    /**
     * Returns the first element in the sequence that matches the given predicate,
     * or an empty {@link Optional} if the sequence is empty.
     *
     * @param predicate The predicate to match.
     */
    public final Optional<T> firstOptional(Predicate<? super T> predicate) {
        return filter(predicate).firstOptional();
    }


    // forEach

    /**
     * Perform the given action for each element in the sequence.
     *
     * @param consumer The action to be performed for each element
     */
    public final void forEach(Consumer<? super T> consumer) {
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
    public final void forEachIndexed(BiConsumer<Integer, ? super T> consumer) {
        Check.notNull(consumer, "consumer");

        AtomicInteger index = new AtomicInteger();
        forEach(t -> consumer.accept(index.getAndIncrement(), t));
    }


    // fold

    /**
     * Accumulates the elements of the sequence into a single value.
     *
     * @param initial   The initial value.
     * @param operation The operation to perform on each element.
     * @param <R>       The type of the accumulated value.
     * @return The accumulated value.
     */
    public final <R> R fold(R initial, BiFunction<R, ? super T, ? extends R> operation) {
        return fold(iterator(), initial, operation);
    }

    private static <E, R> R fold(Iterator<E> iterator, R initial, BiFunction<R, ? super E, ? extends R> operation) {
        Check.notNull(operation, "operation");

        R accumulator = initial;
        while (iterator.hasNext()) {
            accumulator = operation.apply(accumulator, iterator.next());
        }
        return accumulator;
    }

    private static <E> int fold(Iterator<E> iterator, ToIntFunction<? super E> mapper, int initial, IntBinaryOperator operator) {
        Check.notNull(operator, "operator");
        Check.notNull(mapper, "mapper");

        int accumulator = initial;
        while (iterator.hasNext()) {
            accumulator = operator.applyAsInt(accumulator, mapper.applyAsInt(iterator.next()));
        }
        return accumulator;
    }

    private static <E> long fold(Iterator<E> iterator, ToLongFunction<? super E> mapper, long initial, LongBinaryOperator operator) {
        Check.notNull(operator, "operator");
        Check.notNull(mapper, "mapper");

        long accumulator = initial;
        while (iterator.hasNext()) {
            accumulator = operator.applyAsLong(accumulator, mapper.applyAsLong(iterator.next()));
        }
        return accumulator;
    }

    private static <E> double fold(Iterator<E> iterator, ToDoubleFunction<? super E> mapper, double initial, DoubleBinaryOperator operator) {
        Check.notNull(operator, "operator");
        Check.notNull(mapper, "mapper");

        double accumulator = initial;
        while (iterator.hasNext()) {
            accumulator = operator.applyAsDouble(accumulator, mapper.applyAsDouble(iterator.next()));
        }
        return accumulator;
    }


    // indexOf

    /**
     * Returns the index of the first occurrence of the specified element in the sequence,
     * or -1 if the sequence does not contain the element.
     *
     * @param element The element to search for.
     */
    public final int indexOf(T element) {
        return indexOf(t -> Objects.equals(t, element));
    }

    /**
     * Returns the index of the first element matching the given predicate,
     * or -1 if the sequence does not contain any element matching the predicate.
     *
     * @param predicate The predicate to match.
     */
    public final int indexOf(Predicate<? super T> predicate) {
        int index = 0;
        for (T element : this) {
            if (predicate.test(element)) {
                return index;
            }
            index++;
        }
        return -1;
    }


    // last

    /**
     * Returns the last element in the sequence.
     */
    public final T last() {
        return last(nonEmptyIterator());
    }

    /**
     * Returns the last element in the sequence that matches the given predicate.
     *
     * @param predicate The predicate to match.
     */
    public final T last(Predicate<? super T> predicate) {
        return filter(predicate).last();
    }

    /**
     * Returns the last element in the sequence,
     * or an empty {@link Optional} if the sequence is empty.
     */
    public final Optional<T> lastOptional() {
        return optionalIterator().map(Seq::last);
    }

    /**
     * Returns the last element in the sequence that matches the given predicate,
     * or an empty {@link Optional} if the sequence is empty.
     *
     * @param predicate The predicate to match.
     */
    public final Optional<T> lastOptional(Predicate<? super T> predicate) {
        return filter(predicate).lastOptional();
    }

    private static <T> T last(Iterator<T> iterator) {
        T last = iterator.next();
        while (iterator.hasNext()) {
            last = iterator.next();
        }
        return last;
    }


    // lastIndexOf

    /**
     * Returns the index of the first occurrence of the specified element in the sequence,
     * or -1 if the sequence does not contain the element.
     *
     * @param element The element to search for.
     */
    public final int lastIndexOf(T element) {
        return lastIndexOf(t -> Objects.equals(t, element));
    }

    /**
     * Returns the index of the first element matching the given predicate,
     * or -1 if the sequence does not contain any element matching the predicate.
     *
     * @param predicate The predicate to match.
     */
    public final int lastIndexOf(Predicate<? super T> predicate) {
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


    // max

    /**
     * Returns the maximum element in the sequence.
     */
    public final T max() {
        return max(nonEmptyIterator());
    }

    /**
     * Returns the maximum element in the sequence, using the given comparator.
     *
     * @param comparator The comparator to use.
     */
    public final T max(Comparator<? super T> comparator) {
        return max(nonEmptyIterator(), comparator);
    }

    /**
     * Returns the maximum element in the sequence,
     * or an empty {@link Optional} if the sequence is empty.
     */
    public final Optional<T> maxOptional() {
        return optionalIterator().map(Seq::max);
    }

    /**
     * Returns the maximum element in the sequence, using the given comparator,
     * or an empty {@link Optional} if the sequence is empty.
     *
     * @param comparator The comparator to use.
     */
    public final Optional<T> maxOptional(Comparator<? super T> comparator) {
        return optionalIterator().map(it -> max(it, comparator));
    }

    @SuppressWarnings("unchecked")
    private static <T> T max(Iterator<T> iterator) {
        return max(iterator, (Comparator<? super T>) Comparator.naturalOrder());
    }

    private static <T> T max(Iterator<T> iterator, Comparator<? super T> comparator) {
        return reduce(iterator, (a, b) -> comparator.compare(a, b) > 0 ? a : b);
    }


    // min

    /**
     * Returns the minimum element in the sequence.
     */
    public final T min() {
        return min(nonEmptyIterator());
    }

    /**
     * Returns the minimum element in the sequence, using the given comparator.
     *
     * @param comparator The comparator to use.
     */
    public final T min(Comparator<? super T> comparator) {
        return min(nonEmptyIterator(), comparator);
    }

    /**
     * Returns the minimum element in the sequence,
     * or an empty {@link Optional} if the sequence is empty.
     */
    public final Optional<T> minOptional() {
        return optionalIterator().map(Seq::min);
    }

    /**
     * Returns the minimum element in the sequence, using the given comparator,
     * or an empty {@link Optional} if the sequence is empty.
     *
     * @param comparator The comparator to use.
     */
    public final Optional<T> minOptional(Comparator<? super T> comparator) {
        return optionalIterator().map(it -> min(it, comparator));
    }

    @SuppressWarnings("unchecked")
    private static <T> T min(Iterator<T> iterator) {
        return min(iterator, (Comparator<? super T>) Comparator.naturalOrder());
    }

    private static <T> T min(Iterator<T> iterator, Comparator<? super T> comparator) {
        return reduce(iterator, (a, b) -> comparator.compare(a, b) < 0 ? a : b);
    }


    // none

    /**
     * Returns {@code true} if the sequence contains no elements.
     */
    public final boolean none() {
        return !iterator().hasNext();
    }

    /**
     * Returns {@code true} if the sequence contains no elements that match the given predicate.
     *
     * @param predicate The predicate to match.
     */
    public final boolean none(Predicate<? super T> predicate) {
        return filter(predicate).none();
    }


    // reduce

    public final T reduce(BinaryOperator<T> operation) {
        return reduce(nonEmptyIterator(), operation);
    }

    public final Optional<T> reduceOptional(BinaryOperator<T> operation) {
        return optionalIterator().map(it -> reduce(it, operation));
    }

    private static <E> E reduce(Iterator<E> iterator, BinaryOperator<E> operator) {
        return fold(iterator, iterator.next(), operator);
    }


    // single

    /**
     * Returns the single element in the sequence,
     * or throws an exception if there is not exactly one element.
     *
     * @return The single element.
     */
    public final T single() {
        return single(nonEmptyIterator(), true);
    }

    /**
     * Returns the single element in the sequence that matches the given predicate,
     * or throws an exception if there is not exactly one element.
     *
     * @param predicate The predicate to match.
     * @return The single element.
     */
    public final T single(Predicate<? super T> predicate) {
        return filter(predicate).single();
    }

    /**
     * Returns the single element in the sequence,
     * or an empty {@link Optional} if the sequence does not contain exactly one element.
     *
     * @return The single element.
     */
    public final Optional<T> singleOptional() {
        return optionalIterator().map(it -> single(it, false));
    }

    /**
     * Returns the single element in the sequence that matches the given predicate,
     * or an empty {@link Optional} if the sequence does not contain exactly one element.
     *
     * @param predicate The predicate to match.
     * @return The single element.
     */
    public final Optional<T> singleOptional(Predicate<? super T> predicate) {
        return filter(predicate).singleOptional();
    }

    private static <T> T single(Iterator<T> it, boolean throwException) {
        T result = it.next();
        if (!it.hasNext()) {
            return result;
        }
        if (throwException) {
            throw new IllegalArgumentException("Sequence contains more than one element");
        }
        return null;
    }


    // sum

    /**
     * Returns the sum of the elements in the sequence, applying the given function to each element.
     *
     * @param mapper The function to apply to each element.
     * @return The sum.
     */
    public final int sum(ToIntFunction<? super T> mapper) {
        return fold(iterator(), mapper, 0, Integer::sum);
    }

    /**
     * Returns the sum of the elements in the sequence, applying the given function to each element.
     *
     * @param mapper The function to apply to each element.
     * @return The sum.
     */
    public final long sum(ToLongFunction<? super T> mapper) {
        return fold(iterator(), mapper, 0, Long::sum);
    }

    /**
     * Returns the sum of the elements in the sequence, applying the given function to each element.
     *
     * @param mapper The function to apply to each element.
     * @return The sum.
     */
    public final double sum(ToDoubleFunction<? super T> mapper) {
        return fold(iterator(), mapper, 0, Double::sum);
    }


    // summary

    /**
     * Returns the summary of the elements in the sequence, applying the given function to each element.
     *
     * @param mapper The function to apply to each element.
     */
    public final IntSummaryStatistics summary(ToIntFunction<? super T> mapper) {
        return fold(iterator(), new IntSummaryStatistics(), (statistics, element) -> {
            statistics.accept(mapper.applyAsInt(element));
            return statistics;
        });
    }

    /**
     * Returns the summary of the elements in the sequence, applying the given function to each element.
     *
     * @param mapper The function to apply to each element.
     */
    public final LongSummaryStatistics summary(ToLongFunction<? super T> mapper) {
        return fold(iterator(), new LongSummaryStatistics(), (statistics, element) -> {
            statistics.accept(mapper.applyAsLong(element));
            return statistics;
        });
    }

    /**
     * Returns the summary of the elements in the sequence, applying the given function to each element.
     *
     * @param mapper The function to apply to each element.
     */
    public final DoubleSummaryStatistics summary(ToDoubleFunction<? super T> mapper) {
        return fold(iterator(), new DoubleSummaryStatistics(), (statistics, element) -> {
            statistics.accept(mapper.applyAsDouble(element));
            return statistics;
        });
    }


    //
    // Collectors
    //

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
        return List.copyOf(toList());
    }

    public final Set<T> toUnmodifiableSet() {
        return Set.copyOf(toList());
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

    // Helpers

    private Iterator<T> nonEmptyIterator() {
        Iterator<T> iterator = iterator();
        if (iterator.hasNext()) {
            return iterator;
        }
        throw new NoSuchElementException("Sequence contains no elements");
    }

    private Optional<Iterator<T>> optionalIterator() {
        Iterator<T> iterator = iterator();
        if (iterator.hasNext()) {
            return Optional.of(iterator);
        }
        return Optional.empty();
    }


    // Implementations

    private static final class IterableSeq<T> extends Seq<T> {
        private final Iterable<T> iterable;

        private IterableSeq(Iterable<T> iterable) {
            this.iterable = Check.notNull(iterable, "iterable is null");
        }

        @Override
        public Iterator<T> iterator() {
            return iterable.iterator();
        }
    }

    private static final class OnceSeq<T> extends Seq<T> {
        private final AtomicReference<Seq<T>> reference;

        private OnceSeq(Seq<T> seq) {
            reference = new AtomicReference<>(seq);
        }

        @Override
        public Iterator<T> iterator() {
            Seq<T> seq = reference.getAndSet(null);
            if (seq == null) {
                throw new IllegalStateException("Sequence can only be iterated once");
            }
            return seq.iterator();
        }
    }

    private static final class DropIterator<E> implements Iterator<E> {
        private final Iterator<E> iterator;
        private int count;

        private DropIterator(Iterator<E> iterator, int count) {
            this.iterator = Check.notNull(iterator, "iterator is null");
            this.count = count;
        }

        @Override
        public boolean hasNext() {
            while (count > 0 && iterator.hasNext()) {
                iterator.next();
                count--;
            }
            return iterator.hasNext();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return iterator.next();
        }
    }

    private static final class FilterIterator<E> implements Iterator<E> {
        private final Iterator<E> iterator;
        private final Predicate<? super E> predicate;
        private boolean hasNext;
        private E next;

        private FilterIterator(Iterator<E> iterator, Predicate<? super E> predicate) {
            this.iterator = Check.notNull(iterator, "iterator is null");
            this.predicate = Check.notNull(predicate, "predicate is null");
        }

        @Override
        public boolean hasNext() {
            while (!hasNext && iterator.hasNext()) {
                E next = iterator.next();
                if (predicate.test(next)) {
                    hasNext = true;
                    this.next = next;
                }
            }
            return hasNext;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            hasNext = false;
            E result = next;
            next = null;
            return result;
        }
    }

    private static final class FlatMapIterator<E, R> implements Iterator<R> {
        private final Iterator<E> iterator;
        private final Function<? super E, ? extends Iterable<? extends R>> mapper;
        private Iterator<? extends R> itemIterator = Collections.emptyIterator();

        private FlatMapIterator(Iterator<E> iterator, Function<? super E, ? extends Iterable<? extends R>> mapper) {
            this.iterator = Check.notNull(iterator, "iterator is null");
            this.mapper = Check.notNull(mapper, "mapper is null");
        }

        @Override
        public boolean hasNext() {
            while (true) {
                if (itemIterator.hasNext()) {
                    return true;
                }
                if (!iterator.hasNext()) {
                    return false;
                }
                itemIterator = mapper.apply(iterator.next()).iterator();
            }
        }

        @Override
        public R next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return itemIterator.next();
        }
    }

    private static final class MapIterator<E, R> implements Iterator<R> {
        private final Iterator<E> iterator;
        private final Function<? super E, ? extends R> mapper;

        private MapIterator(Iterator<E> iterator, Function<? super E, ? extends R> mapper) {
            this.iterator = Check.notNull(iterator, "iterator is null");
            this.mapper = Check.notNull(mapper, "mapper is null");
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

    private static final class TakeIterator<E> implements Iterator<E> {
        private final Iterator<E> iterator;
        private int count;

        private TakeIterator(Iterator<E> iterator, int count) {
            this.iterator = Check.notNull(iterator, "iterator is null");
            this.count = count;
        }

        @Override
        public boolean hasNext() {
            return count > 0 && iterator.hasNext();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            count--;
            return iterator.next();
        }
    }

}
