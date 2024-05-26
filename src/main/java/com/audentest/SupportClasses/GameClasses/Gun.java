package com.audentest.SupportClasses.GameClasses;

import java.awt.Color;

public class Gun {
    private int damage;//damager per hit
    private float accuracy;//the range of degrees each bullet might shoot
    private float bulletSpeed;//units per second
    private float barrelLength;//world units
    private float weight;
    private String color;
    private float reloadTimeRequirment;//in milliseconds
    private float reloadTime;

    // Getter methods
    public synchronized int getDamage() {
        return damage;
    }

    public synchronized float getAccuracy() {
        return accuracy;
    }

    

    public synchronized float getBarrelLength() {
        return barrelLength;
    }

    public synchronized float getWeight() {
        return weight;
    }

    // Setter methods
    public synchronized void setDamage(int damage) {
        this.damage = damage;
    }

    public synchronized void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }

    public synchronized void setBarrelLength(float barrelLength) {
        this.barrelLength = barrelLength;
    }

    public synchronized void setWeight(float weight) {
        this.weight = weight;
    }
    

    public synchronized String getColor() {
        return color;
    }

    public synchronized void setColor(String color) {
        this.color = color;
    }

    public synchronized void setReloadTime(float reloadTime)
    {
        this.reloadTime = reloadTime;
    }

    public synchronized float getReloadTime()
    {
        return reloadTime;
    }

    public synchronized float getReloadTimeRequirment()
    {
        return reloadTimeRequirment;
    }

    public synchronized float getBulletSpeed()
    {
        return bulletSpeed;
    }

    public synchronized void setBulletSpeed(float bulletSpeed)
    {
        this.bulletSpeed = bulletSpeed;
    }



    //guns
    public static Gun ak47()
    {
        Gun gun = new Gun();

        gun.accuracy = 5;// plus or minus degrees change of each bullet
        gun.damage = 30;
        gun.barrelLength = 50;
        gun.weight = 15;
        gun.color = "000000";
        gun.reloadTimeRequirment = 100;
        gun.reloadTime = 0;//gun.reloadTimeRequirment;
        gun.bulletSpeed = 5000;

        return gun;
    }


    public static Gun awp()
    {
        Gun gun = new Gun();

        gun.accuracy = 0.1f;// plus or minus degrees change of each bullet
        gun.damage = 200;
        gun.barrelLength = 80;
        gun.weight = 30;
        gun.color = "000000";
        gun.reloadTimeRequirment = 3000;
        gun.reloadTime = 0;//gun.reloadTimeRequirment;
        gun.bulletSpeed = 7000;

        return gun;
    }

    public static Gun uzi()
    {
        Gun gun = new Gun();

        gun.accuracy = 20f;// plus or minus degrees change of each bullet
        gun.damage = 100;
        gun.barrelLength = 40;
        gun.weight = 30;
        gun.color = "000000";
        gun.reloadTimeRequirment = 20;
        gun.reloadTime = 0;//gun.reloadTimeRequirment;
        gun.bulletSpeed = 4000;

        return gun;
    }

    public static Gun test()
    {
        Gun gun = new Gun();

        gun.accuracy = 0f;// plus or minus degrees change of each bullet
        gun.damage = 100;
        gun.barrelLength = 50;
        gun.weight = 30;
        gun.color = "000000";
        gun.reloadTimeRequirment = 1;
        gun.reloadTime = 0;//gun.reloadTimeRequirment;
        gun.bulletSpeed = 4000;

        return gun;
    }


    public static Gun ar15()
    {
        Gun gun = new Gun();

        gun.accuracy = 3;
        gun.damage = 40;
        gun.barrelLength = 50;
        gun.weight = 8;
        gun.color = "000000";
        gun.reloadTimeRequirment = 100;
        gun.reloadTime = gun.reloadTimeRequirment;
        gun.bulletSpeed = 4000;

        return gun;
    }

    public static Gun glock9()
    {
        Gun gun = new Gun();

        gun.accuracy = 10;
        gun.damage = 30;
        gun.barrelLength = 32;
        gun.weight = 2;
        gun.color = "000000";
        gun.reloadTimeRequirment = 50;
        gun.reloadTime = gun.reloadTimeRequirment;
        gun.bulletSpeed = 4000;

        return gun;
    }

    public static Gun idk()
    {
        Gun gun = new Gun();

        gun.accuracy = 90;
        gun.damage = 10;
        gun.barrelLength = 38;
        gun.weight = 10;
        gun.color = "000000";
        gun.reloadTimeRequirment = 0.00001f;
        gun.reloadTime = gun.reloadTimeRequirment;
        gun.bulletSpeed = 1000;

        return gun;
    }

    public static Gun mac10()
    {
        Gun gun = new Gun();

        gun.accuracy = 12;
        gun.damage = 20;
        gun.barrelLength = 34;
        gun.weight = 5;
        gun.color = "000000";
        gun.reloadTimeRequirment = 40;
        gun.reloadTime = gun.reloadTimeRequirment;
        gun.bulletSpeed = 5000;

        return gun;
    }
}
