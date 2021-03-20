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

}
