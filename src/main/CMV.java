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

    public Boolean lic6_calculate() {
        int N_PTS = Parameters.N_PTS;
        double DIST = Parameters.DIST;
        if(N_PTS < 3 || N_PTS > datapoints.length || DIST <= 0)
            return false;
        
        // Go through each set of N_PTS consecutive points
        for (int point = 0; point < datapoints.length; point++) {
            // First and last point within the set of consecutive points
            int p1 = point;
            int p2 = (point + N_PTS - 1) % datapoints.length;
            
            // Coordinates for first and last point
            int x1 = datapoints[p1][0];
            int y1 = datapoints[p1][1];
            int x2 = datapoints[p2][0];
            int y2 = datapoints[p2][1];

            // Condition when first and last are "identical".
            // Due to p1 and p2 cannot have the same index, that require N_PTS be greater than 
            // datapoints.length, I will treat identical as close enough within the plane.
            double epsilon = 0.1;
            if (Helper_Functions.euclidean_distance(datapoints[p1], datapoints[p2]) < epsilon) {
                // Compare the distance to all other points. If the max distance
                // is greater than DIST, return true.
                double max_distance = -1;
                for (int p = p1 + 1; p < p1 + N_PTS - 1; p++) {
                    int idx = p % datapoints.length;
                    double d = Helper_Functions.euclidean_distance(datapoints[idx], datapoints[p1]);
                    if (d > max_distance)
                        max_distance = d;
                }
                if (max_distance > DIST)
                    return true;
            }

            // Line between p1-p2. Check all other points distance to line.
            // If any set contains at least one point with distance greater than DIST, return true.
            for (int p = p1 + 1; p < p1 + N_PTS - 1; p++) {
                int idx = p % datapoints.length;
                int x0 = datapoints[idx][0];
                int y0 = datapoints[idx][1];
                double distance = 
                    Math.abs((x2 - x1) * (y1 - y0) - (x1 - x0) * (y2 -y1)) /
                    Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
                if (distance > DIST)
                    return true;
            }
        }

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
