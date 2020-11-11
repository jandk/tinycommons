package be.twofold.tinycommons;

import org.junit.*;

import static org.assertj.core.api.Assertions.*;

@SuppressWarnings({"ConstantConditions", "ObviousNullCheck"})
public class CheckTest {

    @Test
    public void checkNotNullShouldPassWithTrueExpression() {
        Check.notNull(new Object());
    }

    @Test
    public void checkNotNullReturnsOriginalObject() {
        Object value = new Object();
        assertThat(Check.notNull(value))
            .isSameAs(value);
    }

    @Test
    public void checkNotNullShouldFailWithFalseExpression() {
        assertThatThrownBy(() -> Check.notNull(null))
            .isInstanceOf(NullPointerException.class);
    }

    @Test
    public void checkNotNullShouldFailWithCorrectMessage() {
        assertThatThrownBy(() -> Check.notNull(null, "failure"))
            .isInstanceOf(NullPointerException.class)
            .hasMessage("failure");
    }

    @Test
    public void checkNotNullShouldFailWithCorrectSuppliedMessage() {
        assertThatThrownBy(() -> Check.notNull(null, "failure %s", "argument"))
            .isInstanceOf(NullPointerException.class)
            .hasMessage("failure argument");
    }


    @Test
    public void checkArgumentShouldPassWithTrueExpression() {
        Check.argument(true);
    }

    @Test
    public void checkArgumentShouldFailWithFalseExpression() {
        assertThatThrownBy(() -> Check.argument(false))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void checkArgumentShouldFailWithCorrectMessage() {
        assertThatThrownBy(() -> Check.argument(false, "failure"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("failure");
    }

    @Test
    public void checkArgumentShouldFailWithCorrectSuppliedMessage() {
        assertThatThrownBy(() -> Check.argument(false, "failure %s", "argument"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("failure argument");
    }


    @Test
    public void checkStateShouldPassWithTrueExpression() {
        Check.state(true);
    }

    @Test
    public void checkStateShouldFailWithFalseExpression() {
        assertThatThrownBy(() -> Check.state(false))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    public void checkStateShouldFailWithCorrectMessage() {
        assertThatThrownBy(() -> Check.state(false, "failure %s", "argument"))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("failure argument");
    }

    @Test
    public void checkStateShouldFailWithCorrectSuppliedMessage() {
        assertThatThrownBy(() -> Check.state(false, "failure %s", "argument"))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("failure argument");
    }

}
