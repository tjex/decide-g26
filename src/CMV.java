package main;
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
    
    private int lic7_calculate() {
        final int K_PTS = Parameters.K_PTS;
        final double LENGTH1 = Parameters.LENGTH1;
        if(datapoints.length < 3){
            return 0;
        }
        for(int i = 0; i < datapoints.length; i++){
            if(i + K_PTS > datapoints.length - 1){
                return 0;
            }
            int j = i + K_PTS;
            double[] vectorIJ = {datapoints[j][0] - datapoints[i][0], datapoints[j][1] - datapoints[i][1]}; 
            double magnitudeIJ = Math.sqrt(Math.pow(vectorIJ[0],2) + Math.pow(vectorIJ[1],2));
            if(magnitudeIJ > LENGTH1){
                return 1;
            }
        }
        return 0;
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
