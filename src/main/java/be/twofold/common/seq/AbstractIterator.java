package be.twofold.common.seq;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class AbstractIterator<T> implements Iterator<T> {
    private static final int NOT_READY = 0;
    private static final int READY = 1;
    private static final int DONE = 2;

    private int state = 0;

    private T next;

    @Override
    public final boolean hasNext() {
        switch (state) {
            case DONE:
                return false;
            case READY:
                return true;
            default:
        }
        return tryToComputeNext();
    }

    @Override
    public final T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        state = NOT_READY;
        T result = next;
        next = null;
        return result;
    }

    private boolean tryToComputeNext() {
        next = computeNext();
        if (state != DONE) {
            state = READY;
            return true;
        }
        return false;
    }

    protected abstract T computeNext();

    protected final T done() {
        state = DONE;
        return null;
    }
}
