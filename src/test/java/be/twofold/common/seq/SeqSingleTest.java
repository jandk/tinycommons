package be.twofold.common.seq;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

public class SeqSingleTest {

    @Test
    void testSingle() {
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> Seq.empty().single());
        assertThat(Seq.of("one").single()).isEqualTo("one");
        assertThatIllegalArgumentException()
            .isThrownBy(() -> Seq.of("one", "two").single());
    }

    @Test
    void testSingleWithPredicate() {
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> Seq.empty().single(x -> true));
        assertThat(Seq.of("one", "two").single(x -> x.equals("two"))).isEqualTo("two");
        assertThatIllegalArgumentException()
            .isThrownBy(() -> Seq.of("one", "two", "three").single(x -> x.length() == 3));
    }

    @Test
    void testSingleOptional() {
        assertThat(Seq.empty().singleOptional()).isEmpty();
        assertThat(Seq.of("one").singleOptional()).hasValue("one");
        assertThat(Seq.of("one", "two").singleOptional()).isEmpty();
    }

    @Test
    void testSingleOptionalWithPredicate() {
        assertThat(Seq.empty().singleOptional(x -> true)).isEmpty();
        assertThat(Seq.of("one", "two").singleOptional(x -> x.equals("two"))).hasValue("two");
        assertThat(Seq.of("one", "two", "three").singleOptional(x -> x.length() == 3)).isEmpty();
    }

}
