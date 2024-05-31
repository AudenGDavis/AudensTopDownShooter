package com.audentest;

import com.audentest.SupportClasses.GameClasses.Game;
import com.audentest.SupportClasses.NetworkingClasses.ClientReciever;
import com.audentest.SupportClasses.NetworkingClasses.ClientSender;
import com.audentest.SupportClasses.NetworkingClasses.PlayerConnection;

import java.net.Socket;

import javax.swing.text.PlainDocument;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.google.gson.Gson; 

public class ServerCommunicator
{
    private Game game;
    private int portNumber;
    private String ipAddress = "127.0.0.1";
    private Gson gson = new Gson();
    private ClientReciever clientReciever;
    private PlayerConnection playerConnection;
    private ClientSender clientSender;

    public ServerCommunicator(Game Game, int PortNumber)
    {
        game = Game;
        portNumber = PortNumber;

        try 
        {
            Socket server = new Socket(ipAddress,portNumber);
            BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));

            playerConnection = gson.fromJson(in.readLine(), PlayerConnection.class);
            System.out.println(playerConnection.getGame().toString());
            
            game.importGame(playerConnection.getGame());
            System.out.println(game);
            clientReciever = new ClientReciever(playerConnection.getIpAddress(), playerConnection.getClientRecieverPortNumber(), game,playerConnection.getPlayerID());
            new Thread(clientReciever).start();

            clientSender = new ClientSender(playerConnection.getIpAddress(), playerConnection.getClientSenderPortNumber(), game, playerConnection.getPlayerID());
            new Thread(clientSender).start();
            System.out.println("starting..");
            server.close();
        } 
        catch (Exception e) 
        {

        }
    }

    public PlayerConnection getPlayerConnection()
    {
        return playerConnection;
    }


}
