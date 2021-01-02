package be.twofold.common;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

public class StringUtilsTest {

    @Test
    public void testConstructor() {
        TestUtils.testConstructor(StringUtils.class);
    }

    @Test
    public void testIsEmpty() {
        assertThat(StringUtils.isEmpty(null)).isTrue();
        assertThat(StringUtils.isEmpty("")).isTrue();
        assertThat(StringUtils.isEmpty("a")).isFalse();
    }

    @Test
    public void testIsBlank() {
        assertThat(StringUtils.isBlank(null)).isTrue();
        assertThat(StringUtils.isBlank("")).isTrue();
        assertThat(StringUtils.isBlank(" ")).isTrue();
        assertThat(StringUtils.isBlank(" a ")).isFalse();
    }

    @Test
    public void testNullToEmpty() {
        assertThat(StringUtils.nullToEmpty(null)).isEqualTo("");
        assertThat(StringUtils.nullToEmpty("")).isEqualTo("");
        assertThat(StringUtils.nullToEmpty("a")).isEqualTo("a");
    }

    @Test
    public void testEmptyToNull() {
        assertThat(StringUtils.emptyToNull(null)).isNull();
        assertThat(StringUtils.emptyToNull("")).isNull();
        assertThat(StringUtils.emptyToNull("a")).isNotNull().isEqualTo("a");
    }

    @Test
    public void testBlankToNull() {
        assertThat(StringUtils.blankToNull(null)).isNull();
        assertThat(StringUtils.blankToNull("")).isNull();
        assertThat(StringUtils.blankToNull(" ")).isNull();
        assertThat(StringUtils.blankToNull(" a ")).isNotNull().isEqualTo(" a ");
    }

}
