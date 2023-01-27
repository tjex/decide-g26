package main;

public class Main {
    public static void main(String[] args) {
        decide();
    }

    private static void decide() {
        // Get inputs such as:
        // - points
        // - puv_vec
        // - lcm_mat

        //  1. Calculate the CMV
        // CMV cmv = new CMV(data_points);
        // boolean[] cmv_vec = cmv.getVector();

        //  2. Calculate the PUM
        // PUM pum = new PUM(some_argument, another_argument);
        // boolean[][] pum_mat = pum.calculate_pum();

        //  3. Calculate the FUV
        // FUV fuv = new FUV();
        // boolean[] fuv_vec = fuv.calculate_fuv(puv_vec, pum_mat)

        //  4. Launch or no launch?
        // if (fuv.is_all_true(fuv_vec)) print("YES") otherwise print("NO")
    }
}
