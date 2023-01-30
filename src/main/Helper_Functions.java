package main;

public class Helper_Functions {
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

    public static double euclidean_distance(int[] a, int[] b){
        return Math.sqrt(Math.pow((b[0]-a[0]), 2) + Math.pow((b[1]-a[1]), 2));
    }

    //returns vector magnitude, not rounded 
    public static double vector_magnitude(int[] a){
        int[] origin = {0,0};
        double dist = euclidean_distance(a, origin);
        return dist;
    }

    public static int dot_product(int[] a, int[] b){
        return (a[0]*b[0])+(a[1]*b[1]);
    }

    //returns a vector pointing towards the vector a
    public static int[] vector_subtraction(int[] a, int[] b){
        int[] out = {a[0]-b[0], a[1]-b[1]};
        return out;
    }

    //from three points, where the middle argument is the vertex and the other two are the ends of rays from that vertex
    //return the angle  (in radians!) between those rays
    public static double three_point_angle(int[] ray_end_1, int[] vertex, int[] ray_end_2){
        int[] vector1 = vector_subtraction(ray_end_1, vertex);
        int[] vector2 = vector_subtraction(ray_end_2, vertex);
        
        double fraction = (dot_product(vector1, vector2))/(vector_magnitude(vector1)*vector_magnitude(vector2));
        double angle = Math.acos(fraction);

        return angle;
    }

    //returns the area of a triangle given its three vertices as input
    public static double triangle_vertex_area(int[] first, int[] second, int[] third){
        return ((double) 1/2) * (Math.abs(first[0]*(second[1]-third[1])+second[0]*(third[1]-first[1])+third[0]*(first[1]-second[1])));
    }

    /*
     * @param int[] datapoint 
     * Takes in a datapoint and determines which quadrant it belongs to.
     * Returns a int between 1-4.
     */
    public static int quadEvaluation(int[] datapoint){
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
