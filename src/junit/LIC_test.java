package junit;

import main.CMV;
import main.Helper_Functions;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.beans.Transient;

public class LIC_test {
    //LIC 4
    @Test
    public void check_if_dataPoint_converted_to_correct_quadrant(){
        int[][] datapoints = {{0,0},{1,0},{2,0}};
        CMV cmv = new CMV(datapoints);
        int[] point1 = {0,0}; 
        int[] point2 = {-1,0};
        int[] point3 = {0,-1};
        int[] point4 = {1,-1};
        int[] point5 = {0,1};
        int[] point6 = {1,0};
        
        assertEquals(1, cmv.quadEvaluation(point1));
        assertEquals(2, cmv.quadEvaluation(point2));
        assertEquals(3, cmv.quadEvaluation(point3));
        assertEquals(4, cmv.quadEvaluation(point4));
        assertEquals(1, cmv.quadEvaluation(point5));
        assertEquals(1, cmv.quadEvaluation(point6));
    }
}
