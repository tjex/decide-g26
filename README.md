# Decide()

## What is Decide()?
This repository contains the codebase for a function written 
in Java called Decide().

Decide() is used to determine whether a projectile should be launched 
in order to intercept an incoming balistic missile. 

## How it Works

Decide() assesses which launch conditions are relevant for the immediate
situation. If all combinations of launch conditions are met, Decide() will 
issue a launch-unlock signal, enabling the launch of the defensive projectile. 

This assessment is completed by systematic checks of data provided by six other functions 
as outlined below. 

### Launch Interceptor Conditions (LIC)

A set of 15 conditions which is used to confirm a launch or not.

The conditions are calcualted based on an input set of up to 100 data points 
representing radar echos.

### Conditions Met Vector (CMV)

A vector of 15 boolean values describing the current state of each of the 15 LICs.

### Logical Connector Matrix (LCM)

A 15x15 matrix of input data which is used to determine which individual LICs must be considered 
jointly in order to assess a confirmation or denial of an individual launch condition.

### Primary Unlocking Matrix (PUM)

A 15x15 matrix derived from the combination of the LCM and CMV.

### Primary Unlocking Vector (PUV)

The PUV defines which LIC (or multiple LICs) need to be true in the immediate situation.
Each element of the FUV is therefore input data for the program and indicates how to combine 
the elements of the PUM in order to create the Final Unlocking Vector (outlined below). 

### Final Unlocking Vector (FUV)

A 15-element vector which is used to derive a final confirmation or denial of launch.   
Only if every element of the FUV is true, should the launch-unlock signal be generated.


## Statement of Contributions

The following are our statement of contributions to the Decide() project.

The dev team for Decide() is combrised of:
- [Anders Blomqvist](https://github.com/andersblomqvist)
- [Arasp Keighobad](https://github.com/a-kbd)
- [Arian Hanifi](https://github.com/coffecup25)
- [Michael Morales Sundstedt](https://github.com/Darpos)
- [Tillman Jex Schäuble](https://github.com/tjex)
    - Repository oversight and GitHub projects structuring
    - Readme documentation
    - Meeting moderation and adgenda creation
    - Author of the PUM function (code review by Anders)

