package be.twofold.common.func;

import nl.jqno.equalsverifier.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

public class OptionTest {

    @Test
    @DisplayName("Some should be defined")
    public void testSomeDefined() {
        Option<Object> option = Option.some(new Object());
        assertThat(option.isDefined()).isTrue();
        assertThat(option.isEmpty()).isFalse();
    }

    @Test
    @DisplayName("None should be empty")
    public void testNoneEmpty() {
        Option<Object> option = Option.none();
        assertThat(option.isDefined()).isFalse();
        assertThat(option.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Some.get should return value")
    public void testSomeGet() {
        Option<String> option = Option.some("foo");
        assertThat(option.get()).isEqualTo("foo");
    }

    @Test
    @DisplayName("None.get should throw NoSuchElementException")
    public void testNone() {
        Option<Object> option = Option.none();
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(option::get)
            .withMessage("No value present");
    }

    @Test
    @DisplayName("of should return some with non-null value")
    public void testOf() {
        Option<Object> option = Option.of(new Object());
        assertThat(option.isDefined()).isTrue();
    }

    @Test
    @DisplayName("of should return none with null value")
    public void testOfNull() {
        Option<Object> option = Option.of(null);
        assertThat(option).isEqualTo(Option.none());
    }

    @Test
    @DisplayName("Some should have valid equals and hashCode")
    public void testSomeEqualsAndHashCode() {
        EqualsVerifier.forClass(Option.Some.class)
            .verify();
    }

    @Test
    @DisplayName("None should have valid equals and hashCode")
    public void testNoneEqualsAndHashCode() {
        EqualsVerifier.forClass(Option.None.class)
            .suppress(Warning.INHERITED_DIRECTLY_FROM_OBJECT)
            .verify();
    }

    @Test
    @DisplayName("Some should have a toString")
    public void testSomeToString() {
        Option<String> option = Option.some("foo");
        assertThat(option).hasToString("Some(foo)");
    }

    @Test
    @DisplayName("None should have a toString")
    public void testNoneToString() {
        Option<Object> option = Option.none();
        assertThat(option).hasToString("None");
    }

}
