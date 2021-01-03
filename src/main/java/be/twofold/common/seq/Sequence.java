package be.twofold.common.seq;

import be.twofold.common.*;
import be.twofold.common.seq.Sequences.*;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

@FunctionalInterface
public interface Sequence<T> extends Iterable<T> {

    // region Factory Methods

    static <T> Sequence<T> emptySequence() {
        return Collections::emptyIterator;
    }

    @SafeVarargs
    static <T> Sequence<T> sequenceOf(T... values) {
        if (values == null || values.length == 0) {
            return emptySequence();
        }
        return () -> Arrays.asList(values).iterator();
    }

    static <T> Sequence<T> sequence(Enumeration<T> enumeration) {
        return ((Sequence<T>) () -> new EnumerationIterator<>(enumeration)).onlyOnce();
    }

    static <T> Sequence<T> sequence(Iterable<T> iterable) {
        return iterable::iterator;
    }

    static <T> Sequence<T> sequence(Iterator<T> iterator) {
        return ((Sequence<T>) () -> iterator).onlyOnce();
    }

    static <T> Sequence<T> sequence(Stream<T> stream) {
        return ((Sequence<T>) stream::iterator).onlyOnce();
    }

    // endregion

    // region Intermediate Operations

    default Sequence<T> distinct() {
        return () -> new DistinctIterator<>(iterator());
    }


    default Sequence<T> filter(Predicate<? super T> predicate) {
        Check.notNull(predicate, "predicate");
        return () -> new FilterIterator<>(iterator(), predicate);
    }

    default Sequence<T> filterIndexed(BiPredicate<Integer, ? super T> predicate) {
        Check.notNull(predicate, "predicate");
        return indexed()
            .filter(e -> predicate.test(e.getKey(), e.getValue()))
            .map(Map.Entry::getValue);
    }


    default Sequence<Map.Entry<Integer, T>> indexed() {
        return () -> new IndexedIterator<>(iterator());
    }


    default <R> Sequence<R> map(Function<? super T, ? extends R> mapper) {
        Check.notNull(mapper, "mapper");
        return () -> new MapIterator<>(iterator(), mapper);
    }

    default <R> Sequence<R> mapIndexed(BiFunction<Integer, ? super T, ? extends R> mapper) {
        Check.notNull(mapper, "mapper");
        return indexed()
            .map(e -> mapper.apply(e.getKey(), e.getValue()));
    }


    default Sequence<T> onEach(Consumer<? super T> action) {
        Check.notNull(action, "action");
        return map(element -> {
            action.accept(element);
            return element;
        });
    }

    default Sequence<T> onEachIndexed(BiConsumer<Integer, ? super T> action) {
        Check.notNull(action, "action");
        return indexed()
            .map(e -> {
                action.accept(e.getKey(), e.getValue());
                return e.getValue();
            });
    }


    default Sequence<T> onlyOnce() {
        return this instanceof OnlyOnceSequence ? this : new OnlyOnceSequence<>(this);
    }

    @SuppressWarnings("unchecked")
    default Sequence<T> sorted() {
        return sorted((Comparator<? super T>) Comparator.naturalOrder());
    }

    default Sequence<T> sorted(Comparator<? super T> comparator) {
        Check.notNull(comparator, "comparator");
        return () -> {
            List<T> list = toList();
            list.sort(comparator);
            return list.iterator();
        };
    }


    default Sequence<T> take(int count) {
        Check.argument(count >= 0);
        if (count == 0) {
            return emptySequence();
        }
        return () -> new TakeIterator<>(iterator(), count);
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
        Check.notNull(predicate, "predicate");

        for (T element : this) {
            if (predicate.test(element)) {
                return true;
            }
        }
        return false;
    }


    default int count() {
        int count = 0;
        for (T ignored : this) {
            count++;
        }
        return count;
    }

    default int count(Predicate<? super T> predicate) {
        Check.notNull(predicate, "predicate");

        int count = 0;
        for (T element : this) {
            if (predicate.test(element)) {
                count++;
            }
        }
        return count;
    }


    default T first() {
        Iterator<T> iterator = iterator();
        if (!iterator.hasNext()) {
            throw new NoSuchElementException("Empty sequence");
        }
        return iterator.next();
    }

    default Optional<T> firstOptional() {
        Iterator<T> iterator = iterator();
        if (!iterator.hasNext()) {
            return Optional.empty();
        }
        return Optional.of(iterator.next());
    }

    default T first(Predicate<? super T> predicate) {
        Check.notNull(predicate, "predicate");

        for (T element : this) {
            if (predicate.test(element)) {
                return element;
            }
        }
        throw new NoSuchElementException("No matching element in sequence");
    }

    default Optional<T> firstOptional(Predicate<? super T> predicate) {
        Check.notNull(predicate, "predicate");

        for (T element : this) {
            if (predicate.test(element)) {
                return Optional.of(element);
            }
        }
        return Optional.empty();
    }


    default T last() {
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

    default Optional<T> lastOptional() {
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

    default T last(Predicate<? super T> predicate) {
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

    default Optional<T> lastOptional(Predicate<? super T> predicate) {
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


    default void forEach(Consumer<? super T> consumer) {
        Check.notNull(consumer, "consumer");

        for (T element : this) {
            consumer.accept(element);
        }
    }

    default void forEachIndexed(BiConsumer<Integer, ? super T> consumer) {
        Check.notNull(consumer);

        int count = 0;
        for (T element : this) {
            consumer.accept(count++, element);
        }
    }


    default boolean none() {
        return !iterator().hasNext();
    }

    default boolean none(Predicate<? super T> predicate) {
        Check.notNull(predicate, "predicate");

        for (T element : this) {
            if (predicate.test(element)) {
                return false;
            }
        }
        return true;
    }


    default T reduce(BinaryOperator<T> operation) {
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

    default <R> R reduce(R identity, BiFunction<R, ? super T, R> operation) {
        Check.notNull(operation, "operation");

        R result = identity;
        for (T element : this) {
            result = operation.apply(result, element);
        }
        return result;
    }


    default <C extends Collection<? super T>> C toCollection(C collection) {
        Check.notNull(collection, "collection");

        for (T element : this) {
            collection.add(element);
        }
        return collection;
    }

    default List<T> toList() {
        return toCollection(new ArrayList<>());
    }

    default List<T> toUnmodifiableList() {
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

    default Set<T> toSet() {
        return toCollection(new HashSet<>());
    }

    default Set<T> toUnmodifiableSet() {
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

    // endregion

}
