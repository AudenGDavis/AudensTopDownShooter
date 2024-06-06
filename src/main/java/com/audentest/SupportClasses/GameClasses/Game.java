package com.audentest.SupportClasses.GameClasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.audentest.SupportClasses.NetworkingClasses.ClientPackage;

public class Game 
{
    private Map<Integer, Player> players;
    private ArrayList<Wall> walls;
    private ArrayList<Bullet> bullets;
    private ArrayList<Bullet> localBullets;
    
    public Game()
    {
        players = new HashMap<Integer, Player>();
        walls = new ArrayList<Wall>();
        bullets = new ArrayList<Bullet>();
        localBullets = new ArrayList<Bullet>();
    }

    public Game(ArrayList<Wall> Walls)
    {
        players = new HashMap<>();
        walls = Walls;
        bullets = new ArrayList<Bullet>();
        localBullets = new ArrayList<Bullet>();
    }

    public synchronized void addPlayer(Player Player) 
    {
        players.put(Player.getPlayerID(), Player);
    }
    public Map<Integer, Player> getPlayers() 
    {
        return players;
    }
    public ArrayList<Wall> getWalls() 
    {
        return walls;
    }
    public void setWalls(ArrayList<Wall> walls) 
    {
        this.walls = walls;
    }
    public ArrayList<Bullet> getBullets() 
    {
        return bullets;
    }
    public void setBullets(ArrayList<Bullet> bullets) 
    {
        this.bullets = bullets;
    }
    public ArrayList<Bullet> getLocalBullets() 
    {
        return localBullets;
    }
    public void setLocalBullets(ArrayList<Bullet> LocalBullets) 
    {
        this.localBullets = LocalBullets;
    }

    public synchronized void importGame(Game importedGame)
    {
        synchronized(players)
        {
            this.players = importedGame.getPlayers();
        }

        synchronized(walls)
        {
            this.walls = importedGame.getWalls();
        }

        synchronized(bullets)
        {
            this.bullets = importedGame.getBullets();
        }
    }

    public synchronized ClientPackage getClientPackage(int localPlayer)
    {
        synchronized (localBullets)
        {
            ClientPackage clientPackage = new ClientPackage();

            

            clientPackage.setBullets(localBullets);
            clientPackage.setLocalPlayer(players.get(localPlayer));

            for(Bullet localBullet : localBullets)
            {
                bullets.add(localBullet);
            }

            localBullets = new ArrayList<Bullet>();

            return clientPackage;
        }
    }

    public synchronized void updateFromServer(Game serverGame, int localPlayer)
    {
        
        this.walls = serverGame.getWalls();
        this.bullets = serverGame.getBullets();
        for (Map.Entry<Integer, Player> serverEntry : serverGame.getPlayers().entrySet()) 
        {
            if(serverEntry.getKey() != localPlayer)
            {
                players.put(serverEntry.getKey(), serverEntry.getValue());
            } 
        }
        this.players.get(localPlayer).setHealth(serverGame.getPlayers().get(localPlayer).getHealth());
        
    }

    public String toString()
    {
        String returnString = "Game \n";
        
        returnString += "   |- Players \n";
        for (Map.Entry<Integer, Player> serverEntry : players.entrySet()) 
        {
            returnString += "   |   |- " + serverEntry.getKey() + " = " + serverEntry.getValue() + "\n";
            
        }

        returnString += "   |\n";
        returnString += "   |- Walls \n";
        for(Wall wall : walls)
        {
            returnString += "   |   |- [" + wall.getStart().toString() + "  " + wall.getEnd().toString() + "]\n";
        }

        returnString += "   |\n";
        returnString += "   |- Bullets \n";
        for(Bullet bullet : bullets)
        {
            returnString += "   |   |- [" + bullet + "]\n";
        }

        returnString += "   |\n";
        returnString += "   |- Local Bullets \n";
        for(Bullet bullet : localBullets)
        {
            returnString += "   |   |- [" + bullet + "]\n";
        }

        return returnString;
    }
}

//steve parr