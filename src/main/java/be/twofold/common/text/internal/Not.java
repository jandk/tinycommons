package be.twofold.common.text.internal;

import be.twofold.common.*;
import be.twofold.common.text.*;

public final class Not implements CharPredicate {
    private final CharPredicate predicate;

    public Not(CharPredicate predicate) {
        this.predicate = Check.notNull(predicate);
    }

    @Override
    public boolean matches(char c) {
        return !predicate.matches(c);
    }

    @Override
    public CharPredicate negate() {
        return predicate;
    }
}
