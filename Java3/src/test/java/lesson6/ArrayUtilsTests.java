package lesson6;

import Lesson6.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArrayUtilsTests {
    private ArrayUtils arrayUtils;

    @BeforeEach
    public void init() {
        arrayUtils = new ArrayUtils();
    }

    @Test
    public void testArrayAfterLastFour() {
        Assertions.assertArrayEquals(new int[]{1, 7}, arrayUtils.arrayAfterLastFour(new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7}));
        Assertions.assertArrayEquals(new int[]{2, 3, 1, 7}, arrayUtils.arrayAfterLastFour(new int[]{1, 2, 4, 4, 2, 3, 1, 7}));
        Assertions.assertThrows(RuntimeException.class, () -> arrayUtils.arrayAfterLastFour(new int[]{1, 2, 2, 3, 1, 7}));
    }

    @Test
    public void testCheckArrayOneFour() {
        Assertions.assertTrue(arrayUtils.checkArrayOneFour(new int[]{1, 1, 1, 4, 4, 1, 4, 4}));
        Assertions.assertFalse(arrayUtils.checkArrayOneFour(new int[]{1, 1, 1, 1, 1, 1}));
        Assertions.assertFalse(arrayUtils.checkArrayOneFour(new int[]{4, 4, 4, 4}));
        Assertions.assertFalse(arrayUtils.checkArrayOneFour(new int[]{1, 4, 4, 3, 1, 4, 1}));
    }

}
