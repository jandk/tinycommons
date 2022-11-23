package be.twofold.common.seq;

final class Sequences {

    static final Seq<String> Empty = Seq.of();
    static final Seq<String> Strings = Seq.of("one", "two", "three", "four", "five");
    static final Seq<Integer> EmptyInteger = Seq.of();
    static final Seq<Integer> SequenceInteger = Seq.of(1, 2, 3, 4);
    static final Seq<Long> EmptyLong = Seq.of();
    static final Seq<Long> SequenceLong = Seq.of(1L, 2L, 3L, 4L);
    static final Seq<Double> EmptyDouble = Seq.of();
    static final Seq<Double> SequenceDouble = Seq.of(1.0, 2.0, 3.0, 4.0);

    private Sequences() {
    }


}
