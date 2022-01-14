package be.twofold.common;

import java.lang.reflect.*;

public final class NumberUtils {

    private NumberUtils() {
        throw new UnsupportedOperationException();
    }

    // region max

    public static byte max(byte... array) {
        check(array);

        byte max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        return max;
    }

    public static short max(short... array) {
        check(array);

        short max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        return max;
    }

    public static int max(int... array) {
        check(array);

        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        return max;
    }

    public static long max(long... array) {
        check(array);

        long max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        return max;
    }

    public static float max(float... array) {
        check(array);

        float max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        return max;
    }

    public static double max(double... array) {
        check(array);

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
        check(array);

        byte min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }

        return min;
    }

    public static short min(short... array) {
        check(array);

        short min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }

        return min;
    }

    public static int min(int... array) {
        check(array);

        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }

        return min;
    }

    public static long min(long... array) {
        check(array);

        long min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }

        return min;
    }

    public static float min(float... array) {
        check(array);

        float min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }

        return min;
    }

    public static double min(double... array) {
        check(array);

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

    public static Byte tryParseByte(String s) {
        if (s == null) {
            return null;
        }

        try {
            return Byte.parseByte(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Short tryParseShort(String s) {
        if (s == null) {
            return null;
        }

        try {
            return Short.parseShort(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Integer tryParseInt(String s) {
        if (s == null) {
            return null;
        }

        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Long tryParseLong(String s) {
        if (s == null) {
            return null;
        }

        try {
            return Long.parseLong(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Float tryParseFloat(String s) {
        if (s == null) {
            return null;
        }

        try {
            return Float.parseFloat(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Double tryParseDouble(String s) {
        if (s == null) {
            return null;
        }

        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return null;

        }
    }

    // endregion

    private static void check(Object array) {
        Check.notNull(array, "array");
        Check.argument(Array.getLength(array) > 0, "array is empty");
    }

}
