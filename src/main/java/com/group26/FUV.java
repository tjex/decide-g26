package com.group26;

public class FUV {
    
    private boolean[] fuv = new boolean[15];

    public FUV() {}

    public boolean[] calculate_fuv(boolean[] puv, boolean[][] pum) {
        for (int i = 0; i < fuv.length; i++) {
            if (puv[i] == false || is_all_true(pum[i]))
                fuv[i] = true;
            else 
                fuv[i] = false;
        }
        return fuv;
    }

    /**
     *  Check if all values within a boolean array are set to true.
     *  
     *  @returns True if all boolean values are true.
     */
    public boolean is_all_true(boolean[] arr) {
        if (arr.length <= 0) return false;
        for(boolean b : arr) if(!b) return false;
        return true;
    }
}
