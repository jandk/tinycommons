package be.twofold.common;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

class NumberUtilsTest {

    @Test
    void testConstructor() {
        TestUtils.testConstructor(NumberUtils.class);
    }

    // region testTryParse

    @Test
    void testTryParseInt() {
        assertThat(NumberUtils.tryParseInt(null)).isEmpty();
        assertThat(NumberUtils.tryParseInt("foo")).isEmpty();
        assertThat(NumberUtils.tryParseInt("123456789")).hasValue(123456789);
    }

    @Test
    void testTryParseLong() {
        assertThat(NumberUtils.tryParseLong(null)).isEmpty();
        assertThat(NumberUtils.tryParseLong("foo")).isEmpty();
        assertThat(NumberUtils.tryParseLong("123456789012")).hasValue(123456789012L);
    }

    @Test
    void testTryParseDouble() {
        assertThat(NumberUtils.tryParseDouble(null)).isEmpty();
        assertThat(NumberUtils.tryParseDouble("foo")).isEmpty();
        assertThat(NumberUtils.tryParseDouble("123.456")).hasValue(123.456);
    }

    // endregion

    // region testMin

    @Test
    void testMinByte() {
        assertThatNullPointerException()
            .isThrownBy(() -> NumberUtils.min((byte[]) null));
        assertThatIllegalArgumentException()
            .isThrownBy(() -> NumberUtils.min(new byte[0]));

        assertThat(NumberUtils.min((byte) 1)).isEqualTo((byte) 1);
        assertThat(NumberUtils.min((byte) -2, (byte) -1, (byte) 0, (byte) 1, (byte) 2)).isEqualTo((byte) -2);
        assertThat(NumberUtils.min((byte) 0, (byte) -1, (byte) -2, (byte) 1, (byte) 2)).isEqualTo((byte) -2);
    }

    @Test
    void testMinShort() {
        assertThatNullPointerException()
            .isThrownBy(() -> NumberUtils.min((short[]) null));
        assertThatIllegalArgumentException()
            .isThrownBy(() -> NumberUtils.min(new short[0]));

        assertThat(NumberUtils.min((short) 1)).isEqualTo((short) 1);
        assertThat(NumberUtils.min((short) -2, (short) -1, (short) 0, (short) 1, (short) 2)).isEqualTo((short) -2);
        assertThat(NumberUtils.min((short) 0, (short) -1, (short) -2, (short) 1, (short) 2)).isEqualTo((short) -2);
    }

    @Test
    void testMinInt() {
        assertThatNullPointerException()
            .isThrownBy(() -> NumberUtils.min((int[]) null));
        assertThatIllegalArgumentException()
            .isThrownBy(() -> NumberUtils.min(new int[0]));

        assertThat(NumberUtils.min(1)).isEqualTo(1);
        assertThat(NumberUtils.min(-2, -1, 0, 1, 2)).isEqualTo(-2);
        assertThat(NumberUtils.min(0, -1, -2, 1, 2)).isEqualTo(-2);
    }

    @Test
    void testMinLong() {
        assertThatNullPointerException()
            .isThrownBy(() -> NumberUtils.min((long[]) null));
        assertThatIllegalArgumentException()
            .isThrownBy(() -> NumberUtils.min(new long[0]));

        assertThat(NumberUtils.min(1L)).isEqualTo(1L);
        assertThat(NumberUtils.min(-2L, -1L, 0L, 1L, 2L)).isEqualTo(-2L);
        assertThat(NumberUtils.min(0L, -1L, -2L, 1L, 2L)).isEqualTo(-2L);
    }

    @Test
    void testMinFloat() {
        assertThatNullPointerException()
            .isThrownBy(() -> NumberUtils.min((float[]) null));
        assertThatIllegalArgumentException()
            .isThrownBy(() -> NumberUtils.min(new float[0]));

        assertThat(NumberUtils.min(1f)).isEqualTo(1f);
        assertThat(NumberUtils.min(-2f, -1f, 0f, 1f, 2f)).isEqualTo(-2f);
        assertThat(NumberUtils.min(0f, -1f, -2f, 1f, 2f)).isEqualTo(-2f);
    }

    @Test
    void testMinDouble() {
        assertThatNullPointerException()
            .isThrownBy(() -> NumberUtils.min((double[]) null));
        assertThatIllegalArgumentException()
            .isThrownBy(() -> NumberUtils.min(new double[0]));

        assertThat(NumberUtils.min(1d)).isEqualTo(1d);
        assertThat(NumberUtils.min(-2d, -1d, 0d, 1d, 2d)).isEqualTo(-2d);
        assertThat(NumberUtils.min(0d, -1d, -2d, 1d, 2d)).isEqualTo(-2d);
    }

    // endregion

    // region testMax

    @Test
    void testMaxByte() {
        assertThatNullPointerException()
            .isThrownBy(() -> NumberUtils.max((byte[]) null));
        assertThatIllegalArgumentException()
            .isThrownBy(() -> NumberUtils.max(new byte[0]));

        assertThat(NumberUtils.max((byte) 1)).isEqualTo((byte) 1);
        assertThat(NumberUtils.max((byte) -2, (byte) -1, (byte) 0, (byte) 1, (byte) 2)).isEqualTo((byte) 2);
        assertThat(NumberUtils.max((byte) -2, (byte) -1, (byte) 2, (byte) 0, (byte) 1)).isEqualTo((byte) 2);
    }

    @Test
    void testMaxShort() {
        assertThatNullPointerException()
            .isThrownBy(() -> NumberUtils.max((short[]) null));
        assertThatIllegalArgumentException()
            .isThrownBy(() -> NumberUtils.max(new short[0]));

        assertThat(NumberUtils.max((short) 1)).isEqualTo((short) 1);
        assertThat(NumberUtils.max((short) -2, (short) -1, (short) 0, (short) 1, (short) 2)).isEqualTo((short) 2);
        assertThat(NumberUtils.max((short) -2, (short) -1, (short) 2, (short) 0, (short) 1)).isEqualTo((short) 2);
    }

    @Test
    void testMaxInt() {
        assertThatNullPointerException()
            .isThrownBy(() -> NumberUtils.max((int[]) null));
        assertThatIllegalArgumentException()
            .isThrownBy(() -> NumberUtils.max(new int[0]));

        assertThat(NumberUtils.max(1)).isEqualTo(1);
        assertThat(NumberUtils.max(-2, -1, 0, 1, 2)).isEqualTo(2);
        assertThat(NumberUtils.max(-2, -1, 2, 0, 1)).isEqualTo(2);
    }

    @Test
    void testMaxLong() {
        assertThatNullPointerException()
            .isThrownBy(() -> NumberUtils.max((long[]) null));
        assertThatIllegalArgumentException()
            .isThrownBy(() -> NumberUtils.max(new long[0]));

        assertThat(NumberUtils.max(1L)).isEqualTo(1L);
        assertThat(NumberUtils.max(-2L, -1L, 0L, 1L, 2L)).isEqualTo(2L);
        assertThat(NumberUtils.max(-2L, -1L, 2L, 0L, 1L)).isEqualTo(2L);
    }

    @Test
    void testMaxFloat() {
        assertThatNullPointerException()
            .isThrownBy(() -> NumberUtils.max((float[]) null));
        assertThatIllegalArgumentException()
            .isThrownBy(() -> NumberUtils.max(new float[0]));

        assertThat(NumberUtils.max(1f)).isEqualTo(1f);
        assertThat(NumberUtils.max(-2f, -1f, 0f, 1f, 2f)).isEqualTo(2f);
        assertThat(NumberUtils.max(-2f, -1f, 2f, 0f, 1f)).isEqualTo(2f);
    }

    @Test
    void testMaxDouble() {
        assertThatNullPointerException()
            .isThrownBy(() -> NumberUtils.max((double[]) null));
        assertThatIllegalArgumentException()
            .isThrownBy(() -> NumberUtils.max(new double[0]));

        assertThat(NumberUtils.max(1d)).isEqualTo(1d);
        assertThat(NumberUtils.max(-2d, -1d, 0d, 1d, 2d)).isEqualTo(2d);
        assertThat(NumberUtils.max(-2d, -1d, 2d, 0d, 1d)).isEqualTo(2d);
    }

    // endregion

}
