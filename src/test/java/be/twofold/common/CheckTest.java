package be.twofold.common;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

@SuppressWarnings({"ConstantConditions", "ObviousNullCheck"})
public class CheckTest {

    @Test
    public void testConstructor() {
        TestUtils.testConstructor(Check.class);
    }

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


    @Test
    public void checkIndexShouldPass() {
        assertThat(Check.index(0, 1)).isEqualTo(0);
        assertThat(Check.index(0, 2)).isEqualTo(0);
        assertThat(Check.index(1, 2)).isEqualTo(1);
    }

    @Test
    public void checkIndexShouldFail() {
        assertThatThrownBy(() -> Check.index(1, -1))
            .isInstanceOf(IndexOutOfBoundsException.class); // negative size
        assertThatThrownBy(() -> Check.index(-1, 1))
            .isInstanceOf(IndexOutOfBoundsException.class); // negative index
        assertThatThrownBy(() -> Check.index(1, 1))
            .isInstanceOf(IndexOutOfBoundsException.class); // index >= size
    }


    @Test
    public void checkPositionShouldPass() {
        assertThat(Check.position(0, 0)).isEqualTo(0);
        assertThat(Check.position(0, 1)).isEqualTo(0);
        assertThat(Check.position(1, 1)).isEqualTo(1);
    }

    @Test
    public void checkPositionShouldFail() {
        assertThatThrownBy(() -> Check.position(1, -1))
            .isInstanceOf(IndexOutOfBoundsException.class); // negative size
        assertThatThrownBy(() -> Check.position(-1, 1))
            .isInstanceOf(IndexOutOfBoundsException.class); // negative index
        assertThatThrownBy(() -> Check.position(2, 1))
            .isInstanceOf(IndexOutOfBoundsException.class); // index > size
    }

    @Test
    public void checkPositionsShouldPass() {
        Check.positions(0, 0, 0);
        Check.positions(0, 0, 1);
        Check.positions(0, 1, 1);
        Check.positions(1, 1, 1);
    }

    @Test
    public void checkPositionsShouldFail() {
        assertThatThrownBy(() -> Check.positions(1, 1, -1))
            .isInstanceOf(IndexOutOfBoundsException.class); // negative size
        assertThatThrownBy(() -> Check.positions(-1, 1, 1))
            .isInstanceOf(IndexOutOfBoundsException.class); // negative start
        assertThatThrownBy(() -> Check.positions(0, 2, 1))
            .isInstanceOf(IndexOutOfBoundsException.class); //   end >  size
        assertThatThrownBy(() -> Check.positions(1, 0, 1))
            .isInstanceOf(IndexOutOfBoundsException.class); // start >= size
    }

}
