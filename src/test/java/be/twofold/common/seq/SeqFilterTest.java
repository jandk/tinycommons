package be.twofold.common.seq;

import org.junit.jupiter.api.*;

import static be.twofold.common.seq.Sequences.*;
import static org.assertj.core.api.Assertions.*;

class SeqFilterTest {

    @Test
    void testFilter() {
        assertThat(Strings.filter(s -> s.startsWith("t")))
            .containsExactly("two", "three");
    }

    @Test
    void testFilterThrows() {
        assertThatNullPointerException()
            .isThrownBy(() -> Seq.of().filter(null));
    }

    @Test
    void testFilterIndexed() {
        assertThat(Strings.filterIndexed((i, s) -> i % 2 == 0))
            .containsExactly("one", "three", "five");
    }

    @Test
    void testFilterIndexedThrows() {
        assertThatNullPointerException()
            .isThrownBy(() -> Strings.filterIndexed(null));
    }

    @Test
    void testFilterIsInstance() {
        Seq<Number> seq = Seq.of(1, 2.0, 3L, 4, 5.0, 6L);
        assertThat(seq.filterIsInstance(Double.class)).containsExactly(2.0, 5.0);
        assertThat(seq.filterIsInstance(String.class)).isEmpty();
    }

    @Test
    void testFilterIsInstanceThrows() {
        assertThatNullPointerException()
            .isThrownBy(() -> Strings.filterIsInstance(null));
    }

    @Test
    void testFilterNot() {
        assertThat(Strings.filterNot(s -> s.startsWith("t")))
            .containsExactly("one", "four", "five");
    }

    @Test
    void testFilterNotThrows() {
        assertThatNullPointerException()
            .isThrownBy(() -> Strings.filterNot(null));
    }

    @Test
    void testFilterNotNull() {
        assertThat(Seq.of(null, "one", null, "two", null, "three", null).filterNotNull())
            .containsExactly("one", "two", "three");
    }

}
