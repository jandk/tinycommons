package be.twofold.common.tuple;

import java.util.*;

public final class Pair<T1, T2> {

    private final T1 first;
    private final T2 second;

    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    public T1 getFirst() {
        return first;
    }

    public T2 getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Pair)) return false;

        Pair<?, ?> pair = (Pair<?, ?>) obj;
        return Objects.equals(first, pair.first)
            && Objects.equals(second, pair.second);
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
