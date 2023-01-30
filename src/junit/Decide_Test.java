package junit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.Main;
import main.Parameters;

public class Decide_Test {
 
    /**
     *  Testing the decide launch function with default parameters.
     * 
     *  Expected: True
     */
    @Test
    public void test_decide_yes() {
        Main launcher = new Main();
        boolean launch = launcher.decide();
        assertEquals(true, launch);
    }

    /**
     *  Testing the decide launch function with default parameters, but modified
     *  data points.
     * 
     *  Expected: False
     */
    @Test
    public void test_decide_no() {
        Main launcher = new Main();

        Parameters.DATA_POINTS = new double[][] { {0,0}, {1,0}, {2,0}, {3,0}, {4,0} };

        boolean launch = launcher.decide();
        assertEquals(false, launch);
    }
}
