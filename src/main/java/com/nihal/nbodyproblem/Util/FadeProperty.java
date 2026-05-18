package com.nihal.nbodyproblem.Util;

import com.nihal.nbodyproblem.Body.Body;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.List;

public class FadeProperty {

    Circle[] trailingCircles = new Circle[Constants.trailingCirclesCount];
    Color color;

    public FadeProperty(double x, double y, Pane world, Body body, int i)
    {
        setColor(body ,i);
        for(int j = 0; j < Constants.trailingCirclesCount; ++j)
        {
            Circle c = new Circle();
            c.setFill(color);
            c.setOpacity(1.0 - (((double) (j +1)) / ((double) Constants.trailingCirclesCount)));
            c.setRadius(Constants.trailRadius);
            c.setCenterX(x);
            c.setCenterY(y);
            trailingCircles[j] = c;
            world.getChildren().add(c);
        }
    }

    public static void updateTrail(List<Body> bodies, Pane world)
    {
        for(Body body: bodies)
        {
            FadeProperty trail = body.getTrail();
            for(int i = 0; i < Constants.trailingCirclesCount - 1; ++i)
            {
                int currPos = Constants.trailingCirclesCount - 1 - i;
                trail.trailingCircles[currPos].setCenterX(trail.trailingCircles[currPos - 1].getCenterX());
                trail.trailingCircles[currPos].setCenterY(trail.trailingCircles[currPos - 1].getCenterY());
            }
            trail.trailingCircles[0].setCenterX(body.getCenterX());
            trail.trailingCircles[0].setCenterY(body.getCenterY());
        }
    }
    public void setColor(Body body, int i)
    {
        this.color = Constants.trailColors[i];
    }
}
