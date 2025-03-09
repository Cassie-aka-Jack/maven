import org.testng.Assert;
import org.testng.annotations.Test;

public class FactorialTestNG {

    @Test
    public void testCalculateFactorial() {
        Assert.assertEquals(Factorial.calculateFactorial(5), 120);
        Assert.assertEquals(Factorial.calculateFactorial(0), 1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNegativeFactorial() {
        Factorial.calculateFactorial(-1);
    }
}