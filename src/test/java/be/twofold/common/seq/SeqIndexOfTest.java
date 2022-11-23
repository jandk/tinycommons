package be.twofold.common.seq;

import org.junit.jupiter.api.*;

import java.util.function.*;

import static be.twofold.common.seq.Sequences.*;
import static org.assertj.core.api.Assertions.*;

class SeqIndexOfTest {

    @Test
    void testIndexOf() {
        Seq<String> seq = Seq.of("one", "two", "one", "two");
        assertThat(seq.indexOf("two")).isEqualTo(1);
        assertThat(seq.indexOf("three")).isEqualTo(-1);
    }

    @Test
    void testIndexOfWithPredicate() {
        assertThat(Strings.indexOf(s -> s.startsWith("t"))).isEqualTo(1);
        assertThat(Strings.indexOf(s -> s.startsWith("u"))).isEqualTo(-1);
        assertThatNullPointerException()
            .isThrownBy(() -> Strings.indexOf((Predicate<String>) null));
    }

}
