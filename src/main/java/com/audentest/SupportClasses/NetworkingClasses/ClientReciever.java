package com.audentest.SupportClasses.NetworkingClasses;

import com.audentest.SupportClasses.GameClasses.Game;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ClientReciever implements Runnable
{
    private String ipAddress;
    private int portNumber;
    private Game game;

    public ClientReciever(String IpAddress, int PortNumber, Game Game)
    {
        ipAddress = IpAddress;
        portNumber = PortNumber;
        game = Game;
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
                System.out.println(in.readLine());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }
    
}
