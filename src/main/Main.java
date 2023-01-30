package main;

import java.util.Random;

/**
 *  Main entry point of the Decide Missile Program.
 * 
 *  decide() assesses which launch conditions are relevant for the immediate situation. 
 *  If all combinations of launch conditions are met, Decide() will issue a launch-unlock 
 *  signal, enabling the launch of the defensive projectile. 
 * 
 *  @author andersblomqvist
 *  @author a-kbd
 *  @author coffecup25
 *  @author darpos
 *  @author tjex
 */
public class Main {
    public static void main(String[] args) {
        Main launcher = new Main();
        boolean launch = launcher.decide();
        if (launch)
            System.out.println("YES");
        else
            System.out.println("NO");
    }

    public Main() {
        init_parameters();
    }

    public boolean decide() {
        //  1. Calculate the CMV
        CMV cmv = new CMV(Parameters.DATA_POINTS);
        boolean[] cmv_vec = getCMV(cmv);

        //  2. Calculate the PUM
        PUM pum = new PUM(Parameters.LCM_MAT, cmv_vec);
        boolean[][] pum_mat = pum.calculate_pum();

        //  3. Calculate the FUV
        FUV fuv = new FUV();
        boolean[] fuv_vec = fuv.calculate_fuv(Parameters.PUV_VEC, pum_mat);

        //  4. Launch or no launch?
        if (fuv.is_all_true(fuv_vec))
            return true;
        else
            return false;
    }

    /**
     *  Retrieve all LIC into one boolean vector.
     * 
     *  @param cmv CMV object
     *  @returns boolean vector contain value for each LIC.
     */
    private boolean[] getCMV(CMV cmv) {
        boolean[] vec = new boolean[15];
        for(int i = 0; i < 15; i++)
            vec[i] = cmv.get_cmv_value(i);
        return vec;
    }

    /**
     *  Sets all the parameters.
     */
    private void init_parameters() {

        // Fill data points
        Random random = new Random();
        for(int i = 0; i < Parameters.DATA_POINTS.length; i++) {
            int x = random.nextInt(-10, 10 + 1);
            int y = random.nextInt(-10, 10 + 1);
            Parameters.DATA_POINTS[i][0] = x;
            Parameters.DATA_POINTS[i][1] = y;
        }

        // Fill LCM
        // Following the Table 1 in decide.pdf
        for(int i = 0; i < Parameters.LCM_MAT.length; i++)
            for(int j = 0; j < Parameters.LCM_MAT.length; j++)
                Parameters.LCM_MAT[i][j] = -1;
        Parameters.LCM_MAT[0][0] = 1;
        Parameters.LCM_MAT[0][1] = 1;
        Parameters.LCM_MAT[0][2] = 0;
        Parameters.LCM_MAT[0][3] = 1;

        Parameters.LCM_MAT[1][0] = 1;
        Parameters.LCM_MAT[1][1] = 1;
        Parameters.LCM_MAT[1][2] = 0;
        Parameters.LCM_MAT[1][3] = 0;

        Parameters.LCM_MAT[2][0] = 0;
        Parameters.LCM_MAT[2][1] = 0;
        Parameters.LCM_MAT[2][2] = 1;
        Parameters.LCM_MAT[2][3] = 1;

        Parameters.LCM_MAT[3][0] = 1;
        Parameters.LCM_MAT[3][1] = 0;
        Parameters.LCM_MAT[3][2] = 1;
        Parameters.LCM_MAT[3][3] = 1;

        // Fill PUV
        for (int i = 0; i < Parameters.PUV_VEC.length; i++) {
            if (i % 2 == 0)
                Parameters.PUV_VEC[i] = true;
            else
                Parameters.PUV_VEC[i] = false;
        }

        Parameters.LENGTH1  = 6.0;
        Parameters.RADIUS1  = 3.0;
        Parameters.EPSILON  = 1.0;
        Parameters.AREA1    = 8.0;
        Parameters.Q_PTS    = 3;
        Parameters.QUADS    = 2;
        Parameters.DIST     = 5.0;
        Parameters.N_PTS    = 4;
        Parameters.K_PTS    = 0;
        Parameters.A_PTS    = 3;
        Parameters.B_PTS    = 3;
        Parameters.C_PTS    = 3;
        Parameters.D_PTS    = 4;
        Parameters.E_PTS    = 5;
        Parameters.F_PTS    = 3;
        Parameters.G_PTS    = 4;
        Parameters.LENGTH2  = 6.0;
        Parameters.RADIUS2  = 4.0;
        Parameters.AREA2    = 7.0;
    }
}
