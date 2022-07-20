package be.twofold.common.seq;

import org.junit.jupiter.api.*;

import java.util.*;
import java.util.function.*;

import static org.assertj.core.api.Assertions.*;

class SeqTest {

    private static final Seq<String> Sequence = Seq.of("one", "two", "three", "four", "five");
    private static final Seq<String> Empty = Seq.of();
    private static final Seq<Integer> EmptyInteger = Seq.of();
    private static final Seq<Integer> SequenceInteger = Seq.of(1, 2, 3, 4);
    private static final Seq<Long> EmptyLong = Seq.of();
    private static final Seq<Long> SequenceLong = Seq.of(1L, 2L, 3L, 4L);
    private static final Seq<Double> EmptyDouble = Seq.of();
    private static final Seq<Double> SequenceDouble = Seq.of(1.0, 2.0, 3.0, 4.0);

    // region Construction

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

    // endregion

    // region Intermediate Operations

    // region distinct

    @Test
    void testDistinct() {
        assertThat(Seq.of().distinct()).isEmpty();
        assertThat(Seq.of(1, 1, 1, 3, 2, 1, 3, 2, 1, 1).distinct())
            .containsExactlyInAnyOrder(1, 2, 3);
    }

    // endregion

    // region drop

    @Test
    void testDrop() {
        assertThat(Sequence.drop(0)).containsExactlyElementsOf(Sequence);
        assertThat(Sequence.drop(3)).containsExactly("four", "five");
        assertThat(Sequence.drop(5)).isEmpty();
    }

    @Test
    void testDropThrows() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> Sequence.drop(-1));
    }

    // endregion

    // region dropWhile

    @Test
    void testDropWhile() {
        assertThat(Sequence.dropWhile(s -> s.length() == 3))
            .containsExactly("three", "four", "five");
    }

    // endregion

    // region filter

    @Test
    void testFilter() {
        assertThat(Sequence.filter(s -> s.startsWith("t")))
            .containsExactly("two", "three");
    }

    @Test
    void testFilterThrows() {
        assertThatNullPointerException()
            .isThrownBy(() -> Seq.of().filter(null));
    }

    @Test
    void testFilterIndexed() {
        assertThat(Sequence.filterIndexed((i, s) -> i % 2 == 0))
            .containsExactly("one", "three", "five");
    }

    @Test
    void testFilterIndexedThrows() {
        assertThatNullPointerException()
            .isThrownBy(() -> Sequence.filterIndexed(null));
    }

    @Test
    void testFilterIsInstance() {
        Seq<Number> seq = Seq.of(1, 2.0, 3L, 4, 5.0, 6L);
        assertThat(seq.filterIsInstance(Double.class)).containsExactly(2.0, 5.0);
        assertThat(seq.filterIsInstance(String.class)).isEmpty();
    }

    @Test
    void testFilterIsInstanceThrows() {
        assertThatNullPointerException()
            .isThrownBy(() -> Sequence.filterIsInstance(null));
    }

    @Test
    void testFilterNot() {
        assertThat(Sequence.filterNot(s -> s.startsWith("t")))
            .containsExactly("one", "four", "five");
    }

    @Test
    void testFilterNotThrows() {
        assertThatNullPointerException()
            .isThrownBy(() -> Sequence.filterNot(null));
    }

    @Test
    void testFilterNotNull() {
        assertThat(Seq.of(null, "one", null, "two", null, "three", null).filterNotNull())
            .containsExactly("one", "two", "three");
    }

    // endregion

    // region flatMap

    @Test
    void testFlatMap() {
        assertThat(Sequence.flatMap(s -> Seq.of(s, s.toUpperCase())))
            .containsExactly("one", "ONE", "two", "TWO", "three", "THREE", "four", "FOUR", "five", "FIVE");
    }

    @Test
    void testFlatMapThrows() {
        assertThatNullPointerException()
            .isThrownBy(() -> Sequence.flatMap(null));
    }

    @Test
    void testFlatMapIndexed() {
        assertThat(Sequence.flatMapIndexed((i, s) -> Seq.of(String.valueOf(i + 1), s)))
            .containsExactly("1", "one", "2", "two", "3", "three", "4", "four", "5", "five");
    }

    @Test
    void testFlatMapIndexedThrows() {
        assertThatNullPointerException()
            .isThrownBy(() -> Sequence.flatMapIndexed(null));
    }

    // endregion

    // region once

    @Test
    void testOnce() {
        Seq<String> seq = Sequence;
        seq.toList();
        seq.toList();

        Seq<String> once = seq.once();
        once.toList();

        assertThatIllegalStateException()
            .isThrownBy(once::toList);
    }

    // endregion

    // region take

    @Test
    void testTake() {
        assertThat(Sequence.take(0)).isEmpty();
        assertThat(Sequence.take(3)).containsExactly("one", "two", "three");
        assertThat(Sequence.take(5)).containsExactlyElementsOf(Sequence);
    }

    @Test
    void testTakeThrows() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> Sequence.take(-1));
    }

    // endregion

    // endregion

    // region Terminal Operations

    // region any

    @Test
    void testAny() {
        assertThat(Empty.any()).isFalse();
        assertThat(Sequence.any()).isTrue();
    }

    @Test
    void testAnyWithPredicate() {
        assertThat(Empty.any(s -> s.startsWith("t"))).isFalse();
        assertThat(Sequence.any(s -> s.startsWith("t"))).isTrue();
        assertThat(Sequence.any(s -> s.startsWith("u"))).isFalse();
    }

    @Test
    void testAnyWithPredicateThrows() {
        assertThatNullPointerException()
            .isThrownBy(() -> Sequence.any(null));
    }

    // endregion

    // region average

    @Test
    void testAverageInt() {
        assertThat(SequenceInteger.average(Integer::intValue)).isEqualTo(2.5);
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> EmptyInteger.average(Integer::intValue));
    }

    @Test
    void testAverageIntOptional() {
        assertThat(SequenceInteger.averageOptional(Integer::intValue)).hasValue(2.5);
        assertThat(EmptyInteger.averageOptional(Integer::intValue)).isEmpty();
    }

    @Test
    void testAverageIntThrows() {
        assertThatNullPointerException()
            .isThrownBy(() -> SequenceInteger.average((ToIntFunction<Integer>) null));
        assertThatNullPointerException()
            .isThrownBy(() -> SequenceInteger.averageOptional((ToIntFunction<Integer>) null));
    }


    @Test
    void testAverageLong() {
        assertThat(SequenceLong.average(Long::longValue)).isEqualTo(2.5);
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> EmptyLong.average(Long::longValue));
    }

    @Test
    void testAverageLongOptional() {
        assertThat(SequenceLong.averageOptional(Long::longValue)).hasValue(2.5);
        assertThat(EmptyLong.averageOptional(Long::longValue)).isEmpty();
    }

    @Test
    void testAverageLongThrows() {
        assertThatNullPointerException()
            .isThrownBy(() -> SequenceLong.average((ToLongFunction<Long>) null));
        assertThatNullPointerException()
            .isThrownBy(() -> SequenceLong.averageOptional((ToLongFunction<Long>) null));
    }

    @Test
    void testAverageDouble() {
        assertThat(SequenceDouble.average(Double::doubleValue)).isEqualTo(2.5);
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> EmptyDouble.average(Double::doubleValue));
    }

    @Test
    void testAverageDoubleOptional() {
        assertThat(SequenceDouble.averageOptional(Double::doubleValue)).hasValue(2.5);
        assertThat(EmptyDouble.averageOptional(Double::doubleValue)).isEmpty();
    }

    @Test
    void testAverageDoubleThrows() {
        assertThatNullPointerException()
            .isThrownBy(() -> SequenceDouble.average((ToDoubleFunction<Double>) null));
        assertThatNullPointerException()
            .isThrownBy(() -> SequenceDouble.averageOptional((ToDoubleFunction<Double>) null));
    }

    // endregion

    // region contains

    @Test
    void testContains() {
        assertThat(Sequence.contains("three")).isTrue();
        assertThat(Sequence.contains("six")).isFalse();
        assertThat(Sequence.contains(null)).isFalse();
    }

    // endregion

    // region first

    @Test
    void testFirst() {
        assertThat(Sequence.first()).isEqualTo("one");
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(Empty::first);
    }

    @Test
    void testFirstWithPredicate() {
        assertThat(Sequence.first(s -> s.startsWith("t"))).isEqualTo("two");
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> Empty.first(s -> s.startsWith("t")));
    }

    @Test
    void testFirstOptional() {
        assertThat(Sequence.firstOptional()).hasValue("one");
        assertThat(Empty.firstOptional()).isEmpty();
    }

    @Test
    void testFirstOptionalWithPredicate() {
        assertThat(Sequence.firstOptional(s -> s.startsWith("t"))).hasValue("two");
        assertThat(Empty.firstOptional(s -> s.startsWith("t"))).isEmpty();
    }

    // endregion

    // region fold

    @Test
    void testFold() {
        assertThat(Sequence.fold("---", String::concat)).isEqualTo("---onetwothreefourfive");
        assertThat(Empty.fold("---", String::concat)).isEqualTo("---");
    }

    // endregion

    // region groupBy

    @Test
    void testGroupByWithKeyMapper() {
        assertThatNullPointerException().isThrownBy(() -> Sequence.groupBy(null));

        assertThat(Empty.groupBy(String::length)).isEmpty();
        assertThat(Sequence.groupBy(String::length)).containsOnly(
            Map.entry(3, List.of("one", "two")),
            Map.entry(4, List.of("four", "five")),
            Map.entry(5, List.of("three"))
        );
    }

    @Test
    void testGroupByWithKeyMapperAndValueMapper() {
        assertThatNullPointerException().isThrownBy(() -> Sequence.groupBy(null, Function.identity()));
        assertThatNullPointerException().isThrownBy(() -> Sequence.groupBy(Function.identity(), null));

        assertThat(Empty.groupBy(String::length, s -> s.charAt(0))).isEmpty();
        assertThat(Sequence.groupBy(String::length, s -> s.charAt(0))).containsOnly(
            Map.entry(3, List.of('o', 't')),
            Map.entry(4, List.of('f', 'f')),
            Map.entry(5, List.of('t'))
        );
    }

    // endregion

    // region indexOf

    @Test
    void testIndexOf() {
        Seq<String> seq = Seq.of("one", "two", "one", "two");
        assertThat(seq.indexOf("two")).isEqualTo(1);
        assertThat(seq.indexOf("three")).isEqualTo(-1);
    }

    @Test
    void testIndexOfWithPredicate() {
        assertThat(Sequence.indexOf(s -> s.startsWith("t"))).isEqualTo(1);
        assertThat(Sequence.indexOf(s -> s.startsWith("u"))).isEqualTo(-1);
        assertThatNullPointerException()
            .isThrownBy(() -> Sequence.indexOf((Predicate<String>) null));
    }

    // endregion

    // region last

    @Test
    void testLast() {
        assertThat(Sequence.last()).isEqualTo("five");
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(Empty::last);
    }

    @Test
    void testLastWithPredicate() {
        assertThat(Sequence.last(s -> s.startsWith("t"))).isEqualTo("three");
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> Empty.last(s -> s.startsWith("t")));
    }

    @Test
    void testLastOptional() {
        assertThat(Sequence.lastOptional()).hasValue("five");
        assertThat(Empty.lastOptional()).isEmpty();
    }

    @Test
    void testLastOptionalWithPredicate() {
        assertThat(Sequence.lastOptional(s -> s.startsWith("t"))).hasValue("three");
        assertThat(Empty.lastOptional(s -> s.startsWith("t"))).isEmpty();
    }

    // endregion

    // region lastIndexOf

    @Test
    void testLastIndexOf() {
        Seq<String> seq = Seq.of("one", "two", "one", "two");
        assertThat(seq.lastIndexOf("one")).isEqualTo(2);
        assertThat(seq.lastIndexOf("three")).isEqualTo(-1);
    }

    @Test
    void testLastIndexOfWithPredicate() {
        assertThat(Sequence.lastIndexOf(s -> s.startsWith("t"))).isEqualTo(2);
        assertThat(Sequence.lastIndexOf(s -> s.startsWith("u"))).isEqualTo(-1);
        assertThatNullPointerException()
            .isThrownBy(() -> Sequence.lastIndexOf((Predicate<String>) null));
    }

    // endregion

    // region max

    @Test
    void testMax() {
        assertThat(Sequence.max()).isEqualTo("two");
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(Empty::max);
    }

    @Test
    void testMaxWithComparator() {
        assertThat(Sequence.max(Comparator.reverseOrder())).isEqualTo("five");
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> EmptyInteger.max(Comparator.reverseOrder()));
    }

    @Test
    void testMaxOptional() {
        assertThat(Sequence.maxOptional()).hasValue("two");
        assertThat(Empty.maxOptional()).isEmpty();
    }

    @Test
    void testMaxOptionalWithComparator() {
        assertThat(Sequence.maxOptional(Comparator.reverseOrder())).hasValue("five");
        assertThat(EmptyInteger.maxOptional(Comparator.reverseOrder())).isEmpty();
    }

    // endregion

    // region min

    @Test
    void testMin() {
        assertThat(Sequence.min()).isEqualTo("five");
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(Empty::min);
    }

    @Test
    void testMinWithComparator() {
        assertThat(Sequence.min(Comparator.reverseOrder())).isEqualTo("two");
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> EmptyInteger.min(Comparator.reverseOrder()));
    }

    @Test
    void testMinOptional() {
        assertThat(Sequence.minOptional()).hasValue("five");
        assertThat(Empty.minOptional()).isEmpty();
    }

    @Test
    void testMinOptionalWithComparator() {
        assertThat(Sequence.minOptional(Comparator.reverseOrder())).hasValue("two");
        assertThat(EmptyInteger.minOptional(Comparator.reverseOrder())).isEmpty();
    }

    // endregion

    // region none

    @Test
    void testNone() {
        assertThat(Sequence.none()).isFalse();
        assertThat(Empty.none()).isTrue();
    }

    @Test
    void testNoneWithPredicate() {
        assertThat(Sequence.none(t -> t.startsWith("t"))).isFalse();
        assertThat(Sequence.none(t -> t.startsWith("u"))).isTrue();
        assertThat(Empty.none(t -> t.startsWith("t"))).isTrue();
        assertThatNullPointerException()
            .isThrownBy(() -> Sequence.none(null));
    }

    // endregion

    // region reduce

    @Test
    void testReduce() {
        assertThat(Sequence.reduce(String::concat)).isEqualTo("onetwothreefourfive");
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> Empty.reduce(String::concat));
        assertThatNullPointerException()
            .isThrownBy(() -> Sequence.reduce(null));
    }

    @Test
    void testReduceOptional() {
        assertThat(Sequence.reduceOptional(String::concat)).hasValue("onetwothreefourfive");
        assertThat(Empty.reduceOptional(String::concat)).isEmpty();
        assertThatNullPointerException()
            .isThrownBy(() -> Sequence.reduceOptional(null));
    }

    // endregion

    // region single

    @Test
    void testSingle() {
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> Sequence.take(0).single());
        assertThat(Sequence.take(1).single()).isEqualTo("one");
        assertThatIllegalArgumentException()
            .isThrownBy(() -> Sequence.take(2).single());
    }

    @Test
    void testSingleWithPredicate() {
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(() -> Sequence.single(t -> t.startsWith("u")));
        assertThat(Sequence.single(t -> t.startsWith("o"))).isEqualTo("one");
        assertThatIllegalArgumentException()
            .isThrownBy(() -> Sequence.single(t -> t.startsWith("t")));
    }

    @Test
    void testSingleOptional() {
        assertThat(Sequence.take(0).singleOptional()).isEmpty();
        assertThat(Sequence.take(1).singleOptional()).hasValue("one");
        assertThat(Sequence.take(2).singleOptional()).isEmpty();
    }

    @Test
    void testSingleOptionalWithPredicate() {
        assertThat(Sequence.singleOptional(t -> t.startsWith("u"))).isEmpty();
        assertThat(Sequence.singleOptional(t -> t.startsWith("o"))).hasValue("one");
        assertThat(Sequence.singleOptional(t -> t.startsWith("t"))).isEmpty();
    }

    // endregion

    // region summary

    @Test
    void testSummaryInt() {
        // These do not have equals, so compare using toString
        assertThat(EmptyInteger.summary(Integer::intValue))
            .hasToString(new IntSummaryStatistics().toString());

        IntSummaryStatistics summary = SequenceInteger.summary(Integer::intValue);
        assertThat(summary.getCount()).isEqualTo(4);
        assertThat(summary.getSum()).isEqualTo(10);
        assertThat(summary.getMin()).isEqualTo(1);
        assertThat(summary.getMax()).isEqualTo(4);
        assertThat(summary.getAverage()).isEqualTo(2.5);

        assertThatNullPointerException()
            .isThrownBy(() -> Sequence.summary((ToIntFunction<String>) null));
    }

    @Test
    void testSummaryLong() {
        // These do not have equals, so compare using toString
        assertThat(EmptyLong.summary(Long::longValue))
            .hasToString(new LongSummaryStatistics().toString());

        LongSummaryStatistics summary = SequenceLong.summary(Long::longValue);
        assertThat(summary.getCount()).isEqualTo(4);
        assertThat(summary.getSum()).isEqualTo(10L);
        assertThat(summary.getMin()).isEqualTo(1L);
        assertThat(summary.getMax()).isEqualTo(4L);
        assertThat(summary.getAverage()).isEqualTo(2.5);

        assertThatNullPointerException()
            .isThrownBy(() -> Sequence.summary((ToLongFunction<String>) null));
    }

    @Test
    void testSummaryDouble() {
        // These do not have equals, so compare using toString
        assertThat(EmptyDouble.summary(Double::doubleValue))
            .hasToString(new DoubleSummaryStatistics().toString());

        DoubleSummaryStatistics summary = SequenceDouble.summary(Double::doubleValue);
        assertThat(summary.getCount()).isEqualTo(4);
        assertThat(summary.getSum()).isEqualTo(10.0);
        assertThat(summary.getMin()).isEqualTo(1.0);
        assertThat(summary.getMax()).isEqualTo(4.0);
        assertThat(summary.getAverage()).isEqualTo(2.5);

        assertThatNullPointerException()
            .isThrownBy(() -> Sequence.summary((ToDoubleFunction<String>) null));
    }

    // endregion

    // region sum

    @Test
    public void testSumOfInt() {
        assertThat(SequenceInteger.sum(Integer::intValue)).isEqualTo(10);

        assertThatNullPointerException()
            .isThrownBy(() -> Sequence.sum((ToIntFunction<String>) null));
    }

    @Test
    public void testSumOfLong() {
        assertThat(SequenceLong.sum(Long::longValue)).isEqualTo(10L);

        assertThatNullPointerException()
            .isThrownBy(() -> Sequence.sum((ToLongFunction<String>) null));
    }

    @Test
    public void testSumOfDouble() {
        assertThat(SequenceDouble.sum(Double::doubleValue)).isEqualTo(10.0);

        assertThatNullPointerException()
            .isThrownBy(() -> Sequence.sum((ToDoubleFunction<String>) null));
    }

    // endregion

    // endregion

}
