package be.twofold.common.func;

import be.twofold.common.*;
import be.twofold.common.func.fi.*;
import org.junit.jupiter.api.*;

import java.util.function.*;

import static org.assertj.core.api.Assertions.*;

public class UncheckedTest {

    @Test
    public void testConstructor() {
        TestUtils.testConstructor(Unchecked.class);
    }

    @Test
    public void testCheckedConsumer() {
        CheckedConsumer<Object> checkedConsumer = o -> {
            throw new Exception(String.valueOf(o));
        };

        Consumer<Object> consumer = Unchecked.consumer(checkedConsumer);

        assertThat(consumer).isNotNull();
        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> consumer.accept(null))
            .havingCause().isExactlyInstanceOf(Exception.class).withMessage("null");
    }

    @Test
    public void testFunction() {
        CheckedFunction<Object, Object> checkedFunction = t -> {
            throw new Exception(String.valueOf(t));
        };

        Function<Object, Object> function = Unchecked.function(checkedFunction);

        assertThat(function).isNotNull();
        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> function.apply(null))
            .havingCause().isExactlyInstanceOf(Exception.class).withMessage("null");
    }

    @Test
    public void testPredicate() {
        CheckedPredicate<Object> checkedPredicate = t -> {
            throw new Exception(String.valueOf(t));
        };

        Predicate<Object> predicate = Unchecked.predicate(checkedPredicate);

        assertThat(predicate).isNotNull();
        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(() -> predicate.test(null))
            .havingCause().isExactlyInstanceOf(Exception.class).withMessage("null");
    }

    @Test
    public void testSupplier() {
        CheckedSupplier<Object> checkedSupplier = () -> {
            throw new Exception("message");
        };

        Supplier<Object> supplier = Unchecked.supplier(checkedSupplier);

        assertThat(supplier).isNotNull();
        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(supplier::get)
            .havingCause().isExactlyInstanceOf(Exception.class).withMessage("message");
    }

}
