package be.twofold.common.seq;

import org.junit.jupiter.api.*;

import static be.twofold.common.seq.Sequences.*;
import static org.assertj.core.api.Assertions.*;

class SeqContainsTest {

    @Test
    void testContains() {
        assertThat(Strings.contains("three")).isTrue();
        assertThat(Strings.contains("six")).isFalse();
        assertThat(Strings.contains(null)).isFalse();
    }

}
