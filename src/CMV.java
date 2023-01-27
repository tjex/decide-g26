public class CMV {
    
    private Boolean[] matrix = new Boolean[15];
    private int[][] datapoints;
    

    public CMV(int[][] planarDataPoints){
        datapoints = planarDataPoints;
        calculate_lics();

    }

    private void calculate_lics(){
        matrix[0] = lic0_calculate();
        matrix[1] = lic1_calculate();
        matrix[2] = lic2_calculate();
        matrix[3] = lic3_calculate();
        matrix[4] = lic4_calculate();
        matrix[5] = lic5_calculate();
        matrix[6] = lic6_calculate();
        matrix[7] = lic7_calculate();
        matrix[8] = lic8_calculate();
        matrix[9] = lic9_calculate();
        matrix[10] = lic10_calculate();
        matrix[11] = lic11_calculate();
        matrix[12] = lic12_calculate();
        matrix[13] = lic13_calculate();
        matrix[14] = lic14_calculate();
    }

    private Boolean lic0_calculate() {
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