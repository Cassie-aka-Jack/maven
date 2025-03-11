import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArithmeticOperationsTest {

    @Test
    void testAdd() {
        assertEquals(5, ArithmeticOperations.add(2, 3));
    }

    @Test
    void testSubtract() {
        assertEquals(1, ArithmeticOperations.subtract(3, 2));
    }

    @Test
    void testMultiply() {
        assertEquals(6, ArithmeticOperations.multiply(2, 3));
    }

    @Test
    void testDivide() {
        assertEquals(2.0, ArithmeticOperations.divide(4, 2));
        assertThrows(ArithmeticException.class, () -> ArithmeticOperations.divide(4, 0));
    }
}