package be.twofold.common.seq;

import org.junit.jupiter.api.*;

import java.util.*;

import static be.twofold.common.seq.Sequences.*;
import static org.assertj.core.api.Assertions.*;

class SeqMaxTest {

    @Test
    void testMax() {
        assertThat(Strings.max()).isEqualTo("two");
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(Empty::max);
    }

    @Test
    void testMaxWithComparator() {
        assertThat(Strings.max(Comparator.reverseOrder())).isEqualTo("five");
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> EmptyInteger.max(Comparator.reverseOrder()));
    }

    @Test
    void testMaxOptional() {
        assertThat(Strings.maxOptional()).hasValue("two");
        assertThat(Empty.maxOptional()).isEmpty();
    }

    @Test
    void testMaxOptionalWithComparator() {
        assertThat(Strings.maxOptional(Comparator.reverseOrder())).hasValue("five");
        assertThat(EmptyInteger.maxOptional(Comparator.reverseOrder())).isEmpty();
    }

}
