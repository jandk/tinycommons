package be.twofold.common.seq;

import org.junit.jupiter.api.*;

import static be.twofold.common.seq.Sequences.*;
import static org.assertj.core.api.Assertions.*;

class SeqAnyTest {

    @Test
    void testAny() {
        assertThat(Empty.any()).isFalse();
        assertThat(Strings.any()).isTrue();
    }

    @Test
    void testAnyWithPredicate() {
        assertThat(Empty.any(s -> s.startsWith("t"))).isFalse();
        assertThat(Strings.any(s -> s.startsWith("t"))).isTrue();
        assertThat(Strings.any(s -> s.startsWith("u"))).isFalse();
    }

    @Test
    void testAnyWithPredicateThrows() {
        assertThatNullPointerException()
            .isThrownBy(() -> Strings.any(null));
    }

}
