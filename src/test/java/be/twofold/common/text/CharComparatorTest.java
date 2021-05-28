package be.twofold.common.text;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

class CharComparatorTest {

    @Test
    void testNatural() {
        assertThat(compare(0x61, 0x20ac, CharComparator.natural())).isNegative();
        assertThat(compare(0x61, 0x10002, CharComparator.natural())).isNegative();
        assertThat(compare(0xff61, 0x10002, CharComparator.natural())).isPositive();
        assertThat(compare(0x10002, 0x23456, CharComparator.natural())).isNegative();
    }

    @Test
    void testCodePointOrder() {
        assertThat(compare(0x61, 0x20ac, CharComparator.codepointOrder())).isNegative();
        assertThat(compare(0x61, 0x10002, CharComparator.codepointOrder())).isNegative();
        assertThat(compare(0xff61, 0x10002, CharComparator.codepointOrder())).isNegative();
        assertThat(compare(0x10002, 0x23456, CharComparator.codepointOrder())).isNegative();
    }

    int compare(int cp1, int cp2, CharComparator charComparator) {
        String s1 = new String(Character.toChars(cp1));
        String s2 = new String(Character.toChars(cp2));

        return charComparator.toCharSequenceComparator().compare(s1, s2);
    }

}
