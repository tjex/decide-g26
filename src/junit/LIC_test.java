package junit;

import main.CMV;
import main.Helper_Functions;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LIC_test {
    @Test
    public void test_circumscribed_circle_radius() {
        int[][] datapoints = {{0,3},{3,0},{-3,0}};
        double radius = Helper_Functions.circumscribed_circle_radius(datapoints[0],datapoints[1], datapoints[2]);
        assertTrue(radius==3.0);
    }
}
