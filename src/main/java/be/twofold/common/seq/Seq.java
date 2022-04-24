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

    public final Seq<T> distinct() {
        Set<T> seen = new HashSet<>();
        return filter(seen::add);
    }

    public final Seq<T> drop(int count) {
        Check.argument(count >= 0, "Negative count");
        if (count == 0) {
            return this;
        }
        return seq(() -> new DropIterator<>(iterator(), count));
    }

    public final Seq<T> filter(Predicate<? super T> predicate) {
        Check.notNull(predicate, "predicate");

        return seq(() -> new FilterIterator<>(iterator(), predicate));
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

    public final <R> Seq<R> flatMapIndexed(BiFunction<Integer, ? super T, ? extends Iterable<? extends R>> mapper) {
        Check.notNull(mapper, "mapper");

        AtomicInteger index = new AtomicInteger();
        return flatMap(t -> mapper.apply(index.getAndIncrement(), t));
    }

    public final Seq<Pair<Integer, T>> indexed() {
        AtomicInteger index = new AtomicInteger();
        return map(t -> Pair.of(index.getAndIncrement(), t));
    }

    public final <R> Seq<R> map(Function<? super T, ? extends R> mapper) {
        Check.notNull(mapper, "mapper");

        return seq(() -> new MapIterator<>(iterator(), mapper));
    }

    public final <R> Seq<R> mapIndexed(BiFunction<Integer, ? super T, ? extends R> mapper) {
        Check.notNull(mapper, "mapper");

        AtomicInteger index = new AtomicInteger();
        return map(t -> mapper.apply(index.getAndIncrement(), t));
    }

    public final Seq<T> once() {
        return this instanceof OnceSeq ? this : new OnceSeq<>(this);
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
        }
        return seq(() -> new TakeIterator<>(iterator(), count));
    }

    // Terminal Operations

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

    public final int count() {
        int count = 0;
        for (T ignored : this) {
            count++;
        }
        return count;
    }

    public final int count(Predicate<? super T> predicate) {
        return filter(predicate).count();
    }


    // first

    public final T first() {
        return nonEmpty(iterator()).next();
    }

    public final T first(Predicate<? super T> predicate) {
        return filter(predicate).first();
    }

    public final Optional<T> firstOptional() {
        return optional(iterator()).map(Iterator::next);
    }

    public final Optional<T> firstOptional(Predicate<? super T> predicate) {
        return filter(predicate).firstOptional();
    }


    public final void forEach(Consumer<? super T> consumer) {
        Check.notNull(consumer, "consumer");

        for (T element : this) {
            consumer.accept(element);
        }
    }

    public final void forEachIndexed(BiConsumer<Integer, ? super T> consumer) {
        Check.notNull(consumer, "consumer");

        AtomicInteger index = new AtomicInteger();
        forEach(t -> consumer.accept(index.getAndIncrement(), t));
    }

    public final <R> R fold(R initial, BiFunction<R, ? super T, ? extends R> operation) {
        return fold(iterator(), initial, operation);
    }


    // last

    public final T last() {
        Iterator<T> iterator = nonEmpty(iterator());
        return last(iterator);
    }

    public final T last(Predicate<? super T> predicate) {
        return filter(predicate).last();
    }

    public final Optional<T> lastOptional() {
        return optional(iterator()).map(this::last);
    }

    public final Optional<T> lastOptional(Predicate<? super T> predicate) {
        return filter(predicate).lastOptional();
    }

    private T last(Iterator<T> iterator) {
        T last = iterator.next();
        while (iterator.hasNext()) {
            last = iterator.next();
        }
        return last;
    }


    public final boolean none() {
        return !iterator().hasNext();
    }

    public final boolean none(Predicate<? super T> predicate) {
        return filter(predicate).none();
    }

    public final T reduce(BinaryOperator<T> operation) {
        return reduce(iterator(), operation);
    }

    // single

    public final T single() {
        return single(nonEmpty(iterator()), true);
    }

    public final T single(Predicate<? super T> predicate) {
        return filter(predicate).single();
    }

    public final Optional<T> singleOptional() {
        return optional(iterator()).map(it -> single(it, false));
    }

    public final Optional<T> singleOptional(Predicate<? super T> predicate) {
        return filter(predicate).singleOptional();
    }

    private T single(Iterator<T> it, boolean throwException) {
        T result = it.next();
        if (it.hasNext()) {
            if (throwException) {
                throw new IllegalArgumentException("Sequence contains more than one element");
            }
            return null;
        }
        return result;
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

    private static <E, R> R fold(Iterator<E> iterator, R initial, BiFunction<R, ? super E, ? extends R> operation) {
        Check.notNull(operation, "operation");

        R accumulator = initial;
        while (iterator.hasNext()) {
            accumulator = operation.apply(accumulator, iterator.next());
        }
        return accumulator;
    }

    private static <E> E reduce(Iterator<E> iterator, BinaryOperator<E> operator) {
        return fold(nonEmpty(iterator), iterator.next(), operator);
    }

    private static <E> Iterator<E> nonEmpty(Iterator<E> iterator) {
        if (!iterator.hasNext()) {
            throw new NoSuchElementException();
        }
        return iterator;
    }

    private static <E> Optional<Iterator<E>> optional(Iterator<E> iterator) {
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
