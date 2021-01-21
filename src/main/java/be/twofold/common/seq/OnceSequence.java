package be.twofold.common.seq;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;

final class OnceSequence<T> extends Sequence<T> {
    private final AtomicReference<Sequence<T>> reference;

    OnceSequence(Sequence<T> sequence) {
        this.reference = new AtomicReference<>(sequence);
    }

    @Override
    public Iterator<T> iterator() {
        Sequence<T> sequence = reference.getAndSet(null);
        if (sequence == null) {
            throw new IllegalStateException("Sequence can only be iterated once");
        }
        return sequence.iterator();
    }
}
