package be.twofold.common.seq;

import org.junit.jupiter.api.*;

import java.util.*;

import static be.twofold.common.seq.Sequences.*;
import static org.assertj.core.api.Assertions.*;

class SeqSingleTest {

    @Test
    void testSingle() {
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> Strings.take(0).single());
        assertThat(Strings.take(1).single()).isEqualTo("one");
        assertThatIllegalArgumentException()
            .isThrownBy(() -> Strings.take(2).single());
    }

    @Test
    void testSingleWithPredicate() {
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> Strings.single(t -> t.startsWith("u")));
        assertThat(Strings.single(t -> t.startsWith("o"))).isEqualTo("one");
        assertThatIllegalArgumentException()
            .isThrownBy(() -> Strings.single(t -> t.startsWith("t")));
    }

    @Test
    void testSingleOptional() {
        assertThat(Strings.take(0).singleOptional()).isEmpty();
        assertThat(Strings.take(1).singleOptional()).hasValue("one");
        assertThat(Strings.take(2).singleOptional()).isEmpty();
    }

    @Test
    void testSingleOptionalWithPredicate() {
        assertThat(Strings.singleOptional(t -> t.startsWith("u"))).isEmpty();
        assertThat(Strings.singleOptional(t -> t.startsWith("o"))).hasValue("one");
        assertThat(Strings.singleOptional(t -> t.startsWith("t"))).isEmpty();
    }

}
