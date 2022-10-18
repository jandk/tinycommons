package be.twofold.common.func;

import nl.jqno.equalsverifier.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

public class ResultTest {

    @Test
    public void testEqualsAndHashCode() {
        EqualsVerifier.forClasses(Result.Success.class, Result.Failure.class)
            .verify();
    }

    @Test
    public void testSuccess() {
        Result<String> result = Result.success("success");

        assertThat(result.isSuccess()).isTrue();
        assertThat(result.isFailure()).isFalse();

        assertThat(result.getResult()).isEqualTo("success");
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(result::getCause);

        assertThat(result).hasToString("Success(success)");
    }

    @Test
    public void testFailure() {
        Result<String> result = Result.failure(new Exception("failure"));

        assertThat(result.isSuccess()).isFalse();
        assertThat(result.isFailure()).isTrue();

        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(result::getResult);
        assertThat(result.getCause())
            .isInstanceOf(Exception.class)
            .hasMessage("failure");

        assertThat(result).hasToString("Failure(java.lang.Exception: failure)");
    }

}
