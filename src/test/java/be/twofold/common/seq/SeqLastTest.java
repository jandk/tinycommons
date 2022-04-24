package be.twofold.common.seq;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

public class SeqLastTest {

    @Test
    void testLast() {
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> Seq.empty().last());
        assertThat(Seq.of("one").last()).isEqualTo("one");
        assertThat(Seq.of("one", "two").last()).isEqualTo("two");
    }

    @Test
    void testLastWithPredicate() {
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> Seq.empty().last(x -> true));
        assertThat(Seq.of("one", "two").last(x -> x.equals("one"))).isEqualTo("one");
        assertThat(Seq.of("one", "two", "three").last(x -> x.length() == 3)).isEqualTo("two");
    }

    @Test
    void testLastOptional() {
        assertThat(Seq.empty().lastOptional()).isEmpty();
        assertThat(Seq.of("one").lastOptional()).hasValue("one");
        assertThat(Seq.of("one", "two").lastOptional()).hasValue("two");
    }

    @Test
    void testLastOptionalWithPredicate() {
        assertThat(Seq.empty().lastOptional(x -> true)).isEmpty();
        assertThat(Seq.of("one", "two").lastOptional(x -> x.equals("one"))).hasValue("one");
        assertThat(Seq.of("one", "two", "three").lastOptional(x -> x.length() == 3)).hasValue("two");
    }

}
