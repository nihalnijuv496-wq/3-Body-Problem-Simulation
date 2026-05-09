package com.nihal.nbodyproblem.Util;


import javafx.beans.value.ChangeListener;
import javafx.scene.paint.Color;

public class Constants {
    public static final int N = 3;
    public static final int fps = 100;
    public static final double timeStep = 0.1;

    public static final int worldWidth = 1200;
    public static final int worldHeight = 700;
    public static final int sideBarWidth = 225;

    public static final int buttonWidth = 100;
    public static final int buttonHeight = 50;

    public static final int cellWidth = 15;
    public static final int buttonSpacing = 50;

    public static final double G = 100;
    public static final int epsilon = 50;

    public static final int maxVx = 50;
    public static final int maxVy = 50;
    public static final int minVx = -50;
    public static final int minVy = -50;
    public static final double maxV = 50;
    public static final double minV = -50;
    public static final int minMass = 50;
    public static final int maxMass = 500;
    public static final int maxRadius = 100;
    public static final int minRadius = 1;


    public static final Color[] bodyColors =
            {Color.rgb(255, 0, 0),
                    Color.rgb(0, 255, 0),
                    Color.rgb(0,0, 255)};

}
