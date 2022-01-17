package be.twofold.common;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

@SuppressWarnings("ConstantConditions")
class CollectionUtilsTest {

    @Test
    void testConstructor() {
        TestUtils.testConstructor(CollectionUtils.class);
    }

    @Test
    void testIsEmptyCollectionNull() {
        assertThat(CollectionUtils.isEmpty((Collection<?>) null)).isTrue();
    }

    @Test
    void testIsEmptyCollectionEmpty() {
        assertThat(CollectionUtils.isEmpty(Collections.emptyList())).isTrue();
    }

    @Test
    void testIsEmptyCollectionNotEmpty() {
        assertThat(CollectionUtils.isEmpty(Collections.singletonList("foo"))).isFalse();
    }

    @Test
    void testIsEmptyMapNull() {
        assertThat(CollectionUtils.isEmpty((Map<?, ?>) null)).isTrue();
    }

    @Test
    void testIsEmptyMapEmpty() {
        assertThat(CollectionUtils.isEmpty(Collections.emptyMap())).isTrue();
    }

    @Test
    void testIsEmptyMapNotEmpty() {
        assertThat(CollectionUtils.isEmpty(Collections.singletonMap("foo", "bar"))).isFalse();
    }

}
