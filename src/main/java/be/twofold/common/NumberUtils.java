package be.twofold.common;

import java.util.*;

public final class NumberUtils {

    private NumberUtils() {
        throw new UnsupportedOperationException();
    }

    // region max

    public static byte max(byte... array) {
        Check.argument(array.length > 0, "array is empty");

        byte max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        return max;
    }

    public static short max(short... array) {
        Check.argument(array.length > 0, "array is empty");

        short max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        return max;
    }

    public static int max(int... array) {
        Check.argument(array.length > 0, "array is empty");

        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        return max;
    }

    public static long max(long... array) {
        Check.argument(array.length > 0, "array is empty");

        long max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        return max;
    }

    public static float max(float... array) {
        Check.argument(array.length > 0, "array is empty");

        float max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        return max;
    }

    public static double max(double... array) {
        Check.argument(array.length > 0, "array is empty");

        double max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        return max;
    }

    // endregion

    // region min

    public static byte min(byte... array) {
        Check.argument(array.length > 0, "array is empty");

        byte min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }

        return min;
    }

    public static short min(short... array) {
        Check.argument(array.length > 0, "array is empty");

        short min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }

        return min;
    }

    public static int min(int... array) {
        Check.argument(array.length > 0, "array is empty");

        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }

        return min;
    }

    public static long min(long... array) {
        Check.argument(array.length > 0, "array is empty");

        long min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }

        return min;
    }

    public static float min(float... array) {
        Check.argument(array.length > 0, "array is empty");

        float min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }

        return min;
    }

    public static double min(double... array) {
        Check.argument(array.length > 0, "array is empty");

        double min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }

        return min;
    }

    // endregion

    // region tryParse

    public static OptionalInt tryParseInt(String s) {
        if (s == null) {
            return OptionalInt.empty();
        }

        try {
            return OptionalInt.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return OptionalInt.empty();
        }
    }

    public static OptionalLong tryParseLong(String s) {
        if (s == null) {
            return OptionalLong.empty();
        }

        try {
            return OptionalLong.of(Long.parseLong(s));
        } catch (NumberFormatException e) {
            return OptionalLong.empty();
        }
    }

    public static OptionalDouble tryParseDouble(String s) {
        if (s == null) {
            return OptionalDouble.empty();
        }

        try {
            return OptionalDouble.of(Double.parseDouble(s));
        } catch (NumberFormatException e) {
            return OptionalDouble.empty();

        }
    }

    // endregion

}
