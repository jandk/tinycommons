package be.twofold.common.tuple;

import java.io.*;
import java.util.*;

public final class Triple<T, U, V> implements Serializable {

    private final T first;
    private final U second;
    private final V third;

    public Triple(T first, U second, V third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    public V getThird() {
        return third;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Triple)) return false;

        Triple<?, ?, ?> other = (Triple<?, ?, ?>) obj;
        return Objects.equals(first, other.first)
            && Objects.equals(second, other.second)
            && Objects.equals(third, other.third);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + Objects.hashCode(first);
        result = 31 * result + Objects.hashCode(second);
        result = 31 * result + Objects.hashCode(third);
        return result;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ", " + third + ")";
    }

}
