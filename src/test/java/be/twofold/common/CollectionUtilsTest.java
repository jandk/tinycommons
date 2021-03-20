package be.twofold.common;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

@SuppressWarnings("ConstantConditions")
public class CollectionUtilsTest {

    @Test
    public void testIsEmptyCollectionNull() {
        assertThat(CollectionUtils.isEmpty((Collection<?>) null)).isTrue();
    }

    @Test
    public void testIsEmptyCollectionEmpty() {
        assertThat(CollectionUtils.isEmpty(Collections.emptyList())).isTrue();
    }

    @Test
    public void testIsEmptyCollectionNotEmpty() {
        assertThat(CollectionUtils.isEmpty(Collections.singletonList("foo"))).isFalse();
    }

    @Test
    public void testIsEmptyMapNull() {
        assertThat(CollectionUtils.isEmpty((Map<?, ?>) null)).isTrue();
    }

    @Test
    public void testIsEmptyMapEmpty() {
        assertThat(CollectionUtils.isEmpty(Collections.emptyMap())).isTrue();
    }

    @Test
    public void testIsEmptyMapNotEmpty() {
        assertThat(CollectionUtils.isEmpty(Collections.singletonMap("foo", "bar"))).isFalse();
    }

}
