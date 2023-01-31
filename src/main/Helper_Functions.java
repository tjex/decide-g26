package main;

public class Helper_Functions {
    // Calculates the radius of the smallest enclosing circle of the three points
    public static double smallest_enclosing_radius(double[] p1, double[] p2, double[] p3){
        double dist_p1_to_p2 = euclidean_distance(p1,p2);
        double dist_p1_to_p3 = euclidean_distance(p1,p3);
        double dist_p2_to_p3 = euclidean_distance(p2,p3);

        // returns t
        return Math.max(Math.max(dist_p1_to_p2, dist_p1_to_p3),dist_p2_to_p3)/2.0;
    }

    public static double euclidean_distance(double[] a, double[] b){
        return Math.sqrt(Math.pow((b[0]-a[0]), 2) + Math.pow((b[1]-a[1]), 2));
    }

    //returns vector magnitude, not rounded 
    public static double vector_magnitude(double[] a){
        double[] origin = {0,0};
        double dist = euclidean_distance(a, origin);
        return dist;
    }

    public static double dot_product(double[] a, double[] b){
        return (a[0]*b[0])+(a[1]*b[1]);
    }

    //returns a vector pointing towards the vector a
    public static double[] vector_subtraction(double[] a, double[] b){
        double[] out = {a[0]-b[0], a[1]-b[1]};
        return out;
    }

    //from three points, where the middle argument is the vertex and the other two are the ends of rays from that vertex
    //return the angle  (in radians!) between those rays
    public static double three_point_angle(double[] ray_end_1, double[] vertex, double[] ray_end_2){
        double[] vector1 = vector_subtraction(ray_end_1, vertex);
        double[] vector2 = vector_subtraction(ray_end_2, vertex);
        
        double fraction = (dot_product(vector1, vector2))/(vector_magnitude(vector1)*vector_magnitude(vector2));
        double angle = Math.acos(fraction);

        return angle;
    }

    //returns the area of a triangle given its three vertices as input
    public static double triangle_vertex_area(double[] first, double[] second, double[] third){
        return ((double) 1/2) * (Math.abs(first[0]*(second[1]-third[1])+second[0]*(third[1]-first[1])+third[0]*(first[1]-second[1])));
    }

    /*
     * @param int[] datapoint 
     * Takes in a datapoint and determines which quadrant it belongs to.
     * Returns a int between 1-4.
     */
    public static int quadEvaluation(double[] datapoint){
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
}
