package be.twofold.common.text.predicates;

import be.twofold.common.*;
import be.twofold.common.text.*;

public final class And implements CharPredicate {
    private final CharPredicate first;
    private final CharPredicate second;

    public And(CharPredicate first, CharPredicate second) {
        this.first = Check.notNull(first);
        this.second = Check.notNull(second);
    }

    @Override
    public boolean matches(char c) {
        return first.matches(c) && second.matches(c);
    }
}
