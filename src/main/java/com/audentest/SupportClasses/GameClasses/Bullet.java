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
    public Player getShotBy() {
        return shotBy;
    }

    public void setShotBy(Player shotBy) {
        this.shotBy = shotBy;
    }

    // Getter and setter for 'velocity' field
    public int getDamage() {
        return damage;
    }

    public void setdamage(int damage) {
        this.damage = damage;
    }



    // Getter and setter for 'xVelocity' field
    public float getXVelocity() {
        return xVelocity;
    }

    public void setXVelocity(float xVelocity) {
        this.xVelocity = xVelocity;
    }

    // Getter and setter for 'yVelocity' field
    public float getYVelocity() {
        return yVelocity;
    }

    public void setYVelocity(float yVelocity) {
        this.yVelocity = yVelocity;
    }

    // Getter and setter for 'xPositon' field
    public float getXPosition() {
        return xPosition;
    }

    public void setXPosition(float xPosition) {
        this.xPosition = xPosition;
    }

    // Getter and setter for 'yPositon' field
    public float getYPosition() {
        return yPosition;
    }

    public void setYPosition(float yPosition) {
        this.yPosition = yPosition;
    }
}

