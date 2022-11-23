package be.twofold.common.seq;

import be.twofold.common.*;

import java.util.*;
import java.util.function.*;

final class SeqHelpers {

    static final Seq<?> Empty = Collections::emptyIterator;

    private SeqHelpers() {
        throw new UnsupportedOperationException();
    }

    static <T> double average(Iterator<T> iterator, ToDoubleFunction<? super T> mapper) {
        double sum = 0;
        int count = 0;
        while (iterator.hasNext()) {
            sum += mapper.applyAsDouble(iterator.next());
            count++;
        }
        return sum / count;
    }

    static <T> double average(Iterator<T> iterator, ToIntFunction<? super T> mapper) {
        double sum = 0;
        int count = 0;
        while (iterator.hasNext()) {
            sum += mapper.applyAsInt(iterator.next());
            count++;
        }
        return sum / count;
    }

    static <T> double average(Iterator<T> iterator, ToLongFunction<? super T> mapper) {
        double sum = 0;
        int count = 0;
        while (iterator.hasNext()) {
            sum += mapper.applyAsLong(iterator.next());
            count++;
        }
        return sum / count;
    }

    static <E, R> R fold(Iterator<E> iterator, R initial, BiFunction<? super R, ? super E, ? extends R> operation) {
        Check.notNull(operation, "operation");

        R accumulator = initial;
        while (iterator.hasNext()) {
            accumulator = operation.apply(accumulator, iterator.next());
        }
        return accumulator;
    }

    static <E> double fold(Iterator<E> iterator, ToDoubleFunction<? super E> mapper, double initial, DoubleBinaryOperator operator) {
        Check.notNull(operator, "operator");
        Check.notNull(mapper, "mapper");

        double accumulator = initial;
        while (iterator.hasNext()) {
            accumulator = operator.applyAsDouble(accumulator, mapper.applyAsDouble(iterator.next()));
        }
        return accumulator;
    }

    static <E> int fold(Iterator<E> iterator, ToIntFunction<? super E> mapper, int initial, IntBinaryOperator operator) {
        Check.notNull(operator, "operator");
        Check.notNull(mapper, "mapper");

        int accumulator = initial;
        while (iterator.hasNext()) {
            accumulator = operator.applyAsInt(accumulator, mapper.applyAsInt(iterator.next()));
        }
        return accumulator;
    }

    static <E> long fold(Iterator<E> iterator, ToLongFunction<? super E> mapper, long initial, LongBinaryOperator operator) {
        Check.notNull(operator, "operator");
        Check.notNull(mapper, "mapper");

        long accumulator = initial;
        while (iterator.hasNext()) {
            accumulator = operator.applyAsLong(accumulator, mapper.applyAsLong(iterator.next()));
        }
        return accumulator;
    }

    static <T> T last(Iterator<T> iterator) {
        T last = iterator.next();
        while (iterator.hasNext()) {
            last = iterator.next();
        }
        return last;
    }

    @SuppressWarnings("unchecked")
    static <T> T max(Iterator<T> iterator) {
        return max(iterator, (Comparator<? super T>) Comparator.naturalOrder());
    }

    static <T> T max(Iterator<T> iterator, Comparator<? super T> comparator) {
        return reduce(iterator, (a, b) -> comparator.compare(a, b) > 0 ? a : b);
    }

    @SuppressWarnings("unchecked")
    static <T> T min(Iterator<T> iterator) {
        return min(iterator, (Comparator<? super T>) Comparator.naturalOrder());
    }

    static <T> T min(Iterator<T> iterator, Comparator<? super T> comparator) {
        return reduce(iterator, (a, b) -> comparator.compare(a, b) < 0 ? a : b);
    }

    static <T> Iterator<T> nonEmpty(Seq<T> seq) {
        Iterator<T> iterator = seq.iterator();
        if (!iterator.hasNext()) {
            throw new NoSuchElementException("Sequence contains no elements");
        }
        return iterator;
    }

    static <T> Optional<Iterator<T>> optional(Seq<T> seq) {
        return Optional
            .of(seq.iterator())
            .filter(Iterator::hasNext);
    }

    static <E> E reduce(Iterator<E> iterator, BinaryOperator<E> operator) {
        return fold(iterator, iterator.next(), operator);
    }

    static <T> T single(Iterator<T> it, boolean throwException) {
        T result = it.next();
        if (!it.hasNext()) {
            return result;
        }
        if (throwException) {
            throw new IllegalArgumentException("Sequence contains more than one element");
        }
        return null;
    }

}
