# Decide()

## What is Decide()?
This repository contains the codebase for a function written 
in Java called `Decide()`.

`Decide()` is used to determine whether a projectile should be launched 
in order to intercept an incoming balistic missile. 

## How it Works

`Decide()` assesses which launch conditions are relevant for the immediate
situation. If all combinations of launch conditions are met, `Decide()` will 
issue a launch-unlock signal, enabling the launch of the defensive projectile. 

This assessment is completed by systematic checks of data provided by six other functions 
as outlined below. 

## Input Data

### Launch Interceptor Conditions (LIC)

A set of 15 conditions which is used to confirm a launch or not.

The conditions are calculated based on an input set of up to 100 data points 
representing radar echos.

`LICs` are calculated by `CMV.java` using data input of `int[][] planarDataPoints`.   
See below.

### Logical Connector Matrix (LCM)

A 15x15 matrix of input data which is used to determine which individual `LICs` must be considered 
jointly in order to assess a confirmation or denial of an individual launch condition.

This data is dependant on the immediate threat environment.

### Primary Unlocking Vector (PUV)

The `PUV` is a vector of type `bool`.   
It is input data to the program, which defines which `LIC` (or multiple `LICs`) need 
to be true given the immediate situation.

The `PUV` indicates how to combine the elements of the `PUM` in order to create the 
Final Unlocking Vector (outlined below). 

## Functions

### Conditions Met Vector (CMV)

The `CMV` is a vector of 15 boolean values and is created by the `CMV()` function
in `src/main/CMV.java`. 

Data from the `CMV` describes which of the `LIC` conditions have been met.

Each time `CMV()` is called it calculates every `LIC` and fills the boolean result 
into a vector, subsequently returning it for use later in the program.

> data input: `int[][] lic`
> data output: `bool[] cmv`

```java
// src/main/CMV.java

    public CMV(int[][] planarDataPoints){
        datapoints = planarDataPoints;
        calculate_lics();

    }

    //...//

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
```

### Primary Unlocking Matrix (PUM)

The `PUM` is a 15x15 matrix of type `bool`.   

It is derived from the combination of the `LCM` and `CMV` and calculated by 
the `calculate_pum()` function in `src/main/PUM.java`.

The `PUM` is built in order to provide input data to the Final Unlocking Vector (FUV) 
outlined below.

> data input: `int[][] lcm, bool[] cmv`
> data output: `boolean[15][15] pum`


```java
// src/main/PUM.java

    public PUM(int[][] lcm_datapoints, boolean[] cmv_vector){
        // get LCM vector
        this.lcm_datapoints = lcm_datapoints;

        // get CMV vector
        this.cmv_vector = cmv_vector;
    }

    public boolean[][] calculate_pum() {
        pum_matrix = new boolean[15][15];
        for (int i = 0; i < lcm_datapoints.length; i++) {
            for (int j = 0; j < lcm_datapoints[i].length; j++) {
                if  (lcm_datapoints[i][j] == -1 ||
                    (lcm_datapoints[i][j] == 1 && cmv_vector[i] && cmv_vector[j]) ||
                    (lcm_datapoints[i][j] == 0 && (cmv_vector[i] || cmv_vector[j]))) {
                    pum_matrix[i][j] = true;
                }
            }
        }
        return pum_matrix;
    }
```

### Final Unlocking Vector (FUV)

The `FUV` is a 15-element vector of type `bool`.

It is used to calculate the final issuance of the launch-unlock signal.
Only if every element of the `FUV` is true, will the launch-unlock signal be generated at which 
point the defensive projectile is cleared for launch.

> data input: `boolean[] puv, boolean[][] pum`
> data output: `boolean[] fuv`

```java
// src/main/FUV.java

    public boolean[] calculate_fuv(boolean[] puv, boolean[][] pum) {
        for (int i = 0; i < fuv.length; i++) {
            if (puv[i] == false || is_all_true(pum[i]))
                fuv[i] = true;
            else 
                fuv[i] = false;
        }
        return fuv;
    }
```


## Statement of Contributions

We as a team agree that we have undertaken a successuful collaboration and learnt 
a great deal from this assignment. None of us had worked in a collaborative codebase of 
this size before, and on top of that, we began work the very same day that we met.

We found that despite the short deadline we collaborated and coordinated efficiently as well as 
maintaining a healthy and productive work environment. Each member of the group felt they were
given equal opportunity to contribute both in written content (code and documentation) and 
verbaly during meetings and in Discord.

We feel that what we managed to achieve in the short time we had to work on it is remarkable 
given our lack of previous experience in such a scenario. For this we are proud. 

- [Anders Blomqvist](https://github.com/andersblomqvist)
    - Project code setup
    - JUnit Test skeleton
    - Implemented FUV
    - Implemented LIC6
    - Fixed PUM and created its tests
- [Arasp Keighobad](https://github.com/a-kbd)
    - Implemented LICs and their tests:
        - LIC0 
        - LIC3 
        - LIC9 
        - LIC10
    - Helper functions for some LICs
    - Paramaters.java class
    - CMV class skeleton
- [Arian Hanifi](https://github.com/coffecup25)
    - Implemented LICs and their tests:
        - LIC1
        - LIC8
    - Helper function for calculating radius of circumscribed circle
    - Fixing bugs in testing functions
    - Troubleshooted Github branch protection rules
    - Helped with project structure
- [Michael Morales Sundstedt](https://github.com/Darpos)
    - Implemented LICs and their tests:
        - LIC2
        - LIC4
        - LIC5
        - LIC7
        - LIC11
        - LIC12
        - LIC14
    - Helped out with CMV.java code skeleton
- [Tillman Jex Schäuble](https://github.com/tjex)
    - Repository oversight and GitHub projects structuring
    - Readme documentation
    - Meeting moderation and adgenda creation
    - Author of the PUM function (code review by Anders)

