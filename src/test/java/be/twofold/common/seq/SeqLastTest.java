package be.twofold.common.seq;

import org.junit.jupiter.api.*;

import java.util.*;

import static be.twofold.common.seq.Sequences.*;
import static org.assertj.core.api.Assertions.*;

class SeqLastTest {

    @Test
    void testLast() {
        assertThat(Strings.last()).isEqualTo("five");
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(Empty::last);
    }

    @Test
    void testLastWithPredicate() {
        assertThat(Strings.last(s -> s.startsWith("t"))).isEqualTo("three");
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> Empty.last(s -> s.startsWith("t")));
    }

    @Test
    void testLastOptional() {
        assertThat(Strings.lastOptional()).hasValue("five");
        assertThat(Empty.lastOptional()).isEmpty();
    }

    @Test
    void testLastOptionalWithPredicate() {
        assertThat(Strings.lastOptional(s -> s.startsWith("t"))).hasValue("three");
        assertThat(Empty.lastOptional(s -> s.startsWith("t"))).isEmpty();
    }

}
