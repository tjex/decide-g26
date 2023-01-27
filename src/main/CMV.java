package main;
import java.util.ArrayList;
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
        for (int i = 2; i < this.datapoints.length; i++) {

            int[] p1 = this.datapoints[i-2];
            int[] p2 = this.datapoints[i-1];
            int[] p3 = this.datapoints[i];
            double radius = circumscribed_circle_radius(p1,p2,p3);

            // if the radius of the circle going through all the points is larger than RADIUS1
            // the points can't be contained within a circle with RADIUS
            if (radius > Parameters.RADIUS1){
                return true;
            }
        }
        return false;
    }

    /// Calculates the radius of a circle circumscribing the triangle described by the three points
    // source for equation: https://hratliff.com/posts/2019/02/curvature-of-three-points/
    public static double circumscribed_circle_radius(int[] p1, int[] p2, int[] p3){
        int x1 = p1[0];
        int y1 = p1[1];
        int x2 = p2[0];
        int y2 = p2[1];
        int x3 = p3[0];
        int y3 = p3[1];

        double a = Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2);
        double b = Math.pow((x3 - x2), 2) + Math.pow((y3 - y2), 2);
        double c = Math.pow((x1 - x3), 2) + Math.pow((y1 - y3), 2);
        double d = Math.abs(((x2 - x1) * (y3 - y2)) - ((y2 - y1) * (x3 - x2)));
        double radius = Math.sqrt(a * b * c) /(2 * d);

        return radius;
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
