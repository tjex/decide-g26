package junit;

import main.CMV;
import main.Helper_Functions;
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
