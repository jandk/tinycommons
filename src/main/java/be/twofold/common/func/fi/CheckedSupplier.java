package be.twofold.common.func.fi;

@FunctionalInterface
public interface CheckedSupplier<T> {
    T get() throws Throwable;
}
