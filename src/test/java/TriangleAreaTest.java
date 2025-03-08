import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TriangleAreaTest {

    @Test
    void testCalculateArea() {
        assertEquals(10, TriangleArea.calculateArea(5, 4)); // Площадь треугольника с основанием 5 и высотой 4
    }

    @Test
    void testCalculateAreaWithInvalidInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            TriangleArea.calculateArea(-1, 4);
        });
        assertEquals("Основание и высота должны быть положительными числами", exception.getMessage());
    }
}