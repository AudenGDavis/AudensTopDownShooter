package com.audentest.SupportClasses.PhysicsClasses;



public class CircleCollider {
    private double xStart;
    private double yStart;
    private double radius;

    public CircleCollider(double XStart, double YStart, double Radius){
        xStart = XStart;
        yStart = YStart;
        radius = Radius;
    }
    

    public synchronized double getXStart() {
        return xStart;
    }

    public synchronized void setXStart(double xStart) {
        this.xStart = xStart;
    }

    public synchronized double getYStart() {
        return yStart;
    }

    public synchronized void setYStart(double yStart) {
        this.yStart = yStart;
    }

    public synchronized double getRadius() {
        return radius;
    }

    public synchronized void setRadius(double radius) {
        this.radius = radius;
    }

    public String toString()
    {
        return "(" + xStart + ", " + yStart + ", " + radius + ") ";
    }
}

