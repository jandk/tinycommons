package be.twofold.common.func;

import nl.jqno.equalsverifier.*;
import org.junit.jupiter.api.*;

public class TryTest {

    @Test
    public void testEqualsAndHashCode() {
        EqualsVerifier.forClasses(Try.Success.class, Try.Failure.class)
            .verify();
    }

}
