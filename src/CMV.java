public class CMV {
    
    private boolean[] cmv_vector = new boolean[15];
    private int[][] datapoints;

    

    public CMV(int[][] planarDataPoints){
        datapoints = planarDataPoints;
        calculate_lics();

    }

    private void calculate_lics(){
        cmv_vector[0] = lic0_calculate();
        cmv_vector[1] = lic1_calculate();
        cmv_vector[2] = lic2_calculate();
        cmv_vector[3] = lic3_calculate();
        cmv_vector[4] = lic4_calculate();
        cmv_vector[5] = lic5_calculate();
        cmv_vector[6] = lic6_calculate();
        cmv_vector[7] = lic7_calculate();
        cmv_vector[8] = lic8_calculate();
        cmv_vector[9] = lic9_calculate();
        cmv_vector[10] = lic10_calculate();
        cmv_vector[11] = lic11_calculate();
        cmv_vector[12] = lic12_calculate();
        cmv_vector[13] = lic13_calculate();
        cmv_vector[14] = lic14_calculate();
    }

    public static double euclidean_distance(int[] a, int[] b){
        return Math.sqrt(Math.pow((b[0]-a[0]), 2) + Math.pow((b[1]-a[1]), 2));
    }

    private boolean lic0_calculate() {
        for (int j = 1; j < this.datapoints.length; j++){ 
            int i = j - 1;
            if(euclidean_distance(this.datapoints[j], this.datapoints[i]) >= Parameters.LENGTH1 ){
                return true;
            }
        }
        return false;
    }

    private Boolean lic1_calculate() {
        return false;
    }
    
    private Boolean lic2_calculate() {
        return false;
    }

    private Boolean lic3_calculate() {
        return false;
    }

    private Boolean lic4_calculate() {
        return false;
    }

    private Boolean lic5_calculate() {
        for (int j = 1; j < datapoints.length; j++){ 
            int i = j - 1;
            if(datapoints[j][0] - datapoints[i][0] < 0){
                return true;
            }
        }
        return false;
    }

    private Boolean lic6_calculate() {
        return false;
    }
    
    private Boolean lic7_calculate() {
        return false;
    }

    private Boolean lic8_calculate() {
        return false;
    }

    private Boolean lic9_calculate() {
        return false;
    }

    private Boolean lic10_calculate() {
        return false;
    }

    private Boolean lic11_calculate() {
        return false;
    }
    
    private Boolean lic12_calculate() {
        return false;
    }

    private Boolean lic13_calculate() {
        return false;
    }

    private Boolean lic14_calculate() {
        return false;
    }
    
}
