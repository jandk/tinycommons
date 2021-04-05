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

    /**
     * Converts a list to an unmodifiable list, possibly optimizing memory usage
     * <p>
     * If you modify the underlying list after creation, the result is undefined.
     *
     * @param source The source list to convert into an unmodifiable list
     * @param <T>    The type of elements in the list
     * @return The unmodifiable list
     */
    public static <T> List<T> toUnmodifiableList(List<T> source) {
        switch (Check.notNull(source, "source").size()) {
            case 0:
                return Collections.emptyList();
            case 1:
                return Collections.singletonList(source.get(0));
            default:
                if (source instanceof ArrayList) {
                    ((ArrayList<T>) source).trimToSize();
                }
                return Collections.unmodifiableList(source);
        }
    }

    /**
     * Converts a set to an unmodifiable set, possibly optimizing memory usage
     * <p>
     * If you modify the underlying set after creation, the result is undefined.
     *
     * @param source The source set to convert into an unmodifiable set
     * @param <T>    The type of elements in the set
     * @return The unmodifiable set
     */
    public static <T> Set<T> toUnmodifiableSet(Set<T> source) {
        switch (Check.notNull(source, "source").size()) {
            case 0:
                return Collections.emptySet();
            case 1:
                return Collections.singleton(source.iterator().next());
            default:
                return Collections.unmodifiableSet(source);
        }
    }

    /**
     * Converts a map to an unmodifiable map, possibly optimizing memory usage
     * <p>
     * If you modify the underlying map after creation, the result is undefined.
     *
     * @param source The source map to convert into an unmodifiable map
     * @param <K>    The type of keys in the map
     * @param <V>    The type of values in the map
     * @return The unmodifiable map
     */
    public static <K, V> Map<K, V> toUnmodifiableMap(Map<K, V> source) {
        switch (Check.notNull(source, "source").size()) {
            case 0:
                return Collections.emptyMap();
            case 1:
                Map.Entry<K, V> entry = source.entrySet().iterator().next();
                return Collections.singletonMap(entry.getKey(), entry.getValue());
            default:
                return Collections.unmodifiableMap(source);
        }
    }

}
