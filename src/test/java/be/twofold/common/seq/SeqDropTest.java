package be.twofold.common.seq;

import org.junit.jupiter.api.*;

import static be.twofold.common.seq.Sequences.*;
import static org.assertj.core.api.Assertions.*;

class SeqDropTest {

    @Test
    void testDropOnEmptySeq() {
        assertThat(Seq.of().drop(1)).isEmpty();
    }

    @Test
    void testDropZeroElements() {
        assertThat(Strings.drop(0)).containsExactlyElementsOf(Strings);
    }

    @Test
    void testDropThreeElements() {
        assertThat(Strings.drop(3)).containsExactly("four", "five");
    }

    @Test
    void testDropAllElements() {
        assertThat(Strings.drop(5)).isEmpty();
    }

    @Test
    void testDropMoreElementsThanAvailable() {
        assertThat(Strings.drop(6)).isEmpty();
    }

    @Test
    void testDropThrows() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> Strings.drop(-1));
    }

}
