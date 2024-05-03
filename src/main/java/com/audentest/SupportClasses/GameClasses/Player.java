package com.audentest.SupportClasses.GameClasses;

import com.audentest.SupportClasses.PhysicsClasses.CircleCollider;

public class Player {
    private Gun gun;
    private int team;
    private int health;
    private int playerID;
    private double xVelocity;
    private double yVelocity;
    private double xPosition;
    private double yPosition;
    private double angle = 180;//in degrees
    private double size = 30;

    public Player(Gun gun, double xPosition, double yPosition, int team){
        this.gun = gun;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.team = team;
        this.health = 100;
    }

    // Getter and setter for gun
    public synchronized Gun getGun() {
        return gun;
    }

    public synchronized void setGun(Gun gun) {
        this.gun = gun;
    }

    // Getter and setter for team
    public synchronized int getTeam() {
        return team;
    }

    public synchronized void setTeam(int team) {
        this.team = team;
    }

    // Getter and setter for health
    public synchronized int getHealth() {
        return health;
    }

    public synchronized void setHealth(int health) {
        this.health = health;
    }

    // Getter and setter for playerID
    public synchronized int getPlayerID() {
        return playerID;
    }

    public synchronized void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    // Getter and setter for xVelocity
    public synchronized double getXVelocity() {
        return xVelocity;
    }

    public synchronized void setXVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    // Getter and setter for yVelocity
    public synchronized double getYVelocity() {
        return yVelocity;
    }

    public synchronized void setYVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    // Getter and setter for xPosition
    public synchronized double getXPosition() {
        return xPosition;
    }

    public synchronized void setXPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    // Getter and setter for yPosition
    public synchronized double getYPosition() {
        return yPosition;
    }

    public synchronized void setYPosition(double yPosition) {
        this.yPosition = yPosition;
    }

    // Getter for size
    public synchronized double getSize() {
        return size;
    }

    public synchronized CircleCollider getCollider() {
        return new CircleCollider(xPosition, yPosition, size);
    }

    public synchronized double getAngle() {
        return angle;
    }

    public synchronized void setAngle(double Angle) {
        this.angle = Angle;
    }
}

