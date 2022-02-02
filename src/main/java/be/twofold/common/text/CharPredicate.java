package be.twofold.common.text;

import be.twofold.common.*;

import java.util.*;
import java.util.function.*;

public abstract class CharPredicate implements Predicate<Character> {

    private CharPredicate() {
    }

    public static CharPredicate any() {
        return Any.Instance;
    }

    public static CharPredicate none() {
        return None.Instance;
    }

    public static CharPredicate is(char c) {
        return new Is(c);
    }

    public static CharPredicate range(char lower, char upper) {
        return new Range(lower, upper);
    }

    public static CharPredicate among(CharSequence chars) {
        Check.notNull(chars, "chars is null");
        switch (chars.length()) {
            case 0:
                return none();
            case 1:
                return is(chars.charAt(0));
            default:
                return new Among(chars);
        }
    }

    public static CharPredicate from(Predicate<? super Character> predicate) {
        if (predicate instanceof CharPredicate) {
            return (CharPredicate) predicate;
        }
        return new From(predicate);
    }

    // Abstract Methods

    public abstract boolean matches(char c);

    @Override
    @Deprecated
    public boolean test(Character c) {
        return matches(c);
    }

    @Override
    public CharPredicate and(Predicate<? super Character> other) {
        return and(from(other));
    }

    public CharPredicate and(CharPredicate other) {
        return new And(this, other);
    }

    @Override
    public CharPredicate or(Predicate<? super Character> other) {
        return or(from(other));
    }

    public CharPredicate or(CharPredicate other) {
        return new Or(this, other);
    }

    @Override
    public CharPredicate negate() {
        return new Negate(this);
    }

    // Class Methods

    public String stripFrom(CharSequence cs) {
        int len = cs.length();

        int from;
        for (from = 0; from < len; from++) {
            if (!matches(cs.charAt(from))) {
                break;
            }
        }

        int to;
        for (to = len - 1; to > from; to--) {
            if (!matches(cs.charAt(to))) {
                break;
            }
        }

        return cs.subSequence(from, to + 1).toString();
    }

    public String stripLeadingFrom(CharSequence cs) {
        for (int i = 0, len = cs.length(); i < len; i++) {
            if (!matches(cs.charAt(i))) {
                return cs.subSequence(i, len).toString();
            }
        }
        return "";
    }

    public String stripTrailingFrom(CharSequence cs) {
        for (int i = cs.length() - 1; i >= 0; i--) {
            if (!matches(cs.charAt(i))) {
                return cs.subSequence(0, i + 1).toString();
            }
        }
        return "";
    }

    // Implementation classes

    private static final class Any extends CharPredicate {
        private static final Any Instance = new Any();

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

    private static final class None extends CharPredicate {
        private static final None Instance = new None();

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

    private static final class Is extends CharPredicate {
        private final char c;

        private Is(char c) {
            this.c = c;
        }

        @Override
        public boolean matches(char c) {
            return c == this.c;
        }
    }

    private static final class Range extends CharPredicate {
        private final char lower;
        private final char upper;

        private Range(char lower, char upper) {
            Check.argument(lower <= upper, "lower > upper");
            this.lower = lower;
            this.upper = upper;
        }

        @Override
        public boolean matches(char c) {
            return lower <= c && c <= upper;
        }
    }

    private static final class Among extends CharPredicate {
        private final char[] chars;

        private Among(CharSequence chars) {
            Check.notNull(chars, "chars is null");
            this.chars = chars.toString().toCharArray();
            Arrays.sort(this.chars);
        }

        @Override
        public boolean matches(char c) {
            return Arrays.binarySearch(chars, c) >= 0;
        }
    }

    private static final class From extends CharPredicate {
        private final Predicate<? super Character> predicate;

        private From(Predicate<? super Character> predicate) {
            this.predicate = Check.notNull(predicate, "predicate is null");
        }

        @Override
        public boolean matches(char c) {
            return predicate.test(c);
        }
    }

    private static final class And extends CharPredicate {
        private final CharPredicate first;
        private final CharPredicate second;

        private And(CharPredicate first, CharPredicate second) {
            this.first = Check.notNull(first, "first is null");
            this.second = Check.notNull(second, "second is null");
        }

        @Override
        public boolean matches(char c) {
            return first.matches(c) && second.matches(c);
        }
    }

    private static final class Or extends CharPredicate {
        private final CharPredicate first;
        private final CharPredicate second;

        private Or(CharPredicate first, CharPredicate second) {
            this.first = Check.notNull(first, "first is null");
            this.second = Check.notNull(second, "second is null");
        }

        @Override
        public boolean matches(char c) {
            return first.matches(c) || second.matches(c);
        }
    }

    private static final class Negate extends CharPredicate {
        private final CharPredicate predicate;

        private Negate(CharPredicate predicate) {
            this.predicate = Check.notNull(predicate, "predicate is null");
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

}
