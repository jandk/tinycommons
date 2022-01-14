package be.twofold.common;

import java.util.*;

public final class CollectionUtils {

    private CollectionUtils() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns {@code true} if a collection is {@code null} or empty.
     *
     * @param collection The collection to check
     * @return True if null or empty, false otherwise
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * Returns {@code true} if a map is {@code null} or empty.
     *
     * @param map The map to check
     * @return True if null or empty, false otherwise
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    // region toArray

    public static byte[] toByteArray(Collection<? extends Number> collection) {
        Check.notNull(collection, "collection");

        int i = 0;
        byte[] array = new byte[collection.size()];
        for (Number number : collection) {
            array[i++] = number.byteValue();
        }

        return array;
    }

    public static short[] toShortArray(Collection<? extends Number> collection) {
        Check.notNull(collection, "collection");

        int i = 0;
        short[] array = new short[collection.size()];
        for (Number number : collection) {
            array[i++] = number.shortValue();
        }

        return array;
    }

    public static int[] toIntArray(Collection<? extends Number> collection) {
        Check.notNull(collection, "collection");

        int i = 0;
        int[] array = new int[collection.size()];
        for (Number number : collection) {
            array[i++] = number.intValue();
        }

        return array;
    }

    public static long[] toLongArray(Collection<? extends Number> collection) {
        Check.notNull(collection, "collection");

        int i = 0;
        long[] array = new long[collection.size()];
        for (Number number : collection) {
            array[i++] = number.longValue();
        }

        return array;
    }

    public static float[] toFloatArray(Collection<? extends Number> collection) {
        Check.notNull(collection, "collection");

        int i = 0;
        float[] array = new float[collection.size()];
        for (Number number : collection) {
            array[i++] = number.floatValue();
        }

        return array;
    }

    public static double[] toDoubleArray(Collection<? extends Number> collection) {
        Check.notNull(collection, "collection");

        int i = 0;
        double[] array = new double[collection.size()];
        for (Number number : collection) {
            array[i++] = number.doubleValue();
        }

        return array;
    }

    // endregion

}
