package be.twofold.common.seq;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

public class SeqFoldTest {

    @Test
    void testFold() {
        assertThat(Seq.of(1, 2, 3, 4, 5).fold(0, Integer::sum)).isEqualTo(15);
        assertThat(Seq.<Integer>empty().fold(0, Integer::sum)).isEqualTo(0);
    }

}
