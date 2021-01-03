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

        assertThat(Check.notNull(object, "%s is null", "object")).isSameAs(object);
        assertThatNullPointerException()
            .isThrownBy(() -> Check.notNull(null, "%s is null", "object"))
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
            .isThrownBy(() -> Check.argument(true, "failure %s", "argument"));
        assertThatIllegalArgumentException()
            .isThrownBy(() -> Check.argument(false, "failure %s", "argument"))
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
            .isThrownBy(() -> Check.state(true, "failure %s", "state"));
        assertThatIllegalStateException()
            .isThrownBy(() -> Check.state(false, "failure %s", "state"))
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
    public void testCheckPositionShouldPass() {
        assertThat(Check.position(0, 0)).isEqualTo(0);
        assertThat(Check.position(0, 1)).isEqualTo(0);
        assertThat(Check.position(1, 1)).isEqualTo(1);
    }

    @Test
    public void testCheckPositionShouldFail() {
        assertThatThrownBy(() -> Check.position(1, -1))
            .isInstanceOf(IndexOutOfBoundsException.class); // negative size
        assertThatThrownBy(() -> Check.position(-1, 1))
            .isInstanceOf(IndexOutOfBoundsException.class); // negative index
        assertThatThrownBy(() -> Check.position(2, 1))
            .isInstanceOf(IndexOutOfBoundsException.class); // index > size
    }

    @Test
    public void testCheckPositionsShouldPass() {
        Check.positions(0, 0, 0);
        Check.positions(0, 0, 1);
        Check.positions(0, 1, 1);
        Check.positions(1, 1, 1);
    }

    @Test
    public void testCheckPositionsShouldFail() {
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
