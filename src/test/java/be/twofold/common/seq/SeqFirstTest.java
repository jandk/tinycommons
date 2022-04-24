package be.twofold.common.seq;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

public class SeqFirstTest {

    @Test
    void testFirst() {
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> Seq.empty().first());
        assertThat(Seq.of("one").first()).isEqualTo("one");
        assertThat(Seq.of("one", "two").first()).isEqualTo("one");
    }

    @Test
    void testFirstWithPredicate() {
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> Seq.empty().first(x -> true));
        assertThat(Seq.of("one", "two").first(x -> x.equals("two"))).isEqualTo("two");
        assertThat(Seq.of("one", "two", "three").first(x -> x.length() == 5)).isEqualTo("three");
    }

    @Test
    void testFirstOptional() {
        assertThat(Seq.empty().firstOptional()).isEmpty();
        assertThat(Seq.of("one").firstOptional()).hasValue("one");
        assertThat(Seq.of("one", "two").firstOptional()).hasValue("one");
    }

    @Test
    void testFirstOptionalWithPredicate() {
        assertThat(Seq.empty().firstOptional(x -> true)).isEmpty();
        assertThat(Seq.of("one", "two").firstOptional(x -> x.equals("two"))).hasValue("two");
        assertThat(Seq.of("one", "two", "three").firstOptional(x -> x.length() == 5)).hasValue("three");
    }

}
