package be.twofold.common.seq;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

class SeqTest {

    @Test
    void testOf() {
        assertThatNullPointerException()
            .isThrownBy(() -> Seq.of((Object[]) null));
    }

    @Test
    void testSeqIteratorCanOnlyIterateOnce() {
        Seq<?> seq = Seq.seq(Collections.emptyIterator());
        assertThat(seq.iterator()).isNotNull();
        assertThatIllegalStateException().isThrownBy(seq::iterator);
    }

}
