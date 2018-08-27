import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)

public class Test1 {

    Task6 task6 = new Task6();
    private int[] oldarr;
    private int[] newArr;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[]{1, 2, 3, 4}, new int[0]},
                {new int[]{1, 4, 5, 7}, new int[]{5, 7}},
                {new int[]{4, 2, 8, 3}, new int[]{2, 8, 3}},
                {new int[]{1, 4, 1, 2}, new int[]{1, 2}},

        });
    }

    public Test1(int[] oldarr, int[] newArr) {
        this.oldarr = oldarr;
        this.newArr = newArr;
    }

    @Test
    public void testCheckArr (){
        Assert.assertArrayEquals(newArr,task6.checkArray(oldarr,4));
    }
}

