package be.twofold.common;

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
        if (obj == null) {
            throw new NullPointerException();
        }
        return obj;
    }

    public static <T> T notNull(T obj, String message) {
        if (obj == null) {
            throw new NullPointerException(message);
        }
        return obj;
    }

    public static <T> T notNull(T obj, Supplier<String> messageSupplier) {
        if (obj == null) {
            throw new NullPointerException(messageSupplier == null ? null : messageSupplier.get());
        }
        return obj;
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

    public static int index(int index, int length) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException(String.format("Index %s out of bounds for length %s", index, length));
        }
        return index;
    }

    public static int fromToIndex(int fromIndex, int toIndex, int length) {
        if (fromIndex < 0 || fromIndex > toIndex || toIndex > length) {
            throw new IndexOutOfBoundsException(String.format("Range [%s, %s) out of bounds for length %s", fromIndex, toIndex, length));
        }
        return fromIndex;
    }

}
