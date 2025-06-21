import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleTest {

    @Test
    public void test1(){
        int a = 5 + 5;
        Assert.assertEquals(a, 10);
    }

}
