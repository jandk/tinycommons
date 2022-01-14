package be.twofold.common.text.predicates;

import be.twofold.common.*;
import be.twofold.common.text.*;

public final class Range implements CharPredicate {
    private final char from;
    private final char to;

    public Range(char from, char to) {
        Check.argument(from <= to);
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean matches(char c) {
        return c >= from && c <= to;
    }
}
