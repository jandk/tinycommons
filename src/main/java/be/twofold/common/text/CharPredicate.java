package be.twofold.common.text;

import be.twofold.common.*;

import java.util.*;
import java.util.function.*;

public abstract class CharPredicate implements Predicate<Character> {

    CharPredicate() {
    }

    public static CharPredicate any() {
        return Any.Instance;
    }

    public static CharPredicate none() {
        return None.Instance;
    }

    public static CharPredicate is(char match) {
        return new Is(match);
    }

    public static CharPredicate among(CharSequence matches) {
        switch (matches.length()) {
            case 0:
                return none();
            case 1:
                return is(matches.charAt(0));
            default:
                return new Among(matches);
        }
    }

    public static CharPredicate range(char from, char to) {
        return new Range(from, to);
    }

    public static CharPredicate from(Predicate<? super Character> predicate) {
        return predicate instanceof CharPredicate
            ? (CharPredicate) predicate
            : new From(predicate);
    }


    public abstract boolean matches(char c);

    public CharPredicate and(CharPredicate other) {
        return new And(this, other);
    }

    public CharPredicate or(CharPredicate other) {
        return new Or(this, other);
    }

    @Override
    public CharPredicate negate() {
        return new Not(this);
    }

    @Override
    @Deprecated
    public final boolean test(Character c) {
        return matches(c);
    }


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


    static final class Any extends CharPredicate {
        static final Any Instance = new Any();

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

    static final class None extends CharPredicate {
        static final None Instance = new None();

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
            return any();
        }
    }

    static final class Is extends CharPredicate {
        final char match;

        Is(char match) {
            this.match = match;
        }


        @Override
        public boolean matches(char c) {
            return c == match;
        }
    }

    static final class Among extends CharPredicate {
        private final char[] matches;

        Among(CharSequence matches) {
            this.matches = matches.toString().toCharArray();
            Arrays.sort(this.matches);
        }

        @Override
        public boolean matches(char c) {
            return Arrays.binarySearch(matches, c) >= 0;
        }
    }

    static final class Range extends CharPredicate {
        private final char from;
        private final char to;

        Range(char from, char to) {
            Check.argument(from <= to);
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean matches(char c) {
            return c >= from && c <= to;
        }
    }

    static final class From extends CharPredicate {
        private final Predicate<? super Character> predicate;

        From(Predicate<? super Character> predicate) {
            this.predicate = Check.notNull(predicate);
        }

        @Override
        public boolean matches(char c) {
            return predicate.test(c);
        }
    }

    static final class And extends CharPredicate {
        private final CharPredicate first;
        private final CharPredicate second;

        And(CharPredicate first, CharPredicate second) {
            this.first = Check.notNull(first);
            this.second = Check.notNull(second);
        }

        @Override
        public boolean matches(char c) {
            return first.matches(c) && second.matches(c);
        }
    }

    static final class Or extends CharPredicate {
        private final CharPredicate first;
        private final CharPredicate second;

        Or(CharPredicate first, CharPredicate second) {
            this.first = Check.notNull(first);
            this.second = Check.notNull(second);
        }

        @Override
        public boolean matches(char c) {
            return first.matches(c) || second.matches(c);
        }
    }

    static final class Not extends CharPredicate {
        private final CharPredicate predicate;

        Not(CharPredicate predicate) {
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

}
