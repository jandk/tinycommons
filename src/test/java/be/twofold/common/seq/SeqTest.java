package be.twofold.common.seq;

import org.junit.jupiter.api.*;

import java.util.*;
import java.util.stream.*;

import static org.assertj.core.api.Assertions.*;

class SeqTest {

    @Test
    void testOf() {
        assertThatNullPointerException()
            .isThrownBy(() -> Seq.of((Object[]) null));
    }

    @Test
    void testSeqIteratorCanOnlyIterateOnce() {
        Seq<?> seq = Seq.seq(Collections.emptyIterator());
        assertThat(seq.iterator()).isNotNull();
        assertThatIllegalStateException().isThrownBy(seq::iterator);
    }

    @Test
    void testDistinct() {
        assertThat(Seq.empty().distinct().count()).isEqualTo(0);
        assertThat(Seq.of(1, 2, 1, 3, 4, 3, 5, 5, 5).distinct().count()).isEqualTo(5);
    }

    @Test
    void testFilter() {
        assertThatNullPointerException()
            .isThrownBy(() -> Seq.empty().filter(null));

        assertThat(Seq.empty().filter(o -> true).count()).isEqualTo(0);
        assertThat(Seq.empty().filter(o -> false).count()).isEqualTo(0);

        Seq<String> seq = Seq.of("aaa", null, "bbb", null, "ccc");
        List<String> filtered = seq.filter(s -> s == null || s.startsWith("b")).toList();
        assertThat(filtered).containsExactly(null, "bbb", null);
    }

    @Test
    void testFilterIndexed() {
        List<Integer> list = aSequence()
            .filterIndexed((index, __) -> index % 2 == 0)
            .take(5).toList();

        assertThat(list).containsExactly(0, 4, 8, 12, 16);
    }

    @Test
    void testFlatMap() {
        Seq<Integer> seq = Seq.of(1, 2, 3);
        assertThat(seq.flatMap(i -> Seq.of(i, i + 1)).toList())
            .containsExactly(1, 2, 2, 3, 3, 4);
    }

    @Test
    void testFlatMapIndexed() {
        List<Integer> list = aSequence()
            .flatMapIndexed((index, i) -> Seq.of(index, i + 1))
            .take(8).toList();

        assertThat(list).containsExactly(0, 1, 1, 3, 2, 5, 3, 7);
    }

    @Test
    void testOnlyOnce() {
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
    void testTake() {
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
