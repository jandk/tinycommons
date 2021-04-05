package be.twofold.common;

import java.lang.reflect.*;
import java.util.*;

public final class ArrayUtils {

    private ArrayUtils() {
        throw new UnsupportedOperationException();
    }

    public static boolean contains(boolean[] array, boolean value) {
        Check.notNull(array, "array");
        return indexOf(array, value) >= 0;
    }

    public static boolean contains(byte[] array, byte value) {
        Check.notNull(array, "array");
        return indexOf(array, value) >= 0;
    }

    public static boolean contains(char[] array, char value) {
        Check.notNull(array, "array");
        return indexOf(array, value) >= 0;
    }

    public static boolean contains(short[] array, short value) {
        Check.notNull(array, "array");
        return indexOf(array, value) >= 0;
    }

    public static boolean contains(int[] array, int value) {
        Check.notNull(array, "array");
        return indexOf(array, value) >= 0;
    }

    public static boolean contains(long[] array, long value) {
        Check.notNull(array, "array");
        return indexOf(array, value) >= 0;
    }

    public static boolean contains(float[] array, float value) {
        Check.notNull(array, "array");
        return indexOf(array, value) >= 0;
    }

    public static boolean contains(double[] array, double value) {
        Check.notNull(array, "array");
        return indexOf(array, value) >= 0;
    }


    public static int indexOf(boolean[] array, boolean value) {
        return indexOf(array, value, 0, array.length);
    }

    public static int indexOf(byte[] array, byte value) {
        return indexOf(array, value, 0, array.length);
    }

    public static int indexOf(char[] array, char value) {
        return indexOf(array, value, 0, array.length);
    }

    public static int indexOf(short[] array, short value) {
        return indexOf(array, value, 0, array.length);
    }

    public static int indexOf(int[] array, int value) {
        return indexOf(array, value, 0, array.length);
    }

    public static int indexOf(long[] array, long value) {
        return indexOf(array, value, 0, array.length);
    }

    public static int indexOf(float[] array, float value) {
        return indexOf(array, value, 0, array.length);
    }

    public static int indexOf(double[] array, double value) {
        return indexOf(array, value, 0, array.length);
    }


    public static int indexOf(boolean[] array, boolean value, int from, int to) {
        Check.positions(from, to, array.length);

        for (int i = from; i < to; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public static int indexOf(byte[] array, byte value, int from, int to) {
        Check.positions(from, to, array.length);

        for (int i = from; i < to; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public static int indexOf(char[] array, char value, int from, int to) {
        Check.positions(from, to, array.length);

        for (int i = from; i < to; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public static int indexOf(short[] array, short value, int from, int to) {
        Check.positions(from, to, array.length);

        for (int i = from; i < to; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public static int indexOf(int[] array, int value, int from, int to) {
        Check.positions(from, to, array.length);

        for (int i = from; i < to; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public static int indexOf(long[] array, long value, int from, int to) {
        Check.positions(from, to, array.length);

        for (int i = from; i < to; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public static int indexOf(float[] array, float value, int from, int to) {
        Check.positions(from, to, array.length);

        for (int i = from; i < to; i++) {
            if (Float.floatToIntBits(array[i]) == Float.floatToIntBits(value)) {
                return i;
            }
        }
        return -1;
    }

    public static int indexOf(double[] array, double value, int from, int to) {
        Check.positions(from, to, array.length);

        for (int i = from; i < to; i++) {
            if (Double.doubleToLongBits(array[i]) == Double.doubleToLongBits(value)) {
                return i;
            }
        }
        return -1;
    }


    public static int lastIndexOf(boolean[] array, boolean value) {
        return lastIndexOf(array, value, 0, array.length);
    }

    public static int lastIndexOf(byte[] array, byte value) {
        return lastIndexOf(array, value, 0, array.length);
    }

    public static int lastIndexOf(char[] array, char value) {
        return lastIndexOf(array, value, 0, array.length);
    }

    public static int lastIndexOf(short[] array, short value) {
        return lastIndexOf(array, value, 0, array.length);
    }

    public static int lastIndexOf(int[] array, int value) {
        return lastIndexOf(array, value, 0, array.length);
    }

    public static int lastIndexOf(long[] array, long value) {
        return lastIndexOf(array, value, 0, array.length);
    }

    public static int lastIndexOf(float[] array, float value) {
        return lastIndexOf(array, value, 0, array.length);
    }

    public static int lastIndexOf(double[] array, double value) {
        return lastIndexOf(array, value, 0, array.length);
    }


    public static int lastIndexOf(boolean[] array, boolean value, int from, int to) {
        Check.positions(from, to, array.length);

        for (int i = to - 1; i >= from; i--) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public static int lastIndexOf(byte[] array, byte value, int from, int to) {
        Check.positions(from, to, array.length);

        for (int i = to - 1; i >= from; i--) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public static int lastIndexOf(char[] array, char value, int from, int to) {
        Check.positions(from, to, array.length);

        for (int i = to - 1; i >= from; i--) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public static int lastIndexOf(short[] array, short value, int from, int to) {
        Check.positions(from, to, array.length);

        for (int i = to - 1; i >= from; i--) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public static int lastIndexOf(int[] array, int value, int from, int to) {
        Check.positions(from, to, array.length);

        for (int i = to - 1; i >= from; i--) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public static int lastIndexOf(long[] array, long value, int from, int to) {
        Check.positions(from, to, array.length);

        for (int i = to - 1; i >= from; i--) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public static int lastIndexOf(float[] array, float value, int from, int to) {
        Check.positions(from, to, array.length);

        for (int i = to - 1; i >= from; i--) {
            if (Float.floatToIntBits(array[i]) == Float.floatToIntBits(value)) {
                return i;
            }
        }
        return -1;
    }

    public static int lastIndexOf(double[] array, double value, int from, int to) {
        Check.positions(from, to, array.length);

        for (int i = to - 1; i >= from; i--) {
            if (Double.doubleToLongBits(array[i]) == Double.doubleToLongBits(value)) {
                return i;
            }
        }
        return -1;
    }


    public static byte max(byte... array) {
        verifyNonEmptyArray(array);

        byte max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    public static short max(short... array) {
        verifyNonEmptyArray(array);

        short max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    public static int max(int... array) {
        verifyNonEmptyArray(array);

        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            max = Math.max(max, array[i]);
        }
        return max;
    }

    public static long max(long... array) {
        verifyNonEmptyArray(array);

        long max = array[0];
        for (int i = 1; i < array.length; i++) {
            max = Math.max(max, array[i]);
        }
        return max;
    }

    public static float max(float... array) {
        verifyNonEmptyArray(array);

        float max = array[0];
        for (int i = 1; i < array.length; i++) {
            max = Math.max(max, array[i]);
        }
        return max;
    }

    public static double max(double... array) {
        verifyNonEmptyArray(array);

        double max = array[0];
        for (int i = 1; i < array.length; i++) {
            max = Math.max(max, array[i]);
        }
        return max;
    }


    public static byte min(byte... array) {
        verifyNonEmptyArray(array);

        byte min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    public static short min(short... array) {
        verifyNonEmptyArray(array);

        short min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    public static int min(int... array) {
        verifyNonEmptyArray(array);

        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            min = Math.min(min, array[i]);
        }
        return min;
    }

    public static long min(long... array) {
        verifyNonEmptyArray(array);

        long min = array[0];
        for (int i = 1; i < array.length; i++) {
            min = Math.min(min, array[i]);
        }
        return min;
    }

    public static float min(float... array) {
        verifyNonEmptyArray(array);

        float min = array[0];
        for (int i = 1; i < array.length; i++) {
            min = Math.min(min, array[i]);
        }
        return min;
    }

    public static double min(double... array) {
        verifyNonEmptyArray(array);

        double min = array[0];
        for (int i = 1; i < array.length; i++) {
            min = Math.min(min, array[i]);
        }
        return min;
    }


    public static void reverse(boolean[] array) {
        Check.notNull(array, "array");
        reverse(array, 0, array.length);
    }

    public static void reverse(byte[] array) {
        Check.notNull(array, "array");
        reverse(array, 0, array.length);
    }

    public static void reverse(char[] array) {
        Check.notNull(array, "array");
        reverse(array, 0, array.length);
    }

    public static void reverse(short[] array) {
        Check.notNull(array, "array");
        reverse(array, 0, array.length);
    }

    public static void reverse(int[] array) {
        Check.notNull(array, "array");
        reverse(array, 0, array.length);
    }

    public static void reverse(long[] array) {
        Check.notNull(array, "array");
        reverse(array, 0, array.length);
    }

    public static void reverse(float[] array) {
        Check.notNull(array, "array");
        reverse(array, 0, array.length);
    }

    public static void reverse(double[] array) {
        Check.notNull(array, "array");
        reverse(array, 0, array.length);
    }

    public static void reverse(boolean[] array, int from, int to) {
        Check.notNull(array, "array");
        Check.positions(from, to, array.length);

        for (int i = from, j = to - 1; i < j; i++, j--) {
            boolean tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    public static void reverse(byte[] array, int from, int to) {
        Check.notNull(array, "array");
        Check.positions(from, to, array.length);

        for (int i = from, j = to - 1; i < j; i++, j--) {
            byte tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    public static void reverse(char[] array, int from, int to) {
        Check.notNull(array, "array");
        Check.positions(from, to, array.length);

        for (int i = from, j = to - 1; i < j; i++, j--) {
            char tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    public static void reverse(short[] array, int from, int to) {
        Check.notNull(array, "array");
        Check.positions(from, to, array.length);

        for (int i = from, j = to - 1; i < j; i++, j--) {
            short tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    public static void reverse(int[] array, int from, int to) {
        Check.notNull(array, "array");
        Check.positions(from, to, array.length);

        for (int i = from, j = to - 1; i < j; i++, j--) {
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    public static void reverse(long[] array, int from, int to) {
        Check.notNull(array, "array");
        Check.positions(from, to, array.length);

        for (int i = from, j = to - 1; i < j; i++, j--) {
            long tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    public static void reverse(float[] array, int from, int to) {
        Check.notNull(array, "array");
        Check.positions(from, to, array.length);

        for (int i = from, j = to - 1; i < j; i++, j--) {
            float tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    public static void reverse(double[] array, int from, int to) {
        Check.notNull(array, "array");
        Check.positions(from, to, array.length);

        for (int i = from, j = to - 1; i < j; i++, j--) {
            double tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }


    public static void sortDescending(byte[] array) {
        Check.notNull(array, "array");
        sortDescending(array, 0, array.length);
    }

    public static void sortDescending(char[] array) {
        Check.notNull(array, "array");
        sortDescending(array, 0, array.length);
    }

    public static void sortDescending(short[] array) {
        Check.notNull(array, "array");
        sortDescending(array, 0, array.length);
    }

    public static void sortDescending(int[] array) {
        Check.notNull(array, "array");
        sortDescending(array, 0, array.length);
    }

    public static void sortDescending(long[] array) {
        Check.notNull(array, "array");
        sortDescending(array, 0, array.length);
    }

    public static void sortDescending(float[] array) {
        Check.notNull(array, "array");
        sortDescending(array, 0, array.length);
    }

    public static void sortDescending(double[] array) {
        Check.notNull(array, "array");
        sortDescending(array, 0, array.length);
    }


    public static void sortDescending(byte[] array, int from, int to) {
        Check.notNull(array, "array");
        Check.positions(from, to, array.length);
        Arrays.sort(array, from, to);
        reverse(array, from, to);
    }

    public static void sortDescending(char[] array, int from, int to) {
        Check.notNull(array, "array");
        Check.positions(from, to, array.length);
        Arrays.sort(array, from, to);
        reverse(array, from, to);
    }

    public static void sortDescending(short[] array, int from, int to) {
        Check.notNull(array, "array");
        Check.positions(from, to, array.length);
        Arrays.sort(array, from, to);
        reverse(array, from, to);
    }

    public static void sortDescending(int[] array, int from, int to) {
        Check.notNull(array, "array");
        Check.positions(from, to, array.length);
        Arrays.sort(array, from, to);
        reverse(array, from, to);
    }

    public static void sortDescending(long[] array, int from, int to) {
        Check.notNull(array, "array");
        Check.positions(from, to, array.length);
        Arrays.sort(array, from, to);
        reverse(array, from, to);
    }

    public static void sortDescending(float[] array, int from, int to) {
        Check.notNull(array, "array");
        Check.positions(from, to, array.length);
        Arrays.sort(array, from, to);
        reverse(array, from, to);
    }

    public static void sortDescending(double[] array, int from, int to) {
        Check.notNull(array, "array");
        Check.positions(from, to, array.length);
        Arrays.sort(array, from, to);
        reverse(array, from, to);
    }

    private static void verifyNonEmptyArray(Object array) {
        Check.notNull(array, "array");
        Check.argument(Array.getLength(array) > 0, "array is empty");
    }

}
