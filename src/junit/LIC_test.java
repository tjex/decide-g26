package junit;

import main.CMV;
import main.Helper_Functions;
import org.junit.Test;
import main.Parameters;

import static org.junit.Assert.*;

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
    public void test_lic6_invalid_points() {
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
