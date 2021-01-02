package be.twofold.common;

import java.lang.reflect.*;

import static org.assertj.core.api.Assertions.*;

public final class TestUtils {

    /**
     * Tests if the constructor throws an {@link UnsupportedOperationException}.
     * <p>
     * I didn't want to write anything like this,
     * but it's easier this way to satisfy JaCoCo
     */
    public static void testConstructor(Class<?> type) {
        Constructor<?>[] constructors = type.getDeclaredConstructors();
        assertThat(constructors).hasSize(1);

        Constructor<?> constructor = constructors[0];
        constructor.setAccessible(true);

        assertThat(Modifier.isPrivate(constructor.getModifiers())).isTrue();
        assertThatExceptionOfType(InvocationTargetException.class)
            .isThrownBy(constructor::newInstance)
            .withCauseInstanceOf(UnsupportedOperationException.class);
    }

}
