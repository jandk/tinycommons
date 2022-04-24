package be.twofold.common.seq;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

public class SeqMaxTest {

    @Test
    void testMax() {
        assertThat(Seq.of(2, 1, 4, 3).max()).isEqualTo(4);
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> Seq.empty().max());
    }

    @Test
    void testMaxWithPredicate() {
        assertThat(Seq.of(2, 1, 4, 3).max(Comparator.reverseOrder())).isEqualTo(1);
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> Seq.<Integer>empty().max(Comparator.reverseOrder()));
    }

    @Test
    void testMaxOptional() {
        assertThat(Seq.of(2, 1, 4, 3).maxOptional()).isEqualTo(Optional.of(4));
        assertThat(Seq.empty().maxOptional()).isEqualTo(Optional.empty());
    }

    @Test
    void testMaxOptionalWithPredicate() {
        assertThat(Seq.of(2, 1, 4, 3).maxOptional(Comparator.reverseOrder())).isEqualTo(Optional.of(1));
        assertThat(Seq.<Integer>empty().maxOptional(Comparator.reverseOrder())).isEqualTo(Optional.empty());
    }

}
