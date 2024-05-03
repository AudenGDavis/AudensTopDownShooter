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

    public synchronized double getXEnd() {
        return xEnd;
    }

    public synchronized void setXEnd(double xEnd) {
        this.xEnd = xEnd;
    }

    public synchronized double getYEnd() {
        return yEnd;
    }

    public synchronized void setYEnd(double yEnd) {
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

