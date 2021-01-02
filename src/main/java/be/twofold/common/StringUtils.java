package be.twofold.common;

public final class StringUtils {

    private StringUtils() {
        throw new UnsupportedOperationException();
    }

    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isBlank(CharSequence cs) {
        if (isEmpty(cs)) {
            return true;
        }

        for (int i = 0; i < cs.length(); i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String nullToEmpty(String s) {
        return s == null ? "" : s;
    }

    public static String emptyToNull(CharSequence cs) {
        return isEmpty(cs) ? null : cs.toString();
    }

    public static String blankToNull(CharSequence cs) {
        return isBlank(cs) ? null : cs.toString();
    }

}
