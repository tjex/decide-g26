package junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import main.FUV;

public class FUVTests {

    /**
     *  These test inputs should give
     */
    @Test
    public void test_fuv_vector_launch() {
        // TODO: Implement test
    }

    @Test
    public void test_fuv_vector_no_launch() {
        // TODO: Implement test
    }

    /**
     *  Test the helper function for checking if all values are true for a boolean vector.
     * 
     *  Expected:  [true, true, true ... true] => true
     *             [true, false, true] => false
     *             [] => false 
     */
    @Test
    public void test_is_all_boolean_function() {
        boolean[] b1 = new boolean[] {true, true, true, true, true};
        boolean[] b2 = new boolean[] {true, true, true, false, true};
        boolean[] b3 = new boolean[] {false, false, false, false, false};
        boolean[] b4 = new boolean[] {};

        FUV fuv = new FUV();
        assertTrue(fuv.is_all_true(b1));
        assertFalse(fuv.is_all_true(b2));
        assertFalse(fuv.is_all_true(b3));
        assertFalse(fuv.is_all_true(b4));
    }
}