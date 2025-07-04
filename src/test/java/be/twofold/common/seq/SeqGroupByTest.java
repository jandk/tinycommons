package be.twofold.common.seq;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static be.twofold.common.seq.Sequences.Empty;
import static be.twofold.common.seq.Sequences.Strings;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class SeqGroupByTest {

    @Test
    void testGroupByWithKeyMapper() {
        assertThatNullPointerException().isThrownBy(() -> Strings.groupBy(null));

        assertThat(Empty.groupBy(String::length)).isEmpty();
        assertThat(Strings.groupBy(String::length)).containsOnly(
            Map.entry(3, List.of("one", "two")),
            Map.entry(4, List.of("four", "five")),
            Map.entry(5, List.of("three"))
        );
    }

    @Test
    void testGroupByWithKeyMapperAndValueMapper() {
        assertThatNullPointerException().isThrownBy(() -> Strings.groupBy(null, Function.identity()));
        assertThatNullPointerException().isThrownBy(() -> Strings.groupBy(Function.identity(), null));

        assertThat(Empty.groupBy(String::length, s -> s.charAt(0))).isEmpty();
        assertThat(Strings.groupBy(String::length, s -> s.charAt(0))).containsOnly(
            Map.entry(3, List.of('o', 't')),
            Map.entry(4, List.of('f', 'f')),
            Map.entry(5, List.of('t'))
        );
    }

}
