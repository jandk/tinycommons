package be.twofold.common;

import java.lang.reflect.*;
import java.util.*;

/**
 * Operations on arrays, mostly primitive arrays.
 * <p>
 * {@code null} is not allowed as a parameter, and will throw {@link NullPointerException}
 */
public final class ArrayUtils {

    private static final int NOT_FOUND = -1;

    private ArrayUtils() {
        throw new UnsupportedOperationException();
    }

    // region contains

    /**
     * Check if the value is present in the array.
     *
     * @param array An array of values
     * @param value A value to find
     * @return {@code true} if the value is present
     */
    public static boolean contains(byte[] array, byte value) {
        check(array);
        return indexOf(array, value) >= 0;
    }

    /**
     * Check if the value is present in the array.
     *
     * @param array An array of values
     * @param value A value to find
     * @return {@code true} if the value is present
     */
    public static boolean contains(short[] array, short value) {
        check(array);
        return indexOf(array, value) >= 0;
    }

    /**
     * Check if the value is present in the array.
     *
     * @param array An array of values
     * @param value A value to find
     * @return {@code true} if the value is present
     */
    public static boolean contains(int[] array, int value) {
        check(array);
        return indexOf(array, value) >= 0;
    }

    /**
     * Check if the value is present in the array.
     *
     * @param array An array of values
     * @param value A value to find
     * @return {@code true} if the value is present
     */
    public static boolean contains(long[] array, long value) {
        check(array);
        return indexOf(array, value) >= 0;
    }

    /**
     * Check if the value is present in the array.
     * <p>
     * Note: {@code NaN} will work as well.
     *
     * @param array An array of values
     * @param value A value to find
     * @return {@code true} if the value is present
     */
    public static boolean contains(float[] array, float value) {
        check(array);
        return indexOf(array, value) >= 0;
    }

    /**
     * Check if the value is present in the array.
     * <p>
     * Note: {@code NaN} will work as well.
     *
     * @param array An array of values
     * @param value A value to find
     * @return {@code true} if the value is present
     */
    public static boolean contains(double[] array, double value) {
        check(array);
        return indexOf(array, value) >= 0;
    }

    /**
     * Check if the value is present in the array.
     *
     * @param array An array of values
     * @param value A value to find
     * @return {@code true} if the value is present
     */
    public static boolean contains(char[] array, char value) {
        check(array);
        return indexOf(array, value) >= 0;
    }

    /**
     * Check if the value is present in the array.
     *
     * @param array An array of values
     * @param value A value to find
     * @return {@code true} if the value is present
     */
    public static boolean contains(boolean[] array, boolean value) {
        check(array);
        return indexOf(array, value) >= 0;
    }

    // endregion

    // region indexOf

    /**
     * Finds the index of the value in the array.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array The array to search through
     * @param value The value to find
     * @return The index, or {@code -1} if not found
     */
    public static int indexOf(byte[] array, byte value) {
        check(array);
        return indexOf(array, value, 0, array.length);
    }

    /**
     * Finds the index of the value in the array, in the specified range.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array     The array to search through
     * @param value     The value to find
     * @param fromIndex The index to start searching at
     * @param toIndex   The index to end searching at
     * @return The index, or {@code -1} if not found
     */
    public static int indexOf(byte[] array, byte value, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        for (int i = fromIndex; i < toIndex; i++) {
            if (array[i] == value) {
                return i;
            }
        }

        return NOT_FOUND;
    }


    /**
     * Finds the index of the value in the array.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array The array to search through
     * @param value The value to find
     * @return The index, or {@code -1} if not found
     */
    public static int indexOf(short[] array, short value) {
        check(array);
        return indexOf(array, value, 0, array.length);
    }

    /**
     * Finds the index of the value in the array, in the specified range.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array     The array to search through
     * @param value     The value to find
     * @param fromIndex The index to start searching at
     * @param toIndex   The index to end searching at
     * @return The index, or {@code -1} if not found
     */
    public static int indexOf(short[] array, short value, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        for (int i = fromIndex; i < toIndex; i++) {
            if (array[i] == value) {
                return i;
            }
        }

        return NOT_FOUND;
    }


    /**
     * Finds the index of the value in the array.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array The array to search through
     * @param value The value to find
     * @return The index, or {@code -1} if not found
     */
    public static int indexOf(int[] array, int value) {
        check(array);
        return indexOf(array, value, 0, array.length);
    }

    /**
     * Finds the index of the value in the array, in the specified range.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array     The array to search through
     * @param value     The value to find
     * @param fromIndex The index to start searching at
     * @param toIndex   The index to end searching at
     * @return The index, or {@code -1} if not found
     */
    public static int indexOf(int[] array, int value, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        for (int i = fromIndex; i < toIndex; i++) {
            if (array[i] == value) {
                return i;
            }
        }

        return NOT_FOUND;
    }


    /**
     * Finds the index of the value in the array.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array The array to search through
     * @param value The value to find
     * @return The index, or {@code -1} if not found
     */
    public static int indexOf(long[] array, long value) {
        check(array);
        return indexOf(array, value, 0, array.length);
    }

    /**
     * Finds the index of the value in the array, in the specified range.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array     The array to search through
     * @param value     The value to find
     * @param fromIndex The index to start searching at
     * @param toIndex   The index to end searching at
     * @return The index, or {@code -1} if not found
     */
    public static int indexOf(long[] array, long value, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        for (int i = fromIndex; i < toIndex; i++) {
            if (array[i] == value) {
                return i;
            }
        }

        return NOT_FOUND;
    }


    /**
     * Finds the index of the value in the array.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array The array to search through
     * @param value The value to find
     * @return The index, or {@code -1} if not found
     */
    public static int indexOf(float[] array, float value) {
        check(array);
        return indexOf(array, value, 0, array.length);
    }

    /**
     * Finds the index of the value in the array, in the specified range.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array     The array to search through
     * @param value     The value to find
     * @param fromIndex The index to start searching at
     * @param toIndex   The index to end searching at
     * @return The index, or {@code -1} if not found
     */
    public static int indexOf(float[] array, float value, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        for (int i = fromIndex; i < toIndex; i++) {
            if (Float.compare(array[i], value) == 0) {
                return i;
            }
        }

        return NOT_FOUND;
    }


    /**
     * Finds the index of the value in the array.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array The array to search through
     * @param value The value to find
     * @return The index, or {@code -1} if not found
     */
    public static int indexOf(double[] array, double value) {
        check(array);
        return indexOf(array, value, 0, array.length);
    }

    /**
     * Finds the index of the value in the array, in the specified range.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array     The array to search through
     * @param value     The value to find
     * @param fromIndex The index to start searching at
     * @param toIndex   The index to end searching at
     * @return The index, or {@code -1} if not found
     */
    public static int indexOf(double[] array, double value, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        for (int i = fromIndex; i < toIndex; i++) {
            if (Double.compare(array[i], value) == 0) {
                return i;
            }
        }

        return NOT_FOUND;
    }


    /**
     * Finds the index of the value in the array.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array The array to search through
     * @param value The value to find
     * @return The index, or {@code -1} if not found
     */
    public static int indexOf(char[] array, char value) {
        check(array);
        return indexOf(array, value, 0, array.length);
    }

    /**
     * Finds the index of the value in the array, in the specified range.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array     The array to search through
     * @param value     The value to find
     * @param fromIndex The index to start searching at
     * @param toIndex   The index to end searching at
     * @return The index, or {@code -1} if not found
     */
    public static int indexOf(char[] array, char value, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        for (int i = fromIndex; i < toIndex; i++) {
            if (array[i] == value) {
                return i;
            }
        }

        return NOT_FOUND;
    }


    /**
     * Finds the index of the value in the array.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array The array to search through
     * @param value The value to find
     * @return The index, or {@code -1} if not found
     */
    public static int indexOf(boolean[] array, boolean value) {
        check(array);
        return indexOf(array, value, 0, array.length);
    }

    /**
     * Finds the index of the value in the array, in the specified range.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array     The array to search through
     * @param value     The value to find
     * @param fromIndex The index to start searching at
     * @param toIndex   The index to end searching at
     * @return The index, or {@code -1} if not found
     */
    public static int indexOf(boolean[] array, boolean value, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        for (int i = fromIndex; i < toIndex; i++) {
            if (array[i] == value) {
                return i;
            }
        }

        return NOT_FOUND;
    }

    // endregion

    // region lastIndexOf

    /**
     * Finds the last index of the value in the array.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array The array to search through, backwards
     * @param value The value to find
     * @return The index, or {@code -1} if not found
     */
    public static int lastIndexOf(byte[] array, byte value) {
        check(array);
        return lastIndexOf(array, value, 0, array.length);
    }

    /**
     * Finds the last index of the value in the array, in the specified range.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array     The array to search through, backwards
     * @param value     The value to find
     * @param fromIndex The index to start searching at
     * @param toIndex   The index to end searching at
     * @return The index, or {@code -1} if not found
     */
    public static int lastIndexOf(byte[] array, byte value, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        for (int i = toIndex - 1; i >= fromIndex; i--) {
            if (array[i] == value) {
                return i;
            }
        }

        return NOT_FOUND;
    }


    /**
     * Finds the last index of the value in the array.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array The array to search through, backwards
     * @param value The value to find
     * @return The index, or {@code -1} if not found
     */
    public static int lastIndexOf(short[] array, short value) {
        check(array);
        return lastIndexOf(array, value, 0, array.length);
    }

    /**
     * Finds the last index of the value in the array, in the specified range.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array     The array to search through, backwards
     * @param value     The value to find
     * @param fromIndex The index to start searching at
     * @param toIndex   The index to end searching at
     * @return The index, or {@code -1} if not found
     */
    public static int lastIndexOf(short[] array, short value, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        for (int i = toIndex - 1; i >= fromIndex; i--) {
            if (array[i] == value) {
                return i;
            }
        }

        return NOT_FOUND;
    }


    /**
     * Finds the last index of the value in the array.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array The array to search through, backwards
     * @param value The value to find
     * @return The index, or {@code -1} if not found
     */
    public static int lastIndexOf(int[] array, int value) {
        check(array);
        return lastIndexOf(array, value, 0, array.length);
    }

    /**
     * Finds the last index of the value in the array, in the specified range.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array     The array to search through, backwards
     * @param value     The value to find
     * @param fromIndex The index to start searching at
     * @param toIndex   The index to end searching at
     * @return The index, or {@code -1} if not found
     */
    public static int lastIndexOf(int[] array, int value, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        for (int i = toIndex - 1; i >= fromIndex; i--) {
            if (array[i] == value) {
                return i;
            }
        }

        return NOT_FOUND;
    }


    /**
     * Finds the last index of the value in the array.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array The array to search through, backwards
     * @param value The value to find
     * @return The index, or {@code -1} if not found
     */
    public static int lastIndexOf(long[] array, long value) {
        check(array);
        return lastIndexOf(array, value, 0, array.length);
    }

    /**
     * Finds the last index of the value in the array, in the specified range.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array     The array to search through, backwards
     * @param value     The value to find
     * @param fromIndex The index to start searching at
     * @param toIndex   The index to end searching at
     * @return The index, or {@code -1} if not found
     */
    public static int lastIndexOf(long[] array, long value, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        for (int i = toIndex - 1; i >= fromIndex; i--) {
            if (array[i] == value) {
                return i;
            }
        }

        return NOT_FOUND;
    }


    /**
     * Finds the last index of the value in the array.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array The array to search through, backwards
     * @param value The value to find
     * @return The index, or {@code -1} if not found
     */
    public static int lastIndexOf(float[] array, float value) {
        check(array);
        return lastIndexOf(array, value, 0, array.length);
    }

    /**
     * Finds the last index of the value in the array, in the specified range.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array     The array to search through, backwards
     * @param value     The value to find
     * @param fromIndex The index to start searching at
     * @param toIndex   The index to end searching at
     * @return The index, or {@code -1} if not found
     */
    public static int lastIndexOf(float[] array, float value, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        for (int i = toIndex - 1; i >= fromIndex; i--) {
            if (Float.compare(array[i], value) == 0) {
                return i;
            }
        }

        return NOT_FOUND;
    }


    /**
     * Finds the last index of the value in the array.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array The array to search through, backwards
     * @param value The value to find
     * @return The index, or {@code -1} if not found
     */
    public static int lastIndexOf(double[] array, double value) {
        check(array);
        return lastIndexOf(array, value, 0, array.length);
    }

    /**
     * Finds the last index of the value in the array, in the specified range.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array     The array to search through, backwards
     * @param value     The value to find
     * @param fromIndex The index to start searching at
     * @param toIndex   The index to end searching at
     * @return The index, or {@code -1} if not found
     */
    public static int lastIndexOf(double[] array, double value, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        for (int i = toIndex - 1; i >= fromIndex; i--) {
            if (Double.compare(array[i], value) == 0) {
                return i;
            }
        }

        return NOT_FOUND;
    }


    /**
     * Finds the last index of the value in the array.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array The array to search through, backwards
     * @param value The value to find
     * @return The index, or {@code -1} if not found
     */
    public static int lastIndexOf(char[] array, char value) {
        check(array);
        return lastIndexOf(array, value, 0, array.length);
    }

    /**
     * Finds the last index of the value in the array, in the specified range.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array     The array to search through, backwards
     * @param value     The value to find
     * @param fromIndex The index to start searching at
     * @param toIndex   The index to end searching at
     * @return The index, or {@code -1} if not found
     */
    public static int lastIndexOf(char[] array, char value, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        for (int i = toIndex - 1; i >= fromIndex; i--) {
            if (array[i] == value) {
                return i;
            }
        }

        return NOT_FOUND;
    }


    /**
     * Finds the last index of the value in the array.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array The array to search through, backwards
     * @param value The value to find
     * @return The index, or {@code -1} if not found
     */
    public static int lastIndexOf(boolean[] array, boolean value) {
        check(array);
        return lastIndexOf(array, value, 0, array.length);
    }

    /**
     * Finds the last index of the value in the array, in the specified range.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array     The array to search through, backwards
     * @param value     The value to find
     * @param fromIndex The index to start searching at
     * @param toIndex   The index to end searching at
     * @return The index, or {@code -1} if not found
     */
    public static int lastIndexOf(boolean[] array, boolean value, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        for (int i = toIndex - 1; i >= fromIndex; i--) {
            if (array[i] == value) {
                return i;
            }
        }

        return NOT_FOUND;
    }

    // endregion

    // region reverse

    public static void reverse(byte[] array) {
        check(array);
        reverse(array, 0, array.length);
    }

    public static void reverse(byte[] array, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        for (int i = fromIndex, j = toIndex - 1; i < j; i++, j--) {
            swap(array, i, j);
        }
    }


    public static void reverse(short[] array) {
        check(array);
        reverse(array, 0, array.length);
    }

    public static void reverse(short[] array, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        for (int i = fromIndex, j = toIndex - 1; i < j; i++, j--) {
            swap(array, i, j);
        }
    }


    public static void reverse(int[] array) {
        check(array);
        reverse(array, 0, array.length);
    }

    public static void reverse(int[] array, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        for (int i = fromIndex, j = toIndex - 1; i < j; i++, j--) {
            swap(array, i, j);
        }
    }


    public static void reverse(long[] array) {
        check(array);
        reverse(array, 0, array.length);
    }

    public static void reverse(long[] array, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        for (int i = fromIndex, j = toIndex - 1; i < j; i++, j--) {
            swap(array, i, j);
        }
    }


    public static void reverse(float[] array) {
        check(array);
        reverse(array, 0, array.length);
    }

    public static void reverse(float[] array, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        for (int i = fromIndex, j = toIndex - 1; i < j; i++, j--) {
            swap(array, i, j);
        }
    }


    public static void reverse(double[] array) {
        check(array);
        reverse(array, 0, array.length);
    }

    public static void reverse(double[] array, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        for (int i = fromIndex, j = toIndex - 1; i < j; i++, j--) {
            swap(array, i, j);
        }
    }


    public static void reverse(char[] array) {
        check(array);
        reverse(array, 0, array.length);
    }

    public static void reverse(char[] array, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        for (int i = fromIndex, j = toIndex - 1; i < j; i++, j--) {
            swap(array, i, j);
        }
    }


    public static void reverse(boolean[] array) {
        check(array);
        reverse(array, 0, array.length);
    }

    public static void reverse(boolean[] array, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        for (int i = fromIndex, j = toIndex - 1; i < j; i++, j--) {
            swap(array, i, j);
        }
    }


    public static void reverse(Object[] array) {
        check(array);
        reverse(array, 0, array.length);
    }

    public static void reverse(Object[] array, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        for (int i = fromIndex, j = toIndex - 1; i < j; i++, j--) {
            swap(array, i, j);
        }
    }

    // endregion

    // region sortDescending

    public static void sortDescending(byte[] array) {
        check(array);
        sortDescending(array, 0, array.length);
    }

    public static void sortDescending(byte[] array, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);
        Arrays.sort(array, fromIndex, toIndex);
        reverse(array, fromIndex, toIndex);
    }


    public static void sortDescending(short[] array) {
        check(array);
        sortDescending(array, 0, array.length);
    }

    public static void sortDescending(short[] array, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);
        Arrays.sort(array, fromIndex, toIndex);
        reverse(array, fromIndex, toIndex);
    }


    public static void sortDescending(int[] array) {
        check(array);
        sortDescending(array, 0, array.length);
    }

    public static void sortDescending(int[] array, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);
        Arrays.sort(array, fromIndex, toIndex);
        reverse(array, fromIndex, toIndex);
    }


    public static void sortDescending(long[] array) {
        check(array);
        sortDescending(array, 0, array.length);
    }

    public static void sortDescending(long[] array, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);
        Arrays.sort(array, fromIndex, toIndex);
        reverse(array, fromIndex, toIndex);
    }


    public static void sortDescending(float[] array) {
        check(array);
        sortDescending(array, 0, array.length);
    }

    public static void sortDescending(float[] array, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);
        Arrays.sort(array, fromIndex, toIndex);
        reverse(array, fromIndex, toIndex);
    }


    public static void sortDescending(double[] array) {
        check(array);
        sortDescending(array, 0, array.length);
    }

    public static void sortDescending(double[] array, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);
        Arrays.sort(array, fromIndex, toIndex);
        reverse(array, fromIndex, toIndex);
    }


    public static void sortDescending(char[] array) {
        check(array);
        sortDescending(array, 0, array.length);
    }

    public static void sortDescending(char[] array, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);
        Arrays.sort(array, fromIndex, toIndex);
        reverse(array, fromIndex, toIndex);
    }


    public static <T> void sortDescending(T[] array, Comparator<? super T> comparator) {
        check(array);
        Check.notNull(comparator, "comparator");
        sortDescending(array, 0, array.length, comparator);
    }

    public static <T> void sortDescending(T[] array, int fromIndex, int toIndex, Comparator<? super T> comparator) {
        check(array, fromIndex, toIndex);
        Check.notNull(comparator, "comparator");
        Arrays.sort(array, fromIndex, toIndex, comparator);
        reverse(array, fromIndex, toIndex);
    }

    // endregion

    // region swap

    private static void swap(byte[] array, int i, int j) {
        byte tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private static void swap(short[] array, int i, int j) {
        short tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private static void swap(long[] array, int i, int j) {
        long tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private static void swap(float[] array, int i, int j) {
        float tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private static void swap(double[] array, int i, int j) {
        double tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private static void swap(char[] array, int i, int j) {
        char tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private static void swap(boolean[] array, int i, int j) {
        boolean tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private static void swap(Object[] array, int i, int j) {
        Object tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    // endregion

    // region hashCode

    public static int hashCode(byte[] array) {
        if (array == null) {
            return 0;
        }
        return hashCode(array, 0, array.length);
    }

    public static int hashCode(byte[] array, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        int result = 1;
        for (int i = fromIndex; i < toIndex; i++) {
            result = 31 * result + Byte.hashCode(array[i]);
        }
        return result;
    }


    public static int hashCode(short[] array) {
        if (array == null) {
            return 0;
        }
        return hashCode(array, 0, array.length);
    }

    public static int hashCode(short[] array, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        int result = 1;
        for (int i = fromIndex; i < toIndex; i++) {
            result = 31 * result + Short.hashCode(array[i]);
        }
        return result;
    }


    public static int hashCode(int[] array) {
        if (array == null) {
            return 0;
        }
        return hashCode(array, 0, array.length);
    }

    public static int hashCode(int[] array, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        int result = 1;
        for (int i = fromIndex; i < toIndex; i++) {
            result = 31 * result + Integer.hashCode(array[i]);
        }
        return result;
    }


    public static int hashCode(long[] array) {
        if (array == null) {
            return 0;
        }
        return hashCode(array, 0, array.length);
    }

    public static int hashCode(long[] array, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        int result = 1;
        for (int i = fromIndex; i < toIndex; i++) {
            result = 31 * result + Long.hashCode(array[i]);
        }
        return result;
    }


    public static int hashCode(float[] array) {
        if (array == null) {
            return 0;
        }
        return hashCode(array, 0, array.length);
    }

    public static int hashCode(float[] array, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        int result = 1;
        for (int i = fromIndex; i < toIndex; i++) {
            result = 31 * result + Float.hashCode(array[i]);
        }
        return result;
    }


    public static int hashCode(double[] array) {
        if (array == null) {
            return 0;
        }
        return hashCode(array, 0, array.length);
    }

    public static int hashCode(double[] array, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        int result = 1;
        for (int i = fromIndex; i < toIndex; i++) {
            result = 31 * result + Double.hashCode(array[i]);
        }
        return result;
    }


    public static int hashCode(char[] array) {
        if (array == null) {
            return 0;
        }
        return hashCode(array, 0, array.length);
    }

    public static int hashCode(char[] array, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        int result = 1;
        for (int i = fromIndex; i < toIndex; i++) {
            result = 31 * result + Character.hashCode(array[i]);
        }
        return result;
    }


    public static int hashCode(boolean[] array) {
        if (array == null) {
            return 0;
        }
        return hashCode(array, 0, array.length);
    }

    public static int hashCode(boolean[] array, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        int result = 1;
        for (int i = fromIndex; i < toIndex; i++) {
            result = 31 * result + Boolean.hashCode(array[i]);
        }
        return result;
    }


    public static int hashCode(Object[] array) {
        if (array == null) {
            return 0;
        }
        return hashCode(array, 0, array.length);
    }

    public static int hashCode(Object[] array, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        int result = 1;
        for (int i = fromIndex; i < toIndex; i++) {
            result = 31 * result + Objects.hashCode(array[i]);
        }
        return result;
    }

    // endregion

    // region toString

    public static String toString(byte[] array) {
        if (array == null) {
            return "null";
        }
        return toString(array, 0, array.length);
    }

    public static String toString(byte[] array, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        if (fromIndex == toIndex) {
            return "[]";
        }

        StringBuilder builder = new StringBuilder().append('[').append(array[fromIndex]);
        for (int i = fromIndex + 1; i < toIndex; i++) {
            builder.append(", ").append(array[i]);
        }
        return builder.append(']').toString();
    }


    public static String toString(short[] array) {
        if (array == null) {
            return "null";
        }
        return toString(array, 0, array.length);
    }

    public static String toString(short[] array, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        if (fromIndex == toIndex) {
            return "[]";
        }

        StringBuilder builder = new StringBuilder().append('[').append(array[fromIndex]);
        for (int i = fromIndex + 1; i < toIndex; i++) {
            builder.append(", ").append(array[i]);
        }
        return builder.append(']').toString();
    }


    public static String toString(int[] array) {
        if (array == null) {
            return "null";
        }
        return toString(array, 0, array.length);
    }

    public static String toString(int[] array, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        if (fromIndex == toIndex) {
            return "[]";
        }

        StringBuilder builder = new StringBuilder().append('[').append(array[fromIndex]);
        for (int i = fromIndex + 1; i < toIndex; i++) {
            builder.append(", ").append(array[i]);
        }
        return builder.append(']').toString();
    }


    public static String toString(long[] array) {
        if (array == null) {
            return "null";
        }
        return toString(array, 0, array.length);
    }

    public static String toString(long[] array, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        if (fromIndex == toIndex) {
            return "[]";
        }

        StringBuilder builder = new StringBuilder().append('[').append(array[fromIndex]);
        for (int i = fromIndex + 1; i < toIndex; i++) {
            builder.append(", ").append(array[i]);
        }
        return builder.append(']').toString();
    }


    public static String toString(float[] array) {
        if (array == null) {
            return "null";
        }
        return toString(array, 0, array.length);
    }

    public static String toString(float[] array, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        if (fromIndex == toIndex) {
            return "[]";
        }

        StringBuilder builder = new StringBuilder().append('[').append(array[fromIndex]);
        for (int i = fromIndex + 1; i < toIndex; i++) {
            builder.append(", ").append(array[i]);
        }
        return builder.append(']').toString();
    }


    public static String toString(double[] array) {
        if (array == null) {
            return "null";
        }
        return toString(array, 0, array.length);
    }

    public static String toString(double[] array, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        if (fromIndex == toIndex) {
            return "[]";
        }

        StringBuilder builder = new StringBuilder().append('[').append(array[fromIndex]);
        for (int i = fromIndex + 1; i < toIndex; i++) {
            builder.append(", ").append(array[i]);
        }
        return builder.append(']').toString();
    }


    public static String toString(char[] array) {
        if (array == null) {
            return "null";
        }
        return toString(array, 0, array.length);
    }

    public static String toString(char[] array, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        if (fromIndex == toIndex) {
            return "[]";
        }

        StringBuilder builder = new StringBuilder().append('[').append(array[fromIndex]);
        for (int i = fromIndex + 1; i < toIndex; i++) {
            builder.append(", ").append(array[i]);
        }
        return builder.append(']').toString();
    }


    public static String toString(boolean[] array) {
        if (array == null) {
            return "null";
        }
        return toString(array, 0, array.length);
    }

    public static String toString(boolean[] array, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        if (fromIndex == toIndex) {
            return "[]";
        }

        StringBuilder builder = new StringBuilder().append('[').append(array[fromIndex]);
        for (int i = fromIndex + 1; i < toIndex; i++) {
            builder.append(", ").append(array[i]);
        }
        return builder.append(']').toString();
    }


    public static String toString(Object[] array) {
        if (array == null) {
            return "null";
        }
        return toString(array, 0, array.length);
    }

    public static String toString(Object[] array, int fromIndex, int toIndex) {
        check(array, fromIndex, toIndex);

        if (fromIndex == toIndex) {
            return "[]";
        }

        StringBuilder builder = new StringBuilder().append('[').append(array[fromIndex]);
        for (int i = fromIndex + 1; i < toIndex; i++) {
            builder.append(", ").append(array[i]);
        }
        return builder.append(']').toString();
    }

    // endregion


    private static void check(Object array) {
        Check.notNull(array, "array");
    }

    private static void check(Object array, int fromIndex, int toIndex) {
        check(array);
        Check.fromToIndex(fromIndex, toIndex, Array.getLength(array));
    }

}
