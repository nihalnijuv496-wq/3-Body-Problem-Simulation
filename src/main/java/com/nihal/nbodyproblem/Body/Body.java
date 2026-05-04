package com.nihal.nbodyproblem.Body;

import com.nihal.nbodyproblem.UI.PopupData;
import com.nihal.nbodyproblem.UI.SideBar.DataInputBox;
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

    public Body(double x,double y, DataInputBox dib)
    {


        radius = dib.getRadius();
        mass = dib.getMass();
        velocity = dib.getVelocity();
        acceleration = new Vector(0, 0);
        center = new Vector(x, y);
        setCenterX(center.getX());
        setCenterY(center.getY());
        setRadius(radius);

        initialRadius = radius;
        initialMass = mass;
        initialCenter = new Vector(center.getX(), center.getY());
        initialVelocity = new Vector(velocity.getX(), velocity.getY());
        initialAcceleration = new Vector(acceleration.getX(), acceleration.getY());



    }



    public Vector getAcceleration()
    {
        return this.acceleration;
    }
    public Vector getCenter() { return this.center; }
    public double getMass() { return this.mass; }
    public Vector getVelocity() { return this.velocity; }

    public void setAcceleration(Vector acceleration)
    {
        this.acceleration = acceleration;
    }
    public void setVelocity(Vector velocity) { this.velocity = velocity; }
    public void setCenter(Vector c)
    {
        center.setX(c.getX());
        center.setY(c.getY());

        setCenterX(c.getX());
        setCenterY(c.getY());
    }
    public void setMass(double mass) { this.mass = mass; }
    public void setRad(double r)
    {
        super.setRadius(r);
        this.radius = r;
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
