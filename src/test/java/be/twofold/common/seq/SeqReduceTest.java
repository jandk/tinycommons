package be.twofold.common.seq;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

public class SeqReduceTest {

    @Test
    void testReduce() {
        assertThat(Seq.of(1, 2, 3, 4, 5).reduce(Integer::sum)).isEqualTo(15);
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> Seq.<Integer>empty().reduce(Integer::sum));
    }

    @Test
    void testReduceOptional() {
        assertThat(Seq.of(1, 2, 3, 4, 5).reduceOptional(Integer::sum)).hasValue(15);
        assertThat(Seq.<Integer>empty().reduceOptional(Integer::sum)).isEqualTo(Optional.empty());
    }

}
