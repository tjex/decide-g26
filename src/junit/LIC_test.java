package junit;

import main.CMV;
import main.Helper_Functions;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.beans.Transient;

public class LIC_test {

    @Test 
    public void cmv_getter_test(){
        //pretty useless test, works cause lic5-calculate is run in the constructor
        int[][] datapoints = {{4,0},{3,0},{2,0},{1,0},{0,0}};
        CMV cmv = new CMV(datapoints);
        assertTrue(cmv.get_cmv_value(5));
    }
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
    /* LIC 4
     * Q_PTS = 3
     * QUADS = 3
     */
    @Test
    public void checkIfValidGivesTrueLic4(){
        int[][] datapoints = {{1,1},{-1,1},{-1,-1}};
        CMV cmv = new CMV(datapoints);
        assertTrue(cmv.get_cmv_value(4));
    }
    /* LIC 4
     * Q_PTS = 3
     * QUADS = 3
     * only occupying 2 different quadrant and QUADS want 3.
     */
    @Test
    public void checkIfInvalidGivesFalseLic4(){
        int[][] datapoints = {{1,1},{0,-1},{-1,-1}};
        CMV cmv = new CMV(datapoints);
        assertFalse(cmv.get_cmv_value(4));
    }

    //LIC 5
    @Test
    public void checkIfValidGivesTrue(){
        int[][] datapoints = {{4,0},{3,0},{2,0},{1,0},{0,0}};
        CMV cmv = new CMV(datapoints);
        assertTrue(cmv.lic5_calculate());
    }
    //LIC 5
    @Test
    public void checkIfInvalidGivesFalse(){
        int[][] datapoints = {{0,0},{1,0},{2,0},{3,0},{4,0}};
        CMV cmv = new CMV(datapoints);
        assertFalse(cmv.lic5_calculate());
    }
}
