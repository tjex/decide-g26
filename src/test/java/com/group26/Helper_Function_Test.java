package com.group26;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.beans.Transient;
import java.util.Arrays;

public class Helper_Function_Test {

    @Test
    public void test_smallest_enclosing_circle() {
        // points on the circumference of a circle of radius 3
        double[][] datapoints = {{0,3},{3,0},{-3,0}};
        double radius = Helper_Functions.smallest_enclosing_radius(datapoints[0],datapoints[1], datapoints[2]);
        assertEquals(3.0, radius,0.01);

        // points on a straight line
        datapoints = new double[][]{{3,0},{0,0},{-3,0}};
        radius = Helper_Functions.smallest_enclosing_radius(datapoints[0],datapoints[1], datapoints[2]);
        assertEquals(3.0, radius,0.01);
    }
    

    //this tests the function that returns the dot product of two vectors
    @Test
    public void test_dot_product(){
        double[] a1 = {1,2};
        double[] b1 = {2,3};

        double[] a2 = {-1,-2};
        double [] b2 = {3,23424};

        double[] a3 = {-1,-2};
        double [] b3 = {-3,-23424};

        //the vectors a1 and b1 should have the scalar product 8, calculated by hand
        assertEquals(Helper_Functions.dot_product(a1, b1), 8, 0.1);
        //similar to test above
        assertEquals(Helper_Functions.dot_product(a2, b2), -46851, 0.1);
        assertEquals(Helper_Functions.dot_product(a3, b3), 46851, 0.1);
    }

    //tests whether the helper function vector_subtraction finds the correct vector
    @Test
    public void test_vector_subtraction(){
        double[] a1 = {3,2};
        double[] b1 = {4,5};
        double[] r1 = {-1,-3};

        double[] a2 = {-3,-23423523};
        double[] b2 = {23,0};
        double[] r2 = {-26,-23423523};

        //tests the function for the vectors above
        assertTrue(Arrays.equals(Helper_Functions.vector_subtraction(a1, b1), r1));
        assertTrue(Arrays.equals(Helper_Functions.vector_subtraction(a2, b2), r2));
        assertFalse(Arrays.equals(Helper_Functions.vector_subtraction(a2, b2), r1));
    }

    //this tests whether the helper function vector_magnitude finds the vector magnitude
    @Test
    public void test_vector_magnitude(){
        double[] a1 = {12,32};
        double[] b1 = {0,0};
        double[] c1 = {-1,123};

        //asserts that the vector magnitude equals the expected magnitude for the vectors above
        assertEquals(Helper_Functions.vector_magnitude(a1), 34.18, 0.01);
        assertEquals(Helper_Functions.vector_magnitude(b1), 0.0, 0.01);
        assertEquals(Helper_Functions.vector_magnitude(c1), 123.00406497347964, 0.01);
    }

    //this tests whether the helper function finds the angle given three points
    @Test
    public void test_three_point_angle(){
        double[] origin = {0,0};
        
        double[] a1 = {5,5};
        double[] b1 = {3,2};

        double[] a2 = {345,-12};
        double[] b2 = {-3,-3};

        double[] ray1 = {23,26};
        double[] ray2 = {91,-12};
        double[] vertex = {2,2};
        double[] vec1 = Helper_Functions.vector_subtraction(ray1, vertex);
        double[] vec2 = Helper_Functions.vector_subtraction(ray2, vertex);
        double origin_angle = Helper_Functions.three_point_angle(vec1, origin, vec2);
        double three_point_angle = Helper_Functions.three_point_angle(ray1, vertex, ray2);
        
        //checks that the angle given the points matches what is expected from the formula calculated on a calculator
        assertEquals(Helper_Functions.three_point_angle(a1, origin, b1), 0.1974, 0.01);
        assertEquals(Helper_Functions.three_point_angle(a2, origin, b2), 2.3214, 0.01);
        assertEquals(origin_angle, three_point_angle, 0.01);
        assertEquals(three_point_angle, 1.008, 0.01);
    }


    //this tests whether the helper function triangle_vertex_area finds the area of 
    //a triangle given its vertices
    @Test
    public void test_triangle_vertex_area(){
        double[] a1 = {-4,0};
        double[] b1 = {4,0};
        double[] c1 = {0,4};

        //tests that the triangle above has the expected area 16
        assertEquals(Helper_Functions.triangle_vertex_area(a1, b1, c1), 16, 0.1);
        //the dual of the test above
        assertFalse(Helper_Functions.triangle_vertex_area(a1,b1,c1)==12);
    }
    /*
     * Test logic of quadrant evaluation from quadEvaluation().
     */
    @Test
    public void test_quadEvaluation(){
        double[] datapoint1 = {1,1}; //quadrant: 1
        double[] datapoint2 = {-1,1}; //quadrant: 2
        double[] datapoint3 = {-1,-1}; //quadrant: 3
        double[] datapoint4 = {1,-1}; //quadrant: 4
        double[] datapoint5 = {0,0}; //quadrant: 1
        double[] datapoint6 = {-1,0}; //quadrant: 2
        double[] datapoint7 = {0,-1}; //quadrant: 3
        double[] datapoint8 = {0,1}; //quadrant: 1
        double[] datapoint9 = {1,0}; //quadrant: 1
        assertEquals(Helper_Functions.quadEvaluation(datapoint1), 1);
        assertEquals(Helper_Functions.quadEvaluation(datapoint2), 2);
        assertEquals(Helper_Functions.quadEvaluation(datapoint3), 3);
        assertEquals(Helper_Functions.quadEvaluation(datapoint4), 4);
        assertEquals(Helper_Functions.quadEvaluation(datapoint5), 1);
        assertEquals(Helper_Functions.quadEvaluation(datapoint6), 2);
        assertEquals(Helper_Functions.quadEvaluation(datapoint7), 3);
        assertEquals(Helper_Functions.quadEvaluation(datapoint8), 1);
        assertEquals(Helper_Functions.quadEvaluation(datapoint9), 1);
    }
}
