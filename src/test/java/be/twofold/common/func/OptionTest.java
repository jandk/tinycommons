package be.twofold.common.func;

import nl.jqno.equalsverifier.*;
import org.junit.jupiter.api.*;

public class OptionTest {

    @Test
    public void testEqualsAndHashCode() {
        EqualsVerifier.forClasses(Option.Some.class, Option.None.class)
            .verify();
    }

}
