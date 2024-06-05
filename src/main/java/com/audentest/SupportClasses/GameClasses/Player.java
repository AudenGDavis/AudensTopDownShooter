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

    public Player(Gun gun, double xPosition, double yPosition, int team,int PlayerID){
        this.gun = gun;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.team = team;
        this.health = 100;
        this.playerID = PlayerID;
    }

    // Getter and setter for gun
    public Gun getGun() {
        return gun;
    }

    public void setGun(Gun gun) {
        this.gun = gun;
    }

    // Getter and setter for team
    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    // Getter and setter for health
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    // Getter and setter for playerID
    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    // Getter and setter for xVelocity
    public double getXVelocity() {
        return xVelocity;
    }

    public void setXVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    // Getter and setter for yVelocity
    public double getYVelocity() {
        return yVelocity;
    }

    public void setYVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    // Getter and setter for xPosition
    public double getXPosition() {
        return xPosition;
    }

    public void setXPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    // Getter and setter for yPosition
    public double getYPosition() {
        return yPosition;
    }

    public void setYPosition(double yPosition) {
        this.yPosition = yPosition;
    }

    // Getter for size
    public double getSize() {
        return size;
    }

    public CircleCollider getCollider() {
        return new CircleCollider(xPosition, yPosition, size);
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double Angle) {
        this.angle = Angle;
    }
}

