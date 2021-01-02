package be.twofold.common.text;

import org.junit.jupiter.api.*;

import java.util.function.*;

import static org.assertj.core.api.Assertions.*;

@SuppressWarnings("ConstantConditions")
public class CharPredicateTest {
    private static final char C1 = 'a';
    private static final char C2 = 'z';


    @Test
    public void testNegate() {
        CharPredicate predicate = CharPredicate.is(C1);
        testAllChars(predicate.negate(), c -> c != C1, "negate");
    }

    @Test
    @SuppressWarnings({"deprecation", "FunctionalExpressionCanBeFolded"})
    public void testTest() {
        CharPredicate predicate = CharPredicate.is(C1);
        testAllChars(predicate, predicate::test, "test");
    }


    @Test
    public void testAny() {
        CharPredicate predicate = CharPredicate.any();

        testAllChars(predicate, c -> true, "any");
        assertThat(CharPredicate.any()).isEqualTo(predicate);
    }

    @Test
    public void testNone() {
        CharPredicate predicate = CharPredicate.none();

        testAllChars(predicate, c -> false, "none");
        assertThat(CharPredicate.none()).isEqualTo(predicate);
    }

    @Test
    public void testIs() {
        CharPredicate predicate = CharPredicate.is(C1);

        testAllChars(predicate, c -> c == C1, "is");
    }

    @Test
    public void testRange() {
        CharPredicate predicate = CharPredicate.range(C1, C2);

        testAllChars(predicate, c -> c >= C1 && c <= C2, "range");
    }

    @Test
    public void testAmong() {
        CharPredicate predicate = CharPredicate.among("abc");

        testAllChars(predicate, c -> c == 'a' || c == 'b' || c == 'c', "among");
    }

    @Test
    public void testForPredicate() {
        CharPredicate predicate = CharPredicate.from(character -> character == 'a');
        testAllChars(predicate, character -> character == 'a', "from");
    }


    @Test
    public void testAnd() {
        CharPredicate p1 = CharPredicate.range('a', 'n');
        CharPredicate p2 = CharPredicate.range('m', 'z');
        CharPredicate predicate = p1.and(p2);

        testAllChars(predicate, c -> c == 'm' || c == 'n', "and");
    }

    @Test
    public void testOr() {
        CharPredicate p1 = CharPredicate.range('a', 'n');
        CharPredicate p2 = CharPredicate.range('m', 'z');
        CharPredicate predicate = p1.or(p2);

        testAllChars(predicate, c -> c >= 'a' && c <= 'z', "or");
    }

    @Test
    public void testNot() {
        CharPredicate predicate = CharPredicate.range(C1, C2).not();

        testAllChars(predicate, c -> c < C1 || c > C2, "not");
    }


    @Test
    public void testStripFrom() {
        CharPredicate predicate = CharPredicate.is(C1);

        assertThat(predicate.stripFrom("aaabbbaaa")).isEqualTo("bbb");
        assertThat(predicate.stripFrom("bbbaaabbb")).isEqualTo("bbbaaabbb");
        assertThat(predicate.stripFrom("aaaaaaaaa")).isEqualTo("");
    }

    @Test
    public void testStripFromThrowsNPE() {
        CharPredicate predicate = CharPredicate.is(C1);

        assertThatNullPointerException()
            .isThrownBy(() -> predicate.stripFrom(null));
    }

    @Test
    public void testStripLeadingFrom() {
        CharPredicate predicate = CharPredicate.is(C1);

        assertThat(predicate.stripLeadingFrom("aaabbbaaa")).isEqualTo("bbbaaa");
        assertThat(predicate.stripLeadingFrom("bbbaaabbb")).isEqualTo("bbbaaabbb");
        assertThat(predicate.stripLeadingFrom("aaaaaaaaa")).isEqualTo("");
    }

    @Test
    public void testStripLeadingFromThrowsNPE() {
        CharPredicate predicate = CharPredicate.is(C1);

        assertThatNullPointerException()
            .isThrownBy(() -> predicate.stripLeadingFrom(null));
    }

    @Test
    public void testStripTrailingFrom() {
        CharPredicate predicate = CharPredicate.is(C1);

        assertThat(predicate.stripTrailingFrom("aaabbbaaa")).isEqualTo("aaabbb");
        assertThat(predicate.stripTrailingFrom("bbbaaabbb")).isEqualTo("bbbaaabbb");
        assertThat(predicate.stripTrailingFrom("aaaaaaaaa")).isEqualTo("");
    }

    @Test
    public void testStripTrailingFromThrowsNPE() {
        CharPredicate predicate = CharPredicate.is(C1);

        assertThatNullPointerException()
            .isThrownBy(() -> predicate.stripTrailingFrom(null));
    }


    @Test
    public void testAnyOr() {
        CharPredicate predicate = CharPredicate.any();

        assertThat(predicate.or(CharPredicate.is(C1))).isEqualTo(predicate);
    }

    @Test
    public void testAnyAnd() {
        CharPredicate predicate = CharPredicate.any();
        CharPredicate other = CharPredicate.is(C1);

        assertThat(predicate.and(other)).isEqualTo(other);
    }

    @Test
    public void testAnyNot() {
        CharPredicate predicate = CharPredicate.any();

        assertThat(predicate.not()).isEqualTo(CharPredicate.none());
    }

    @Test
    public void testNoneOr() {
        CharPredicate predicate = CharPredicate.none();
        CharPredicate other = CharPredicate.is(C1);

        assertThat(predicate.or(other)).isEqualTo(other);
    }

    @Test
    public void testNoneAnd() {
        CharPredicate predicate = CharPredicate.none();

        assertThat(predicate.and(CharPredicate.is(C1))).isEqualTo(predicate);
    }

    @Test
    public void testNoneNot() {
        CharPredicate predicate = CharPredicate.none();

        assertThat(predicate.not()).isEqualTo(CharPredicate.any());
    }

    @Test
    public void testNotNot() {
        CharPredicate predicate = CharPredicate.range(C1, C2);

        assertThat(predicate.not().not()).isEqualTo(predicate);
    }

    @Test
    public void testForWithCharPredicate() {
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
