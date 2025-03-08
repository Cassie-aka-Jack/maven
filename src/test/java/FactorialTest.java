import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FactorialTest {

    @Test
    void testCalculateFactorial() {
        assertEquals(120, Factorial.calculateFactorial(5)); // 5! = 120
        assertEquals(1, Factorial.calculateFactorial(0)); // 0! = 1
    }
}