package be.twofold.common;

public final class StringUtils {

    private StringUtils() {
        throw new UnsupportedOperationException();
    }

    /**
     * Check if a string is null or empty.
     *
     * @param string The string to check
     * @return {@code true} if the string is null or empty
     */
    public static boolean isEmpty(String string) {
        return string == null || string.isEmpty();
    }

    /**
     * Check if a string is null or blank.
     *
     * @param string The string to check
     * @return {@code true} if the string is null or blank
     */
    public static boolean isBlank(String string) {
        return string == null || string.isBlank();
    }

    public static String nullToEmpty(String string) {
        return string == null ? "" : string;
    }

    public static String emptyToNull(String string) {
        return isEmpty(string) ? null : string;
    }

    public static String blankToNull(String string) {
        return isBlank(string) ? null : string;
    }

}
