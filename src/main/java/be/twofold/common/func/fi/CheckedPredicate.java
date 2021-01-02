package be.twofold.common.func.fi;

@FunctionalInterface
public interface CheckedPredicate<T> {
    boolean test(T t) throws Throwable;
}
