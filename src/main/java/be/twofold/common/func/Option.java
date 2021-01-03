package be.twofold.common.func;

import java.util.*;

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

    public abstract T get();

    public boolean isPresent() {
        return this instanceof Some;
    }

    public boolean isAbsent() {
        return this instanceof None;
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
            throw new NoSuchElementException();
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof None;
        }

        @Override
        public int hashCode() {
            return 1;
        }

        @Override
        public String toString() {
            return "None";
        }
    }

}
