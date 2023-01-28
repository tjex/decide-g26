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
    
    /* LIC 7
    *  K_PTS = 3
    *  LENGTH1 = 2
    */
    @Test
    public void checkIfValidGivesTrueLic7(){
        int[][] datapoints = {{0,0},{0,0},{0,0},{5,0},{0,0}};
        CMV cmv = new CMV(datapoints);
        assertTrue(cmv.get_cmv_value(7));
    }

    /* LIC 7
    *  K_PTS = 3
    *  LENGTH1 = 2
    */
    @Test
    public void checkIfInvalidGivesFalseLic7(){
        int[][] datapoints = {{0,0},{0,0},{0,0},{1,0},{0,0}};
        CMV cmv = new CMV(datapoints);
        assertFalse(cmv.get_cmv_value(7));
    }
}
