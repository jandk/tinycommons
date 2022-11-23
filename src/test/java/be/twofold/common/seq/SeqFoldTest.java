package be.twofold.common.seq;

import org.junit.jupiter.api.*;

import static be.twofold.common.seq.Sequences.*;
import static org.assertj.core.api.Assertions.*;

class SeqFoldTest {

    @Test
    void testFold() {
        assertThat(Strings.fold("---", String::concat)).isEqualTo("---onetwothreefourfive");
        assertThat(Empty.fold("---", String::concat)).isEqualTo("---");
    }

}
