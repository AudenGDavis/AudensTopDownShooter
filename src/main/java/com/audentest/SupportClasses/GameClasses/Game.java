package com.audentest.SupportClasses.GameClasses;

import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players;
    private ArrayList<Wall> walls;
    private ArrayList<Bullet> bullets;
    

    public Game( ArrayList<Player> Players, ArrayList<Wall> Walls ,ArrayList<Bullet> Bullets){
        players = Players;
        walls = Walls;
        bullets = Bullets;
    }

    public synchronized void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
    public synchronized ArrayList<Player> getPlayers() {
        return players;
    }
    public synchronized ArrayList<Wall> getWalls() {
        return walls;
    }
    public synchronized void setWalls(ArrayList<Wall> walls) {
        this.walls = walls;
    }
    public synchronized ArrayList<Bullet> getBullets() {
        return bullets;
    }
    public synchronized void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }
}

//steve parr