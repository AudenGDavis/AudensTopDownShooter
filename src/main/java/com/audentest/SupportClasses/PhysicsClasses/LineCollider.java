package com.audentest.SupportClasses.PhysicsClasses;



public class LineCollider {
    private double xStart;
    private double yStart;
    private double xEnd;
    private double yEnd;

    public LineCollider(double XStart, double YStart, double XEnd, double YEnd){
        xStart = XStart;
        yStart = YStart;
        xEnd = XEnd;
        yEnd = YEnd;
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

    public double getXEnd() {
        return xEnd;
    }

    public void setXEnd(double xEnd) {
        this.xEnd = xEnd;
    }

    public double getYEnd() {
        return yEnd;
    }

    public void setYEnd(double yEnd) {
        this.yEnd = yEnd;
    }

    public boolean isWithinBound(Vector2 point)
    {
        if((point.getX() > xStart && point.getX() > xEnd) || (point.getX() < xStart && point.getX() < xEnd)){
            return false;
        }

        if((point.getY() > yStart && point.getY() > yEnd) || (point.getY() < yStart && point.getY() < yEnd)){
            return false;
        }

        return true; 
    }

    public String toString()
    {
        return "(" + xStart + ", " + yStart + ") => (" + xEnd + " , " + yEnd + ")";
    }
}

