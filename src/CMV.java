import java.util.ArrayList;

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
        final int Q_PTS = Parameters.Q_PTS;
        final int QUADS = Parameters.QUADS;
        int quadrant;
        ArrayList<Integer> quadrants = new ArrayList<>(3);
        for (int i = 0; i < datapoints.length; i++){ 
            quadrants.add(quadEvaluation(datapoints[i]));
            for (int j = i+1; j < Q_PTS; j++){
                if(j >= datapoints.length - 1){
                    return false;
                }
                if(quadrants.size() == QUADS){
                    return true;
                }
                quadrant = quadEvaluation(datapoints[j]);
                if(!quadrants.contains(quadrant)){
                    quadrants.add(quadrant);
                }
            }
        }
        return false;
    }

    private int quadEvaluation(int[] datapoint){
        if(datapoint[0] >= 0 && datapoint[1] >= 0){
            return 1;
        }
        else if(datapoint[0] <= 0 && datapoint[1] >= 0){
            return 2;
        }
        else if(datapoint[0] <= 0 && datapoint[1] <= 0){
            return 3;
        }
        else{
            return 4;
        }
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