package be.twofold.common.text.internal;

import be.twofold.common.*;
import be.twofold.common.text.*;

public final class None implements CharPredicate {
    public static final None Instance = new None();

    private None() {
    }


    @Override
    public boolean matches(char c) {
        return false;
    }

    @Override
    public CharPredicate and(CharPredicate other) {
        Check.notNull(other);
        return this;
    }

    @Override
    public CharPredicate or(CharPredicate other) {
        return Check.notNull(other);
    }

    @Override
    public CharPredicate negate() {
        return CharPredicate.any();
    }
}
