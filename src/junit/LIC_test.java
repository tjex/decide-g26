package junit;

import main.CMV;
import main.Helper_Functions;
import main.Parameters;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LIC_test {

    
    /**
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
        assertEquals(false, cmv.lic6_calculate());

        cmv = new CMV(one_data_point);
        assertEquals(false, cmv.lic6_calculate());

        cmv = new CMV(two_data_points);
        assertEquals(false, cmv.lic6_calculate());

        // Num of data points is OK
        // But N_PTS is not!
        int[][] three_data_points = new int[][] {{2, 1},{0, 0}, {5, 5}};
        cmv = new CMV(three_data_points);
        Parameters.N_PTS = 2;
        assertEquals(false, cmv.lic6_calculate());
        Parameters.N_PTS = -1;
        cmv = new CMV(three_data_points);
        assertEquals(false, cmv.lic6_calculate());

        // Num data points and N_PTS are OK.
        // But DIST is not!
        Parameters.N_PTS = 3;
        Parameters.DIST = 0;
        cmv = new CMV(three_data_points);
        assertEquals(false, cmv.lic6_calculate());
        Parameters.DIST = -0.1;
        cmv = new CMV(three_data_points);
        assertEquals(false, cmv.lic6_calculate());
    }

    /**
     *  Test a set of points which are expected to return true.
     */
    @Test()
    public void test_lic6_valid_points() {
        int[][] data_points = new int[][] {{0, 0}, {1, 1}, {2, 2}, {3, 0}};
        
        Parameters.DIST = 1.1;
        Parameters.N_PTS = 4;

        CMV cmv = new CMV(data_points);
        assertEquals(true, cmv.lic6_calculate());

        // Shifted the (0, 0) and (3, 0) points so it tests array wrapping
        data_points = new int[][] {{1, 1}, {0, 0}, {3, 0}, {2, 2}};
        cmv = new CMV(data_points);
        assertEquals(true, cmv.lic6_calculate());
    }

    /**
     *  Test a set of invalid points which are expected to return false.
     */
    @Test()
    public void test_lic6_invalid_points() {
        int[][] data_points = new int[][] {{0, 0}, {1, 0}, {2, 0}, {3, 0}};
        
        Parameters.DIST = 0.01; // all points are on the line!
        Parameters.N_PTS = 4;

        CMV cmv = new CMV(data_points);
        assertEquals(false, cmv.lic6_calculate());

        data_points = new int[][] {{0, 0}, {-1, 0}, {-2, 0}, {-3, 0}};
        cmv = new CMV(data_points);
        assertEquals(false, cmv.lic6_calculate());
    }
}
