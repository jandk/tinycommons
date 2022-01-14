package be.twofold.common.text.internal;

import be.twofold.common.text.*;

public final class Is implements CharPredicate {
    final char match;

    public Is(char match) {
        this.match = match;
    }


    @Override
    public boolean matches(char c) {
        return c == match;
    }
}
