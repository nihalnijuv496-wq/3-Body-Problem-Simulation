package com.nihal.nbodyproblem.Util;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Grid extends Group
{
    final int cellWidth = 15;

    public Grid()
    {
        draw();
    }

    void draw()
    {

        for(int start = 0; start < Constants.worldHeight; start += cellWidth)
        {
            Line horizontalLine = new Line(0, start, Constants.worldWidth, start);
            horizontalLine.setStroke(Color.GRAY);
            getChildren().add(horizontalLine);

        }
        for(int start = 0; start < Constants.worldWidth; start += cellWidth)
        {
            Line verticalLine = new Line(start, 0, start, Constants.worldHeight);
            verticalLine.setStroke(Color.GRAY);
            getChildren().add(verticalLine);
        }
    }

}