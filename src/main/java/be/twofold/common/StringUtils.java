package be.twofold.common;

public final class StringUtils {

    private StringUtils() {
        throw new UnsupportedOperationException();
    }

    public static boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public static boolean isBlank(String s) {
        return s == null || s.isBlank();
    }

    public static String nullToEmpty(String s) {
        return s == null ? "" : s;
    }

    public static String emptyToNull(String s) {
        return isEmpty(s) ? null : s;
    }

    public static String blankToNull(String s) {
        return isBlank(s) ? null : s;
    }

}
