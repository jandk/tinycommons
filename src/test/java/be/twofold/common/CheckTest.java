package be.twofold.common;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

@SuppressWarnings({"ConstantConditions"})
public class CheckTest {

    @Test
    public void testConstructor() {
        TestUtils.testConstructor(Check.class);
    }

    @Test
    public void testCheckNotNull() {
        Object object = new Object();

        assertThat(Check.notNull(object)).isSameAs(object);
        assertThatNullPointerException()
            .isThrownBy(() -> Check.notNull(null));

        assertThat(Check.notNull(object, "object")).isSameAs(object);
        assertThatNullPointerException()
            .isThrownBy(() -> Check.notNull(null, "object"))
            .withMessage("object");

        assertThat(Check.notNull(object, () -> String.format("%s is null", "object"))).isSameAs(object);
        assertThatNullPointerException()
            .isThrownBy(() -> Check.notNull(null, () -> String.format("%s is null", "object")))
            .withMessage("object is null");
    }

    @Test
    public void testCheckArgument() {
        assertThatNoException()
            .isThrownBy(() -> Check.argument(true));
        assertThatIllegalArgumentException()
            .isThrownBy(() -> Check.argument(false));

        assertThatNoException()
            .isThrownBy(() -> Check.argument(true, "failure"));
        assertThatIllegalArgumentException()
            .isThrownBy(() -> Check.argument(false, "failure"))
            .withMessage("failure");

        assertThatNoException()
            .isThrownBy(() -> Check.argument(true, () -> String.format("failure %s", "argument")));
        assertThatIllegalArgumentException()
            .isThrownBy(() -> Check.argument(false, () -> String.format("failure %s", "argument")))
            .withMessage("failure argument");
    }

    @Test
    public void testCheckState() {
        assertThatNoException()
            .isThrownBy(() -> Check.state(true));
        assertThatIllegalStateException()
            .isThrownBy(() -> Check.state(false));

        assertThatNoException()
            .isThrownBy(() -> Check.state(true, "failure"));
        assertThatIllegalStateException()
            .isThrownBy(() -> Check.state(false, "failure"))
            .withMessage("failure");

        assertThatNoException()
            .isThrownBy(() -> Check.state(true, () -> String.format("failure %s", "state")));
        assertThatIllegalStateException()
            .isThrownBy(() -> Check.state(false, () -> String.format("failure %s", "state")))
            .withMessage("failure state");
    }

    @Test
    public void testCheckIndexShouldPass() {
        assertThat(Check.index(0, 1)).isEqualTo(0);
        assertThat(Check.index(0, 2)).isEqualTo(0);
        assertThat(Check.index(1, 2)).isEqualTo(1);
    }

    @Test
    public void testCheckIndexShouldFail() {
        assertThatThrownBy(() -> Check.index(1, -1))
            .isInstanceOf(IndexOutOfBoundsException.class); // negative size
        assertThatThrownBy(() -> Check.index(-1, 1))
            .isInstanceOf(IndexOutOfBoundsException.class); // negative index
        assertThatThrownBy(() -> Check.index(1, 1))
            .isInstanceOf(IndexOutOfBoundsException.class); // index >= size
    }

    @Test
    public void testCheckFromToIndexShouldPass() {
        Check.fromToIndex(0, 0, 0);
        Check.fromToIndex(0, 0, 1);
        Check.fromToIndex(0, 1, 1);
        Check.fromToIndex(1, 1, 1);
    }

    @Test
    public void testCheckFromToIndexShouldFail() {
        assertThatThrownBy(() -> Check.fromToIndex(1, 1, -1))
            .isInstanceOf(IndexOutOfBoundsException.class); // negative size
        assertThatThrownBy(() -> Check.fromToIndex(-1, 1, 1))
            .isInstanceOf(IndexOutOfBoundsException.class); // negative start
        assertThatThrownBy(() -> Check.fromToIndex(0, 2, 1))
            .isInstanceOf(IndexOutOfBoundsException.class); //   end >  size
        assertThatThrownBy(() -> Check.fromToIndex(1, 0, 1))
            .isInstanceOf(IndexOutOfBoundsException.class); // start >= size
    }

}
