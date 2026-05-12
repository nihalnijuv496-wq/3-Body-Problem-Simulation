package com.nihal.nbodyproblem.Presets;

public enum Presets {
    LagrangeEquilateralTriangle ;

    public static String getText(Presets buttonType)
    {
        if(buttonType == LagrangeEquilateralTriangle)
            return "Equilateral Triangle";
        return "Failure";
    }
}

