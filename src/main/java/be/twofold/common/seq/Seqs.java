package be.twofold.common.seq;

import java.util.*;
import java.util.concurrent.atomic.*;

final class Seqs {

    private Seqs() {
        throw new UnsupportedOperationException();
    }

    static final class Once<T> implements Seq<T> {
        private final AtomicReference<Seq<T>> reference;

        Once(Seq<T> seq) {
            reference = new AtomicReference<>(seq);
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

}
