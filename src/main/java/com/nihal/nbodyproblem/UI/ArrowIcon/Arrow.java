package com.nihal.nbodyproblem.UI.ArrowIcon;

import com.nihal.nbodyproblem.Util.Vector;
import javafx.scene.Group;
import javafx.scene.shape.Line;

public class Arrow extends Group {
    private double length;
    private double angle;
    private Line line;
    private Triangle triangle;

    public Arrow(double length,double angle, Vector tailPos)
    {
        this.length = length;
        this.angle = angle;

        double startX = tailPos.getX();
        double startY = tailPos.getY();
        double endX = startX + length * Math.cos(angle);
        double endY = startY + length * Math.sin(angle);

        line = new Line(startX, startY, endX, endY);

        double tipX = startX + length * Math.cos(this.angle);
        double tipY = startY + length * Math.sin(this.angle);
        triangle = new Triangle(new Vector(
                startX + length * Math.cos(this.angle),
                startY + length * Math.sin(this.angle)
        ), this.angle);

        this.getStyleClass().add("arrow");
        line.getStyleClass().add("arrow-line");
        triangle.getStyleClass().add("arrow-head");

        this.getChildren().addAll(line, triangle);

    }

    public void setValues(Vector velocity, Vector start)
    {
        this.length = velocity.magn();
        this.angle = velocity.getAngle();


        double startX = start.getX();
        double startY = start.getY();
        double endX = startX + length * Math.cos(angle);
        double endY = startY + length * Math.sin(angle);
        line.setStartX(startX);
        line.setStartY(startY);
        line.setEndX(endX);
        line.setEndY(endY);

        triangle.setValues(length, angle, start);
    }
}
