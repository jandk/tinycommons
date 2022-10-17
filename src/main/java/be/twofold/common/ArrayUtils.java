package be.twofold.common;

import java.util.*;

/**
 * Operations on arrays, mostly primitive arrays.
 * <p>
 * {@code null} is not allowed as a parameter, and will throw {@link NullPointerException}
 */
public final class ArrayUtils {

    private ArrayUtils() {
        throw new UnsupportedOperationException();
    }


    /**
     * Check if the value is present in the array.
     *
     * @param array An array of values
     * @param value A value to find
     * @return {@code true} if the value is present
     */
    public static boolean contains(byte[] array, byte value) {
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
        return indexOf(array, value) >= 0;
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
    public static int indexOf(byte[] array, byte value) {
        return indexOf(array, 0, array.length, value);
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
        return indexOf(array, 0, array.length, value);
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
        return indexOf(array, 0, array.length, value);
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
        return indexOf(array, 0, array.length, value);
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
        return indexOf(array, 0, array.length, value);
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
        return indexOf(array, 0, array.length, value);
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
        return indexOf(array, 0, array.length, value);
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
        return indexOf(array, 0, array.length, value);
    }


    /**
     * Finds the index of the value in the array, in the specified range.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array     The array to search through
     * @param fromIndex The index to start searching at
     * @param toIndex   The index to end searching at
     * @param value     The value to find
     * @return The index, or {@code -1} if not found
     */
    public static int indexOf(byte[] array, int fromIndex, int toIndex, byte value) {
        Check.fromToIndex(fromIndex, toIndex, array.length);

        for (int i = fromIndex; i < toIndex; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the index of the value in the array, in the specified range.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array     The array to search through
     * @param fromIndex The index to start searching at
     * @param toIndex   The index to end searching at
     * @param value     The value to find
     * @return The index, or {@code -1} if not found
     */
    public static int indexOf(short[] array, int fromIndex, int toIndex, short value) {
        Check.fromToIndex(fromIndex, toIndex, array.length);

        for (int i = fromIndex; i < toIndex; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the index of the value in the array, in the specified range.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array     The array to search through
     * @param fromIndex The index to start searching at
     * @param toIndex   The index to end searching at
     * @param value     The value to find
     * @return The index, or {@code -1} if not found
     */
    public static int indexOf(int[] array, int fromIndex, int toIndex, int value) {
        Check.fromToIndex(fromIndex, toIndex, array.length);

        for (int i = fromIndex; i < toIndex; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the index of the value in the array, in the specified range.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array     The array to search through
     * @param fromIndex The index to start searching at
     * @param toIndex   The index to end searching at
     * @param value     The value to find
     * @return The index, or {@code -1} if not found
     */
    public static int indexOf(long[] array, int fromIndex, int toIndex, long value) {
        Check.fromToIndex(fromIndex, toIndex, array.length);

        for (int i = fromIndex; i < toIndex; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the index of the value in the array, in the specified range.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array     The array to search through
     * @param fromIndex The index to start searching at
     * @param toIndex   The index to end searching at
     * @param value     The value to find
     * @return The index, or {@code -1} if not found
     */
    public static int indexOf(float[] array, int fromIndex, int toIndex, float value) {
        Check.fromToIndex(fromIndex, toIndex, array.length);

        for (int i = fromIndex; i < toIndex; i++) {
            if (Float.floatToIntBits(array[i]) == Float.floatToIntBits(value)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the index of the value in the array, in the specified range.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array     The array to search through
     * @param fromIndex The index to start searching at
     * @param toIndex   The index to end searching at
     * @param value     The value to find
     * @return The index, or {@code -1} if not found
     */
    public static int indexOf(double[] array, int fromIndex, int toIndex, double value) {
        Check.fromToIndex(fromIndex, toIndex, array.length);

        for (int i = fromIndex; i < toIndex; i++) {
            if (Double.doubleToLongBits(array[i]) == Double.doubleToLongBits(value)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the index of the value in the array, in the specified range.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array     The array to search through
     * @param fromIndex The index to start searching at
     * @param toIndex   The index to end searching at
     * @param value     The value to find
     * @return The index, or {@code -1} if not found
     */
    public static int indexOf(char[] array, int fromIndex, int toIndex, char value) {
        Check.fromToIndex(fromIndex, toIndex, array.length);

        for (int i = fromIndex; i < toIndex; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the index of the value in the array, in the specified range.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array     The array to search through
     * @param fromIndex The index to start searching at
     * @param toIndex   The index to end searching at
     * @param value     The value to find
     * @return The index, or {@code -1} if not found
     */
    public static int indexOf(boolean[] array, int fromIndex, int toIndex, boolean value) {
        Check.fromToIndex(fromIndex, toIndex, array.length);

        for (int i = fromIndex; i < toIndex; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
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
    public static int lastIndexOf(byte[] array, byte value) {
        return lastIndexOf(array, 0, array.length, value);
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
        return lastIndexOf(array, 0, array.length, value);
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
        return lastIndexOf(array, 0, array.length, value);
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
        return lastIndexOf(array, 0, array.length, value);
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
        return lastIndexOf(array, 0, array.length, value);
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
        return lastIndexOf(array, 0, array.length, value);
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
        return lastIndexOf(array, 0, array.length, value);
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
        return lastIndexOf(array, 0, array.length, value);
    }


    /**
     * Finds the last index of the value in the array, in the specified range.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array     The array to search through, backwards
     * @param fromIndex The index to start searching at
     * @param toIndex   The index to end searching at
     * @param value     The value to find
     * @return The index, or {@code -1} if not found
     */
    public static int lastIndexOf(byte[] array, int fromIndex, int toIndex, byte value) {
        Check.fromToIndex(fromIndex, toIndex, array.length);

        for (int i = toIndex - 1; i >= fromIndex; i--) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the last index of the value in the array, in the specified range.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array     The array to search through, backwards
     * @param fromIndex The index to start searching at
     * @param toIndex   The index to end searching at
     * @param value     The value to find
     * @return The index, or {@code -1} if not found
     */
    public static int lastIndexOf(short[] array, int fromIndex, int toIndex, short value) {
        Check.fromToIndex(fromIndex, toIndex, array.length);

        for (int i = toIndex - 1; i >= fromIndex; i--) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the last index of the value in the array, in the specified range.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array     The array to search through, backwards
     * @param fromIndex The index to start searching at
     * @param toIndex   The index to end searching at
     * @param value     The value to find
     * @return The index, or {@code -1} if not found
     */
    public static int lastIndexOf(int[] array, int fromIndex, int toIndex, int value) {
        Check.fromToIndex(fromIndex, toIndex, array.length);

        for (int i = toIndex - 1; i >= fromIndex; i--) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the last index of the value in the array, in the specified range.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array     The array to search through, backwards
     * @param fromIndex The index to start searching at
     * @param toIndex   The index to end searching at
     * @param value     The value to find
     * @return The index, or {@code -1} if not found
     */
    public static int lastIndexOf(long[] array, int fromIndex, int toIndex, long value) {
        Check.fromToIndex(fromIndex, toIndex, array.length);

        for (int i = toIndex - 1; i >= fromIndex; i--) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the last index of the value in the array, in the specified range.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array     The array to search through, backwards
     * @param fromIndex The index to start searching at
     * @param toIndex   The index to end searching at
     * @param value     The value to find
     * @return The index, or {@code -1} if not found
     */
    public static int lastIndexOf(float[] array, int fromIndex, int toIndex, float value) {
        Check.fromToIndex(fromIndex, toIndex, array.length);

        for (int i = toIndex - 1; i >= fromIndex; i--) {
            if (Float.floatToIntBits(array[i]) == Float.floatToIntBits(value)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the last index of the value in the array, in the specified range.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array     The array to search through, backwards
     * @param fromIndex The index to start searching at
     * @param toIndex   The index to end searching at
     * @param value     The value to find
     * @return The index, or {@code -1} if not found
     */
    public static int lastIndexOf(double[] array, int fromIndex, int toIndex, double value) {
        Check.fromToIndex(fromIndex, toIndex, array.length);

        for (int i = toIndex - 1; i >= fromIndex; i--) {
            if (Double.doubleToLongBits(array[i]) == Double.doubleToLongBits(value)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the last index of the value in the array, in the specified range.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array     The array to search through, backwards
     * @param fromIndex The index to start searching at
     * @param toIndex   The index to end searching at
     * @param value     The value to find
     * @return The index, or {@code -1} if not found
     */
    public static int lastIndexOf(char[] array, int fromIndex, int toIndex, char value) {
        Check.fromToIndex(fromIndex, toIndex, array.length);

        for (int i = toIndex - 1; i >= fromIndex; i--) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the last index of the value in the array, in the specified range.
     * <p>
     * If the value does not appear, {@code -1} is returned.
     *
     * @param array     The array to search through, backwards
     * @param fromIndex The index to start searching at
     * @param toIndex   The index to end searching at
     * @param value     The value to find
     * @return The index, or {@code -1} if not found
     */
    public static int lastIndexOf(boolean[] array, int fromIndex, int toIndex, boolean value) {
        Check.fromToIndex(fromIndex, toIndex, array.length);

        for (int i = toIndex - 1; i >= fromIndex; i--) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }


    public static void reverse(byte[] array) {
        reverse(array, 0, array.length);
    }

    public static void reverse(short[] array) {
        reverse(array, 0, array.length);
    }

    public static void reverse(int[] array) {
        reverse(array, 0, array.length);
    }

    public static void reverse(long[] array) {
        reverse(array, 0, array.length);
    }

    public static void reverse(float[] array) {
        reverse(array, 0, array.length);
    }

    public static void reverse(double[] array) {
        reverse(array, 0, array.length);
    }

    public static void reverse(char[] array) {
        reverse(array, 0, array.length);
    }

    public static void reverse(boolean[] array) {
        reverse(array, 0, array.length);
    }

    public static void reverse(Object[] array) {
        reverse(array, 0, array.length);
    }


    public static void reverse(byte[] array, int fromIndex, int toIndex) {
        Check.fromToIndex(fromIndex, toIndex, array.length);

        for (int i = fromIndex, j = toIndex - 1; i < j; i++, j--) {
            byte temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public static void reverse(short[] array, int fromIndex, int toIndex) {
        Check.fromToIndex(fromIndex, toIndex, array.length);

        for (int i = fromIndex, j = toIndex - 1; i < j; i++, j--) {
            short temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public static void reverse(int[] array, int fromIndex, int toIndex) {
        Check.fromToIndex(fromIndex, toIndex, array.length);

        for (int i = fromIndex, j = toIndex - 1; i < j; i++, j--) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public static void reverse(long[] array, int fromIndex, int toIndex) {
        Check.fromToIndex(fromIndex, toIndex, array.length);

        for (int i = fromIndex, j = toIndex - 1; i < j; i++, j--) {
            long temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public static void reverse(float[] array, int fromIndex, int toIndex) {
        Check.fromToIndex(fromIndex, toIndex, array.length);

        for (int i = fromIndex, j = toIndex - 1; i < j; i++, j--) {
            float temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public static void reverse(double[] array, int fromIndex, int toIndex) {
        Check.fromToIndex(fromIndex, toIndex, array.length);

        for (int i = fromIndex, j = toIndex - 1; i < j; i++, j--) {
            double temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public static void reverse(char[] array, int fromIndex, int toIndex) {
        Check.fromToIndex(fromIndex, toIndex, array.length);

        for (int i = fromIndex, j = toIndex - 1; i < j; i++, j--) {
            char temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public static void reverse(boolean[] array, int fromIndex, int toIndex) {
        Check.fromToIndex(fromIndex, toIndex, array.length);

        for (int i = fromIndex, j = toIndex - 1; i < j; i++, j--) {
            boolean temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public static void reverse(Object[] array, int fromIndex, int toIndex) {
        Check.fromToIndex(fromIndex, toIndex, array.length);

        for (int i = fromIndex, j = toIndex - 1; i < j; i++, j--) {
            Object temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }


    public static void sortDescending(byte[] array) {
        sortDescending(array, 0, array.length);
    }

    public static void sortDescending(short[] array) {
        sortDescending(array, 0, array.length);
    }

    public static void sortDescending(int[] array) {
        sortDescending(array, 0, array.length);
    }

    public static void sortDescending(long[] array) {
        sortDescending(array, 0, array.length);
    }

    public static void sortDescending(float[] array) {
        sortDescending(array, 0, array.length);
    }

    public static void sortDescending(double[] array) {
        sortDescending(array, 0, array.length);
    }

    public static void sortDescending(char[] array) {
        sortDescending(array, 0, array.length);
    }

    public static <T> void sortDescending(T[] array, Comparator<? super T> comparator) {
        Check.notNull(comparator, "comparator");
        sortDescending(array, 0, array.length, comparator);
    }


    public static void sortDescending(byte[] array, int fromIndex, int toIndex) {
        Check.fromToIndex(fromIndex, toIndex, array.length);
        Arrays.sort(array, fromIndex, toIndex);
        reverse(array, fromIndex, toIndex);
    }

    public static void sortDescending(short[] array, int fromIndex, int toIndex) {
        Check.fromToIndex(fromIndex, toIndex, array.length);
        Arrays.sort(array, fromIndex, toIndex);
        reverse(array, fromIndex, toIndex);
    }

    public static void sortDescending(int[] array, int fromIndex, int toIndex) {
        Check.fromToIndex(fromIndex, toIndex, array.length);
        Arrays.sort(array, fromIndex, toIndex);
        reverse(array, fromIndex, toIndex);
    }

    public static void sortDescending(long[] array, int fromIndex, int toIndex) {
        Check.fromToIndex(fromIndex, toIndex, array.length);
        Arrays.sort(array, fromIndex, toIndex);
        reverse(array, fromIndex, toIndex);
    }

    public static void sortDescending(float[] array, int fromIndex, int toIndex) {
        Check.fromToIndex(fromIndex, toIndex, array.length);
        Arrays.sort(array, fromIndex, toIndex);
        reverse(array, fromIndex, toIndex);
    }

    public static void sortDescending(double[] array, int fromIndex, int toIndex) {
        Check.fromToIndex(fromIndex, toIndex, array.length);
        Arrays.sort(array, fromIndex, toIndex);
        reverse(array, fromIndex, toIndex);
    }

    public static void sortDescending(char[] array, int fromIndex, int toIndex) {
        Check.fromToIndex(fromIndex, toIndex, array.length);
        Arrays.sort(array, fromIndex, toIndex);
        reverse(array, fromIndex, toIndex);
    }

    public static <T> void sortDescending(T[] array, int fromIndex, int toIndex, Comparator<? super T> comparator) {
        Check.fromToIndex(fromIndex, toIndex, array.length);
        Check.notNull(comparator, "comparator");
        Arrays.sort(array, fromIndex, toIndex, comparator);
        reverse(array, fromIndex, toIndex);
    }


    public static int compare(byte[] a, byte[] b) {
        return compare(a, 0, a.length, b, 0, b.length);
    }

    public static int compare(short[] a, short[] b) {
        return compare(a, 0, a.length, b, 0, b.length);
    }

    public static int compare(int[] a, int[] b) {
        return compare(a, 0, a.length, b, 0, b.length);
    }

    public static int compare(long[] a, long[] b) {
        return compare(a, 0, a.length, b, 0, b.length);
    }

    public static int compare(float[] a, float[] b) {
        return compare(a, 0, a.length, b, 0, b.length);
    }

    public static int compare(double[] a, double[] b) {
        return compare(a, 0, a.length, b, 0, b.length);
    }

    public static int compare(char[] a, char[] b) {
        return compare(a, 0, a.length, b, 0, b.length);
    }

    public static int compare(boolean[] a, boolean[] b) {
        return compare(a, 0, a.length, b, 0, b.length);
    }


    public static int compare(byte[] a, int aFromIndex, int aToIndex, byte[] b, int bFromIndex, int bToIndex) {
        Check.fromToIndex(aFromIndex, aToIndex, a.length);
        Check.fromToIndex(bFromIndex, bToIndex, b.length);

        int aLength = aToIndex - aFromIndex;
        int bLength = bToIndex - bFromIndex;
        int limit = Math.min(aLength, bLength);

        for (int i = 0; i < limit; i++) {
            int compare = Byte.compare(a[aFromIndex + i], b[bFromIndex + i]);
            if (compare != 0) {
                return compare;
            }
        }
        return aLength - bLength;
    }

    public static int compare(short[] a, int aFromIndex, int aToIndex, short[] b, int bFromIndex, int bToIndex) {
        Check.fromToIndex(aFromIndex, aToIndex, a.length);
        Check.fromToIndex(bFromIndex, bToIndex, b.length);

        int aLength = aToIndex - aFromIndex;
        int bLength = bToIndex - bFromIndex;
        int limit = Math.min(aLength, bLength);

        for (int i = 0; i < limit; i++) {
            int compare = Short.compare(a[aFromIndex + i], b[bFromIndex + i]);
            if (compare != 0) {
                return compare;
            }
        }
        return aLength - bLength;
    }

    public static int compare(int[] a, int aFromIndex, int aToIndex, int[] b, int bFromIndex, int bToIndex) {
        Check.fromToIndex(aFromIndex, aToIndex, a.length);
        Check.fromToIndex(bFromIndex, bToIndex, b.length);

        int aLength = aToIndex - aFromIndex;
        int bLength = bToIndex - bFromIndex;
        int limit = Math.min(aLength, bLength);

        for (int i = 0; i < limit; i++) {
            int compare = Integer.compare(a[aFromIndex + i], b[bFromIndex + i]);
            if (compare != 0) {
                return compare;
            }
        }
        return aLength - bLength;
    }

    public static int compare(long[] a, int aFromIndex, int aToIndex, long[] b, int bFromIndex, int bToIndex) {
        Check.fromToIndex(aFromIndex, aToIndex, a.length);
        Check.fromToIndex(bFromIndex, bToIndex, b.length);

        int aLength = aToIndex - aFromIndex;
        int bLength = bToIndex - bFromIndex;
        int limit = Math.min(aLength, bLength);

        for (int i = 0; i < limit; i++) {
            int compare = Long.compare(a[aFromIndex + i], b[bFromIndex + i]);
            if (compare != 0) {
                return compare;
            }
        }
        return aLength - bLength;
    }

    public static int compare(float[] a, int aFromIndex, int aToIndex, float[] b, int bFromIndex, int bToIndex) {
        Check.fromToIndex(aFromIndex, aToIndex, a.length);
        Check.fromToIndex(bFromIndex, bToIndex, b.length);

        int aLength = aToIndex - aFromIndex;
        int bLength = bToIndex - bFromIndex;
        int limit = Math.min(aLength, bLength);

        for (int i = 0; i < limit; i++) {
            int compare = Float.compare(a[aFromIndex + i], b[bFromIndex + i]);
            if (compare != 0) {
                return compare;
            }
        }
        return aLength - bLength;
    }

    public static int compare(double[] a, int aFromIndex, int aToIndex, double[] b, int bFromIndex, int bToIndex) {
        Check.fromToIndex(aFromIndex, aToIndex, a.length);
        Check.fromToIndex(bFromIndex, bToIndex, b.length);

        int aLength = aToIndex - aFromIndex;
        int bLength = bToIndex - bFromIndex;
        int limit = Math.min(aLength, bLength);

        for (int i = 0; i < limit; i++) {
            int compare = Double.compare(a[aFromIndex + i], b[bFromIndex + i]);
            if (compare != 0) {
                return compare;
            }
        }
        return aLength - bLength;
    }

    public static int compare(char[] a, int aFromIndex, int aToIndex, char[] b, int bFromIndex, int bToIndex) {
        Check.fromToIndex(aFromIndex, aToIndex, a.length);
        Check.fromToIndex(bFromIndex, bToIndex, b.length);

        int aLength = aToIndex - aFromIndex;
        int bLength = bToIndex - bFromIndex;
        int limit = Math.min(aLength, bLength);

        for (int i = 0; i < limit; i++) {
            int compare = Character.compare(a[aFromIndex + i], b[bFromIndex + i]);
            if (compare != 0) {
                return compare;
            }
        }
        return aLength - bLength;
    }

    public static int compare(boolean[] a, int aFromIndex, int aToIndex, boolean[] b, int bFromIndex, int bToIndex) {
        Check.fromToIndex(aFromIndex, aToIndex, a.length);
        Check.fromToIndex(bFromIndex, bToIndex, b.length);

        int aLength = aToIndex - aFromIndex;
        int bLength = bToIndex - bFromIndex;
        int limit = Math.min(aLength, bLength);

        for (int i = 0; i < limit; i++) {
            int compare = Boolean.compare(a[aFromIndex + i], b[bFromIndex + i]);
            if (compare != 0) {
                return compare;
            }
        }
        return aLength - bLength;
    }


    public static boolean equals(byte[] a, byte[] b) {
        return equals(a, 0, a.length, b, 0, b.length);
    }

    public static boolean equals(short[] a, short[] b) {
        return equals(a, 0, a.length, b, 0, b.length);
    }

    public static boolean equals(int[] a, int[] b) {
        return equals(a, 0, a.length, b, 0, b.length);
    }

    public static boolean equals(long[] a, long[] b) {
        return equals(a, 0, a.length, b, 0, b.length);
    }

    public static boolean equals(float[] a, float[] b) {
        return equals(a, 0, a.length, b, 0, b.length);
    }

    public static boolean equals(double[] a, double[] b) {
        return equals(a, 0, a.length, b, 0, b.length);
    }

    public static boolean equals(char[] a, char[] b) {
        return equals(a, 0, a.length, b, 0, b.length);
    }

    public static boolean equals(boolean[] a, boolean[] b) {
        return equals(a, 0, a.length, b, 0, b.length);
    }

    public static boolean equals(Object[] a, Object[] b) {
        return equals(a, 0, a.length, b, 0, b.length);
    }


    public static boolean equals(byte[] a, int aFromIndex, int aToIndex, byte[] b, int bFromIndex, int bToIndex) {
        Check.fromToIndex(aFromIndex, aToIndex, a.length);
        Check.fromToIndex(bFromIndex, bToIndex, b.length);

        int aLength = aToIndex - aFromIndex;
        int bLength = bToIndex - bFromIndex;
        if (aLength != bLength) {
            return false;
        }

        for (int i = 0; i < aLength; i++) {
            if (!(a[aFromIndex + i] == b[bFromIndex + i])) {
                return false;
            }
        }
        return true;
    }

    public static boolean equals(short[] a, int aFromIndex, int aToIndex, short[] b, int bFromIndex, int bToIndex) {
        Check.fromToIndex(aFromIndex, aToIndex, a.length);
        Check.fromToIndex(bFromIndex, bToIndex, b.length);

        int aLength = aToIndex - aFromIndex;
        int bLength = bToIndex - bFromIndex;
        if (aLength != bLength) {
            return false;
        }

        for (int i = 0; i < aLength; i++) {
            if (!(a[aFromIndex + i] == b[bFromIndex + i])) {
                return false;
            }
        }
        return true;
    }

    public static boolean equals(int[] a, int aFromIndex, int aToIndex, int[] b, int bFromIndex, int bToIndex) {
        Check.fromToIndex(aFromIndex, aToIndex, a.length);
        Check.fromToIndex(bFromIndex, bToIndex, b.length);

        int aLength = aToIndex - aFromIndex;
        int bLength = bToIndex - bFromIndex;
        if (aLength != bLength) {
            return false;
        }

        for (int i = 0; i < aLength; i++) {
            if (!(a[aFromIndex + i] == b[bFromIndex + i])) {
                return false;
            }
        }
        return true;
    }

    public static boolean equals(long[] a, int aFromIndex, int aToIndex, long[] b, int bFromIndex, int bToIndex) {
        Check.fromToIndex(aFromIndex, aToIndex, a.length);
        Check.fromToIndex(bFromIndex, bToIndex, b.length);

        int aLength = aToIndex - aFromIndex;
        int bLength = bToIndex - bFromIndex;
        if (aLength != bLength) {
            return false;
        }

        for (int i = 0; i < aLength; i++) {
            if (!(a[aFromIndex + i] == b[bFromIndex + i])) {
                return false;
            }
        }
        return true;
    }

    public static boolean equals(float[] a, int aFromIndex, int aToIndex, float[] b, int bFromIndex, int bToIndex) {
        Check.fromToIndex(aFromIndex, aToIndex, a.length);
        Check.fromToIndex(bFromIndex, bToIndex, b.length);

        int aLength = aToIndex - aFromIndex;
        int bLength = bToIndex - bFromIndex;
        if (aLength != bLength) {
            return false;
        }

        for (int i = 0; i < aLength; i++) {
            if (!(Float.floatToIntBits(a[aFromIndex + i]) == Float.floatToIntBits(b[bFromIndex + i]))) {
                return false;
            }
        }
        return true;
    }

    public static boolean equals(double[] a, int aFromIndex, int aToIndex, double[] b, int bFromIndex, int bToIndex) {
        Check.fromToIndex(aFromIndex, aToIndex, a.length);
        Check.fromToIndex(bFromIndex, bToIndex, b.length);

        int aLength = aToIndex - aFromIndex;
        int bLength = bToIndex - bFromIndex;
        if (aLength != bLength) {
            return false;
        }

        for (int i = 0; i < aLength; i++) {
            if (!(Double.doubleToLongBits(a[aFromIndex + i]) == Double.doubleToLongBits(b[bFromIndex + i]))) {
                return false;
            }
        }
        return true;
    }

    public static boolean equals(char[] a, int aFromIndex, int aToIndex, char[] b, int bFromIndex, int bToIndex) {
        Check.fromToIndex(aFromIndex, aToIndex, a.length);
        Check.fromToIndex(bFromIndex, bToIndex, b.length);

        int aLength = aToIndex - aFromIndex;
        int bLength = bToIndex - bFromIndex;
        if (aLength != bLength) {
            return false;
        }

        for (int i = 0; i < aLength; i++) {
            if (!(a[aFromIndex + i] == b[bFromIndex + i])) {
                return false;
            }
        }
        return true;
    }

    public static boolean equals(boolean[] a, int aFromIndex, int aToIndex, boolean[] b, int bFromIndex, int bToIndex) {
        Check.fromToIndex(aFromIndex, aToIndex, a.length);
        Check.fromToIndex(bFromIndex, bToIndex, b.length);

        int aLength = aToIndex - aFromIndex;
        int bLength = bToIndex - bFromIndex;
        if (aLength != bLength) {
            return false;
        }

        for (int i = 0; i < aLength; i++) {
            if (!(a[aFromIndex + i] == b[bFromIndex + i])) {
                return false;
            }
        }
        return true;
    }

    public static boolean equals(Object[] a, int aFromIndex, int aToIndex, Object[] b, int bFromIndex, int bToIndex) {
        Check.fromToIndex(aFromIndex, aToIndex, a.length);
        Check.fromToIndex(bFromIndex, bToIndex, b.length);

        int aLength = aToIndex - aFromIndex;
        int bLength = bToIndex - bFromIndex;
        if (aLength != bLength) {
            return false;
        }

        for (int i = 0; i < aLength; i++) {
            if (!Objects.equals(a[aFromIndex + i], b[bFromIndex + i])) {
                return false;
            }
        }
        return true;
    }


    public static int hashCode(byte[] array) {
        return hashCode(array, 0, array.length);
    }

    public static int hashCode(short[] array) {
        return hashCode(array, 0, array.length);
    }

    public static int hashCode(int[] array) {
        return hashCode(array, 0, array.length);
    }

    public static int hashCode(long[] array) {
        return hashCode(array, 0, array.length);
    }

    public static int hashCode(float[] array) {
        return hashCode(array, 0, array.length);
    }

    public static int hashCode(double[] array) {
        return hashCode(array, 0, array.length);
    }

    public static int hashCode(char[] array) {
        return hashCode(array, 0, array.length);
    }

    public static int hashCode(boolean[] array) {
        return hashCode(array, 0, array.length);
    }

    public static int hashCode(Object[] array) {
        return hashCode(array, 0, array.length);
    }


    public static int hashCode(byte[] array, int fromIndex, int toIndex) {
        Check.fromToIndex(fromIndex, toIndex, array.length);

        int result = 1;
        for (int i = fromIndex; i < toIndex; i++) {
            result = 31 * result + Byte.hashCode(array[i]);
        }
        return result;
    }

    public static int hashCode(short[] array, int fromIndex, int toIndex) {
        Check.fromToIndex(fromIndex, toIndex, array.length);

        int result = 1;
        for (int i = fromIndex; i < toIndex; i++) {
            result = 31 * result + Short.hashCode(array[i]);
        }
        return result;
    }

    public static int hashCode(int[] array, int fromIndex, int toIndex) {
        Check.fromToIndex(fromIndex, toIndex, array.length);

        int result = 1;
        for (int i = fromIndex; i < toIndex; i++) {
            result = 31 * result + Integer.hashCode(array[i]);
        }
        return result;
    }

    public static int hashCode(long[] array, int fromIndex, int toIndex) {
        Check.fromToIndex(fromIndex, toIndex, array.length);

        int result = 1;
        for (int i = fromIndex; i < toIndex; i++) {
            result = 31 * result + Long.hashCode(array[i]);
        }
        return result;
    }

    public static int hashCode(float[] array, int fromIndex, int toIndex) {
        Check.fromToIndex(fromIndex, toIndex, array.length);

        int result = 1;
        for (int i = fromIndex; i < toIndex; i++) {
            result = 31 * result + Float.hashCode(array[i]);
        }
        return result;
    }

    public static int hashCode(double[] array, int fromIndex, int toIndex) {
        Check.fromToIndex(fromIndex, toIndex, array.length);

        int result = 1;
        for (int i = fromIndex; i < toIndex; i++) {
            result = 31 * result + Double.hashCode(array[i]);
        }
        return result;
    }

    public static int hashCode(char[] array, int fromIndex, int toIndex) {
        Check.fromToIndex(fromIndex, toIndex, array.length);

        int result = 1;
        for (int i = fromIndex; i < toIndex; i++) {
            result = 31 * result + Character.hashCode(array[i]);
        }
        return result;
    }

    public static int hashCode(boolean[] array, int fromIndex, int toIndex) {
        Check.fromToIndex(fromIndex, toIndex, array.length);

        int result = 1;
        for (int i = fromIndex; i < toIndex; i++) {
            result = 31 * result + Boolean.hashCode(array[i]);
        }
        return result;
    }

    public static int hashCode(Object[] array, int fromIndex, int toIndex) {
        Check.fromToIndex(fromIndex, toIndex, array.length);

        int result = 1;
        for (int i = fromIndex; i < toIndex; i++) {
            result = 31 * result + Objects.hashCode(array[i]);
        }
        return result;
    }


    public static String toString(byte[] array) {
        return toString(array, 0, array.length);
    }

    public static String toString(short[] array) {
        return toString(array, 0, array.length);
    }

    public static String toString(int[] array) {
        return toString(array, 0, array.length);
    }

    public static String toString(long[] array) {
        return toString(array, 0, array.length);
    }

    public static String toString(float[] array) {
        return toString(array, 0, array.length);
    }

    public static String toString(double[] array) {
        return toString(array, 0, array.length);
    }

    public static String toString(char[] array) {
        return toString(array, 0, array.length);
    }

    public static String toString(boolean[] array) {
        return toString(array, 0, array.length);
    }

    public static String toString(Object[] array) {
        return toString(array, 0, array.length);
    }


    public static String toString(byte[] array, int fromIndex, int toIndex) {
        Check.fromToIndex(fromIndex, toIndex, array.length);
        if (fromIndex == toIndex) {
            return "[]";
        }

        StringBuilder builder = new StringBuilder().append('[').append(array[fromIndex]);
        for (int i = fromIndex + 1; i < toIndex; i++) {
            builder.append(", ").append(array[i]);
        }
        return builder.append(']').toString();
    }

    public static String toString(short[] array, int fromIndex, int toIndex) {
        Check.fromToIndex(fromIndex, toIndex, array.length);
        if (fromIndex == toIndex) {
            return "[]";
        }

        StringBuilder builder = new StringBuilder().append('[').append(array[fromIndex]);
        for (int i = fromIndex + 1; i < toIndex; i++) {
            builder.append(", ").append(array[i]);
        }
        return builder.append(']').toString();
    }

    public static String toString(int[] array, int fromIndex, int toIndex) {
        Check.fromToIndex(fromIndex, toIndex, array.length);
        if (fromIndex == toIndex) {
            return "[]";
        }

        StringBuilder builder = new StringBuilder().append('[').append(array[fromIndex]);
        for (int i = fromIndex + 1; i < toIndex; i++) {
            builder.append(", ").append(array[i]);
        }
        return builder.append(']').toString();
    }

    public static String toString(long[] array, int fromIndex, int toIndex) {
        Check.fromToIndex(fromIndex, toIndex, array.length);
        if (fromIndex == toIndex) {
            return "[]";
        }

        StringBuilder builder = new StringBuilder().append('[').append(array[fromIndex]);
        for (int i = fromIndex + 1; i < toIndex; i++) {
            builder.append(", ").append(array[i]);
        }
        return builder.append(']').toString();
    }

    public static String toString(float[] array, int fromIndex, int toIndex) {
        Check.fromToIndex(fromIndex, toIndex, array.length);
        if (fromIndex == toIndex) {
            return "[]";
        }

        StringBuilder builder = new StringBuilder().append('[').append(array[fromIndex]);
        for (int i = fromIndex + 1; i < toIndex; i++) {
            builder.append(", ").append(array[i]);
        }
        return builder.append(']').toString();
    }

    public static String toString(double[] array, int fromIndex, int toIndex) {
        Check.fromToIndex(fromIndex, toIndex, array.length);
        if (fromIndex == toIndex) {
            return "[]";
        }

        StringBuilder builder = new StringBuilder().append('[').append(array[fromIndex]);
        for (int i = fromIndex + 1; i < toIndex; i++) {
            builder.append(", ").append(array[i]);
        }
        return builder.append(']').toString();
    }

    public static String toString(char[] array, int fromIndex, int toIndex) {
        Check.fromToIndex(fromIndex, toIndex, array.length);
        if (fromIndex == toIndex) {
            return "[]";
        }

        StringBuilder builder = new StringBuilder().append('[').append(array[fromIndex]);
        for (int i = fromIndex + 1; i < toIndex; i++) {
            builder.append(", ").append(array[i]);
        }
        return builder.append(']').toString();
    }

    public static String toString(boolean[] array, int fromIndex, int toIndex) {
        Check.fromToIndex(fromIndex, toIndex, array.length);
        if (fromIndex == toIndex) {
            return "[]";
        }

        StringBuilder builder = new StringBuilder().append('[').append(array[fromIndex]);
        for (int i = fromIndex + 1; i < toIndex; i++) {
            builder.append(", ").append(array[i]);
        }
        return builder.append(']').toString();
    }

    public static String toString(Object[] array, int fromIndex, int toIndex) {
        Check.fromToIndex(fromIndex, toIndex, array.length);
        if (fromIndex == toIndex) {
            return "[]";
        }

        StringBuilder builder = new StringBuilder().append('[').append(array[fromIndex]);
        for (int i = fromIndex + 1; i < toIndex; i++) {
            builder.append(", ").append(array[i]);
        }
        return builder.append(']').toString();
    }

}
