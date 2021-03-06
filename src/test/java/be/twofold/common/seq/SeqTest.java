package be.twofold.common.seq;

import org.junit.jupiter.api.*;

import java.util.*;
import java.util.stream.*;

import static org.assertj.core.api.Assertions.*;

public class SeqTest {

    @Test
    public void testConstructedFromEnumerator() {
        Vector<String> strings = new Vector<>();
        strings.add("one");
        strings.add("two");
        strings.add("three");

        Enumeration<String> enumeration = strings.elements();
        assertThat(Seq.seq(enumeration).toList())
                .containsExactly("one", "two", "three");
    }

    @Test
    public void testFilter() {
        assertThatNullPointerException()
            .isThrownBy(() -> Seq.empty().filter(null));

        assertThat(Seq.empty().filter(o -> true).count()).isEqualTo(0);
        assertThat(Seq.empty().filter(o -> false).count()).isEqualTo(0);

        Seq<String> seq = Seq.of("aaa", null, "bbb", null, "ccc");
        List<String> filtered = seq.filter(s -> s == null || s.startsWith("b")).toList();
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
        Seq<Integer> seq = Seq.of(1, 2, 3);
        seq.toList();
        seq.toList();

        Seq<Integer> seqOnce = seq.once();
        seqOnce.toList();

        assertThatIllegalStateException()
            .isThrownBy(seqOnce::toList);

        Seq<Object> once = Seq.empty().once();
        assertThat(once.once())
            .isSameAs(once);
    }

    @Test
    public void testTake() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> Seq.empty().take(-1));

        assertThat(Seq.empty().take(0))
            .isEqualTo(Seq.empty());

        assertThat(aSequence().take(5).toList())
            .containsExactly(0, 2, 4, 6, 8);
    }

    private Seq<Integer> aSequence() {
        // For now generate via stream as we don't have our own generate methods yet
        return Seq.seq(Stream.iterate(0, i -> i + 2));
    }

}
