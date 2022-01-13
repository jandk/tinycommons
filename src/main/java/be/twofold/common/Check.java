package be.twofold.common;

import java.util.*;
import java.util.function.*;

/**
 * Misc routines for checking arguments to functions.
 * <p>
 * Even though there's some standard stuff in {@link java.util.Objects}, there's stuff missing.
 * <p>
 * This is just to make it consistent and to add the missing items.
 */
public final class Check {

    private Check() {
        throw new UnsupportedOperationException();
    }

    public static <T> T notNull(T obj) {
        return Objects.requireNonNull(obj);
    }

    public static <T> T notNull(T obj, String message) {
        return Objects.requireNonNull(obj, message);
    }

    public static <T> T notNull(T obj, Supplier<String> messageSupplier) {
        return Objects.requireNonNull(obj, messageSupplier);
    }

    public static void argument(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }

    public static void argument(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void argument(boolean expression, Supplier<String> messageSupplier) {
        if (!expression) {
            throw new IllegalArgumentException(messageSupplier == null ? null : messageSupplier.get());
        }
    }

    public static void state(boolean expression) {
        if (!expression) {
            throw new IllegalStateException();
        }
    }

    public static void state(boolean expression, String message) {
        if (!expression) {
            throw new IllegalStateException(message);
        }
    }

    public static void state(boolean expression, Supplier<String> messageSupplier) {
        if (!expression) {
            throw new IllegalStateException(messageSupplier == null ? null : messageSupplier.get());
        }
    }

    public static int index(int index, int size) {
        return Objects.checkIndex(index, size);
    }

    public static int fromToIndex(int from, int to, int size) {
        return Objects.checkFromToIndex(from, to, size);
    }

}
