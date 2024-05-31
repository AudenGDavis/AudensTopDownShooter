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
    public synchronized int getClientSenderPortNumber() {
        return clientSenderPortNumber;
    }

    public synchronized void setClientSenderPortNumber(int clientSenderPortNumber) {
        this.clientSenderPortNumber = clientSenderPortNumber;
    }

    // Synchronized getter and setter for clientRecieverPortNumber
    public synchronized int getClientRecieverPortNumber() {
        return clientRecieverPortNumber;
    }

    public synchronized void setClientRecieverPortNumber(int clientRecieverPortNumber) {
        this.clientRecieverPortNumber = clientRecieverPortNumber;
    }

    // Synchronized getter and setter for ipAddress
    public synchronized String getIpAddress() {
        return ipAddress;
    }

    public synchronized void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    // Synchronized getter and setter for playerID
    public synchronized int getPlayerID() {
        return playerID;
    }

    public synchronized void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public synchronized Game getGame() {
        return game;
    }

    public synchronized void setGame(Game Game) {
        this.game = Game;
    }
}

