package com.audentest;

import com.audentest.SupportClasses.GameClasses.Game;
import com.audentest.SupportClasses.NetworkingClasses.ClientCommunicator;
import com.audentest.SupportClasses.NetworkingClasses.PlayerConnection;

import java.net.Socket;
import java.net.InetSocketAddress;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.google.gson.Gson;
import java.net.SocketAddress;


public class ServerCommunicator
{
    private Game game;
    private int portNumber;
    private Gson gson = new Gson();
    private ClientCommunicator clientCommunicator;
    private PlayerConnection playerConnection;
    private GameEngine engine;

    public ServerCommunicator(String ipAddress,Game Game, int PortNumber) throws Exception
    {
        game = Game;
        portNumber = PortNumber;
        SocketAddress socketAddress = new InetSocketAddress(ipAddress, portNumber);
        Socket server = new Socket();
        server.connect(socketAddress, 2000);


        BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        playerConnection = gson.fromJson(in.readLine(), PlayerConnection.class);

        game.importGame(playerConnection.getGame());
        engine = new GameEngine(ipAddress, playerConnection,game);
        clientCommunicator = new ClientCommunicator(playerConnection.getIpAddress(), playerConnection.getPortNumber(), game,playerConnection.getPlayerID());
        new Thread(clientCommunicator).start();
        server.close();
    }

    public PlayerConnection getPlayerConnection()
    {
        return playerConnection;
    }


}
