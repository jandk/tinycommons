package be.twofold.common.seq;

import org.junit.jupiter.api.*;

import java.util.*;

import static be.twofold.common.seq.Sequences.*;
import static org.assertj.core.api.Assertions.*;

class SeqMinTest {

    @Test
    void testMin() {
        assertThat(Strings.min()).isEqualTo("five");
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(Empty::min);
    }

    @Test
    void testMinWithComparator() {
        assertThat(Strings.min(Comparator.reverseOrder())).isEqualTo("two");
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> EmptyInteger.min(Comparator.reverseOrder()));
    }

    @Test
    void testMinOptional() {
        assertThat(Strings.minOptional()).hasValue("five");
        assertThat(Empty.minOptional()).isEmpty();
    }

    @Test
    void testMinOptionalWithComparator() {
        assertThat(Strings.minOptional(Comparator.reverseOrder())).hasValue("two");
        assertThat(EmptyInteger.minOptional(Comparator.reverseOrder())).isEmpty();
    }

}
