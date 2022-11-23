package be.twofold.common.seq;

import org.junit.jupiter.api.*;

import java.util.*;

import static be.twofold.common.seq.Sequences.*;
import static org.assertj.core.api.Assertions.*;

class SeqReduceTest {

    @Test
    void testReduce() {
        assertThat(Strings.reduce(String::concat)).isEqualTo("onetwothreefourfive");
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> Empty.reduce(String::concat));
        assertThatNullPointerException()
            .isThrownBy(() -> Strings.reduce(null));
    }

    @Test
    void testReduceOptional() {
        assertThat(Strings.reduceOptional(String::concat)).hasValue("onetwothreefourfive");
        assertThat(Empty.reduceOptional(String::concat)).isEmpty();
        assertThatNullPointerException()
            .isThrownBy(() -> Strings.reduceOptional(null));
    }

}
