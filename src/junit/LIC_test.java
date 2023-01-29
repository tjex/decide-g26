package junit;

import main.CMV;
import main.Parameters;
import org.junit.Test;
import main.Parameters;

import static org.junit.Assert.assertEquals;
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
    /* LIC 4
     * -------------------------------------------------------------------------
     * Take the whole set of datapoints {{1,1},{-1,1},{-1,-1}}, which belong to
     * 3 unique quadrants. This should return true since it's 3 distinct quadrants,
     * which is equal to QUADS.
     */
    @Test
    public void checkIfValidGivesTrueLic4(){
        Parameters.Q_PTS = 3;
        Parameters.QUADS = 3;
        int[][] datapoints = {{1,1},{-1,1},{-1,-1}};
        CMV cmv = new CMV(datapoints);
        assertTrue(cmv.get_cmv_value(4));
    }
    /* LIC 4
     * Take the whole set of datapoints {{1,1},{0,-1},{-1,-1}, which belong to
     * 2 unique quadrants. This should return false since it's 2 distinct quadrants,
     * which is not equal to QUADS.
     */
    @Test
    public void checkIfInvalidGivesFalseLic4(){
        Parameters.Q_PTS = 3;
        Parameters.QUADS = 3;
        int[][] datapoints = {{1,1},{0,-1},{-1,-1}};
        CMV cmv = new CMV(datapoints);
        assertFalse(cmv.get_cmv_value(4));
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
        Parameters.G_PTS = 3;
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
        Parameters.G_PTS = 3;
        CMV cmv = new CMV(datapoints);
        assertFalse(cmv.get_cmv_value(11));
    }

    /*LIC 12
    //-------------------------------------------------
     * Checks if two datapoints exits, seperated by 2, will have a magnitude between 
     * 2 and 5. This should return true since index 1 and 4 gives magnitude 2 < sqrt(18) < 5
     */
    @Test
    public void checkInvalidDistancebetweenLic12(){
        Parameters.K_PTS = 2;
        Parameters.LENGTH1 = 2;
        Parameters.LENGTH2 = 5;
        int[][] datapoints = {{0,0},{4,4},{0,0},{0,0},{1,1}};
        CMV cmv = new CMV(datapoints);
        assertTrue(cmv.get_cmv_value(12));
    }
    /*
     * Checks if two datapoints exits, seperated by 2, will have a magnitude between 
     * 2 and 5. This should return false since all magnitude is over 5. 
     */
    @Test
    public void checkInvalidDistanceOverLic12(){
        Parameters.K_PTS = 2;
        Parameters.LENGTH1 = 2;
        Parameters.LENGTH2 = 5;
        int[][] datapoints = {{2,2},{2,2},{2,2},{10,10},{10,10}};
        CMV cmv = new CMV(datapoints);
        assertFalse(cmv.get_cmv_value(12));
    }
    /*
     * Checks if two datapoints exits, seperated by 2, will have a magnitude between 
     * 2 and 5. This should return false since all magnitude is under 2. 
     */
    @Test
    public void checkInvalidDistanceUnderLic12(){
        Parameters.K_PTS = 2;
        Parameters.LENGTH1 = 2;
        Parameters.LENGTH2 = 5;
        int[][] datapoints = {{0,0},{2,2},{0,0},{0,0},{1,1}};
        CMV cmv = new CMV(datapoints);
        assertFalse(cmv.get_cmv_value(12));
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


    /**
     *  LIC 6
     *  Tests the LIC6 parameter requirements.
     * 
     *  - 3 <= N_PTS <= NUMPOINTS
     *  - 0 <= DIST
     * 
     *  Expected result: all calculations should return false.
     */
    @Test
    public void test_lic6_parameter_requirements() {
        int[][] no_data_points  = new int[][] {};
        int[][] one_data_point  = new int[][] {{0, 0}};
        int[][] two_data_points = new int[][] {{2, 1},{0, 0}};
        
        Parameters.DIST = 10;
        Parameters.N_PTS = 3;

        CMV cmv = new CMV(no_data_points);
        assertEquals(false, cmv.get_cmv_value(6));

        cmv = new CMV(one_data_point);
        assertEquals(false, cmv.get_cmv_value(6));

        cmv = new CMV(two_data_points);
        assertEquals(false, cmv.get_cmv_value(6));

        // Num of data points is OK
        // But N_PTS is not!
        int[][] three_data_points = new int[][] {{2, 1},{0, 0}, {5, 5}};
        cmv = new CMV(three_data_points);
        Parameters.N_PTS = 2;
        assertEquals(false, cmv.get_cmv_value(6));
        Parameters.N_PTS = -1;
        cmv = new CMV(three_data_points);
        assertEquals(false, cmv.get_cmv_value(6));

        // Num data points and N_PTS are OK.
        // But DIST is not!
        Parameters.N_PTS = 3;
        Parameters.DIST = 0;
        cmv = new CMV(three_data_points);
        assertEquals(false, cmv.get_cmv_value(6));
        Parameters.DIST = -0.1;
        cmv = new CMV(three_data_points);
        assertEquals(false, cmv.get_cmv_value(6));
    }

    /**
     *  LIC 6
     *  Test a set of points which are expected to return true.
     */
    @Test()
    public void test_lic6_valid_points() {
        int[][] data_points = new int[][] {{0, 0}, {1, 1}, {2, 2}, {3, 0}};
        
        Parameters.DIST = 1.1;
        Parameters.N_PTS = 4;

        CMV cmv = new CMV(data_points);
        assertEquals(true, cmv.get_cmv_value(6));
    }

    /**
     *  LIC 6  
     *  Test a set of invalid points which are expected to return false.
     */
    @Test()
    public void test_lic6_invalid_points() {
        int[][] data_points = new int[][] {{0, 0}, {1, 0}, {2, 0}, {3, 0}};
        
        Parameters.DIST = 0.01; // all points are on the line!
        Parameters.N_PTS = 4;

        CMV cmv = new CMV(data_points);
        assertEquals(false, cmv.get_cmv_value(6));

        data_points = new int[][] {{0, 0}, {-1, 0}, {-2, 0}, {-3, 0}};
        cmv = new CMV(data_points);
        assertEquals(false, cmv.get_cmv_value(6));
    }


    /**
     *  LIC 8
     *  Test a set of points which are expected to return true.
     */
    @Test()
    public void test_lic8_valid_points() {
        int[][] data_points = new int[][] {{3, 0}, {1, 1}, {0, 3}, {3, 4}, {-3,0}};

        Parameters.A_PTS = 1;
        Parameters.B_PTS = 1;
        Parameters.RADIUS1 = 2;

        CMV cmv = new CMV(data_points);
        assertEquals(true, cmv.get_cmv_value(8));
    }

    /**
     *  LIC 8
     *  Test a set of invalid points which are expected to return false.
     */
    @Test()
    public void test_lic8_invalid_points() {
        int[][] data_points_1 = new int[][] {{0, 2}, {1, 0}, {0, 0}, {3, 1}};
        int[][] data_points_2 = new int[][] {{3, 0}, {1, 1}, {0, 3}, {3, 4}};
        Parameters.A_PTS = 1;
        Parameters.B_PTS = 1;
        Parameters.RADIUS1 = 10;

        CMV cmv = new CMV(data_points_1);
        assertEquals(false, cmv.get_cmv_value(8));

        cmv = new CMV(data_points_2);
        assertEquals(false, cmv.get_cmv_value(8));
    }

}