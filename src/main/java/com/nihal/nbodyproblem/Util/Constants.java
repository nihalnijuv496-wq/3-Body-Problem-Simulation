package com.nihal.nbodyproblem.Util;


import javafx.beans.value.ChangeListener;
import javafx.scene.paint.Color;

public class Constants {
    public static final int N = 3;
    public static final int fps = 100;
    public static final double timeStep = 0.5;

    public static final int worldWidth = 1200;
    public static final int worldHeight = 700;
    public static final int sideBarWidth = 225;

    public static final int buttonWidth = 100;
    public static final int buttonHeight = 50;

    public static final int cellWidth = 15;
    public static final int buttonSpacing = 50;

    public static final double G = 100;
    public static final int epsilon = 5;

    public static final int maxVx = 50;
    public static final int maxVy = 50;
    public static final int minVx = -50;
    public static final int minVy = -50;
    public static final double maxV = Math.sqrt(2)*maxVx;
    public static final double minV = -1*maxV;
    public static final int minMass = 50;
    public static final int maxMass = 500;
    public static final int maxRadius = 100;
    public static final int minRadius = 1;


    //STYLES
    public static final String worldStyle = "-fx-background-color: #f5f5f5;";
    public static final String sideBarStyle =
            "-fx-background-color: #ffffff;" +
                    "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.12), 12, 0, 2, 0);";

    public static final String tabStyleUnselected =
            "-fx-background-color: #ffffff;" +
                    "-fx-text-fill: #aaaaaa;" +
                    "-fx-font-size: 15px;" +
                    "-fx-font-family: monospace;" +
                    "-fx-border-width: 0 0 2 0;" +
                    "-fx-border-color: transparent;";

    public static final String tabStyleSelected =
            "-fx-background-color: #ffffff;" +
                    "-fx-text-fill: #111111;" +
                    "-fx-font-size: 15px;" +
                    "-fx-font-family: monospace;" +
                    "-fx-border-width: 0 0 2 0;" +
                    "-fx-border-color: #111111;" +
                    "-fx-border-style: solid;";

    public static final String headerStyle =
            "-fx-font-size: 20px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-font-family: monospace;" +
                    "-fx-text-fill: #aaaaaa;" +
                    "-fx-padding: 25 0 4 0;";

    public static final String subHeaderStyle =
            "-fx-font-size: 17px;" +
                    "-fx-font-family: monospace;" +
                    "-fx-text-fill: #bbbbbb;";
    public static final String sliderStyle =
            "-fx-control-inner-background: #222222;" +
                    "-fx-thumb-color: #f0f0f0;" +
                    "-fx-track-color: #333333;";


    public static final Color[] bodyColors =
            {Color.rgb(255, 0, 0),
                    Color.rgb(0, 255, 0),
                    Color.rgb(0,0, 255)};

}
