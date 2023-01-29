package main;

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
        boolean lanch = launcher.decide();
        if (lanch)
            System.out.println("YES");
        else
            System.out.println("NO");
    }

    public Main() {
        init_parameters();
    }

    private boolean decide() {
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
        // TODO: set all variables in Parameter.class
    }
}
