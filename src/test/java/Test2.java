import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class Test2 {

    Task6 task6 = new Task6();
    private int[] arr;
    private boolean torf;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[]{1, 2, 3, 4}, true},
                {new int[]{1, 4, 19, 32}, true},
                {new int[]{1, 4, 11, 2}, true},
                {new int[]{0, 2, 3, 0}, false},
                {new int[]{1, 44, 3, 3}, false},
                {new int[]{4, 3, 2, 1}, false}, //!!!!
        });
    }

    public Test2(int[] arr, boolean torf) {
        this.arr = arr;
        this.torf = torf;
    }

    @Test
    public void metod2Test() {
        Assert.assertEquals(torf, task6.isArrayCorrect(arr, 4, 1));
    }

}
