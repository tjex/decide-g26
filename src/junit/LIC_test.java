package junit;

import main.CMV;
import main.Helper_Functions;
import main.Parameters;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LIC_test {

    @Test 
    public void cmv_getter_test(){
        //pretty useless test, works cause lic5-calculate is run in the constructor
        int[][] datapoints = {{4,0},{3,0},{2,0},{1,0},{0,0}};
        CMV cmv = new CMV(datapoints);
        assertTrue(cmv.get_cmv_value(5));
    }
    /*LIC 2
     * -------------------------------------------------
     * Checks the condition angle < PI-EPSILON = (3/4)*PI, which is bigger 
     * than 90 degress = PI/2. This should return true since condition is meet.
     */
  
    @Test
    public void checkValid90DegreeAngleUnderLic2(){
        Parameters.EPSILON = Math.PI / 4;
        int[][] datapoints = {{0,1},{0,0},{1,0}};
        CMV cmv = new CMV(datapoints);
        assertTrue(cmv.get_cmv_value(2));
    }
    /*
     * Checks the condition angle > PI+EPSILON => angle > (5/4)*PI. Since the 
     * angle is 270 degress = (3/2)*pi, the condition is meet and the test
     * should return true.
     */
    @Test
    public void checkValid270DegreeAngleOverLic2(){
        Parameters.EPSILON = Math.PI / 4;
        int[][] datapoints = {{0,-1},{0,0},{1,0}};
        CMV cmv = new CMV(datapoints);
        assertTrue(cmv.get_cmv_value(2));
    }
    /*
     * Checks both test since the angle is set to 180 degress = pi. 
     * The angle is both bigger than PI-EPSILON (3/4*PI) and smaller
     * than PI+EPSILON (5/4*PI) which leaves both conditions unfullfilled.
     * Therefore the test should return false.
     */
    @Test
    public void checkInvalid180DegreeAngleLic2(){
        Parameters.EPSILON = Math.PI / 4;
        int[][] datapoints = {{-1,0},{0,0},{1,0}};
        CMV cmv = new CMV(datapoints);
        assertFalse(cmv.get_cmv_value(2));
    }

    @Test
    public void test_lic3(){
        Parameters.AREA1 = 15.0;

        int[][] datapoints1 = {{-4,0},{4,0},{0,4}};
        CMV cmv1 = new CMV(datapoints1);

        int[][] datapoints2 = {{-1,0},{1,0},{0,1}};
        CMV cmv2 = new CMV(datapoints2);

        int[][] datapoints3 = {{-4,0},{4,0},{0,0},{0,4}};
        CMV cmv3 = new CMV(datapoints3);

        //We have a triangle with area 16, which is bigger than AREA1=15
        assertTrue(cmv1.get_cmv_value(3));
        //Triangle with smaller area should fail
        assertFalse(cmv2.get_cmv_value(3));
        //If datapoints are not consecutive it should fail 
        assertFalse(cmv3.get_cmv_value(3));

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
