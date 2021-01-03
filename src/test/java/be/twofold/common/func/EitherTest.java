package be.twofold.common.func;

import nl.jqno.equalsverifier.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

public class EitherTest {

    @Test
    public void testEqualsAndHashCode() {
        EqualsVerifier.forClasses(Either.Left.class, Either.Right.class)
            .verify();
    }

    @Test
    public void testLeft() {
        Either<String, ?> either = Either.left("left");
        assertThat(either.isLeft()).isTrue();
        assertThat(either.isRight()).isFalse();
        assertThat(either.getLeft()).isEqualTo("left");
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(either::getRight);
        assertThat(either).hasToString("Left(left)");
    }

    @Test
    public void testRight() {
        Either<?, String> either = Either.right("right");
        assertThat(either.isLeft()).isFalse();
        assertThat(either.isRight()).isTrue();
        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(either::getLeft);
        assertThat(either.getRight()).isEqualTo("right");
        assertThat(either).hasToString("Right(right)");
    }

}
