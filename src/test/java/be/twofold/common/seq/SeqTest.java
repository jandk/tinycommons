package be.twofold.common.seq;

import org.junit.jupiter.api.*;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static org.assertj.core.api.Assertions.*;

class SeqTest {

    private static final Seq<Integer> IntegerSeqEmpty = Seq.empty();
    private static final Seq<Double> DoubleSeqEmpty = Seq.empty();
    private static final Seq<Long> LongSeqEmpty = Seq.empty();
    private static final Seq<Integer> IntegerSeq = Seq.of(1, 2, 3, 4, 5);
    private static final Seq<Double> DoubleSeq = Seq.of(1.0, 2.0, 3.0, 4.0, 5.0);
    private static final Seq<Long> LongSeq = Seq.of(1L, 2L, 3L, 4L, 5L);

    // region Seq

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

        assertThat(IntegerSeq.filter(i -> i % 2 == 0))
            .containsExactly(2, 4);
    }

    @Test
    void testFilterIndexed() {
        List<Integer> list = aSequence()
            .filterIndexed((index, __) -> index % 2 == 0)
            .take(5).toList();

        assertThat(list).containsExactly(0, 4, 8, 12, 16);
    }

    @Test
    void testFilterInstanceOf() {
        Seq<Number> seq = Seq.of(1, 2.0, 3L, 4, 5.0, 6L);
        assertThat(seq.filterInstanceOf(Double.class)).containsExactly(2.0, 5.0);
        assertThat(seq.filterInstanceOf(String.class)).isEmpty();
        assertThatNullPointerException()
            .isThrownBy(() -> seq.filterInstanceOf(null));
    }

    @Test
    void testFilterNot() {
        assertThatNullPointerException()
            .isThrownBy(() -> Seq.empty().filterNot(null));

        assertThat(IntegerSeq.filterNot(i -> i % 2 == 0))
            .containsExactly(1, 3, 5);
    }

    @Test
    void testFilterNotNull() {
        assertThat(Seq.of(null, 1, 2, 3, null, 4, 5).filterNotNull())
            .containsExactly(1, 2, 3, 4, 5);
    }


    @Test
    void testFlatMap() {
        assertThat(IntegerSeq.flatMap(i -> Seq.of(i, i * 2)).toList())
            .containsExactly(1, 2, 2, 4, 3, 6, 4, 8, 5, 10);
    }

    @Test
    void testFlatMapIndexed() {
        List<Integer> list = aSequence()
            .flatMapIndexed((index, i) -> Seq.of(index, i * 2))
            .take(8).toList();

        assertThat(list).containsExactly(0, 0, 1, 4, 2, 8, 3, 12);
    }

    @Test
    void testOnce() {
        Seq<Integer> seq = IntegerSeq;
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

    // endregion

    // any

    @Test
    void testAny() {
        assertThat(Seq.empty().any()).isFalse();
        assertThat(IntegerSeq.any()).isTrue();
    }

    @Test
    void testAnyWithPredicate() {
        assertThat(IntegerSeq.any(i -> i == 3)).isTrue();
        assertThat(IntegerSeq.any(i -> i == 6)).isFalse();
        assertThat(Seq.<Integer>of().any(i -> i == 2)).isFalse();
    }


    // average

    @Test
    void testAnyWithPredicateThrowsOnNull() {
        assertThatNullPointerException()
            .isThrownBy(() -> IntegerSeq.any(null));
    }

    @Test
    void testAverageInt() {
        assertThat(IntegerSeq.average(Integer::intValue)).isEqualTo(3.0);
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> IntegerSeqEmpty.average(Integer::intValue));
        assertThatNullPointerException()
            .isThrownBy(() -> IntegerSeq.average((ToIntFunction<? super Integer>) null));
    }

    @Test
    void testAverageIntOptional() {
        assertThat(IntegerSeq.averageOptional(Integer::intValue)).hasValue(3.0);
        assertThat(IntegerSeqEmpty.averageOptional(Integer::intValue)).isEmpty();
        assertThatNullPointerException()
            .isThrownBy(() -> IntegerSeq.averageOptional((ToIntFunction<? super Integer>) null));
    }

    @Test
    void testAverageLong() {
        assertThat(LongSeq.average(Long::longValue)).isEqualTo(3.0);
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> LongSeqEmpty.average(Long::longValue));
        assertThatNullPointerException()
            .isThrownBy(() -> LongSeq.average((ToLongFunction<? super Long>) null));
    }

    @Test
    void testAverageLongOptional() {
        assertThat(LongSeq.averageOptional(Long::longValue)).hasValue(3.0);
        assertThat(LongSeqEmpty.averageOptional(Long::longValue)).isEmpty();
        assertThatNullPointerException()
            .isThrownBy(() -> LongSeq.averageOptional((ToLongFunction<? super Long>) null));
    }

    @Test
    void testAverageDouble() {
        assertThat(DoubleSeq.average(Double::doubleValue)).isEqualTo(3.0);
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> DoubleSeqEmpty.average(Double::doubleValue));
        assertThatNullPointerException()
            .isThrownBy(() -> DoubleSeq.average((ToDoubleFunction<? super Double>) null));
    }

    @Test
    void testAverageDoubleOptional() {
        assertThat(DoubleSeq.averageOptional(Double::doubleValue)).hasValue(3.0);
        assertThat(DoubleSeqEmpty.averageOptional(Double::doubleValue)).isEmpty();
        assertThatNullPointerException()
            .isThrownBy(() -> DoubleSeq.averageOptional((ToDoubleFunction<? super Double>) null));
    }


    // contains

    @Test
    void testContains() {
        assertThat(IntegerSeq.contains(3)).isTrue();
        assertThat(IntegerSeq.contains(6)).isFalse();
        assertThat(Seq.of("a", "b", "c").contains(null)).isFalse();
    }


    // first

    @Test
    void testFirst() {
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> Seq.empty().first());
        assertThat(Seq.of("one").first()).isEqualTo("one");
        assertThat(Seq.of("one", "two").first()).isEqualTo("one");
    }

    @Test
    void testFirstWithPredicate() {
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> Seq.empty().first(x -> true));
        assertThat(Seq.of("one", "two").first(x -> x.equals("two"))).isEqualTo("two");
        assertThat(Seq.of("one", "two", "three").first(x -> x.length() == 5)).isEqualTo("three");
    }

    @Test
    void testFirstOptional() {
        assertThat(Seq.empty().firstOptional()).isEmpty();
        assertThat(Seq.of("one").firstOptional()).hasValue("one");
        assertThat(Seq.of("one", "two").firstOptional()).hasValue("one");
    }

    @Test
    void testFirstOptionalWithPredicate() {
        assertThat(Seq.empty().firstOptional(x -> true)).isEmpty();
        assertThat(Seq.of("one", "two").firstOptional(x -> x.equals("two"))).hasValue("two");
        assertThat(Seq.of("one", "two", "three").firstOptional(x -> x.length() == 5)).hasValue("three");
    }


    // fold

    @Test
    void testFold() {
        assertThat(IntegerSeq.fold(0, Integer::sum)).isEqualTo(15);
        assertThat(IntegerSeqEmpty.fold(0, Integer::sum)).isEqualTo(0);
    }


    // indexOf

    @Test
    void testIndexOf() {
        assertThat(Seq.of(1, 2, 1, 2).indexOf(2)).isEqualTo(1);
        assertThat(Seq.of(1, 2, 1, 2).indexOf(3)).isEqualTo(-1);
    }

    @Test
    void testIndexOfWithPredicate() {
        assertThat(Seq.of(1, 2, 1, 2).indexOf(x -> x == 2)).isEqualTo(1);
        assertThat(Seq.of(1, 2, 1, 2).indexOf(x -> x == 3)).isEqualTo(-1);
        assertThatNullPointerException()
            .isThrownBy(() -> Seq.of(1, 2, 1, 2).indexOf((Predicate<? super Integer>) null));
    }


    // last

    @Test
    void testLast() {
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> Seq.empty().last());
        assertThat(Seq.of("one").last()).isEqualTo("one");
        assertThat(Seq.of("one", "two").last()).isEqualTo("two");
    }

    @Test
    void testLastWithPredicate() {
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> Seq.empty().last(x -> true));
        assertThat(Seq.of("one", "two").last(x -> x.equals("one"))).isEqualTo("one");
        assertThat(Seq.of("one", "two", "three").last(x -> x.length() == 3)).isEqualTo("two");
    }

    @Test
    void testLastOptional() {
        assertThat(Seq.empty().lastOptional()).isEmpty();
        assertThat(Seq.of("one").lastOptional()).hasValue("one");
        assertThat(Seq.of("one", "two").lastOptional()).hasValue("two");
    }

    @Test
    void testLastOptionalWithPredicate() {
        assertThat(Seq.empty().lastOptional(x -> true)).isEmpty();
        assertThat(Seq.of("one", "two").lastOptional(x -> x.equals("one"))).hasValue("one");
        assertThat(Seq.of("one", "two", "three").lastOptional(x -> x.length() == 3)).hasValue("two");
    }


    // lastIndexOf

    @Test
    void testLastIndexOf() {
        assertThat(Seq.of(1, 2, 1, 2).lastIndexOf(1)).isEqualTo(2);
        assertThat(Seq.of(1, 2, 1, 2).lastIndexOf(3)).isEqualTo(-1);
    }

    @Test
    void testLastIndexOfWithPredicate() {
        assertThat(Seq.of(1, 2, 1, 2).lastIndexOf(x -> x == 1)).isEqualTo(2);
        assertThat(Seq.of(1, 2, 1, 2).lastIndexOf(x -> x == 3)).isEqualTo(-1);
        assertThatNullPointerException()
            .isThrownBy(() -> Seq.of(1, 2, 1, 2).lastIndexOf((Predicate<? super Integer>) null));
    }


    // max

    @Test
    void testMax() {
        assertThat(Seq.of(2, 1, 4, 3).max()).isEqualTo(4);
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> Seq.empty().max());
    }

    @Test
    void testMaxWithPredicate() {
        assertThat(Seq.of(2, 1, 4, 3).max(Comparator.reverseOrder())).isEqualTo(1);
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> IntegerSeqEmpty.max(Comparator.reverseOrder()));
    }

    @Test
    void testMaxOptional() {
        assertThat(Seq.of(2, 1, 4, 3).maxOptional()).isEqualTo(Optional.of(4));
        assertThat(Seq.empty().maxOptional()).isEqualTo(Optional.empty());
    }

    @Test
    void testMaxOptionalWithPredicate() {
        assertThat(Seq.of(2, 1, 4, 3).maxOptional(Comparator.reverseOrder())).isEqualTo(Optional.of(1));
        assertThat(IntegerSeqEmpty.maxOptional(Comparator.reverseOrder())).isEqualTo(Optional.empty());
    }


    // min

    @Test
    void testMin() {
        assertThat(Seq.of(2, 1, 4, 3).min()).isEqualTo(1);
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> Seq.empty().min());
    }

    @Test
    void testMinWithPredicate() {
        assertThat(Seq.of(2, 1, 4, 3).min(Comparator.reverseOrder())).isEqualTo(4);
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> IntegerSeqEmpty.min(Comparator.reverseOrder()));
    }

    @Test
    void testMinOptional() {
        assertThat(Seq.of(2, 1, 4, 3).minOptional()).isEqualTo(Optional.of(1));
        assertThat(Seq.empty().minOptional()).isEqualTo(Optional.empty());
    }

    @Test
    void testMinOptionalWithPredicate() {
        assertThat(Seq.of(2, 1, 4, 3).minOptional(Comparator.reverseOrder())).isEqualTo(Optional.of(4));
        assertThat(IntegerSeqEmpty.minOptional(Comparator.reverseOrder())).isEqualTo(Optional.empty());
    }


    // none

    @Test
    void testNone() {
        assertThat(Seq.empty().none()).isTrue();
        assertThat(IntegerSeq.none()).isFalse();
    }

    @Test
    void testNoneWithPredicate() {
        assertThat(IntegerSeq.none(i -> i == 3)).isFalse();
        assertThat(IntegerSeq.none(i -> i == 6)).isTrue();
        assertThat(Seq.<Integer>of().none(i -> i == 2)).isTrue();
    }


    // reduce

    @Test
    void testReduce() {
        assertThat(IntegerSeq.reduce(Integer::sum)).isEqualTo(15);
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> IntegerSeqEmpty.reduce(Integer::sum));
    }

    @Test
    void testReduceOptional() {
        assertThat(IntegerSeq.reduceOptional(Integer::sum)).hasValue(15);
        assertThat(IntegerSeqEmpty.reduceOptional(Integer::sum)).isEqualTo(Optional.empty());
    }


    // single

    @Test
    void testSingle() {
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> Seq.empty().single());
        assertThat(Seq.of("one").single()).isEqualTo("one");
        assertThatIllegalArgumentException()
            .isThrownBy(() -> Seq.of("one", "two").single());
    }

    @Test
    void testSingleWithPredicate() {
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> Seq.empty().single(x -> true));
        assertThat(Seq.of("one", "two").single(x -> x.equals("two"))).isEqualTo("two");
        assertThatIllegalArgumentException()
            .isThrownBy(() -> Seq.of("one", "two", "three").single(x -> x.length() == 3));
    }

    @Test
    void testSingleOptional() {
        assertThat(Seq.empty().singleOptional()).isEmpty();
        assertThat(Seq.of("one").singleOptional()).hasValue("one");
        assertThat(Seq.of("one", "two").singleOptional()).isEmpty();
    }

    @Test
    void testSingleOptionalWithPredicate() {
        assertThat(Seq.empty().singleOptional(x -> true)).isEmpty();
        assertThat(Seq.of("one", "two").singleOptional(x -> x.equals("two"))).hasValue("two");
        assertThat(Seq.of("one", "two", "three").singleOptional(x -> x.length() == 3)).isEmpty();
    }


    // summary

    @Test
    void testSummaryInt() {
        // These do not have equals, so compare using toString
        assertThat(IntegerSeqEmpty.summary(Integer::intValue))
            .hasToString(new IntSummaryStatistics().toString());

        IntSummaryStatistics summary = IntegerSeq.summary(Integer::intValue);
        assertThat(summary.getCount()).isEqualTo(5);
        assertThat(summary.getSum()).isEqualTo(15);
        assertThat(summary.getMin()).isEqualTo(1);
        assertThat(summary.getMax()).isEqualTo(5);
        assertThat(summary.getAverage()).isEqualTo(3.0);
    }

    @Test
    void testSummaryLong() {
        // These do not have equals, so compare using toString
        assertThat(LongSeqEmpty.summary(Long::longValue))
            .hasToString(new LongSummaryStatistics().toString());

        LongSummaryStatistics summary = LongSeq.summary(Long::longValue);
        assertThat(summary.getCount()).isEqualTo(5);
        assertThat(summary.getSum()).isEqualTo(15L);
        assertThat(summary.getMin()).isEqualTo(1L);
        assertThat(summary.getMax()).isEqualTo(5L);
        assertThat(summary.getAverage()).isEqualTo(3.0);
    }

    @Test
    void testSummaryDouble() {
        // These do not have equals, so compare using toString
        assertThat(DoubleSeqEmpty.summary(Double::doubleValue))
            .hasToString(new DoubleSummaryStatistics().toString());

        DoubleSummaryStatistics summary = DoubleSeq.summary(Double::doubleValue);
        assertThat(summary.getCount()).isEqualTo(5);
        assertThat(summary.getSum()).isEqualTo(15.0);
        assertThat(summary.getMin()).isEqualTo(1.0);
        assertThat(summary.getMax()).isEqualTo(5.0);
        assertThat(summary.getAverage()).isEqualTo(3.0);
    }


    // sum

    @Test
    public void testSumOfInt() {
        assertThat(IntegerSeq.sum(Integer::intValue)).isEqualTo(15);
    }

    @Test
    public void testSumOfLong() {
        assertThat(LongSeq.sum(Long::longValue)).isEqualTo(15L);
    }

    @Test
    public void testSumOfDouble() {
        assertThat(DoubleSeq.sum(Double::doubleValue)).isEqualTo(15.0);
    }

}
