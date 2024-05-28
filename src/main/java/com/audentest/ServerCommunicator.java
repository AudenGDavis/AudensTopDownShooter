package com.audentest;

import com.audentest.SupportClasses.GameClasses.Game;
import com.audentest.SupportClasses.NetworkingClasses.ClientReciever;
import com.audentest.SupportClasses.NetworkingClasses.ClientSender;
import com.audentest.SupportClasses.NetworkingClasses.PlayerConnection;

import java.net.Socket;
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
    private ClientSender clientSender;

    public ServerCommunicator(Game Game, int PortNumber)
    {
        game = Game;
        portNumber = PortNumber;

        try 
        {
            Socket server = new Socket(ipAddress,portNumber);
            BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));

            PlayerConnection connectionInfo = gson.fromJson(in.readLine(), PlayerConnection.class);
            
            clientReciever = new ClientReciever(connectionInfo.getIpAddress(), connectionInfo.getClientRecieverPortNumber(), game);
            new Thread(clientReciever).start();

            clientSender = new ClientSender(connectionInfo.getIpAddress(), connectionInfo.getClientSenderPortNumber(), game);
            new Thread(clientSender).start();
            System.out.println("starting..");
            server.close();
        } 
        catch (Exception e) 
        {

        }
    }
}
