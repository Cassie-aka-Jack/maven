import org.testng.Assert;
import org.testng.annotations.Test;

public class NumberComparatorTestNG {

    @Test
    public void testCompareFirstGreater() {
        Assert.assertEquals(NumberComparator.compare(5, 3), "5 > 3");
    }

    @Test
    public void testCompareSecondGreater() {
        Assert.assertEquals(NumberComparator.compare(3, 5), "3 < 5");
    }

    @Test
    public void testCompareEqual() {
        Assert.assertEquals(NumberComparator.compare(4, 4), "4 = 4");
    }
}