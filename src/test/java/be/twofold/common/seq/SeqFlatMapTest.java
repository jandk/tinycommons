package be.twofold.common.seq;

import org.junit.jupiter.api.*;

import static be.twofold.common.seq.Sequences.*;
import static org.assertj.core.api.Assertions.*;

class SeqFlatMapTest {

    @Test
    void testFlatMap() {
        assertThat(Strings.flatMap(s -> Seq.of(s, s.toUpperCase())))
            .containsExactly("one", "ONE", "two", "TWO", "three", "THREE", "four", "FOUR", "five", "FIVE");
    }

    @Test
    void testFlatMapThrows() {
        assertThatNullPointerException()
            .isThrownBy(() -> Strings.flatMap(null));
    }

    @Test
    void testFlatMapIndexed() {
        assertThat(Strings.flatMapIndexed((i, s) -> Seq.of(String.valueOf(i + 1), s)))
            .containsExactly("1", "one", "2", "two", "3", "three", "4", "four", "5", "five");
    }

    @Test
    void testFlatMapIndexedThrows() {
        assertThatNullPointerException()
            .isThrownBy(() -> Strings.flatMapIndexed(null));
    }

}
