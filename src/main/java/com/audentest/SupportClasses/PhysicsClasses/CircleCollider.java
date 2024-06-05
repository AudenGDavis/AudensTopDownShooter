package com.audentest.SupportClasses.PhysicsClasses;



public class CircleCollider 
{
    private double xStart;
    private double yStart;
    private double radius;

    public CircleCollider(double XStart, double YStart, double Radius){
        xStart = XStart;
        yStart = YStart;
        radius = Radius;
    }
    

    public double getXStart() {
        return xStart;
    }

    public void setXStart(double xStart) {
        this.xStart = xStart;
    }

    public double getYStart() {
        return yStart;
    }

    public void setYStart(double yStart) {
        this.yStart = yStart;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public String toString()
    {
        return "(" + xStart + ", " + yStart + ", " + radius + ") ";
    }
}
