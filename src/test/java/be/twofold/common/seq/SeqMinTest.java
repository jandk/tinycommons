package be.twofold.common.seq;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

public class SeqMinTest {

    @Test
    void testMin() {
        assertThat(Seq.of(2, 1, 4, 3).min()).isEqualTo(1);
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> Seq.empty().min());
    }

    @Test
    void testMinWithPredicate() {
        assertThat(Seq.of(2, 1, 4, 3).min(Comparator.reverseOrder())).isEqualTo(4);
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> Seq.<Integer>empty().min(Comparator.reverseOrder()));
    }

    @Test
    void testMinOptional() {
        assertThat(Seq.of(2, 1, 4, 3).minOptional()).isEqualTo(Optional.of(1));
        assertThat(Seq.empty().minOptional()).isEqualTo(Optional.empty());
    }

    @Test
    void testMinOptionalWithPredicate() {
        assertThat(Seq.of(2, 1, 4, 3).minOptional(Comparator.reverseOrder())).isEqualTo(Optional.of(4));
        assertThat(Seq.<Integer>empty().minOptional(Comparator.reverseOrder())).isEqualTo(Optional.empty());
    }

}
