package com.nihal.nbodyproblem.Body;

import com.nihal.nbodyproblem.Util.PopupData;
import com.nihal.nbodyproblem.Util.Vector;
import javafx.scene.shape.Circle;

public class Body extends Circle {
    private double radius;
    private Vector center;
    private Vector velocity;
    private Vector acceleration;
    private double mass;

    private double initialRadius;
    private Vector initialCenter;
    private Vector initialVelocity;
    private Vector initialAcceleration;
    private double initialMass;

    public Body(double x,double y)
    {

        PopupData dataGetter = new PopupData("Enter mass, Vx, Vy, specify center, radius", x, y);

        radius = dataGetter.getRadius();
        mass = dataGetter.getMass();
        velocity = dataGetter.getVelocity();
        acceleration = new Vector(0, 0);
        center = new Vector(dataGetter.getCenter().getX(), dataGetter.getCenter().getY());
        setCenterX(center.getX());
        setCenterY(center.getY());

        initialRadius = radius;
        initialMass = mass;
        initialCenter = new Vector(center.getX(), center.getY());
        initialVelocity = new Vector(velocity.getX(), velocity.getY());
        initialAcceleration = new Vector(acceleration.getX(), acceleration.getY());


        setRadius(radius);
    }

    public void setAcceleration(Vector acceleration)
    {
        this.acceleration = acceleration;
    }
    public void setVelocity(Vector velocity) { this.velocity = velocity; }

    public Vector getAcceleration()
    {
        return this.acceleration;
    }
    public Vector getCenter() { return this.center; }
    public double getMass() { return this.mass; }
    public Vector getVelocity() { return this.velocity; }

    public void setCenter(double x, double y) {
        center.setX(x);
        center.setY(y);

        setCenterX(x);
        setCenterY(y);
    }

    public void resetFieldsToInitial()
    {
        radius = initialRadius;
        mass = initialMass;
        velocity = new Vector(initialVelocity.getX(), initialVelocity.getY());
        acceleration = new Vector(initialAcceleration.getX(), initialAcceleration.getY());
        center = new Vector(initialCenter.getX(), initialCenter.getY());

        setCenterX(center.getX());
        setCenterY(center.getY());
        setRadius(radius);
    }

}
