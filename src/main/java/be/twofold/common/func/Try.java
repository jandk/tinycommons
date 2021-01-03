package be.twofold.common.func;

import java.util.*;

public abstract class Try<T> {

    Try() {
    }

    public static <T> Try<T> success(T result) {
        return new Success<>(result);
    }

    public static <T> Failure<T> failure(Throwable cause) {
        return new Failure<>(cause);
    }

    public boolean isSuccess() {
        return this instanceof Success;
    }

    public boolean isFailure() {
        return this instanceof Failure;
    }

    public abstract T getResult();

    public abstract Throwable getCause();

    static final class Success<T> extends Try<T> {
        private final T result;

        Success(T result) {
            this.result = result;
        }

        @Override
        public T getResult() {
            return result;
        }

        @Override
        public Throwable getCause() {
            throw new NoSuchElementException();
        }

        @Override
        public boolean equals(Object obj) {
            return this == obj || obj instanceof Success
                && Objects.equals(result, ((Success<?>) obj).result);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(result);
        }

        @Override
        public String toString() {
            return "Success(" + result + ")";
        }
    }

    static final class Failure<T> extends Try<T> {
        private final Throwable cause;

        Failure(Throwable cause) {
            this.cause = cause;
        }

        @Override
        public T getResult() {
            throw new NoSuchElementException();
        }

        @Override
        public Throwable getCause() {
            return cause;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Failure)) return false;

            Failure<?> failure = (Failure<?>) o;

            return Objects.equals(cause, failure.cause);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(cause);
        }

        @Override
        public String toString() {
            return "Failure(" + cause + ")";
        }
    }

}
