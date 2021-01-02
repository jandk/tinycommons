package be.twofold.common.func;

import be.twofold.common.*;
import be.twofold.common.func.fi.*;

import java.io.*;
import java.util.function.*;

public final class Unchecked {

    private Unchecked() {
        throw new UnsupportedOperationException();
    }

    public static <T> Consumer<T> consumer(CheckedConsumer<T> consumer) {
        Check.notNull(consumer, "consumer");
        return t -> {
            try {
                consumer.accept(t);
            } catch (Throwable throwable) {
                throw toRuntimeException(throwable);
            }
        };
    }

    public static <T, R> Function<T, R> function(CheckedFunction<T, R> function) {
        Check.notNull(function, "function");
        return t -> {
            try {
                return function.apply(t);
            } catch (Throwable throwable) {
                throw toRuntimeException(throwable);
            }
        };
    }

    public static <T> Predicate<T> predicate(CheckedPredicate<T> predicate) {
        Check.notNull(predicate, "predicate");
        return t -> {
            try {
                return predicate.test(t);
            } catch (Throwable throwable) {
                throw toRuntimeException(throwable);
            }
        };
    }

    public static <T> Supplier<T> supplier(CheckedSupplier<T> supplier) {
        Check.notNull(supplier, "supplier");
        return () -> {
            try {
                return supplier.get();
            } catch (Throwable throwable) {
                throw toRuntimeException(throwable);
            }
        };
    }

    private static RuntimeException toRuntimeException(Throwable throwable) {
        if (throwable instanceof Error) {
            throw (Error) throwable;
        }
        if (throwable instanceof RuntimeException) {
            throw (RuntimeException) throwable;
        }
        if (throwable instanceof IOException) {
            throw new UncheckedIOException((IOException) throwable);
        }
        throw new RuntimeException(throwable);
    }

}
