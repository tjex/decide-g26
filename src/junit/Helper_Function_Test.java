package junit;

import main.Helper_Functions;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.beans.Transient;
import java.util.Arrays;

public class Helper_Function_Test {
    @Test
    public void test_circumscribed_circle_radius() {
        int[][] datapoints = {{0,3},{3,0},{-3,0}};
        double radius = Helper_Functions.circumscribed_circle_radius(datapoints[0],datapoints[1], datapoints[2]);
        assertTrue(radius==3.0);
    }

    @Test
    public void test_dot_product(){
        int[] a1 = {1,2};
        int[] b1 = {2,3};

        int[] a2 = {-1,-2};
        int [] b2 = {3,23424};

        int[] a3 = {-1,-2};
        int [] b3 = {-3,-23424};

        assertEquals(Helper_Functions.dot_product(a1, b1), 8);
        assertEquals(Helper_Functions.dot_product(a2, b2), -46851);
        assertEquals(Helper_Functions.dot_product(a3, b3), 46851);
    }

    @Test
    public void test_vector_subtraction(){
        int[] a1 = {3,2};
        int[] b1 = {4,5};
        int[] r1 = {-1,-3};

        int[] a2 = {-3,-23423523};
        int[] b2 = {23,0};
        int[] r2 = {-26,-23423523};


        assertTrue(Arrays.equals(Helper_Functions.vector_subtraction(a1, b1), r1));
        assertTrue(Arrays.equals(Helper_Functions.vector_subtraction(a2, b2), r2));
        assertFalse(Arrays.equals(Helper_Functions.vector_subtraction(a2, b2), r1));
    }
}
