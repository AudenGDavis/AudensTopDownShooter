package com.audentest.SupportClasses.NetworkingClasses;

import com.audentest.SupportClasses.GameClasses.Bullet;
import com.audentest.SupportClasses.GameClasses.Player;

import java.util.ArrayList;
import java.util.Map;

public class ServerPackage 
{
    private Map<Integer, Player> players;
    private ArrayList<Bullet> bullets;

    public ServerPackage(Map<Integer, Player> players, ArrayList<Bullet> bullets)
    {
        this.players = players;
        this.bullets = bullets;
    }

    public Map<Integer, Player> getPlayers(){
        return players;
    }

    public ArrayList<Bullet> getBullets()
    {
        return bullets;
    }
}
