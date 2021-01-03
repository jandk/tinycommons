package be.twofold.common.func;

import nl.jqno.equalsverifier.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

public class TryTest {

    @Test
    public void testEqualsAndHashCode() {
        EqualsVerifier.forClasses(Try.Success.class, Try.Failure.class)
            .verify();
    }

    @Test
    public void testSuccess() {
        Try<String> tryInstance = Try.success("success");

        assertThat(tryInstance.isSuccess()).isTrue();
        assertThat(tryInstance.isFailure()).isFalse();

        assertThat(tryInstance.getResult()).isEqualTo("success");
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(tryInstance::getCause);

        assertThat(tryInstance).hasToString("Success(success)");
    }

    @Test
    public void testFailure() {
        Try<String> tryInstance = Try.failure(new Exception("failure"));

        assertThat(tryInstance.isSuccess()).isFalse();
        assertThat(tryInstance.isFailure()).isTrue();

        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(tryInstance::getResult);
        assertThat(tryInstance.getCause())
            .isInstanceOf(Exception.class)
            .hasMessage("failure");

        assertThat(tryInstance).hasToString("Failure(java.lang.Exception: failure)");
    }

}
