package com.audentest.SupportClasses.NetworkingClasses;

import com.audentest.SupportClasses.GameClasses.Game;

public class PlayerConnection {
    private int clientSenderPortNumber;
    private int clientRecieverPortNumber;
    private String ipAddress;
    private int playerID;
    private Game game;

    public PlayerConnection(int ClientSenderPortNumber, int ClientRecieverPortNumber, String IpAddress, int PlayerID,Game Game){
        clientSenderPortNumber = ClientSenderPortNumber;
        clientRecieverPortNumber = ClientRecieverPortNumber;
        ipAddress = IpAddress;
        playerID = PlayerID;
        game = Game;
    }

    // Synchronized getter and setter for clientSenderPortNumber
    public int getClientSenderPortNumber() {
        return clientSenderPortNumber;
    }

    public void setClientSenderPortNumber(int clientSenderPortNumber) {
        this.clientSenderPortNumber = clientSenderPortNumber;
    }

    // Synchronized getter and setter for clientRecieverPortNumber
    public int getClientRecieverPortNumber() {
        return clientRecieverPortNumber;
    }

    public void setClientRecieverPortNumber(int clientRecieverPortNumber) {
        this.clientRecieverPortNumber = clientRecieverPortNumber;
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

