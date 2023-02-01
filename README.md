# Decide()

## Table of Contents

- [What is this Project?](#What-is-this-Project)   
- [How it Works](#How-it-Works)   
- [Input Data](#Input-Data)   
    - [Launch Interceptor Conditions (LIC)](#Launch-Interceptor-Conditions-lic)   
    - [Logical Connector Matrix (LCM)](#Logical-Connector-Matrix-lcm)   
    - [Primary Unlocking Vector (PUV)](#Primary-Unlocking-Vector-puv)
- [Primary Functions](#Primary-Functions)
    - [Conditions Met Vector (CMV)](#Conditions-Met-Vector-CMV)
    - [Primary Unlocking Matrix (PUM)](#Primary-Unlocking-Matrix-PUM)
    - [Final Unlocking Vector (FUV)](#Final-Unlocking-Vector-FUV)
- [Our Way of Working](#our-way-of-working)
- [Statement of Contributions](#Statement-of-Contributions)
- [Building](#Building)

## What is this Project?
This repository contains the code for a function written 
in Java called `Decide()`. The project is the first assignment for
the Fundamentals of Software Engineering course at KTH, 
taught by [Cyrille Artho](https://github.com/cyrille-artho).

`Decide()` is used to determine whether a projectile should be launched 
in order to intercept an incoming balistic missile. 

## How it Works

`Decide()` assesses which launch conditions are relevant for the immediate
situation. If all combinations of launch conditions are met, `Decide()` will 
issue a launch-unlock signal, enabling the launch of the defensive projectile. 

This assessment is completed by the computations of given input data, handled by 
six primary functions as outlined below. 

## Input Data

### Launch Interceptor Conditions (LIC)

A set of 15 conditions which are used to confirm a launch or not. For a positive confirmation,
all 15 conditions must register as being `true`.

The conditions are calculated based on an input set of up to 100 data points 
representing radar echos.

`LICs` are calculated by functions in `src/main/CMV.java` using data input of `int[][] planarDataPoints`.   
Outlined under _Conditions Met Vector (CMV)_ below.

### Logical Connector Matrix (LCM)

A 15x15 matrix of input data which is used to determine which individual `LICs` must be considered 
jointly in order to assess a confirmation or denial of an individual launch condition.

The `LCM` is subsequently used as input data for the `PUM` function outlined below.
The `LCM` is outlined at `ref-docs/decide-3.pdf` page 5 and implemented in `src/main/Main.java`:

```java
// src/main/Main.java

        // Fill LCM
        // Following the Table 1 in decide.pdf
        for(int i = 0; i < Parameters.LCM_MAT.length; i++)
            for(int j = 0; j < Parameters.LCM_MAT.length; j++)
                Parameters.LCM_MAT[i][j] = -1;
        Parameters.LCM_MAT[0][0] = 1;
        Parameters.LCM_MAT[0][1] = 1;
        Parameters.LCM_MAT[0][2] = 0;
        Parameters.LCM_MAT[0][3] = 1;

        Parameters.LCM_MAT[1][0] = 1;
        Parameters.LCM_MAT[1][1] = 1;
        Parameters.LCM_MAT[1][2] = 0;
        Parameters.LCM_MAT[1][3] = 0;

        Parameters.LCM_MAT[2][0] = 0;
        Parameters.LCM_MAT[2][1] = 0;
        Parameters.LCM_MAT[2][2] = 1;
        Parameters.LCM_MAT[2][3] = 1;

        Parameters.LCM_MAT[3][0] = 1;
        Parameters.LCM_MAT[3][1] = 0;
        Parameters.LCM_MAT[3][2] = 1;
        Parameters.LCM_MAT[3][3] = 1;

```


### Primary Unlocking Vector (PUV)

The `PUV` is a vector of type `bool`.   
It defines which `LIC` (or multiple `LICs`) need to be true given the immediate situation.

The `PUV` indicates how to combine the elements of the `PUM` in order to create the 
Final Unlocking Vector. 

```java
// src/main/Main.java

        // Fill PUV
        for (int i = 0; i < Parameters.PUV_VEC.length; i++) {
            if (i % 2 == 0)
                Parameters.PUV_VEC[i] = true;
            else
                Parameters.PUV_VEC[i] = false;
        }
```

## Primary Functions

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

## Our Way of Working

We are pleased with the structure and workflow that we implemented within this assignment.
On the first day we established principles for the team to follow (eg. a protocol for missed work days, 
remote vs IRL group work and a git commit formatting structure) as well as agreed on the tools we were to use 
(GitHub, GitHub Projects, Discord and Java).   

We found that we were quite reliable in following the above structures, although the git commit formatting
did get forgotten occasionally. Due to the short timeline, we did desynchronise our group approach to overarching 
software engineering problems that needed to be addressed, such as implementing a test framework and unit tests.   
However, due to the fluidity in our communication channels and organisation via the GitHub issue tracker and 
projects, our productivity and efficiency remained on its positive trend through to the end of the assignment.

As a team, the state we are in is excellent. We communicate well and understand each others skill sets. We have 
shown that we are all comfortable with the tools we have chosen to work with and are looking forward to further 
optimize our workflow in the comming assignment. For the next assignment we will work on a more refined 
issuing of tasks, as with this project there was a lot of cross-over between tasks, which occasionally caused 
confusion and lead to merge conflicts and increased communication time to debug.

## Statement of Contributions

We as a team agree that we have undertaken a successuful collaboration and learnt 
a great deal from this assignment. None of us had worked in a collaborative codebase of 
this size before, and on top of that, we began work the very same day that we met.

We found that despite the short deadline, and not knowing each other personally beforehand,
we still collaborated and coordinated efficiently as well as 
maintained a healthy and productive work environment. Each member of the group felt they were
given equal opportunity to contribute both in written content (code and documentation) and 
verbally during meetings and in Discord.

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

## Building

For building and running this project the requirements are:

- [Maven v3.8.6](https://maven.apache.org/docs/3.8.6/release-notes.html)
- Java 19+

### How to build and run

1. Clone this repository.
2. Import project as a Maven project with your desired IDE.
3. Run `Main.java`.

Or by manually running with maven:

1. Clone this repository.
2. Enter repository root `cd decide-g26`
3. Compile with `mvn compile`
4. Run the program `mvn exec:java -Dexec.mainClass=com.group26.Main`
5. Test the program `mvn test`

Windows PS users: use this command instead for running: `mvn exec:java -D"exec.mainClass"="com.group26.Main"`
