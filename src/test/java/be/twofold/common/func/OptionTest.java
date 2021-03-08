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
    @DisplayName("of should return some with non-null value")
    public void testOfNonNull() {
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
    @DisplayName("ofOptional should throw when null")
    public void testOfOptionalNull() {
        assertThatNullPointerException()
            .isThrownBy(() -> Option.ofOptional(null));
    }

    @Test
    @DisplayName("ofOptional should return some when optional is present")
    public void testOfOptionalPresent() {
        Option<String> option = Option.ofOptional(Optional.of("foo"));
        assertThat(option).isEqualTo(Option.of("foo"));
    }

    @Test
    @DisplayName("ofOptional should return none when optional is empty")
    public void testOfOptionalEmpty() {
        Option<String> option = Option.ofOptional(Optional.empty());
        assertThat(option).isEqualTo(Option.none());
    }

    @Test
    @DisplayName("Some.get should return value")
    public void testSomeGet() {
        Option<String> option = Option.some("foo");
        assertThat(option.get()).isEqualTo("foo");
    }

    @Test
    @DisplayName("None.get should throw NoSuchElementException")
    public void testNoneGet() {
        Option<Object> option = Option.none();
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(option::get)
            .withMessage("No value present");
    }

    // region flatMap

    @Test
    @DisplayName("flatMap should throw on null")
    public void testFlatMapNull() {
        assertThatNullPointerException()
            .isThrownBy(() -> Option.of("foo").flatMap(null));
    }

    @Test
    @DisplayName("flatMap should not map none")
    public void testFlatMapNone() {
        Option<Integer> option = Option.<String>none()
            .flatMap(s -> Option.of(s.length()));

        assertThat(option).isEqualTo(Option.none());
    }

    @Test
    @DisplayName("flatMap should throw on null result")
    public void testFlatMapSomeNull() {
        assertThatNullPointerException()
            .isThrownBy(() -> Option.of("foo").flatMap(s -> null));
    }

    @Test
    @DisplayName("flatMap should map to none")
    public void testFlatMapSomeNone() {
        Option<Integer> option = Option.of("foo")
            .flatMap(s -> Option.none());

        assertThat(option).isEqualTo(Option.none());
    }

    @Test
    @DisplayName("flatMap should map to some")
    public void testFlatMapSomeSome() {
        Option<Integer> option = Option.of("foo")
            .flatMap(s -> Option.of(s.length()));

        assertThat(option).isEqualTo(Option.some(3));
    }

    // endregion

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
