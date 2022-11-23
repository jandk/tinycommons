package be.twofold.common.seq;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

class SeqDistinctTest {

    @Test
    void testDistinctOnEmptySeq() {
        assertThat(Seq.of().distinct()).isEmpty();
    }

    @Test
    void testDistinctOnSeqWithOneElement() {
        assertThat(Seq.of(1).distinct()).containsExactly(1);
    }

    @Test
    void testDistinctOnSeqWithTwoDistinctElements() {
        assertThat(Seq.of(1, 2).distinct()).containsExactly(1, 2);
    }

    @Test
    void testDistinctOnSeqWithTwoEqualElements() {
        assertThat(Seq.of(1, 1).distinct()).containsExactly(1);
    }

}
