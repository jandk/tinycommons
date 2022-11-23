package be.twofold.common.seq;

import org.junit.jupiter.api.*;

import static be.twofold.common.seq.Sequences.*;
import static org.assertj.core.api.Assertions.*;

class SeqDropWhileTest {

    @Test
    void testDropWhile() {
        assertThat(Strings.dropWhile(s -> s.length() == 3))
            .containsExactly("three", "four", "five");
    }

    @Test
    void testDropWhileThrows() {
        assertThatNullPointerException()
            .isThrownBy(() -> Strings.dropWhile(null));
    }

}
