package main;


import java.util.ArrayList;

import java.util.Arrays;


public class CMV {
    
    private boolean[] cmv_vector = new boolean[15];
    private double[][] datapoints;

    

    public CMV(double[][] planarDataPoints){
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

            double[] p1 = this.datapoints[i-2];
            double[] p2 = this.datapoints[i-1];
            double[] p3 = this.datapoints[i];
            double radius = Helper_Functions.circumscribed_circle_radius(p1,p2,p3);

            // if the radius of the circle going through all the points is larger than RADIUS1
            // the points can't be contained within a circle with RADIUS
            if (radius > Parameters.RADIUS1){
                return true;
            }
        }
        return false;
    }

    
    private boolean lic2_calculate() {
        final double EPSILON = Parameters.EPSILON;
        
        for (int k = 2; k < datapoints.length; k++){ 
            int j = k - 1;
            int i = j - 1;
            if(datapoints[j][0] == datapoints[i][0] && datapoints[j][1] == datapoints[i][1]){
                return false;
            }
            else if(datapoints[j][0] == datapoints[k][0] && datapoints[j][1] == datapoints[k][1]){
                return false;
            }
            double angle = Helper_Functions.three_point_angle(datapoints[i], datapoints[j], datapoints[k]) % (2*Math.PI);
            if(angle < (Math.PI - EPSILON) || angle > (Math.PI + EPSILON)){
                return true;
            }
        }
        return false;
    }

    private boolean lic3_calculate() {
        for (int i = 0; i < datapoints.length - 2; i++) {
            double[] first = datapoints[i];
            double[] second = datapoints[i + 1];
            double[] third = datapoints[i + 2];
            
            double area = Helper_Functions.triangle_vertex_area(first, second, third);

            if(area > Parameters.AREA1){
                return true;
            }
        }
        return false;
    }

    /*
     * Return true if Q_PTS consecutive datapoints lie in QUAD different quadrants.
     * This is done through having a nested for loop which takes a set of datapoints
     * of size Q_PTS and iterates through them. Each unique beloning quadrant is stored
     * in a ArrayList where the size of it gets compared to QUAD. If the size = QUAD return
     * true, else continue or return false.   
     * 
     */

     private Boolean lic4_calculate() {
        final int Q_PTS = Parameters.Q_PTS;
        final int QUADS = Parameters.QUADS;
        int quadrant;
        ArrayList<Integer> quadrants = new ArrayList<>(3);
        for (int i = 0; i < datapoints.length; i++){ 
            quadrants.add(Helper_Functions.quadEvaluation((datapoints[i])));
            for (int j = i+1; j < i + Q_PTS; j++){
                if(j > datapoints.length - 1){
                    continue;
                }
                if(quadrants.size() == QUADS){
                    return true;
                }
                quadrant = Helper_Functions.quadEvaluation(datapoints[j]);
                if(!quadrants.contains(quadrant)){
                    quadrants.add(quadrant);
                }
            }
            
            if(quadrants.size() == QUADS){
                return true;
            }
            quadrant = -1;
            quadrants.clear();
        }
        return false;
    }
    
    
    /*
     * Returns true if two adjecent datapoints I and I+1 fullfills the
     * condition datapoints[I+1][0] - datapoints[I][0] < 0. These values
     * corresponds to the x-axis. If condition not meet return false. 
     */
    private boolean lic5_calculate() {
        for (int j = 1; j < datapoints.length; j++){ 
            int i = j - 1;
            if(datapoints[j][0] - datapoints[i][0] < 0){
                return true;
            }
        }
        return false;
    }

    private boolean lic6_calculate() {
        int N_PTS = Parameters.N_PTS;
        double DIST = Parameters.DIST;
        if(N_PTS < 3 || N_PTS > datapoints.length || DIST <= 0)
            return false;
        
        // Go through each set of N_PTS consecutive points
        for (int point = 0; point < datapoints.length - (N_PTS - 1); point++) {
            // First and last point within the set of consecutive points
            int p1 = point;
            int p2 = Math.min(p1 + N_PTS - 1, datapoints.length - 1);
            
            // Coordinates for first and last point
            double x1 = datapoints[p1][0];
            double y1 = datapoints[p1][1];
            double x2 = datapoints[p2][0];
            double y2 = datapoints[p2][1];

            // Condition when first and last are "identical".
            // Due to p1 and p2 cannot have the same index, that require N_PTS be greater than 
            // datapoints.length, I will treat identical as close enough within the plane.
            double epsilon = 0.1;
            if (Helper_Functions.euclidean_distance(datapoints[p1], datapoints[p2]) < epsilon) {
                // Compare the distance to all other points. If the max distance
                // is greater than DIST, return true.
                double max_distance = -1;
                for (int p = p1 + 1; p < p2; p++) {
                    double d = Helper_Functions.euclidean_distance(datapoints[p], datapoints[p1]);
                    if (d > max_distance)
                        max_distance = d;
                }
                if (max_distance > DIST)
                    return true;
            }

            // Line between p1-p2. Check all other points distance to line.
            // If any set contains at least one point with distance greater than DIST, return true.
            for (int p = p1 + 1; p < p2; p++) {
                double x0 = datapoints[p][0];
                double y0 = datapoints[p][1];
                double distance = 
                    Math.abs((x2 - x1) * (y1 - y0) - (x1 - x0) * (y2 -y1)) /
                    Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
                if (distance > DIST)
                    return true;
            }
        }

        return false;
    }
    /*
     * Returns true if there exist two datapoints (I,J), seperated by K_PTS datapoints,
     * that are at a distance greater than LENGTH1. 
     */
    
    private boolean lic7_calculate() {
        
        final int K_PTS = Parameters.K_PTS;
        final double LENGTH1 = Parameters.LENGTH1;
        if(datapoints.length < 3){
            return false;
        }
        for(int i = 0; i < datapoints.length; i++){
            int j = i + K_PTS + 1;
            if(j > datapoints.length - 1){
                break;
            }
            
            double[] vectorIJ = Helper_Functions.vector_subtraction(datapoints[j],datapoints[i]);
            double magnitudeIJ = Helper_Functions.vector_magnitude(vectorIJ);
            if(magnitudeIJ > LENGTH1){
                return true;
            }
        }
        return false;
    }

    private boolean lic8_calculate() {
        //The condition is not met when NUMPOINTS < 5.
        if(this.datapoints.length < 5){
            return false;
        }

        for (int i = 0; i < this.datapoints.length-Parameters.A_PTS-Parameters.B_PTS - 2; i++) {
            int p2_index = i + Parameters.A_PTS + 1;
            int p3_index = p2_index + Parameters.B_PTS + 1;

            double[] p1 = this.datapoints[i];
            double[] p2 = this.datapoints[p2_index];
            double[] p3 = this.datapoints[p3_index];
            double radius = Helper_Functions.circumscribed_circle_radius(p1,p2,p3);

            // if the radius of the circle going through all the points is larger than RADIUS1
            // the points can't be contained within a circle with RADIUS
            if (radius > Parameters.RADIUS1){
                return true;
            }
        }

        return false;
    }

    private boolean lic9_calculate() {

        //prevents array out of bounds (sum of points between and the two points themselves)
        int points_bound = Parameters.C_PTS + Parameters.D_PTS + 2;
        double pi = Math.PI;
        double ep = Parameters.EPSILON;

        boolean length_cond = Parameters.C_PTS + Parameters.D_PTS <= datapoints.length - 3;
        boolean bound_cond = 1 <= Parameters.C_PTS && 1<= Parameters.D_PTS;

        if(datapoints.length < 5){
            return false;
        }

        for (int i = 0; i < datapoints.length - points_bound ; i += 1) {
            //we have location i, then C_PTS points _between_ then point itself (+1)
            //third location follows same logic, however, i is second_point_location            
            int second_point_location = i + Parameters.C_PTS + 1 ;
            int third_point_location = second_point_location + Parameters.D_PTS + 1 ;
            
            double[] first = datapoints[i];
            double[] second = datapoints[second_point_location];
            double[] third = datapoints[third_point_location];

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

    private boolean lic10_calculate() {

        int points_bound = Parameters.E_PTS + Parameters.F_PTS + 2;

        if(datapoints.length < 5){
            return false;
        }

        for (int i = 0; i < datapoints.length - points_bound ; i += 1) {
           
            int second_point_location = i + Parameters.E_PTS + 1 ;
            int third_point_location = second_point_location + Parameters.F_PTS + 1 ;
            
            double[] first = datapoints[i];
            double[] second = datapoints[second_point_location];
            double[] third = datapoints[third_point_location];

            if(Helper_Functions.triangle_vertex_area(first, second, third) > Parameters.AREA1){
                return true;
            }

        }

        return false;
    }
    /*
     * Return true if there exist a set of datapoints {I,J} that are seperated
     * by G_PTS datapoints and fullfills the condition X[j] - X[I] < 0. Meaning
     * the datapoints (I,J) x-axis subtracted as above has to be less than 0.
     * If this condition is not fullfilled the function return false.  
     */
    private boolean lic11_calculate() {
        if(datapoints.length < 3){
            return false;
        }
        final int G_PTS = Parameters.G_PTS;

        for(int i = 0; i < datapoints.length; i++){
            int j = i + G_PTS + 1;
            if(j > datapoints.length - 1){
                return false;
            }
            if(datapoints[j][0] - datapoints[i][0] < 0){
                return true;
            }
        }
        return false;
    }
    /* 
     * The function returns true if both checkBigger and checkSmaller is true:
     * 1. checkBigger is true if there exist two datapoints, seperated by K_PTS
     * datapoints, has a magnitude > LENGTH1.
     * 2. checkSmaller is true if there exist two datapoints, seperated by K_PTS
     * datapoints, has a magnitude < LENGTH2.
     * Returns false if 1. or 2. is not meet.
     */
    public boolean lic12_calculate() {
        boolean checkBigger = false;
        boolean checkSmaller = false;
        final int K_PTS = Parameters.K_PTS;
        final double LENGTH1 = Parameters.LENGTH1;
        final double LENGTH2 = Parameters.LENGTH2;
        for(int i = 0; i < datapoints.length; i++){
            int j = i + K_PTS + 1;
            if (j > datapoints.length - 1){
                break;
            }
            double[] vectorIJ = Helper_Functions.vector_subtraction(datapoints[j],datapoints[i]);    
            double magnitudeIJ = Helper_Functions.vector_magnitude(vectorIJ);                   
            if(magnitudeIJ > LENGTH1){
                checkBigger = true;
            }
            if(magnitudeIJ < LENGTH2){
                checkSmaller = true;
            }
        }
        if(checkSmaller && checkBigger){
            return true;
        }
        return false;
    }

    /*
        Returns true if the following requirements are fulfilled:
        1.  There exists at least one set of three data points,
            separated by exactly A PTS and B PTS consecutive intervening points
            that cannot be contained within or on a circle of radius RADIUS1.
        2.  There exists at least one set of three data points,
            separated by exactly A PTS and B PTS consecutive intervening points
            that can be contained in or on a circle of radius RADIUS2.
        3.  NUMPOINTS > 5
     */
    private Boolean lic13_calculate() {
        if(datapoints.length < 5){
            return false;
        }

        boolean requirement_1 = false;
        boolean requirement_2 = false;

        for (int i = 0; i < this.datapoints.length-Parameters.A_PTS-Parameters.B_PTS - 2; i++) {
            int p2_index = i + Parameters.A_PTS + 1;
            int p3_index = p2_index + Parameters.B_PTS + 1;

            double[] p1 = this.datapoints[i];
            double[] p2 = this.datapoints[p2_index];
            double[] p3 = this.datapoints[p3_index];
            double radius = Helper_Functions.circumscribed_circle_radius(p1,p2,p3);

            // if the radius of the circle going through all the points is larger than RADIUS1
            // the points can't be contained within a circle with RADIUS
            if (radius > Parameters.RADIUS1){
                requirement_1 = true;
            }

            if(radius <= Parameters.RADIUS2){
                requirement_2 = true;
            }

            if(requirement_1 && requirement_2){
                return true;
            }
        }


        return false;
    }

    /* 
     * Returns a boolean which has two requirment to be true:
     * 1. There exist a triplet of dataset i, j, k which make up a area bigger than AREA1
     * 2. There exist a triplet of dataset i, j, k which make up a area smaller than AREA2.
     * 
     */
    private Boolean lic14_calculate() {
        final int E_PTS = Parameters.E_PTS;
        final int F_PTS = Parameters.F_PTS;
        final double AREA1 = Parameters.AREA1;
        final double AREA2 = Parameters.AREA2;
        boolean checkA1 = false;
        boolean checkA2 = false;
        for(int i = 0; i < datapoints.length; i++){
            int j = i + E_PTS + 1;
            int k = j + F_PTS + 1;
            if(j > datapoints.length - 1 || k > datapoints.length - 1){
                break;
            }
            
            if(Helper_Functions.triangle_vertex_area(datapoints[i],datapoints[j],datapoints[k]) > AREA1){
                checkA1 = true;
            }
            if(Helper_Functions.triangle_vertex_area(datapoints[i],datapoints[j],datapoints[k]) < AREA2){
                checkA2 = true;
            }
        }
        if(checkA1 && checkA2){
            return true;
        }
        return false;
    }
    
}