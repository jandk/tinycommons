package be.twofold.common.seq;

import be.twofold.common.collect.*;
import org.junit.jupiter.api.*;

import java.util.function.*;

import static be.twofold.common.seq.Sequences.*;
import static org.assertj.core.api.Assertions.*;

class SeqGroupByTest {

    @Test
    void testGroupByWithKeyMapper() {
        assertThatNullPointerException().isThrownBy(() -> Strings.groupBy(null));

        assertThat(Empty.groupBy(String::length)).isEmpty();
        assertThat(Strings.groupBy(String::length)).containsOnly(
            ImmutableMap.entry(3, ImmutableList.of("one", "two")),
            ImmutableMap.entry(4, ImmutableList.of("four", "five")),
            ImmutableMap.entry(5, ImmutableList.of("three"))
        );
    }

    @Test
    void testGroupByWithKeyMapperAndValueMapper() {
        assertThatNullPointerException().isThrownBy(() -> Strings.groupBy(null, Function.identity()));
        assertThatNullPointerException().isThrownBy(() -> Strings.groupBy(Function.identity(), null));

        assertThat(Empty.groupBy(String::length, s -> s.charAt(0))).isEmpty();
        assertThat(Strings.groupBy(String::length, s -> s.charAt(0))).containsOnly(
            ImmutableMap.entry(3, ImmutableList.of('o', 't')),
            ImmutableMap.entry(4, ImmutableList.of('f', 'f')),
            ImmutableMap.entry(5, ImmutableList.of('t'))
        );
    }

}
