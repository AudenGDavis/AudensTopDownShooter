package com.audentest;

import com.audentest.SupportClasses.GameClasses.Game;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerCommunicator implements Runnable
{
    Game game;

    public ServerCommunicator(Game Game)
    {
        game = Game;
        
        try 
        {
            Socket server = new Socket("127.0.0.1",42069);
            BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
			PrintWriter out = new PrintWriter(server.getOutputStream(),true);
            out.println("hello server, how are are?");

            System.out.println(in.readLine());
            System.out.println("ending");
        } 
        catch (Exception e) 
        {

        }
    }

    public void run() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }
}
