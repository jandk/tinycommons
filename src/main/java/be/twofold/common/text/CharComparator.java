package be.twofold.common.text;

import java.util.*;

@FunctionalInterface
public interface CharComparator extends Comparator<Character> {

    static CharComparator natural() {
        return Character::compare;
    }

    static CharComparator caseInsensitive() {
        return (c1, c2) -> {
            c1 = Character.toUpperCase(c1);
            c2 = Character.toUpperCase(c2);
            if (c1 == c2) return 0;

            c1 = Character.toLowerCase(c1);
            c2 = Character.toLowerCase(c2);
            if (c1 == c2) return 0;

            return c1 - c2;
        };
    }

    static CharComparator codepointOrder() {
        return (c1, c2) -> {
            if (c1 >= Character.MIN_SURROGATE && c2 >= Character.MIN_SURROGATE) {
                c1 += c1 <= Character.MAX_SURROGATE ? 0x2000 : -0x800;
                c2 += c2 <= Character.MAX_SURROGATE ? 0x2000 : -0x800;
            }

            return c1 - c2;
        };
    }

    int compare(char c1, char c2);

    @Override
    default int compare(Character c1, Character c2) {
        return compare((char) c1, (char) c2);
    }

    default Comparator<CharSequence> toCharSequenceComparator() {
        return (s1, s2) -> {
            int len1 = s1.length();
            int len2 = s2.length();
            for (int i = 0, lim = Math.min(len1, len2); i < lim; i++) {
                char c1 = s1.charAt(i);
                char c2 = s2.charAt(i);
                if (c1 != c2) {
                    return compare(c1, c2);
                }
            }
            return len1 - len2;
        };
    }

}
