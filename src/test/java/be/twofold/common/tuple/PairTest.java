package be.twofold.common.tuple;

import nl.jqno.equalsverifier.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

public class PairTest {

    @Test
    public void testEqualsAndHashCode() {
        EqualsVerifier.forClass(Pair.class)
            .verify();
    }

    @Test
    public void testBasics() {
        Pair<String, String> pair = new Pair<>("first", "second");
        assertThat(pair.getFirst()).isEqualTo("first");
        assertThat(pair.getSecond()).isEqualTo("second");
        assertThat(pair.toString()).isEqualTo("(first, second)");
    }

}
