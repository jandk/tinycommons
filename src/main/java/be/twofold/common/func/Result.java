package be.twofold.common.func;

import be.twofold.common.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

/**
 * Result is a functional alternative to exceptions.
 *
 * @param <T> the type of the result
 */
public abstract class Result<T> {

    Result() {
    }

    /**
     * Creates a new Result from a callable. When the callable throws an exception, the result will be a failure.
     *
     * @param callable The callable
     * @param <T>      The type of the result
     * @return A new Result
     */
    public static <T> Result<T> of(Callable<T> callable) {
        Check.notNull(callable, "callable is null");
        try {
            return success(callable.call());
        } catch (Throwable t) {
            return failure(t);
        }
    }

    /**
     * Creates a new Result from a supplier. When the supplier throws an exception, the result will be a failure.
     *
     * @param supplier The supplier
     * @param <T>      The type of the result
     * @return A new Result
     */
    public static <T> Result<T> ofSupplier(Supplier<T> supplier) {
        Check.notNull(supplier, "supplier is null");
        return of(supplier::get);
    }

    /**
     * Creates a new instance of {@link Result} that represents a successful result.
     *
     * @param value the value of the result
     * @param <T>   the type of the result
     * @return a new instance of {@link Result}
     */
    public static <T> Result<T> success(T value) {
        return new Success<>(value);
    }

    /**
     * Creates a new instance of {@link Result} that represents a failed result.
     *
     * @param cause the cause of the failure
     * @param <T>   the type of the result
     * @return a new instance of {@link Result}
     */
    public static <T> Result<T> failure(Throwable cause) {
        return new Failure(cause).cast();
    }

    /**
     * Returns {@code true} if this result is a success.
     *
     * @return {@code true} if this result is a success
     */
    public boolean isSuccess() {
        return this instanceof Success;
    }

    /**
     * Returns {@code true} if this result is a failure.
     *
     * @return {@code true} if this result is a failure
     */
    public boolean isFailure() {
        return this instanceof Failure;
    }

    /**
     * Returns the value of this result if it is a success. Throws the original exception if it's a failure.
     *
     * @return the value of this result
     */
    public abstract T get();

    /**
     * Returns the cause of this result if it is a failure. Throws an {@link IllegalStateException} if it's a success.
     *
     * @return the cause of this result
     * @throws IllegalStateException if this result is a success
     */
    public abstract Throwable getCause();

    @SuppressWarnings("unchecked")
    <R> Result<R> cast() {
        return (Result<R>) this;
    }


    static final class Success<T> extends Result<T> {
        private final T value;

        Success(T value) {
            this.value = value;
        }

        @Override
        public T get() {
            return value;
        }

        @Override
        public Throwable getCause() {
            throw new NoSuchElementException();
        }

        @Override
        public boolean equals(Object obj) {
            return this == obj || obj instanceof Success
                && Objects.equals(value, ((Success<?>) obj).value);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(value);
        }

        @Override
        public String toString() {
            return "Success(" + value + ")";
        }
    }


    static final class Failure extends Result<Void> {
        private final Throwable cause;

        Failure(Throwable cause) {
            Check.notNull(cause);
            if (isFatal(cause)) {
                sneakyThrow(cause);
            }
            this.cause = cause;
        }

        @Override
        public Void get() {
            return sneakyThrow(cause);
        }

        @Override
        public Throwable getCause() {
            return cause;
        }

        @Override
        public boolean equals(Object obj) {
            return this == obj || obj instanceof Failure
                && Objects.equals(cause, ((Failure) obj).cause);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(cause);
        }

        @Override
        public String toString() {
            return "Failure(" + cause + ")";
        }

        @SuppressWarnings("unchecked")
        private <E extends Throwable> Void sneakyThrow(Throwable cause) throws E {
            throw (E) cause;
        }

        private boolean isFatal(Throwable cause) {
            return cause instanceof InterruptedException
                || cause instanceof LinkageError
                || cause instanceof ThreadDeath
                || cause instanceof VirtualMachineError;
        }
    }

}
