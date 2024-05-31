package com.audentest.SupportClasses.NetworkingClasses;

import com.audentest.SupportClasses.GameClasses.Game;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import com.google.gson.Gson;
public class ClientSender implements  Runnable
{
    private String ipAddress;
    private int portNumber;
    private Game game;
    private Gson gson;
    private int localPlayer;

    public ClientSender(String IpAddress, int PortNumber, Game Game, int LocalPlayer)
    {
        ipAddress = IpAddress;
        portNumber = PortNumber;
        game = Game;
        gson = new Gson();
        localPlayer = LocalPlayer;
    }


    public void run() 
    {
        Socket server;
        BufferedReader in = null;
		PrintWriter out = null;

        try 
        {
            server = new Socket(ipAddress,portNumber);
            in = new BufferedReader(new InputStreamReader(server.getInputStream()));
		    out = new PrintWriter(server.getOutputStream(),true);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        

        while(true)
        {
            try {
                out.println(gson.toJson(game.getClientPackage(localPlayer),ClientPackage.class));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }
    
}
