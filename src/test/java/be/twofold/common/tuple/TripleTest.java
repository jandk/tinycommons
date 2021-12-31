package be.twofold.common.tuple;

import nl.jqno.equalsverifier.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

public class TripleTest {

    @Test
    public void testEqualsAndHashCode() {
        EqualsVerifier.forClass(Triple.class)
            .verify();
    }

    @Test
    public void testBasics() {
        Triple<String, String, String> pair = new Triple<>("first", "second", "third");
        assertThat(pair.getFirst()).isEqualTo("first");
        assertThat(pair.getSecond()).isEqualTo("second");
        assertThat(pair.getThird()).isEqualTo("third");
        assertThat(pair.toString()).isEqualTo("(first, second, third)");
    }

}
