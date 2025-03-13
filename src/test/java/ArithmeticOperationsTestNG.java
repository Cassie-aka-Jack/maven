import org.testng.Assert;
import org.testng.annotations.Test;

public class ArithmeticOperationsTestNG {

    @Test
    public void testAdd() {
        Assert.assertEquals(ArithmeticOperations.add(2, 3), 5);
    }

    @Test
    public void testSubtract() {
        Assert.assertEquals(ArithmeticOperations.subtract(3, 2), 1);
    }

    @Test
    public void testMultiply() {
        Assert.assertEquals(ArithmeticOperations.multiply(2, 3), 6);
    }

    @Test
    public void testDivide() {
        Assert.assertEquals(ArithmeticOperations.divide(4, 2), 2.0);
    }

    @Test(expectedExceptions = ArithmeticException.class)
    public void testDivideByZero() {
        ArithmeticOperations.divide(4, 0);
    }
}