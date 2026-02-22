package com.nihal.nbodyproblem.Util;

public class Vector {
    double x;
    double y;

    public Vector(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public double getX()
    {
        return x;
    }
    public double getY()
    {
        return y;
    }


    Vector add(Vector B)
    {
        return new Vector(this.x + B.x, this.y + B.y);
    }
    Vector sub(Vector B)
    {
        return new Vector(this.x - B.x, this.y - B.y);
    }

    double dot(Vector B)
    {
        return (this.x * B.x + this.y * B.y);
    }
}
