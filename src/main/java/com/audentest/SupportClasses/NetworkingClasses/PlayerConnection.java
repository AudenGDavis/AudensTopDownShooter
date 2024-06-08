package com.audentest.SupportClasses.NetworkingClasses;

import com.audentest.SupportClasses.GameClasses.Game;

public class PlayerConnection 
{
    private int portNumber;
    private String ipAddress;
    private int playerID;
    private Game game;

    public PlayerConnection(int PortNumber, String IpAddress, int PlayerID,Game Game){
        portNumber = PortNumber;
        ipAddress = IpAddress;
        playerID = PlayerID;
        game = Game;
    }

    // Synchronized getter and setter for clientRecieverPortNumber
    public int getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(int PortNumber) {
        this.portNumber = PortNumber;
    }

    // Synchronized getter and setter for ipAddress
    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    // Synchronized getter and setter for playerID
    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game Game) {
        this.game = Game;
    }
}

