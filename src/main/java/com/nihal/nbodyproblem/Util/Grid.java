package com.nihal.nbodyproblem.Util;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Grid extends Group
{


    public Grid()
    {
        draw();
    }

    void draw()
    {

        for(int start = 0; start < Constants.worldHeight; start += Constants.cellWidth)
        {
            Line horizontalLine = new Line(0, start, Constants.worldWidth, start);
            horizontalLine.setStroke(Color.GRAY);
            getChildren().add(horizontalLine);

        }
        for(int start = 0; start < Constants.worldWidth; start += Constants.cellWidth)
        {
            Line verticalLine = new Line(start, 0, start, Constants.worldHeight);
            verticalLine.setStroke(Color.GRAY);
            getChildren().add(verticalLine);
        }
    }
}