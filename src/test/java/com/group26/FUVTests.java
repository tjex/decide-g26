package com.group26;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


public class FUVTests {

    /**
     *  Test FUV calculation when all elements in PUV are false and true in PUM.
     *  Expected true for all elements in FUV.
     */
    @Test
    public void test_fuv_vector_with_pum_all_true() {
        boolean[] puv = new boolean[15];
        boolean[][] pum = new boolean[15][15];
        for(int i = 0; i < pum.length; i++)
            for(int j = 0; j < pum[i].length; j++)
                pum[i][j] = true;

        FUV fuv = new FUV();
        boolean[] res = fuv.calculate_fuv(puv, pum);
        assertTrue(fuv.is_all_true(res));        
    }

    /**
     *  Test FUV calculation when all elements in PUV and PUM are false.
     *  Expected true for all elements in FUV.
     */
    @Test
    public void test_fuv_vector_with_puv_all_false() {
        boolean[] puv = new boolean[15];
        boolean[][] pum = new boolean[15][15];

        FUV fuv = new FUV();
        boolean[] res = fuv.calculate_fuv(puv, pum);
        assertTrue(fuv.is_all_true(res));
    }

    /**
     *  Test FUV calculation which results in a no launch (at least one false element in FUV)
     */
    @Test
    public void test_fuv_vector_no_launch() {
        boolean[] puv = new boolean[15];
        boolean[][] pum = new boolean[15][15];

        for (int i = 0; i < puv.length; i++)
            puv[i] = true;

        FUV fuv = new FUV();
        boolean[] res = fuv.calculate_fuv(puv, pum);
        assertFalse(fuv.is_all_true(res));
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