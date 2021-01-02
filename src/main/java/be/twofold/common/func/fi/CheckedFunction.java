package be.twofold.common.func.fi;

@FunctionalInterface
public interface CheckedFunction<T, R> {
    R apply(T t) throws Throwable;
}
