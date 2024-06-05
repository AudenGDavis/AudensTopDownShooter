package com.audentest.SupportClasses.PhysicsClasses;

public class Vector2 {
    private double x;
    private double y;

    
    public Vector2(double X, double Y){
        x = X;
        y = Y;
    }
    public String toString(){
        return "(" + x + ", " + y + ")";
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public static double getDistance(Vector2 one, Vector2 two)
    {
        return Math.sqrt((one.getX()-two.getX())*(one.getX()-two.getX()) + (one.getY()-two.getY())*(one.getY()-two.getY()));
    }

    

    
}

