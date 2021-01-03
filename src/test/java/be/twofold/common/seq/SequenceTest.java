package be.twofold.common.seq;

import org.junit.jupiter.api.*;

import java.util.*;
import java.util.stream.*;

import static be.twofold.common.seq.Sequence.*;
import static org.assertj.core.api.Assertions.*;

public class SequenceTest {

    @Test
    public void testConstructedFromEnumerator() {
        Vector<String> strings = new Vector<>();
        strings.add("one");
        strings.add("two");
        strings.add("three");

        Enumeration<String> enumeration = strings.elements();
        assertThat(sequence(enumeration).toList())
            .containsExactly("one", "two", "three");
    }

    @Test
    public void testFilter() {
        assertThatNullPointerException()
            .isThrownBy(() -> emptySequence().filter(null));

        assertThat(emptySequence().filter(o -> true).count()).isEqualTo(0);
        assertThat(emptySequence().filter(o -> false).count()).isEqualTo(0);

        Sequence<String> sequence = sequenceOf("aaa", null, "bbb", null, "ccc");
        List<String> filtered = sequence.filter(s -> s == null || s.startsWith("b")).toList();
        assertThat(filtered).containsExactly(null, "bbb", null);
    }

    @Test
    public void testFilterIndexed() {
        List<Integer> list = aSequence()
            .filterIndexed((index, __) -> index % 2 == 0)
            .take(5).toList();

        assertThat(list).containsExactly(0, 4, 8, 12, 16);
    }

    @Test
    public void testOnlyOnce() {
        Sequence<Integer> sequence = sequenceOf(1, 2, 3);
        sequence.toList();
        sequence.toList();

        Sequence<Integer> sequenceOnce = sequence.onlyOnce();
        sequenceOnce.toList();

        assertThatIllegalStateException()
            .isThrownBy(sequenceOnce::toList);

        Sequence<Object> once = emptySequence().onlyOnce();
        assertThat(once.onlyOnce())
            .isSameAs(once);
    }

    @Test
    public void testTake() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> emptySequence().take(-1));

        assertThat(emptySequence().take(0))
            .isSameAs(emptySequence());

        assertThat(aSequence().take(5).toList())
            .containsExactly(0, 2, 4, 6, 8);
    }

    private Sequence<Integer> aSequence() {
        // For now generate via stream as we don't have our own generate methods yet
        return sequence(Stream.iterate(0, i -> i + 2));
    }

}
