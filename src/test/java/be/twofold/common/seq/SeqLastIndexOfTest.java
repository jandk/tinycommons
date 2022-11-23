package be.twofold.common.seq;

import org.junit.jupiter.api.*;

import java.util.function.*;

import static be.twofold.common.seq.Sequences.*;
import static org.assertj.core.api.Assertions.*;

class SeqLastIndexOfTest {

    @Test
    void testLastIndexOf() {
        Seq<String> seq = Seq.of("one", "two", "one", "two");
        assertThat(seq.lastIndexOf("one")).isEqualTo(2);
        assertThat(seq.lastIndexOf("three")).isEqualTo(-1);
    }

    @Test
    void testLastIndexOfWithPredicate() {
        assertThat(Strings.lastIndexOf(s -> s.startsWith("t"))).isEqualTo(2);
        assertThat(Strings.lastIndexOf(s -> s.startsWith("u"))).isEqualTo(-1);
        assertThatNullPointerException()
            .isThrownBy(() -> Strings.lastIndexOf((Predicate<String>) null));
    }

}
