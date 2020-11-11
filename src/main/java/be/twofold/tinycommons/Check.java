package be.twofold.tinycommons;

import java.util.*;

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

    public static <T> T notNull(T obj, String message, Object... args) {
        return Objects.requireNonNull(obj, () -> String.format(message, args));
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

    public static void argument(boolean expression, String message, Object... args) {
        if (!expression) {
            throw new IllegalArgumentException(String.format(message, args));
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

    public static void state(boolean expression, String message, Object... args) {
        if (!expression) {
            throw new IllegalStateException(String.format(message, args));
        }
    }

    public static int index(int index, int size) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return index;
    }

    public static int position(int index, int size) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        return index;
    }

    public static int positions(int from, int to, int size) {
        if (from < 0 || from > to || to > size) {
            throw new IndexOutOfBoundsException();
        }
        return from;
    }

}
