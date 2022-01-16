package be.twofold.common;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

@SuppressWarnings("RedundantCast")
class ArrayUtilsTest {

    public static final float[] SpecialFloats = {Float.NEGATIVE_INFINITY, Float.NaN, Float.POSITIVE_INFINITY};
    public static final double[] SpecialDoubles = {Double.NEGATIVE_INFINITY, Double.NaN, Double.POSITIVE_INFINITY};

    @Test
    void testConstructor() {
        TestUtils.testConstructor(ArrayUtils.class);
    }

    // region testContains

    @Test
    void testContainsByte() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.contains((byte[]) null, (byte) 0));

        byte[] array = {0, 1, 2, 3};
        assertThat(ArrayUtils.contains(array, (byte) 0)).isTrue();
        assertThat(ArrayUtils.contains(array, (byte) 1)).isTrue();
        assertThat(ArrayUtils.contains(array, (byte) 2)).isTrue();
        assertThat(ArrayUtils.contains(array, (byte) 3)).isTrue();
        assertThat(ArrayUtils.contains(array, (byte) 9)).isFalse();
    }

    @Test
    void testContainsShort() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.contains((short[]) null, (short) 0));

        short[] array = {0, 1, 2, 3};
        assertThat(ArrayUtils.contains(array, (short) 0)).isTrue();
        assertThat(ArrayUtils.contains(array, (short) 1)).isTrue();
        assertThat(ArrayUtils.contains(array, (short) 2)).isTrue();
        assertThat(ArrayUtils.contains(array, (short) 3)).isTrue();
        assertThat(ArrayUtils.contains(array, (short) 9)).isFalse();
    }

    @Test
    void testContainsInt() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.contains((int[]) null, (int) 0));

        int[] array = {0, 1, 2, 3};
        assertThat(ArrayUtils.contains(array, (int) 0)).isTrue();
        assertThat(ArrayUtils.contains(array, (int) 1)).isTrue();
        assertThat(ArrayUtils.contains(array, (int) 2)).isTrue();
        assertThat(ArrayUtils.contains(array, (int) 3)).isTrue();
        assertThat(ArrayUtils.contains(array, (int) 9)).isFalse();
    }

    @Test
    void testContainsLong() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.contains((long[]) null, 0));

        long[] array = {0, 1, 2, 3};
        assertThat(ArrayUtils.contains(array, 0)).isTrue();
        assertThat(ArrayUtils.contains(array, 1)).isTrue();
        assertThat(ArrayUtils.contains(array, 2)).isTrue();
        assertThat(ArrayUtils.contains(array, 3)).isTrue();
        assertThat(ArrayUtils.contains(array, 9)).isFalse();
    }

    @Test
    void testContainsFloat() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.contains((float[]) null, 0));

        float[] array = {0, 1, 2, 3};
        assertThat(ArrayUtils.contains(array, 0)).isTrue();
        assertThat(ArrayUtils.contains(array, 1)).isTrue();
        assertThat(ArrayUtils.contains(array, 2)).isTrue();
        assertThat(ArrayUtils.contains(array, 3)).isTrue();
        assertThat(ArrayUtils.contains(array, 9)).isFalse();

        float[] special = SpecialFloats;
        assertThat(ArrayUtils.contains(special, Float.NEGATIVE_INFINITY)).isTrue();
        assertThat(ArrayUtils.contains(special, Float.POSITIVE_INFINITY)).isTrue();
        assertThat(ArrayUtils.contains(special, Float.NaN)).isTrue();
    }

    @Test
    void testContainsDouble() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.contains((double[]) null, 0));

        double[] array = {0, 1, 2, 3};
        assertThat(ArrayUtils.contains(array, 0)).isTrue();
        assertThat(ArrayUtils.contains(array, 1)).isTrue();
        assertThat(ArrayUtils.contains(array, 2)).isTrue();
        assertThat(ArrayUtils.contains(array, 3)).isTrue();
        assertThat(ArrayUtils.contains(array, 9)).isFalse();

        double[] special = SpecialDoubles;
        assertThat(ArrayUtils.contains(special, Double.NEGATIVE_INFINITY)).isTrue();
        assertThat(ArrayUtils.contains(special, Double.POSITIVE_INFINITY)).isTrue();
        assertThat(ArrayUtils.contains(special, Double.NaN)).isTrue();
    }

    @Test
    void testContainsChar() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.contains((char[]) null, 'a'));

        char[] array = {'a', 'b', 'c', 'd'};
        assertThat(ArrayUtils.contains(array, 'a')).isTrue();
        assertThat(ArrayUtils.contains(array, 'b')).isTrue();
        assertThat(ArrayUtils.contains(array, 'c')).isTrue();
        assertThat(ArrayUtils.contains(array, 'd')).isTrue();
        assertThat(ArrayUtils.contains(array, 'z')).isFalse();
    }

    @Test
    void testContainsBoolean() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.contains((boolean[]) null, false));

        boolean[] array = new boolean[]{false};
        assertThat(ArrayUtils.contains(array, false)).isTrue();
        assertThat(ArrayUtils.contains(array, true)).isFalse();
    }

    // endregion

    // region testIndexOf

    @Test
    void testIndexOfByte() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.indexOf((byte[]) null, (byte) 0));

        byte[] array = {1, 2, 3, 4, 1};
        assertThat(ArrayUtils.indexOf(array, (byte) 1)).isEqualTo(0);
        assertThat(ArrayUtils.indexOf(array, (byte) 2)).isEqualTo(1);
        assertThat(ArrayUtils.indexOf(array, (byte) 3)).isEqualTo(2);
        assertThat(ArrayUtils.indexOf(array, (byte) 4)).isEqualTo(3);
        assertThat(ArrayUtils.indexOf(array, (byte) 9)).isEqualTo(-1);
    }

    @Test
    void testIndexOfShort() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.indexOf((short[]) null, (short) 0));

        short[] array = {1, 2, 3, 4, 1};
        assertThat(ArrayUtils.indexOf(array, (short) 1)).isEqualTo(0);
        assertThat(ArrayUtils.indexOf(array, (short) 2)).isEqualTo(1);
        assertThat(ArrayUtils.indexOf(array, (short) 3)).isEqualTo(2);
        assertThat(ArrayUtils.indexOf(array, (short) 4)).isEqualTo(3);
        assertThat(ArrayUtils.indexOf(array, (short) 9)).isEqualTo(-1);
    }

    @Test
    void testIndexOfInt() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.indexOf((int[]) null, (int) 0));

        int[] array = {1, 2, 3, 4, 1};
        assertThat(ArrayUtils.indexOf(array, (int) 1)).isEqualTo(0);
        assertThat(ArrayUtils.indexOf(array, (int) 2)).isEqualTo(1);
        assertThat(ArrayUtils.indexOf(array, (int) 3)).isEqualTo(2);
        assertThat(ArrayUtils.indexOf(array, (int) 4)).isEqualTo(3);
        assertThat(ArrayUtils.indexOf(array, (int) 9)).isEqualTo(-1);
    }

    @Test
    void testIndexOfLong() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.indexOf((long[]) null, 0));

        long[] array = {1, 2, 3, 4, 1};
        assertThat(ArrayUtils.indexOf(array, 1)).isEqualTo(0);
        assertThat(ArrayUtils.indexOf(array, 2)).isEqualTo(1);
        assertThat(ArrayUtils.indexOf(array, 3)).isEqualTo(2);
        assertThat(ArrayUtils.indexOf(array, 4)).isEqualTo(3);
        assertThat(ArrayUtils.indexOf(array, 9)).isEqualTo(-1);
    }

    @Test
    void testIndexOfFloat() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.indexOf((float[]) null, 0));

        float[] array = {1, 2, 3, 4, 1};
        assertThat(ArrayUtils.indexOf(array, 1)).isEqualTo(0);
        assertThat(ArrayUtils.indexOf(array, 2)).isEqualTo(1);
        assertThat(ArrayUtils.indexOf(array, 3)).isEqualTo(2);
        assertThat(ArrayUtils.indexOf(array, 4)).isEqualTo(3);
        assertThat(ArrayUtils.indexOf(array, 9)).isEqualTo(-1);

        float[] special = SpecialFloats;
        assertThat(ArrayUtils.indexOf(special, Float.NEGATIVE_INFINITY)).isEqualTo(0);
        assertThat(ArrayUtils.indexOf(special, Float.POSITIVE_INFINITY)).isEqualTo(2);
        assertThat(ArrayUtils.indexOf(special, Float.NaN)).isEqualTo(1);
    }

    @Test
    void testIndexOfDouble() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.indexOf((double[]) null, 0));

        double[] array = {1, 2, 3, 4, 1};
        assertThat(ArrayUtils.indexOf(array, 1)).isEqualTo(0);
        assertThat(ArrayUtils.indexOf(array, 2)).isEqualTo(1);
        assertThat(ArrayUtils.indexOf(array, 3)).isEqualTo(2);
        assertThat(ArrayUtils.indexOf(array, 4)).isEqualTo(3);
        assertThat(ArrayUtils.indexOf(array, 9)).isEqualTo(-1);

        double[] special = SpecialDoubles;
        assertThat(ArrayUtils.indexOf(special, Double.NEGATIVE_INFINITY)).isEqualTo(0);
        assertThat(ArrayUtils.indexOf(special, Double.POSITIVE_INFINITY)).isEqualTo(2);
        assertThat(ArrayUtils.indexOf(special, Double.NaN)).isEqualTo(1);
    }

    @Test
    void testIndexOfChar() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.indexOf((char[]) null, 'a'));

        char[] array = {'a', 'b', 'c', 'd', 'a'};
        assertThat(ArrayUtils.indexOf(array, 'a')).isEqualTo(0);
        assertThat(ArrayUtils.indexOf(array, 'b')).isEqualTo(1);
        assertThat(ArrayUtils.indexOf(array, 'c')).isEqualTo(2);
        assertThat(ArrayUtils.indexOf(array, 'd')).isEqualTo(3);
        assertThat(ArrayUtils.indexOf(array, 'z')).isEqualTo(-1);
    }

    @Test
    void testIndexOfBoolean() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.indexOf((boolean[]) null, false));

        boolean[] array = new boolean[]{false, true, false};
        assertThat(ArrayUtils.indexOf(array, false)).isEqualTo(0);
        assertThat(ArrayUtils.indexOf(array, true)).isEqualTo(1);
    }


    @Test
    void testIndexOfByteWithFromToIndex() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.indexOf((byte[]) null, (byte) 0, 0, 0));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.indexOf(new byte[2], (byte) 0, -1, 2));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.indexOf(new byte[2], (byte) 0, 0, 3));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.indexOf(new byte[2], (byte) 0, 2, 1));

        byte[] array = {1, 2, 3, 4, 1};
        assertThat(ArrayUtils.indexOf(array, (byte) 1, 2, 5)).isEqualTo(4);
        assertThat(ArrayUtils.indexOf(array, (byte) 2, 2, 4)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(array, (byte) 3, 2, 4)).isEqualTo(2);
        assertThat(ArrayUtils.indexOf(array, (byte) 4, 2, 4)).isEqualTo(3);
        assertThat(ArrayUtils.indexOf(array, (byte) 9, 0, 4)).isEqualTo(-1);
    }

    @Test
    void testIndexOfShortWithFromToIndex() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.indexOf((short[]) null, (short) 0, 0, 0));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.indexOf(new short[2], (short) 0, -1, 2));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.indexOf(new short[2], (short) 0, 0, 3));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.indexOf(new short[2], (short) 0, 2, 1));

        short[] array = {1, 2, 3, 4, 1};
        assertThat(ArrayUtils.indexOf(array, (short) 1, 2, 5)).isEqualTo(4);
        assertThat(ArrayUtils.indexOf(array, (short) 2, 2, 4)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(array, (short) 3, 2, 4)).isEqualTo(2);
        assertThat(ArrayUtils.indexOf(array, (short) 4, 2, 4)).isEqualTo(3);
        assertThat(ArrayUtils.indexOf(array, (short) 9, 0, 4)).isEqualTo(-1);
    }

    @Test
    void testIndexOfIntWithFromToIndex() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.indexOf((int[]) null, (int) 0, 0, 0));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.indexOf(new int[2], (int) 0, -1, 2));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.indexOf(new int[2], (int) 0, 0, 3));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.indexOf(new int[2], (int) 0, 2, 1));

        int[] array = {1, 2, 3, 4, 1};
        assertThat(ArrayUtils.indexOf(array, (int) 1, 2, 5)).isEqualTo(4);
        assertThat(ArrayUtils.indexOf(array, (int) 2, 2, 4)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(array, (int) 3, 2, 4)).isEqualTo(2);
        assertThat(ArrayUtils.indexOf(array, (int) 4, 2, 4)).isEqualTo(3);
        assertThat(ArrayUtils.indexOf(array, (int) 9, 0, 4)).isEqualTo(-1);
    }

    @Test
    void testIndexOfLongWithFromToIndex() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.indexOf((long[]) null, 0, 0, 0));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.indexOf(new long[2], 0, -1, 2));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.indexOf(new long[2], 0, 0, 3));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.indexOf(new long[2], 0, 2, 1));

        long[] array = {1, 2, 3, 4, 1};
        assertThat(ArrayUtils.indexOf(array, 1, 2, 5)).isEqualTo(4);
        assertThat(ArrayUtils.indexOf(array, 2, 2, 4)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(array, 3, 2, 4)).isEqualTo(2);
        assertThat(ArrayUtils.indexOf(array, 4, 2, 4)).isEqualTo(3);
        assertThat(ArrayUtils.indexOf(array, 9, 0, 4)).isEqualTo(-1);
    }

    @Test
    void testIndexOfFloatWithFromToIndex() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.indexOf((float[]) null, 0, 0, 0));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.indexOf(new float[2], 0, -1, 2));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.indexOf(new float[2], 0, 0, 3));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.indexOf(new float[2], 0, 2, 1));

        float[] array = {1, 2, 3, 4, 1};
        assertThat(ArrayUtils.indexOf(array, 1, 2, 5)).isEqualTo(4);
        assertThat(ArrayUtils.indexOf(array, 2, 2, 4)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(array, 3, 2, 4)).isEqualTo(2);
        assertThat(ArrayUtils.indexOf(array, 4, 2, 4)).isEqualTo(3);
        assertThat(ArrayUtils.indexOf(array, 9, 0, 4)).isEqualTo(-1);
    }

    @Test
    void testIndexOfDoubleWithFromToIndex() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.indexOf((double[]) null, 0, 0, 0));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.indexOf(new double[2], 0, -1, 2));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.indexOf(new double[2], 0, 0, 3));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.indexOf(new double[2], 0, 2, 1));

        double[] array = {1, 2, 3, 4, 1};
        assertThat(ArrayUtils.indexOf(array, 1, 2, 5)).isEqualTo(4);
        assertThat(ArrayUtils.indexOf(array, 2, 2, 4)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(array, 3, 2, 4)).isEqualTo(2);
        assertThat(ArrayUtils.indexOf(array, 4, 2, 4)).isEqualTo(3);
        assertThat(ArrayUtils.indexOf(array, 9, 0, 4)).isEqualTo(-1);
    }

    @Test
    void testIndexOfCharWithFromToIndex() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.indexOf((char[]) null, 'a', 0, 0));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.indexOf(new char[2], 'a', -1, 2));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.indexOf(new char[2], 'a', 0, 3));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.indexOf(new char[2], 'a', 2, 1));

        char[] array = {'a', 'b', 'c', 'd', 'a'};
        assertThat(ArrayUtils.indexOf(array, (char) 'a', 2, 5)).isEqualTo(4);
        assertThat(ArrayUtils.indexOf(array, (char) 'b', 2, 5)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(array, (char) 'c', 2, 5)).isEqualTo(2);
        assertThat(ArrayUtils.indexOf(array, (char) 'd', 2, 5)).isEqualTo(3);
        assertThat(ArrayUtils.indexOf(array, (char) 'z', 0, 5)).isEqualTo(-1);
    }

    @Test
    void testIndexOfBooleanWithFromToIndex() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.indexOf((boolean[]) null, true, 0, 0));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.indexOf(new boolean[2], true, -1, 2));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.indexOf(new boolean[2], true, 0, 3));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.indexOf(new boolean[2], true, 2, 1));

        boolean[] array = new boolean[]{false, true, false};
        assertThat(ArrayUtils.indexOf(array, false, 1, 3)).isEqualTo(2);
        assertThat(ArrayUtils.indexOf(array, true, 0, 2)).isEqualTo(1);
    }

    // endregion

    // region testLastIndexOf

    @Test
    void testLastIndexOfByte() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.lastIndexOf((byte[]) null, (byte) 0));

        byte[] array = {1, 2, 3, 4, 1};
        assertThat(ArrayUtils.lastIndexOf(array, (byte) 1)).isEqualTo(4);
        assertThat(ArrayUtils.lastIndexOf(array, (byte) 2)).isEqualTo(1);
        assertThat(ArrayUtils.lastIndexOf(array, (byte) 3)).isEqualTo(2);
        assertThat(ArrayUtils.lastIndexOf(array, (byte) 4)).isEqualTo(3);
        assertThat(ArrayUtils.lastIndexOf(array, (byte) 9)).isEqualTo(-1);
    }

    @Test
    void testLastIndexOfShort() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.lastIndexOf((short[]) null, (short) 0));

        short[] array = {1, 2, 3, 4, 1};
        assertThat(ArrayUtils.lastIndexOf(array, (short) 1)).isEqualTo(4);
        assertThat(ArrayUtils.lastIndexOf(array, (short) 2)).isEqualTo(1);
        assertThat(ArrayUtils.lastIndexOf(array, (short) 3)).isEqualTo(2);
        assertThat(ArrayUtils.lastIndexOf(array, (short) 4)).isEqualTo(3);
        assertThat(ArrayUtils.lastIndexOf(array, (short) 9)).isEqualTo(-1);
    }

    @Test
    void testLastIndexOfInt() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.lastIndexOf((int[]) null, (int) 0));

        int[] array = {1, 2, 3, 4, 1};
        assertThat(ArrayUtils.lastIndexOf(array, (int) 1)).isEqualTo(4);
        assertThat(ArrayUtils.lastIndexOf(array, (int) 2)).isEqualTo(1);
        assertThat(ArrayUtils.lastIndexOf(array, (int) 3)).isEqualTo(2);
        assertThat(ArrayUtils.lastIndexOf(array, (int) 4)).isEqualTo(3);
        assertThat(ArrayUtils.lastIndexOf(array, (int) 9)).isEqualTo(-1);
    }

    @Test
    void testLastIndexOfLong() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.lastIndexOf((long[]) null, 0));

        long[] array = {1, 2, 3, 4, 1};
        assertThat(ArrayUtils.lastIndexOf(array, 1)).isEqualTo(4);
        assertThat(ArrayUtils.lastIndexOf(array, 2)).isEqualTo(1);
        assertThat(ArrayUtils.lastIndexOf(array, 3)).isEqualTo(2);
        assertThat(ArrayUtils.lastIndexOf(array, 4)).isEqualTo(3);
        assertThat(ArrayUtils.lastIndexOf(array, 9)).isEqualTo(-1);
    }


    @Test
    void testLastIndexOfFloat() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.lastIndexOf((float[]) null, 0));

        float[] array = {1, 2, 3, 4, 1};
        assertThat(ArrayUtils.lastIndexOf(array, 1)).isEqualTo(4);
        assertThat(ArrayUtils.lastIndexOf(array, 2)).isEqualTo(1);
        assertThat(ArrayUtils.lastIndexOf(array, 3)).isEqualTo(2);
        assertThat(ArrayUtils.lastIndexOf(array, 4)).isEqualTo(3);
        assertThat(ArrayUtils.lastIndexOf(array, 9)).isEqualTo(-1);

        float[] special = SpecialFloats;
        assertThat(ArrayUtils.lastIndexOf(special, Float.NEGATIVE_INFINITY)).isEqualTo(0);
        assertThat(ArrayUtils.lastIndexOf(special, Float.POSITIVE_INFINITY)).isEqualTo(2);
        assertThat(ArrayUtils.lastIndexOf(special, Float.NaN)).isEqualTo(1);
    }

    @Test
    void testLastIndexOfDouble() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.lastIndexOf((double[]) null, 0));

        double[] array = {1, 2, 3, 4, 1};
        assertThat(ArrayUtils.lastIndexOf(array, 1)).isEqualTo(4);
        assertThat(ArrayUtils.lastIndexOf(array, 2)).isEqualTo(1);
        assertThat(ArrayUtils.lastIndexOf(array, 3)).isEqualTo(2);
        assertThat(ArrayUtils.lastIndexOf(array, 4)).isEqualTo(3);
        assertThat(ArrayUtils.lastIndexOf(array, 9)).isEqualTo(-1);

        double[] special = SpecialDoubles;
        assertThat(ArrayUtils.lastIndexOf(special, Double.NEGATIVE_INFINITY)).isEqualTo(0);
        assertThat(ArrayUtils.lastIndexOf(special, Double.POSITIVE_INFINITY)).isEqualTo(2);
        assertThat(ArrayUtils.lastIndexOf(special, Double.NaN)).isEqualTo(1);
    }

    @Test
    void testLastIndexOfChar() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.lastIndexOf((char[]) null, 'a'));

        char[] array = {'a', 'b', 'c', 'd', 'a'};
        assertThat(ArrayUtils.lastIndexOf(array, 'a')).isEqualTo(4);
        assertThat(ArrayUtils.lastIndexOf(array, 'b')).isEqualTo(1);
        assertThat(ArrayUtils.lastIndexOf(array, 'c')).isEqualTo(2);
        assertThat(ArrayUtils.lastIndexOf(array, 'd')).isEqualTo(3);
        assertThat(ArrayUtils.lastIndexOf(array, 'z')).isEqualTo(-1);
    }

    @Test
    void testLastIndexOfBoolean() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.lastIndexOf((boolean[]) null, false));

        boolean[] array = new boolean[]{false, true, false};
        assertThat(ArrayUtils.lastIndexOf(array, false)).isEqualTo(2);
        assertThat(ArrayUtils.lastIndexOf(array, true)).isEqualTo(1);
    }


    @Test
    void testLastIndexOfByteWithFromToIndex() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.lastIndexOf((byte[]) null, (byte) 0, 0, 0));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.lastIndexOf(new byte[2], (byte) 0, -1, 2));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.lastIndexOf(new byte[2], (byte) 0, 0, 3));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.lastIndexOf(new byte[2], (byte) 0, 2, 1));

        byte[] array = {1, 2, 3, 4, 1};
        assertThat(ArrayUtils.lastIndexOf(array, (byte) 1, 2, 5)).isEqualTo(4);
        assertThat(ArrayUtils.lastIndexOf(array, (byte) 2, 2, 4)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(array, (byte) 3, 2, 4)).isEqualTo(2);
        assertThat(ArrayUtils.lastIndexOf(array, (byte) 4, 2, 4)).isEqualTo(3);
        assertThat(ArrayUtils.lastIndexOf(array, (byte) 9, 0, 4)).isEqualTo(-1);
    }

    @Test
    void testLastIndexOfShortWithFromToIndex() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.lastIndexOf((short[]) null, (short) 0, 0, 0));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.lastIndexOf(new short[2], (short) 0, -1, 2));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.lastIndexOf(new short[2], (short) 0, 0, 3));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.lastIndexOf(new short[2], (short) 0, 2, 1));

        short[] array = {1, 2, 3, 4, 1};
        assertThat(ArrayUtils.lastIndexOf(array, (short) 1, 2, 5)).isEqualTo(4);
        assertThat(ArrayUtils.lastIndexOf(array, (short) 2, 2, 4)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(array, (short) 3, 2, 4)).isEqualTo(2);
        assertThat(ArrayUtils.lastIndexOf(array, (short) 4, 2, 4)).isEqualTo(3);
        assertThat(ArrayUtils.lastIndexOf(array, (short) 9, 0, 4)).isEqualTo(-1);
    }

    @Test
    void testLastIndexOfIntWithFromToIndex() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.lastIndexOf((int[]) null, (int) 0, 0, 0));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.lastIndexOf(new int[2], (int) 0, -1, 2));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.lastIndexOf(new int[2], (int) 0, 0, 3));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.lastIndexOf(new int[2], (int) 0, 2, 1));

        int[] array = {1, 2, 3, 4, 1};
        assertThat(ArrayUtils.lastIndexOf(array, (int) 1, 2, 5)).isEqualTo(4);
        assertThat(ArrayUtils.lastIndexOf(array, (int) 2, 2, 4)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(array, (int) 3, 2, 4)).isEqualTo(2);
        assertThat(ArrayUtils.lastIndexOf(array, (int) 4, 2, 4)).isEqualTo(3);
        assertThat(ArrayUtils.lastIndexOf(array, (int) 9, 0, 4)).isEqualTo(-1);
    }

    @Test
    void testLastIndexOfLongWithFromToIndex() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.lastIndexOf((long[]) null, 0, 0, 0));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.lastIndexOf(new long[2], 0, -1, 2));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.lastIndexOf(new long[2], 0, 0, 3));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.lastIndexOf(new long[2], 0, 2, 1));

        long[] array = {1, 2, 3, 4, 1};
        assertThat(ArrayUtils.lastIndexOf(array, 1, 2, 5)).isEqualTo(4);
        assertThat(ArrayUtils.lastIndexOf(array, 2, 2, 4)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(array, 3, 2, 4)).isEqualTo(2);
        assertThat(ArrayUtils.lastIndexOf(array, 4, 2, 4)).isEqualTo(3);
        assertThat(ArrayUtils.lastIndexOf(array, 9, 0, 4)).isEqualTo(-1);
    }

    @Test
    void testLastIndexOfFloatWithFromToIndex() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.lastIndexOf((float[]) null, 0, 0, 0));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.lastIndexOf(new float[2], 0, -1, 2));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.lastIndexOf(new float[2], 0, 0, 3));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.lastIndexOf(new float[2], 0, 2, 1));

        float[] array = {1, 2, 3, 4, 1};
        assertThat(ArrayUtils.lastIndexOf(array, 1, 2, 5)).isEqualTo(4);
        assertThat(ArrayUtils.lastIndexOf(array, 2, 2, 4)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(array, 3, 2, 4)).isEqualTo(2);
        assertThat(ArrayUtils.lastIndexOf(array, 4, 2, 4)).isEqualTo(3);
        assertThat(ArrayUtils.lastIndexOf(array, 9, 0, 4)).isEqualTo(-1);
    }

    @Test
    void testLastIndexOfDoubleWithFromToIndex() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.lastIndexOf((double[]) null, 0, 0, 0));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.lastIndexOf(new double[2], 0, -1, 2));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.lastIndexOf(new double[2], 0, 0, 3));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.lastIndexOf(new double[2], 0, 2, 1));

        double[] array = {1, 2, 3, 4, 1};
        assertThat(ArrayUtils.lastIndexOf(array, 1, 2, 5)).isEqualTo(4);
        assertThat(ArrayUtils.lastIndexOf(array, 2, 2, 4)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(array, 3, 2, 4)).isEqualTo(2);
        assertThat(ArrayUtils.lastIndexOf(array, 4, 2, 4)).isEqualTo(3);
        assertThat(ArrayUtils.lastIndexOf(array, 9, 0, 4)).isEqualTo(-1);
    }

    @Test
    void testLastIndexOfCharWithFromToIndex() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.lastIndexOf((char[]) null, 'a', 0, 0));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.lastIndexOf(new char[2], 'a', -1, 2));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.lastIndexOf(new char[2], 'a', 0, 3));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.lastIndexOf(new char[2], 'a', 2, 1));

        char[] array = {'a', 'b', 'c', 'd', 'a'};
        assertThat(ArrayUtils.lastIndexOf(array, (char) 'a', 2, 5)).isEqualTo(4);
        assertThat(ArrayUtils.lastIndexOf(array, (char) 'b', 2, 4)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(array, (char) 'c', 2, 4)).isEqualTo(2);
        assertThat(ArrayUtils.lastIndexOf(array, (char) 'd', 2, 4)).isEqualTo(3);
        assertThat(ArrayUtils.lastIndexOf(array, (char) 'z', 0, 4)).isEqualTo(-1);
    }

    @Test
    void testLastIndexOfBooleanWithFromToIndex() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.lastIndexOf((boolean[]) null, true, 0, 0));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.lastIndexOf(new boolean[2], true, -1, 2));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.lastIndexOf(new boolean[2], true, 0, 3));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
            .isThrownBy(() -> ArrayUtils.lastIndexOf(new boolean[2], true, 2, 1));

        boolean[] array = new boolean[]{false, true, false};
        assertThat(ArrayUtils.lastIndexOf(array, false, 0, 2)).isEqualTo(0);
        assertThat(ArrayUtils.lastIndexOf(array, true, 0, 2)).isEqualTo(1);
    }

    // endregion

}
