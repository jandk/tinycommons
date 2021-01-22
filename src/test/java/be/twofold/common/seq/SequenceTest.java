package be.twofold.common.seq;

import org.junit.jupiter.api.Test;

import java.util.Enumeration;
import java.util.List;
import java.util.Vector;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class SequenceTest {

    @Test
    public void testConstructedFromEnumerator() {
        Vector<String> strings = new Vector<>();
        strings.add("one");
        strings.add("two");
        strings.add("three");

        Enumeration<String> enumeration = strings.elements();
        assertThat(Sequence.sequence(enumeration).toList())
                .containsExactly("one", "two", "three");
    }

    @Test
    public void testFilter() {
        assertThatNullPointerException()
                .isThrownBy(() -> Sequence.empty().filter(null));

        assertThat(Sequence.empty().filter(o -> true).count()).isEqualTo(0);
        assertThat(Sequence.empty().filter(o -> false).count()).isEqualTo(0);

        Sequence<String> sequence = Sequence.of("aaa", null, "bbb", null, "ccc");
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
        Sequence<Integer> sequence = Sequence.of(1, 2, 3);
        sequence.toList();
        sequence.toList();

        Sequence<Integer> sequenceOnce = sequence.once();
        sequenceOnce.toList();

        assertThatIllegalStateException()
                .isThrownBy(sequenceOnce::toList);

        Sequence<Object> once = Sequence.empty().once();
        assertThat(once.once())
                .isSameAs(once);
    }

    @Test
    public void testTake() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Sequence.empty().take(-1));

        assertThat(Sequence.empty().take(0))
                .isEqualTo(Sequence.empty());

        assertThat(aSequence().take(5).toList())
                .containsExactly(0, 2, 4, 6, 8);
    }

    private Sequence<Integer> aSequence() {
        // For now generate via stream as we don't have our own generate methods yet
        return Sequence.sequence(Stream.iterate(0, i -> i + 2));
    }

}
