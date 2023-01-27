
// PUM function
// The Conditions Met Vector (CMV) can now be used in conjunction with the Logical Connector Matrix (LCM) to form the Preliminary Unlocking Matrix (PUM). The entries in the LCM represent the logical connectors to be used between pairs of LICs to determine the corresponding entry in the PUM.

// CONDITIONS
// LCM[i,j] represents the boolean operator to be applied to CMV[i] and CMV[j].
// PUM[i,j] is set according to the result of this operation
// eg: LCM[i, j] with CMV[i, j] as a state check = PUM[i, j]?


// (Note that the LCM is symmetric, i.e. LCM[i,j]=LCM[j,i] for all i and j.)

package main;
public class PUM {
    
    private int[][] lcm_datapoints;
    private int[] puv_booleans;
    private int[][] pum_matrix;


    public PUM(int[][] lcm_datapoints, int[] puv_booleans){
        // get LCM vector
        lcm_datapoints = lcm_datapoints;

        // get CMV vector
        cmv_vector = cmv_vector;
    }

    public void calculate_pum(){

    // If LCM[i,j] is NOTUSED, then PUM[i,j] should be set to true.
    // If LCM[i,j] is ANDD, PUM[i,j] should be set to true 
    // only if (CMV[i] AND CMV[j]) is true.
    
        for (int i = 0; i < lcm_datapoints.length; i++){
            for (int j = 0; j < lcm_datapoints[j].length; j++){
                if (lcm_datapoints[i][j] == -1 || (lcm_datapoints[i][j] == 1 && cmv_vector[i][j] == 1)){
                    pum_matrix[i][j] = 1;
                    System.out.println("in if");
                } else {
                    System.out.println("in else");
                    // continue or set to false? 
                }
            }
        }
    }
}
