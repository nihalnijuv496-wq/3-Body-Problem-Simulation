package com.nihal.nbodyproblem.Body;

import com.nihal.nbodyproblem.Util.PopupData;
import com.nihal.nbodyproblem.Util.Vector;
import javafx.scene.shape.Circle;

public class Body extends Circle {
    private double radius;
    private Vector velocity;
    private Vector acceleration;
    private double mass;

    public Body(double x,double y)
    {

        PopupData dataGetter = new PopupData("Enter mass, Vx, Vy, specify center, radius", x, y);

        radius = dataGetter.getRadius();
        mass = dataGetter.getMass();
        velocity = dataGetter.getVelocity();
        acceleration = new Vector(0, 0);

        setCenterX(dataGetter.getCenter().getX());
        setCenterY(dataGetter.getCenter().getY());
        setRadius(radius);
    }


}
