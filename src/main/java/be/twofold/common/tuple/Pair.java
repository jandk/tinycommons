package be.twofold.common.tuple;

import java.io.*;
import java.util.*;

public final class Pair<T, U> implements Serializable {

    private final T first;
    private final U second;

    private Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public static <T, U> Pair<T, U> of(T first, U second) {
        return new Pair<>(first, second);
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Pair)) return false;

        Pair<?, ?> other = (Pair<?, ?>) obj;
        return Objects.equals(first, other.first)
            && Objects.equals(second, other.second);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + Objects.hashCode(first);
        result = 31 * result + Objects.hashCode(second);
        return result;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }

}
