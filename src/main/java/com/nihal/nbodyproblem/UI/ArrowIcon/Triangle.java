package com.nihal.nbodyproblem.UI.ArrowIcon;

import com.nihal.nbodyproblem.Util.Vector;
import javafx.collections.ObservableList;
import javafx.scene.shape.Polygon;

public class Triangle extends Polygon {
    double a = 7;
    public Triangle(Vector tip, double angle)
    {
        double endX = tip.getX() - a * Math.cos(angle);
        double endY = tip.getY() - a * Math.sin(angle);

        getPoints().addAll(
                endX - a * Math.sin(angle), endY + a * Math.cos(angle),  // base left
                tip.getX(), tip.getY(),                                       // tip
                endX + a * Math.sin(angle), endY - a * Math.cos(angle)        // base right
        );
    }

    public void setValues(double length, double angle, Vector start)
    {
        double endX = start.getX() + length*Math.cos(angle);
        double endY = start.getY() + length*Math.sin(angle);
        double vertex1X = endX + a*Math.cos(angle);
        double vertex1Y = endY + a*Math.sin(angle);
        double vertex2X = endX - a * Math.sin(angle);
        double vertex2Y = endY + a * Math.cos(angle);
        double vertex3X = endX + a * Math.sin(angle);
        double vertex3Y = endY - a * Math.cos(angle);

        ObservableList<Double> points = this.getPoints();

        points.set(0, vertex1X);  // X1
        points.set(1, vertex1Y); // Y1
        points.set(2, vertex2X);  // X2
        points.set(3, vertex2Y); // Y2
        points.set(4, vertex3X);  // X2
        points.set(5, vertex3Y); // Y3

    }
}
