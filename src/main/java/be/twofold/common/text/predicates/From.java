package be.twofold.common.text.predicates;

import be.twofold.common.*;
import be.twofold.common.text.*;

import java.util.function.*;

public final class From implements CharPredicate {
    private final Predicate<? super Character> predicate;

    public From(Predicate<? super Character> predicate) {
        this.predicate = Check.notNull(predicate);
    }

    @Override
    public boolean matches(char c) {
        return predicate.test(c);
    }
}
