package be.twofold.common.seq;

import org.junit.jupiter.api.*;

import static be.twofold.common.seq.Sequences.*;
import static org.assertj.core.api.Assertions.*;

class SeqOnceTest {

    @Test
    void testOnce() {
        Seq<String> seq = Strings;
        assertThatNoException().isThrownBy(seq::toList);
        assertThatNoException().isThrownBy(seq::toList);

        Seq<String> once = seq.once();
        assertThatNoException().isThrownBy(once::toList);
        assertThatIllegalStateException().isThrownBy(once::toList);
    }

}
