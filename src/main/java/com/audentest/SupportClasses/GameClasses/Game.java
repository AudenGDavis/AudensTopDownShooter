package com.audentest.SupportClasses.GameClasses;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class Game {
    private Dictionary<Integer, Player> players;
    private ArrayList<Wall> walls;
    private ArrayList<Bullet> bullets;
    

    public Game(ArrayList<Wall> Walls ,ArrayList<Bullet> Bullets){
        players = new Hashtable<>();
        walls = Walls;
        bullets = Bullets;
    }

    public synchronized void addPlayer(Player Player) {
        players.put(players.size()+ 1, Player);
    }
    public synchronized Dictionary<Integer, Player> getPlayers() {
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

    public synchronized void importGame(Game importedGame)
    {
        this.players = importedGame.getPlayers();
        this.walls = importedGame.getWalls();
        this.bullets = importedGame.getBullets();
    }
}

//steve parr