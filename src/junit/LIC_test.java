package junit;

import main.CMV;
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
    /* LIC 5
     * --------------------------------------------------------------
     * checks if datapoints of descending order returns true. This 
     * fullfills the requirments of Lic 5 condition since descending
     * value on x-axis fullfills datapoint[i+1][0] - datapoint[i][0] < 0.
     */
    @Test
    public void checkIfValidGivesTrueLic5(){
        int[][] datapoints = {{4,0},{3,0},{2,0},{1,0},{0,0}};
        CMV cmv = new CMV(datapoints);
        assertTrue(cmv.get_cmv_value(5));
    }
    /* 
     * checks if datapoints of ascending order returns false. This 
     * doesn't fullfill the requirments of Lic 5 condition since ascending
     * value on x-axis givs datapoint[i+1][0] - datapoint[i][0] > 0. Since 
     * condition is not fullfilled lic5_calculate() should return false.
    */ 
    @Test
    public void checkIfInvalidGivesFalseLic5(){
        int[][] datapoints = {{0,0},{1,0},{2,0},{3,0},{4,0}};
        CMV cmv = new CMV(datapoints);
        assertFalse(cmv.get_cmv_value(5));
    }

    @Test
    public void test_lic9(){
        Parameters.EPSILON = 0.5; //is this safe? will it set globally or just for this test?
        //this epsilon allows angle to be < 2.64 and > 3.64
        //this is very arbitrary since our formula calculates angles < pi
        //how would you know that the outer angle should be used for the points?
        Parameters.C_PTS = 2;
        Parameters.D_PTS = 3;

        int[][] datapoints1 = {{1,1}, {2,2}, {3,3}};
        CMV cmv1 = new CMV(datapoints1);
        

        int[][] datapoints2 = {{1,1}, {2,2}, {3,3}, {1,1}, {2,2}, {3,3}, {1,1}, {2,2}, {3,3}};
        CMV cmv2 = new CMV(datapoints2);

        //90deg=1.57rad angle < 3.14 - EPSILON
        int[][] datapoints3 = {{1,1}, {0,0}, {0,0}, {1,3}, {0,0}, {0,0}, {0,0}, {3,3}}; 
        CMV cmv3 = new CMV(datapoints3);

        int[][] datapoints4 = {{0,1}, {0,0}, {0,0}, {2,3}, {0,0}, {0,0}, {0,0}, {4,4}}; 
        CMV cmv4 = new CMV(datapoints4);
        
        
        //condition should not be met when datapoints are fewer than 5
        assertFalse(cmv1.get_cmv_value(9)); 
        //points where the ones divided by the boundaries do not have the required angle
        assertFalse(cmv2.get_cmv_value(9)); 
        //the required points have a 90 degree angle which is ~1.57 radians which is less than 2.64
        assertTrue(cmv3.get_cmv_value(9));
        //points with a very obtuse angle almost 180deg which is more than 2.64 and below 3.64, should be false 
        assertFalse(cmv4.get_cmv_value(9)); 

    }

    /* LIC 11
    *  G_PTS = 3
    *  #Test wheter {3,0} and {2,0}, which are seperated by three datapoints, 
        will yield true in the function lic11_calculate(). This since 2 - 3 < 0
        which fullfills the requirment of being less than 0.  
    */
    @Test
    public void checkIfValidGivesTrueLic11(){
        int[][] datapoints = {{3,0},{0,0},{0,0},{0,0},{2,0}};
        CMV cmv = new CMV(datapoints);
        assertTrue(cmv.get_cmv_value(11));
    }
    /* LIC 11
    *  G_PTS = 3
    *  #Test wheter {3,0} and {4,0}, which are seperated by three datapoints, 
        will yield false in the function lic11_calculate(). This since 4 - 3 > 0
        and function returns true on less than 0.  
    */
    @Test
    public void checkIfInvalidGivesFalseLic11(){
        int[][] datapoints = {{3,0},{0,0},{0,0},{0,0},{4,0}};
        CMV cmv = new CMV(datapoints);
        assertFalse(cmv.get_cmv_value(11));
    }

    @Test
    public void test_lic_10(){
        Parameters.E_PTS = 2;
        Parameters.F_PTS = 3;
        Parameters.AREA1 = 14;

        int[][] datapoints1 = {{-4,0}, {0,0}, {0,0}, {4,0}, {0,0}, {0,0}, {0,0}, {0,4}, {0,0}};
        CMV cmv1 = new CMV(datapoints1);

        int[][] datapoints2 = {{-1,0}, {0,0}, {0,0}, {1,0}, {0,0}, {0,0}, {0,0}, {0,4}, {0,0}};
        CMV cmv2 = new CMV(datapoints2);

        int[][] datapoints3 = {{-4,0}, {0,0}, {0,0}, {0,0}, {4,0}, {0,0}, {0,0}, {0,4}, {0,0}};
        CMV cmv3 = new CMV(datapoints3);

        //this should pass, we have a triangle with area 16 which is bigger than AREA1=14
        assertTrue(cmv1.get_cmv_value(10));
        //this should fail, we have a triangle with area 4
        assertFalse(cmv2.get_cmv_value(10));
        //this should fail, the area is correct however, the points are not spaced exactly with
        //E_PTS and F_PTS points apart
        assertFalse(cmv3.get_cmv_value(10));
    }
}