package main;

import java.util.Arrays;

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

    public boolean get_cmv_value(int lic_number){
        return cmv_vector[lic_number];
    }

    private boolean lic0_calculate() {
        for (int j = 1; j < this.datapoints.length; j++){ 
            int i = j - 1;
            if(Helper_Functions.euclidean_distance(this.datapoints[j], this.datapoints[i]) > Parameters.LENGTH1 ){
                return true;
            }
        }
        return false;
    }

    private Boolean lic1_calculate() {
        for (int i = 2; i < this.datapoints.length; i++) {

            int[] p1 = this.datapoints[i-2];
            int[] p2 = this.datapoints[i-1];
            int[] p3 = this.datapoints[i];
            double radius = Helper_Functions.circumscribed_circle_radius(p1,p2,p3);

            // if the radius of the circle going through all the points is larger than RADIUS1
            // the points can't be contained within a circle with RADIUS
            if (radius > Parameters.RADIUS1){
                return true;
            }
        }
        return false;
    }

    
    private Boolean lic2_calculate() {
        return false;
    }

    private boolean lic3_calculate() {
        for (int i = 0; i < datapoints.length - 2; i += 3) {
            int[] first = datapoints[i];
            int[] second = datapoints[i + 1];
            int[] third = datapoints[i + 2];
            
            double area = ((double) 1/2) * (Math.abs(first[0]*(second[1]-third[1])+second[0]*(third[1]-first[1])+third[0]*(first[1]-second[1])));

            if(area > Parameters.AREA1){
                return true;
            }
        }
        return false;
    }

    private Boolean lic4_calculate() {
        return false;
    }

    public boolean lic5_calculate() {
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

    private boolean lic9_calculate() {

        //prevents array out of bounds (sum of points between and the two points themselves)
        int points_bound = Parameters.C_PTS + Parameters.D_PTS + 2;
        double pi = Math.PI;
        double ep = Parameters.EPSILON;

        boolean length_cond = Parameters.C_PTS + Parameters.D_PTS <= datapoints.length - 3;
        boolean bound_cond = 1 <= Parameters.C_PTS && 1<= Parameters.D_PTS;

        if(!(length_cond && bound_cond)){
            System.exit(1);
        }

        if(datapoints.length < 5){
            return false;
        }

        for (int i = 0; i < datapoints.length - points_bound ; i += 1) {
            //we have location i, then C_PTS points _between_ then point itself (+1)
            //third location follows same logic, however, i is second_point_location            
            int second_point_location = i + Parameters.C_PTS + 1 ;
            int third_point_location = second_point_location + Parameters.D_PTS + 1 ;
            
            int[] first = datapoints[i];
            int[] second = datapoints[second_point_location];
            int[] third = datapoints[third_point_location];

            //angle undefined, ray coincides with vertex, LIC not satisfied by those points
            if(Arrays.equals(first,second) | Arrays.equals(second,third)){
                continue;
            }

            boolean condition1 = Helper_Functions.three_point_angle(first, second, third) < (pi - ep);
            boolean condition2 = Helper_Functions.three_point_angle(first, second, third) > (pi + ep);

            if(condition1 || condition2){
                return true;
            }

        }

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
