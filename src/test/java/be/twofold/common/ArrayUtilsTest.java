package be.twofold.common;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

public class ArrayUtilsTest {

    @Test
    public void testContainsByte() {
        assertThat(ArrayUtils.contains(new byte[0], (byte) 1)).isFalse();
        assertThat(ArrayUtils.contains(new byte[]{1}, (byte) 2)).isFalse();
        assertThat(ArrayUtils.contains(new byte[]{-1}, (byte) -1)).isTrue();
        assertThat(ArrayUtils.contains(new byte[]{2, 3, 4}, (byte) 1)).isFalse();
        assertThat(ArrayUtils.contains(new byte[]{2, 3, 4}, (byte) 2)).isTrue();
        assertThat(ArrayUtils.contains(new byte[]{2, 3, 4}, (byte) 3)).isTrue();
        assertThat(ArrayUtils.contains(new byte[]{2, 3, 4}, (byte) 4)).isTrue();
        assertThat(ArrayUtils.contains(new byte[]{2, 3, 2, 3}, (byte) 3)).isTrue();
    }

    @Test
    public void testContainsShort() {
        assertThat(ArrayUtils.contains(new short[0], (short) 1)).isFalse();
        assertThat(ArrayUtils.contains(new short[]{1}, (short) 2)).isFalse();
        assertThat(ArrayUtils.contains(new short[]{-1}, (short) -1)).isTrue();
        assertThat(ArrayUtils.contains(new short[]{2, 3, 4}, (short) 1)).isFalse();
        assertThat(ArrayUtils.contains(new short[]{2, 3, 4}, (short) 2)).isTrue();
        assertThat(ArrayUtils.contains(new short[]{2, 3, 4}, (short) 3)).isTrue();
        assertThat(ArrayUtils.contains(new short[]{2, 3, 4}, (short) 4)).isTrue();
        assertThat(ArrayUtils.contains(new short[]{2, 3, 2, 3}, (short) 3)).isTrue();
    }

    @Test
    public void testContainsInt() {
        assertThat(ArrayUtils.contains(new int[0], 1)).isFalse();
        assertThat(ArrayUtils.contains(new int[]{1}, 2)).isFalse();
        assertThat(ArrayUtils.contains(new int[]{-1}, -1)).isTrue();
        assertThat(ArrayUtils.contains(new int[]{2, 3, 4}, 1)).isFalse();
        assertThat(ArrayUtils.contains(new int[]{2, 3, 4}, 2)).isTrue();
        assertThat(ArrayUtils.contains(new int[]{2, 3, 4}, 3)).isTrue();
        assertThat(ArrayUtils.contains(new int[]{2, 3, 4}, 4)).isTrue();
        assertThat(ArrayUtils.contains(new int[]{2, 3, 2, 3}, 3)).isTrue();
    }

    @Test
    public void testContainsLong() {
        assertThat(ArrayUtils.contains(new long[0], 1L)).isFalse();
        assertThat(ArrayUtils.contains(new long[]{1L}, 2L)).isFalse();
        assertThat(ArrayUtils.contains(new long[]{-1L}, -1L)).isTrue();
        assertThat(ArrayUtils.contains(new long[]{2L, 3L, 4L}, 1L)).isFalse();
        assertThat(ArrayUtils.contains(new long[]{2L, 3L, 4L}, 2L)).isTrue();
        assertThat(ArrayUtils.contains(new long[]{2L, 3L, 4L}, 3L)).isTrue();
        assertThat(ArrayUtils.contains(new long[]{2L, 3L, 4L}, 4L)).isTrue();
        assertThat(ArrayUtils.contains(new long[]{2L, 3L, 2L, 3L}, 3L)).isTrue();
    }

    @Test
    public void testContainsFloat() {
        assertThat(ArrayUtils.contains(new float[0], 1f)).isFalse();
        assertThat(ArrayUtils.contains(new float[]{1f}, 2f)).isFalse();
        assertThat(ArrayUtils.contains(new float[]{-1f}, -1f)).isTrue();
        assertThat(ArrayUtils.contains(new float[]{2f, 3f, 4f}, 1f)).isFalse();
        assertThat(ArrayUtils.contains(new float[]{2f, 3f, 4f}, 2f)).isTrue();
        assertThat(ArrayUtils.contains(new float[]{2f, 3f, 4f}, 3f)).isTrue();
        assertThat(ArrayUtils.contains(new float[]{2f, 3f, 4f}, 4f)).isTrue();
        assertThat(ArrayUtils.contains(new float[]{2f, 3f, 2f, 3f}, 3f)).isTrue();
        assertThat(ArrayUtils.contains(new float[]{5f, Float.NaN}, Float.NaN)).isTrue();
    }

    @Test
    public void testContainsDouble() {
        assertThat(ArrayUtils.contains(new double[0], 1d)).isFalse();
        assertThat(ArrayUtils.contains(new double[]{1d}, 2d)).isFalse();
        assertThat(ArrayUtils.contains(new double[]{-1d}, -1d)).isTrue();
        assertThat(ArrayUtils.contains(new double[]{2d, 3d, 4d}, 1d)).isFalse();
        assertThat(ArrayUtils.contains(new double[]{2d, 3d, 4d}, 2d)).isTrue();
        assertThat(ArrayUtils.contains(new double[]{2d, 3d, 4d}, 3d)).isTrue();
        assertThat(ArrayUtils.contains(new double[]{2d, 3d, 4d}, 4d)).isTrue();
        assertThat(ArrayUtils.contains(new double[]{2d, 3d, 2d, 3d}, 3d)).isTrue();
        assertThat(ArrayUtils.contains(new double[]{5f, Double.NaN}, Double.NaN)).isTrue();
    }

    @Test
    public void testContainsChar() {
        assertThat(ArrayUtils.contains(new char[0], 'a')).isFalse();
        assertThat(ArrayUtils.contains(new char[]{'a'}, 'b')).isFalse();
        assertThat(ArrayUtils.contains(new char[]{'a'}, 'a')).isTrue();
        assertThat(ArrayUtils.contains(new char[]{'b', 'c', 'd'}, 'a')).isFalse();
        assertThat(ArrayUtils.contains(new char[]{'b', 'c', 'd'}, 'b')).isTrue();
        assertThat(ArrayUtils.contains(new char[]{'b', 'c', 'd'}, 'c')).isTrue();
        assertThat(ArrayUtils.contains(new char[]{'b', 'c', 'd'}, 'd')).isTrue();
        assertThat(ArrayUtils.contains(new char[]{'b', 'c', 'b', 'c'}, 'c')).isTrue();
    }

    @Test
    public void testContainsBoolean() {
        assertThat(ArrayUtils.contains(new boolean[0], false)).isFalse();
        assertThat(ArrayUtils.contains(new boolean[]{false}, true)).isFalse();
        assertThat(ArrayUtils.contains(new boolean[]{false}, false)).isTrue();
        assertThat(ArrayUtils.contains(new boolean[]{true, false}, true)).isTrue();
        assertThat(ArrayUtils.contains(new boolean[]{true, false}, false)).isTrue();
        assertThat(ArrayUtils.contains(new boolean[]{true, false, true, false}, false)).isTrue();
    }

    @Test
    public void testContainsObject() {
        assertThat(ArrayUtils.contains(new String[0], null)).isFalse();
        assertThat(ArrayUtils.contains(new String[0], "a")).isFalse();
        assertThat(ArrayUtils.contains(new String[]{"a"}, null)).isFalse();
        assertThat(ArrayUtils.contains(new String[]{"a"}, "b")).isFalse();
        assertThat(ArrayUtils.contains(new String[]{"a"}, "a")).isTrue();
        assertThat(ArrayUtils.contains(new String[]{null}, "a")).isFalse();
        assertThat(ArrayUtils.contains(new String[]{null}, null)).isTrue();
        assertThat(ArrayUtils.contains(new String[]{"b", "c", null, "d"}, "a")).isFalse();
        assertThat(ArrayUtils.contains(new String[]{"b", "c", null, "d"}, "b")).isTrue();
        assertThat(ArrayUtils.contains(new String[]{"b", "c", null, "d"}, "c")).isTrue();
        assertThat(ArrayUtils.contains(new String[]{"b", "c", null, "d"}, "d")).isTrue();
        assertThat(ArrayUtils.contains(new String[]{"b", "c", null, "d"}, null)).isTrue();
        assertThat(ArrayUtils.contains(new String[]{"b", "c", "b", "c"}, "c")).isTrue();
    }


    @Test
    public void testIndexOfByte() {
        assertThat(ArrayUtils.indexOf(new byte[0], (byte) 1)).isEqualTo(-1);
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
        assertThat(ArrayUtils.indexOf(new short[0], (short) 1)).isEqualTo(-1);
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
        assertThat(ArrayUtils.indexOf(new int[0], 1)).isEqualTo(-1);
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
        assertThat(ArrayUtils.indexOf(new long[0], 1L)).isEqualTo(-1);
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
        assertThat(ArrayUtils.indexOf(new float[0], 1f)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new float[]{1f}, 2f)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new float[]{-1f}, -1f)).isEqualTo(0);
        assertThat(ArrayUtils.indexOf(new float[]{2f, 3f, 4f}, 1f)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new float[]{2f, 3f, 4f}, 2f)).isEqualTo(0);
        assertThat(ArrayUtils.indexOf(new float[]{2f, 3f, 4f}, 3f)).isEqualTo(1);
        assertThat(ArrayUtils.indexOf(new float[]{2f, 3f, 4f}, 4f)).isEqualTo(2);
        assertThat(ArrayUtils.indexOf(new float[]{2f, 3f, 2f, 3f}, 3f)).isEqualTo(1);
        assertThat(ArrayUtils.indexOf(new float[]{5f, Float.NaN}, Float.NaN)).isEqualTo(1);
    }

    @Test
    public void testIndexOfDouble() {
        assertThat(ArrayUtils.indexOf(new double[0], 1d)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new double[]{1d}, 2d)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new double[]{-1d}, -1d)).isEqualTo(0);
        assertThat(ArrayUtils.indexOf(new double[]{2d, 3d, 4d}, 1d)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new double[]{2d, 3d, 4d}, 2d)).isEqualTo(0);
        assertThat(ArrayUtils.indexOf(new double[]{2d, 3d, 4d}, 3d)).isEqualTo(1);
        assertThat(ArrayUtils.indexOf(new double[]{2d, 3d, 4d}, 4d)).isEqualTo(2);
        assertThat(ArrayUtils.indexOf(new double[]{2d, 3d, 2d, 3d}, 3d)).isEqualTo(1);
        assertThat(ArrayUtils.indexOf(new double[]{5f, Double.NaN}, Double.NaN)).isEqualTo(1);
    }

    @Test
    public void testIndexOfChar() {
        assertThat(ArrayUtils.indexOf(new char[0], 'a')).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new char[]{'a'}, 'b')).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new char[]{'a'}, 'a')).isEqualTo(0);
        assertThat(ArrayUtils.indexOf(new char[]{'b', 'c', 'd'}, 'a')).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new char[]{'b', 'c', 'd'}, 'b')).isEqualTo(0);
        assertThat(ArrayUtils.indexOf(new char[]{'b', 'c', 'd'}, 'c')).isEqualTo(1);
        assertThat(ArrayUtils.indexOf(new char[]{'b', 'c', 'd'}, 'd')).isEqualTo(2);
        assertThat(ArrayUtils.indexOf(new char[]{'b', 'c', 'b', 'c'}, 'c')).isEqualTo(1);
    }

    @Test
    public void testIndexOfBoolean() {
        assertThat(ArrayUtils.indexOf(new boolean[0], false)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new boolean[]{false}, true)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new boolean[]{false}, false)).isEqualTo(0);
        assertThat(ArrayUtils.indexOf(new boolean[]{true, false}, true)).isEqualTo(0);
        assertThat(ArrayUtils.indexOf(new boolean[]{true, false}, false)).isEqualTo(1);
        assertThat(ArrayUtils.indexOf(new boolean[]{true, false, true, false}, false)).isEqualTo(1);
    }

    @Test
    public void testIndexOfObject() {
        assertThat(ArrayUtils.indexOf(new String[0], null)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new String[0], "a")).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new String[]{"a"}, null)).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new String[]{"a"}, "b")).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new String[]{"a"}, "a")).isEqualTo(0);
        assertThat(ArrayUtils.indexOf(new String[]{null}, "a")).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new String[]{null}, null)).isEqualTo(0);
        assertThat(ArrayUtils.indexOf(new String[]{"b", "c", null, "d"}, "a")).isEqualTo(-1);
        assertThat(ArrayUtils.indexOf(new String[]{"b", "c", null, "d"}, "b")).isEqualTo(0);
        assertThat(ArrayUtils.indexOf(new String[]{"b", "c", null, "d"}, "c")).isEqualTo(1);
        assertThat(ArrayUtils.indexOf(new String[]{"b", "c", null, "d"}, "d")).isEqualTo(3);
        assertThat(ArrayUtils.indexOf(new String[]{"b", "c", null, "d"}, null)).isEqualTo(2);
        assertThat(ArrayUtils.indexOf(new String[]{"b", "c", "b", "c"}, "c")).isEqualTo(1);
    }


    @Test
    public void testLastIndexOfByte() {
        assertThat(ArrayUtils.lastIndexOf(new byte[0], (byte) 1)).isEqualTo(-1);
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
        assertThat(ArrayUtils.lastIndexOf(new short[0], (short) 1)).isEqualTo(-1);
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
        assertThat(ArrayUtils.lastIndexOf(new int[0], 1)).isEqualTo(-1);
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
        assertThat(ArrayUtils.lastIndexOf(new long[0], 1L)).isEqualTo(-1);
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
        assertThat(ArrayUtils.lastIndexOf(new float[0], 1f)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new float[]{1f}, 2f)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new float[]{-1f}, -1f)).isEqualTo(0);
        assertThat(ArrayUtils.lastIndexOf(new float[]{2f, 3f, 4f}, 1f)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new float[]{2f, 3f, 4f}, 2f)).isEqualTo(0);
        assertThat(ArrayUtils.lastIndexOf(new float[]{2f, 3f, 4f}, 3f)).isEqualTo(1);
        assertThat(ArrayUtils.lastIndexOf(new float[]{2f, 3f, 4f}, 4f)).isEqualTo(2);
        assertThat(ArrayUtils.lastIndexOf(new float[]{2f, 3f, 2f, 3f}, 3f)).isEqualTo(3);
        assertThat(ArrayUtils.lastIndexOf(new float[]{Float.NaN, 5f}, Float.NaN)).isEqualTo(0);
    }

    @Test
    public void testLastIndexOfDouble() {
        assertThat(ArrayUtils.lastIndexOf(new double[0], 1d)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new double[]{1d}, 2d)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new double[]{-1d}, -1d)).isEqualTo(0);
        assertThat(ArrayUtils.lastIndexOf(new double[]{2d, 3d, 4d}, 1d)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new double[]{2d, 3d, 4d}, 2d)).isEqualTo(0);
        assertThat(ArrayUtils.lastIndexOf(new double[]{2d, 3d, 4d}, 3d)).isEqualTo(1);
        assertThat(ArrayUtils.lastIndexOf(new double[]{2d, 3d, 4d}, 4d)).isEqualTo(2);
        assertThat(ArrayUtils.lastIndexOf(new double[]{2d, 3d, 2d, 3d}, 3d)).isEqualTo(3);
        assertThat(ArrayUtils.lastIndexOf(new double[]{Double.NaN, 5f}, Double.NaN)).isEqualTo(0);
    }

    @Test
    public void testLastIndexOfChar() {
        assertThat(ArrayUtils.lastIndexOf(new char[0], 'a')).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new char[]{'a'}, 'b')).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new char[]{'a'}, 'a')).isEqualTo(0);
        assertThat(ArrayUtils.lastIndexOf(new char[]{'b', 'c', 'd'}, 'a')).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new char[]{'b', 'c', 'd'}, 'b')).isEqualTo(0);
        assertThat(ArrayUtils.lastIndexOf(new char[]{'b', 'c', 'd'}, 'c')).isEqualTo(1);
        assertThat(ArrayUtils.lastIndexOf(new char[]{'b', 'c', 'd'}, 'd')).isEqualTo(2);
        assertThat(ArrayUtils.lastIndexOf(new char[]{'b', 'c', 'b', 'c'}, 'c')).isEqualTo(3);
    }

    @Test
    public void testLastIndexOfBoolean() {
        assertThat(ArrayUtils.lastIndexOf(new boolean[0], false)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new boolean[]{false}, true)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new boolean[]{false}, false)).isEqualTo(0);
        assertThat(ArrayUtils.lastIndexOf(new boolean[]{true, false}, true)).isEqualTo(0);
        assertThat(ArrayUtils.lastIndexOf(new boolean[]{true, false}, false)).isEqualTo(1);
        assertThat(ArrayUtils.lastIndexOf(new boolean[]{true, false, true, false}, false)).isEqualTo(3);
    }

    @Test
    public void testLastIndexOfObject() {
        assertThat(ArrayUtils.lastIndexOf(new String[0], null)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new String[0], "a")).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new String[]{"a"}, null)).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new String[]{"a"}, "b")).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new String[]{"a"}, "a")).isEqualTo(0);
        assertThat(ArrayUtils.lastIndexOf(new String[]{null}, "a")).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new String[]{null}, null)).isEqualTo(0);
        assertThat(ArrayUtils.lastIndexOf(new String[]{"b", "c", null, "d"}, "a")).isEqualTo(-1);
        assertThat(ArrayUtils.lastIndexOf(new String[]{"b", "c", null, "d"}, "b")).isEqualTo(0);
        assertThat(ArrayUtils.lastIndexOf(new String[]{"b", "c", null, "d"}, "c")).isEqualTo(1);
        assertThat(ArrayUtils.lastIndexOf(new String[]{"b", "c", null, "d"}, "d")).isEqualTo(3);
        assertThat(ArrayUtils.lastIndexOf(new String[]{"b", "c", null, "d"}, null)).isEqualTo(2);
        assertThat(ArrayUtils.lastIndexOf(new String[]{"b", "c", "b", "c"}, "c")).isEqualTo(3);
    }

}
