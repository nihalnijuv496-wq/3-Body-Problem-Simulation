package com.nihal.nbodyproblem.Util;

public class Vector {
    double x;
    double y;

    Vector(double x, double y)
    {
        this.x = x;
        this.y = y;
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
