package be.twofold.common.seq;

import be.twofold.common.Check;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
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
        return new IterableSeq<>(iterable);
    }

    // endregion

    // region Intermediate Operations

    public final Sequence<T> distinct() {
        Set<T> seen = new HashSet<>();
        return filter(seen::add);
    }


    public final Sequence<T> filter(Predicate<? super T> predicate) {
        Check.notNull(predicate, "predicate");
        return wrap(() -> new FilterItr<>(iterator(), predicate));
    }

    public final Sequence<T> filterIndexed(BiPredicate<Integer, ? super T> predicate) {
        Check.notNull(predicate, "predicate");

        AtomicInteger index = new AtomicInteger();
        return filter(t -> predicate.test(index.getAndIncrement(), t));
    }


    public final Sequence<Entry<Integer, T>> indexed() {
        AtomicInteger index = new AtomicInteger();
        return map(t -> new AbstractMap.SimpleImmutableEntry<>(index.getAndIncrement(), t));
    }


    public final <R> Sequence<R> map(Function<? super T, ? extends R> mapper) {
        Check.notNull(mapper, "mapper");
        return wrap(() -> new MapItr<>(iterator(), mapper));
    }

    public final <R> Sequence<R> mapIndexed(BiFunction<Integer, ? super T, ? extends R> mapper) {
        Check.notNull(mapper, "mapper");

        AtomicInteger index = new AtomicInteger();
        return map(t -> mapper.apply(index.getAndIncrement(), t));
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

        AtomicInteger index = new AtomicInteger();
        return map(t -> {
            action.accept(index.getAndIncrement(), t);
            return t;
        });
    }


    public final Sequence<T> once() {
        return this instanceof OnceSeq ? this : new OnceSeq<>(this);
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
        Check.notNull(consumer);

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
