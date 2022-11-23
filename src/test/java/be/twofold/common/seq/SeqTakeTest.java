package be.twofold.common.seq;

import org.junit.jupiter.api.*;

import static be.twofold.common.seq.Sequences.*;
import static org.assertj.core.api.Assertions.*;

class SeqTakeTest {

    @Test
    void testTakeOnEmptySeq() {
        assertThat(Seq.of().take(1)).isEmpty();
    }

    @Test
    void testTakeZeroElements() {
        assertThat(Strings.take(0)).isEmpty();
    }

    @Test
    void testTakeThreeElements() {
        assertThat(Strings.take(3)).containsExactly("one", "two", "three");
    }

    @Test
    void testTakeAllElements() {
        assertThat(Strings.take(5)).containsExactlyElementsOf(Strings);
    }

    @Test
    void testTakeMoreElementsThanAvailable() {
        assertThat(Strings.take(6)).containsExactlyElementsOf(Strings);
    }

    @Test
    void testTakeThrows() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> Strings.take(-1));
    }

}
