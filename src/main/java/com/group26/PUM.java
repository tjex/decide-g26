package com.group26;

public class PUM {
    
    private int[][] lcm_datapoints;
    private boolean[] cmv_vector;
    private boolean[][] pum_matrix;

    public PUM(int[][] lcm_datapoints, boolean[] cmv_vector){
        // get LCM vector
        this.lcm_datapoints = lcm_datapoints;

        // get CMV vector
        this.cmv_vector = cmv_vector;
    }

    public boolean[][] calculate_pum() {
        pum_matrix = new boolean[15][15];
        for (int i = 0; i < lcm_datapoints.length; i++) {
            for (int j = 0; j < lcm_datapoints[i].length; j++) {
                if  (lcm_datapoints[i][j] == -1 ||
                    (lcm_datapoints[i][j] == 1 && cmv_vector[i] && cmv_vector[j]) ||
                    (lcm_datapoints[i][j] == 0 && (cmv_vector[i] || cmv_vector[j]))) {
                    pum_matrix[i][j] = true;
                }
            }
        }
        return pum_matrix;
    }
}
