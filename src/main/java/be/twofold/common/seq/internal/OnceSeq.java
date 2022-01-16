package be.twofold.common.seq.internal;

import be.twofold.common.seq.*;

import java.util.*;
import java.util.concurrent.atomic.*;

public final class OnceSeq<T> implements Seq<T> {
    private final AtomicReference<Seq<T>> reference;

    public OnceSeq(Seq<T> seq) {
        this.reference = new AtomicReference<>(seq);
    }

    @Override
    public Iterator<T> iterator() {
        Seq<T> seq = reference.getAndSet(null);
        if (seq == null) {
            throw new IllegalStateException("Sequence can only be iterated once");
        }
        return seq.iterator();
    }
}
