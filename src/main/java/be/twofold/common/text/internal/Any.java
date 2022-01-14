package be.twofold.common.text.internal;

import be.twofold.common.*;
import be.twofold.common.text.*;

public final class Any implements CharPredicate {
    public static final Any Instance = new Any();

    private Any() {
    }


    @Override
    public boolean matches(char c) {
        return true;
    }

    @Override
    public CharPredicate and(CharPredicate other) {
        return Check.notNull(other);
    }

    @Override
    public CharPredicate or(CharPredicate other) {
        Check.notNull(other);
        return this;
    }

    @Override
    public CharPredicate negate() {
        return CharPredicate.none();
    }
}
