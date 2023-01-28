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
}
