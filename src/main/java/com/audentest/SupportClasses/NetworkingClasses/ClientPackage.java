package com.audentest.SupportClasses.NetworkingClasses;

import java.util.ArrayList;
import com.audentest.SupportClasses.GameClasses.Bullet; 

import com.audentest.SupportClasses.GameClasses.Player;

public class ClientPackage
{
    private Player localPlayer;
    private ArrayList<Bullet> bullets;

    // Synchronized getter for localPlayer
    public Player getLocalPlayer() {
        return localPlayer;
    }

    // Synchronized setter for localPlayer
    public void setLocalPlayer(Player localPlayer) {
        this.localPlayer = localPlayer;
    }

    // Synchronized getter for bullets
    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    // Synchronized setter for bullets
    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }
}