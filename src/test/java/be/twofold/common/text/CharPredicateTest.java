package be.twofold.common.text;

import org.junit.jupiter.api.*;

import java.util.function.*;

import static org.assertj.core.api.Assertions.*;

@SuppressWarnings("ConstantConditions")
class CharPredicateTest {
    private static final char C1 = 'a';
    private static final char C2 = 'z';

    @Test
    @SuppressWarnings({"deprecation", "FunctionalExpressionCanBeFolded"})
    void testTest() {
        CharPredicate predicate = CharPredicate.is(C1);
        testAllChars(predicate, predicate::test, "test");
    }

    @Test
    void testAny() {
        CharPredicate predicate = CharPredicate.any();

        testAllChars(predicate, c -> true, "any");
        assertThat(CharPredicate.any()).isEqualTo(predicate);
    }

    @Test
    void testNone() {
        CharPredicate predicate = CharPredicate.none();

        testAllChars(predicate, c -> false, "none");
        assertThat(CharPredicate.none()).isEqualTo(predicate);
    }

    @Test
    void testIs() {
        CharPredicate predicate = CharPredicate.is(C1);

        testAllChars(predicate, c -> c == C1, "is");
    }

    @Test
    void testRange() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> CharPredicate.range(C2, C1));

        CharPredicate predicate = CharPredicate.range(C1, C2);

        testAllChars(predicate, c -> c >= C1 && c <= C2, "range");
    }

    @Test
    void testAmong() {
        assertThatNullPointerException()
            .isThrownBy(() -> CharPredicate.among(null));

        assertThat(CharPredicate.among(""))
            .isEqualTo(CharPredicate.none());

//        assertThat(CharPredicate.among("a"))
//            .isInstanceOfSatisfying(Is.class, predicate -> {
//                assertThat(predicate.match).isEqualTo('a');
//            });

        CharPredicate predicate = CharPredicate.among("abc");

        testAllChars(predicate, c -> c == 'a' || c == 'b' || c == 'c', "among");
    }

    @Test
    void testForPredicate() {
        CharPredicate predicate = CharPredicate.from(character -> character == 'a');
        testAllChars(predicate, character -> character == 'a', "from");
    }


    @Test
    void testAnd() {
        CharPredicate p1 = CharPredicate.range('a', 'n');
        CharPredicate p2 = CharPredicate.range('m', 'z');
        CharPredicate predicate = p1.and(p2);

        testAllChars(predicate, c -> c == 'm' || c == 'n', "and");
    }

    @Test
    void testOr() {
        CharPredicate p1 = CharPredicate.range('a', 'n');
        CharPredicate p2 = CharPredicate.range('m', 'z');
        CharPredicate predicate = p1.or(p2);

        testAllChars(predicate, c -> c >= 'a' && c <= 'z', "or");
    }

    @Test
    void testNot() {
        CharPredicate predicate = CharPredicate.is(C1).negate();

        testAllChars(predicate, c -> c != C1, "not");
    }


    @Test
    void testStripFrom() {
        CharPredicate predicate = CharPredicate.is(C1);

        assertThat(predicate.stripFrom("aaabbbaaa")).isEqualTo("bbb");
        assertThat(predicate.stripFrom("bbbaaabbb")).isEqualTo("bbbaaabbb");
        assertThat(predicate.stripFrom("aaaaaaaaa")).isEqualTo("");
    }

    @Test
    void testStripFromThrowsNPE() {
        CharPredicate predicate = CharPredicate.is(C1);

        assertThatNullPointerException()
            .isThrownBy(() -> predicate.stripFrom(null));
    }

    @Test
    void testStripLeadingFrom() {
        CharPredicate predicate = CharPredicate.is(C1);

        assertThat(predicate.stripLeadingFrom("aaabbbaaa")).isEqualTo("bbbaaa");
        assertThat(predicate.stripLeadingFrom("bbbaaabbb")).isEqualTo("bbbaaabbb");
        assertThat(predicate.stripLeadingFrom("aaaaaaaaa")).isEqualTo("");
    }

    @Test
    void testStripLeadingFromThrowsNPE() {
        CharPredicate predicate = CharPredicate.is(C1);

        assertThatNullPointerException()
            .isThrownBy(() -> predicate.stripLeadingFrom(null));
    }

    @Test
    void testStripTrailingFrom() {
        CharPredicate predicate = CharPredicate.is(C1);

        assertThat(predicate.stripTrailingFrom("aaabbbaaa")).isEqualTo("aaabbb");
        assertThat(predicate.stripTrailingFrom("bbbaaabbb")).isEqualTo("bbbaaabbb");
        assertThat(predicate.stripTrailingFrom("aaaaaaaaa")).isEqualTo("");
    }

    @Test
    void testStripTrailingFromThrowsNPE() {
        CharPredicate predicate = CharPredicate.is(C1);

        assertThatNullPointerException()
            .isThrownBy(() -> predicate.stripTrailingFrom(null));
    }


    @Test
    void testAnyOr() {
        CharPredicate predicate = CharPredicate.any();

        assertThat(predicate.or(CharPredicate.is(C1))).isEqualTo(predicate);
    }

    @Test
    void testAnyAnd() {
        CharPredicate predicate = CharPredicate.any();
        CharPredicate other = CharPredicate.is(C1);

        assertThat(predicate.and(other)).isEqualTo(other);
    }

    @Test
    void testAnyNot() {
        CharPredicate predicate = CharPredicate.any();

        assertThat(predicate.negate()).isEqualTo(CharPredicate.none());
    }

    @Test
    void testNoneOr() {
        CharPredicate predicate = CharPredicate.none();
        CharPredicate other = CharPredicate.is(C1);

        assertThat(predicate.or(other)).isEqualTo(other);
    }

    @Test
    void testNoneAnd() {
        CharPredicate predicate = CharPredicate.none();

        assertThat(predicate.and(CharPredicate.is(C1))).isEqualTo(predicate);
    }

    @Test
    void testNoneNot() {
        CharPredicate predicate = CharPredicate.none();

        assertThat(predicate.negate()).isEqualTo(CharPredicate.any());
    }

    @Test
    void testNotNot() {
        CharPredicate predicate = CharPredicate.range(C1, C2);

        assertThat(predicate.negate().negate()).isEqualTo(predicate);
    }

    @Test
    void testForWithCharPredicate() {
        CharPredicate originalPredicate = CharPredicate.any();
        CharPredicate predicate = CharPredicate.from(originalPredicate);

        assertThat(predicate).isEqualTo(originalPredicate);
    }

    private void testAllChars(CharPredicate actual, Predicate<Character> expected, String name) {
        for (char c = Character.MIN_VALUE; c < Character.MAX_VALUE; c++) {
            if (actual.matches(c) != expected.test(c)) {
                fail(name + " should not match " + c);
            }
        }
    }

}
