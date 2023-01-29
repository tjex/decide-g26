package junit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.PUM;

public class PUM_Test {

    /**
     *  Checks the #1 entry on page 7 from decide.pdf
     * 
     *  PUM[0, 1] should be false because LCM[0, 1] is ANDD, and at least one
     *  of CMV[0] and CMV[1] is false.
     * 
     *  Expected: PUM[0][1] = false
     */
    @Test
    public void test_pum_entry1() {

        // LCM[0, 1] is ANDD
        int[][] lcm = new int[15][15];
        lcm[0][1] = 1;

        // Now, CMV[0] = true, and CMV[1] = false
        boolean[] cmv = new boolean[15];
        cmv[0] = true;

        PUM pum = new PUM(lcm, cmv);
        boolean[][] pum_mat = pum.calculate_pum();
        assertEquals(false, pum_mat[0][1]);

        // Now, CMV[0] = false, and CMV[1] = true
        cmv = new boolean[15];
        cmv[1] = true;

        pum = new PUM(lcm, cmv);
        pum_mat = pum.calculate_pum();
        assertEquals(false, pum_mat[0][1]);
    }

    /**
     *  Checks the #2 entry on page 7 from decide.pdf
     * 
     *  PUM[0, 2] should be true because LCM[0, 2] is ORR, and at least one
     *  of CMV[0] and CMV[2] is true.
     * 
     *  Expected: PUM[0][2] = true
     */
    @Test
    public void test_pum_entry2() {

        // LCM[0, 2] is ORR
        int[][] lcm = new int[15][15];
        lcm[0][2] = 0;

        // Now, CMV[0] = true, and CMV[2] = false
        boolean[] cmv = new boolean[15];
        cmv[0] = true;

        PUM pum = new PUM(lcm, cmv);
        boolean[][] pum_mat = pum.calculate_pum();
        assertEquals(true, pum_mat[0][2]);

        // Now, CMV[0] = false, and CMV[2] = true
        cmv = new boolean[15];
        cmv[2] = true;

        pum = new PUM(lcm, cmv);
        pum_mat = pum.calculate_pum();
        assertEquals(true, pum_mat[0][2]);
    }

    /**
     *  Checks the #3 entry on page 7 from decide.pdf
     * 
     *  PUM[1, 2] should be true because LCM[1, 2] is ORR, and at least one
     *  of CMV[1] and CMV[2] is true.
     * 
     *  Expected: PUM[1][2] = true
     */
    @Test
    public void test_pum_entry3() {

        // LCM[0, 2] is ORR
        int[][] lcm = new int[15][15];
        lcm[1][2] = 0;

        // Now, CMV[0] = true, and CMV[2] = false
        boolean[] cmv = new boolean[15];
        cmv[1] = true;

        PUM pum = new PUM(lcm, cmv);
        boolean[][] pum_mat = pum.calculate_pum();
        assertEquals(true, pum_mat[1][2]);

        // Now, CMV[0] = false, and CMV[2] = true
        cmv = new boolean[15];
        cmv[2] = true;

        pum = new PUM(lcm, cmv);
        pum_mat = pum.calculate_pum();
        assertEquals(true, pum_mat[1][2]);
    }

    /**
     *  Checks the #5 entry on page 7 from decide.pdf
     * 
     *  PUM[0, 4] should be true because LCM[0, 4] is NOTUSED
     * 
     *  Expected: PUM[0][4] = true
     */
    @Test
    public void test_pum_entry5() {

        // LCM[0, 2] is NOTUSED
        int[][] lcm = new int[15][15];
        lcm[0][4] = -1;

        boolean[] cmv = new boolean[15];

        PUM pum = new PUM(lcm, cmv);
        boolean[][] pum_mat = pum.calculate_pum();
        assertEquals(true, pum_mat[0][4]);
    }
}
