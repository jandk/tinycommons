package be.twofold.common.seq;

import org.junit.jupiter.api.*;

import java.util.*;

import static be.twofold.common.seq.Sequences.*;
import static org.assertj.core.api.Assertions.*;

class SeqFirstTest {

    @Test
    void testFirst() {
        assertThat(Strings.first()).isEqualTo("one");
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(Empty::first);
    }

    @Test
    void testFirstWithPredicate() {
        assertThat(Strings.first(s -> s.startsWith("t"))).isEqualTo("two");
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> Empty.first(s -> s.startsWith("t")));
    }

    @Test
    void testFirstOptional() {
        assertThat(Strings.firstOptional()).hasValue("one");
        assertThat(Empty.firstOptional()).isEmpty();
    }

    @Test
    void testFirstOptionalWithPredicate() {
        assertThat(Strings.firstOptional(s -> s.startsWith("t"))).hasValue("two");
        assertThat(Empty.firstOptional(s -> s.startsWith("t"))).isEmpty();
    }

}
