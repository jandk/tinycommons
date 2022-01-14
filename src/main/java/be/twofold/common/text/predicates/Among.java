package be.twofold.common.text.predicates;

import be.twofold.common.text.*;

import java.util.*;

public final class Among implements CharPredicate {
    private final char[] matches;

    public Among(CharSequence matches) {
        this.matches = matches.toString().toCharArray();
        Arrays.sort(this.matches);
    }

    @Override
    public boolean matches(char c) {
        return Arrays.binarySearch(matches, c) >= 0;
    }
}
