import org.testng.Assert;
import org.testng.annotations.Test;

public class TestExample {

    @Test
    public void testAddition() {
        int a = 5, b = 3;
        int sum = a + b;
        Assert.assertEquals(sum, 8, "Addition result is incorrect!");
    }

    @Test
    public void testString() {
        String expected = "Hello, TestNG!";
        Assert.assertTrue(expected.contains("TestNG"), "String does not contain expected text");
    }
}
