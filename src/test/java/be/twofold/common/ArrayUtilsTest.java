package be.twofold.common;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

@SuppressWarnings("RedundantCast")
public class ArrayUtilsTest {

    private static final float[] SpecialFloats = new float[]{
        Float.NaN,
        Float.POSITIVE_INFINITY,
        Float.NEGATIVE_INFINITY,
        Float.MAX_VALUE,
        -Float.MAX_VALUE,
        Float.MIN_VALUE,
        -Float.MIN_VALUE,
        Float.MIN_NORMAL,
        -Float.MIN_NORMAL,
        0f,
        -0f,
    };

    private static final double[] SpecialDoubles = new double[]{
        Double.NaN,
        Double.POSITIVE_INFINITY,
        Double.NEGATIVE_INFINITY,
        Double.MAX_VALUE,
        -Double.MAX_VALUE,
        Double.MIN_VALUE,
        -Double.MIN_VALUE,
        Double.MIN_NORMAL,
        -Double.MIN_NORMAL,
        0d,
        -0d,
    };

    @Test
    public void testConstructor() {
        TestUtils.testConstructor(ArrayUtils.class);
    }

    // region testContains

    @Test
    public void testContainsByte() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.contains((byte[]) null, (byte) 0));

        assertThat(ArrayUtils.contains(new byte[]{}, (byte) 1)).isFalse();
        assertThat(ArrayUtils.contains(new byte[]{1}, (byte) 2)).isFalse();
        assertThat(ArrayUtils.contains(new byte[]{-1}, (byte) -1)).isTrue();
        assertThat(ArrayUtils.contains(new byte[]{2, 3, 4}, (byte) 1)).isFalse();
        assertThat(ArrayUtils.contains(new byte[]{2, 3, 4}, (byte) 2)).isTrue();
        assertThat(ArrayUtils.contains(new byte[]{2, 3, 4}, (byte) 3)).isTrue();
        assertThat(ArrayUtils.contains(new byte[]{2, 3, 4}, (byte) 4)).isTrue();
    }

    @Test
    public void testContainsShort() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.contains((short[]) null, (short) 0));

        assertThat(ArrayUtils.contains(new short[]{}, (short) 1)).isFalse();
        assertThat(ArrayUtils.contains(new short[]{1}, (short) 2)).isFalse();
        assertThat(ArrayUtils.contains(new short[]{-1}, (short) -1)).isTrue();
        assertThat(ArrayUtils.contains(new short[]{2, 3, 4}, (short) 1)).isFalse();
        assertThat(ArrayUtils.contains(new short[]{2, 3, 4}, (short) 2)).isTrue();
        assertThat(ArrayUtils.contains(new short[]{2, 3, 4}, (short) 3)).isTrue();
        assertThat(ArrayUtils.contains(new short[]{2, 3, 4}, (short) 4)).isTrue();
    }

    @Test
    public void testContainsInt() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.contains((int[]) null, 0));

        assertThat(ArrayUtils.contains(new int[]{}, 1)).isFalse();
        assertThat(ArrayUtils.contains(new int[]{1}, 2)).isFalse();
        assertThat(ArrayUtils.contains(new int[]{-1}, -1)).isTrue();
        assertThat(ArrayUtils.contains(new int[]{2, 3, 4}, 1)).isFalse();
        assertThat(ArrayUtils.contains(new int[]{2, 3, 4}, 2)).isTrue();
        assertThat(ArrayUtils.contains(new int[]{2, 3, 4}, 3)).isTrue();
        assertThat(ArrayUtils.contains(new int[]{2, 3, 4}, 4)).isTrue();
    }

    @Test
    public void testContainsLong() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.contains((long[]) null, 0L));

        assertThat(ArrayUtils.contains(new long[]{}, 1L)).isFalse();
        assertThat(ArrayUtils.contains(new long[]{1L}, 2L)).isFalse();
        assertThat(ArrayUtils.contains(new long[]{-1L}, -1L)).isTrue();
        assertThat(ArrayUtils.contains(new long[]{2L, 3L, 4L}, 1L)).isFalse();
        assertThat(ArrayUtils.contains(new long[]{2L, 3L, 4L}, 2L)).isTrue();
        assertThat(ArrayUtils.contains(new long[]{2L, 3L, 4L}, 3L)).isTrue();
        assertThat(ArrayUtils.contains(new long[]{2L, 3L, 4L}, 4L)).isTrue();
    }

    @Test
    public void testContainsFloat() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.contains((float[]) null, 0f));

        assertThat(ArrayUtils.contains(new float[]{}, 1f)).isFalse();
        assertThat(ArrayUtils.contains(new float[]{1f}, 2f)).isFalse();
        assertThat(ArrayUtils.contains(new float[]{-1f}, -1f)).isTrue();
        assertThat(ArrayUtils.contains(new float[]{2f, 3f, 4f}, 1f)).isFalse();
        assertThat(ArrayUtils.contains(new float[]{2f, 3f, 4f}, 2f)).isTrue();
        assertThat(ArrayUtils.contains(new float[]{2f, 3f, 4f}, 3f)).isTrue();
        assertThat(ArrayUtils.contains(new float[]{2f, 3f, 4f}, 4f)).isTrue();

        for (float value : SpecialFloats) {
            assertThat(ArrayUtils.contains(new float[]{5f, value}, value))
                .withFailMessage(Float.toString(value))
                .isTrue();
        }
    }

    @Test
    public void testContainsDouble() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.contains((double[]) null, 0d));

        assertThat(ArrayUtils.contains(new double[]{}, 1d)).isFalse();
        assertThat(ArrayUtils.contains(new double[]{1f}, 2d)).isFalse();
        assertThat(ArrayUtils.contains(new double[]{-1f}, -1d)).isTrue();
        assertThat(ArrayUtils.contains(new double[]{2d, 3d, 4d}, 1d)).isFalse();
        assertThat(ArrayUtils.contains(new double[]{2d, 3d, 4d}, 2d)).isTrue();
        assertThat(ArrayUtils.contains(new double[]{2d, 3d, 4d}, 3d)).isTrue();
        assertThat(ArrayUtils.contains(new double[]{2d, 3d, 4d}, 4d)).isTrue();

        for (double value : SpecialDoubles) {
            assertThat(ArrayUtils.contains(new double[]{5d, value}, value))
                .withFailMessage(Double.toString(value))
                .isTrue();
        }
    }

    @Test
    public void testContainsChar() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.contains((char[]) null, (char) 0));

        assertThat(ArrayUtils.contains(new char[]{}, (char) 1)).isFalse();
        assertThat(ArrayUtils.contains(new char[]{1}, (char) 2)).isFalse();
        assertThat(ArrayUtils.contains(new char[]{(char) -1}, (char) -1)).isTrue();
        assertThat(ArrayUtils.contains(new char[]{2, 3, 4}, (char) 1)).isFalse();
        assertThat(ArrayUtils.contains(new char[]{2, 3, 4}, (char) 2)).isTrue();
        assertThat(ArrayUtils.contains(new char[]{2, 3, 4}, (char) 3)).isTrue();
        assertThat(ArrayUtils.contains(new char[]{2, 3, 4}, (char) 4)).isTrue();
    }

    @Test
    public void testContainsBoolean() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.contains((boolean[]) null, false));

        assertThat(ArrayUtils.contains(new boolean[]{}, false)).isFalse();
        assertThat(ArrayUtils.contains(new boolean[]{false}, true)).isFalse();
        assertThat(ArrayUtils.contains(new boolean[]{false}, false)).isTrue();
        assertThat(ArrayUtils.contains(new boolean[]{false, true}, false)).isTrue();
        assertThat(ArrayUtils.contains(new boolean[]{false, true}, true)).isTrue();
    }

    // endregion

    // region testIndexOf

    @Test
    public void testIndexOfByte() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.indexOf((byte[]) null, (byte) 0));

        assertThat(ArrayUtils.indexOf(new byte[]{}, (byte) 1)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new byte[]{1}, (byte) 2)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new byte[]{-1}, (byte) -1)).isEqualTo(0);
        assertThat(ArrayUtils.indexOf(new byte[]{2, 3, 4}, (byte) 1)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new byte[]{2, 3, 4}, (byte) 2)).isEqualTo(0);
        assertThat(ArrayUtils.indexOf(new byte[]{2, 3, 4}, (byte) 3)).isEqualTo(1);
        assertThat(ArrayUtils.indexOf(new byte[]{2, 3, 4}, (byte) 4)).isEqualTo(2);
        assertThat(ArrayUtils.indexOf(new byte[]{2, 3, 2, 3}, (byte) 3)).isEqualTo(1);
    }

    @Test
    public void testIndexOfShort() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.indexOf((short[]) null, (short) 0));

        assertThat(ArrayUtils.indexOf(new short[]{}, (short) 1)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new short[]{1}, (short) 2)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new short[]{-1}, (short) -1)).isEqualTo(0);
        assertThat(ArrayUtils.indexOf(new short[]{2, 3, 4}, (short) 1)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new short[]{2, 3, 4}, (short) 2)).isEqualTo(0);
        assertThat(ArrayUtils.indexOf(new short[]{2, 3, 4}, (short) 3)).isEqualTo(1);
        assertThat(ArrayUtils.indexOf(new short[]{2, 3, 4}, (short) 4)).isEqualTo(2);
        assertThat(ArrayUtils.indexOf(new short[]{2, 3, 2, 3}, (short) 3)).isEqualTo(1);
    }

    @Test
    public void testIndexOfInt() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.indexOf((int[]) null, 0));

        assertThat(ArrayUtils.indexOf(new int[]{}, 1)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new int[]{1}, 2)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new int[]{-1}, -1)).isEqualTo(0);
        assertThat(ArrayUtils.indexOf(new int[]{2, 3, 4}, 1)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new int[]{2, 3, 4}, 2)).isEqualTo(0);
        assertThat(ArrayUtils.indexOf(new int[]{2, 3, 4}, 3)).isEqualTo(1);
        assertThat(ArrayUtils.indexOf(new int[]{2, 3, 4}, 4)).isEqualTo(2);
        assertThat(ArrayUtils.indexOf(new int[]{2, 3, 2, 3}, 3)).isEqualTo(1);
    }

    @Test
    public void testIndexOfLong() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.indexOf((long[]) null, 0L));

        assertThat(ArrayUtils.indexOf(new long[]{}, 1L)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new long[]{1L}, 2L)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new long[]{-1L}, -1L)).isEqualTo(0);
        assertThat(ArrayUtils.indexOf(new long[]{2L, 3L, 4L}, 1L)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new long[]{2L, 3L, 4L}, 2L)).isEqualTo(0);
        assertThat(ArrayUtils.indexOf(new long[]{2L, 3L, 4L}, 3L)).isEqualTo(1);
        assertThat(ArrayUtils.indexOf(new long[]{2L, 3L, 4L}, 4L)).isEqualTo(2);
        assertThat(ArrayUtils.indexOf(new long[]{2L, 3L, 2L, 3L}, 3L)).isEqualTo(1);
    }

    @Test
    public void testIndexOfFloat() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.indexOf((float[]) null, 0f));

        assertThat(ArrayUtils.indexOf(new float[]{}, 1f)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new float[]{1f}, 2f)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new float[]{-1f}, -1f)).isEqualTo(0);
        assertThat(ArrayUtils.indexOf(new float[]{2f, 3f, 4f}, 1f)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new float[]{2f, 3f, 4f}, 2f)).isEqualTo(0);
        assertThat(ArrayUtils.indexOf(new float[]{2f, 3f, 4f}, 3f)).isEqualTo(1);
        assertThat(ArrayUtils.indexOf(new float[]{2f, 3f, 4f}, 4f)).isEqualTo(2);
        assertThat(ArrayUtils.indexOf(new float[]{2f, 3f, 2f, 3f}, 3f)).isEqualTo(1);

        for (float value : SpecialFloats) {
            assertThat(ArrayUtils.indexOf(new float[]{5f, value}, value))
                .withFailMessage(Float.toString(value))
                .isEqualTo(1);
        }
    }

    @Test
    public void testIndexOfDouble() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.indexOf((double[]) null, 0d));

        assertThat(ArrayUtils.indexOf(new double[]{}, 1d)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new double[]{1f}, 2d)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new double[]{-1f}, -1d)).isEqualTo(0);
        assertThat(ArrayUtils.indexOf(new double[]{2d, 3d, 4d}, 1d)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new double[]{2d, 3d, 4d}, 2d)).isEqualTo(0);
        assertThat(ArrayUtils.indexOf(new double[]{2d, 3d, 4d}, 3d)).isEqualTo(1);
        assertThat(ArrayUtils.indexOf(new double[]{2d, 3d, 4d}, 4d)).isEqualTo(2);
        assertThat(ArrayUtils.indexOf(new double[]{2d, 3d, 2d, 3d}, 3d)).isEqualTo(1);

        for (double value : SpecialDoubles) {
            assertThat(ArrayUtils.indexOf(new double[]{5d, value}, value))
                .withFailMessage(Double.toString(value))
                .isEqualTo(1);
        }
    }

    @Test
    public void testIndexOfChar() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.indexOf((char[]) null, (char) 0));

        assertThat(ArrayUtils.indexOf(new char[]{}, (char) 1)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new char[]{1}, (char) 2)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new char[]{(char) -1}, (char) -1)).isEqualTo(0);
        assertThat(ArrayUtils.indexOf(new char[]{2, 3, 4}, (char) 1)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new char[]{2, 3, 4}, (char) 2)).isEqualTo(0);
        assertThat(ArrayUtils.indexOf(new char[]{2, 3, 4}, (char) 3)).isEqualTo(1);
        assertThat(ArrayUtils.indexOf(new char[]{2, 3, 4}, (char) 4)).isEqualTo(2);
        assertThat(ArrayUtils.indexOf(new char[]{(char) 2, (char) 3, (char) 2, (char) 3}, (char) 3)).isEqualTo(1);
    }

    @Test
    public void testIndexOfBoolean() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.indexOf((boolean[]) null, false));

        assertThat(ArrayUtils.indexOf(new boolean[]{}, false)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new boolean[]{false}, true)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new boolean[]{false, false}, true)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new boolean[]{false}, false)).isEqualTo(0);
        assertThat(ArrayUtils.indexOf(new boolean[]{false, true}, false)).isEqualTo(0);
        assertThat(ArrayUtils.indexOf(new boolean[]{false, true}, true)).isEqualTo(1);
        assertThat(ArrayUtils.indexOf(new boolean[]{true, false, false}, false)).isEqualTo(1);
    }

    // endregion

    // region testLastIndexOf

    @Test
    public void testLastIndexOfByte() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.lastIndexOf((byte[]) null, (byte) 0));

        assertThat(ArrayUtils.lastIndexOf(new byte[]{}, (byte) 1)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new byte[]{1}, (byte) 2)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new byte[]{-1}, (byte) -1)).isEqualTo(0);
        assertThat(ArrayUtils.lastIndexOf(new byte[]{2, 3, 4}, (byte) 1)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new byte[]{2, 3, 4}, (byte) 2)).isEqualTo(0);
        assertThat(ArrayUtils.lastIndexOf(new byte[]{2, 3, 4}, (byte) 3)).isEqualTo(1);
        assertThat(ArrayUtils.lastIndexOf(new byte[]{2, 3, 4}, (byte) 4)).isEqualTo(2);
        assertThat(ArrayUtils.lastIndexOf(new byte[]{2, 3, 2, 3}, (byte) 3)).isEqualTo(3);
    }

    @Test
    public void testLastIndexOfShort() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.lastIndexOf((short[]) null, (short) 0));

        assertThat(ArrayUtils.lastIndexOf(new short[]{}, (short) 1)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new short[]{1}, (short) 2)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new short[]{-1}, (short) -1)).isEqualTo(0);
        assertThat(ArrayUtils.lastIndexOf(new short[]{2, 3, 4}, (short) 1)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new short[]{2, 3, 4}, (short) 2)).isEqualTo(0);
        assertThat(ArrayUtils.lastIndexOf(new short[]{2, 3, 4}, (short) 3)).isEqualTo(1);
        assertThat(ArrayUtils.lastIndexOf(new short[]{2, 3, 4}, (short) 4)).isEqualTo(2);
        assertThat(ArrayUtils.lastIndexOf(new short[]{2, 3, 2, 3}, (short) 3)).isEqualTo(3);
    }

    @Test
    public void testLastIndexOfInt() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.lastIndexOf((int[]) null, 0));

        assertThat(ArrayUtils.lastIndexOf(new int[]{}, 1)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new int[]{1}, 2)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new int[]{-1}, -1)).isEqualTo(0);
        assertThat(ArrayUtils.lastIndexOf(new int[]{2, 3, 4}, 1)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new int[]{2, 3, 4}, 2)).isEqualTo(0);
        assertThat(ArrayUtils.lastIndexOf(new int[]{2, 3, 4}, 3)).isEqualTo(1);
        assertThat(ArrayUtils.lastIndexOf(new int[]{2, 3, 4}, 4)).isEqualTo(2);
        assertThat(ArrayUtils.lastIndexOf(new int[]{2, 3, 2, 3}, 3)).isEqualTo(3);
    }

    @Test
    public void testLastIndexOfLong() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.lastIndexOf((long[]) null, 0L));

        assertThat(ArrayUtils.lastIndexOf(new long[]{}, 1L)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new long[]{1L}, 2L)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new long[]{-1L}, -1L)).isEqualTo(0);
        assertThat(ArrayUtils.lastIndexOf(new long[]{2L, 3L, 4L}, 1L)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new long[]{2L, 3L, 4L}, 2L)).isEqualTo(0);
        assertThat(ArrayUtils.lastIndexOf(new long[]{2L, 3L, 4L}, 3L)).isEqualTo(1);
        assertThat(ArrayUtils.lastIndexOf(new long[]{2L, 3L, 4L}, 4L)).isEqualTo(2);
        assertThat(ArrayUtils.lastIndexOf(new long[]{2L, 3L, 2L, 3L}, 3L)).isEqualTo(3);
    }

    @Test
    public void testLastIndexOfFloat() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.lastIndexOf((float[]) null, 0f));

        assertThat(ArrayUtils.lastIndexOf(new float[]{}, 1f)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new float[]{1f}, 2f)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new float[]{-1f}, -1f)).isEqualTo(0);
        assertThat(ArrayUtils.lastIndexOf(new float[]{2f, 3f, 4f}, 1f)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new float[]{2f, 3f, 4f}, 2f)).isEqualTo(0);
        assertThat(ArrayUtils.lastIndexOf(new float[]{2f, 3f, 4f}, 3f)).isEqualTo(1);
        assertThat(ArrayUtils.lastIndexOf(new float[]{2f, 3f, 4f}, 4f)).isEqualTo(2);
        assertThat(ArrayUtils.lastIndexOf(new float[]{2f, 3f, 2f, 3f}, 3f)).isEqualTo(3);

        for (float value : SpecialFloats) {
            assertThat(ArrayUtils.lastIndexOf(new float[]{value, 5f}, value))
                .withFailMessage(Float.toString(value))
                .isEqualTo(0);
        }
    }

    @Test
    public void testLastIndexOfDouble() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.lastIndexOf((double[]) null, 0d));

        assertThat(ArrayUtils.lastIndexOf(new double[]{}, 1d)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new double[]{1f}, 2d)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new double[]{-1f}, -1d)).isEqualTo(0);
        assertThat(ArrayUtils.lastIndexOf(new double[]{2d, 3d, 4d}, 1d)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new double[]{2d, 3d, 4d}, 2d)).isEqualTo(0);
        assertThat(ArrayUtils.lastIndexOf(new double[]{2d, 3d, 4d}, 3d)).isEqualTo(1);
        assertThat(ArrayUtils.lastIndexOf(new double[]{2d, 3d, 4d}, 4d)).isEqualTo(2);
        assertThat(ArrayUtils.lastIndexOf(new double[]{2d, 3d, 2d, 3d}, 3d)).isEqualTo(3);

        for (double value : SpecialDoubles) {
            assertThat(ArrayUtils.lastIndexOf(new double[]{value, 5d}, value))
                .withFailMessage(Double.toString(value))
                .isEqualTo(0);
        }
    }

    @Test
    public void testLastIndexOfChar() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.lastIndexOf((char[]) null, (char) 0));

        assertThat(ArrayUtils.lastIndexOf(new char[]{}, (char) 1)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new char[]{1}, (char) 2)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new char[]{(char) -1}, (char) -1)).isEqualTo(0);
        assertThat(ArrayUtils.lastIndexOf(new char[]{2, 3, 4}, (char) 1)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new char[]{2, 3, 4}, (char) 2)).isEqualTo(0);
        assertThat(ArrayUtils.lastIndexOf(new char[]{2, 3, 4}, (char) 3)).isEqualTo(1);
        assertThat(ArrayUtils.lastIndexOf(new char[]{2, 3, 4}, (char) 4)).isEqualTo(2);
        assertThat(ArrayUtils.lastIndexOf(new char[]{(char) 2, (char) 3, (char) 2, (char) 3}, (char) 3)).isEqualTo(3);
    }

    @Test
    public void testLastIndexOfBoolean() {
        assertThatNullPointerException()
            .isThrownBy(() -> ArrayUtils.lastIndexOf((boolean[]) null, false));

        assertThat(ArrayUtils.lastIndexOf(new boolean[]{}, false)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new boolean[]{false}, true)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new boolean[]{false, false}, true)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new boolean[]{false}, false)).isEqualTo(0);
        assertThat(ArrayUtils.lastIndexOf(new boolean[]{false, true}, false)).isEqualTo(0);
        assertThat(ArrayUtils.lastIndexOf(new boolean[]{false, true}, true)).isEqualTo(1);
        assertThat(ArrayUtils.lastIndexOf(new boolean[]{true, true, false}, true)).isEqualTo(1);
    }

    // endregion

}
