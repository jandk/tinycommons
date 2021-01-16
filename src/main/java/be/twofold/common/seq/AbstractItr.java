package be.twofold.common.seq;

import java.util.Iterator;
import java.util.NoSuchElementException;

abstract class AbstractItr<E> implements Iterator<E> {
    private static final int Failed = -1;
    private static final int NotReady = 0;
    private static final int Ready = 1;
    private static final int Done = 2;

    private int state = NotReady;
    private E next = null;

    @Override
    public boolean hasNext() {
        switch (state) {
            case Failed:
                throw new IllegalStateException();
            case NotReady:
                return tryComputeNext();
            case Ready:
                return true;
            case Done:
                return false;
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        state = NotReady;
        E next = this.next;
        this.next = null;
        return next;
    }

    private boolean tryComputeNext() {
        state = Failed;
        computeNext();
        return state == Ready;
    }

    protected abstract void computeNext();

    protected void setNext(E next) {
        this.next = next;
        state = Ready;
    }

    protected void done() {
        state = Done;
    }

}
