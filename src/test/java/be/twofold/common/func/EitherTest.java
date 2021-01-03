package be.twofold.common.func;

import nl.jqno.equalsverifier.*;
import org.junit.jupiter.api.*;

public class EitherTest {

    @Test
    public void testEqualsAndHashCode() {
        EqualsVerifier.forClasses(Either.Left.class, Either.Right.class)
            .verify();
    }

}
