package be.twofold.common.text;

import be.twofold.common.text.predicates.*;

import java.util.function.*;

public interface CharPredicate extends Predicate<Character> {

    static CharPredicate any() {
        return Any.Instance;
    }

    static CharPredicate none() {
        return None.Instance;
    }

    static CharPredicate is(char match) {
        return new Is(match);
    }

    static CharPredicate among(CharSequence matches) {
        switch (matches.length()) {
            case 0:
                return none();
            case 1:
                return is(matches.charAt(0));
            default:
                return new Among(matches);
        }
    }

    static CharPredicate range(char from, char to) {
        return new Range(from, to);
    }

    static CharPredicate from(Predicate<? super Character> predicate) {
        if (predicate instanceof CharPredicate) {
            return (CharPredicate) predicate;
        }
        return new From(predicate);
    }


    boolean matches(char c);

    default CharPredicate and(CharPredicate other) {
        return new And(this, other);
    }

    default CharPredicate or(CharPredicate other) {
        return new Or(this, other);
    }

    @Override
    default CharPredicate negate() {
        return new Not(this);
    }

    @Override
    @Deprecated
    default boolean test(Character c) {
        return matches(c);
    }


    default String stripFrom(CharSequence cs) {
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

    default String stripLeadingFrom(CharSequence cs) {
        for (int i = 0, len = cs.length(); i < len; i++) {
            if (!matches(cs.charAt(i))) {
                return cs.subSequence(i, len).toString();
            }
        }
        return "";
    }

    default String stripTrailingFrom(CharSequence cs) {
        for (int i = cs.length() - 1; i >= 0; i--) {
            if (!matches(cs.charAt(i))) {
                return cs.subSequence(0, i + 1).toString();
            }
        }
        return "";
    }

}
