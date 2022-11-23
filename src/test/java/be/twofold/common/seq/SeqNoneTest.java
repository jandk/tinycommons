package be.twofold.common.seq;

import org.junit.jupiter.api.*;

import static be.twofold.common.seq.Sequences.*;
import static org.assertj.core.api.Assertions.*;

class SeqNoneTest {

    @Test
    void testNone() {
        assertThat(Strings.none()).isFalse();
        assertThat(Empty.none()).isTrue();
    }

    @Test
    void testNoneWithPredicate() {
        assertThat(Strings.none(t -> t.startsWith("t"))).isFalse();
        assertThat(Strings.none(t -> t.startsWith("u"))).isTrue();
        assertThat(Empty.none(t -> t.startsWith("t"))).isTrue();
        assertThatNullPointerException()
            .isThrownBy(() -> Strings.none(null));
    }

}
