package be.twofold.common.func.fi;

@FunctionalInterface
public interface CheckedConsumer<T> {
    void accept(T t) throws Throwable;
}
