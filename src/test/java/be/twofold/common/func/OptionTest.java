package be.twofold.common.func;

import nl.jqno.equalsverifier.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

public class OptionTest {

    @Test
    public void testEqualsAndHashCode() {
        EqualsVerifier.forClasses(Option.Some.class, Option.None.class)
            .verify();
    }

    @Test
    public void testSome() {
        Option<String> option = Option.some("some");
        assertThat(option.isPresent()).isTrue();
        assertThat(option.isAbsent()).isFalse();
        assertThat(option.get()).isEqualTo("some");
        assertThat(option).hasToString("Some(some)");
    }

    @Test
    public void testNone() {
        Option<?> option = Option.none();
        assertThat(option.isPresent()).isFalse();
        assertThat(option.isAbsent()).isTrue();
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(option::get);
        assertThat(option).hasToString("None");
    }

    @Test
    public void testOf() {
        assertThat(Option.of("some")).isInstanceOf(Option.Some.class);
        assertThat(Option.of(null)).isInstanceOf(Option.None.class);
    }

}
