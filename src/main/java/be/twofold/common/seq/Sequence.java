package be.twofold.common.seq;

import be.twofold.common.seq.Sequences.*;

import java.util.*;
import java.util.stream.*;

@FunctionalInterface
public interface Sequence<T> extends Iterable<T> {

    // region Factory Methods

    static <T> Sequence<T> empty() {
        return Collections::emptyIterator;
    }

    @SafeVarargs
    static <T> Sequence<T> sequenceOf(T... values) {
        if (values == null || values.length == 0) {
            return empty();
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

    // region Intermediate Methods

    default Sequence<T> onlyOnce() {
        return new OnlyOnceSequence<>(this);
    }

    // endregion

}
