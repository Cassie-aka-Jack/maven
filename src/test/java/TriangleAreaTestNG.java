import org.testng.Assert;
import org.testng.annotations.Test;

public class TriangleAreaTestNG {

    @Test
    public void testCalculateArea() {
        Assert.assertEquals(TriangleArea.calculateArea(5, 4), 10.0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCalculateAreaWithInvalidInput() {
        TriangleArea.calculateArea(-1, 4);
    }
}