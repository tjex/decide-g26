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

    @Test
    public void test_vector_magnitude(){
        int[] a1 = {12,32};
        int[] b1 = {0,0};
        int[] c1 = {-1,123};

        assertEquals(Helper_Functions.vector_magnitude(a1), 34.18, 0.01);
        assertEquals(Helper_Functions.vector_magnitude(b1), 0.0, 0.01);
        assertEquals(Helper_Functions.vector_magnitude(c1), 123.00406497347964, 0.01);
    }

    @Test
    public void test_three_point_angle(){
        int[] origin = {0,0};
        
        int[] a1 = {5,5};
        int[] b1 = {3,2};

        int[] a2 = {345,-12};
        int[] b2 = {-3,-3};

        int[] ray1 = {23,26};
        int[] ray2 = {91,-12};
        int[] vertex = {2,2};
        int[] vec1 = Helper_Functions.vector_subtraction(ray1, vertex);
        int[] vec2 = Helper_Functions.vector_subtraction(ray2, vertex);
        double origin_angle = Helper_Functions.three_point_angle(vec1, origin, vec2);
        double three_point_angle = Helper_Functions.three_point_angle(ray1, vertex, ray2);
        

        assertEquals(Helper_Functions.three_point_angle(a1, origin, b1), 0.1974, 0.01);
        assertEquals(Helper_Functions.three_point_angle(a2, origin, b2), 2.3214, 0.01);
        assertEquals(origin_angle, three_point_angle, 0.01);
        assertEquals(three_point_angle, 1.008, 0.01);
    }

    @Test
    public void test_triangle_vertex_area(){
        int[] a1 = {-4,0};
        int[] b1 = {4,0};
        int[] c1 = {0,4};

        assertEquals(Helper_Functions.triangle_vertex_area(a1, b1, c1), 16, 0.1);
    }
}
