# Design Plan

Inputs:  
`# points 2-100`
`[(x,y)...] points`
`PUV 1x15 true/false`
`LCM 15x15 OR/AND/NOTUSED`

## Flow

inputs -> decide() -> yes/no (fire?)
CMV: [LIC0, LIC1, ... LIC14]
LIC#() -> true/false

PUMij = CMVi LCMij CMVi (15x15)
```java
FUVi = {
    if(PUVi = false OR PUMi row all true) is True
} else
{    }

}
```

## Commit Structure

prefix = fix, feat, doc, refactor, verbs

[prefix] [info] [#issuenumber]

... detailed info
... detailed info


