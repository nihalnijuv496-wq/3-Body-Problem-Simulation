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

    public void setX(double x) {this.x = x; }
    public void setY(double y) {this.y = y; }


    public Vector add(Vector B)
    {
        return new Vector(this.x + B.x, this.y + B.y);
    }
    public Vector sub(Vector B)
    {
        return new Vector(this.x - B.x, this.y - B.y);
    }
    public Vector scale(double c) { return new Vector(c*this.getX(), c*this.getY()); }
    public double magn() { return Math.sqrt(Math.pow(this.getX(), 2) + Math.pow(this.getX(), 2)); }
    double dot(Vector B)
    {
        return (this.x * B.x + this.y * B.y);
    }
}
