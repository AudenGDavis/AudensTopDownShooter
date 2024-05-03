package com.audentest.SupportClasses.GameClasses;

public class Bullet {
    private Player shotBy;
    private int damage;
    private float xVelocity;
    private float yVelocity;
    private float xPosition;
    private float yPosition;


    public Bullet(Player ShotBy, int Damage,float xPosition,float yPosition, float xVelocity, float yVelocity){
        shotBy = ShotBy;
        damage = Damage;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    
    // Getter and setter for 'shotBy' field
    public synchronized Player getShotBy() {
        return shotBy;
    }

    public synchronized void setShotBy(Player shotBy) {
        this.shotBy = shotBy;
    }

    // Getter and setter for 'velocity' field
    public synchronized int getDamage() {
        return damage;
    }

    public synchronized void setdamage(int damage) {
        this.damage = damage;
    }



    // Getter and setter for 'xVelocity' field
    public synchronized float getXVelocity() {
        return xVelocity;
    }

    public synchronized void setXVelocity(float xVelocity) {
        this.xVelocity = xVelocity;
    }

    // Getter and setter for 'yVelocity' field
    public synchronized float getYVelocity() {
        return yVelocity;
    }

    public synchronized void setYVelocity(float yVelocity) {
        this.yVelocity = yVelocity;
    }

    // Getter and setter for 'xPositon' field
    public synchronized float getXPosition() {
        return xPosition;
    }

    public synchronized void setXPosition(float xPosition) {
        this.xPosition = xPosition;
    }

    // Getter and setter for 'yPositon' field
    public synchronized float getYPosition() {
        return yPosition;
    }

    public synchronized void setYPosition(float yPosition) {
        this.yPosition = yPosition;
    }
}

