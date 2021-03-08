package be.twofold.common.func;

import java.util.*;
import java.util.function.*;

/**
 * Replacement for {@link Optional} following monadic rules.
 */
public abstract class Option<T> {

    Option() {
    }

    public static <T> Option<T> some(T value) {
        return new Some<>(value);
    }

    @SuppressWarnings("unchecked")
    public static <T> Option<T> none() {
        return (Option<T>) None.Instance;
    }

    public static <T> Option<T> of(T value) {
        return value != null ? some(value) : none();
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public static <T> Option<T> ofOptional(Optional<? extends T> optional) {
        return Objects.requireNonNull(optional, "optional is null")
            .<Option<T>>map(Option::of)
            .orElseGet(Option::none);
    }

    public abstract T get();

    public final boolean isDefined() {
        return this instanceof Some;
    }

    public final boolean isEmpty() {
        return this instanceof None;
    }

    @SuppressWarnings("unchecked")
    public final <R> Option<R> flatMap(Function<? super T, ? extends Option<? extends R>> mapper) {
        Objects.requireNonNull(mapper, "mapper is null");
        if (isEmpty()) {
            return none();
        }
        Option<R> result = (Option<R>) mapper.apply(get());
        return Objects.requireNonNull(result);
    }

    static final class Some<T> extends Option<T> {
        private final T value;

        Some(T value) {
            this.value = value;
        }

        @Override
        public T get() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            return this == obj || obj instanceof Some
                && Objects.equals(value, ((Some<?>) obj).value);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(value);
        }

        @Override
        public String toString() {
            return "Some(" + value + ")";
        }
    }

    static final class None extends Option<Void> {
        static final None Instance = new None();

        private None() {
        }

        @Override
        public Void get() {
            throw new NoSuchElementException("No value present");
        }

        @Override
        public String toString() {
            return "None";
        }
    }

}
