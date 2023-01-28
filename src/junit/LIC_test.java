package junit;

import main.CMV;
import main.Helper_Functions;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LIC_test {
    /*
    K_PTS = 2
    LENGTH1 = 2
    LENGTH2 = 6
    */
    @Test
    public void check_If_Valid_yields_true(){
        int[][] datapoints = {{1,1},{1,1},{1,1},{6,2},{1,1},{1,1}};
        CMV cmv = new CMV(datapoints);
        assertTrue(cmv.lic12_calculate());

    }
}
