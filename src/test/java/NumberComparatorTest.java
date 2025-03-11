import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NumberComparatorTest {

    @Test
    void testCompareFirstGreater() {
        assertEquals("5 > 3", NumberComparator.compare(5, 3));
    }

    @Test
    void testCompareSecondGreater() {
        assertEquals("3 < 5", NumberComparator.compare(3, 5));
    }

    @Test
    void testCompareEqual() {
        assertEquals("4 = 4", NumberComparator.compare(4, 4));
    }
}